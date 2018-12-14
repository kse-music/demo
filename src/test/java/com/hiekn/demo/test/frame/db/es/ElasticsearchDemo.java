package com.hiekn.demo.test.frame.db.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hiekn.demo.test.TestBase;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.lucene.search.function.CombineFunction;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder.FilterFunctionBuilder;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.*;
import java.util.Collections;

public class ElasticsearchDemo extends TestBase {

    private static final String INDEX = "twitter";
    private static final String TYPE = "twitter_data";

    @Resource
    private TransportClient client;

    @Before
    public void init(){

    }

    @Test
    public void testSearch(){
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEX).setTypes(TYPE);

//		searchRequestBuilder.storedFields("name");//不推荐，用source过滤

        SearchResponse response = searchRequestBuilder
                .setQuery(QueryBuilders.termQuery("name", "空调"))
                .setExplain(true)
//				.setFetchSource(new String[]{"name"}, new String[]{})
                .get();

        logger.info("result = {}",response);
    }

    @Test
    public void es6() throws IOException{
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("user", "kimchy"));

        SearchRequest searchRequest = new SearchRequest("twitter").types("tweet");
        searchRequest.source(searchSourceBuilder);
//        SearchResponse response =restHighLevelClient.search(searchRequest);
//        response.getHits().forEach(hit -> logger.info(hit.getSourceAsMap()));

    }

    @Test
    public void testFunction() {

        SearchRequestBuilder srb = client.prepareSearch("");

        ScoreFunctionBuilder<ExponentialDecayFunctionBuilder> exp = ScoreFunctionBuilders.exponentialDecayFunction("count", 0, 100,0,0.5).setWeight(-0.2f);
        ScoreFunctionBuilder<LinearDecayFunctionBuilder> line = ScoreFunctionBuilders.linearDecayFunction("coefficient", 0, 10,0,0.5).setWeight(1f);

        FilterFunctionBuilder[] functions = {
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(exp),
                new FunctionScoreQueryBuilder.FilterFunctionBuilder(line)
        };

        BoolQueryBuilder bool = QueryBuilders.boolQuery();

        FunctionScoreQueryBuilder s = QueryBuilders.functionScoreQuery(bool,functions).boostMode(CombineFunction.SUM);

        srb.setQuery(s).setExplain(true);
    }


    @Test
    public void testScroll() throws Exception {

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.1.157", 9200, "http"));
        restClientBuilder.setMaxRetryTimeoutMillis(30000);//在为同一请求进行多次尝试时应该遵守的超时。默认值为30秒
        RestClient restClient = restClientBuilder.build();

        HttpEntity entity = new NStringEntity("{\"query\": {\"match_all\":{}},\"size\": 10000}", ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(HttpGet.METHOD_NAME, "/gw_prompt_200/_search?scroll=1m", Collections.singletonMap("pretty", "true"),entity);
        String data = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSON.parseObject(data);
        String scrollId = jsonObject.getString("_scroll_id");
        JSONArray jsonArray = jsonObject.getJSONObject("hits").getJSONArray("hits");
        //把导出的结果以JSON的格式写到文件里
        File file = new File("src/docs/data/es.txt");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
        for (int i = 0; i < jsonArray.size(); i++) {
            String json = jsonArray.getString(i);
            out.write(json);
            out.write("\r\n");
        }
        try {
            while (!jsonArray.isEmpty()){
                entity = new NStringEntity("{\"scroll\" : \"1m\",\"scroll_id\" : \""+scrollId+"\"}", ContentType.APPLICATION_JSON);
                response = restClient.performRequest(HttpPost.METHOD_NAME, "/_search/scroll", Collections.emptyMap(), entity);
                data = EntityUtils.toString(response.getEntity());
                jsonObject = JSON.parseObject(data);
                jsonArray = jsonObject.getJSONObject("hits").getJSONArray("hits");
                for (int i = 0; i < jsonArray.size(); i++) {
                    String json = jsonArray.getString(i);
                    out.write(json);
                    out.write("\r\n");
                }
//                entity = new NStringEntity("{\"scroll_id\" : [\""+scrollId+"]\"}", ContentType.APPLICATION_JSON);
//                response = restClient.performRequest(HttpDelete.METHOD_NAME, "/_search/scroll/"+scrollId, Collections.emptyMap());
//                response = restClient.performRequest(HttpDelete.METHOD_NAME, "/_search/scroll", Collections.emptyMap(), entity);
            }
            logger.info("查询结束");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            restClient.close();
        }

    }

    @Test
    public void dataOut(){
        SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE)
                .setQuery(QueryBuilders.matchAllQuery())
                .setSize(10000)
                .setScroll(new TimeValue(600000))
                .setSearchType(SearchType.DEFAULT)
                .get();//setSearchType(SearchType.Scan) 告诉ES不需要排序只要结果返回即可 setScroll(new TimeValue(600000)) 设置滚动的时间
        String scrollid = response.getScrollId();
        try {
            //把导出的结果以JSON的格式写到文件里
            BufferedWriter out = new BufferedWriter(new FileWriter("data/es/data.txt", true));

            //每次返回数据10000条。一直循环查询直到所有的数据都查询出来
            while (true) {
                SearchHits searchHit = response.getHits();
                //再次查询不到数据时跳出循环
                if (searchHit.getHits().length == 0) {
                    break;
                }
                logger.info("查询数量 ：" + searchHit.getHits().length);
                for (int i = 0; i < searchHit.getHits().length; i++) {
                    String json = searchHit.getHits()[i].getSourceAsString();
                    out.write(json);
                    out.write("\r\n");
                }
                response = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000)).get();
            }
            logger.info("查询结束");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dataIn(){
        try {
            //读取刚才导出的ES数据
            BufferedReader br = new BufferedReader(new FileReader("data/es/data.txt"));
            String json = null;
            int count = 0;
            //开启批量插入
            BulkRequestBuilder bulkRequest = client.prepareBulk();
            while ((json = br.readLine()) != null) {
                bulkRequest.add(client.prepareIndex(INDEX, TYPE).setSource(json,XContentType.JSON));
                //每一千条提交一次
                count++;
                if (count % 1000 ==0) {
                    bulkRequest.get();
                    bulkRequest = client.prepareBulk();
                }
            }
            if(bulkRequest.numberOfActions() != 0){
                bulkRequest.get();
            }
            logger.info("插入完毕");
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

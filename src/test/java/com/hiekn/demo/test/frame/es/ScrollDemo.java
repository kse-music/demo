package com.hiekn.demo.test.frame.es;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Collections;

public class ScrollDemo {

    private static final Logger logger = LogManager.getLogger(ScrollDemo.class);

    public static void main(String[] args) throws Exception {

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost("192.168.1.157", 9200, "http"));
        restClientBuilder.setMaxRetryTimeoutMillis(30000);//在为同一请求进行多次尝试时应该遵守的超时。默认值为30秒
        RestClient restClient = restClientBuilder.build();

        HttpEntity entity = new NStringEntity("{\"query\": {},\"size\": 10000}", ContentType.APPLICATION_JSON);
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



}

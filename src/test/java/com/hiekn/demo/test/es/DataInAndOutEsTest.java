package com.hiekn.demo.test.es;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import com.hiekn.demo.test.TestBase;

public class DataInAndOutEsTest extends TestBase{
	
	private static Logger log = LogManager.getLogger(DataInAndOutEsTest.class);  
	
	private static final String INDEX = "twitter";
	private static final String TYPE = "twitter_data";

	@Resource
	private TransportClient client;

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
				log.info("查询数量 ：" + searchHit.getHits().length);
				for (int i = 0; i < searchHit.getHits().length; i++) {
					String json = searchHit.getHits()[i].getSourceAsString();
					out.write(json);
					out.write("\r\n");
				}
				response = client.prepareSearchScroll(scrollid).setScroll(new TimeValue(1000000)).get();
			}
			log.info("查询结束");
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
			log.info("插入完毕");
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}

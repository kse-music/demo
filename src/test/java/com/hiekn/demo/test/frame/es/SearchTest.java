package com.hiekn.demo.test.frame.es;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

import com.hiekn.demo.test.TestBase;

public class SearchTest extends TestBase{
	
	private static Logger log = LogManager.getLogger(SearchTest.class);  
	
	private static final String INDEX = "twitter";
	private static final String TYPE = "twitter_data";
	
	@Resource
	private TransportClient client;

	@Test
	public void search(){
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(INDEX).setTypes(TYPE);
		
//		searchRequestBuilder.storedFields("name");//不推荐，用source过滤
		
		SearchResponse response = searchRequestBuilder
				.setQuery(QueryBuilders.termQuery("name", "空调"))
				.setExplain(true)
//				.setFetchSource(new String[]{"name"}, new String[]{})
				.get();
		
		log.info(response);
		
	}
}

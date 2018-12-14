package com.hiekn.demo.test.frame.db.es;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.hiekn.demo.test.TestBase;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileReader;
import java.util.concurrent.ExecutionException;

public class IndexDemo extends TestBase{
	
	private static final String INDEX = "twitter";
	private static final String TYPE = "twitter_data";
	
	@Resource
	private TransportClient client;

	@Test
	public void create(){
		CreateIndexResponse createIndexResponse = client.admin().indices().prepareCreate(INDEX)
		.addAlias(new Alias("tw"))
		.setSource(loadJson("data/es/aliases.json"), XContentType.JSON)//一步到位
//		.setSettings(Settings.builder().loadFromSource(loadJson("data/settings.json"), XContentType.JSON))//两步到位
//		.addMapping(TYPE,loadJson("data/mapping.json"),XContentType.JSON)
		.get();
		logger.info("result = {}",createIndexResponse.isAcknowledged());
	}
	
	@Test
	public void putMapping(){
		client.admin().indices().preparePutMapping(INDEX).setType(TYPE)
		.setSource(loadJson("data/es/mapping.json"),XContentType.JSON).get();
		
//		client.admin().indices().prepareCreate(INDEX)   
//        .addMapping(TYPE, "{\n" +                
//                "    \"tweet\": {\n" +
//                "      \"properties\": {\n" +
//                "        \"message\": {\n" +
//                "          \"type\": \"string\"\n" +
//                "        }\n" +
//                "      }\n" +
//                "    }\n" +
//                "  }",XContentType.JSON)
//        .get();
	}
	

	public boolean indexExist(){
		IndicesExistsResponse indicesExistsResponse = null;
		try {
			indicesExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(INDEX)).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return indicesExistsResponse.isExists();
	}
	
	@Test
	public void delete(){
		if(indexExist()){
			DeleteIndexResponse dResponse = client.admin().indices().prepareDelete(INDEX).get();
			logger.info("result = {}",dResponse.isAcknowledged());
		}
	}
	
	@Test
	public void modify(){
		client.admin().indices().prepareCreate(INDEX)
//		.setSettings("{\"index\":{\"number_of_replicas\":1}}", XContentType.JSON)
        .setSettings(Settings.builder()             
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        )
        .get();    
	}
	
	@Test
	public void getSetting(){
		GetSettingsResponse response = client.admin().indices().prepareGetSettings(INDEX).get();                           
		for (ObjectObjectCursor<String, Settings> cursor : response.getIndexToSettings()) { 
		    String index = cursor.key;                                                      
		    Settings settings = cursor.value;                                               
		    Integer shards = settings.getAsInt("index.number_of_shards", null);             
		    Integer replicas = settings.getAsInt("index.number_of_replicas", null);  
		    logger.info(index);
		    logger.info("result = {}",settings);
		    logger.info("result = {}",shards);
		    logger.info("result = {}",replicas);
		}
	}
	
	@Test
	public void refresh(){
		client.admin().indices().prepareRefresh(INDEX).get();
	}
	
	private String loadJson(String file) {
		String str = "";
		try {
			str = IOUtils.toString(new FileReader(new File(file)));
		} catch (Exception e) {
		}
		return str;
	}
	
}

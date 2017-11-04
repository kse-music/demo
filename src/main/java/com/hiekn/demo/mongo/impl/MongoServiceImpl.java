package com.hiekn.demo.mongo.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hiekn.demo.bean.result.RestData;
import com.hiekn.demo.mongo.MongoService;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;

@Service
public class MongoServiceImpl implements MongoService{

	@Override
	public MongoClient connect(Integer userId,String ip, Integer port) {
		MongoClientOptions options = MongoClientOptions.builder()
				.connectTimeout(100000)
				.socketTimeout(1000000)
				.build();
		return new MongoClient(new ServerAddress(ip, port), options);
	}
	

	@Override
	public MongoClient connect(Integer userId, String ip, Integer port, String username, String password) {
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return connect(userId,ip,port);
		}
		MongoClientOptions options = MongoClientOptions.builder()
				.connectTimeout(100000)
				.socketTimeout(1000000)
				.build();
		MongoCredential credentials = MongoCredential.createScramSha1Credential(username, username, password.toCharArray());
		return new MongoClient(new ServerAddress(ip, port), Lists.newArrayList(credentials),options);
	}

	@Override
	public List<String> getDbList(Integer userId,String ip, Integer port) {
		MongoClient mongoClient = connect(userId,ip,port);
		final List<String> list = Lists.newArrayList();
		MongoIterable<String> dbs = mongoClient.listDatabaseNames();
		dbs.forEach(new Block<String>() {
			@Override
			public void apply(String name) {
				if(!"local".equals(name)){
					list.add(name);
				}
			}
		});
		close(mongoClient);
		return list;
	}

	@Override
	public List<String> getTableList(Integer userId,String db,String ip, Integer port) {
		MongoClient mongoClient = connect(userId,ip,port);
		final List<String> list = Lists.newArrayList();
		MongoIterable<String> tables = mongoClient.getDatabase(db).listCollectionNames();
		tables.forEach(new Block<String>() {
			@Override
			public void apply(String name) {
				if(!"system.indexes".equals(name)){
					list.add(name);
				}
			}
		});
		close(mongoClient);
		return list;
	}

	@Override
	public RestData<Map<String,Object>> getData(Integer userId,String db, String table,Integer pageNo,Integer pageSize,String ip, Integer port) {
		MongoClient mongoClient = connect(userId,ip,port);
		MongoCollection<Document> collection = mongoClient.getDatabase(db).getCollection(table);
		long count = collection.count();
		final List<Map<String,Object>> rd = Lists.newArrayList();
		collection.find().skip((pageNo-1)*pageSize).limit(pageSize).forEach(new Block<Document>(){
			@Override
			public void apply(final Document document) {
				Map<String,Object> map = Maps.newHashMap();
				map.put("_id", document.get("_id").toString());
				for(Entry<String, Object> m :document.entrySet()){
					if(!"_id".equals(m.getKey())){
						map.put(m.getKey(), m.getValue());
					}
				}
				rd.add(map);
			}
		});
		close(mongoClient);
		return new RestData<Map<String,Object>>(rd,Long.valueOf(count).intValue());
	}

	@Override
	public void close(MongoClient mongoClient) {
		if(mongoClient != null){
			mongoClient.close();
		}
	}

}

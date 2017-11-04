package com.hiekn.demo.test;

import javax.annotation.Resource;

import org.bson.Document;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoTest extends TestBase{
	@Resource
	private MongoClient mongoClient;

	@Test
	public void test(){
		MongoCollection<Document> col = mongoClient.getDatabase("test_db").getCollection("test_coll");
		Document doc = new Document().append("title", "MongoDB").
				append("description", "database").
				append("likes", System.nanoTime()).
				append("tags", Lists.newArrayList("数据")).
				append("url", "//www.w3cschool.cn/mongodb/").
				append("by", "w3cschool.cn");
		col.insertOne(doc);
	}
	
	@Test
	public void getFields(){
		MongoDatabase db =  mongoClient.getDatabase("data");
		MongoCollection<Document> table = db.getCollection("conference_data");
		String map = "function() {for (var key in this) { emit(key, null); } }";
		String reduce = "function(key, stuff) { return null; }";
		MapReduceIterable<Document> docs = table.mapReduce(map, reduce);
		for (Document document : docs) {
			System.out.println(document.get("_id"));
		}
	}
}

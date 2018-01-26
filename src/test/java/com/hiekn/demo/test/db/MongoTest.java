package com.hiekn.demo.test.db;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import com.mongodb.MongoClient;
import com.mongodb.client.MapReduceIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

import javax.annotation.Resource;

public class MongoTest extends TestBase {
	@Resource
	private MongoClient mongoClient;

	@Test
	public void test(){
		MongoCollection<Document> col = mongoClient.getDatabase("test_db").getCollection("test_coll");
		Document doc = new Document("title", "MongoDB").
				append("description", "database").
				append("likes", System.nanoTime()).
				append("tags", Lists.newArrayList("数据")).
				append("url", "//www.w3cschool.cn/mongodb/").
				append("by", "w3cschool.cn");
		col.insertOne(doc);
	}
	
	@Test
	public void getFields(){
//		MongoCollection<Document> table = mongoClient.getDatabase("test_db").getCollection("test_coll");
//		String map = "function() {for (var key in this) { emit(key, null); } }";
//		String reduce = "function(key, stuff) { return null; }";
//		MapReduceIterable<Document> docs = table.mapReduce(map, reduce);
//		for (Document document : docs) {
//			System.out.println(document.get("_id"));
//		}
        for (String name : mongoClient.getDatabase("kg_attribute_definition").listCollectionNames()) {
            if(name.indexOf("kg_ct_attribute") == -1){
                mongoClient.getDatabase("kg_attribute_definition").getCollection(name).drop();
            }
        }
    }

	@Test
    public void find(){
        MongoCollection<Document> table = mongoClient.getDatabase("test_db").getCollection("test_coll");
        for (Document document : table.find()) {
            System.out.println(document);
        }
        table.findOneAndUpdate(Filters.eq("title","MongoDB"),new Document("$set",new Document("likes",200)));
//        table.deleteMany(Filters.eq("5a3883e172a15434b89671e3"));

    }
}

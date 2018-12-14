package com.hiekn.demo.test.frame.db.mongo;

import com.google.common.collect.Lists;
import com.hiekn.demo.test.TestBase;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class MongoDemo extends TestBase {

    @Resource
    private MongoClient mongoClient;

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

    @Test
    public void basic() {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectTimeout(100000)
                .socketTimeout(1000000)
                .build();
        MongoClient client = new MongoClient(new ServerAddress("127.0.0.1", 19130), options);

        client.getDatabase("stdaily_test001_153485135122340208").getCollection("_domain_dic")
                .find(Filters.in("entityId", Lists.newArrayList(37,38))).forEach((Consumer<? super Document>) d ->{
            System.out.println(d);
        });

        MongoCollection<Document> col = client.getDatabase("test_db").getCollection("test_coll");
        Document doc = new Document("title", "MongoDB").
                append("description", "database").
                append("likes", System.nanoTime()).
                append("tags", Lists.newArrayList("数据")).
                append("url", "//www.w3cschool.cn/mongodb/").
                append("by", "w3cschool.cn");
        col.insertOne(doc);
    }

    @Test
    public void excel() throws IOException {
        MongoCollection<Document> col = mongoClient.getDatabase("patent_kg_b").getCollection("patent_info");
        MongoCursor<Document> cursor = col.find().limit(200).iterator();
        int count = 0;
        Set<String> dic = new HashSet<>();
        try(FileWriter fw = new FileWriter("data/doc.txt");MongoCursor<Document> dcursor = col.find().limit(1).iterator()){
            while (dcursor.hasNext()) {
                Document doc = dcursor.next();
                for (String string : doc.keySet()) {
                    dic.add(string);
                }
                dic.add("agencyOrg");
                dic.add("agencyPerson");
            }
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                System.out.println(doc.toJson());
                String title = "";
                String result = "";
                for (String key : dic) {
                    title = title + key + "@";
                    String s = doc.get(key) == null ? "" : doc.get(key).toString();
                    result = result + s + "@";
                }
                if (count == 0) {
                    fw.write(title + "\r\n");
                    fw.flush();
                }
                fw.write(result + "\r\n");
                fw.flush();
                count++;
            }
        }
    }
}

package com.hiekn.demo.test.frame.db;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.hiekn.demo.test.TestBase;
import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class Mongo2Excel extends TestBase {
	@Resource
	private MongoClient client;
	
	@Test
	public void t1() throws IOException {
		MongoCollection<Document> col = client.getDatabase("patent_kg_b").getCollection("patent_info");
		MongoCursor<Document> cursor = col.find().limit(200).iterator();
		FileWriter fw = new FileWriter("data/doc.txt");
		int count = 0;
		Set<String> dic = new HashSet<String>();
		MongoCursor<Document> dcursor = col.find().limit(1).iterator();
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
		fw.close();
	}
}

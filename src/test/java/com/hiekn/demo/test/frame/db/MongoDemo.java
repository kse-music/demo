package com.hiekn.demo.test.frame.db;

import com.google.common.collect.Lists;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.function.Consumer;

public class MongoDemo {

    public static void main(String[] args) {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectTimeout(100000)
                .socketTimeout(1000000)
                .build();
        MongoClient client = new MongoClient(new ServerAddress("127.0.0.1", 19130), options);

        client.getDatabase("stdaily_test001_153485135122340208").getCollection("_domain_dic")
                .find(Filters.in("entityId", Lists.newArrayList(37,38))).forEach((Consumer<? super Document>) d ->{
            System.out.println(d);
        });
    }
}

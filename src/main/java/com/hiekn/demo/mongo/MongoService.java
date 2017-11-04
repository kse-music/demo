package com.hiekn.demo.mongo;

import java.util.List;
import java.util.Map;

import com.hiekn.demo.bean.result.RestData;
import com.mongodb.MongoClient;

/**
 * Created by DH on 2017/4/7.
 */
public interface MongoService {

	MongoClient connect(Integer userId,String ip, Integer port);
	MongoClient connect(Integer userId,String ip, Integer port,String username,String password);
    void close(MongoClient mongoClient);
    List<String> getDbList(Integer userId,String ip, Integer port);
    List<String> getTableList(Integer userId,String db,String ip, Integer port);
    RestData<Map<String,Object>> getData(Integer userId,String db, String table,Integer pageNo,Integer pageSize,String ip, Integer port);
}


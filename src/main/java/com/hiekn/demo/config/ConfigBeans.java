package com.hiekn.demo.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration  
public class ConfigBeans {  
	
	@Value("${es_name}")  
	private String es_name;
	@Value("#{'${es_ip}'.split(',')}")
	private List<String> es_ip;
	@Value("${es_port}")
	private Integer es_port;
	
	@Value("${mongo_ip}")  
	private String mongo_ip;
	@Value("${mongo_port}")
	private Integer mongo_port;
	
    
	@Value("${redis_ip}")
	private String redis_ip;
	@Value("${redis_port}")
	private Integer redis_port;
	@Value("${redis_max_total}")
	private Integer max_toal;
	@Value("${redis_min_idle}")
	private Integer min_idle;
	@Value("${redis_max_idle}")
	private Integer max_idle;
	@Value("${redis_test_on_borrow}")
	private Boolean test_on_borrow;
	@Value("${redis_max_wait_millis}")
	private Integer max_wait_millis;
	@Value("${redis_timeout}")
	private Integer timeout;
	
	@Lazy
    @Bean
    public TransportClient esClient(){ 
    	TransportClient client = null;
    	if(es_name != null && !"".equals(es_name)){
			Settings settings = Settings.builder().put("cluster.name", es_name).build();
			client = new PreBuiltTransportClient(settings);
		}else{
			client = new PreBuiltTransportClient(Settings.EMPTY);
		}
		try {
			for (String ip : es_ip) {
				client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ip), es_port));
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
        return client;  
    } 
    
    @Lazy
    @Bean
    public MongoClient mongoClient(){  
    	MongoClientOptions options = MongoClientOptions.builder().connectTimeout(100000).maxWaitTime(1000000).build();
    	return new MongoClient(new ServerAddress(mongo_ip, mongo_port), options);
    }  
    
    @Lazy
    @Bean
    public JedisPool jedisPool(){
		JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(max_toal);
        config.setMinIdle(min_idle);
        config.setMaxIdle(max_idle);
        config.setMaxWaitMillis(max_wait_millis);
        config.setTestOnBorrow(test_on_borrow);
        return new JedisPool(config, redis_ip,redis_port,timeout);
	}
  
}  

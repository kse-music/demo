package com.hiekn.demo.config;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
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
    public RestHighLevelClient esClient() {
        HttpHost[] httpHosts = new HttpHost[es_ip.size()];
        for (int i = 0; i < es_ip.size(); i++) {
            httpHosts[i] = new HttpHost(es_ip.get(i), es_port,"http");
        }
        return new RestHighLevelClient(builder(httpHosts));
    }

    private RestClientBuilder builder(HttpHost... httpHosts) {
        RestClientBuilder builder = RestClient.builder(httpHosts);
        Header[] defaultHeaders = new Header[]{new BasicHeader("x", "y")};
        builder.setDefaultHeaders(defaultHeaders);
        builder.setRequestConfigCallback((RequestConfig.Builder requestConfigBuilder) -> requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000));
        final CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("user", "password"));
        builder.setHttpClientConfigCallback((HttpAsyncClientBuilder httpAsyncClientBuilder) ->
                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        builder.setNodeSelector((Iterable<Node> nodes) -> {
            boolean foundOne = false;
            for (Node node : nodes) {
//                String rackId = node.getAttributes().get("rack_id").get(0);
//                if ("rack_one".equals(rackId)) {
//                    foundOne = true;
//                    break;
//                }
            }
            if (foundOne) {
                Iterator<Node> nodesIt = nodes.iterator();
                while (nodesIt.hasNext()) {
                    Node node = nodesIt.next();
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("rack_one".equals(rackId) == false) {
                        nodesIt.remove();
                    }
                }
            }
        });
        return builder;
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

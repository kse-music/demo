package com.hiekn.demo.test.java.redis;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * 消息生成者
 */
public class Publish extends TestBase{
    public final static String QUEUE_NAME="rabbitMQ.test";
    
    @Resource
    private JedisPool jedisPool;
    
    @Test
    public void publish() throws InterruptedException{
    	Jedis jedis = jedisPool.getResource();
    	jedis.publish(QUEUE_NAME, "redis发布订阅");
    	jedis.close();
    }

}

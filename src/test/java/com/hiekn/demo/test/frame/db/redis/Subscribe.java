package com.hiekn.demo.test.frame.db.redis;

import com.hiekn.demo.test.TestBase;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;

/**
 * 消费者
 * @author
 *
 */
public class Subscribe extends TestBase{
	
	public final static String QUEUE_NAME="rabbitMQ.test";

	@Resource
	private JedisPool jedisPool;

	@Test
	public void subscribe(){
		Jedis jedis = jedisPool.getResource();
		JedisPubSub jedisPubSub=new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println(message);
			}
		};
		jedis.subscribe(jedisPubSub, QUEUE_NAME);//subscribe是一个阻塞的方法,在取消订阅该频道前，会一直阻塞在这
		jedis.close();
	}
}
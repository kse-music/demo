package com.hiekn.demo.config;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisHelper {
	
	@Resource
	private JedisPool jedisPool;
	
	public synchronized boolean add(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String ok = jedis.set(key, value);
		close(jedis);
		return Objects.equals("OK", ok);
	}

	public synchronized boolean add(String key, String value, long time) {
		Jedis jedis = jedisPool.getResource();
		String ok = jedis.set(key, value,"NX","EX",time);
		close(jedis);
		return Objects.equals("OK", ok);
	}


	public synchronized boolean del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long ok = jedis.del(key);
		close(jedis);
		return Objects.equals(1L, ok);
	}

	public synchronized String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String token =  jedis.get(key);
		close(jedis);
		return token;
	}

	public void close(Jedis jedis) {
		if(Objects.nonNull(jedis)){
			jedis.close();
		}
	}

}

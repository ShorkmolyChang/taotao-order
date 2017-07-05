package com.taotao.order.component.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.order.component.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisClientSingle implements JedisClient {

	@Autowired
	private JedisPool jedisPool;

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub

		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String key, String item, String value) {
		// TODO Auto-generated method stub

		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(key, item, value);
		jedis.close();
		return result;
	}

	@Override
	public String hget(String key, String item) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		String result = jedis.hget(key, item);
		jedis.close();
		return result;
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.decr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String key, String item) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.hdel(key, item);
		jedis.close();
		return result;
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		Jedis jedis=jedisPool.getResource();
		Long result=jedis.del(key);
		jedis.close();
		return result;
	}

}

package com.taotao.order.component.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.order.component.JedisClient;

import redis.clients.jedis.JedisCluster;

public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.set(key, value);
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.get(key);
	}

	@Override
	public Long hset(String key, String item, String value) {
		// TODO Auto-generated method stub
		return jedisCluster.hset(key, item, value);
	}

	@Override
	public String hget(String key, String item) {
		// TODO Auto-generated method stub
		return jedisCluster.hget(key, item);
	}

	@Override
	public Long incr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.incr(key);
	}

	@Override
	public Long decr(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.decr(key);
	}

	@Override
	public Long expire(String key, int second) {
		// TODO Auto-generated method stub
		return jedisCluster.expire(key, second);
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.ttl(key);
	}

	@Override
	public Long hdel(String key, String item) {
		// TODO Auto-generated method stub
		return jedisCluster.hdel(key, item);
	}

	@Override
	public Long del(String key) {
		// TODO Auto-generated method stub
		return jedisCluster.del(key);
	}

}

package com.org.service.categoryclassification.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.org.service.categoryclassification.model.ProductClassification;

@Repository
public class RedisProductRepository {

	private HashOperations<String, String, ProductClassification> hashOperations;
	private RedisTemplate redisTemplate;

	public RedisProductRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(ProductClassification response) {
		hashOperations.put("PRODUCTCLASSIFICATION", response.getReferencekey(), response);
	}

	public ProductClassification findById(String referencekey) {
		return (ProductClassification) hashOperations.get("PRODUCTCLASSIFICATION", referencekey);
	}

	public void delete(String referencekey) {
		hashOperations.delete("PRODUCTCLASSIFICATION", referencekey);
	}

	public void flushAll() {
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
		connection.flushAll();
	}
}

package com.org.service.categoryclassification.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.org.service.categoryclassification.model.ProductClassificationResponse;

@Repository
public class RedisProductRepository {

	private HashOperations<String, String, ProductClassificationResponse> hashOperations;
	private RedisTemplate redisTemplate;

	public RedisProductRepository(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

	public void save(ProductClassificationResponse response) {
		hashOperations.put("PRODUCTCLASSIFICATION", response.getReferencekey(), response);
	}

	public ProductClassificationResponse findById(String referencekey) {
		return (ProductClassificationResponse) hashOperations.get("PRODUCTCLASSIFICATION", referencekey);
	}

	public void delete(String referencekey) {
		hashOperations.delete("PRODUCTCLASSIFICATION", referencekey);
	}


}

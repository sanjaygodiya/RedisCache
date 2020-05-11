package com.org.service.categoryclassification.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.service.categoryclassification.model.ProductClassification;
import com.org.service.categoryclassification.redis.RedisProductRepository;
import com.org.service.categoryclassification.service.ProductClassificationService;

@RestController
@RequestMapping({ "/restservice" })
public class ProductClassificationController {

	@Autowired
	private ProductClassificationService productClassificationService;
	@Autowired
	private RedisProductRepository redisProductRepository;
	private List<ProductClassification> productClassificationList;
	private static final Logger logger = LoggerFactory.getLogger(ProductClassificationController.class);


	@RequestMapping(value = { "/getproductclassification" }, method = {
			org.springframework.web.bind.annotation.RequestMethod.GET })
	public ProductClassification getProductClassification(@RequestParam("prodref") String prodref) {
		ProductClassification response = new ProductClassification(null, null, null, null);
		String referencekey = "null";
		String name = "null";
		String tagDescription = "null";
		String classification = "null";
		if (redisProductRepository.findById(prodref) != null) {
			response = redisProductRepository.findById(prodref);
			logger.debug(prodref + " retrieved from cache");
			logger.info("referencekey : " + response.getReferencekey());
			logger.info("name : " + response.getName());
			logger.info("tagDescription : " + response.getTagdescription());
			logger.info("classification : " + response.getClassification());
		} else {
			this.productClassificationList = this.productClassificationService.getCategoryClass(prodref.trim().toUpperCase());
			try {
				referencekey = ((ProductClassification) this.productClassificationList.get(0)).getReferencekey().toString();
				name = ((ProductClassification) this.productClassificationList.get(0)).getName().toString();
				tagDescription = ((ProductClassification) this.productClassificationList.get(0)).getTagdescription().toString();
				classification = ((ProductClassification) this.productClassificationList.get(0)).getClassification().toString();
				response.setReferencekey(referencekey);
				response.setName(name);
				response.setTagdescription(tagDescription);
				response.setClassification(classification);
				redisProductRepository.save(response);
				logger.debug(prodref + " stored into cache");
				logger.info("referencekey : " + referencekey);
				logger.info("name : " + name);
				logger.info("tagDescription : " + tagDescription);
				logger.info("classification : " + classification);
			} catch (NullPointerException ex1) {
				logger.debug("Handeling Exception ex1 : " + ex1.getMessage());
				referencekey = ((ProductClassification) this.productClassificationList.get(0)).getReferencekey().toString();
				name = ((ProductClassification) this.productClassificationList.get(0)).getName().toString();
				classification = ((ProductClassification) this.productClassificationList.get(0)).getClassification().toString();
				response.setReferencekey(referencekey);
				response.setName(name);
				response.setTagdescription(tagDescription);
				response.setClassification(classification);
				redisProductRepository.save(response);
				logger.debug(prodref + " stored into cache");
				logger.info("referencekey : " + referencekey);
				logger.info("name : " + name);
				logger.info("tagDescription : " + tagDescription);
				logger.info("classification : " + classification);
			} catch (IndexOutOfBoundsException ex2) {
				logger.debug("Handeling Exception ex2 : " + ex2.getMessage());
				response.setReferencekey(referencekey);
				response.setName(name);
				response.setTagdescription(tagDescription);
				response.setClassification(classification);
				redisProductRepository.save(response);
				logger.debug(prodref + " stored into cache");
				logger.info("referencekey : " + referencekey);
				logger.info("name : " + name);
				logger.info("tagDescription : " + tagDescription);
				logger.info("classification : " + classification);
			}
		}
		return response;
	}

}

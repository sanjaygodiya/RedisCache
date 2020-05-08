package com.org.service.categoryclassification.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.service.categoryclassification.dao.ProductClassificationDAO;
import com.org.service.categoryclassification.model.ProductClassification;

@Service
public class ProductClassificationService {
	@Autowired
	private ProductClassificationDAO productClassificationDAO;

	public List<ProductClassification> getCategoryClass(String prodref) {
		return this.productClassificationDAO.findProductClassification(prodref);
	}

}

package com.org.service.categoryclassification.dao;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.org.service.categoryclassification.model.ProductClassification;

@Repository
public class ProductClassificationDAO {
	private static final Logger logger = LoggerFactory.getLogger(ProductClassificationDAO.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private List<ProductClassification> productClassificationList;

	public List<ProductClassification> findProductClassification(final String prodref) {
		logger.info("Product referencekey : " + prodref);
		final String productClassSql = "select referencekey,name,tagdescription,classification from PRODUCT_CLASSIFICATION where referencekey='"
				+ prodref + "'";
		logger.debug("Product Classification SQL : " + productClassSql);
		try {
			this.productClassificationList = this.jdbcTemplate.query(productClassSql,
					(rs, rowNum) -> new ProductClassification(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)));
		} catch (BadSqlGrammarException e) {
			logger.info("DataAccessException->" + e.getMessage() + ",SQL Code->"
					+ ((SQLException) e.getCause()).getErrorCode());
		}
		return this.productClassificationList;
	}
}

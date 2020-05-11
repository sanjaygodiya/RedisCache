package com.org.service.categoryclassification.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductClassification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2367178775747167326L;
	private String referencekey;
	private String name;
	private String tagdescription;
	private String classification;

}

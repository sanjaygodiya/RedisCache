package com.org.service.categoryclassification.model;

public class ProductClassification {
	private String referencekey;
	private String name;
	private String tagdescription;
	private String classification;

	public ProductClassification(String referencekey, String name, String tagdescription, String classification) {
		this.referencekey = referencekey;
		this.name = name;
		this.tagdescription = tagdescription;
		this.classification = classification;
	}

	public String getReferencekey() {
		return this.referencekey;
	}

	public void setReferencekey(String referencekey) {
		this.referencekey = referencekey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTagdescription() {
		return this.tagdescription;
	}

	public void setTagdescription(String tagdescription) {
		this.tagdescription = tagdescription;
	}

	public String getClassification() {
		return this.classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
}

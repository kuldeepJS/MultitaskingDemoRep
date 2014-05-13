package org.infra.opti.multiTasking.models;

import java.util.ArrayList;

public class Product {
	
	private int productId;
	private String name;
	private String description;
	
	private Category category;
	
	private ArrayList<ProductVersion> versionInformation;

	public Product(int productId, String name, String description){
		this.productId = productId;
		this.name = name;
		this.description = description;
	}
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ArrayList<ProductVersion> getVersionInformation() {
		return versionInformation;
	}

	public void setVersionInformation(ArrayList<ProductVersion> versionInformation) {
		this.versionInformation = versionInformation;
	}
	
}

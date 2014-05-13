package org.infra.opti.multiTasking.automator;

import java.util.Date;

import org.infra.opti.multiTasking.models.Category;

public class GeneratedCategory extends Category {
	private int generatedCategoryId;
	private boolean generatedDynamically;
	private String generatedName;
	private Date generatedOn;
	
	public GeneratedCategory(Category toBeWrapped, int id, String name){
		super();
		setCategoryId(toBeWrapped.getCategoryId());
		setCategoryName(toBeWrapped.getCategoryName());
		setCategoryDescription(toBeWrapped.getCategoryDescription());
		generatedCategoryId = id;
		generatedName = name;
		generatedOn = new Date();
		generatedDynamically = false;
	}
	
	public GeneratedCategory(int id, String name){
		super();
		setCategoryId(id);
		setCategoryName(name + "_" + id);
		setCategoryDescription("Generated dynamically by thread " + name);
		generatedCategoryId = id;
		generatedName = name;
		generatedOn = new Date();
		generatedDynamically = false;
	}
	
	public int getGeneratedCategoryId() {
		return generatedCategoryId;
	}
	public void setGeneratedCategoryId(int generatedCategoryId) {
		this.generatedCategoryId = generatedCategoryId;
	}
	public boolean isGeneratedDynamically() {
		return generatedDynamically;
	}
	public void setGeneratedDynamically(boolean generatedDynamically) {
		this.generatedDynamically = generatedDynamically;
	}
	public String getGeneratedName() {
		return generatedName;
	}
	public void setGeneratedName(String generatedName) {
		this.generatedName = generatedName;
	}
	public Date getGeneratedOn() {
		return generatedOn;
	}
	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}
	
	
}

package org.infra.opti.multiTasking.automator;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.infra.opti.multiTasking.models.Category;

public class CategoryGenerator implements Callable<Vector<GeneratedCategory>> {

	private static int categoryId = 0;
	private int maxNumberOfCategories;
	private String threadName;
	private static Vector<GeneratedCategory> categoriesGenerated;
	
	static{
		categoriesGenerated = new Vector<GeneratedCategory>();
	}
	
	public CategoryGenerator(int maxNumberOfCategories){
		this.maxNumberOfCategories = maxNumberOfCategories;
		threadName = Thread.currentThread().getName();
	}
	
	@Override
	public Vector<GeneratedCategory> call() throws Exception {
		Vector<GeneratedCategory> categories = new Vector<GeneratedCategory>(maxNumberOfCategories);
		String threadName = Thread.currentThread().getName();
		System.out.println("Category created by thread " + Thread.currentThread().getId());
		
		for(int categoryIndex=0; categoryIndex < maxNumberOfCategories; categoryIndex++){
			categories.add(new GeneratedCategory(getCategoryId(), threadName));
		}
		
		categoriesGenerated.addAll(categories);
		
		return categories;
	}
	
	public synchronized int getCategoryId(){
		System.out.println("Category Generated -> " + (categoryId + 1));
		return ++categoryId;
	}
	
	public synchronized void appendCategories(ArrayList<Category> categories){
		for(int catIndex = 0; catIndex<categories.size(); catIndex++)
			categoriesGenerated.add(new GeneratedCategory(categories.get(catIndex), getCategoryId(), threadName));
	}
	
	public static Vector<GeneratedCategory> getGeneratedCategories(){
		return categoriesGenerated;
	}
}

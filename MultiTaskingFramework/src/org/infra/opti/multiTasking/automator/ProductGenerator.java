package org.infra.opti.multiTasking.automator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.infra.opti.multiTasking.models.Product;

public class ProductGenerator implements Callable<Vector<GeneratedProduct>>{

	private static int genProductId = 0;
	private int maxNumberOfProducts;
	private static Vector<GeneratedProduct> productsGenerated;
	Random indexGenerator;
	private String threadName;
	private int maximumNumberOfVersions;
	
	static{
		productsGenerated = new Vector<GeneratedProduct>();
	}
	
	public ProductGenerator(int numberOfProducts, int maximumNumberOfVersions){
		maxNumberOfProducts = numberOfProducts;
		indexGenerator = new Random();
		threadName = Thread.currentThread().getName();
		this.maximumNumberOfVersions = maximumNumberOfVersions;
	}
	
	@Override
	public Vector<GeneratedProduct> call() throws Exception {
		Vector<GeneratedProduct> products = new Vector<GeneratedProduct>(maxNumberOfProducts);
		System.out.println("Products created by thread " + Thread.currentThread().getId());
		for(int prdIndex = productsGenerated.size() - 1; prdIndex <  maxNumberOfProducts; prdIndex++){
			GeneratedCategory randomCat = getRandomCategory();
			System.out.println("Retrieved category at: " + randomCat.getCategoryName());
			GeneratedProduct genProduct = new GeneratedProduct(getGenProductId(), randomCat, threadName);
			genProduct.setRequiredVersions(getVersionNumbers());
			products.add(genProduct);
		}
		
		productsGenerated.addAll(products);
		
		return products;
	}
	
	private GeneratedCategory getRandomCategory(){
		int catIndex = indexGenerator.nextInt();
		if(catIndex < 0)
			catIndex *= -1;
		System.out.println("Retrieving category at: " + catIndex);
		int size = CategoryGenerator.getGeneratedCategories().size();
		return CategoryGenerator.getGeneratedCategories().get(catIndex % size);
	}
	
	private int getVersionNumbers(){
		int verNumber = indexGenerator.nextInt();
		return (verNumber % maximumNumberOfVersions) + 1;
	}
	
	public synchronized int getGenProductId(){
		System.out.println("Product Generated -> " + (genProductId + 1));
		return ++genProductId;
	}
	
	public synchronized void appendProducts(ArrayList<Product> products){
		for(int prdIndex = 0; prdIndex < products.size(); prdIndex++)
			productsGenerated.add(new GeneratedProduct(products.get(prdIndex), getGenProductId()));
	}
	
	public static Vector<GeneratedProduct> getGeneratedProducts(){
		return productsGenerated;
	}

}

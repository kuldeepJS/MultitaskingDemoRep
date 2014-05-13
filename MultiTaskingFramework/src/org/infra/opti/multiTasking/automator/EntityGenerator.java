package org.infra.opti.multiTasking.automator;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.infra.opti.multiTasking.models.Category;
import org.infra.opti.multiTasking.models.Dimensions;
import org.infra.opti.multiTasking.models.PackType;
import org.infra.opti.multiTasking.models.Product;
import org.infra.opti.multiTasking.utility.ServerInformation;

public class EntityGenerator {
	CategoryGenerator cg;
	DimensionGenerator dg;
	ProductGenerator pg;
	ProductVersionGenerator pvg;
	
	int[] widthRange = {1,20};
	int[] heightRange = {1,20};
	int[] depthRange = {1,20};
	
	int[] priceRange = {10, 5000};
	int[] profitRange = {10, 5000};
	int[] revenueRange = {10, 5000};
	
	public EntityGenerator(int numberOfCategories, int maxDimensions, int numberOfProducts, int maximumNumberOfVersions, ArrayList<PackType> packs){
		cg = new CategoryGenerator(numberOfCategories);
		dg = new DimensionGenerator(maxDimensions, widthRange, heightRange, depthRange);
		pg = new ProductGenerator(numberOfProducts, maximumNumberOfVersions);
		pvg = new ProductVersionGenerator(packs, priceRange, profitRange, revenueRange);
	}
	
	public void addCategories(ArrayList<Category> categories){
		cg.appendCategories(categories);
	}
	
	public void addProducts(ArrayList<Product> products){
		pg.appendProducts(products);
	}
	
	public void addDimensions(ArrayList<Dimensions> dimensions){
		dg.appendDimensions(dimensions);
	}
	
	public void startGeneration(){
		ExecutorService executor = Executors.newFixedThreadPool(ServerInformation.getServerInformation().getMaxNumberOfThreads(false, 10, 10));
		
		FutureTask<Vector<GeneratedCategory>> futureCategories = new FutureTask<Vector<GeneratedCategory>>(cg);
		executor.execute(futureCategories);
		
		
		FutureTask<Vector<GeneratedDimension>> futureDimensions = new FutureTask<Vector<GeneratedDimension>>(dg);
		executor.execute(futureDimensions);
		Vector<GeneratedCategory> generatedCategories = null;
		Vector<GeneratedDimension> generatedDimensions = null;
		try {
			System.out.println("Waiting for categories to get...");
			generatedCategories = futureCategories.get();
			System.out.println("Waiting for dimensions to get...");
			generatedDimensions = futureDimensions.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
		FutureTask<Vector<GeneratedProduct>> futureProducts = new FutureTask<Vector<GeneratedProduct>>(pg);
		executor.execute(futureProducts);
		Vector<GeneratedProduct> generatedProducts = null;
		try {
			generatedProducts = futureProducts.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		if(generatedCategories != null || generatedDimensions != null || generatedProducts != null){
			System.out.println("Data generated successfully !");
			System.out.println("Writing data to a file...");
			serializeCategories("Category.xml", generatedCategories);
			serializeDimensions("Dimension.xml", generatedDimensions);
			serializeProducts("Product.xml", generatedProducts);
		}
		else{
			System.out.println("Error creating data...");
		}
	}
	
	private void serializeCategories(String fileLocation, Vector<GeneratedCategory> generated){
		FileOutputStream os;
		try {
			os = new FileOutputStream(fileLocation);
			XMLEncoder encoder = new XMLEncoder(os);
	        encoder.writeObject(generated);
	        encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void serializeDimensions(String fileLocation, Vector<GeneratedDimension> generated){
		FileOutputStream os;
		try {
			os = new FileOutputStream(fileLocation);
			XMLEncoder encoder = new XMLEncoder(os);
	        encoder.writeObject(generated);
	        encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void serializeProducts(String fileLocation, Vector<GeneratedProduct> generated){
		FileOutputStream os;
		try {
			os = new FileOutputStream(fileLocation);
			XMLEncoder encoder = new XMLEncoder(os);
	        encoder.writeObject(generated);
	        encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

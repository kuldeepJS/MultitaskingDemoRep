package org.infra.opti.multiTasking.automator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import org.infra.opti.multiTasking.models.PackType;
import org.infra.opti.multiTasking.models.ProductVersion;

public class ProductVersionGenerator {

	private Random indexGenerator;
	private Vector<GeneratedProduct> products;
	private ArrayList<PackType> packs;
	private static int genVersionId = 0;
	
	private double[] salesDump;
	
	private int priceMin;
	private int priceMax;
	
	private int profitMin;
	private int profitMax;
	
	private int revenueMin;
	private int revenueMax;
	
	private static Vector<GeneratedProductVersion> productVersions;
	static{
		productVersions = new Vector<GeneratedProductVersion>();
	}
	
	public ProductVersionGenerator(ArrayList<PackType> packs, int[] priceRange, int[] profitRange, int[] revenueRange){
		indexGenerator = new Random();
		products = ProductGenerator.getGeneratedProducts();
		this.packs = packs;
		
		salesDump = new double[3];
		
		/*
		 * Setting the width range
		 */
		if(priceRange == null){
			priceMin = 0;
			priceMax = 10;
		}
		else if(priceRange.length == 2){
			priceMin = priceRange[0];
			priceMax = priceRange[1];
		}
		else if(priceRange.length >= 0){
			priceMin = priceRange[0];
			priceMax = priceRange[priceRange.length - 1];
		}
		//Final validation for width
		if(priceMin == priceMax)
			priceMax = priceMin + 10;
		else if(priceMax < priceMin){
			priceMax = priceMax ^ priceMin;
			priceMin = priceMax ^ priceMin;
			priceMax = priceMax ^ priceMin;
		}
		
		/*
		 * Setting the height range
		 */
		if(profitRange == null){
			profitMin = 0;
			profitMax = 10;
		}
		else if(profitRange.length == 2){
			profitMin = profitRange[0];
			profitMax = profitRange[1];
		}
		else if(profitRange.length >= 0){
			profitMin = profitRange[0];
			profitMax = profitRange[profitRange.length - 1];
		}
		//Final validation for height
		if(profitMin == profitMax)
			profitMax = profitMin + 10;
		else if(profitMax < profitMin){
			profitMax = profitMax ^ profitMin;
			profitMin = profitMax ^ profitMin;
			profitMax = profitMax ^ profitMin;
		}
		
		/*
		 * Setting the depth range
		 */
		if(revenueRange == null){
			revenueMin = 0;
			revenueMax = 10;
		}
		else if(revenueRange.length == 2){
			revenueMin = revenueRange[0];
			revenueMax = revenueRange[1];
		}
		else if(revenueRange.length >= 0){
			revenueMin = revenueRange[0];
			revenueMax = revenueRange[revenueRange.length - 1];
		}
		//Final validation for depth
		if(revenueMin == revenueMax)
			revenueMax = revenueMin + 10;
		else if(revenueMax < revenueMin){
			revenueMax = revenueMax ^ revenueMin;
			revenueMin = revenueMax ^ revenueMin;
			revenueMax = revenueMax ^ revenueMin;
		}
	}
	
	public void loadAllProductsVersionInformation(){
		for(int productIndex=0; productIndex < products.size(); productIndex++)
			loadProductVersion(products.get(productIndex));
	}
	
	private synchronized void loadProductVersion(GeneratedProduct target){
		int maxNumberOfVersions = target.getRequiredVersions();
		ArrayList<ProductVersion> prdVersion = new ArrayList<ProductVersion>(maxNumberOfVersions);
		for(int vIndex = 0; vIndex < maxNumberOfVersions; vIndex++){
			generater3DValue();
			GeneratedProductVersion gpv = new GeneratedProductVersion(target, getRandomPack(), getRandomDimension(), getGeneratedVersion(), vIndex, salesDump[0], salesDump[1], salesDump[2]);
			productVersions.add(gpv);
			prdVersion.add(gpv);
		}
		target.setVersionInformation(prdVersion);
	}
	
	private GeneratedDimension getRandomDimension(){
		int dimIndex = indexGenerator.nextInt();
		int size = DimensionGenerator.getGeneratedDimensions().size();
		return DimensionGenerator.getGeneratedDimensions().get(dimIndex % size);
	}
	
	private PackType getRandomPack(){
		int packIndex = indexGenerator.nextInt();
		int size = packs.size();
		return packs.get(packIndex % size);
	}
	
	private synchronized int getGeneratedVersion(){
		return ++genVersionId;
	}

	private void generater3DValue(){
		salesDump[0] = generateNextNumber(priceMin, priceMax);
		salesDump[1] = generateNextNumber(profitMin, profitMax);
		salesDump[2] = generateNextNumber(revenueMin, revenueMax);
	}
	
	private double generateNextNumber(int minLimit, int maxLimit){
		double genNumber = indexGenerator.nextDouble();
		while(genNumber < minLimit || genNumber > maxLimit)
			genNumber = indexGenerator.nextDouble();
		return genNumber;
	}
}
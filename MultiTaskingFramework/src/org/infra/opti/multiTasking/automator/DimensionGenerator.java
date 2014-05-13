package org.infra.opti.multiTasking.automator;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.Callable;

import org.infra.opti.multiTasking.models.Dimensions;

public class DimensionGenerator implements Callable<Vector<GeneratedDimension>>{

	private static int genDimensionId = 0;
	private int maximumNumberOfDimension;
	private Random generator;
	private double[] dimensionDump;
	
	private int widthMin;
	private int widthMax;
	
	private int heightMin;
	private int heightMax;
	
	private int depthMin;
	private int depthMax;
	
	private static Vector<GeneratedDimension> generatedDimensions;
	static{
		generatedDimensions = new Vector<GeneratedDimension>();
	}
	
	public DimensionGenerator(int maxDimensions, int[] widthRange, int[] heightRange, int[] depthRange){
		maximumNumberOfDimension = maxDimensions;
		generator = new Random();
		dimensionDump = new double[3];
		
		/*
		 * Setting the width range
		 */
		if(widthRange == null){
			widthMin = 0;
			widthMax = 10;
		}
		else if(widthRange.length == 2){
			widthMin = widthRange[0];
			widthMax = widthRange[1];
		}
		else if(widthRange.length >= 0){
			widthMin = widthRange[0];
			widthMax = widthRange[widthRange.length - 1];
		}
		//Final validation for width
		if(widthMin == widthMax)
			widthMax = widthMin + 10;
		else if(widthMax < widthMin){
			widthMax = widthMax ^ widthMin;
			widthMin = widthMax ^ widthMin;
			widthMax = widthMax ^ widthMin;
		}
		
		/*
		 * Setting the height range
		 */
		if(heightRange == null){
			heightMin = 0;
			heightMax = 10;
		}
		else if(heightRange.length == 2){
			heightMin = heightRange[0];
			heightMax = heightRange[1];
		}
		else if(heightRange.length >= 0){
			heightMin = heightRange[0];
			heightMax = heightRange[heightRange.length - 1];
		}
		//Final validation for height
		if(heightMin == heightMax)
			heightMax = heightMin + 10;
		else if(heightMax < heightMin){
			heightMax = heightMax ^ heightMin;
			heightMin = heightMax ^ heightMin;
			heightMax = heightMax ^ heightMin;
		}
		
		/*
		 * Setting the depth range
		 */
		if(depthRange == null){
			depthMin = 0;
			depthMax = 10;
		}
		else if(depthRange.length == 2){
			depthMin = depthRange[0];
			depthMax = depthRange[1];
		}
		else if(depthRange.length >= 0){
			depthMin = depthRange[0];
			depthMax = depthRange[depthRange.length - 1];
		}
		//Final validation for depth
		if(depthMin == depthMax)
			depthMax = depthMin + 10;
		else if(depthMax < depthMin){
			depthMax = depthMax ^ depthMin;
			depthMin = depthMax ^ depthMin;
			depthMax = depthMax ^ depthMin;
		}
		
		System.out.println("Dimension generator intitated...");
	}
	
	@Override
	public Vector<GeneratedDimension> call() throws Exception {
		Vector<GeneratedDimension> dimensions = new Vector<GeneratedDimension>(maximumNumberOfDimension);
		System.out.println("Dimension created by thread " + Thread.currentThread().getId());
		for(int dimIndex = 0; dimIndex < maximumNumberOfDimension; dimIndex++){
			generater3DValue();
			dimensions.add(new GeneratedDimension(getGenDimensionId(), dimensionDump[2], dimensionDump[0], dimensionDump[1]));
		}
		
		generatedDimensions.addAll(dimensions);
		
		return dimensions;
	}

	public synchronized int getGenDimensionId(){
		System.out.println("Dimension Generated -> " + (genDimensionId + 1));
		return ++ genDimensionId;
	}
	
	public synchronized void appendDimensions(ArrayList<Dimensions> dimensions){
		for(int dimIndex=0; dimIndex < dimensions.size(); dimIndex++)
			generatedDimensions.add(new GeneratedDimension(dimensions.get(dimIndex), getGenDimensionId()));
	}
	
	public static Vector<GeneratedDimension> getGeneratedDimensions(){
		return generatedDimensions;
	}
	
	private void generater3DValue(){
		dimensionDump[0] = generateNextNumber(widthMin, widthMax);
		dimensionDump[1] = generateNextNumber(heightMin, heightMax);
		dimensionDump[2] = generateNextNumber(depthMin, depthMax);
	}
	
	private double generateNextNumber(int minLimit, int maxLimit){
		//System.out.println("Generating dimensions limits...");
		double genNumber = generator.nextDouble();
		while(genNumber < minLimit || genNumber > maxLimit){
			//System.out.println("Getting next random number after " + genNumber);
			genNumber = generator.nextDouble();
			if(genNumber<1)
				genNumber *= 10;
		}
		//System.out.println("Generated dimensions limits...");
		return genNumber;
	}
}

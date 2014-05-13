package org.infra.opti.multiTasking.executor;

import org.infra.opti.pocs.POCExecutor;

public class MultiTaskingInitiator {

	public static void main(String[] args) {
		
		/*
		EntityGenerator eg = new EntityGenerator(50, 100, 1000, 10000, XMLBeanGenerator.generatePacks());
		eg.addCategories(XMLBeanGenerator.generateCategories());
		eg.addDimensions(XMLBeanGenerator.generateDimensions());
		eg.addProducts(XMLBeanGenerator.generateProducts());
		eg.startGeneration();
		System.out.println("Out of the generation...");
		*/
		
		//Loadint the demo work
		POCExecutor pocExe = new POCExecutor();
		pocExe.pocMain(null);
	}
	
}

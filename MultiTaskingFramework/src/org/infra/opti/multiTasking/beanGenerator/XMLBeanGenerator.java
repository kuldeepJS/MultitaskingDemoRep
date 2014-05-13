package org.infra.opti.multiTasking.beanGenerator;

import java.util.ArrayList;

import org.infra.opti.multiTasking.models.Category;
import org.infra.opti.multiTasking.models.Dimensions;
import org.infra.opti.multiTasking.models.PackType;
import org.infra.opti.multiTasking.models.Product;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLBeanGenerator {
	
	public static ArrayList<Product> generateProducts(){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		context.start();
		ArrayList<Product> products = new ArrayList<Product>();
		for(int i=1; i<6;i++)
			products.add((Product)context.getBean("product" + i, Product.class));
		context.close();
		return products;
	}
	
	public static ArrayList<Category> generateCategories(){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		context.start();
		ArrayList<Category> categories = new ArrayList<Category>();
		for(int i=1; i<5;i++)
			categories.add((Category)context.getBean("category" + i, Category.class));
		context.close();
		return categories;
	}
	
	public static ArrayList<PackType> generatePacks(){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		context.start();
		ArrayList<PackType> packs = new ArrayList<PackType>();
		for(int i=1; i<5;i++)
			packs.add((PackType)context.getBean("pack" + i, PackType.class));
		context.close();
		return packs;
	}
	
	public static ArrayList<Dimensions> generateDimensions(){
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		context.start();
		ArrayList<Dimensions> dimensions = new ArrayList<Dimensions>();
		for(int i=1; i<7;i++)
			dimensions.add((Dimensions)context.getBean("dim" + i, Dimensions.class));
		context.close();
		return dimensions;
	}
}

package org.infra.opti.multiTasking.automator;

import java.util.Date;

import org.infra.opti.multiTasking.models.PackType;
import org.infra.opti.multiTasking.models.ProductVersion;

public class GeneratedProductVersion extends ProductVersion {
	private int generatedVersionId;
	private Date generatedOn;
	
	public GeneratedProductVersion(ProductVersion base, int id){
		super();
		setDimension(base.getDimension());
		setProductId(base.getProductId());
		setPackType(base.getPackType());
		setPrice(base.getPrice());
		setProductVersionId(base.getProductVersionId());
		setProfit(base.getProfit());
		setRevenue(base.getRevenue());
		generatedVersionId = id;
		generatedOn = new Date();
	}

	public GeneratedProductVersion(GeneratedProduct baseProduct, PackType packType, GeneratedDimension baseDimension, int id, int versionId, double price, double profit, double revenue){
		super();
		setDimension(baseDimension);
		setProductId(baseProduct.getProductId());
		setPackType(packType);
		setProductVersionId(versionId);
		setPrice(price);
		setProfit(profit);
		setRevenue(revenue);
		generatedVersionId = id;
		generatedOn = new Date();
	}
	
	public int getGeneratedVersionId() {
		return generatedVersionId;
	}

	public void setGeneratedVersionId(int generatedVersionId) {
		this.generatedVersionId = generatedVersionId;
	}

	public Date getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}
	
	
}

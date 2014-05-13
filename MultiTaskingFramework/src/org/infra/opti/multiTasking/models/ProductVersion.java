package org.infra.opti.multiTasking.models;

public class ProductVersion {
	private int productId;
	private int productVersionId;
	
	private PackType packType;
	
	private double revenue;
	private double profit;
	private double price;
	private Dimensions dimension;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getProductVersionId() {
		return productVersionId;
	}
	public void setProductVersionId(int productVersionId) {
		this.productVersionId = productVersionId;
	}
	public PackType getPackType() {
		return packType;
	}
	public void setPackType(PackType packType) {
		this.packType = packType;
	}
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Dimensions getDimension() {
		return dimension;
	}
	public void setDimension(Dimensions dimension) {
		this.dimension = dimension;
	}
	
}

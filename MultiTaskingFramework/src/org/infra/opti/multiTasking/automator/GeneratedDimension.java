package org.infra.opti.multiTasking.automator;

import java.util.Date;

import org.infra.opti.multiTasking.models.Dimensions;

public class GeneratedDimension extends Dimensions {
	private int generatedDimensionId;
	private Date generatedOn;
	
	public GeneratedDimension(int id, double depth, double width, double height){
		super();
		setDepth(depth);
		setWidth(width);
		setHeight(height);
		generatedDimensionId = id;
		generatedOn = new Date();
	}

	public GeneratedDimension(Dimensions base, int id){
		super();
		setDepth(base.getDepth());
		setWidth(base.getWidth());
		setHeight(base.getHeight());
		generatedDimensionId = id;
		generatedOn = new Date();
	}
	
	public int getGeneratedDimensionId() {
		return generatedDimensionId;
	}

	public void setGeneratedDimensionId(int generatedDimensionId) {
		this.generatedDimensionId = generatedDimensionId;
	}

	public Date getGeneratedOn() {
		return generatedOn;
	}

	public void setGeneratedOn(Date generatedOn) {
		this.generatedOn = generatedOn;
	}
	
}

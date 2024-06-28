package com.saumyaSingh.testingSpringDemo;

public class ShortTable implements Table {
	double height;
	double length;
	public ShortTable() {
		this.height=15.5;
		this.length=25.5;
	}
	
	@Override
	public String showDetails() {	
		return "height= "+this.height+" length= "+this.length;
	}

}

package com.saumyaSingh.testingSpringDemo;

public class LongTable implements Table {
	double height;
	double length;
	
	LongTable(){
		this.height=30;
		this.length=50;
	}
	
	@Override
	public String showDetails() {
		return "height= "+this.height+" length= "+this.length;
	}

}

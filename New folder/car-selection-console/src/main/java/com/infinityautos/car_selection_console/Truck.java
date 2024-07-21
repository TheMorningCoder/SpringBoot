package com.infinityautos.car_selection_console;

public class Truck implements Car {
	String owner;

	@Override
	public String getInfo() {
		return owner + " owns a Sports Car";
	}

	@Override
	public void setOwnerName(String name) {
		owner = name;
	}

	@Override
	public String getOwnerName() {
		return owner;
	}

}

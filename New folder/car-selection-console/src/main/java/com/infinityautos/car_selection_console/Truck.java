package com.infinityautos.car_selection_console;

public class Truck implements Car {
	String owner;
	Tyre tyre;

	public Truck(Tyre tyre) {
		this.tyre = tyre;
	}

	@Override
	public String getInfo() {
		return owner + " owns a Sports Car "+tyre.getTyreInfo();
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

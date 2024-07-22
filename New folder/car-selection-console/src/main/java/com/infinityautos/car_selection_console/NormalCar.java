package com.infinityautos.car_selection_console;

public class NormalCar implements Car {
	String owner;
	Tyre tyre;
	
	public NormalCar(Tyre tyre) {
		this.tyre=tyre;
	}

	@Override
	public String getInfo() {
		
		return owner+" owns a Normal Car "+tyre.getTyreInfo();
	}

	@Override
	public void setOwnerName(String name) {
		owner=name;
	}

	@Override
	public String getOwnerName() {
		
		return null;
	}

}

package com.example.codingninjas.domain;

public interface User {
	public boolean createUser(String name, String location, String college, String gender, int age);
	public Integer saveUser();
}

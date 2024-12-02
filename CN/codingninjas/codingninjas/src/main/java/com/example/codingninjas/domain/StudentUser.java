package com.example.codingninjas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Generates getters, setters, toString, equals, and hashCode methods.
@NoArgsConstructor //Creates a no-argument constructor.
@AllArgsConstructor //Creates a constructor with arguments for all fields.

public class StudentUser implements User {
    private String name;
    private String location;
    private String college;
    private String gender;
    private int age;

    @Override
    public boolean createUser(String name, String location, String college, String gender, int age) {
        // TODO: Implement the method logic
        return false;
    }

    @Override
    public Integer saveUser() {
        // TODO: Implement the method logic
        return null;
    }
}

package com.example.lombok_demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @GetMapping("/person")
    public Person getPerson() {
        return new Person("John Doe", 30);
    }
}

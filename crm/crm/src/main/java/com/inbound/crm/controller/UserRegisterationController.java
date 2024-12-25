package com.inbound.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inbound.crm.service.UserRegisterationService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/check-user")
public class UserRegisterationController {
	@Autowired
	UserRegisterationService service;
	@GetMapping("/already-exists")
	Mono<Boolean> checkIfUserExists(@RequestParam String phoneNumber){
		return service.checkIfUserExists(phoneNumber);
	}
	
}

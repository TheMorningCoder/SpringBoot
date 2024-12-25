package com.inbound.crm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class UserRegisterationService {
	WebClient webClient;
	UserRegisterationService(@Value("$external.api.base-url") String baseUrl) {
		this.webClient=WebClient.builder()
								.baseUrl(baseUrl)
								.build();
		}
	

	public Mono<Boolean> checkIfUserExists(String phoneNumber) {
		
		return null;
	}
	

}

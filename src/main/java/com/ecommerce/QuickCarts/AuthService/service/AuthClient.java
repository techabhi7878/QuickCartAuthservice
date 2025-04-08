package com.ecommerce.QuickCarts.AuthService.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.QuickCarts.AuthService.Entity.User;

@FeignClient(name = "Auth-service", path = "/auth")
public interface AuthClient {

	@GetMapping("/{id}")
	ResponseEntity<User>getUserById(@PathVariable("id")Long id);
}

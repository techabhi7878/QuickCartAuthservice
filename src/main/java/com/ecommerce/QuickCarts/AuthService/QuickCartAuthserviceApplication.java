package com.ecommerce.QuickCarts.AuthService;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
 @EnableDiscoveryClient
 
@EnableFeignClients(basePackages = "com.ecommerce.QuickCarts.AuthService.FeignClient")
public class QuickCartAuthserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickCartAuthserviceApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		
		return new ModelMapper();
	}

}

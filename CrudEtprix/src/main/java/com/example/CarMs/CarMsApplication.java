package com.example.CarMs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CarMsApplication {
	public static void main(String[] args) {
		SpringApplication.run(CarMsApplication.class, args);
	}
}

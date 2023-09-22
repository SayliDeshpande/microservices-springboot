package com.springbootmicroservices.product_service_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceCatalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceCatalogApplication.class, args);
	}

}

package com.springbootmicroservices.product_service_catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbootmicroservices.product_service_catalog.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}

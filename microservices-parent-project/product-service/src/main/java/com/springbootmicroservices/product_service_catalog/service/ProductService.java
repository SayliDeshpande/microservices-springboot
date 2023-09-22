package com.springbootmicroservices.product_service_catalog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springbootmicroservices.product_service_catalog.dto.ProductRequest;
import com.springbootmicroservices.product_service_catalog.dto.ProductResponse;
import com.springbootmicroservices.product_service_catalog.model.Product;
import com.springbootmicroservices.product_service_catalog.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
	
	private final ProductRepository productRepository;

	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		
		productRepository.save(product);
		log.info("Product {} successfully created."+product.getId() );
	}
	
	public List<ProductResponse> getAllProducts(){
		List<Product> products = productRepository.findAll();
		return products.stream().map(this:: mapToProductResponse).toList();
	}
	
	private ProductResponse mapToProductResponse(Product product) {
		return ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build();
	}

}

package com.springbootmicroservices.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmicroservices.inventoryservice.dto.InventoryResponse;
import com.springbootmicroservices.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
	private final InventoryService inventoryService;
	
	// this is verify if product is in stock or not by using skuCode
	 @GetMapping
	    @ResponseStatus(HttpStatus.OK)
	    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
	        log.info("Received inventory check request for skuCode: {}", skuCode);
	        return inventoryService.isInStock(skuCode);
	    }
	
}

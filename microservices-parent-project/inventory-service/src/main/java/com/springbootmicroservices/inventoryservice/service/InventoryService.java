package com.springbootmicroservices.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springbootmicroservices.inventoryservice.dto.InventoryResponse;
import com.springbootmicroservices.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	@SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
		
		/*
		 * log.info("Wait started ......."); Thread.sleep(10000);
		 * log.info("Your wait is ending.....");
		 */
		 
        log.info("Checking Inventory for {} ", skuCode);
        List<InventoryResponse> resp = inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                )
                .toList();
        log.info("Printing Inventory {}..", resp.toString());
       
        
        return resp;
    }

}

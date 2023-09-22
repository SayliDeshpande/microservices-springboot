package com.springbootmicroservices.orderservice.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.springbootmicroservices.orderservice.dto.InventoryResponse;
import com.springbootmicroservices.orderservice.dto.OrderLineItemDto;
import com.springbootmicroservices.orderservice.dto.OrderRequest;
import com.springbootmicroservices.orderservice.model.Order;
import com.springbootmicroservices.orderservice.model.OrderLineItems;
import com.springbootmicroservices.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	private final Tracer tracer;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemDtoList()
		.stream()
		.map(this::mapToDto).toList();	
		order.setOrderLineItemsList(orderLineItems);
		
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
		.map(OrderLineItems::getSkuCode)
		.toList();
		
		Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServicelookup");
		try(Tracer.SpanInScope spanInscope = tracer.withSpan(inventoryServiceLookup.start())){
			
			//call inventory service, and place order if product is in stock
			//below piece of code will make a sysnchronous call to inventory service on port 8082
			
			InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
					  .uri("http://inventory-service:8082/api/inventory",
							  uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
					  .retrieve()
					  .bodyToMono(InventoryResponse[].class)
					  .block();
			
			boolean result = Arrays.stream(inventoryResponseArray)
					               .allMatch(InventoryResponse::isInStock);
			if(result) {
				orderRepository.save(order);
				return "Your Order has been Placed Successfully!";
			}
			else {
				throw new IllegalArgumentException("Product is not in stock! please try in later");
			}	
		}finally {
			inventoryServiceLookup.end();
		}
		
	}
	
	private OrderLineItems mapToDto(OrderLineItemDto orderLineItemDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemDto.getPrice());
		orderLineItems.setQuantity(orderLineItemDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemDto.getSkuCode());
		return orderLineItems;
	}

}

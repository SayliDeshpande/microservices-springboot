package com.springbootmicroservices.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springbootmicroservices.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}

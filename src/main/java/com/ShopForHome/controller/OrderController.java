package com.ShopForHome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopForHome.entity.Order;
import com.ShopForHome.request.CreateOrderRequest;
import com.ShopForHome.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@GetMapping("/all")
	public List<Order> getAllOrders(){
		return orderService.getOrders();
	}
	
	@PostMapping
	public Order addOrder(@RequestBody CreateOrderRequest request) {
		return orderService.createOrder(request);
	}
	
}

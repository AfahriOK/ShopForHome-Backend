package com.ShopForHome.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopForHome.entity.Coupon;
import com.ShopForHome.entity.MyUser;
import com.ShopForHome.entity.Order;
import com.ShopForHome.repository.CouponRepository;
import com.ShopForHome.repository.OrderRepository;
import com.ShopForHome.repository.UserRepository;
import com.ShopForHome.request.CreateOrderRequest;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CouponRepository couponRepository;
	
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	
	public Order createOrder(CreateOrderRequest request) {
		Order order = new Order();
		order.setDate(LocalDate.now());
		MyUser user = userRepository.findByEmail(request.getUser());
		System.out.println(user);
		order.setUser(user);
		order.setOriginalPrice(request.getOriginalPrice());
		order.setFinalPrice(request.getFinalPrice());
		Optional<Coupon> coupon = couponRepository.findByCode(request.getCoupon());
		if (coupon.isPresent()) {
			order.setCoupon(coupon.get());
		}else {
			order.setCoupon(null);
		}
		return orderRepository.save(order);
	}
}

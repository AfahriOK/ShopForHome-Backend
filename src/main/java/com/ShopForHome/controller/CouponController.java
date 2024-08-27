package com.ShopForHome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopForHome.entity.Coupon;
import com.ShopForHome.request.CreateCouponRequest;
import com.ShopForHome.request.ValidateCouponRequest;
import com.ShopForHome.response.CouponResponse;
import com.ShopForHome.service.CouponService;

@RestController
@RequestMapping("/coupons")
@CrossOrigin
public class CouponController {

	@Autowired
	CouponService couponService;
	
	@GetMapping()
	public List<Coupon> getCoupons(){
		return couponService.getAllCoupons();
	}
	
	@PostMapping
	public Coupon createCoupon(@RequestBody CreateCouponRequest request) throws Exception {
		return couponService.createCoupon(request);
	}
	
	@DeleteMapping("/coupon/{id}")
	public void deleteCoupon(@PathVariable long id) {
		couponService.deleteCoupon(id);
	}
	
	@PutMapping("/coupon/{id}")
	public Coupon updateCoupon(@PathVariable long id, @RequestBody CreateCouponRequest request) throws Exception {
		return couponService.updateCoupon(id, request);
	}
	
	@PostMapping("/validate")
	public CouponResponse validateCoupon(@RequestBody ValidateCouponRequest request) throws Exception {
		return couponService.validateCoupon(request);
	}
}

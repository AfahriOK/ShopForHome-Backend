package com.ShopForHome.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShopForHome.entity.Coupon;
import com.ShopForHome.repository.CouponRepository;
import com.ShopForHome.request.CreateCouponRequest;
import com.ShopForHome.request.ValidateCouponRequest;
import com.ShopForHome.response.CouponResponse;

@Service
public class CouponService {

	@Autowired
	CouponRepository couponRepository;
	
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}
	
	public Coupon createCoupon(CreateCouponRequest request) throws Exception {
		Coupon coupon = new Coupon();
		coupon.setCode(request.getCode());
		coupon.setDiscountPercent(request.getDiscountPercent());
		coupon.setExpDate(request.getExpDate());
		if (coupon.getExpDate().isBefore(LocalDate.now())) {
			throw new Exception("Expiration date has passed!");
		}
		return couponRepository.save(coupon);
	}
	
	public void deleteCoupon(long id) {
		couponRepository.deleteById(id);
	}
	
	public Coupon updateCoupon(long id, CreateCouponRequest request) throws Exception {
		Optional<Coupon> couponResult = couponRepository.findById(id);
		if(couponResult.isEmpty()) {
			throw new Exception("Coupon not found");
		}
		
		Coupon coupon = couponResult.get();
		coupon.setCode(request.getCode());
		coupon.setDiscountPercent(request.getDiscountPercent());
		coupon.setExpDate(request.getExpDate());
		return couponRepository.save(coupon);
	}
	
	public CouponResponse validateCoupon(ValidateCouponRequest request) throws Exception {
		Optional<Coupon> couponResult = couponRepository.findByCode(request.getCode());
		if(couponResult.isEmpty()) {
			throw new Exception("Coupon not found!");
		}
		Coupon coupon = couponResult.get();
		if (coupon.getExpDate().isBefore(LocalDate.now())) {
			throw new Exception("Coupon has expired!");
		}
		
		CouponResponse response = new CouponResponse();
		response.setDiscount(coupon.getDiscountPercent());
		return response;
	}
}

package com.ShopForHome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest {
	String user;
	String coupon;
	float originalPrice;
	float finalPrice;
}

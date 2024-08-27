package com.ShopForHome.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCouponRequest {

	private String code;
	private int discountPercent;
	private LocalDate expDate;
}

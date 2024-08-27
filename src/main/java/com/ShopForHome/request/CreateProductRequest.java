package com.ShopForHome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest {

	private String name;
	private String description;
	private String category;
	private float price;
	private int stock;
	private String image;
}

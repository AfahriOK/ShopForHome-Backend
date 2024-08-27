package com.ShopForHome.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse {
	
	private String jwt;
	private boolean isAdmin = false;
	
}

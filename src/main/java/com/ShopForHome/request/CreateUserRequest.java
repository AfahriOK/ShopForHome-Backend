package com.ShopForHome.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	private String email;
	private String password;
	private String[] roles;
}

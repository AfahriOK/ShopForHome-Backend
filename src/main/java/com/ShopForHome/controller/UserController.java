package com.ShopForHome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopForHome.entity.MyUser;
import com.ShopForHome.entity.Role;
import com.ShopForHome.request.AuthenticationRequest;
import com.ShopForHome.request.CreateUserRequest;
import com.ShopForHome.response.AuthenticationResponse;
import com.ShopForHome.service.MyUserService;
import com.ShopForHome.utility.JwtUtil;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	@Autowired
	MyUserService userService;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/all")
	public List<MyUser> getUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/authenticate")
	public AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest request) throws Exception {
		try { 
			  authenticationManager.authenticate( new
			  UsernamePasswordAuthenticationToken(request.getEmail(),
			  request.getPassword()) );
			  String jwt = jwtUtil.generateToken(request.getEmail());
			  AuthenticationResponse response = new AuthenticationResponse();
			  response.setJwt(jwt);
			  MyUser user = userService.getUserByEmail(request.getEmail());
			  for (Role role: user.getRoles()) {
				  if (role.getName().equals("ADMIN")) {
					  response.setAdmin(true);
				  }
			  }
			  return response;
		  } catch (Exception ex) { 
			  throw new Exception("invalid username/password");
		  }
	}
	
	@PostMapping("/create")
	public MyUser createUser(@RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}
	
	@GetMapping("/user/{id}")
	public MyUser getUserById(@PathVariable long id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/user/{id}")
	public MyUser updateUser(@PathVariable long id, @RequestBody CreateUserRequest request) {
		return userService.updateUser(id, request);
	}
	
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}
}

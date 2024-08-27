package com.ShopForHome.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ShopForHome.entity.MyUser;
import com.ShopForHome.entity.Role;
import com.ShopForHome.repository.RoleRepository;
import com.ShopForHome.repository.UserRepository;
import com.ShopForHome.request.CreateUserRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MyUserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	
	public MyUser getUserByEmail(String email) {
		MyUser user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return user;
	}
	
	public MyUser getUserById(long id) {
		Optional<MyUser> userResult = userRepository.findById(id);
		
		if (userResult.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return userResult.get();
	}
	
	public List<MyUser> getAllUsers(){
		return userRepository.findAll();
		}
	
	public MyUser createUser(CreateUserRequest request) {
		MyUser user = new MyUser();
		user.setEmail(request.getEmail());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(request.getPassword()));
		
		Set<Role> roles = user.getRoles();
		for (String role: request.getRoles()) {
			Role r = roleRepository.findByName(role);
			roles.add(r);
		}
		user.setRoles(roles);
		
		return userRepository.save(user);
	}
	
	public MyUser updateUser(long id, CreateUserRequest request) {
		Optional<MyUser> userResult = userRepository.findById(id);
		if (userResult.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		MyUser user = userResult.get();
		user.setEmail(request.getEmail());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(request.getPassword()));
		
		Set<Role> roles = user.getRoles();
		roles.clear();
		for (String role: request.getRoles()) {
			Role r = roleRepository.findByName(role);
			roles.add(r);
		}
		user.setRoles(roles);
		
		return userRepository.save(user);
	}
	
	public void deleteUser(long id) {
		Optional<MyUser> userResult = userRepository.findById(id);
		if (userResult.isEmpty()) {
			throw new UsernameNotFoundException("Could not find user");
		}
		MyUser user = userResult.get();
		Set<Role> roles = user.getRoles();
		roles.removeAll(roles);
		userRepository.save(user);
		userRepository.deleteById(id);
	}
}

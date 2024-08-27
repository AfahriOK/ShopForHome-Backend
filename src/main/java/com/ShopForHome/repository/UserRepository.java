package com.ShopForHome.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopForHome.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long>{

	MyUser findByEmail(String email);
}

package com.ShopForHome.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShopForHome.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}

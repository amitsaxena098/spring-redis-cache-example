package com.redisexample.springredisexample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.redisexample.springredisexample.entity.Order;

@Repository
public class OrderDao {

	@Autowired
	private RedisTemplate template;
	
	public Order saveOrder(Order order) {
		
		template.opsForHash().put("Order", order.getId(), order);
		
		return order;
	}
	
	public List<Order> findAll() {
		return template.opsForHash().values("Order");
	}
	
	public Order findOrder(int id) {
		System.out.println("Hitting DB");
		return (Order) template.opsForHash().get("Order", id);
	}
	
	public String deleteOrder(int id) {
		template.opsForHash().delete("Order", id);
		return "Deleted";
	}
	
	
}

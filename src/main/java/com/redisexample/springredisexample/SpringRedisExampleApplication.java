package com.redisexample.springredisexample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.redisexample.springredisexample.entity.Order;
import com.redisexample.springredisexample.repository.OrderDao;

@SpringBootApplication
@RestController
@RequestMapping("/order")
@EnableCaching
public class SpringRedisExampleApplication {

	@Autowired
	private OrderDao orderDao;
	
	@PostMapping
	public Order save(@RequestBody Order order) {
		return orderDao.saveOrder(order);
	}
	
	@GetMapping
	public List<Order> findAll() {
		return orderDao.findAll();
	}
	
	@GetMapping("/{id}")
	@Cacheable(key = "#id", value = "Order")
	
	public Order getOrder(@PathVariable int id) {
		return orderDao.findOrder(id);
	}
	
	@DeleteMapping("/{id}")
	@CacheEvict(key = "#id", value = "Order")
	public String deleteOrder(@PathVariable int id) {
		return orderDao.deleteOrder(id);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringRedisExampleApplication.class, args);
	}

}

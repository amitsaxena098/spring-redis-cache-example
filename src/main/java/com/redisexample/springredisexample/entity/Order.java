package com.redisexample.springredisexample.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Order")
public class Order {
	
	@Id
	String id;
	String productName;
	int price;
	String productCategory;

}

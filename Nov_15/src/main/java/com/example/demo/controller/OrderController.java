package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Order;
import com.example.demo.entity.Review;
import com.example.demo.service.OrderService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	@Autowired
	OrderService orderService;
	 @GetMapping("/")
	    public String home() {
	        return "Welcome to Student API!";
	    }
	@GetMapping("/order")
	Iterable<Order> getOrder() {
		System.out.println("Get Request");
		return orderService.getAllOrders();
	}
	@GetMapping("/order/review/{orderId}")
	Iterable<Review> getReview(@PathVariable @Valid int orderId){
		return orderService.getAllReviews(orderId);
	}
	@PostMapping("/addReview/{orderId}")
	void addReview(@RequestBody @Valid Review r,@PathVariable @Valid int id) {
		orderService.addOrderReview(id,r);
	}
	@PostMapping("/add")
	void getOrders(@RequestBody @Valid Order hello) {
		System.out.println("Post request");
		orderService.saveOrder(hello);
	}
	@DeleteMapping("/delete/{orderId}")
		void saveOrder(@PathVariable @Valid int orderId) {
		System.out.println(orderId);
		orderService.deleteOrder(orderId);
	}
}

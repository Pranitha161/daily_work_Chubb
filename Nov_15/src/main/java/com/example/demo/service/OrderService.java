package com.example.demo.service;
import com.example.demo.entity.Order;
import com.example.demo.entity.Review;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ReviewRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.*;
@Service
@Slf4j
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private ReviewRepository reviewRepo;
	public Iterable<Order> getAllOrders(){
		return orderRepo.findAll();
	}
public Order saveOrder(Order o) {
	return orderRepo.save(o);
}
public void deleteOrder(int id) {
	orderRepo.deleteById(id);
}
public Iterable<Review> getAllReviews(int id){
	Optional<Order> order=orderRepo.findById(id);
	if(order.isEmpty())return null;
	return order.get().getReview();
}
public void addOrderReview(int id,Review r) {
	Optional<Order> order=orderRepo.findById(id);
	if(order.isEmpty()) {
		System.out.println("No order is present");
	}
	else {
		reviewRepo.save(r);
		order.get().getReview().add(r);
	}
	
}
}
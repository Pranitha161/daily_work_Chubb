package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	private final ProductService service;
	@GetMapping("/{id}")
	public Mono<Product> getProduct(@PathVariable String id) {
		return service.getProductById(id);
	}
	@PostMapping("/add")
	public Mono<Product> addProduct(@RequestBody Product pro) {
		return service.addProd(pro);
	}
	@GetMapping("/get")
	public Flux<Product> getProducts() {
		return service.getAll();
	}
}

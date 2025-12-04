package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String>{

}

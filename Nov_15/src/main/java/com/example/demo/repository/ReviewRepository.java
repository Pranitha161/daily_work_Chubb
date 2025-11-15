package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Review;

public interface ReviewRepository extends CrudRepository<Review, Integer>{

}

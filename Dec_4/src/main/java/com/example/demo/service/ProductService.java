package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {
	@Autowired
	private final ProductRepository productrepo;
	
	@Cacheable("products")
	public Mono<Product> getProductById(String id) {
		return productrepo.findById(id);
	}
	@CacheEvict(value = "products", allEntries = true)
	public Mono<Product> addProd(Product pro) {
	    return productrepo.save(pro);
	}
	@Cacheable("products")
	public Flux<Product> getAll()
	{
		return productrepo.findAll();
	}
	

}

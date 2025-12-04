package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Data
@Document(collection = "product")
public class Product implements Serializable{
	@Id
	private String id;
    private String name;
    private double price;
}

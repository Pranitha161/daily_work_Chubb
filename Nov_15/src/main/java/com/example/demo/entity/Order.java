package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;
@Entity
@Table(name = "orders")
public class Order {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer Id;
		@NotBlank
		private String item;
		private float price;
		@Min(value=1,message="Quantity must be atleast 1")
		private int quantity;
		@Embedded
		private Address address;
		@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
		private List<Review> review=new ArrayList<>();
		public String getItem() {
			return item;
		}
		public void setItem(String item) {
			this.item = item;
		}
		public float getPrice() {
			return price;
		}
		public void setPrice(float price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public Address getAddress() {
		    return address;
		}
		public void setAddress(Address address) {
		    this.address = address;
		}
		public List<Review> getReview() {
			return review;
		}
		public void setReview(List<Review> review) {
			this.review = review;
		}

		
		
}

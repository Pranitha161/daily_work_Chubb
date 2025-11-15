package com.example.demo.entity;

import jakarta.persistence.*;

@Embeddable
public class Address {
	private String colony;
	private String state;
	private int pincode;
	public String getColony() {
		return colony;
	}
	public void setColony(String colony) {
		this.colony = colony;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	
}

package com.mybootapp.main.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class OutwardRegisterDto {
	
	private String productTitle;
	private int productQuantity;
	private String godownLocation;
	private String godownManager;
	private int quantity;
	
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public String getGodownLocation() {
		return godownLocation;
	}
	public void setGodownLocation(String godownLocation) {
		this.godownLocation = godownLocation;
	}
	public String getGodownManager() {
		return godownManager;
	}
	public void setGodownManager(String godownManager) {
		this.godownManager = godownManager;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

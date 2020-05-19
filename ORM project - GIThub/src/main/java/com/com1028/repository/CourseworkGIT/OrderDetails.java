package com.com1028.repository.CourseworkGIT;

public class OrderDetails {
	
	private int orderNumber = 0;
	private double priceEach = 0;
	private int quantityOrdered = 0;
	private int orderLineNumber = 0;
	private String productCode = null;
	
	public OrderDetails(int orderNumber, double priceEach, int quantityOrdered, int orderLineNumvber, String productCode) {
		this.orderNumber = orderNumber;
		this.priceEach = priceEach;
		this.quantityOrdered = quantityOrdered;
		this.orderLineNumber = orderLineNumvber;
		this.productCode = productCode;
	}
	
	public int getOrderNumber() {
		return this.orderNumber;
	}
	
	public double getPriceEach() {
		return this.priceEach;
	}
	
	public int getQuantityOrdered() {
		return this.quantityOrdered;
	}
	
	public int getOrderLineNumber() {
		return this.orderLineNumber;
	}
	
	public String getProductCode() {
		return this.productCode;
	}
}

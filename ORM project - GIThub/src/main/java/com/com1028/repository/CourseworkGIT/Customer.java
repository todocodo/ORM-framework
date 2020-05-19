package com.com1028.repository.CourseworkGIT;

public class Customer {
	private int customerNumber = 0;
	private String customerName = null;
	private String contactLastName = null;
	private String contactFirstName = null;
	private String phone = null;
	private String addressLine1 = null;
	private String addressLine2 = null;
	private String city = null;
	private String state = null;
	private String postalCode = null;
	private String country = null;
	private int salesRepEmployeeNumber = 0;
	private double creditLimit = 0;
	
	public Customer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, int salesRepEmployeeNumber, double creditLimit) {
		super();
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public String getCounntry() {
		return country;
	}

	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public double getCreditLimit() {
		return creditLimit;
	}
	
	
	
}

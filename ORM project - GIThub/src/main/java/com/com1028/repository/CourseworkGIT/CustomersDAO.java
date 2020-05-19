package com.com1028.repository.CourseworkGIT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;


public class CustomersDAO {
	private BaseQuery query;
	
	/***
	 * List all the values from table "customers" into a List
	 * 
	 * @return list with "customers" values
	 */
	public List<Customer> getCustomers() {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet results = query.useQuery("SELECT * FROM customers");
			
			while (results.next()) {
				int customerNumber = results.getInt("customerNumber");
				String customerName = results.getString("customerName");
				String contactLastName = results.getString("contactLastName");
				String contactFirstName = results.getString("contactFirstName");
				String phone = results.getString("phone");
				String addressLine1 = results.getString("addressLine1");
				String addressLine2 = results.getString("addressLine2");
				String city = results.getString("city");
				String state = results.getString("state");
				String postalCode = results.getString("postalCode");
				String country = results.getString("country");
				int salesRepEmployeeNumber = results.getInt("salesRepEmployeeNumber");
				double creditLimit = results.getDouble("creditLimit");
				
				Customer tempCustomers = new Customer(customerNumber, customerName, contactLastName, 
						contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, 
						country, salesRepEmployeeNumber, creditLimit);
				customers.add(tempCustomers);
			}
					
		}  catch (SQLException e) {
			System.out.println("SQLException happened while retrieving records- abort programmme");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			query.closeConnection();
		}
		return customers;
	}
	
	/***
	 * Requirement 3 
	 * 
	 * @return List - the revenue produced by each sales Rep based on the orders from the customers they serve
	 */
	public String revenue() {
		PaymentsDAO paymentsDAO = new PaymentsDAO();
		
		DecimalFormat df = new DecimalFormat("#.00");
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		List<Customer> customers = getCustomers();
		List<Payments> payments = paymentsDAO.getPayments();
		// you can use a second iterator or just restart the old one in order to go through the list again - I used both methods
		Iterator<Customer> iterCustomer = customers.iterator();
		Iterator<Customer> iterCustomer2 = customers.iterator();
		Iterator<Payments> iterPayments = payments.iterator();

		StringBuffer bufferRevenue = new StringBuffer();
		
		Customer tempCustomer = iterCustomer2.next();
		Payments tempPayment = iterPayments.next();
		
		
		while (iterCustomer2.hasNext()) {
			if(tempCustomer.getSalesRepEmployeeNumber() != 0) {
				treeSet.add(tempCustomer.getSalesRepEmployeeNumber());
			}
			
			if (iterCustomer2.hasNext()) {
				tempCustomer = iterCustomer2.next();
			} else {
				break;
			}
		}
		tempCustomer = iterCustomer.next();
		int customerNumber = tempPayment.getCustomerNumber();
		double sum = 0;
		double sumRevenue = 0;
		int salesRepNumber = 0;
		Iterator<Integer> iterInt = treeSet.iterator();
		
		while(iterInt.hasNext()) {
			salesRepNumber = iterInt.next();
			
			while (iterPayments.hasNext()) {
				
				while (tempPayment.getCustomerNumber() == customerNumber) {
					sum += tempPayment.getAmount();
					
					if (iterPayments.hasNext()) {
						tempPayment = iterPayments.next();
					} else {
						break;
					}
				}
				customerNumber++;
				 
				if(sum != 0) {
					if (salesRepNumber == tempCustomer.getSalesRepEmployeeNumber()) {
						sumRevenue += sum;
					}
					if (iterCustomer.hasNext()) {
						tempCustomer = iterCustomer.next();
					} else {
						break;
					}
					
				}
				//make sure that the null values in salesRepEmployeeNumber column is skipped
				while (tempCustomer.getCustomerNumber() != tempPayment.getCustomerNumber()) {
					if (iterCustomer.hasNext()) {
						tempCustomer = iterCustomer.next();
					} else {
						break;
					}
				}
				sum = 0;
				
			}
			//restart the iterator 
			iterCustomer = customers.iterator();
			iterPayments = payments.iterator();
			tempCustomer = iterCustomer.next();
			tempPayment = iterPayments.next();
			customerNumber = tempPayment.getCustomerNumber();
			
			bufferRevenue.append("salesRepEmployeeNumber: " + salesRepNumber + ", revenue --> " + df.format(sumRevenue).replace(",", ".") + "\n");
			sumRevenue = 0;
			sum = 0;
		} 
		
		return bufferRevenue.toString();
	}
	
} 

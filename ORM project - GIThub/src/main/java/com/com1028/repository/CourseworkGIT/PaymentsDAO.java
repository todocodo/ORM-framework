package com.com1028.repository.CourseworkGIT;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PaymentsDAO {
	private BaseQuery query;
	
	/***
	 * List all the values from table "payments" into a List
	 * 
	 * @return list with "payments" values
	 */
	
	public List<Payments> getPayments() {
		ArrayList<Payments> payments = new ArrayList<Payments>();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet results = query.useQuery("SELECT * FROM payments");
			
			while (results.next()) {
				int customerNumber = results.getInt("customerNumber");
				String checkNumber = results.getString("checkNumber");
				String paymentDate = results.getString("paymentDate");
				double amount = results.getDouble("amount");
				Payments tempPayment = new Payments(customerNumber, checkNumber, paymentDate, amount);
				payments.add(tempPayment);
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException happened while retrieving records- abort programmme");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally {
			query.closeConnection();
		}
		return payments;
	}
	
	/***
	 * Requirement 2
	 * 
	 * @return List - the amount paid by each customer
	 */
	public String amountPaidCustomer() {
		List<Payments> payments = getPayments();
		Iterator<Payments> iter = payments.iterator();
		DecimalFormat df = new DecimalFormat("#.00");

		StringBuffer buffer = new StringBuffer();
		
		Payments tempPayment= iter.next();
		int customerNumber = tempPayment.getCustomerNumber();
		double sum = 0;
		
		while (iter.hasNext()) {
			while (tempPayment.getCustomerNumber() == customerNumber) {
				sum += tempPayment.getAmount();
				
				if (iter.hasNext()) {
					tempPayment = iter.next();
				} else {
					break;
				}
			}
			
			customerNumber++;
			
			if(sum != 0) {
				buffer.append("Customer Number: " + (customerNumber - 1) + "  paid ->  "+ String.valueOf(df.format(sum).replace(",", ".")) + "\n");
			}
			sum = 0;
			
		}
		return buffer.toString();
	}
	

}

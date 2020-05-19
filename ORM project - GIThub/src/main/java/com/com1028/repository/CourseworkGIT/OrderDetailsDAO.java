package com.com1028.repository.CourseworkGIT;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderDetailsDAO {
	private BaseQuery query;
	
	/***
	 * List all the values from table "orderDetails" into a List
	 * 
	 * @return list with "orderDetails" values
	 */
	
	public List<OrderDetails> getOrders() {
		ArrayList<OrderDetails> orders = new ArrayList<OrderDetails>();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet results = query.useQuery("SELECT * FROM orderdetails");
			
			while(results.next()) {
				
				int orderNumber = results.getInt("orderNumber");
				double price = results.getDouble("priceEach");
				int quantityOrdered = results.getInt("quantityOrdered");
				int orderLineNumber = results.getInt("orderLineNumber");
				String productCode = results.getString("productCode");
				OrderDetails tempOrder = new OrderDetails(orderNumber, price, quantityOrdered, orderLineNumber, productCode);
				orders.add(tempOrder);
			}
			
		} catch (SQLException e) {
			System.out.println("SQLException happened while retrieving records- abort programmme");
			e.printStackTrace();
				throw new RuntimeException(e);
		}
		finally {
			query.closeConnection();
		}
		return orders;
	}
	/***
	 * Requirement 1 
	 * 
	 * @return List - orders that have a value greater than 5000
	 */
	//order list (in the declaration of the method) in order to return the full list of the 
	//orderNumber (maybe without the price attached to it) 
	
	public String orderPriceAbove5000() {
		List<OrderDetails> orders = getOrders();
		Iterator<OrderDetails> iter = orders.iterator();
		
		StringBuffer buffer = new StringBuffer();
		
		OrderDetails tempOrder = iter.next();
		int number = tempOrder.getOrderNumber();
		double sum = 0;
		
		while (iter.hasNext()) {	
			while (tempOrder.getOrderNumber() == number) {
				sum += (tempOrder.getPriceEach() * tempOrder.getQuantityOrdered());
				
				if (iter.hasNext()) {
					tempOrder = iter.next();
				} else {
					break;
				}	
			}
			if(sum > 5000) {
				buffer.append("Order with orderNumber: " + number + " has a value greater than 5000\n");
			}
			sum = 0;
			number++;
		}	
		return buffer.toString();
	}	
	
}

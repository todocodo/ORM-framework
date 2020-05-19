package com.com1028.repository.CourseworkGIT;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import org.junit.Test;

public class OrderDetailsDAOTest {
	protected BaseQuery query;

	@Test
	public void orderPriceAbove5000Test() {
		OrderDetailsDAO order = new OrderDetailsDAO();
		StringBuffer buffer = new StringBuffer();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet rs = query.useQuery("SELECT orderNumber FROM orderdetails GROUP BY orderNumber HAVING SUM(quantityOrdered * priceEach) > 5000");
			
			// Store the selected database via SQL into a list
			while (rs.next()) {
				int number = rs.getInt("orderNumber");
				buffer.append("Order with orderNumber: " + number + " has a value greater than 5000\n");
			} 
			
			assertEquals(buffer.toString(), order.orderPriceAbove5000());
			
        } 
		catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
		finally {
			this.query.closeConnection();
		}
	}
}

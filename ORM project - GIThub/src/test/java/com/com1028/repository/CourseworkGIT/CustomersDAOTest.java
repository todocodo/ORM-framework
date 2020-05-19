package com.com1028.repository.CourseworkGIT;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class CustomersDAOTest {

	private BaseQuery query;

	@Test
	public void amountPaidCustomerTest() {
		CustomersDAO customer = new CustomersDAO();
		StringBuffer buffer = new StringBuffer();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet rs = query.useQuery("SELECT salesRepEmployeeNumber, SUM(payments.amount) as revenue "
					+ "FROM customers INNER JOIN payments ON customers.customerNumber = payments.customerNumber "
					+ "GROUP BY salesRepEmployeeNumber "
					+ "HAVING SUM(payments.amount) ORDER BY salesRepEmployeeNumber");
			
			// Store the selected database via SQL into a list
			while (rs.next()) {
				String salesRepNumber = rs.getString("salesRepEmployeeNumber");
				String revenue  = rs.getString("revenue");
				buffer.append("salesRepEmployeeNumber: " + salesRepNumber + ", revenue --> " + revenue + "\n");
			}
			
			assertEquals(buffer.toString(), customer.revenue());
		
		}
		catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
		//close the connection 
		this.query.closeConnection();

	}
}

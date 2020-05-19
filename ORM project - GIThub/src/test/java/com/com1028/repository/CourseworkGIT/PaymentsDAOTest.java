package com.com1028.repository.CourseworkGIT;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;

public class PaymentsDAOTest {
	private BaseQuery query;
	
	@Test
	public void amountPaidCustomerTest() {
		PaymentsDAO payment = new PaymentsDAO();
		StringBuffer buffer = new StringBuffer();
		
		try {
			this.query = new BaseQuery("root", "");
			ResultSet rs = query.useQuery("SELECT customerNumber, SUM(amount) as amount "
					+ "FROM payments GROUP BY customerNumber HAVING SUM(amount)");
			
			while (rs.next()) {
				String customerNumber = rs.getString("customerNumber");
				String amount  = rs.getString("amount");
				buffer.append("Customer Number: " + customerNumber + "  paid ->  " + amount + "\n");
			}
			
			assertEquals(buffer.toString(), payment.amountPaidCustomer());
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

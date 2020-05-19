package com.com1028.repository.CourseworkGIT;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseQuery {
	protected Connection con;
	protected Statement s;
	protected ResultSet rs;
	private final String db = "jdbc:mysql://localhost:3306/classicmodels";

	public BaseQuery(String uname, String pwd){
		try {
		      //Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver ());
			con = DriverManager.getConnection( db, uname, pwd);
		}
		catch(Exception e) {
			System.out.println(e + "dsdsd");
			e.printStackTrace();
		}
	}
	
	protected ResultSet useQuery(String query) {
		try {
			this.s = con.createStatement();
			this.rs = s.executeQuery(query);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.rs;
	}
	
	public void closeConnection() {
		if (this.rs != null) {
	        try {
	        	this.rs.close();
	        } catch (SQLException e) { e.printStackTrace();}
	    }
	    if (this.s != null) {
	        try {
	        	this.s.close();
	        } catch (SQLException e) { e.printStackTrace();}
	    }
	    if (this.con != null) {
	        try {
	        	this.con.close();
	        } catch (SQLException e) { e.printStackTrace();}
	    }
	}

}

package com.highradius.internship;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetterClass {	
	static ResultSet result = null;
	public static int getNewOrderID() {
		result = DatabaseConnection.execQuery("select max(Order_ID) as maxOrder from order_details");
		int orderID = 0;
		try {
			result.next();
			orderID = result.getInt("maxOrder");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderID + 1;		
	}
	
	public static String getDate() {
		result = DatabaseConnection.execQuery("select curdate() as date");
		String date = null;
		try {
			result.next();
			date = result.getString("date");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static int getNewCustomerID() {
		result = DatabaseConnection.execQuery("select max(Customer_ID) as maxCustomerNo from order_details");
		int customerID = 0;
		try {
			result.next();
			customerID = result.getInt("maxCustomerNo");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerID + 1;
	}

}

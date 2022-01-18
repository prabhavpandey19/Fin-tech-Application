package com.highradius.internship;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class DatabaseConnection {
	static Connection con;
	protected static void initializeDatabase() 
	        throws SQLException, ClassNotFoundException 
	    { 
	        // Initialize all the information regarding 
	        // Database Connection			
	        String dbDriver = "com.mysql.cj.jdbc.Driver"; 
	        String dbURL = "jdbc:mysql:// localhost:3306/"; 
	        String dbName = "winter_internship"; 
	        String dbUsername = "root"; 
	        String dbPassword = "Prabhav@19"; 
	  
	        Class.forName(dbDriver);	        
	        con = DriverManager.getConnection(dbURL + dbName, 
	                                                     dbUsername,  
	                                                     dbPassword); 
	        //return con; 
	    } 	
	
	public static ResultSet execQuery(String sql) {
		ResultSet res = null;
		try {
			Statement st = con.createStatement();
			res = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
}

package com.virtusa.bstore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

	public static Connection getConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root","rgukt123");
			return con;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

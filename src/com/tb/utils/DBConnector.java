package com.tb.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	private static DBConnector dbc;
	private static  Connection c;
	private DBConnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/new_prod_db",
					"root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static DBConnector getInstance(){
		if(dbc == null){
			dbc = new DBConnector();
		}
		
		return dbc;
	}
	public Connection getConnection(){
		return c;
	}
}

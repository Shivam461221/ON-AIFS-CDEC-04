package com.cloud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Demo {
	
	public static void main(String[] args) {
		final String URL = "jdbc:mysql://localhost:3306/mydb";
		final String USERNAME = "root";
		final String PASSWORD = "root";
		try {
			
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			Statement stmt = connection.createStatement();
			
			//for select query only
			//ResultSet rs = stmt.executeQuery("select * from employees");
			
//			while(rs.next()) {
//				System.out.println(rs.getInt("id")+", "+rs.getString("name")+", "+rs.getString("email"));
//			}
			
			
			//for insert/update and delete
			int executeUpdate = stmt.executeUpdate("delete from employees where id = 105");
			
			System.out.println(executeUpdate+" rows deleted");
			
			//rs.close();
			stmt.close();
			connection.close();
		}
		
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}

package com.cloud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		
		final String URL = "jdbc:mysql://localhost:3306/mydb";
		final String USERNAME = "root";
		final String PASSWORD = "root";
		
		String INSERT_QUERY = "insert into employees (id, name, email, department_id, salary) values (?,?,?,?,?)";
		
		try {
			Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
			PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY);
			pstmt.setInt(1, 120);
			pstmt.setString(2, "Nilima");
			pstmt.setString(3, "nilima@123");
			pstmt.setInt(4, 2);
			pstmt.setDouble(5, 15000);
			
			int result = pstmt.executeUpdate();
			
			System.out.println(result+ " rows inserted");
		}
		catch(SQLException e) {
			System.out.println(e);
		}

	}

}

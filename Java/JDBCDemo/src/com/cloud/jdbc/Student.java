package com.cloud.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Student {
	private static final String URL = "jdbc:mysql://localhost:3306/student_db";
	private static final String username = "root";
	private static final String password = "root";

	private static Connection connection;

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("\n Student Management System");
		System.out.println("============================");

		boolean running = true;

		while (running) {
			try {
				connectDatabase();
				showMenu();
				int choice = scanner.nextInt();

				switch (choice) {
				case 1 -> addStudent();
				case 2 -> viewAllStudents();
				case 3 -> updateStudent();
				case 4 -> deleteStudent();
				case 5 -> searchStudent();
				case 6 -> {
					running = false;
					connection.close();
					System.out.println("Goodbye");
				}
				default -> System.out.println("Wrong choice, try again!!");
				}
			}

			catch (SQLException e) {
				System.out.println(e);
			}

		}
	}

	private static void connectDatabase() throws SQLException {
		System.out.println("Connecting");
		connection = DriverManager.getConnection(URL, username, password);
		System.out.println("Database Connected");
	}

	private static void showMenu() {
		System.out.println("1 Add Student");
		System.out.println("2 View All Students");
		System.out.println("3 Update Student");
		System.out.println("4 Delete Student");
		System.out.println("5 Search Student");
		System.out.println("0 Exit");
		System.out.println("Enter your choice");
	}

	private static void addStudent() throws SQLException {
		System.out.println("Add Student");
		System.out.println("Enter student details");

		scanner.nextLine();

		System.out.println("Enter Name");
		String name = scanner.nextLine();

		System.out.println("Enter Email");
		String email = scanner.nextLine();

		System.out.println("Enter age");
		int age = scanner.nextInt();

		scanner.nextLine();

		System.out.println("Enter course");
		String course = scanner.nextLine();

		String INSERT_QUERY = "INSERT INTO students (name, email, age, course) VALUES (?, ?, ?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(INSERT_QUERY)) {
			
			
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setInt(3, age);
			pstmt.setString(4, course);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(result + " rows inserted");
			} else {
				System.out.println("not inserted");
			}
		}

	}

	private static void viewAllStudents() throws SQLException {
		System.out.println("View All Student");

		String SQL = "SELECT * FROM students";

		try (PreparedStatement pstmt = connection.prepareStatement(SQL); ResultSet rs = pstmt.executeQuery();) {
			System.out.println("id\tname\t\temail\t\tage\tcourse");

			if (!rs.isBeforeFirst()) {
				System.out.println("No data");
				return;
			}

			while (rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("email") + "\t"
						+ rs.getInt("age") + "\t" + rs.getString("course"));
			}
		}
	}

	private static void updateStudent() throws SQLException {
		System.out.println("Update Student");
	}

	private static void deleteStudent() throws SQLException {
		System.out.println("Delete Student");
	}

	private static void searchStudent() throws SQLException {
		System.out.println("Search Student");
	}
}

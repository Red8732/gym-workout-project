package com.login.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	// The URL to connect with database:
	private String url = "jdbc:hsqldb:hsql://localhost:9001/users";
	private String user = "SA";
	private String password = "";
	
	// Or you can write:
	// DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/users", "SA", "");
	
	public Connection getConnection() {
		try {
			return DriverManager.getConnection(
				url, user, password);
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

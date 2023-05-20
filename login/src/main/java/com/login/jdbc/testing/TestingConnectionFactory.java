package com.login.jdbc.testing;

import java.sql.Statement;

import com.login.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestingConnectionFactory {
	
	// java -classpath lib/hsqldb.jar org.hsqldb.server.Server --database.0 file:database/users --dbname.0 users
	
	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		
		// DDL
		Statement create = connection.createStatement();
		boolean r = create.execute(
				"CREATE TABLE IF NOT EXISTS users ("
				+ "id_user INTEGER IDENTITY,"
				+ "email VARCHAR(80),"
				+ "password varchar(50),"
				+ "PRIMARY KEY (id_user, email)"
				+ ");");
		
		// false = no errors
		System.out.println(r);
		create.close();
		
		
		// DML
		Statement insert = connection.createStatement();
		int i = insert.executeUpdate("INSERT INTO users (email, password) VALUES ('testmail@gmail.com', '123')");
		// i = 1 means no errors too
		System.out.println(i);
		insert.close();
		
		// DQL
		Statement select = connection.createStatement();
		ResultSet result = select.executeQuery("SELECT * FROM users");
		
		System.out.println();
		
		while(result.next()) {
			int id = result.getInt(1);
			String email = result.getString(2);
			String password = result.getString(3);
			
			System.out.println("Id: " + id 
						    + "\nEmail: " + email 
						    + "\nPassword: " + password);
			System.out.println();
		}
		result.close();
		select.close();
		
		
		/* If you want to drop the table:
		
		Statement end = connection.createStatement();
		boolean k = end.execute("DROP TABLE users");
		System.out.println(k); // if false = table dropped with success 
		end.close();
		*/
		
		connection.close();
	}
}
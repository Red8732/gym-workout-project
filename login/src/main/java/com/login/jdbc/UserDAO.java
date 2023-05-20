package com.login.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.login.model.User;


public class UserDAO {
	
	// Connecting with the database:
	private Connection connection;
	
	public UserDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void create() {
		try {
			Statement create = connection.createStatement();
			boolean r = create.execute(
					"CREATE TABLE IF NOT EXISTS users ("
					+ "id_user INTEGER IDENTITY,"
					+ "email VARCHAR(80),"
					+ "password varchar(50),"
					+ "PRIMARY KEY (id_user, email)"
					+ ");");
		
		} catch(SQLException e) {
			throw new RuntimeException(e);		
		}
	}
	
	public List<User> getList() {
		try {
			List<User> users = new ArrayList<User>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM users");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				// Creating user object:
				User user = new User();
				user.setId(rs.getInt("id_user"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				
				// Add user to list:
				users.add(user);
			}
			rs.close();
			stmt.close();
			return users;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void add(User user) {
		String sql = "INSERT INTO users" + 
				" (email, password)" +
				" VALUES (?,?)";
		try {
			// preparedStatement for insertion:
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			// Setting the data:
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			
			// Run:
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			
			if (rs.next()) {
				int generatedId = rs.getInt(1);
				user.setId(generatedId);
			}
			
			stmt.close();
		
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void update(User user) {
		String sql = "UPDATE users SET password=? WHERE id_user=?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(2, user.getPassword());
			stmt.execute();
			stmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);		
		}
	}

	public void delete(User user) {
		try {
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id_user=?");
			stmt.setInt(1, user.getId());
			stmt.execute();
			stmt.close();
		
		} catch(SQLException e) {
			throw new RuntimeException(e);		
		}
	}

}

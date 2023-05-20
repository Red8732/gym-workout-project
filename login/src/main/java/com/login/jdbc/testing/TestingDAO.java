package com.login.jdbc.testing;

import com.login.jdbc.UserDAO;
import com.login.model.User;

public class TestingDAO {

	public static void main(String[] args) {
		UserDAO db = new UserDAO();
		User user1 = new User("testdao@gmail.com", "1234");
		User user2 = new User("daotest@hotmail.com", "4321");
		
		db.create(); // creates the "users" table if not exists
		db.add(user1); // adds user1 to the table
		db.add(user2); // adds user2  "  "    "
		db.delete(user2); // delete user2 from the table
	}
}

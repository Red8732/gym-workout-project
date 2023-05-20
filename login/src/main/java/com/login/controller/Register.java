package com.login.controller;

import java.io.IOException;
import java.io.PrintWriter;
import com.login.jdbc.UserDAO;
import com.login.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/home.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		
		//	Getting the parameters:
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		// Creating the user object and adding it to the database:
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		UserDAO db = new UserDAO();
		db.add(user);
		
		// imprimindo o contato adicionado:
		out.println("<html>");
		out.println("<body>");
		out.println("Your account has been registered with success.");								
		out.println("</body>");
		out.println("</html>");
	}
}

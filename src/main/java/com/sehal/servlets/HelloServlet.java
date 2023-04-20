package com.sehal.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@EJB
//	User user;

	public HelloServlet() {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uname = (String)request.getParameter("uname");
		String psw = (String)request.getParameter("psw");
		
		System.out.println(uname + "  " + psw);
		
//		user.setUsername(uname);
//		user.setPassword(psw);
//		
//		if(user.isUser()) {
//			System.out.println("Welcome");
//		} else {
//			System.out.println("User is exist!");
//		};
				
	}

}

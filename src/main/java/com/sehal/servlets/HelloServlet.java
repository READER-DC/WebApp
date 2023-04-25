package com.sehal.servlets;

import java.io.IOException;
import com.sehal.model.services.UserService;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserService userService;
//	@Inject
//	User user;
	
	public HelloServlet() {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String uname = (String) request.getParameter("uname");
		String psw = (String) request.getParameter("psw");
//		user.setUsername(uname);
//		user.setPassword(psw);

		System.out.println(uname + "  " + psw);
//TODO create Authorization & Authentication & Accounting
		if (userService.authorization(uname, psw)) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./mainWindow.html");
			dispatcher.forward(request, response);
		} else {
			System.out.println("User not found!");	
			request.getRequestDispatcher("./").forward(request, response);
		}
	}

}

package com.sehal.servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sehal.model.services.KontragentService;

import jakarta.inject.Inject;

@WebServlet("/Report1")
public class Report1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private KontragentService kontragentService;

    public Report1() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		kontragentService.getAll();
		request.getRequestDispatcher("/report1.jsp").forward(request, response);
		
	}

}

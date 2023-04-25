package com.sehal.servlets;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sehal.model.Kontragent;
import com.sehal.model.services.KontragentService;

import jakarta.inject.Inject;

@WebServlet("/Report1")
public class Report1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private KontragentService kontragentService;

	public Report1() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String k_id = "0";
		if (Kontragent.kontragents.isEmpty())
			kontragentService.getAll();
		if (request.getParameterNames().hasMoreElements()) {
			Map<String, String[]> param = request.getParameterMap();
			for (Map.Entry<String, String[]> entry : param.entrySet()) {
				System.out.print(entry.getKey() + "--");
				for (String value : entry.getValue()) {
					k_id = value;
				}
			}
			kontragentService.makeReport1byKontragent(Integer.valueOf(k_id));
		}

		request.getRequestDispatcher("/report1.jsp").forward(request, response);

	}

}

package com.sehal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sehal.model.util.parser.ParsCSV;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/LoadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 100, // 100 MB
		maxRequestSize = 1024 * 1024 * 200 // 200 MB
)
public class LoadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	ParsCSV parsCSV;

	public LoadFile() {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		filePart.write(fileName);
		parsCSV.readFile(fileName);
		List<String> list = new ArrayList<>();
		list = parsCSV.readFile(fileName);
		String title = parsCSV.readTitle(list);
		System.out.println(title);
		request.setAttribute("Title", title);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("./title_maping.jsp");
		dispatcher.forward(request, response);

	}

}

package com.sehal.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletContext;

@WebServlet("/ServletImg")
public class ServletImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletImg() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext sContext = getServletContext();
		try (InputStream in = sContext.getResourceAsStream("/image/avatar.jpg");){
			OutputStream out = response.getOutputStream();
			System.out.println(sContext.getMimeType("/image/avatar.jpg"));
			if (in!=null) {
				byte[] buf = new byte[2048];
				int bRead;
				while ((bRead = in.read(buf)) != -1) {
					out.write(buf, 0, bRead);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

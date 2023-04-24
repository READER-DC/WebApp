package com.sehal.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sehal.model.services.GoodService;
import com.sehal.model.services.GoodsFamilyService;
import com.sehal.model.services.KontragentService;
import com.sehal.model.services.MatrixService;
import com.sehal.model.services.SaleService;
import com.sehal.model.services.StockService;
import com.sehal.util.parser.ParsCSV;

import jakarta.inject.Inject;
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
public class LoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private ParsCSV parsCSV;
	@Inject
	GoodService goodService;
	@Inject
	SaleService saleService;
	@Inject
	MatrixService matrixService;
	@Inject
	KontragentService kontragentService;
	@Inject
	StockService stockService;
	@Inject
	GoodsFamilyService goodsFamilyService;

	public LoadFileServlet() {
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		filePart.write(fileName);

		String servletName = request.getParameter("servlet_name");

		if (fileName.isEmpty())
			request.getRequestDispatcher("index.html");

		parsCSV.openFile(fileName);

		List<String> list = new ArrayList<>();
		list = parsCSV.readFile(fileName);
		list.remove(0);

		if (servletName.equals("TBL_SALES")) {
			saleService.insert(list);
		} else if (servletName.equals("TBL_CURR_STOCK")) {
			stockService.insert(list);
		} else if (servletName.equals("TBL_CURR_MATRIX")) {
			matrixService.insert(list);
		} else if (servletName.equals("TBL_GOODS_FAMILY")) {
			goodsFamilyService.insert(list);
		} else if (servletName.contentEquals("TBL_KONTRAGENTS")) {
			kontragentService.insert(list);
		} else if (servletName.equals("TBL_GOODS")) {
			goodService.insert(list);
		}

		response.getWriter().append("Data uploade! ");

	}

}

package com.sehal.servlets;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.sehal.model.DistribytionReport;
import com.sehal.model.Good;
import com.sehal.model.Kontragent;
import com.sehal.model.Sale;
import com.sehal.model.services.GoodService;
import com.sehal.model.services.KontragentService;
import com.sehal.model.services.SaleService;
import com.sehal.report1.ReportDistribution;

import jakarta.inject.Inject;

@WebServlet("/Report1")
public class Report1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private KontragentService kontragentService;
	@Inject
	private GoodService goodService;
	@Inject
	private SaleService saleService;
	@Inject
	ReportDistribution distribution;

	public Report1Servlet() {

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (Kontragent.kontragents.isEmpty()) {
			kontragentService.getAll();
			System.out.println("Kontragent.kontragents --> OK");
		}
		if (DistribytionReport.distribytionLines.isEmpty()) {
			kontragentService.makeReport1();
			System.out.println("DistribytionReport --> OK");
		}
		if (Good.categories.isEmpty()) {
			goodService.getCategories();
			System.out.println("Table categories --> OK");
		}
		if (Good.goods.isEmpty()) {
			goodService.getAll();
			System.out.println("Table Goods --> OK");
		}
		if (Sale.sales.isEmpty()) {
			saleService.getSales(LocalDate.now().minusDays(60).toString(),
					LocalDate.now().toString());
			System.out.println("Table Sales --> OK");
		}
			

		if (request.getParameterNames().hasMoreElements()) {

			Map<String, String[]> param = request.getParameterMap();

			for (Map.Entry<String, String[]> entry : param.entrySet()) {

				for (String value : entry.getValue()) {
					// k_id = value;
					System.out.print(entry.getKey() + "--" + value);
				}
			}
			String k_id = request.getParameter("kontragents");
			String dateStart = request.getParameter("date_start");
			String dateEnd = request.getParameter("date_end");
			String categories = request.getParameter("categories");
			
//			ReportDistribution distribution = new ReportDistribution();
			double distr1 = distribution.distribution(k_id, categories);
			String answ = String.format("%.2f", distr1);
			System.out.println("answ = " + answ);
			request.setAttribute("answ", answ);
//			if (!k_id.equals("0")) {
//				k_id = request.getParameter("kontragents");
//				ReportDistribution distribution = new ReportDistribution();
//				double distr1 = distribution.distributionByKId(k_id);
//				String answ = String.format("%.2f", distr1);
//				request.setAttribute("answ", answ);
//			} else {
//				request.setAttribute("k_id", "0");
//			}
			if (!dateStart.isEmpty() && !dateEnd.isEmpty()) {
				request.setAttribute("dateStart", dateStart);
				request.setAttribute("dateEnd", dateEnd);
				saleService.getSales(dateStart, dateEnd);
			} else {
				request.setAttribute("dateStart",
						LocalDate.now().minusDays(60).toString());
				System.out.println(LocalDate.now().minusDays(60).toString());
				request.setAttribute("dateEnd", LocalDate.now().toString());
				System.out.println(LocalDate.now().toString());
				// request.setAttribute("dateEnd",
				// LocalDate.now().format(DateTimeFormatter.ofPattern("mm/dd/yyyy")));
			}
			if (!categories.isEmpty()) {
				request.setAttribute("categories", categories);
			} else {
				request.setAttribute("categories", "0");
			}
			request.getRequestDispatcher("/report1_1.jsp").forward(request,
					response);

		} else {
			request.setAttribute("k_id", "0");
			request.setAttribute("dateStart",
					LocalDate.now().minusDays(60).toString());
			request.setAttribute("dateEnd", LocalDate.now().toString());
			request.setAttribute("categories", "0");
			request.getRequestDispatcher("/report1_1.jsp").forward(request,
					response);
		}

	}

}

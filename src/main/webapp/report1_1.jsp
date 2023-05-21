<%@page import="java.util.ArrayList"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.sehal.model.Sale"%>
<%@page import="com.sehal.model.Good"%>
<%@page import="com.sehal.model.DistribytionReport"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.Period"%>
<%@page import="java.time.temporal.ChronoUnit"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="com.sehal.model.Kontragent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<meta charset="UTF-8">
<title>Report - 1</title>
<link rel="stylesheet" href="./styles.css">
</head>
<body>

	<h4>Магазин:</h4>
	<div class="kontainer">
		<div class="form_Repo1">
			<form action="./Report1" id="repo1">
				<div class="btn_build">
					<input type="submit" value="Построить">
				</div>
				<div class="date">
					<input name="date_start" type="date" value="" /> <input
						name="date_end" type="date" value="" />
				</div>

				<select class="selKon" name="kontragents">
					<option value="0">Магазины</option>
					<%
					Kontragent kontragent = new Kontragent();
					kontragent.getAll();
					for (Kontragent item : Kontragent.kontragents) {
					%>
					<option value="<%=item.getK_ID()%>">
						<%out.print(item.getK_NAME());%>
					</option>
					<%
					}
					%>
				</select> <select class="selectCategory" name="categories">
					<option value="0">Categories</option>
					<%
					Good good = new Good();
					good.getCategories();
					for (Good item : Good.categories) {
					%>
					<option value="<%=item.getCAT_ID()%>">
						<%out.print(item.getCAT_NAME());%>
					</option>
					<%
					}
					%>
				</select>

			</form>
		</div>

		<%
		//TODO: всегда идет по пути елсе.
		String k_id = (String) request.getAttribute("k_id");
		String dateStart = (String) request.getAttribute("dateStart");
		String dateEnd = (String) request.getAttribute("dateEnd");
		String categories = (String) request.getAttribute("categories");
		List<Good> goodsByCategory = new ArrayList<>();
		//goodsByCategory = Good.categories;
		List<DistribytionReport> distrRepByK_ID = new ArrayList<>();
		//
		List<Sale> sales = new ArrayList<>();
		System.out.print("k_id: " + k_id + " // categories: " + categories);
		
		int nd_plan = 0, nd_fact = 0, qty = 0;
		long days = 0l , sale = 0l;
		double distr = 0d, salePerDay = 0d;
		if(!categories.equals("0")){
			goodsByCategory.clear();
			int category = Integer.valueOf(categories);
			for(Good item : Good.goods){
				if(item.getCAT_ID() == category){
					goodsByCategory.add(item);
				}
			}
		} else{
			goodsByCategory = Good.categories;
		}
		
		if (!k_id.equals("0") ){
			distrRepByK_ID.clear();
			int shop = Integer.valueOf(k_id);
			for(DistribytionReport line : DistribytionReport.distribytionLines){
				if(shop == line.getK_id()){
					distrRepByK_ID.add(line);
				}
			}
		} else {
			distrRepByK_ID = DistribytionReport.distribytionLines;
		}
		
		if(!goodsByCategory.equals(Good.categories)) {
			for(DistribytionReport report : distrRepByK_ID){
				for(Good goodOfCategory : goodsByCategory){
					if(report.getG_id()==goodOfCategory.getG_ID()){
						nd_plan += report.getNd_plan();
						nd_fact += report.getNd_fact();
						qty += report.getQty_stock();
					}
				}
				for (Sale item : Sale.sales) {
					if (report.getK_id() == item.getFK_K_ID() && report.getG_id() == item.getFK_G_ID())
						sale += item.getQTY();
				}
				LocalDate date1 = LocalDate.parse(dateStart);
				LocalDate date2 = LocalDate.parse(dateEnd);
				days = ChronoUnit.DAYS.between(date1, date2) + 1;
			}
		}
		
		if (!k_id.equals("0") && !k_id.equals("All")) {
			int shop = Integer.valueOf(k_id);
			for (DistribytionReport item : DistribytionReport.distribytionLines) {
				if (shop == item.getK_id()) {
					nd_plan += item.getNd_plan();
					nd_fact += item.getNd_fact();
					qty += item.getQty_stock();
				}
			}
			if (!dateStart.equals("not") && !dateEnd.equals("not")) {
				
				for (Sale item : Sale.sales) {
					if (shop == item.getFK_K_ID())
						sale += item.getQTY();
				}
				LocalDate date1 = LocalDate.parse(dateStart);
				LocalDate date2 = LocalDate.parse(dateEnd);
				days = ChronoUnit.DAYS.between(date1, date2) + 1;
				System.out.println("Дней = " + days + "  Sale = " + sale);

			}
		} else {
			for (DistribytionReport item : DistribytionReport.distribytionLines) {
				nd_plan = nd_plan + item.getNd_plan();
				nd_fact = nd_fact + item.getNd_fact();
				qty += item.getQty_stock();	
			}
			if (!dateStart.isEmpty() && !dateEnd.isEmpty()) {
				for (Sale itemAll : Sale.sales) {
					sale += itemAll.getQTY();
				}
			}
			LocalDate date1 = LocalDate.parse(dateStart);
			LocalDate date2 = LocalDate.parse(dateEnd);		days = ChronoUnit.DAYS.between(date1, date2) + 1;
			System.out.println("Дней = " + days);
		} 

		distr = nd_fact * 100d / nd_plan;

		System.out.print(k_id + "  " + nd_plan + "  " + nd_fact + "  " + qty + "  "
				+ distr + "  " + sale);
		String answ = String.format("%.2f", distr);
		%>

		<div class="main_window">
			Main Window Report
			<div class="panel_container">
				<div class="panel1">
					Дистрибуция
					<p><%=answ%>
						%
				</div>
				<div class="panel2">
					Остаток шт
					<p>
						<%=qty%>
				</div>
				<div class="panel3">
					Запас в днях
					<p>
						<%
						if (sale > 0) {
							out.print(qty / (sale / days));
						} else {
							out.print("Not sold");
						}
						%>
					
				</div>
				<div class="panel4">
					Продажи
					<p><%=sale%>
				</div>
				<div class="panel5">Сервис</div>
			</div>
		</div>
	</div>
	<script src="./js/start.js"></script>
</body>
</html>
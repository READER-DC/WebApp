<%@page import="com.sehal.model.Good"%>
<%@page import="com.sehal.model.DistribytionReport"%>
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
					for (Good item : Good.goods) {
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
		String k_id = "0";
		int nd_plan = 0, nd_fact = 0, qty = 0;
		double distr = 0d;
			for (DistribytionReport item : DistribytionReport.distribytionLines) {
				//if(item.getK_id() == Integer.valueOf(k_id)){
				nd_plan = nd_plan + item.getNd_plan();
				nd_fact = nd_fact + item.getNd_fact();
				qty += item.getQty_stock();
				//}
			}
		

		distr = nd_fact * 100d / nd_plan;
		System.out.print(k_id + "  " + nd_plan + "  " + nd_fact + "  " + qty + "  " + distr);
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
				<div class="panel3">Запас в днях</div>
				<div class="panel4">Продажи в день</div>
				<div class="panel5">Сервис</div>
			</div>
		</div>
	</div>
	<script src="./js/start.js"></script>
</body>
</html>
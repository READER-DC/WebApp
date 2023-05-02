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
					<%
					// 			Kontragent kontragent = new Kontragent();
					int i = 0;
					for (Kontragent item : Kontragent.kontragents) {
						i++;
					%>
					<option value="<%=item.getK_ID()%>">
						<%out.print(item.getK_NAME());%>
					</option>
					<%
					}
					%>
				</select>
			</form>
		</div>

		<%
		String k_id = (String)request.getAttribute("k_id");
		int nd_plan = 0, nd_fact = 0, qty = 0;
		double distr = 0d;
		for(DistribytionReport item : DistribytionReport.distribytionLines){
			if(item.getK_id() == Integer.valueOf(k_id)){
				nd_plan = nd_plan + item.getNd_plan();
				nd_fact = nd_fact + item.getNd_fact();
				qty += item.getQty_stock();
			}
		}
		System.out.print(nd_fact);
		System.out.print(nd_plan);
		distr = nd_fact * 100d / nd_plan ;
		String answ = String.format("%.2f", distr);
		System.out.print(distr);
		%>

		<div class="main_window">Main Window Report
		<div class= "panel_container">
			<div class="panel1">Дистрибуция <p><%=answ%> % </div>
			<div class="panel2">Остаток шт <p> <%=qty %></div>
			<div class="panel3">Запас в днях</div>
			<div class="panel4">Продажи в день</div>
			<div class="panel5">Сервис</div>
		</div>
		</div>
	</div>

</body>
</html>
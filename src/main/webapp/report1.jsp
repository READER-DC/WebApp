<%@page import="com.sehal.model.DistribytionReport"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import = "java.util.Date"%>
<%@page import="com.sehal.model.Kontragent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report - 1</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>

<h4>Магазин: </h4>
<div class="kontainer">
<div class = "form_Repo1">
	<form action="./Report1" id="repo1">
		<div>
			<input type="submit" value="Построить">
		</div>
		<div>
			<input name="date_start" type="date" value="" />
			<input name="date_end" type="date" value="" />
		</div>
		<select name="kontragents">
			<%
// 			Kontragent kontragent = new Kontragent();
			int i = 0;
			for (Kontragent item : Kontragent.kontragents) {
				i++;
			%>
			<option value="<%=item.getK_ID()%>"><%out.print(item.getK_NAME());%>
			</option>
			<%
				}
			%>
		</select>		
	</form>
</div>
<div class="content"> 
content 
<% 
//TODO 
	for(DistribytionReport dr: DistribytionReport.distribytionLines) {
		out.println(dr);
		%>
<p>
<%
	}
%>

</div>
</div>
</body>
</html>
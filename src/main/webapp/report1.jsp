<%@page import="java.util.Scanner"%>
<%@page import="com.sehal.model.Kontragent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Report - 1</title>
</head>
<body>
	<form action="./Report1" id="repo1">
	<div class="button">
	  <input type="submit" value="Построить">
	</div>
		<select name="kontragents">
			<% Kontragent kontragent = new Kontragent();
			int i = 0;
			for (Kontragent item : Kontragent.kontragents) {
				i++;%>
			<option value="<%=item.getK_ID()%>"> <%out.print(item.getK_NAME());%> </option>
			<% } %>
		</select>
	</form>

</body>
</html>
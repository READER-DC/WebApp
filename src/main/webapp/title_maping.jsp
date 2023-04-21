<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<h1>The input value attribute</h1>
	<p>
	<form method="get" action="./LoadFile">
		<%
		//List<String> goods = new ArrayList<>();
		String goods = (String) request.getAttribute("Title");
		String fileName = (String) request.getAttribute("fileName");
		String[] title = goods.split(";");
		%>
		<label for = "fileName">fileName:</label>
		<input type="text" id="fileName" name="fileName" value="<%out.print(fileName);%>"><br>
		<%
		for (String feeld : title) {
		%>
		<label for="<%out.print(feeld);%>"> <%out.print(feeld);%>:</label>
		<input type="text" id="<%out.print(feeld);%>"
			name="<%out.print(feeld);%>" value="<%out.print(feeld);%>"><br>
		<%}%>
		<input type="submit" value="Submit">
	</form>


</body>
</html>
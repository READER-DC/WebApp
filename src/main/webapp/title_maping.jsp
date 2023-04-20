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
	<p>
		<%
		//List<String> goods = new ArrayList<>();
		String goods = (String) request.getAttribute("Title");
		String[] title = goods.split(";");
		for (String feeld : title) {
			out.println(feeld);
		%>
		<input type="text" placeholder="<%out.println(feeld);%>"
			name="<%out.println(feeld);%>" required> <br>
		<%
}
%>
	
</body>
</html>
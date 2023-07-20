<%@page import="com.sehal.report1.ReportDistribution"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		Distribution by kontragent:
		<%
		ReportDistribution distribution = new ReportDistribution();
		out.print(distribution.distributionByKId("0"));
		
%>
	</h2>
</body>
</html>
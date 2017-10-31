<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="registerUser.User"%>
<%
	User registerUser = (User) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Register</title>
</head>
<body>
	<p>Please confirm the following information to be registered:</p>
	<p>
		Login ID:<%=registerUser.getID()%><br>
		Name:<%=registerUser.getName()%><br>
	</p>
	<a href="/RegisterUser/RegisterUser">Return</a>
	<a href="/RegisterUser/RegisterUser?action=done">Confirm</a>
</body>
</html>
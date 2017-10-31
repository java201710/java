<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="registerUser.User"%>
<%
	ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Register</title>
</head>
<body>
	<p>Thank you for registering!</p>
	<p>List of registered users::
	<table border="1">
		<tr>
			<td><b>User ID</b></td>
			<td><b>User Name</b></td>
			<%
				for (User u : userList) {
			%>
		
		<tr>
			<td><%=u.getID()%></td>
			<td><%=u.getName()%></td>
		</tr>
		<%
			}
		%>

	</table>
	<a href="/RegisterUser/RegisterUser">Return</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%
	ArrayList<String> msg = (ArrayList<String>) request.getAttribute("resultMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Register</title>
</head>
<body>
	<p>There was an error processing your request. Please see below::
	<table border="1">
		<tr>
			<td>
				<%
					for (String s : msg) {
				%>
				<p>
					<font color="red"><b> <%= s %> </b></font>
				</p> 
				<%
 					}
 				%>
			</td>
		</tr>
	</table>
	<a href="/RegisterUser/RegisterUser">Return</a>
</body>
</html>
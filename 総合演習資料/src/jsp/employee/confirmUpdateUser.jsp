<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%
String html = (String) request.getAttribute("html");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修正内容確認</title>
</head>
<body>
<h1 style="text-align:left">修正内容確認</h1>
<%= html%>
<form action="/employeeAdmin/EmployeeSystem" method="get">
<input type = "hidden" name="page" value="updateUser">
<input type="submit" value="前画面に戻る">
</form>
</body>
</html>
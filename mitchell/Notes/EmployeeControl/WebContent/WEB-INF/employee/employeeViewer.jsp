<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String selectedUser = (String) session.getAttribute("selectedUser");
	byte adminFlag = (Byte) session.getAttribute("adminFlag");
	String userMessage = (String) request.getAttribute("message");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員情報詳細</title>
</head>
<body>
社員情報詳細
<br>
<br>
	<% if (userMessage != null) { %>
		<%= userMessage %>
	<%
	}
	%>
	<hr>
	<table>
		<tr>
			<% if (adminFlag == 1) {
			%>
			<td><a href="/EmployeeControl/EmployeeSystem?page=updateUser&lastpage=viewUser">修正</a></td>
			<td><a href="/EmployeeControl/EmployeeSystem?page=deleteUser&lastpage=viewUser">削除</a></td>
			<%
			}
			%>
			<td><a href="/EmployeeControl/EmployeeSystem">戻る</a></td>
		</tr>
	</table>
</body>
</html>
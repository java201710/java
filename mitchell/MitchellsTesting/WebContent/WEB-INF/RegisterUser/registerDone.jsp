<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="registerUser.User"%>
<%
	ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<p>登録完了しました</p>
	<a href="/MitchellsTesting/RegisterUser">戻る</a>
	<p>登録完了しました人</p>
	<table border=1>
		<tr>
			<td><b>ユーザーID</b></td>
			<td><b>ユーザー名</b></td>
		</tr>
		<%
			for (User u : userList) {
		%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getName()%></td>
		</tr>
		<%
			}
		%>
	</table>
</body>
</html>
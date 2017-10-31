<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理ログイン</title>
</head>
<body>
	社員情報管理ログイン
	<form action="/EmployeeControl/EmployeeSystem" method="post">
		<input type="hidden" name="action" value="login">
		<input type="hidden" name="page" value=<%= request.getAttribute("page") %>>
		<input type="hidden" name="user" value=<%= request.getAttribute("user") %>>
		<table>
			<tr>
				<td align="right">社員ID：</td><td><input type="text" name="login_employeeId"></td>
			</tr>
			<tr>
				<td align="right">パスワード：</td><td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="ログイン"></td>
			</tr>
			</table>
	</form>
</body>
</html>
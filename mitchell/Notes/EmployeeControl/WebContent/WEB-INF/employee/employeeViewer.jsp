<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*
	* 社員情報管理の社員情報詳細画面
	*	（更新日時：２０１７年１０月２９日）
	*	<-- メイズ・ミッチェル -->　
	*/
	
	//変数
	byte login_adminFlag = (Byte) session.getAttribute("login_adminFlag");
	String html = (String) request.getAttribute("html");
	
	if (html == null) {
		html = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<style>
span#header {
	font-weight: bold;
	font-size: 25px;
}
table#dataframe {
	 width: 600px;
}

table#pictureframe {
	border: 1px solid black;
	width: 300px;
	height: 400px;
}

td#rowheader {
	font-weight: bold;
	text-align: right
}

td#pictureheader {
	font-weight: bold;
	text-align: center
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>社員情報詳細</title>
</head>
<body>
	<span id="header">社員情報詳細</span>
	<br>
	<br>
	<%= html %>
	<table>
		<tr>
			<% if (login_adminFlag == 1) { %>
			<td><a href="/EmployeeControl/EmployeeSystem?page=updateUser&lastpage=viewUser">修正</a></td>
			<td><a href="/EmployeeControl/EmployeeSystem?page=deleteUser&lastpage=viewUser">削除</a></td>
			<% } %>
			<td><a href="/EmployeeControl/EmployeeSystem">戻る</a></td>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*
	*メイズ・ミッチェル　（更新日時：２０１７年１０月２８日）
	*（未入力）
	*/

	//引数の初期化
	byte adminFlag = (Byte) session.getAttribute("adminFlag");
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
	<%=html%>
	<hr>
	<table>
		<tr>
			<%
				if (adminFlag == 1) {
			%>
			<td><a
				href="/employeeAdmin/EmployeeSystem?page=updateUser&lastpage=viewUser">修正</a></td>
			<td><a
				href="/employeeAdmin/EmployeeSystem?page=deleteUser&lastpage=viewUser">削除</a></td>
			<%
				}
			%>
			<td><a href="/employeeAdmin/EmployeeSystem">戻る</a></td>
		</tr>
	</table>
</body>
</html>
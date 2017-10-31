<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%

//入力した情報を取得
//EmployeeBean employeeBean = (EmployeeBean) request.getAttribute("EmployeeBean");
//EmployeeSystemLogic logic = new EmployeeSystemLogic();
String lastpage = (String) request.getAttribute("lastpage");
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
<meta charset="UTF-8">
<title>削除内容確認</title>
</head>
<body>
<%=html %>
<table>
		<tr>
			<td><a href="/employeeAdmin/EmployeeSystem?action=delete">削除</a></td>
			<td><% if (lastpage == null) { %>
				<a href="/employeeAdmin/EmployeeSystem">前画面に戻る</a>
			<% } else { %>
				<a href="/employeeAdmin/EmployeeSystem?page=<%= lastpage %>">前画面に戻る</a>
			<% } %>
			</td>
		</tr>
	</table>
</body>
</html>
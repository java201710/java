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
<meta charset="UTF-8">
<title>削除内容確認</title>
</head>
<body>
<%=html %>
<table>
		<tr>
			<td><a href="/EmployeeControl/EmployeeSystem?action=delete">削除</a></td>
			<td><% if (lastpage.equals("")) { %>
				<a href="/EmployeeControl/EmployeeSystem">前画面に戻る</a>
			<% } else { %>
				<a href="/EmployeeControl/EmployeeSystem?page=<%= lastpage %>">前画面に戻る</a>
			<% } %>
			</td>
		</tr>
	</table>
</body>
</html>
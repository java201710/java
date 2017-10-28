<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%
	/*
	*メイズ・ミッチェル　（更新日時：２０１７年１０月２８日）
	*（未入力）
	*/

	//引数の初期化
	String employeeName = (String) session.getAttribute("employeeName");
	String html = (String) request.getAttribute("html");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	byte adminFlag = (Byte) session.getAttribute("adminFlag");
	int employeeId = (Integer) session.getAttribute("employeeId");
	
	if (html == null) {
		html = "";
	}
	if (message == null) {
		message = new ArrayList<String>();
	}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body style="min-width:1000px;">
	<table width=100%>
		<tr>
			<td align="left">社員情報管理</td>
			<%
			if (message != null) {
				for (String s : message) {
				%>

				<td align="center"><%=s %></td>
				<%
				}
				%>
			<%
			}
			%>
			<td align="right">ユーザー：<% if (adminFlag == 1) {
											%>
											<font color="blue"><%= employeeName %></font>
										<% } else { %>
											<%= employeeName %>
										<%
										}
										%>さん<br><a href="">ログアウト</a>
			</td>
		</tr>
	</table>
	<table width=100%>
	<tr>
		<td><table style="width:100psx">
			<tr>
				<td align="center">メニュー</td>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
			<tr>
				<td align="center"><a href="">
								<a href="/employeeAdmin/EmployeeSystem?lastpage&page=viewuser&selectedUser=
								<%= employeeId %>">個人情報</a></td>
			</tr>
				<% if (adminFlag == 1) {
					%>
					<tr>
						<td align="center"><a href="/employeeAdmin/EmployeeSystem?page=registerUser">新規登録</a></td>
					</tr>
				<%
				}
				%>
		</table></td>
		<td colspan="2" align="center"><form action="/employeeAdmin/EmployeeSystem" method="post">
			<input type="hidden" name="action" value="search">
			<table border=1>
				<tr>
					<td colspan="8" align="left">検索するには</td>
				</tr>
				<tr>
					<td>拠点：</td><td><select name="baseName"><option value=0>--</option></select></td>
					<td>部署：</td><td><select name="departmentName"><option value=0>--</option></select></td>
					<td>課：</td><td><select name="divisionName"><option value=0>--</option></select></td>
					<td>役職：</td><td><select name="positionName"><option value=0>--</option></select></td>
				</tr>
					<td>入社年月：</td><td colspan="3"><select name="fromDate"><option value=0>--</option></select></td>
					<td>　～　</td><td colspan="3"><select name="toDate"><option value=0>--</option></select></td>
				</tr>
				<tr>
					<td colspan="8" align="right"><input type="submit" value="検索"></td>
				</tr>
			</table>
		</form></td>
	</tr>
	</table>
	<br>
	社員情報
	<div style="height: 500px; overflow-y: auto; overflow-x: auto">
	<%= html %>
	</div>
</body>
</html>
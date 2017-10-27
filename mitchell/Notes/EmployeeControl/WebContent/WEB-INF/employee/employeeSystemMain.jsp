<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%
	String employeeName = (String) session.getAttribute("employeeName");
	byte adminFlag = (Byte) session.getAttribute("adminFlag");
	int employeeId = (Integer) session.getAttribute("employeeId");



	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();



	ArrayList<String> baseList = eSysLogic.baseList;
	ArrayList<String> departmentList = eSysLogic.departmentList;
	ArrayList<String> divisionList = eSysLogic.divisionList;
	ArrayList<String> positionList = eSysLogic.positionList;
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
										%>さん<br><a href="/EmployeeControl/EmployeeSystem?logout=1">ログアウト</a>
			</td>
		</tr>
	</table>
	<table border=1 width=100%>
	<tr>
		<td><table border=1>
			<tr>
				<td align="center">メニュー</td>
			</tr>
			<tr>
				<td align="center"><hr></td>
			</tr>
			<tr>
				<td align="left"><a href="">
								<a href="/EmployeeControl/EmployeeSystem?page=viewuser&selectedUser=
								<%= employeeId %>">個人情報</a></td>
			</tr>
				<% if (adminFlag == 1) {
					%>
					<tr>
						<td align="left"><a href="/EmployeeControl/EmployeeSystem?page=registerUser">新規登録</a></td>
					</tr>
				<%
				}
				%>
		</table></td>
		<td align="center"><form action="/EmployeeControl/EmployeeSystem" method="post">
			<input type="hidden" name="action" value="search">
			<table border=1>
				<tr>
					<td colspan="8" align="left">検索するには</td>
				</tr>
				<tr>
					<td>拠点：</td><td><!-- <select name="baseName"><option value=0>--</option></select> --><%= eSysLogic.createSelectBox("baseName", baseList) %></td>
					<td>部署：</td><td><!-- <select name="departmentName"><option value=0>--</option></select> --><%= eSysLogic.createSelectBox("departmentName", departmentList) %></td>
					<td>課：</td><td><!-- <select name="divisionName"><option value=0>--</option></select> --><%= eSysLogic.createSelectBox("divisionName", divisionList) %></td>
					<td>役職：</td><td><!-- <select name="positionName"><option value=0>--</option></select> --><%= eSysLogic.createSelectBox("PositionName", positionList) %></td>
				</tr>
					<td>入社年月：</td><td colspan="3"><select name="fromDate"><option value=0>--</option></select></td>
					<td>　～　</td><td colspan="3"><select name="toDate"><option value=0>--</option></select></td>
				</tr>
				<tr>
					<td colspan="8" align="right"><input type="submit" value="検索"></td>
				</tr>
			</table>
		</form></td>
		<td><table border=1>
			<tr>
				<td>hi</td>
			</tr>
		</table></td>
	</tr>
	</table>
	<br>
	社員情報

	<div style="height: 500px; overflow-y: scroll; overflow-x: scroll">
	<table border=1 width=100%>
		<tr>
			<th>社員ＩＤ</th><th>名前</th><th>拠点</th><th>部署</th><th>課</th><th>役職</th><th>内線</th><th>携帯番号</th>
		</tr>
		<tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr>
		<tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr>
		<tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr>
		<tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr>
		<tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr><tr><td>1</td></tr>
	</table>
	</div>
</body>
</html>
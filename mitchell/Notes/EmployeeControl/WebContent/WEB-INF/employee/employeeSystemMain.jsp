<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="employee.model.EmployeeBean"%>
<%
	/*
	* 社員情報管理メイン画面
	*	（更新日時：２０１７年１０月２９日）
	*	<-- メイズ・ミッチェル -->　
	*/

	//引数
	//セッションスコープの変数
	String employeeName = (String) session.getAttribute("employeeName");
	int employeeId = (Integer) session.getAttribute("employeeId");
	byte login_adminFlag = (Byte) session.getAttribute("login_adminFlag");
	
	//リクエストスコープの変数
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	String html = (String) request.getAttribute("html");
	EmployeeBean eBean = (EmployeeBean) request.getAttribute("employeeBean"); //
	
	//変数
	EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();
	
	if (message == null) {
		message = new ArrayList<String>();
	}
	if (html == null) {
		html = "";
	}
%>

<!DOCTYPE html>
<html>
<head>
<style>
table#fulltable {
	width: 100%;
}
table#menu {
	width: 100px;
	border: 1px solid;
}
table#searchform {
	border: 1px solid;
}

td#pagetitle {
	text-align: left;
	font-weight: bold;
	font-size: 25px;
}
td#notificationarea {
	text-align: center;
	color: red;
	font-weight: bold;
	font-size: 20px;
}
td#userinformation {
	font-weight: bold;
	text-align: right;
}
td#menuheader {
	font-weight: bold;
	text-align: center;
}
td#center {
	text-align: center;
}
span#summaryarea {
	font-weight: bold;
	font-size: 20px;
}
th {
	border: 1px solid;
	width: 10%;
}
th#short {
	border: 1px solid;
	width: 3%;
}
th#long {
	border: 1px solid;
	width: 12%
}
</style>
<meta charset="UTF-8">
<title>社員情報管理</title>
</head>
<body style="min-width:1000px;">

	<!-- ページの先頭：ページのタイトル・メッセージエリア・ログインしたユーザーの情報 -->
	<table id=fulltable>
		<tr>
			<td id=pagetitle>社員情報管理</td>
			
				<!-- リクエストスコープにメッセージがあったら、表示する -->
				<% if (message.size() > 0) { %>
					<td id=notificationarea><%= message.get(0) %></td>
				<% } %>
			<td id=userinformation>ユーザー：
				<% if (login_adminFlag == 1) { %>
					<font color="blue"><%= employeeName %></font>
				<% } else { %>
						<%= employeeName %>
				<% } %>さん<br>
				<a href="/EmployeeControl/EmployeeSystem?logout=1">ログアウト</a>
			</td>
		</tr>
	</table>
	
	<!-- メニュー表と検索のフォーム -->
	<table id=fulltable>
		<tr>
			
			<!-- メニュー表 -->
			<td><table id=menu>
				<tr>
					<td id=menuheader>メニュー</td>
				</tr>
				<tr>
					<td><hr></td>
				</tr>
				<tr>
					<td id=center><a href="/EmployeeControl/EmployeeSystem?lastpage&page=viewuser&selectedUser=<%= employeeId %>">個人情報</a></td>
				</tr>
					<% if (login_adminFlag == 1) { %>
						<tr>
							<td id=center><a href="/EmployeeControl/EmployeeSystem?page=registerUser">新規登録</a></td>
						</tr>
					<% } %>
				</table>
			</td>
			
			<!-- 検索フォーム -->
			<td colspan="2">
				<form action="/EmployeeControl/EmployeeSystem" method="post">
					<input type="hidden" name="action" value="search">
					<table id=searchform>
						<tr>
							<td colspan="8" align="left">検索するには</td>
						</tr>
						<tr>
							<td>拠点：</td><td><%= eSysLogic.createSelectBox("baseName", eBean).get(0) %></td>
							<td>部署：</td><td><%= eSysLogic.createSelectBox("departmentName", eBean).get(0) %></td>
							<td>課：</td><td><%= eSysLogic.createSelectBox("divisionName", eBean).get(0) %></td>
							<td>役職：</td><td><%= eSysLogic.createSelectBox("positionName", eBean).get(0) %></td>
						</tr>
							<td>入社年月：</td>
							<td align="center" colspan="3"><%= eSysLogic.createSelectBox("fromDate", eBean).get(0) %></td>
							<td id=center>～</td>
							<td align="center" colspan="3"><%= eSysLogic.createSelectBox("toDate", eBean).get(0) %></td>
						</tr>
						<tr>
							<td colspan="8" align="right"><input type="submit" value="検索"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
	<br>
	
	<!-- 社員情報一覧・検索の結果 -->
	<span id=summaryarea>社員情報</span>
	<div style="height: 500px; overflow-y: auto; overflow-x: auto;">
	<table id=fulltable>
		<% if (login_adminFlag == 1) { %>
			<tr><th id=short></th><th id=short></th>
		<% } %>
		<th id=short>社員ＩＤ</th><th>名前</th><th>拠点</th><th id=long>部署</th><th>課</th><th>役職</th><th>内線</th><th>携帯番号</th></tr>
	<%= html %>
	</table>
	</div>
</body>
</html>
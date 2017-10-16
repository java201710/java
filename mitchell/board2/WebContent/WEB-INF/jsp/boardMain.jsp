<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="model.BoardBean"%>
    <%@ page import="model.BoardLogic"%>
<%
	ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
		BoardLogic bLogic = new BoardLogic();

	//Updated for request field 17/10/16
	String name = (String) request.getAttribute("name");
	if (name == null) {
		name = "";
	}
	String comment = (String) request.getAttribute("comment");
	if (comment == null) {
		comment = "";
	}

%>

<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>掲示板メイン</title>
</head>
<body>
<p>掲示板メイン</p>
<p>新規投稿<p>
<% if (message != null) { %>
	<table border="1">
	<% for (String s : message) { %>
	<tr><td><font color="red"><b> <%= s %></b></font></td></tr>
	<% } %>
	</table>
	<% } %>
<form action="/board2/BoardDo" method="post">
	名前:<input type="text" name="name"><br>
	E-Mail:<input type="text" name="email"><br>
	コメント:<textarea rows="4" cols="50" name="comment"></textarea><br>
	<!-- Removed 17/10/16 replaced by textarea<input type="text" name="comment"><br>  -->
	<input type="hidden" name="action" value="add">
	<input type="submit" value="新規投稿">
	</form>

	<!-- New Request form (17/10/16) -->
	<hr>
	<form action="/board2/BoardDo" method="post">
	名前:<input type="text" name="name"><br>
	コメント:<textarea rows="4" cols="50" name="comment"></textarea><br>
	<input type="hidden" name="action" value="search">
	<input type="submit" value="検索">
	</form>

	<% if (boardList != null) {
		%>

		<!-- Edited to incorporate the new search feater 17/10/16
		<%= bLogic.show(boardList) %>
		-->
		<%= bLogic.show(bLogic.search(name, comment, boardList)) %>

		<!-- Removed as of 2017.10.16
		<table border="1">
			<% for (BoardBean bBean : boardList) { %>
			<tr><td>
			<table>
			<tr><td>No.<%= bBean.getId() %>：</td><td><%= bBean.getName() %></td></tr>
			<tr><td>E-Mail：<%= bBean.getEmail() %></td></tr>
			<tr><td></td><td>投稿日時：<%= bBean.getDateTime() %> </td></tr>
			<tr><td>コメント：<%= bBean.getComment() %></td></tr>
			</table>
			<% } %>
			</td></tr>
		</table>
		 -->
	<% } %>
	<form action="/board2/BoardDo" method="post">
	管理者用:<input type="password" name="adminpass"><br>
	<input type="hidden" name="action" value="admin">
	<input type="submit" value="送信">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="model.BoardBean"%>
    <%@ page import="model.BoardLogic"%>
<%
	ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	BoardLogic bLogic = new BoardLogic();
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
	コメント:<textarea rows="4" cols="50" name="comment"></textarea>
	<!-- <input type="text" name="comment"><br>  -->
	<input type="hidden" name="action" value="add">
	<input type="submit" value="新規投稿">
	</form>

	<% if (boardList != null) {
		ArrayList<BoardBean> reverseList = new ArrayList<BoardBean>();
		for (int i = boardList.size()-1;i > -1; i--) {
			reverseList.add(boardList.get(i));
		}
		%>
		<%= bLogic.show(reverseList) %>

		<!-- Removed as of 2017.10.16
		<table border="1">
			<% for (BoardBean bBean : reverseList) { %>
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
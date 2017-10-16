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
<title>掲示板管理</title>
</head>
<body>
<p>掲示板管理</p>
<% if (message != null) { %>
	<table border="1">
	<% for (String s : message) { %>
	<tr><td><font color="red"><b> <%= s %></b></font></td></tr>
	<% } %>
	</table>
	<% } %>
<a href="/board2/BoardDo">Return</a>
<!-- Removed 17/10/16
<form action="/board2/BoardDo" method="post">
	管理者用:<input type="text" name="delid"><br>
	<input type="hidden" name="action" value="del">
	<input type="submit" value="送信">
	</form>
 -->
 <% if (boardList != null) { %>
<form action="/board2/BoardDo" method="post">
 <%= bLogic.selectBox(boardList) %>


 <!--  Moved to BoardLogic.java
 <select name="delid">

 			<% for (BoardBean bBean : boardList) {%>
 			<option value = <%= bBean.getId() %> >No.<%= bBean.getId() %></option>
 		<% } %>

</select>
-->
<input type="hidden" name="action" value="del">
	<input type="submit" value="送信">
	</form>
<% } %>

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
			<tr><td>No.<%= bBean.getId() %>：</td><td><%= bBean.getName() %></td><td>E-Mail：<%= bBean.getEmail() %></td></tr>
			<tr><td></td><td>投稿日時：<%= bBean.getDateTime() %></td></tr>
			<tr><td>コメント：<%= bBean.getComment() %></td></tr>
			</table>
			<% } %>
			</td></tr>
		</table>
		-->
	<% } %>
</body>
</html>
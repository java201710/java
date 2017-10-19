<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.BoardBean"%>
<%@ page import="model.BoardLogic"%>

<%
	//ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	BoardLogic bLogic = new BoardLogic();
%>
<!-- ミッチェル -->
<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	text-align: center;
}

table#msgtable {
	width: auto;
	margin-left: auto; margin-right: auto;
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
	color: red;
	font-weight: bold;
	margin-right: auto;
}

</style>
<meta charset="UTF-8">
<title>掲示板管理</title>
</head>
<body>
	<h1>掲示板管理</h1>
	<hr>
	<%
		if (message != null) {
	%>
	<table id="msgtable" border="1">
		<%
			for (String s : message) {
		%>
		<tr>
			<td><font color="red"><b> <%=s%></b></font></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	<a href="/board2/BoardDo">Return</a>

	<form action="/board2/BoardDo" method="post">
		<%=bLogic.selectBox(boardList)%>

		<input type="hidden" name="action" value="del"> <input
			type="submit" value="送信">
	</form>

	<%=bLogic.show(boardList)%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.BoardBean"%>
<%@ page import="model.BoardLogic"%>
<%
	//ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
	BoardLogic bLogic = new BoardLogic();

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
<style>
h1 {
	text-align: center;
}

table#admincontrol {
	width: auto;
	margin-left: auto; margin-right: auto;
	boarder-collapse: collapse
	font-weight: bold;
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
<title>掲示板メイン</title>
</head>
<body>
	<h1>掲示板メイン</h1>
	<hr>
	<p>
		<%
			if (message != null) {
		%>

		<table id="msgtable" border="1">
		<%
			for (String s : message) {
		%>
		<tr><td><font color="red"><b> <%=s%></b></font></td></tr>
		<%
			}
		%>

	</table>
	<%
		}
	%>
	<p><b>新規投稿</b></p>

	<form action="/boardDB/BoardDo" method="post">
		<input type="hidden" name="action" value="add">
		<table>
			<tr>
				<td align="right">名前：</td><td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td align="right">E-Mail：</td><td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td align="right">コメント：</td><td><textarea rows="4" cols="50" name="comment"></textarea></td><td>※必須入力</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="新規投稿"></td>
			</tr>
		</table>
	</form>
	<hr>
	<form action="/boardDB/BoardDo" method="post">
		<input type="hidden" name="action" value="search">
		<table>
			<tr>
				<td align="right">名前:</td><td><input type="text" name="name" value=<%= name %>></td><td>※完全一致</td>
			</tr>
			<tr>
				<td align="right">コメント:</td><td><input type="text" name="comment" value=<%= comment  %>></td><td>※部分一致</td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" value="検索"></td>
			</tr>
		</table>

	</form>
	<hr>
	<%=bLogic.show(name, comment)%>
	<form action="/boardDB/BoardDo" method="post">
	<input type="hidden" name="action" value="admin">
		<table id="admincontrol">
			<tr>
				<td>管理者用:</td>
				<td><input type="password" name="adminpass"></td>
				<td><input type="submit" value="送信"></td>
			</tr>
		</table>
	</form>
</body>
</html>
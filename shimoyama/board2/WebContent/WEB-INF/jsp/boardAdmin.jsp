<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.BoardBean"%>
<%@ page import="model.BoardLogic"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
ArrayList<String> message =(ArrayList<String>) request.getAttribute("message");
BoardLogic boardLogic = new BoardLogic();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板管理</title>
</head>
<body>
<Div Align="center"><h1>掲示板管理</h1></Div>
<%
if(message != null){
%>
<h2><%= message.get(0) %></h2>
<%
}
%>
<Div Align="right"><a href="/board2/BoardDo">掲示板メインへ戻る</a></Div>
<hr>
<form action="/board2/BoardDo" method="post">
<input type="hidden" name="action" value="del">
投稿削除No:<select name="delid">
<%
for (BoardBean c : boardList) {
%>
    <option value="<%=c.getId()%>"><%=c.getId()%></option>
<%
}
%>
</select>
<input type="submit" value="送信">
</form>
<hr>
<%= boardLogic.show(boardList) %>
<br>
</body>
</html>

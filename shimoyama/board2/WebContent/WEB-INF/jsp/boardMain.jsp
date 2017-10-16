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
<title>掲示板メイン</title>
</head>
<body>
<Div Align="center"><h1>掲示板メイン</h1></Div>
<hr>
<%
if(message != null){
%>
<h2><%= message.get(0) %></h2>
<hr>
<%	
}
%>
新規投稿<form action="/board2/BoardDo" method="post">
<input type="hidden" name="action" value="add">
　　名前：<input type="text" name="name"><br>
　e-mail：<input type="text" name="email"><br>
 コメント:<TEXTAREA name="comment" cols="40" rows="6" ></TEXTAREA><br>
<Div Align="right"><input type="submit" value="新規投稿"></Div>
</form>
<hr>
<%= boardLogic.show(boardList) %>
<br>
<form action="/board2/BoardDo" method="post">
<input type="hidden" name="action" value="admin">
<Div Align="center">管理者用：<input type="password" name="adminpass">
<input type="submit" value="送信"></Div><br>
</form>
</body>
</html>

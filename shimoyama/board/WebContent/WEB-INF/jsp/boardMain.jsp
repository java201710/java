<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.BoardBean"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
ArrayList<String> message =(ArrayList<String>) request.getAttribute("message");
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
新規投稿<form action="/board/BoardDo" method="post">
<input type="hidden" name="action" value="add">
　　名前：<input type="text" name="name"><br>
　e-mail：<input type="text" name="email"><br>
 コメント:<input type="text" name="comment"><br>
<Div Align="right"><input type="submit" value="新規投稿"></Div><br>
</form>
<hr>
<%
if(boardList != null){
for (int i = boardList.size()-1;i >= 0;i--) {
%>
	No.<%=boardList.get(i).getId()%>: <%=boardList.get(i).getName()%><br>
	E-mail: <%=boardList.get(i).getEmail()%><br>
<Div Align="right">投稿日時: <%=boardList.get(i).getDateTime()%></Div><br>
	コメント: <%=boardList.get(i).getComment()%><br>
	<hr>
<%
}
}
%>
<form action="/board/BoardDo" method="post">
<input type="hidden" name="action" value="admin">
<Div Align="center">管理者用：<input type="password" name="adminpass">
<input type="submit" value="送信"></Div><br>
</form>
</body>
</html>

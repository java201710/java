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
<Div Align="right"><a href="/board/BoardDo">掲示板メインへ戻る</a></Div>
<hr>
<form action="/board/BoardDo" method="post">
<input type="hidden" name="action" value="del">
投稿削除No:<input type="text" name="delid"><input type="submit" value="送信">
</form>
<hr>
<%
if(boardList != null){
for (int i = boardList.size()-1;i >= 0;i--) {
%>
	No.<%=boardList.get(i).getId()%>: <%=boardList.get(i).getName()%>
　　　E-mail: <%=boardList.get(i).getEmail()%><br>
<Div Align="right">投稿日時: <%=boardList.get(i).getDateTime()%></Div><br>
	コメント: <%=boardList.get(i).getComment()%><br>
	<hr>
<%
}
}
%>
<br>
</body>
</html>

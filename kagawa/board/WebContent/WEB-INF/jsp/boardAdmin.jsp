<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.BoardBean" %>
<%@ page import="java.util.ArrayList" %>
<%
@SuppressWarnings("unchecked")
//既存のboarderListを取得
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
@SuppressWarnings("unchecked")
//messageを取得
ArrayList<String> messageList = (ArrayList<String>) request.getAttribute("message");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板</title>
</head>
<body>
<h1 style="text-align:center">掲示板管理</h1>
<%
if(messageList != null){
%>
<%= messageList.get(0) %>
<%
}
%>
<p style="text-align:right"><a href="/board/BoardDo">掲示板メインへ戻る</a>
<p>-----------------------------------------------------------------------------------------------
<form action="/board/BoardDo" method="post">
<p>投稿削除No.：<input type="text" name="delid" >
<input type = "hidden" name="action" value="del">
<input type="submit" value="送信"><br>
</form>
<%
if(boardList != null){
	for(int i = boardList.size() - 1; i >= 0; i--){
%>
<p>-----------------------------------------------------------------------------------------------
<p>No.<%= boardList.get(i).getId() %>：<%= boardList.get(i).getName() %>
E-Mail：<%= boardList.get(i).getEmail() %><br>
<p style="text-align:center">投稿日時：<%= boardList.get(i).getDateTime() %><br>
<p>コメント：<%= boardList.get(i).getComment() %><br>
<%
	}
}
%>
<p>-----------------------------------------------------------------------------------------------
</body>
</html>

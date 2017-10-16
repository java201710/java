<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.BoardBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardLogic" %>
<%
@SuppressWarnings("unchecked")
//既存のboarderListを取得
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
@SuppressWarnings("unchecked")
//messageを取得
ArrayList<String> messageList = (ArrayList<String>) request.getAttribute("message");
//ロジッククラスのインスタンス作成
BoardLogic logic = new BoardLogic();
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
<p style="text-align:right"><a href="/board2/BoardDo">掲示板メインへ戻る</a>
<p>-----------------------------------------------------------------------------------------------
<form action="/board2/BoardDo" method="post">
<p>投稿削除No.：
<%= logic.selectBox(boardList) %>
<input type = "hidden" name="action" value="del">
<input type="submit" value="送信"><br>
</form>
<%= logic.show(boardList) %>
<p>-----------------------------------------------------------------------------------------------
</body>
</html>

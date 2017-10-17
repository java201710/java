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
//検索フォームに入力した名前とコメントをリクエストスコープから取得
String name = (String) request.getAttribute("name");
if(name == null)
	name = "";

String comment = (String) request.getAttribute("comment");
if(comment == null)
	comment = "";

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
<h1 style="text-align:center">掲示板メイン</h1>
<%
if(messageList != null && messageList.size() != 0){
%>
<%= messageList.get(0) %>
<%
}
%>
<p>-----------------------------------------------------------------------------------------------
<p>新期投稿
<form action="/board2/BoardDo" method="post">
<p>　名前：<input type="text" name="name" >
<p>E-Mail：<input type="text" name="email" >
<p>コメント：<textarea name="comment"  rows="4" cols="40"></textarea>※必須入力
<input type = "hidden" name="action" value="add">
<p style="text-align:center"><input type="submit" value="新規投稿">
</form>
<p>-----------------------------------------------------------------------------------------------
<p>検索
<form action="/board2/BoardDo" method="post">
<p>　名前：<input type="text" name="name" value=<%= name %>>※完全一致
<p>コメント：<input type="text" name="comment" value=<%= comment %>>※部分一致
<input type = "hidden" name="action" value="search">
<p style="text-align:center"><input type="submit" value="検索">
</form>
<%= logic.show(logic.search(name,comment,boardList)) %>
<p>-----------------------------------------------------------------------------------------------
<form style="text-align:center" action="/board2/BoardDo" method="post">
管理者用：<input type="password" name="adminpass" >
<input type = "hidden" name="action" value="admin">
<input type="submit" value="送信"><br>
</form>
</body>
</html>

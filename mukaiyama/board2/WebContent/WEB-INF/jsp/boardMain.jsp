<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardBean" %>
<%@ page import="model.BoardLogic" %>
<%
//<<アプリケーションスコープからの取得>>
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>)application.getAttribute("boardList");
if(boardList==null){
	boardList = new ArrayList<BoardBean>();
}
//<<リクエストスコープからの取得>>
//投稿リスト
ArrayList<String> message = (ArrayList<String>)request.getAttribute("message");
if(message==null){
	message = new ArrayList<String>();
}
//検索フォーム．名前（初期値）
String searchName = (String)request.getAttribute("name");
if(searchName==null){
	searchName = "";
}
//検索フォーム．コメント（初期値）
String searchComment = (String)request.getAttribute("comment");
if(searchComment==null){
	searchComment = "";
}
//<<インスタンス>>
BoardLogic boardLogic = new BoardLogic();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板メイン</title>
</head>
<body>
<div align="center"><h1>掲示板メイン</h1>
<%
for(String msg:message){
	%>
	<p><font color="red"><%=msg %></font></p>
	<%
}
%>
</div>
<hr/>
<p>新規投稿</p>
<form action="/board2/BoardDo" method="post">
名前：<input type="text" name="name"><br>
E-Mail：<input type="text" name="email"><br>
コメント：<br>
<textarea name="comment" rows="4" cols="40"></textarea>※必須入力<br>
<input type="submit" value="新規投稿">
<input type="hidden" name="action" value="add">
</form>
<hr/>
<p>検索</p>
<form action="/board2/BoardDo" method="post">
名前：<input type="text" name="name" value=<%=searchName %>>※完全一致<br>
コメント：<input type="text" name="comment" value=<%=searchComment %>>※部分一致<br>
<input type="submit" value="検索">
<input type="hidden" name="action" value="search">
</form>
<hr/>
<%= boardLogic.show(boardLogic.search(searchName, searchComment, boardList)) %>
<div align="center">
<form action="/board2/BoardDo" method="post">
管理者用：<input type="password" name="adminpass">
<input type="submit" value="送信">
<input type="hidden" name="action" value="admin">
</form>
</div>
</body>
</html>

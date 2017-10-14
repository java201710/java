<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.BoardBean" %>
<%@ page import="java.util.ArrayList" %>
<%
ArrayList<BoardBean> boardList = (ArrayList<BoardBean>)application.getAttribute("boardList");
if(boardList==null){
	boardList = new ArrayList<BoardBean>();
}
ArrayList<String> message = (ArrayList<String>)request.getAttribute("message");
if(message==null){
	message = new ArrayList<String>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<div align="center"><h1>掲示板管理</h1>
<%
for(String msg:message){
	%>
	<p><font color="red"><%=msg %></font></p>
	<%
}
%>
</div>
<div align="right"><a href="/board/BoardDo">掲示板メインへ戻る</a></div>
<hr/>
<form action="/board/BoardDo" method="post">
投稿削除№：<input type="text" name="delid">
<input type="submit" value="送信">
<input type="hidden" name="action" value="del">
</form>
<hr/>
<%
for(int i=boardList.size()-1; i>=0; i--){
	BoardBean b = boardList.get(i);
	%>
	<p>No.<%=b.getId() %>:<%=b.getName() %>  E-Mail:<%=b.getEmail() %></p>
	<p>投稿日時:<%=b.getDateTime() %></p>
	<p>コメント:<%=b.getComment() %></p>
	<hr/>
	<%
}
%>
</body>
</html>
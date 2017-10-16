<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.BoardBean" %>
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
<textarea name="comment" rows="4" cols="40"></textarea><br>
<input type="submit" value="新規投稿">
<input type="hidden" name="action" value="add">
</form>
<hr/>
<%
for(int i=boardList.size()-1; i>=0; i--){
	BoardBean b = boardList.get(i);
	%>
	<p>No.<%=b.getId() %>:<%=b.getName() %></p>
	<p>E-Mail:<%=b.getEmail() %></p>
	<p>投稿日時:<%=b.getDateTime() %></p>
	<p>コメント:</p>
	<p><%=b.getComment().replaceAll("\n", "<br>") %></p>
	<hr/>
	<% System.out.print(b.getComment());
}
%>
<div align="center">
<form action="/board2/BoardDo" method="post">
管理者用：<input type="password" name="adminpass">
<input type="submit" value="送信">
<input type="hidden" name="action" value="admin">
</form>
</div>
</body>
</html>

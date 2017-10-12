<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="model.User"%>
<%
	ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
	String result;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<p>登録出来ませんでした：入力したＩＤを使えません。</p>
	<%
		if (request.getAttribute("id") != null) {
	%>
	<p>
		入力したＩＤ：<%=request.getAttribute("id")%></p>
	<a href="/javaWeb02/RegisterUser">戻る</a>

	<p>登録完了しました人</p>
	<table border=1>
		<tr>
			<td><b>ユーザーID</b></td>
			<td><b>ユーザー名</b></td>
		</tr>
		<%
			for (User u : userList) {
		%>
		<tr>
			<td><%=u.getId()%></td>
			<td><%=u.getName()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
			 //int i;
			 //int i = (String) request.getAttribute("result"));
			 int i = (Integer) request.getAttribute("result");
			 //result =  (String) request.getAttribute("result");
			 //i = Integer.parseInt(result);


			//userScanner Results:: 1. No Id, 2. No Pass, 3. No Name, 4. Improper Pass
			if (i == 1) { %>
	<p>IDを入力してください。</p>
	<%} else if (i == 2) { %>
	<p>パスワードを入力してください。</p>
	<%} else if (i == 3) { %>
	<p>名前を入力してください。</p>

	<%} else if (i == 4) {%>
	<p>正しいパスワードを入力してください。</p>

	<% }} %>
	<a href="/javaWeb02/RegisterUser">戻る</a>
</body>
</html>
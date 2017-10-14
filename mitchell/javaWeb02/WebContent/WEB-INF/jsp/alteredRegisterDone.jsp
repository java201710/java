<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = (String) request.getAttribute("id");
	String name = (String) request.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
	<p>
		ユーザーID:<%=id%><br> ユーザーのパスワード：（秘密)<br> ユーザー名：<%=name%>
	</p>
	<p>登録完了しました</p>
	<a href="/javaWeb02/RegisterUser">戻る</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Do I need to cast this as String? Is it just to be save? -->
<%
	String msg = (String) request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザー登録結果</title>
</head>
<body>
	<p><%= msg %></p>
	<a href="/MitchellsTesting/ForwardServletWithForm">リンク</a>
</body>
</html>
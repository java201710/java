<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
User registerUser = (User) session.getAttribute("registerUser");
Boolean isIDDuplication = (Boolean) session.getAttribute("isIDDuplication");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>
<% if(registerUser.getId().equals("")){ %>
ログインIDが空白です。<br>
<% } if(registerUser.getName().equals("")){ %>
名前が空白です。<br>
<% } if(registerUser.getPass().equals("")){ %>
パスワードが空白です。<br>
<% } if(isIDDuplication){ %>
ログインIDが重複しています。<br>
<% } %>
</p>
<a href="/javaWeb03/RegisterUser">戻る</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="registerUser.User" %>
<%
//Because I did not set a user in the controller then this does not work anymore
//User registerUser = (User) session.getAttribute("registerUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>下記のユーザーを登録します</p>
<!-- Modif this because of the changes made to what is being set -->
<!-- Removed: registerUser.getId(), registerUser.getName() -->
<p>
ログインID：<%= registerUser.getId() %><br>
名前:<%= registerUser.getName() %><br>
</p>
<a href="/MitchellsTesting/RegisterUser">戻る</a>
<a href="/MitchellsTesting/RegisterUser?action=done">登録</a>
</body>
</html>
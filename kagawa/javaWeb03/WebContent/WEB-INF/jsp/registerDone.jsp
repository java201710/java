<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
@SuppressWarnings("unchecked")
ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
// 直接呼ばれたら以下の処理がいる
//if (userList == null)
	//userList = new ArrayList<User>();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>登録完了しました</p>
<p>登録ユーザは<br>
<% for(User user:userList){ %>
ログインID：<%= user.getId() %>、
名前:<%= user.getName() %><br>
<% } %>
</p>
<a href="/javaWeb03/RegisterUser">戻る</a></body>
</html>
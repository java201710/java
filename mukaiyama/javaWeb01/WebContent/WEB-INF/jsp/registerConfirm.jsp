<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
User registerUser = (User) session.getAttribute("registerUser");
ArrayList<String> errList = (ArrayList<String>)session.getAttribute("errList");
if(errList==null){
	errList = new ArrayList<String>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<%
if(errList.size() > 0){
	for(String errMsg:errList){
	%>
	<p><%= errMsg %></p>
	<%
	}
}else{
%>
	<p>下記のユーザーを登録します</p>
<%
}
%>
<p>
ログインID：<%= registerUser.getId() %><br>
名前:<%= registerUser.getName() %><br>
</p>
<a href="/javaWeb01/RegisterUser">戻る</a>
<%
if(errList.size() == 0){
%>
	<a href="/javaWeb01/RegisterUser?action=done">登録</a>
<% } %>
</body>
</html>
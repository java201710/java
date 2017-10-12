<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%
User registerUser = (User) session.getAttribute("registerUser");
String err = (String) session.getAttribute("err");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<%
if(err.equals("ID")){
%>
<p style="color:red;"><b>下記のユーザーは既に存在しています。</b></p>
<p>
ログインID：<%= registerUser.getId() %><br>
名前:<%= registerUser.getName() %><br>
</p>
<%
}else if((err.equals(""))){
%>
<p style="color:blue;"><b>下記のユーザーを登録します</b></p>
<p>
ログインID：<%= registerUser.getId() %><br>
名前:<%= registerUser.getName() %><br>
</p>
<%
}else{
	if (registerUser.getId().equals("")) {
%>
<p style="color:red;"><b>ログインIDが未入力です。</b></p>
<%
	}
	if (registerUser.getPass().equals("")) {
%>
<p style="color:red;"><b>パスワードが未入力です。</b></p>
<%
	}
	if (registerUser.getName().equals("")) {
%>
<p style="color:red;"><b>名前が未入力です。</b></p>
<%
	}
}
%>
<a href="/javaWeb00/RegisterUser">戻る</a>
<%
if(err.equals("")){
%>
<a href="/javaWeb00/RegisterUser?action=done">登録</a>
<%
}
%>
</body>
</html>
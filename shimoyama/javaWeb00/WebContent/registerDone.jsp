<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
if(userList==null){
	userList= new ArrayList<User>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>登録完了しました</p><br>
<table border="1"><tr><th>id</th><th>name</th><th>pass</th></tr>
<%
for(int i=0;i<userList.size();i++){
%>
<tr><td><%= userList.get(i).getId() %></td><td><%= userList.get(i).getName() %></td><td><%= userList.get(i).getPass() %></td></tr>
<%
}
%>
</table>
<br><a href="/javaWeb00/RegisterUser">戻る</a></body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
ArrayList<User> userList = (ArrayList<User>)application.getAttribute("userList");
if(userList==null){
	  userList = new ArrayList<User>();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録</title>
</head>
<body>
<p>登録完了しました</p>
<table border="1">
	<tr>
		<th>ログインID</th>
		<th>名前</th>
		<th>パスワード</th>
	</tr>
<%
	for(User user:userList){
		%>
		<tr>
			<td><%=user.getId() %></td>
			<td><%=user.getName() %></td>
			<td><%=user.getPass() %></td>
		</tr>
		<%
	}
%>
</table>
<a href="/javaWeb01/RegisterUser">戻る</a></body>
</html>
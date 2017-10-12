<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録もどき</title>
</head>
<body>
<form action="/javaWeb03/FormSampleServlet" method="get">
名前：<br>
<input type="text" name="name"><br>
性別：<br>
男<input type="radio" name="gender" value="0">
女<input type="radio" name="gender" value="1">
<input type="submit" value="登録">
<input type = "hidden" name = "action" value="1">
<a href="/javaWeb03/FormSampleServlet?name=%E9%A6%99%E5%B7%9D&gender=0&action=1">
リンク</a>
</form>
</body>
</html>
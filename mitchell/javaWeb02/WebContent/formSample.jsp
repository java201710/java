<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録もどき</title>
</head>
<body>
<!-- <form action="/javaWeb02/FormSampleServlet" method="post"> -->
<form action="/javaWeb02/FormSampleServlet" method="get">
名前：<br>
<input type="text" name="name"><br>
性別：<br>
男<input type="radio" name="gender" value="0">
女<input type="radio" name="gender" value="1">
<input type="hidden" name="action" value="hello">
<input type="submit" value="登録">
<a href="/javaWeb02//FormSampleServlet?name=%E3%83%9F%E3%83%83%E3%83%81%E3%82%A7%E3%83%AB&gender=0&action=1">リンク</a>
</form>
</body>
</html>
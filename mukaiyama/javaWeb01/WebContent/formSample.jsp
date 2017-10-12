<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<% String myName="向山"; %>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー登録もどき</title>
</head>
<body>
<form action="/javaWeb01/FormSampleServlet" method="get">
名前：<br>
<input type="text" name="name"><br>
性別：<br>
男<input type="radio" name="gender" value="0">
女<input type="radio" name="gender" value="1">
<input type="submit" value="登録">
<input type="hidden" name="action" value="1">
</form>
<a href="/javaWeb01/FormSampleServlet?name=<%=myName %>&gender=0&action=901">リンク1（変数を使用）</a><br/>
<a href="/javaWeb01/FormSampleServlet?name=向山&gender=0&action=902">リンク2（URLに名前を直接セット）</a><br/>
<a href="/javaWeb01/FormSampleServlet?name=%E5%90%91%E5%B1%B1&gender=0&action=903">リンク3（変換コード）</a><br/>
</body>
</html>
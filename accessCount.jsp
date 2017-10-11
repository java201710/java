<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="javax.servlet.*"%>
<%
	Integer count1 = (Integer) request.getAttribute("count1");
	if (count1 == null) {
		count1 = 0;
	}
	request.setAttribute("count1", ++count1);
	
	
	Integer count2 = (Integer) session.getAttribute("count2");
	if (count2 == null) {
		count2 = 0;
	}
	session.setAttribute("count2", ++count2);
	
	
	Integer count3 = (Integer) application.getAttribute("count3");
	if (count3 == null) {
		count3 = 0;
	}
	application.setAttribute("count3", ++count3);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>カウンタ</title>
</head>
<body>
	<p>　　Requestは<%= count1 %>回目のアクセスです</p><br>
	<p>　　Sessionは<%= count2 %>回目のアクセスです</p><br>
	<p>applicationは<%= count3 %>回目のアクセスです</p><br>
</body>
</html>
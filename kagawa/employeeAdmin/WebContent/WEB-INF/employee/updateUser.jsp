<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%
//messageを取得
ArrayList<String> messageList = (ArrayList<String>) request.getAttribute("message");
if(messageList == null){
	messageList = new ArrayList<String>();
}

String html = (String) request.getAttribute("html");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修正</title>
</head>
<body>
<%
for(int i = 1;i<messageList.size();i++){
%>
<%= messageList.get(i) %>
<%
}
%>
<%= html%>
</body>
</html>
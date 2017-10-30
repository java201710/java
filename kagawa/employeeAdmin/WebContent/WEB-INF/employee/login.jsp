<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList"%>
<%
int user;
if(request.getAttribute("user")==null){
	user = 0;
}else{
	user = (Integer)request.getAttribute("user");
}

String message = (String)request.getAttribute("message");
if(message==null){
	message = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
<P><%=message %>
<FORM name="body" ACTION="/employeeAdmin/EmployeeSystem" method="post">
<INPUT TYPE="hidden" NAME="action" value="login" >
<INPUT TYPE="hidden" NAME="user" value=<%= user %> >
<P>社員ID：
<INPUT TYPE="text" NAME="employeeId" maxlength='9' size="60" value= "">
<P>パスワード：
<INPUT TYPE="password" NAME="password" maxlength='20' size="60" value= "">
<P><INPUT TYPE="submit" VALUE="ログイン" NAME="login">
</FORM>
</body>
</html>
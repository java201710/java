<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Form Register</title>
</head>
<body>
	<form action="/RegisterUser/RegisterUser" method="post">
	Login ID:<input type="text" name="id"><br> 
	Password:<input type="password" name="pass"><br> 
	Name:<input type="text" name="name"><br>
	<input type="submit" value="Confirm">
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%
//入力した情報を取得
EmployeeBean employeeBean = (EmployeeBean) request.getAttribute("EmployeeBean");
EmployeeSystemLogic logic = new EmployeeSystemLogic();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録内容確認</title>
</head>
<body>
<h1 style="text-align:left">登録内容確認</h1>
<p>　社員ID：
<%= employeeBean.getEmployeeId() %>
<p>　パスワード：
<%= employeeBean.getPassword() %>
<p>　名前：
<%= employeeBean.getEmployeeName() %>
<p>　ふりがな：
<%= employeeBean.getKana() %>
<p>性別：
<%= employeeBean.getGender() %>
<p>部署名：
<%= employeeBean.getDepartmentName() %>
<p>課名：
<%= employeeBean.getDivisionName() %>
<p>役職名：
<%= employeeBean.getPositionName() %>
<p>　役職詳細：
<%= employeeBean.getPositionMemo() %>
<p>　内線：
<%= employeeBean.getNaisenNumber() %>
<p>　業務携帯番号：
<%= employeeBean.getPublicCellphoneNumber() %>
<p>　写真：
<img src=\"img/" + employeeBean.getEmployeeId() + ".jpg\"/>
<form action="/employee/EmployeeSystem" method="get">
<input type = "hidden" name="action" value="register">
<p><input type="submit" value="登録">
</form>
<form action="/employee/EmployeeSystem" method="get">
<input type = "hidden" name="page" value="registerUser">
<input type="submit" value="前画面に戻る">
</form>
</body>
</html>
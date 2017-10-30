<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%
//messageを取得
ArrayList<String> messageList = (ArrayList<String>) request.getAttribute("message");
EmployeeSystemLogic logic = new EmployeeSystemLogic();
//入力フォームに入力した項目をリクエストスコープから取得
String employeeId = (String) request.getAttribute("employeeId");
if(employeeId == null)
	employeeId = "";

String password = (String) request.getAttribute("password");
if(password == null)
	password = "";

String employeeName = (String) request.getAttribute("employeeName");
if(employeeName == null)
	employeeName = "";

String kana = (String) request.getAttribute("kana");
if(kana == null)
	kana = "";

String gender = (String) request.getAttribute("gender");
if(gender == null)
	gender = "";

String departmentName = (String) request.getAttribute("departmentName");
if(departmentName == null)
	departmentName = "";

String divisionName = (String) request.getAttribute("divisionName");
if(divisionName == null)
	divisionName = "";

String positionName = (String) request.getAttribute("positionName");
if(positionName == null)
	positionName = "";

String positionMemo = (String) request.getAttribute("positionMemo");
if(positionMemo == null)
	positionMemo = "";

String naisenNumber = (String) request.getAttribute("naisenNumber");
if(naisenNumber == null)
	naisenNumber = "";

String publicCellphoneNumber = (String) request.getAttribute("publicCellphoneNumber");
if(publicCellphoneNumber == null)
	publicCellphoneNumber = "";


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
</head>
<body>
<h1 style="text-align:left">社員情報 新規登録</h1>
<%
if(messageList != null && messageList.size() != 0){
	for(String m : messageList){
%>
<%= m %>
<%
	}
}
%>
<form action="/employee/EmployeeSystem" method="post">
<p>　社員ID：<input type="number" name="employeeId" value=<%= employeeId %> >
<p>　パスワード：<input type="password" name="password" value=<%= password %>>
<p>　名前：<input type="text" name="employeeName" value=<%= employeeName %>>
<p>　ふりがな：<input type="text" name="kana" value=<%= kana %>>
<p>性別：
<%= logic.selectGenderBox() %>
<p>部署名：
<%= logic.selectDepartmentBox() %>
<p>課名：
<%= logic.selectDivisionBox() %>
<p>役職名：
<%= logic.selectPositionBox() %>
<p>　役職詳細：<input type="text" name="positionMemo" value=<%= positionMemo %>>
<p>　内線：<input type="text" name="naisenNumber" value=<%= naisenNumber %>>
<p>　業務携帯番号：<input type="text" name="publicCellphoneNumber" value=<%= publicCellphoneNumber %>>
<input type = "hidden" name="action" value="confirmNewUser">
<p><input type="submit" value="登録">
</form>
<form action="/employee/EmployeeSystem" method="get">
<input type="submit" value="戻る">
</form>
</body>
</html>
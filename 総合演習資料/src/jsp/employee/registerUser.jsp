<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="employee.model.EmployeeBean"%>
<%@ page import="employee.model.EmployeeSystemLogic"%>
<%@ page import="java.util.ArrayList" %>
<%
//messageを取得
//ArrayList<String> messageList = (ArrayList<String>) request.getAttribute("message");
String messageList = (String) request.getAttribute("message");
if(messageList==null){
	messageList="";
}
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
	<a><%= messageList %>
	<form action="/employeeAdmin/EmployeeSystem" method="post">
		<p>　社員ID：<input type="number" name="employeeId" value=<%= employeeId %> >
		<p>　パスワード：<input type="password" name="password" value=<%= password %>>
		<p>　名前：<input type="text" name="employeeName" value=<%= employeeName %>>
		<p>　ふりがな：<input type="text" name="kana" value=<%= kana %>>
		<p>性別：
			<%= logic.selectGenderBox() %>
		<p>部署名：
			<select name='departmentCode'>
				<option value="101">本社経営企画部</option>
				<option value="102">本社経理部</option>
				<option value="103">本社人事部</option>
				<option value="104">本社システム部</option>
				<option value="105">本社総務部</option>
				<option value="106">本社営業部</option>
				<option value="201">名古屋支店経営企画部</option>
				<option value="204">名古屋支店システム部</option>
				<option value="206">名古屋支店営業部</option>
				<option value="301">大阪支店経営企画部</option>
				<option value="304">大阪支店システム部</option>
				<option value="306">大阪支店営業部</option>
				<option value="406">仙台支店営業部</option>
			</select>
		<p>課名：
			<select name='divisionCode'>
				<option value="00">なし</option>
				<option value="11">推進企画課</option>
				<option value="12">事業企画課</option>
				<option value="21">経理課</option>
				<option value="31">人事課</option>
				<option value="41">システム課</option>
				<option value="51">総務課</option>
				<option value="61">営業1課</option>
				<option value="62">営業2課</option>
				<option value="63">営業3課</option>
			</select>
		<p>役職名：
			<select name='positionCode'>
				<option value="6">役員</option>
				<option value="5">部長</option>
				<option value="4">課長</option>
				<option value="3">主任</option>
				<option value="2">一般社員</option>
				<option value="1">契約社員</option>
			</select>
		<p>　役職詳細：<input type="text" name="positionMemo" value=<%= positionMemo %>>
		<p>　内線：<input type="text" name="naisenNumber" value=<%= naisenNumber %>>
		<p>　業務携帯番号：<input type="text" name="publicCellphoneNumber" value=<%= publicCellphoneNumber %>>
		<input type = "hidden" name="action" value="confirmNewUser">
		<p><input type="submit" value="登録">
	</form>
	<form action="/employeeAdmin/EmployeeSystem" method="get">
		<input type="submit" value="戻る">
	</form>
</body>
</html>
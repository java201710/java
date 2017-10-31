<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.BoardBean"%>
<%
	ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
	ArrayList<String> message = (ArrayList<String>) request.getAttribute("message");
%>

<!DOCTYPE html>
<html>
<head>
<style>

input#inputshort {
	padding: 0px 40px

}

h1 {
	text-align: center;
}

p#newpostheader {
	padding: 0px 20px;
	font-weight: bold;
}

table#msgtable {
	width: auto;
	margin-left: auto;
	margin-right: auto;
	border-collapse: collapse;
	border: 1px solid black;
	text-align: center;
	color: red;
	font-size: 120%;
	font-weight: bold;
}

table#postinputtable {
	padding: 0px 60px
}
td#inputheader {
	font-weight: bold;
	text-align: right;
}
td#submitbutton {
	text-align: right;
	padding: 5px 10px;

}

</style>
<meta charset="UTF-8">
<title>掲示板メイン</title>
</head>
<body>
	<h1>掲示板メイン
	</h1>
	<hr />
	<!-- Message area. Any messages passed from the server will appear here. -->
	<%
		if (message != null) {
	%>
	<table id="msgtable" border="1">
		<%
			for (String s : message) {
		%>
		<tr>
			<td><%=s%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
	<p id="newpostheader">新規投稿</p>
	<form action="/board/BoardDo" method="post">
	<input type="hidden" name="action" value="add">
	<table id="postinputtable">
		<tr><td id="inputheader">名前　：</td><td><input id="inputshort" type="text" name="name"></td></tr>
		<tr><td id="inputheader">E-Mail　：</td><td><input id="inputshort" type="text" name="email"></td></tr>
		<tr><td id="inputheader">コメント　：</td><td><input id="inputshort"　type="text" name="comment"></td></tr>
		<tr><td></td><td id="submitbutton"> <input type="submit" value="新規投稿"></td></tr>
	</table>
	</form>
	<hr>

	<%
		if (boardList != null) {
			ArrayList<BoardBean> reverseList = new ArrayList<BoardBean>();
			for (int i = boardList.size() - 1; i > -1; i--) {
				reverseList.add(boardList.get(i));
			}
	%>
	<table border="1">
		<%
			for (BoardBean bBean : reverseList) {
		%>
		<tr>
			<td>
				<table>
					<tr>
						<td>No.<%=bBean.getId()%>：
						</td>
						<td><%=bBean.getName()%></td>
					</tr>
					<tr>
						<td>E-Mail：<%=bBean.getEmail()%></td>
					</tr>
					<tr>
						<td></td>
						<td>投稿日時：<%=bBean.getDateTime()%>
						</td>
					</tr>
					<tr>
						<td>コメント：<%=bBean.getComment()%></td>
					</tr>
				</table> <%
 	}
 %>
			</td>
		</tr>
	</table>
	<%
		}
	%>
	<form action="/board/BoardDo" method="post">
		管理者用:<input type="password" name="adminpass"><br> <input
			type="hidden" name="action" value="admin"> <input
			type="submit" value="送信">
	</form>
</body>
</html>
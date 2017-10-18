<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<%
	//変数定義
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	request.setCharacterEncoding("UTF-8");

	//DB接続情報を設定する
	String path = "jdbc:mysql://localhost/java_db"; //接続パス
	String id = "root"; //ログインID
	String pw = "root"; //ログインパスワード

	try {
		//JDBCドライバをロードする
		Class.forName("com.mysql.jdbc.Driver");

		//DBへのコネクションを作成する
		conn = DriverManager.getConnection(path, id, pw);

		Statement stmt = conn.createStatement();

		// INSERT
		stmt.executeUpdate
				("INSERT INTO board(name,email,time,comment)VALUES('下山','aaa@bbb.com','2017-08-28 16:17:20','コメント\nコメント')");

		//DELETE
		stmt.executeUpdate
				("DELETE FROM board WHERE ID=" + (int) (Math.random() * 100 + 1));

		//DELETE
		stmt.executeUpdate
				("UPDATE board SET name='変身' WHERE id=" + (int) (Math.random() * 30 + 1));

		//SQL文を定義する
		String sql = "SELECT * FROM board WHERE id > ? AND email LIKE ? ORDER BY id DESC";

		//実行するSQL文とパラメータを指定する
		ps = conn.prepareStatement(sql);

		ps.setInt(1, 1);
		ps.setString(2, "%@%");

		//SELECTを実行する
		rs = ps.executeQuery();
%>
<html>
<head>
<meta charset="UTF-8">
<title>掲示板JSP</title>
</head>
<body>
	<%
		while (rs.next()) {
	%>
	<hr>
	No.<%=rs.getInt("id")%>:<%=rs.getString("name")%>
	<br>E-Mail:<%=rs.getString("email")%>
	<br>投稿日時：<%=rs.getString("time")%>
	<br>コメント：<%=rs.getString("comment")%>
	<br>
	<%
		}
	%>
</body>
</html>
<%
	//取得した結果を全件出力する
	} catch (Exception ex) {
		//例外発生時の処理
		ex.printStackTrace(); //エラー内容をコンソールに出力する

	} finally {
		//クローズ処理
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception ex) {
		}
	}
%>

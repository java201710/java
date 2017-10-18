package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jdbcSample")
public class jdbcSample extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//変数定義
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		request.setCharacterEncoding("UTF-8");

		//DB接続情報を設定する
		String path = "jdbc:mysql://localhost/boarddb"; //接続パス
		String id = "root"; //ログインID
		String pw = "root00"; //ログインパスワード

		try {
			//JDBCドライバをロードする
			Class.forName("com.mysql.jdbc.Driver");

			//DBへのコネクションを作成する
			conn = DriverManager.getConnection(path, id, pw);

			Statement stmt = conn.createStatement();

			// INSERT
			stmt.executeUpdate
					("INSERT INTO board_db(name,email,comment,datetime) VALUES('下山', 'aaa@bbb.com', 'コメント\nコメント', '2017-08-28 16:17:20')");

			// DELETE
			stmt.executeUpdate
					("DELETE FROM board_db WHERE ID=" + (int) (Math.random() * 100 + 1));

			// UPDATE
			stmt.executeUpdate
				("UPDATE board_db SET name='変身' WHERE id=" + (int) (Math.random() * 30 + 1));

			//SQL文を定義する
			String sql = "SELECT * FROM board_db WHERE id > ? AND email LIKE ? ORDER BY id DESC";


			//実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);

			ps.setInt(1, 1);
			ps.setString(2, "%@%");

			//SELECTを実行する
			rs = ps.executeQuery();

			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>掲示板yo</title>");
			out.println("</head>");
			out.println("<body>");

			while (rs.next()) {
				out.print("<hr>No." + rs.getInt("id"));
				out.print(":" + rs.getString("name"));
				out.print("<br>E-Mail:" + rs.getString("email"));
				out.print("<br>投稿日時：" + rs.getString("datetime"));
				out.print("<br>コメント：" + rs.getString("comment"));
				out.println();
			}
			out.println("</body>");
			out.println("</html>");
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
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}

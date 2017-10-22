package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BoardBean;

public class DaoLogic {

	//DB接続情報を設定する
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String PATH = "jdbc:mysql://localhost/boarddb"; //接続パス
	private final String ID = "root"; //ログインID
	private final String PW = "root00"; //ログインパスワード

	//変数定義
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// DBに接続する
	private void startDB() {
		try {
			//JDBCドライバをロードする
			Class.forName(DRIVER);

			//DBへのコネクションを作成する
			conn = DriverManager.getConnection(PATH, ID, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DBから切断する
	private boolean endDB() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 取得メソッド
	public ArrayList<BoardBean> findBoard(String sql) {
		// 戻り値の掲示板リスト
		ArrayList<BoardBean> boardList = new ArrayList<BoardBean>();

		try {
			startDB();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String comment = rs.getString("comment");
				String dateTime = rs.getDate("dateTime") +" "+ rs.getTime("dateTime");
				BoardBean board = new BoardBean(id, name, email, comment, dateTime);
				boardList.add(board);
			}
		} catch (Exception e) {
			//例外発生時の処理
			e.printStackTrace(); //エラー内容をコンソールに出力する
			return null;
		} finally {
			// DBから切断する（失敗した場合は戻り値null）
			if (!endDB())
				return null;
		}

		return boardList;
	}

	// 更新メソッド
	public Boolean updateBoard(String sql) {

		try {
			startDB();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();

		} catch (Exception e) {
			//例外発生時の処理
			e.printStackTrace(); //エラー内容をコンソールに出力する
			return false;
		} finally {
			// DBから切断する
			endDB();
		}

		return true;
	}
}

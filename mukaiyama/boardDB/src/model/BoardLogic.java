package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import dao.DaoLogic;

public class BoardLogic {
	private static String adminPass = "root00"; //管理用パスワード

	//新規投稿ボタン用
	public ArrayList<String> add(BoardBean boardBean) {
		ArrayList<String> message = new ArrayList<String>();
		boolean normalEnd = true; //正常終了

		//入力内容チェック
		if (boardBean.getComment() == null || boardBean.getComment().length() == 0) {
			message.add("コメントを入力してください。");
			normalEnd = false;
		}

		//編集
		if (normalEnd == true) {
			if (boardBean.getName() == null || boardBean.getName().length() == 0) {
				boardBean.setName("ゲスト"); //名前
			}
			if (boardBean.getEmail() == null || boardBean.getEmail().length() == 0) {
				boardBean.setEmail("なし"); //E-Mail
			}
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			boardBean.setDateTime(f.format(now)); //投稿日時

			//20171019 update start mukaiyama
			String sql = "INSERT INTO board_db(name,email,comment,dateTime) " +
					"values('"
						+ boardBean.getName()
						+ "','"
						+ boardBean.getEmail()
						+ "','"
						+ boardBean.getComment()
						+ "','" + boardBean.getDateTime()
						+ "')";
			DaoLogic Dao = new DaoLogic();
			if(Dao.updateBoard(sql)==true){
				message.add("投稿しました。");
			}
			//20171019 update end mukaiyama
		}

		return message;
	}

	//送信ボタン（掲示板メイン）用
	public ArrayList<String> admin(String pass) {
		ArrayList<String> message = new ArrayList<String>();

		//入力内容チェック
		if (pass.equals(adminPass) == false) {
			message.add("パスワードが違います。");
		} else {
			message.add("");
		}

		return message;
	}

	//送信ボタン（掲示板管理）用
	//20171019 update 1Line mukaiyama
//	public ArrayList<String> del(String id, ArrayList<BoardBean> boardList) {
	public ArrayList<String> del(String id) {
		ArrayList<String> message = new ArrayList<String>();
		boolean normalEnd = false; //異常終了
		if(id!=null && !id.equals("")){
		//入力内容チェック
		Pattern pattern = Pattern.compile("^[0-9]*$");
			if (pattern.matcher(id).matches()) {
				int intId = Integer.parseInt(id);
				//20171019 delete start mukaiyama
	//			for (BoardBean b : boardList) {
	//
	//				if (b.getId() == intId) {
	//					normalEnd = true;
	//					message.add("投稿を削除しました。");
	//					break;
	//				}
	//			}
				//20171019 delete end mukaiyama
				//20171019 add start mukaiyama
				String sql = "DELETE FROM board_db WHERE id = " + id;
				DaoLogic Dao = new DaoLogic();
				if(Dao.updateBoard(sql)==true){
					message.add("投稿を削除しました。");
				}
			}
			//20171019 add end mukaiyama
//20171019 update start mukaiyama
//		}
//		if (normalEnd == false) {
		}else{
//20171019 update end mukaiyama
			message.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}

		return message;
	}

	//掲示板リスト画面表示用
	//20171019 update 1Line mukaiyama
//	public String show(ArrayList<BoardBean> boardList) {
	public String show(String name, String comment) {
		Common c = new Common();
		StringBuffer buf = new StringBuffer();

		//20171019 add start mukaiyama
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM board_db");
		if(!name.equals("")||!comment.equals("")){
			sql.append("WHERE ");
			if(comment.equals("")){
				//名前≠"" and コメント＝""
				sql.append("name = '");
				sql.append(name);
				sql.append("'");
			}else if(name.equals("")){
				//名前＝"" and コメント≠""
				sql.append("comment LIKE '%");
				sql.append(comment);
				sql.append("%'");
			}else{
				//名前≠"" and コメント≠""
				sql.append("name = '");
				sql.append(name);
				sql.append("' AND ");
				sql.append("comment LIKE '%");
				sql.append(comment);
				sql.append("%'");
			}
		}
		DaoLogic Dao = new DaoLogic();
		ArrayList<BoardBean> boardList = Dao.findBoard(sql.toString());
		//20171019 add end mukaiyama

		for (int i = boardList.size() - 1; i >= 0; i--) {
			//投稿リスト取り出し
			BoardBean b = boardList.get(i);

			//HTML作成
			buf.append("<p>No.");
			buf.append(b.getId());
			buf.append("：　");
			buf.append(c.sanitizing(b.getName()));
			buf.append("</br>");
			buf.append("E-Mail：　");
			buf.append(c.sanitizing(b.getEmail()));
			buf.append("<br>");
			buf.append("投稿日時：　");
			buf.append(b.getDateTime());
			buf.append("<br>");
			buf.append("コメント：<br>");
			buf.append(c.sanitizing(b.getComment()).replaceAll("\n", "<br>"));
			buf.append("</p>");
			buf.append("<hr/>");
		}

		return buf.toString();
	}

	//20171019 add start mukaiyama
	//掲示板リスト画面表示用
	public String show() {
		return this.show("", "");
	}
	//20171019 add end mukaiyama

	//掲示板管理 投稿削除№セレクトボックス用
	//20171019 update 1Line mukaiyama
//	public String selectBox(ArrayList<BoardBean> boardList) {
	public String selectBox() {
		StringBuffer buf = new StringBuffer();

		//20171019 add start mukaiyama
		DaoLogic Dao = new DaoLogic();
		ArrayList<BoardBean> boardList = Dao.findBoard("SELECT * FROM board_db");
		//20171019 add end mukaiyama

		//HTML作成
		buf.append("<select name='delid'>");
		for (BoardBean b : boardList) {
			buf.append("<option value=");
			buf.append(b.getId());
			buf.append(">");
			buf.append(b.getId());
			buf.append("</option>");

		}
		buf.append("</select>");

		return buf.toString();
	}

// 2017/10/19 delete mukaiyama
//	//検索ボタン用
//	public ArrayList<BoardBean> search(String name, String comment, ArrayList<BoardBean> boardList) {
//		if(name.equals("")&&comment.equals("")){
//			return boardList;
//		}else{
//			ArrayList<BoardBean> returnList = new ArrayList<BoardBean>();	//戻り値用リスト
//			String blName;
//			String blComment;
//
//			for(BoardBean b:boardList){
//				blName = b.getName();
//				blComment = b.getComment();
//
//				//<<条件チェック>>
//				//引数.name≠""
//				if(!name.equals("")){
//					if(!name.equals(blName)){
//						//引数.nameと完全一致？→NG時
//						continue;	//読み飛ばす
//					}
//				}
//				//引数comment≠""
//				if(!comment.equals("")){
//					if(blComment.matches(".*" + comment + ".*")==false){
//						//引数.commentと部分一致？→NG時
//						continue;	//読み飛ばす
//					}
//				}
//
//				//戻り値用リストに追加
//				returnList.add(b);
//			}
//
//			return returnList;
//		}
//	}
}

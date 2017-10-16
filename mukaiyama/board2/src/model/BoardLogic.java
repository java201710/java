package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

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

			message.add("投稿しました。");
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
	public ArrayList<String> del(String id, ArrayList<BoardBean> boardList) {
		ArrayList<String> message = new ArrayList<String>();
		boolean normalEnd = false; //異常終了

		//入力内容チェック
		Pattern pattern = Pattern.compile("[0-9]");
		if (pattern.matcher(id).matches()) {
			int intId = Integer.parseInt(id);
			for (BoardBean b : boardList) {
				if (b.getId() == intId) {
					normalEnd = true;
					message.add("投稿を削除しました。");
					break;
				}
			}
		}
		if (normalEnd == false) {
			message.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}

		return message;
	}

	//掲示板リスト画面表示用
	public String show(ArrayList<BoardBean> boardList) {
		StringBuffer buf = new StringBuffer();

		for (int i = boardList.size() - 1; i >= 0; i--) {
			//投稿リスト取り出し
			BoardBean b = boardList.get(i);

			//HTML作成
			buf.append("<p>No.");
			buf.append(b.getId());
			buf.append("：　");
			buf.append(b.getName());
			buf.append("</br>");
			buf.append("E-Mail：　");
			buf.append(b.getEmail());
			buf.append("<br>");
			buf.append("投稿日時：　");
			buf.append(b.getDateTime());
			buf.append("<br>");
			buf.append("コメント：<br>");
			buf.append(b.getComment().replaceAll("\n", "<br>"));
			buf.append("</p>");
			buf.append("<hr/>");
		}

		return buf.toString();
	}

	//掲示板管理 投稿削除№セレクトボックス用
	public String selectBox(ArrayList<BoardBean> boardList) {
		StringBuffer buf = new StringBuffer();

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
}

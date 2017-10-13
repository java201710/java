package model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class BoardLogic {
	ArrayList<String> messageList = new ArrayList<String>(); // メッセージを格納
	final String PASSWORD = "root00"; //正しい管理者用パスワード

	// 新規投稿ボタンをクリックした時
	public ArrayList<String> add(BoardBean board) {

		if (board.getComment().equals(""))
			messageList.add("コメントを入力してください。");
		else {
			//名前が未入力の場合、「ゲスト」と置き換える
			if (board.getName().equals(""))
				board.setName("ゲスト");
			//emailが未入力の場合、「なし」と置き換える
			if (board.getEmail().equals(""))
				board.setEmail("なし");
			messageList.add("投稿しました。");
		}

		return messageList;
	}

	// 管理画面にアクセスする送信ボタンをクリックした時
	public ArrayList<String> admin(String password) {

		if (!password.equals(PASSWORD))
			messageList.add("パスワードが違います。");
		else
			messageList.add("");

		return messageList;
	}

	// 投稿を削除する送信ボタンをクリックした時
	public ArrayList<String> del(String delid, ArrayList<BoardBean> boardList) {
		// 投稿削除Noの正常パターンの生成
		Pattern pattern = Pattern.compile("^[0-9]$");
		// 入力した投稿削除Noが存在する投稿削除Noかどうか
		Boolean isExistNo = false;

		// パターンのチェック（半角数字かどうか）
		if (!pattern.matcher(delid).matches())
			messageList.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		else {
			// 存在する投稿削除Noかどうかのチェック
			for (BoardBean board : boardList) {
				if (String.valueOf(board.getId()).equals(delid)) {
					isExistNo = true;
					break;
				}
			}

			if (isExistNo)
				messageList.add("投稿を削除しました。");
			else
				messageList.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}

		return messageList;
	}
}
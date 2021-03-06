package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

			// 投稿日時を取得
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateTime = f.format(now);
			board.setDateTime(dateTime);
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

	// 画面に表示する掲示板を格納
	public String show(ArrayList<BoardBean> boardList) {

		String result = "";
		if (boardList != null) {
			for (int i = boardList.size() - 1; i >= 0; i--) {
				result = result
						+ "-----------------------------------------------------------------------------------------------<br>";
				result = result + "No." + boardList.get(i).getId() + "：" + boardList.get(i).getName() + "<br>";
				result = result + "E-Mail：" + boardList.get(i).getEmail() + "<br>";
				result = result + "投稿日時：" + boardList.get(i).getDateTime() + "<br>";
				result = result + "コメント：" + boardList.get(i).getComment().replaceAll("\n", "<br>") + "<br>";
			}
		}
		return result;
	}

	// 投稿削除Noの選択肢を格納
	public String selectBox(ArrayList<BoardBean> boardList) {

		String result = "<select name='delid'>";

		if (boardList != null) {
			for (int i = boardList.size() - 1; i >= 0; i--) {
				result = result + "<option>" + boardList.get(i).getId() + "</option>";
			}
		}
		result = result + "</select>";

		return result;
	}

	// 検索結果後の掲示板を返す
	public ArrayList<BoardBean> search(String name, String comment, ArrayList<BoardBean> boardList) {

		ArrayList<BoardBean> boardSearchResultList = new ArrayList<BoardBean>();
		// 名前とコメント両方入力なしの場合、全ての掲示板を返す
		if ((name == "" && comment == "") || boardList == null)
			return boardList;
		// 名前入力なしで、コメント入力ありの場合
		else if (name == "") {
			for (BoardBean board : boardList) {
				if (board.getComment().matches(".*" + comment + ".*"))
					boardSearchResultList.add(board);
			}
		}
		// 名前入力ありで、コメント入力なしの場合
		else if (comment == "") {
			for (BoardBean board : boardList) {
				if (board.getName().equals(name))
					boardSearchResultList.add(board);
			}
		}
		// 名前とコメント両方入力ありの場合
		else {
			for (BoardBean board : boardList) {
				if (board.getName().equals(name) && board.getComment().matches(".*" + comment + ".*"))
					boardSearchResultList.add(board);
			}
		}

		return boardSearchResultList;
	}
}
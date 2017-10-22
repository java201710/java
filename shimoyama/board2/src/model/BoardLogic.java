package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardLogic {

	private final String ADMIN_PASS = "root00";

	public ArrayList<String> add(BoardBean board) {

		ArrayList<String> message = new ArrayList<String>();

		board.setId(board.getId() + 1);
		if (board.getName().equals("")) {
			board.setName("ゲスト");
		}
		if (board.getEmail().equals("")) {
			board.setEmail("なし");
		}
		if (board.getComment().equals("")) {
			message.add("コメントを入力してください。");
		} else {
			message.add("投稿しました。");
		}
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		board.setDateTime(f.format(now));

		return message;
	}

	public ArrayList<String> admin(String password) {

		ArrayList<String> message = new ArrayList<String>();

		if (password.equals(ADMIN_PASS)) {
			message.add("");
		} else {
			message.add("パスワードが違います。");
		}

		return message;
	}

	public ArrayList<String> del(String delId, ArrayList<BoardBean> boardList) {

		ArrayList<String> message = new ArrayList<String>();
		message.add("IDが違います！存在するIDを半角数字で入力して下さい。");

		try {
			int delNo = Integer.parseInt(delId);

			for (int i = 0; i < boardList.size(); i++) {
				if (boardList.get(i).getId() == delNo) {
					boardList.remove(i);
					message.set(0, "投稿を削除しました。");
					break;
				}
			}
		} catch (NumberFormatException e) {
			//数字以外が入力された場合
		}

		return message;
	}

	public String show(ArrayList<BoardBean> boardList) {
		Common c =new Common();

		String showList = "";
		if (boardList != null) {
			for (int i = boardList.size() - 1; i >= 0; i--) {

				showList += "No." + boardList.get(i).getId() + ": " + c.sanitizing(boardList.get(i).getName());

				showList += "　　　E-mail: " + c.sanitizing(boardList.get(i).getEmail()) + "<br>";
				showList += "<Div Align=\"right\">投稿日時: " + boardList.get(i).getDateTime() + "</Div><br>";
				showList += "コメント: " + c.sanitizing(boardList.get(i).getComment().replaceAll("\n", "<br>")) + "<br><hr>";
			}
		}
		return showList;

	}

	public String selectBox(ArrayList<BoardBean> boardList) {

		String showList = "<select name=\"delid\">";
		if (boardList != null) {
			for (int i = boardList.size() - 1; i >= 0; i--) {
				showList += "<option value=\"" + boardList.get(i).getId() +"\">" + boardList.get(i).getId() + "</option>";
			}
			showList +="</select>";
		}

		return showList;

	}
}
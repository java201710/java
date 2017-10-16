package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardLogic {

	// 変数の宣言と初期値(Set variables)。
	final String adminPass = "root00";
	ArrayList<String> msg = new ArrayList<String>();

	//コンストラクタ(Constructors)
	public BoardLogic() {
	}

	//以下のメソッドは引数の値を確認する(The following methods confirm the contents of the variable passed)。
	//Update 17/10/16 Put the date time in here.
	public ArrayList<String> add(BoardBean bBean) {
		if (bBean.getName().equals("")) {
			bBean.setName("ゲスト");
		}
		if (bBean.getEmail().equals("")) {
			bBean.setEmail("なし");
		}


		if (!bBean.getComment().equals("")) {
			bBean.setComment(Common.sanitizing(bBean.getComment()));
			String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
			bBean.setDateTime(dateTime);
			msg.add("投稿しました。");
		} else {
			msg.add("コメントを入力してください。");
		}
		return msg;
	}

	public ArrayList<String> admin(String adminPass) {
		if (!adminPass.equals(this.adminPass)) {
			msg.add("パスワードが違います。");
		} else {
			msg.add("");
		}
		return msg;
	}

	public ArrayList<String> del(String adminId, ArrayList<BoardBean> userList) {
		if (!(adminId == null)) {
			if (!adminId.equals("")) {
				boolean match = false;

				//「userList」には「adminId」を探す。あるなら、同じ値持ってるオブジェクトを「userList」から消す
				//	(Search 'userList' for 'adminId'. If a match is found, remove the object from 'userList')。
				for (int i = 0; i < userList.size(); i++) {
					if (userList.get(i).getId() == Integer.parseInt(adminId)) {
						userList.remove(i);
						i = userList.size();
						match = true;
					}
				}

				if (match) {
					msg.add("投稿を削除しました。");
				} else {
					msg.add("IDが違います！存在するIDを半角数字で入力して下さい。");
				}
			}
		} else {
			msg.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}
		return msg;
	}

	//New methods as of 2017/10/16
	public String show(ArrayList<BoardBean> boardList) {
		StringBuilder output = new StringBuilder();
		String editedOutput;

		ArrayList<BoardBean> reverseList = new ArrayList<BoardBean>();
		for (int i = boardList.size()-1;i > -1; i--) {
			reverseList.add(boardList.get(i));
		}

		output.append("<table width=100% border=\"0\">");
		for (BoardBean bBean : reverseList) {
			output.append("<tr><td>");
			output.append("<table width=100% border=\"0\">");

			output.append("<tr><td width=1%  nowrap=\"nowrap\">No." + bBean.getId() + "：</td><td>" + bBean.getName() + "</td></tr>");
			output.append("<tr><td width=1%  nowrap=\"nowrap\">E-Mail：</td><td>" + bBean.getEmail() + "</td></tr>");
			output.append("<tr><td colspan=2 align=\"right\">投稿日時：" + bBean.getDateTime() + " </td></tr>");
			output.append("<tr><td width=1%  nowrap=\"nowrap\" valign=\"top\">コメント：</td><td>" + bBean.getComment() + "</td></tr>");
			output.append("</table>");
			output.append("<hr>");
		}
		output.append("</td></tr>");
		output.append("</table>");

		editedOutput = output.toString();
		return editedOutput.replace("\n", "<br>");
	}

	public String selectBox(ArrayList<BoardBean> boardList) {
		StringBuilder output = new StringBuilder();

		output.append("<select name=\"delid\">");
		for (BoardBean bBean : boardList) {
			output.append("<option value = " + bBean.getId() + ">No." + bBean.getId() + "</option>");
		}
		output.append("</select>");
		return output.toString();
	}

	public ArrayList<BoardBean> search(String name, String comment, ArrayList<BoardBean> boardList) {
		if (name.equals("") && comment.equals("")) {
			return boardList;
		} else {
			ArrayList<BoardBean> searchList = new ArrayList<BoardBean>();

			if (comment.equals("")) {
				for (BoardBean bBean : boardList) {
					if (bBean.getName().equals(name)) {
						searchList.add(bBean);
					}
				}
			} else {
				for (BoardBean bBean : boardList) {
					if (bBean.getComment().indexOf(comment) != -1) {
						searchList.add(bBean);
					}
				}
			}
			return searchList;
		}
	}

}

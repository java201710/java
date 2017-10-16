package model;

import java.util.ArrayList;

public class BoardLogic {

	// 変数の宣言と初期値(Set variables)。
	final String adminPass = "root00";
	ArrayList<String> msg = new ArrayList<String>();

	//コンストラクタ(Constructors)
	public BoardLogic() {
	}

	//以下のメソッドは引数の値を確認する(The following methods confirm the contents of the variable passed)。
	public ArrayList<String> add(BoardBean bBean) {
		if (!bBean.getComment().equals("")) {
			msg.add("投稿しました。");
		} else {
			msg.add("コメントを入力してください。");
		}
		return msg;
	}

	public ArrayList<String> admin(String adminPass) {
		if (!adminPass.equals(this.adminPass)) {
			msg.add("パスワードが違います。");
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

		output.append("<table width=100% border=\"0\">");
		for (BoardBean bBean : boardList) {
			output.append("<tr><td>");
			output.append("<table width=100% border=\"0\">");

			output.append("<tr><td>No." + bBean.getId() + "：" + bBean.getName() + "</td></tr>");
			output.append("<tr><td>E-Mail：" + bBean.getEmail() + "</td></tr>");
			output.append("<tr><td></td><td align=\"right\">投稿日時：" + bBean.getDateTime() + " </td></tr>");
			output.append("<tr><td>コメント：</td><td>" + bBean.getComment() + "</td></tr>");
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

}

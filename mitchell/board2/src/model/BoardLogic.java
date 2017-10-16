package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardLogic {

	// 変数の宣言と初期値(Set variables)。
	final String adminPass = "root00";
	ArrayList<String> msg = new ArrayList<String>();

	// コンストラクタ(Constructors)
	public BoardLogic() {
	}

	// 「BoardBean」オブジェクトの内容を確認する(The 'add' method will check the contents of the BoadBean object)。 
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

	// 「adminPass」を確認する(The 'admin' method checks the entered string with this.adminPass)。
	public ArrayList<String> admin(String adminPass) {
		if (!adminPass.equals(this.adminPass)) {
			msg.add("パスワードが違います。");
		} else {
			msg.add("");
		}
		return msg;
	}

	// 「ArrayList<BoardBean>」引数の中に「adminId」引数を探す。存在があれば、「ArrayList<BoardBean>」引数から消す
	//		(Search the ArrayList<BoardBean> for adminId. If found, then remove it from the ArrayList passed)。
	public ArrayList<String> del(String adminId, ArrayList<BoardBean> userList) {
		if (!(adminId == null)) {
			boolean match = false;

			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getId() == Integer.parseInt(adminId)) {
					userList.remove(i);
					i = userList.size();
					match = true;
				}
			}

			if (match) {
				msg.add("投稿を削除しました。");
			} 
		} 
		
		if (msg.size() == 0) {
			msg.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}
		
		return msg;
	}

	//「ArrayList<BoardBean>」を使って、画面に示す表示を作る(Use the ArrayList<BoardBean> passes and create a view to be shown on the page)。
	public String show(ArrayList<BoardBean> boardList) {
		StringBuilder output = new StringBuilder();
		
		output.append("<table width=100% border=\"0\">");
		for (int i = boardList.size() - 1; i > -1; i--) {
			output.append("<tr><td>");
			output.append("<table width=100% border=\"0\">");
			output.append("<tr><td width=1%  nowrap=\"nowrap\">No." + boardList.get(i).getId() + "：</td><td>" + 
					boardList.get(i).getName() + "</td></tr>");
			output.append("<tr><td width=1%  nowrap=\"nowrap\">E-Mail：</td><td>" + boardList.get(i).getEmail() + "</td></tr>");
			output.append("<tr><td colspan=2 align=\"right\">投稿日時：" + boardList.get(i).getDateTime() + " </td></tr>");
			output.append("<tr><td width=1%  nowrap=\"nowrap\" valign=\"top\">コメント：</td><td>" + 
					boardList.get(i).getComment() + "</td></tr>");
			output.append("</table>");
			output.append("<hr>");
		}
		output.append("</td></tr>");
		output.append("</table>");

		return output.toString();
	}

	//掲示板管理の画面にあるSelectの選択肢を作る(Populate the the select field, on the bulletin board control window)。
	public String selectBox(ArrayList<BoardBean> boardList) {
		StringBuilder output = new StringBuilder();

		output.append("<select name=\"delid\">");
		for (BoardBean bBean : boardList) {
			output.append("<option value = " + bBean.getId() + ">No." + bBean.getId() + "</option>");
		}
		output.append("</select>");
		return output.toString();
	}

	//「ArrayList<BoardBean>」の中にnameやcommentを探す。存在があれば、別のArrayList<BoardBean>に入れる(Search the ArrayList<BoardBean>
	//		for either name or comment or both. Any matches found will be copied into a new ArrayList<BoardBean>)。
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

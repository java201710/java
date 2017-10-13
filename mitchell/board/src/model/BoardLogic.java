package model;

import java.util.ArrayList;

public class BoardLogic {
	final String adminPass = "root00";
	ArrayList<String> msg = new ArrayList<String>();

	public BoardLogic() {
	}

	public ArrayList<String> add(BoardBean bBean) {
		//I do not check the fields of the bean because I assume they were created correctly... We knew what they should be if empty...
		if (bBean.getComment().equals("")) {
			msg.add("コメントを入力してください。");
			return msg;
		} else {
			msg.add("投稿しました。");
			return msg;
		}
	}

	//Return an empty ArrayList<String> if the password is correct.
	public ArrayList<String> admin (String adminPass) {
		if (!adminPass.equals(this.adminPass)) {
			msg.add("パスワードが違います。");
			return msg;
		} else {
			return new ArrayList<String>();
		}
	}

	public ArrayList<String> del (String adminId, ArrayList<BoardBean> userList) {
		if (adminId.equals("")) {
			msg.add("IDが違います！存在するIDを半角数字で入力して下さい。");
			System.out.println("here1");
			return msg;
		} else {
			boolean match = false;
			for (int i = 0; i < userList.size(); i++) {
				if (userList.get(i).getId() == Integer.parseInt(adminId)) {
					userList.remove(i);
					i = userList.size();
					match = true;
					System.out.println("here2");
				}
			}

			if (match) {
				msg.add("投稿を削除しました。");
			} else {
				msg.add("IDが違います！存在するIDを半角数字で入力して下さい。");
			}
			return msg;
		}
	}
}

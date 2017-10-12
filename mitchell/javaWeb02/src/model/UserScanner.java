package model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserScanner {
	public ArrayList<String> checkInput(User u) {
		ArrayList<String> msgList = new ArrayList<String>();
		Pattern pat = Pattern.compile("[^A-Za-z0-9]");
		Matcher match;

		if (u.getId().equals("")) {
			msgList.add("[エラー]ＩＤは未入力です。");
		}
		if (u.getId().equals("")) {
			msgList.add("[エラー]パスワードは未入力です。");
		}
		if (u.getId().equals("")) {
			msgList.add("[エラー]名前は未入力です。");
		} else {
			match = pat.matcher(u.getPass());
			//TODO: Check for small, big, and number;
			if (!((match.find()) && (u.getPass().length() > 5))) {
				msgList.add("[エラー]正しいパスワードを入力してください。");
			}
		}
		return msgList;
	}
}

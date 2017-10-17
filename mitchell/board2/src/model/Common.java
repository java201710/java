package model;

public class Common {
	// 文字列のなかに特別なタッグがあれば、普通の文字に入れ替える
	//	(This method is used to remove special tags from strings and replace characters.)。
	public static String sanitizing(String checkString) {
		if (!(checkString == null)) {
			checkString = checkString.replace("&", "&amp;");
			checkString = checkString.replace("<", "&lt;");
			checkString = checkString.replace(">", "&gt;");
			checkString = checkString.replace("\"", "&quot;");
			checkString = checkString.replace("\'", "&#39;");
			
			checkString = checkString.replace("\n", "<br>");
		}
		return checkString;
	}
}

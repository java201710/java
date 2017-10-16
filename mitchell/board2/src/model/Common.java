package model;

public class Common {
	public static String sanitizing(String checkString) {

		if (checkString == null) {
			return checkString;
		} else {
			checkString = checkString.replace("&", "&amp;");
			checkString = checkString.replace("<", "&lt;");
			checkString = checkString.replace(">", "&gt;");
			checkString = checkString.replace("\"", "&quot;");
			checkString = checkString.replace("\'", "&#39;");
			return checkString;
		}
	}

}

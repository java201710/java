package registerUser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserScanner {
	public ArrayList<String> checkUser(User registerUser, ArrayList<User> registeredUL) {
		Pattern upperCaseCharacter = Pattern.compile("[\\p{Upper}]");
		Pattern lowerCaseCharacter = Pattern.compile("[\\p{Lower}]");
		Pattern specialCharacter = Pattern.compile("[\\p{Punct}]");
		Pattern digit = Pattern.compile("[\\p{Digit}]");
		Matcher m;

		ArrayList<String> msg = new ArrayList<String>();

		if (registerUser.getID().equals("")) {
			msg.add("[Error] The 'ID' field is blank.");
		} else {
			for (User u : registeredUL) {
				if (registerUser.getID().equals(u.getID())) {
					msg.add("[Error] The 'ID' entered, " + registerUser.getID() + ", is already taken.");
					break;
				}
			}
		}
		if (registerUser.getPass().equals("")) {
			msg.add("[Error] The 'Password' field is blank.");
		} else {
			if (registerUser.getPass().length() < 4) {
				msg.add("[Error] The 'Password' needs to be 4 characters or more.");
			}
			m = upperCaseCharacter.matcher(registerUser.getPass());
			if (!m.find()) {
				msg.add("[Error] The 'Password' field is missing an upper case character.");
			}
			m = lowerCaseCharacter.matcher(registerUser.getPass());
			if (!m.find()) {
				msg.add("[Error] The 'Password' field is missing a lower case character.");
			}
			m = digit.matcher(registerUser.getPass());
			if (!m.find()) {
				msg.add("[Error] The 'Password' field is missing a digit.");
			}
			m = specialCharacter.matcher(registerUser.getPass());
			if (!m.find()) {
				msg.add("[Error] The 'Password' field is missing a special character.");
			}
		}
		if (registerUser.getName().equals("")) {
			msg.add("[Error] The 'Name' field is blank.");
		}

		return msg;
	}

}

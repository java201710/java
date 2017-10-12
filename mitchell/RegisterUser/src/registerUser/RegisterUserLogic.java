package registerUser;

public class RegisterUserLogic {
	public User makeUser(String id, String pass, String name) {
		User newUser = new User(id, pass, name);
		return newUser;
	}
}

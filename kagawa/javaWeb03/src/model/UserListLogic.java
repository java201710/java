package model;

public class UserListLogic {
	public User add(String id, String name, String pass) {

		User user = new User(id, name, pass);

		return user;
	}
}
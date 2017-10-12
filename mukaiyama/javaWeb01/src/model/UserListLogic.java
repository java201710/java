package model;

public class UserListLogic {
  public User add(String id, String name, String pass) {
	  User addUser = new User(id, name, pass);
    return addUser;
  }
}
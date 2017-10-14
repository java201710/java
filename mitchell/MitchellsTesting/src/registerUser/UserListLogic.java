package registerUser;


public class UserListLogic {
  public User add(String id, String name, String pass) {

	  User u = new User(id, name, pass);

    return u;
  }
}
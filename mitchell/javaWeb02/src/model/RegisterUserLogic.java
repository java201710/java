package model;

public class RegisterUserLogic {
  public boolean execute(User user) {

//    Mitchell's Testing
	  System.out.println("ユーザーのID：" + user.getId() + "/nユーザーのパスワード：（秘密」） /nユーザー名： " + user.getName() );
    return true;
  }
}
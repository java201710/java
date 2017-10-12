package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.RegisterUserLogic;
import model.User;
import model.UserListLogic;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    // フォワード先
    String forwardPath = null;

    // サーブレットクラスの動作を決定する「action」の値を
    // リクエストパラメータから取得
    String action = request.getParameter("action");

    // 「登録の開始」をリクエストされたときの処理
    if (action == null) {
      // フォワード先を設定
      forwardPath = "/WEB-INF/jsp/registerForm.jsp";
    }

    // 登録確認画面から「登録実行」をリクエストされたときの処理
    else if (action.equals("done")) {
      // セッションスコープに保存された登録ユーザ
      HttpSession session = request.getSession();
      User registerUser = (User) session.getAttribute("registerUser");

      // 登録処理の呼び出し
      RegisterUserLogic logic = new RegisterUserLogic();
      logic.execute(registerUser);

//<<20171012 ADD START>>

      //アプリケーションスコープに保存されているリストを取得
      ServletContext application = this.getServletContext();
      ArrayList<User> userList = (ArrayList<User>)application.getAttribute("userList");

      //スコープの初回取得時
      if(userList==null){
    	  userList = new ArrayList<User>();
      }

      //取得したリストに登録するユーザーを追加
      if(registerUser != null){
    	  userList.add(registerUser);
      }

      //アプリケーションスコープにリストを保存
      application.setAttribute("userList", userList);

//<<20171012 ADD END>>

      // 不要となったセッションスコープ内のインスタンスを削除
      session.removeAttribute("registerUser");
//<<20171012 ADD START>>
      session.removeAttribute("errList");
//<<20171012 ADD END>>

      // 登録後のフォワード先を設定
      forwardPath = "/WEB-INF/jsp/registerDone.jsp";
    }

    // 設定されたフォワード先にフォワード
    RequestDispatcher dispatcher =
        request.getRequestDispatcher(forwardPath);
    dispatcher.forward(request, response);
  }

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    // リクエストパラメータの取得
    request.setCharacterEncoding("UTF-8");
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
//<<20171012 ADD START>>
    ArrayList<String> errList = new ArrayList<String>();	//エラーリスト（セッションスコープに保存）
//<<20171012 ADD END>>

    // 登録するユーザーの情報を設定
//<<20171012 UPDATE START>>
//    User registerUser = new User(id, name, pass);
    UserListLogic userListLogic = new UserListLogic();
    User registerUser = userListLogic.add(id, name, pass);
//<<20171012 UPDATE END>>

//<<20171012 ADD START>>
    //未入力チェック
    if(id==null || id.length()==0){
    	errList.add("【エラー】ログインIDが未入力です");
    }
    if(name==null || name.length()==0){
    	errList.add("【エラー】名前が未入力です");
    }
    if(pass==null || pass.length()==0){
    	errList.add("【エラー】パスワードが未入力です");
    }

    //キー重複チェック
    //アプリケーションスコープに保存されているリストを取得
    ServletContext application = this.getServletContext();
	ArrayList<User> userList = (ArrayList<User>)application.getAttribute("userList");

	//スコープの初回取得時
	if(userList==null){
		userList = new ArrayList<User>();
	}

	//スコープに保存されたリストとの比較
	for(User appUser:userList){
		if(appUser.getId().equals(id)){
			errList.add("【エラー】同じログインIDが既に登録されています");
		}
	}
//<<20171012 ADD END>>

    // セッションスコープに登録ユーザーを保存
    HttpSession session = request.getSession();
    session.setAttribute("registerUser", registerUser);
//<<20171012 ADD START>>
    // セッションスコープにエラーリストを保存
    session.setAttribute("errList", errList);
//<<20171012 ADD END>>
    // フォワード
    RequestDispatcher dispatcher =
        request.getRequestDispatcher
            ("/WEB-INF/jsp/registerConfirm.jsp");
    dispatcher.forward(request, response);
  }
}
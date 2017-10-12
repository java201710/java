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
@SuppressWarnings("unchecked")
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

			// アプリケーションコープに登録ユーザーを保存
			ServletContext application = this.getServletContext();
			// 既存のuserListを取得
			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
			//もし既存のuserListがなければ作成
			if (userList == null)
				userList = new ArrayList<User>();
			//登録ユーザがあればuserListに追加
			if (registerUser != null)
				userList.add(registerUser);

			//登録ユーザを追加したuserListをアプリケーションスコープに保存
			application.setAttribute("userList", userList);

			// 不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");
			// 以下もいるかも
			session.removeAttribute("isIDDuplication");

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

		// フォワード先
		String forwardPath = "/WEB-INF/jsp/registerConfirm.jsp";
		// 登録するログインIDが既存のIDと重複しているかどうか
		Boolean isIDDuplication = false;

		if (id.equals("") || name.equals("") || pass.equals(""))
			forwardPath = "/WEB-INF/jsp/registerError.jsp";
		else {
			// アプリケーションスコープを作成
			ServletContext application = this.getServletContext();
			// 既存のuserListを取得
			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
			if (userList != null) {
				// 既存のuserListのIDと同じであればエラー画面を返す
				for (User user : userList) {
					if (id.equals(user.getId())) {
						forwardPath = "/WEB-INF/jsp/registerError.jsp";
						isIDDuplication = true;
						break;
					}
				}
			}
		}

		// 登録するユーザーの情報を設定
		//		User registerUser = new User(id, name, pass);
		UserListLogic logic = new UserListLogic();
		User registerUser = logic.add(id, name, pass);

		// セッションスコープに登録ユーザーとエラー種類を保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);
		session.setAttribute("isIDDuplication", isIDDuplication);

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
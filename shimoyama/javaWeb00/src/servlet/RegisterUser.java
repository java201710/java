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

			// applicationスコープに登録ユーザーを保存
			// アプリケーションスコープに保存されたサイト評価を取得
			ServletContext application = this.getServletContext();
			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
			if (userList == null) {
				userList = new ArrayList<User>();
			}

			//ArrayListにregisterUserを追加
			if (registerUser != null) {
				userList.add(registerUser);
			}

			// applicationスコープに登録ユーザーを保存
			application.setAttribute("userList", userList);

			// 不要となったセッションスコープ内のインスタンスを削除
			session.removeAttribute("registerUser");

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
		
		String err = "";
		if (id.equals("")) {
			err = "id";
		}
		if (name.equals("")) {
			err = "name";
		}
		if (id.equals("")) {
			err = "pass";
		}

		// applicationスコープに登録ユーザーを保存
		// アプリケーションスコープに保存されたサイト評価を取得
		ServletContext application = this.getServletContext();
		ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
		if (userList == null) {
			userList = new ArrayList<User>();
		}

		//重複チェック
		for (User u : userList) {
			if (u.getId().equals(id)) {
				err = "ID";
			}
		}

		// 登録するユーザーの情報をUserListLogicを使って設定
		UserListLogic logic = new UserListLogic();
		User registerUser;
			registerUser = logic.add(id, name, pass);

		// セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);
		session.setAttribute("err", err);

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher
						("/WEB-INF/jsp/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
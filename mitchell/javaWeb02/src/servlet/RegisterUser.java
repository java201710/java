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
import model.UserScanner;
//import model.RegisterUserLogic;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		boolean userExists;

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
			ServletContext application = this.getServletContext();
			HttpSession session = request.getSession();

			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
			User registerUser = (User) session.getAttribute("registerUser");

			userExists = false;
			if (userList == null) {
				userList = new ArrayList<User>();
				userList.add(registerUser);
			} else {
				for (User u : userList) {
					if (registerUser.getId().equals(u.getId())) {
						userExists = true;
					}
				}
				if (!userExists) {
					userList.add(registerUser);
				}

			}

			if(userExists) {
				request.setAttribute("id", registerUser.getId());
				forwardPath = "/WEB-INF/jsp/registerFail.jsp";
			} else {
				forwardPath = "/WEB-INF/jsp/newRegisterDone.jsp";
			}

			application.setAttribute("userList", userList);

		}

		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String forwardPath = null;
		//int registerResult = 0;

		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");

		// 登録するユーザーの情報を設定
		UserListLogic logic = new UserListLogic();
		User registerUser = logic.add(id, name, pass);

		UserScanner userScanner = new UserScanner();
		if (userScanner.checkInput(registerUser).size) > 0) {
			forwardPath = "/WEB-INF/jsp/registerConfirm.jsp";
		} else {
			request.setAttribute("result", registerResult);
			forwardPath = "/WEB-INF/jsp/registerFail.jsp";
		}

		// セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);

		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
package registerUserServlet;

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

import registerUser.User;
import registerUser.UserListLogic;
//import model.RegisterUserLogic;

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
			forwardPath = "/WEB-INF/RegisterUser/registerForm.jsp";
		}

		// 登録確認画面から「登録実行」をリクエストされたときの処理
		else if (action.equals("done")) {
			ServletContext application = this.getServletContext();

			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
			if (userList == null) {
				userList = new ArrayList<User>();
			}
			application.setAttribute("userList", userList);

			HttpSession session = request.getSession();

			//This needs to be changed if I do not create the user in "doPost"
			/*
			User registerUser = (User) session.getAttribute("registerUser");
			userList.add(registerUser);
			/*
			 * Testing
			 */
			UserListLogic logic = new UserListLogic();
			userList.add(logic.add((String) session.getAttribute("id"), (String) session.getAttribute("name"), (String) session.getAttribute("pass")));
			/*
			 * End Testing
			 */


			forwardPath = "/WEB-INF/RegisterUser/registerDone.jsp";
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

		// 登録するユーザーの情報を設定

		//What if Ⅰ dont want to call the logic...
		/*
		UserListLogic logic = new UserListLogic();
		User registerUser = logic.add(id, name, pass);

		// セッションスコープに登録ユーザーを保存
		HttpSession session = request.getSession();
		session.setAttribute("registerUser", registerUser);
		/*
		 * Testing
		 */
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("pass", pass);

		/*
		 * End Testing
		 */

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher
						("/WEB-INF/RegisterUser/registerConfirm.jsp");
		dispatcher.forward(request, response);
	}
}
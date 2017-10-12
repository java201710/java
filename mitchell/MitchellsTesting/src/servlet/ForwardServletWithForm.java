package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Page 174
@WebServlet("/ForwardServletWithForm")
public class ForwardServletWithForm extends HttpServlet {
	private static final long serialVersion = 1L;

	//Original Example
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/forward.jsp");
		dispatcher.forward(request, response);
	}
	*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

//		if (action == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formInput.jsp");
			dispatcher.forward(request, response);
//		} else {
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formInput.jsp");
//			dispatcher.forward(request, response);
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");

		String errorMsg = "";
		if(name == null || name.length() == 0) {
			errorMsg += "名前が入力されていません<br>";
		}
		if(gender == null || gender.length() == 0) {
			errorMsg += "性別が選択されていません<br>";
		} else {
			if (gender.equals("0")) {
				gender = "男性";
			} else if (gender.equals("1")) {
				gender = "女性";
			}
		}

		//TODO: Test what happens if name is null and I try to use it
		String msg = "ミッチェルのテスト：" + name + "さん（" + gender + ") を登録しました";
		if (errorMsg.length() != 0) {
			msg = errorMsg;
		}

		//This is the new section,  which is setting the variables in the jsp
		request.setAttribute("msg", msg);


		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/forwardResults.jsp");
		dispatcher.forward(request, response);
	}

}

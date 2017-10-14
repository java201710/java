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

import model.BoardBean;
import model.BoardLogic;

@WebServlet("/BoardDo")
public class BoardDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		// フォワード先
		String forwardPath = "/WEB-INF/jsp/boardMain.jsp";

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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String comment = request.getParameter("comment");
		String action = request.getParameter("action");
		String adminPass = request.getParameter("adminpass");
		String delId = request.getParameter("delid");
		
		// デフォルトのフォワード先
		String forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		if (action == null) {
			action = "";

			// 登録確認画面から「登録実行」をリクエストされたときの処理
		} else if (action.equals("add")) {

			// アプリケーションスコープを取得
			ServletContext application = this.getServletContext();
			ArrayList<BoardBean> userList = (ArrayList<BoardBean>) application.getAttribute("boardList");
			if (userList == null) {
				userList = new ArrayList<BoardBean>();
			}

			Integer finalId = (Integer) application.getAttribute("finalId");
			if (finalId == null) {
				finalId = 0;
			}

			BoardBean boardBean = new BoardBean(finalId,name,email,comment,"");
	
			BoardLogic boardLogic = new BoardLogic();
			ArrayList<String> message = boardLogic.add(boardBean);

			if (message.get(0).equals("投稿しました。")) {
				userList.add(boardBean);
				application.setAttribute("boardList", userList);
				application.setAttribute("finalId", ++finalId);
			}
			
			request.setAttribute("message", message);
			
			
			// 登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		} else if (action.equals("admin")) {
			
			BoardLogic boardLogic = new BoardLogic();
			ArrayList<String> message = boardLogic.admin(adminPass);

			if (message.get(0).equals("")) {
				// パスワード一致のフォワード先を設定
				forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
			}
			
			request.setAttribute("message", message);
			
		} else if (action.equals("del")) {
			
			ServletContext application = this.getServletContext();
			ArrayList<BoardBean> userList = (ArrayList<BoardBean>) application.getAttribute("boardList");
			if (userList == null) {
				userList = new ArrayList<BoardBean>();
			}

			BoardLogic boardLogic = new BoardLogic();
			ArrayList<String> message = boardLogic.del(delId,userList);

			request.setAttribute("message", message);

			// 登録後のフォワード先を設定
			forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
		}


		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher
						(forwardPath);
		dispatcher.forward(request, response);
	}
}
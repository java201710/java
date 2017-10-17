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

@SuppressWarnings("unchecked")
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

		ArrayList<String> messageList = new ArrayList<String>(); // メッセージを格納

		// サーブレットクラスの動作を決定する「action」の値を
		// リクエストパラメータから取得
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		// フォワード先
		String forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		// ロジッククラスのインスタンス作成
		BoardLogic logic = new BoardLogic();

		// アプリケーションスコープを作成
		ServletContext application = this.getServletContext();

		if (action.equals("add")) {
			// リクエストパラメータの取得
			//		int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");
			//		String dateTime = request.getParameter("dateTime");

			// 既存の投稿最終Noを取得
			Integer finalId = (Integer) application.getAttribute("finalId");
			// 最初の投稿なら投稿最終Noは0
			if (finalId == null)
				finalId = 0;
			// 投稿Noを決定
			int id = finalId + 1;
			// BoardBeanインスタンス作成
			BoardBean board = new BoardBean(id, name, email, comment, "");
			// ロジッククラスのメソッドを実行
			messageList = logic.add(board);
			if (messageList.get(0).equals("投稿しました。")) {
				//既存のboarderListを取得
				ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
				if (boardList == null)
					boardList = new ArrayList<BoardBean>();
				// boardListに追加
				boardList.add(board);
				// アプリケーションスコープに保存
				application.setAttribute("boardList", boardList);
				application.setAttribute("finalId", id);
			}
		}
		else if (action.equals("admin")) {
			// リクエストパラメータの取得
			String password = request.getParameter("adminpass");

			// ロジッククラスのメソッドを実行
			messageList = logic.admin(password);
			if (messageList.get(0).equals("")) {
				forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
			}
		}
		else if (action.equals("del")) {
			// リクエストパラメータの取得
			String delid = request.getParameter("delid");
			if (delid == null)
				delid = "";
			// 既存のboarderListを取得
			ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
			if (boardList == null)
				boardList = new ArrayList<BoardBean>();

			// ロジッククラスのメソッドを実行
			messageList = logic.del(delid, boardList);
			if (messageList.get(0).equals("投稿を削除しました。")) {
				// 削除する投稿
				BoardBean delBoard = new BoardBean();
				for (BoardBean board : boardList) {
					if (String.valueOf(board.getId()).equals(delid)) {
						delBoard = board;
						break;
					}
				}
				// boardListから削除
				boardList.remove(delBoard);
				// アプリケーションスコープに保存
				application.setAttribute("boardList", boardList);
			}

			forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
		}
		else if (action.equals("search")) {
			// リクエストパラメータの取得
			String name = request.getParameter("name");
			String comment = request.getParameter("comment");

			// リクエストスコープに保存
			request.setAttribute("name", name);
			request.setAttribute("comment", comment);

			//messageList.add("");
		}

		// リクエストスコープに保存
		request.setAttribute("message", messageList);

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}
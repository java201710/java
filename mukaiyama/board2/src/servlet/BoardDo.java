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

		//<<初回表示or掲示板管理からの戻り時>>
		// フォワード先
		String forwardPath = null;
		// フォワード先を設定
		forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		//アプリケーションスコープに保存されているリストを取得
		ServletContext application = this.getServletContext();
		ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
		if (boardList == null) {
			boardList = new ArrayList<BoardBean>();
		}
		// 設定されたフォワード先にフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// サーブレットクラスの動作を決定する「action」の値を
		// リクエストパラメータから取得
		String action = request.getParameter("action");
		ArrayList<String> message = new ArrayList<String>(); //エラーリスト（リクエストスコープに保存）

		// フォワード先
		String forwardPath = null;

		//ロジックのインスタンス
		BoardLogic boardLogic = new BoardLogic();

		if (action.equals("add")) {
			//<<新規投稿ボタン>>
			int finalId = 0;

			// リクエストパラメータの取得
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");

			//アプリケーションスコープに保存されている投稿リストを取得
			ServletContext application = this.getServletContext();
			ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
			if (boardList == null) {
				//スコープ存在しない場合（初回時）
				boardList = new ArrayList<BoardBean>();
			}
			//アプリケーションスコープに保存されている最終投稿№を取得
			if(application.getAttribute("finalId")==null){
				//スコープ存在しない場合（初回時）
				finalId = 0;
			}else{
				finalId = (Integer) application.getAttribute("finalId");
			}

			//最終投稿№の更新
			finalId++;

			//投稿情報の作成
			BoardBean boardBean = new BoardBean(finalId, name, email, comment, "");

			//投稿情報のチェック処理・編集処理
			message = boardLogic.add(boardBean);

			//チェック処理・編集処理が正常終了した場合
			if (message.get(0).equals("投稿しました。")) {
				//投稿情報を投稿リストに追加
				boardList.add(boardBean);

				//アプリケーションスコープに更新した情報を保存
				application.setAttribute("finalId", finalId);
				application.setAttribute("boardList", boardList);
			}
			// フォワード先を設定
			forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		} else if (action.equals("admin")) {
			//送信ボタン（掲示板メイン）用
			// リクエストパラメータの取得
			String adminpass = request.getParameter("adminpass");

			//投稿情報のチェック処理・編集処理
			message = boardLogic.admin(adminpass);

			//チェック処理・編集処理が正常終了した場合／エラーの場合
			if (message.get(0).equals("")) {
				//【正常】
				// フォワード先を設定→掲示板管理へ
				forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
			} else {
				//【エラー】
				// フォワード先を設定→掲示板メインへ
				forwardPath = "/WEB-INF/jsp/boardMain.jsp";
			}

		} else if (action.equals("del")) {
			//送信ボタン（掲示板管理）用
			// リクエストパラメータの取得
			String delid = request.getParameter("delid");

			//アプリケーションスコープに保存されている情報を取得
			ServletContext application = this.getServletContext();
			ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList"); //投稿リスト
			if (boardList == null) {
				boardList = new ArrayList<BoardBean>();
			}

			//投稿情報のチェック処理・編集処理
			message = boardLogic.del(delid, boardList);

			//チェック処理・編集処理が正常終了した場合
			if (message.get(0).equals("投稿を削除しました。")) {
				int intDelId = Integer.parseInt(delid);
				//対象の投稿を投稿リストから削除する
				for (int i = 0; i < boardList.size(); i++) {
					if (boardList.get(i).getId() == intDelId) {
						boardList.remove(i);
						application.setAttribute("boardList", boardList);
						break;
					}
				}
			}
			// フォワード先を設定
			forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";

		} else {

		}

		//メッセージをリクエストスコープへ保存
		request.setAttribute("message", message);

		// フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);

	}
}
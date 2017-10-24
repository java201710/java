package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardLogic;
import model.Common;

@WebServlet("/BoardDo")
public class BoardDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 掲示板画面のアドレスを渡す(Pass the address to the main page)。
		request.setAttribute("message", request.getSession().getAttribute("msg"));
		request.getSession().removeAttribute("msg");

		if (request.getSession().getAttribute("paramArrayList") != null) {
			ArrayList<String> test = (ArrayList<String>) request.getSession().getAttribute("paramArrayList");
			for (String s : test) {
				request.setAttribute(s, request.getSession().getAttribute(s));
			}
		}

		request.getRequestDispatcher("/WEB-INF/jsp/boardMain.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 変数の宣言と初期値(Set variables)。
		BoardLogic bLogic = new BoardLogic();
		ArrayList<String> msg = null;
		String forwardPath = null;

		/*
		 * クリックしたボタンに応じて以下のステップを行う。 ２０１７年１０月１６日までに、４つのボタンがあて、各の値は：
		 * 	(Identify which button was pressed. As of 2017/10/16, 4 buttons total with action values:)
		 * 		"add" → 新しオブジェクトを作って、アプリケーションスコープの「boardList」にオブジェクトを入れる(Create a new member in application
		 * 			scope 'boardList')。
		 * 		"admin" → 掲示板管理画面のアドレスを渡す(Transfer to admin page)。
		 * 		"del" → アプリケーションスコープの「boardList」から一つのオブジェクトを消す(Remove member from application scope 'boardList')。
		 * 		"search" → リクエストスコープに「name」と「comment」を設定して、掲示板メインの画面を示す(Re-open page with requestscope variables
		 * 			'name' and 'comment')。
		 */
		if (request.getParameter("action").equals("add")) {
			// 変数の宣言と初期値(Set variables)。
			String name = request.getParameter("name"), email = request.getParameter("email"), comment = request
					.getParameter("comment");
			BoardBean bBean;

			bBean = new BoardBean(0, name, email, comment, "");
			msg = bLogic.add(bBean);

		} else if (request.getParameter("action").equals("admin")) {
			// 変数の宣言と初期値(Set variables)。
			String adminPass = request.getParameter("adminpass");

			msg = bLogic.admin(adminPass);
			if (msg.get(0).equals("")) {
				// 成功の場合(If successful)。
				forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
			} else {
				request.setAttribute("message", msg);
				forwardPath = "/WEB-INF/jsp/boardMain.jsp";
			}
		} else if (request.getParameter("action").equals("del")) {
			// 変数の宣言と初期値(Set variables)。
			String delId = request.getParameter("delid");

			msg = bLogic.del(delId);
			request.setAttribute("message", msg);
			forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
		} else if (request.getParameter("action").equals("search")) {
			Common c = new Common();

			request.getSession().setAttribute("name", c.sanitizing(request.getParameter("name")));
			request.getSession().setAttribute("comment", c.sanitizing(request.getParameter("comment")));
			ArrayList<String> test = new ArrayList<String>();
			test.add("name");
			test.add("comment");


			//request.setAttribute("name", c.sanitizing(request.getParameter("name")));
			//request.setAttribute("comment", c.sanitizing(request.getParameter("comment")));
//			forwardPath = "/WEB-INF/jsp/boardMain.jsp";
		}

//		if (msg != null) {
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("/boardDB/BoardDoRedirect");
//		} else {
//			request.getRequestDispatcher(forwardPath).forward(request, response);
//		}
	}

	private void paramAdd(HttpServletRequest request, ArrayList<String> tempList) {
		for (String s : tempList) {
			request.getSession().setAttribute(s, s);
			System.out.println("2 " + request.getSession().getAttribute(s));
		}
		request.getSession().setAttribute("paramArrayList", tempList);
	}

	private void paramRemove(HttpServletRequest request, ArrayList<String> tempList) {
		for (String s : tempList) {
			request.getSession().removeAttribute(s);
		}
		request.getSession().removeAttribute("paramArrayList");
	}

}
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

import model.BoardBean;
import model.BoardLogic;

@WebServlet("/BoardDo")
public class BoardDo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 掲示板画面のアドレスを渡す(Pass the address to the main page)。
		request.setAttribute("message", request.getSession().getAttribute("msg"));
		request.getSession().removeAttribute("msg");
				
		if (request.getSession().getAttribute("attributeToRemove") != null) {
			ArrayList<String> attributeList = (ArrayList<String>) request.getSession().getAttribute("attributeToRemove");
			for (String s : attributeList) {
				request.setAttribute(s, request.getSession().getAttribute(s));
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/boardMain.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// 変数の宣言と初期値(Set variables)。
		ServletContext application = this.getServletContext();
		ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
		ArrayList<String> msg;
//		ArrayList<String> attributeToRemove = new ArrayList<String>();
		ArrayList<String> attributeToRemove = (ArrayList<String>) request.getSession().getAttribute("attributeToRemove");
		if (attributeToRemove != null) {
			removeTempAttributes(request, attributeToRemove);
		}
		attributeToRemove = new ArrayList<String>();
		
		BoardLogic bLogic = new BoardLogic();

		String forwardPath = null;

		// 'boardList'はnullかどうか確認する。もしnullなら、これを作って、アプリケーションスコープに設置する
		// 		(Check if the boardList is null. If so, create a new ArrayList and add to the application scope)。
		if (boardList == null) {
			boardList = new ArrayList<BoardBean>();
			application.setAttribute("boardList", boardList);
		}

		/*
		 * クリックしたボタンに応じて以下のステップを行う。 ２０１７年１０月１３日までに、３つのボタンがあて、各の値は：
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
			Integer finalId = (Integer) application.getAttribute("finalId");
			String name = request.getParameter("name"), email = request.getParameter("email"), comment = request
					.getParameter("comment");

			BoardBean bBean;

			if (finalId == null) {
				finalId = 0;
			}

			// 新しいオブジェクトを作って、オブジェクトの内容を確認する(Create a new object, and check the values of the object)。
			bBean = new BoardBean(finalId + 1, name, email, comment, "");
			msg = bLogic.add(bBean);
			if (msg.get(0).indexOf("投稿") != -1) {
				// 成功の場合(If successful)。
				finalId += 1;
				application.setAttribute("finalId", finalId);
				boardList.add(bBean);
			}
			
			request.getSession().setAttribute("msg", msg);
			response.sendRedirect("/board2/BoardDoRedirect");
		} else if (request.getParameter("action").equals("admin")) {
			// 変数の宣言と初期値(Set variables)。
			String adminPass = request.getParameter("adminpass");

			msg = bLogic.admin(adminPass);
			if (msg.get(0).equals("")) {
				// 成功の場合(If successful)。
				//Update 2017.10.22 Replaced by redirect process below::
//				forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
				
				request.getSession().setAttribute("msg", msg);
				System.out.println(request.getSession().getAttribute("msg"));
				response.sendRedirect("/board2/BoardDoRedirect");
			} else {
				//Update 2017.10.22 Replaced by redirect process below::
//				request.setAttribute("message", msg);
//				forwardPath = "/WEB-INF/jsp/boardMain.jsp";
				
				request.getSession().setAttribute("msg", msg);
				response.sendRedirect("/board2/BoardDoRedirect");
			}
		} else if (request.getParameter("action").equals("del")) {
			// 変数の宣言と初期値(Set variables)。
			String delId = request.getParameter("delid");
			
			msg = bLogic.del(delId, boardList);
			
			//Update 2017.10.22 Replaced by redirect process below::
//			request.setAttribute("message", msg);
//			forwardPath = "/WEB-INF/jsp/boardAdmin.jsp";
			
			request.getSession().setAttribute("msg", msg);
			request.getSession().setAttribute("admin", "admin");
			response.sendRedirect("/board2/BoardDoRedirect");
		} else if (request.getParameter("action").equals("search")) {
			
//			request.setAttribute("name", request.getParameter("name"));
//			request.setAttribute("comment", request.getParameter("comment"));
//			forwardPath = "/WEB-INF/jsp/boardMain.jsp";
			
			attributeToRemove.add("name");
			attributeToRemove.add("comment");
			addTempAttributes(request, attributeToRemove);
			
			response.sendRedirect("/board2/BoardDoRedirect");
		}
		
		if (forwardPath != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
			dispatcher.forward(request, response);
		}
	}
	
	private void addTempAttributes(HttpServletRequest request, ArrayList<String> attributeList) {
		for (String s : attributeList) {
			request.getSession().setAttribute(s, request.getParameter(s));
		}
		request.getSession().setAttribute("attributeToRemove", attributeList);
	}
	
	private void removeTempAttributes(HttpServletRequest request, ArrayList<String> attributeList) {
		for (String s : attributeList) {
			request.getSession().removeAttribute(s);
		}
		request.getSession().removeAttribute("attributeToRemove");
	}

}
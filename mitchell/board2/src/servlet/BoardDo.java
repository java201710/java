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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * Activates when the page is accessed
		 */

		//TODO: Can I put this directly into the dispatcher?
		//String forwardPath = "/WEB-INF/jsp/boardMain.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/boardMain.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = this.getServletContext();

		String forwardPath = null;
		ArrayList<BoardBean> boardList = (ArrayList<BoardBean>) application.getAttribute("boardList");
		if (boardList == null) {
			boardList = new ArrayList<BoardBean>();
			application.setAttribute("boardList", boardList);
		}

		BoardLogic bLogic = new BoardLogic();
		if (request.getParameter("action").equals("add")) {
			//TODO: Move these to the top or keep here?
			Integer finalId = (Integer) application.getAttribute("finalId");
			if (finalId == null) {
				finalId = 0;
			}
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String comment = request.getParameter("comment");

			BoardBean bBean = new BoardBean(finalId+1, name, email, comment);
			ArrayList<String> msg = bLogic.add(bBean);
			if (msg.get(0).indexOf("投稿") != -1) {
				finalId += 1;
				//I dont think I need to reset the id again... might be wrong though
								application.setAttribute("finalId", finalId);

				boardList.add(bBean);
				//I dont think I need this here. If set at the top then I shouldnt have to do it again.
								//application.setAttribute("boardList", boardList);
			}
			request.setAttribute("message", msg);
			forwardPath = "/WEB-INF/jsp/boardMain.jsp";
		} else if (request.getParameter("action").equals("admin")) {
			//Finish this one first.
			String adminPass = request.getParameter("adminpass");
			ArrayList<String> errMsg = bLogic.admin(adminPass);
			if (errMsg.size() > 0) {
				//If Error
				request.setAttribute("message", errMsg);
				forwardPath = "/WEB-INF/jsp/boardMain.jsp";
			} else {
				forwardPath = "/WEB-INF/jsp/boardadmin.jsp";
			}
		} else if (request.getParameter("action").equals("del")) {
			String delId = request.getParameter("delid");
			ArrayList<String> msg = bLogic.del(delId, boardList);

			request.setAttribute("message", msg);

			// Nothing changes on pass or fail
//			if (msg.get(0).indexOf("投稿") != -1) {
//
//			if (bLogic.del(delId).size() > 0) {
//				//Pass Error
//
//			} else {
				forwardPath = "/WEB-INF/jsp/boardadmin.jsp";
			//}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}

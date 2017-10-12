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

import registerUser.RegisterUserLogic;
import registerUser.User;
import registerUser.UserScanner;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forwardPath = null;
		String action = request.getParameter("action");
		ArrayList<String> errMsg = new ArrayList<String>();
		
		if (action == null) {
			forwardPath = "/WEB-INF/registerForm.jsp";
		} else if (action.equals("done")) {
			ServletContext application = this.getServletContext();
			HttpSession session = request.getSession();
			
			User registerUser = (User) session.getAttribute("registerUser");
			if (registerUser == null) {
				errMsg.add("[Error] Page refreshed/Illegal access. Please return to the main page.");
				request.setAttribute("resultMsg", errMsg);
				forwardPath = "WEB-INF/registerError.jsp";
			} else {
			
			ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");

			userList.add(registerUser);
			request.setAttribute("registerUser", registerUser);
			session.removeAttribute("registerUser");
			
			forwardPath = "/WEB-INF/registerComplete.jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("ISO-8859-1");
		
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		String forwardPath = null;
		ArrayList<String> errMsg = new ArrayList<String>();
		
		RegisterUserLogic registerUL = new RegisterUserLogic();
		User registerUser = registerUL.makeUser(id, pass, name);
		UserScanner userScan = new UserScanner();
		
		ArrayList<User> userList = (ArrayList<User>) application.getAttribute("userList");
		if (userList == null) {
			userList = new ArrayList<User>();
			application.setAttribute("userList", userList);
		}
				
		errMsg = userScan.checkUser(registerUser, userList);
		if (errMsg.size() > 0) {
			request.setAttribute("resultMsg", errMsg);
			forwardPath = "WEB-INF/registerError.jsp";
		} else {
			request.setAttribute("registerUser", registerUser);
			session.setAttribute("registerUser", registerUser);
			forwardPath = "WEB-INF/registerConfirmation.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}

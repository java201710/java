package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EmployeeSystem")
public class EmployeeSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String forward = "";
		
		if (session.getAttribute("employeeId") == null) {
			forward = "/WEB-INF/employee/login.jsp";
		} else {
			
			if ((String) request.getParameter("logout") != null) {
				removeSessionAttributes(session);
				response.sendRedirect("/EmployeeControl/EmployeeSystem");
				return;
			}
			forward = "/WEB-INF/employee/employeeSystemMain.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		//request.getRequestDispatcher(forward).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (request.getParameter("action").equals("login")) {
			String employeeId = (String) request.getParameter("employeeId");
			//TODO check with database
			//Dummy values.. always pass
			setSessionAttribute(session, "employeeId", employeeId);
			setSessionAttribute(session, "employeeName", "ミッチェル");
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeSystemMain.jsp").forward(request, response);
	}
	
	private void setSessionAttribute(HttpSession session, String attributeName, String attributeValue) {
		ArrayList<String> sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
		if (sessionAttributes == null) {
			sessionAttributes = new ArrayList<String>();
		}
		
		session.setAttribute(attributeName, attributeValue);
		sessionAttributes.add(attributeName);
		session.setAttribute("sessionAttributes", sessionAttributes);
	}
	private void removeSessionAttributes(HttpSession session) {
		ArrayList<String>sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
		if (sessionAttributes != null) {
			for (String s : sessionAttributes) {
				session.removeAttribute(s);
			}
			session.removeAttribute("sessionAttributes");
		}
		
	}
}

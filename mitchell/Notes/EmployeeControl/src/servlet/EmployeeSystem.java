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

import employee.model.EmployeeBean;
import employee.model.EmployeeSystemLogic;

@WebServlet("/EmployeeSystem")
public class EmployeeSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();

		String message = "";
		String forward = "";

		if (session.getAttribute("employeeId") == null) {
			forward = "/WEB-INF/employee/login.jsp";
		} else {

			if ((String) request.getParameter("logout") != null) {
				removeSessionAttributes(session);
				response.sendRedirect("/EmployeeControl/EmployeeSystem");
				return;
			}

			if (((String) request.getParameter("action")) != null) {
				//TODO:
				forward = "/WEB-INF/employee/employeeSystemMain.jsp";
			} else {
				if ((String) request.getParameter("page") != null) {
					if (((String) request.getParameter("page")).equals("viewuser")) {
						//TODO: there is no error check here
						EmployeeBean eBean = new EmployeeBean(Integer.parseInt((String) request.getParameter("selectedUser")));
						message = eSysLogic.viewUser(eBean);

						setSessionAttribute(session, "selectedUserBean", eBean);
						//setSessionAttribute(session, "message", message);
						request.setAttribute("message", message);
						forward = "/WEB-INF/employee/employeeViewer.jsp";
					} else {
						//TODO:
						forward = "/WEB-INF/employee/employeeSystemMain.jsp";
					}
				} else {
					//TODO:
					forward = "/WEB-INF/employee/employeeSystemMain.jsp";
				}
			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		//request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();

		if (request.getParameter("action").equals("login")) {
			EmployeeBean eBean = eSysLogic.findEmployee(Integer.parseInt(request.getParameter("employeeId")));

			setSessionAttribute(session, "employeeId", eBean.getEmployeeId());
			setSessionAttribute(session, "employeeName", eBean.getEmployeeName());
			setSessionAttribute(session, "adminFlag", eBean.getAdminFlag());
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeSystemMain.jsp").forward(request, response);
	}

	//TODO:: Cant I just look at the object and then cast it instead of trying to make all of these seperate methods?
//	private void setSessionAttribute(HttpSession session, String attributeName, Object attributeValue) {
//		ArrayList<String> sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
//		if (sessionAttributes == null) {
//			sessionAttributes = new ArrayList<String>();
//		}
//
//		session.setAttribute(attributeName, attributeValue);
//		sessionAttributes.add(attributeName);
//		session.setAttribute("sessionAttributes", sessionAttributes);
//	}





	private void setSessionAttribute(HttpSession session, String attributeName, int attributeValue) {
		ArrayList<String> sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
		if (sessionAttributes == null) {
			sessionAttributes = new ArrayList<String>();
		}

		session.setAttribute(attributeName, attributeValue);
		sessionAttributes.add(attributeName);
		session.setAttribute("sessionAttributes", sessionAttributes);
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

	private void setSessionAttribute(HttpSession session, String attributeName, byte attributeValue) {
		ArrayList<String> sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
		if (sessionAttributes == null) {
			sessionAttributes = new ArrayList<String>();
		}

		session.setAttribute(attributeName, attributeValue);
		sessionAttributes.add(attributeName);
		session.setAttribute("sessionAttributes", sessionAttributes);
	}

	private void setSessionAttribute(HttpSession session, String attributeName, EmployeeBean attributeValue) {
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

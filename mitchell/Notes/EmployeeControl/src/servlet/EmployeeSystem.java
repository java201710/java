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

import dao.employee.EmployeeSystemDAO;
import employee.model.EmployeeBean;
import employee.model.EmployeeSystemLogic;

@WebServlet("/EmployeeSystem")
public class EmployeeSystem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
				// TODO:
				forward = "/WEB-INF/employee/employeeSystemMain.jsp";
			} else {
				if ((String) request.getParameter("page") != null) {
					if (((String) request.getParameter("page")).equals("viewuser")) {
						
						// TODO: there is no error check here
						EmployeeBean eBean = new EmployeeBean();
						eBean.setEmployeeId((Integer.parseInt((String) request.getParameter("selectedUser"))));
						message = eSysLogic.viewUser(eBean).get(0);
						
						request.setAttribute("html", message);
						forward = "/WEB-INF/employee/employeeViewer.jsp";
					} else {
						// TODO:
						forward = "/WEB-INF/employee/employeeSystemMain.jsp";
					}
				} else {

					EmployeeBean eBean = new EmployeeBean();
					request.setAttribute("employeeBean", eBean);
					ArrayList<String> result = eSysLogic.search(eBean, (Byte) session.getAttribute("login_adminFlag"));

					if (result.get(0).equals("")) {
						request.setAttribute("html", result.get(1));
					} else {
						request.setAttribute("html", result.get(0));
					}
					
					forward = "/WEB-INF/employee/employeeSystemMain.jsp";
				}

			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forward);
		dispatcher.forward(request, response);
		// request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");

		if (request.getParameter("action").equals("login")) {
			EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();
			EmployeeBean eBean = new EmployeeBean();
			eBean.setEmployeeId((Integer.parseInt((String) request.getParameter("employeeId"))));
			
			eBean = eSysLogic.findEmployee(eBean);
			eBean.setAdminFlag((byte) 1);

			setSessionAttribute(session, "employeeId", eBean.getEmployeeId());
			setSessionAttribute(session, "employeeName", eBean.getEmployeeName());
			setSessionAttribute(session, "login_adminFlag", eBean.getAdminFlag());
			
			ArrayList<String> result = eSysLogic.search(new EmployeeBean(), (Byte) session.getAttribute("login_adminFlag"));

			if (result.get(0).equals("")) {
				request.setAttribute("message", result.get(1));
			} else {
				request.setAttribute("html", result.get(0));
			}
		} else if (request.getParameter("action").equals("search")) {
			String baseName = request.getParameter("baseName");
			String departmentName = request.getParameter("departmentName");
			String divisionName = request.getParameter("divisionName");
			String positionName = request.getParameter("positionName");
			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
						
			EmployeeBean eBean = new EmployeeBean();
			eBean.setBaseName(baseName);
			eBean.setDepartmentName(departmentName);
			eBean.setDivisionName(divisionName);
			eBean.setPositionName(positionName);
			
			if (fromDate.equals("")) {
				eBean.setFromDate(0);
			} else {
				eBean.setFromDate(Integer.parseInt(fromDate));
			}
			if (toDate.equals("")) {
				eBean.setToDate(0);
			} else {
				eBean.setToDate(Integer.parseInt(toDate));
			}
			request.setAttribute("employeeBean", eBean);
			
			EmployeeSystemLogic eSysLogic = new EmployeeSystemLogic();
			ArrayList<String> result = eSysLogic.search(eBean, (Byte) session.getAttribute("login_adminFlag"));
			
			if (result.get(0).equals("") && result.size() > 1) {
				request.setAttribute("message", result.get(1));
			} else {
				request.setAttribute("html", result.get(0));
			}
			
		}
		request.getRequestDispatcher("/WEB-INF/employee/employeeSystemMain.jsp").forward(request, response);
	}

	// TODO:: Cant I just look at the object and then cast it instead of trying to
	// make all of these seperate methods?
	// private void setSessionAttribute(HttpSession session, String attributeName,
	// Object attributeValue) {
	// ArrayList<String> sessionAttributes = (ArrayList<String>)
	// session.getAttribute("sessionAttributes");
	// if (sessionAttributes == null) {
	// sessionAttributes = new ArrayList<String>();
	// }
	//
	// session.setAttribute(attributeName, attributeValue);
	// sessionAttributes.add(attributeName);
	// session.setAttribute("sessionAttributes", sessionAttributes);
	// }

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
		ArrayList<String> sessionAttributes = (ArrayList<String>) session.getAttribute("sessionAttributes");
		if (sessionAttributes != null) {
			for (String s : sessionAttributes) {
				session.removeAttribute(s);
			}
			session.removeAttribute("sessionAttributes");
		}

	}
}

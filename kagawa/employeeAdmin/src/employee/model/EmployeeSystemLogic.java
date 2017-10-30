package employee.model;

import java.util.ArrayList;

import model.Common;

public class EmployeeSystemLogic {
	//作成：2017/10/27 香川 雄一
	public ArrayList<String> register(EmployeeBean employeeBean) {
		Common c = new Common();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM employee WHERE employeeId = '");
		sql.append(employeeBean.employeeId);
		sql.append("'");
//		DaoLogic Dao = new DaoLogic();
//		ArrayList<BoardBean> boardList = Dao.findBoard(sql.toString());

		return null;
	}

	public ArrayList<String> update(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> delete(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> viewUser(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> updateUser(EmployeeBean EmployeeBean, String lastpage) {

		return null;
	}

	public ArrayList<String> search(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> confirmNewUser(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> confirmUpdateUser(EmployeeBean EmployeeBean) {

		return null;
	}

	public ArrayList<String> confirmDeleteUser(EmployeeBean EmployeeBean) {

		return null;
	}

	public EmployeeBean login(EmployeeBean EmployeeBean) {
		return null;
	}

}

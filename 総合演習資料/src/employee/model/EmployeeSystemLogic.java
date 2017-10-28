package employee.model;

import java.util.ArrayList;

import dao.employee.EmployeeSystemDAO;

public class EmployeeSystemLogic {

	ArrayList<String> register(EmployeeBean EmployeeBean) {

		return null;
	}
	
	ArrayList<String> update(EmployeeBean EmployeeBean) {

		return null;
	}

	
	ArrayList<String> delete(EmployeeBean EmployeeBean) {

		return null;
	}

	ArrayList<String> viewUser(EmployeeBean EmployeeBean) {
		/*
		*メイズ・ミッチェル　（更新日時：２０１７年１０月２８日）
		*（未入力）
		*/
		ArrayList<EmployeeBean> employeeList;
		EmployeeSystemDAO eSysDAO = new EmployeeSystemDAO();
		ArrayList<String> message = new ArrayList<String>();
		
		String sql = "SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" + 
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" + 
				"DI.divisionName ,P.positionCode ,P.positionName ,E.positionMemo ,\r\n" + 
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" + 
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" + 
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" + 
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" + 
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n" + 
				"WHERE employeeID = " + EmployeeBean.getEmployeeId() + ";";
		
		employeeList = eSysDAO.findEmployee(sql);
		if (employeeList == null) {
			message.add("e017：データベースでエラーが発生しています");
		} else if (employeeList.size() == 0) {
			message.add("");
		} else {
			EmployeeBean = employeeList.get(0);
			
			StringBuilder output = new StringBuilder();
			output.append("<table style=\"width:600px\"><tr>" +
					"				<td id=rowheader>社員ＩＤ：</td><td>" + EmployeeBean.getEmployeeId() + "</td><td id=pictureheader>写真：</td><td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>名前：</td><td>" + EmployeeBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
					"					<table border=1 width=300 height=400>" +
					"						<tr>" +
					"							<td align=\"center\"><img src=\"img/"+ EmployeeBean.getEmployeeId() +".jpg\"/></td>" +
					"						</tr>" +
					"					</table>" +
					"				</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>ふりがな：</td><td>" + EmployeeBean.getKana() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>性別：</td><td>" + EmployeeBean.getGender() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>拠点：</td><td>" + EmployeeBean.getBaseName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>部署：</td><td>" + EmployeeBean.getDepartmentName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>課：</td><td>" + EmployeeBean.getDivisionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職：</td><td>" + EmployeeBean.getPositionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職詳細：</td><td>" + EmployeeBean.getPositionMemo() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>内線：</td><td>" + EmployeeBean.getNaisenNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>業務携帯番号：</td><td>" + EmployeeBean.getPublicCellphoneNumber() + "</td>" +
					"			</tr>" +
					"		</table>");
			message.add(output.toString());
		}

		return message;
	}
	
	ArrayList<String> updateUser(EmployeeBean EmployeeBean,String lastpage) {

		return null;
	}
	
	ArrayList<String> search(EmployeeBean EmployeeBean, byte adminFlag) {
		/*
		*メイズ・ミッチェル　（更新日時：２０１７年１０月２８日）
		*（未入力）
		*/
		ArrayList<EmployeeBean> employeeList;
		EmployeeSystemDAO eSysDAO = new EmployeeSystemDAO();
		ArrayList<String> message = new ArrayList<String>();
		
		//Temp
		String sql = "SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" + 
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" + 
				"DI.divisionName ,P.positionCode ,P.positionName ,E.positionMemo ,\r\n" + 
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" + 
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" + 
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" + 
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" + 
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n" +
				"ORDER BY registrationDateTime DESC;";
		employeeList = eSysDAO.findEmployee(sql);
		
		if (employeeList == null) {
			message.add("");
			message.add("e017：データベースでエラーが発生しています");
		} else {
			StringBuilder output = new StringBuilder();
			output.append("<table border=1 width=100%>");
			if (adminFlag == 1) {
				output.append("<tr><th></th><th></th>");
			}
			output.append("<th>社員ＩＤ</th><th>名前</th><th>拠点</th><th>部署</th><th>課</th><th>役職</th><th>内線</th><th>携帯番号</th>"
					+ "			</tr>");
			for (EmployeeBean b : employeeList) {
				output.append("<tr align=\"center\">");
				if (adminFlag == 1) {
					output.append("<td><a href=\"/employeeAdmin/EmployeeSystem?page=updateUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">修正</a></td>"
							+ "    <td><a href=\"/employeeAdmin/EmployeeSystem?page=deleteUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">削除</a></td>");
				}
				output.append("<td>" + b.getEmployeeId() + "</td>"
								+ "				<td><a href=\"/employeeAdmin/EmployeeSystem?page=viewuser&selectedUser=" + b.getEmployeeId() + "\">" + b.getEmployeeName() + " </a></td>"
								+ "<td>" + b.getBaseName() + " </td>"
						+ "			<td>" + b.getDepartmentName() + " </td><td>" + b.getDivisionName() + " </td><td>" + b.getPositionName() + " </td>"
						+ "			<td>" + b.getNaisenNumber() + " </td><td>" + b.getPublicCellphoneNumber() + " </td>"
						+	 "</tr>");
			}
			
			output.append("</table");
			message.add(output.toString());
		}
		
		return message;
	}
	
	ArrayList<String> confirmNewUser(EmployeeBean EmployeeBean) {

		return null;
	}
	
	ArrayList<String> confirmUpdateUser(EmployeeBean EmployeeBean) {

		return null;
	}
	
	ArrayList<String> confirmDeleteUser(EmployeeBean EmployeeBean) {

		return null;
	}
	
	
		
	
	
	
	
	
}

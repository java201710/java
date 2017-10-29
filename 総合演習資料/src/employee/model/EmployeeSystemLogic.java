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

	public ArrayList<String> viewUser (EmployeeBean eBean) {
		/*
		*	（更新日時：２０１７年１０月２９日）
		*	<-- メイズ・ミッチェル -->
		*	社員情報管理の社員情報詳細画面の為に社員情報の表を準備する
		*/
		
		//変数
		ArrayList<EmployeeBean> employeeList;
		EmployeeSystemDAO eSysDAO = new EmployeeSystemDAO();
		ArrayList<String> message = new ArrayList<String>();
		
		//SQL文の準備
		String sql = "SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" + 
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" + 
				"DI.divisionName ,P.positionCode ,P.positionName ,E.positionMemo ,\r\n" + 
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" + 
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" + 
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" + 
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" + 
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n" + 
				"WHERE employeeID = " + eBean.getEmployeeId() + ";";
		
		//データベースからSQLのArrayListの結果をもらって、"employeeList"に入れる。結果は一つはず
		employeeList = eSysDAO.findEmployee(sql);
		if (employeeList == null) {
			message.add("");
			message.add("e017：データベースでエラーが発生しています");
		} else if (employeeList.size() == 0) {
			message.add("");
			message.add("e018:該当の社員IDは存在しません");
		} else {
			//一つのEmployeeBeanから表を準備する
			eBean = employeeList.get(0);
			
			StringBuilder output = new StringBuilder();
			output.append("<table id=dataframe><tr>" +
					"				<td id=rowheader>社員ＩＤ：</td><td>" + eBean.getEmployeeId() + "</td><td id=pictureheader>写真：</td><td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>名前：</td><td>" + eBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
					"					<table id=pictureframe>" +
					"						<tr>" +
					"							<td><img src=\"img/"+ eBean.getEmployeeId() +".jpg\"/></td>" +
					"						</tr>" +
					"					</table>" +
					"				</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>ふりがな：</td><td>" + eBean.getKana() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>性別：</td><td>" + eBean.getGender() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>拠点：</td><td>" + eBean.getBaseName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>部署：</td><td>" + eBean.getDepartmentName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>課：</td><td>" + eBean.getDivisionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職：</td><td>" + eBean.getPositionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職詳細：</td><td>" + eBean.getPositionMemo() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>内線：</td><td>" + eBean.getNaisenNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>業務携帯番号：</td><td>" + eBean.getPublicCellphoneNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td colspan=\"3\"><hr></td>" +
					"			</tr>" +
					"		</table>");
			message.add(output.toString());
		}

		return message;
	}
	
	ArrayList<String> updateUser(EmployeeBean EmployeeBean,String lastpage) {

		return null;
	}
	
	public ArrayList<String> search(EmployeeBean eBean, byte adminFlag) {
		/*
		*	（更新日時：２０１７年１０月２９日）
		*	<-- メイズ・ミッチェル -->
		*	社員情報管理のメインページの検索フォーム処理
		*/
		
		//変数
		EmployeeSystemDAO eSysDAO = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList;
		
		ArrayList<String> selectedValues = new ArrayList<String>();
		ArrayList<String> message = new ArrayList<String>();
		StringBuilder sqlBuilder = new StringBuilder();
		String sql = "";
		
		//引数EmployeeBeanからデータを引く
		String baseName = eBean.getBaseName();
		String departmentName = eBean.getDepartmentName();
		String divisionName = eBean.getDivisionName();
		String positionName = eBean.getPositionName();
		int fromDate = eBean.getFromDate();
		int toDate = eBean.getToDate();
		
		//検索フォームに選んだパラメータを決める
		if (!(baseName == null)) {
			if (!baseName.equals("")){
				selectedValues.add("baseName");
				selectedValues.add(baseName);
			}
		}
		if (!(departmentName == null)) {
			if (!departmentName.equals("")) {
				selectedValues.add("departmentName");
				selectedValues.add(departmentName);
			}
		}
		if (!(divisionName == null)) {
			if (!divisionName.equals("")){
				selectedValues.add("divisionName");
				selectedValues.add(divisionName);
			}
		}
		if (!(positionName == null)) {
			if (!positionName.equals("")){
				selectedValues.add("positionName");
				selectedValues.add(positionName);
			}
		}
		if (!(fromDate == 0)) {
			selectedValues.add("fromDate");
			selectedValues.add(Integer.toString(fromDate*1000));
		}
		if (!(toDate == 0)) {
			selectedValues.add("toDate");
			selectedValues.add(Integer.toString(toDate*1000+999));
		}
		
		//SQL文の準備
		sqlBuilder.append("SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" + 
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" + 
				"DI.divisionName ,P.positionCode ,P.positionName , E.positionMemo ,\r\n" + 
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" + 
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" + 
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" + 
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" + 
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n");
		
		//SQL文に検索フォームで選んだパラメータの値を加える
		if (!selectedValues.isEmpty()) {
			sqlBuilder.append("WHERE ");
			for (int i = 0; i < selectedValues.size(); i += 2) {
				if (i > 0) {
					sqlBuilder.append("AND ");
				}
				if (selectedValues.get(i).equals("fromDate")) {
					sqlBuilder.append("employeeID > " + selectedValues.get(i+1) +"\r\n");
				} else if (selectedValues.get(i).equals("toDate")) {
					sqlBuilder.append("employeeID < " + selectedValues.get(i+1) +"\r\n");
				} else {
					sqlBuilder.append(selectedValues.get(i) + " LIKE \"%" + selectedValues.get(i+1) + "%\"\r\n");
				}
			}
			sqlBuilder.append("ORDER BY positionCode DESC\r\n");
		} else {
			sqlBuilder.append("ORDER BY registrationDateTime DESC\r\n");
		}
		sqlBuilder.append(";");
		sql = sqlBuilder.toString();
		
		//データベースからSQLのArrayListの結果をもらって、"employeeList"に入れる
		employeeList = eSysDAO.findEmployee(sql);
		if (employeeList == null) {
			message.add("");
			message.add("e017:データベースでエラーが発生しています");
		} else {
			//エラーが発生していない場合、一覧テーブル文を準備する
			StringBuilder tableBuilder = new StringBuilder();

			for (EmployeeBean b : employeeList) {
				tableBuilder.append("<tr align=\"center\">");
				if (adminFlag == 1) {
					tableBuilder.append("<td><a href=\"/employeeAdmin/EmployeeSystem?page=updateUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">修正</a></td>"
							+ "    <td><a href=\"/employeeAdmin/EmployeeSystem?page=deleteUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">削除</a></td>");
				}
				tableBuilder.append("<td>" + b.getEmployeeId() + "</td>"
								+ "				<td><a href=\"/employeeAdmin/EmployeeSystem?page=viewuser&selectedUser=" + b.getEmployeeId() + "\">" + b.getEmployeeName() + " </a></td>"
								+ "<td>" + b.getBaseName() + " </td>"
						+ "			<td>" + b.getDepartmentName() + " </td><td>" + b.getDivisionName() + " </td><td>" + b.getPositionName() + " </td>"
						+ "			<td>" + b.getNaisenNumber() + " </td><td>" + b.getPublicCellphoneNumber() + " </td>"
						+	 "</tr>");
			}
			message.add(tableBuilder.toString());
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
	
	public ArrayList<String> createSelectBox(String selectCategory, EmployeeBean eBean) {
		/*
		*	（更新日時：２０１７年１０月２９日）
		*	<-- メイズ・ミッチェル -->
		*	それぞれのページの選択リストが必要があるなら、このメソッドを呼ぶ.
		*	リスト：「拠点」・「拠点：」・「課」・「役職」・「入社年月」
		*/
		
		//変数
		EmployeeSystemDAO eSysDAO = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList;
		ArrayList<String> message = new ArrayList<String>();
		ArrayList<String> selectValues = new ArrayList<String>();
		StringBuilder output = new StringBuilder();	
		
		if (eBean == null) {
			eBean = new EmployeeBean();
			eBean.setBaseName("");
			eBean.setDepartmentName("");
			eBean.setDivisionName("");
			eBean.setPositionName("");
			eBean.setFromDate(0);
			eBean.setToDate(0);
		}
		
		//SQL文の為、引数"selectCategory"でSQLの結果の並び替えを定める
		String orderBy = "";
		if (selectCategory.equals("baseName")) {
			orderBy = "baseCode";
		} else if (selectCategory.equals("departmentName")) {
			orderBy = "departmentCode";
		} else if (selectCategory.equals("divisionName")) {
			orderBy = "divisionCode";
		} else if (selectCategory.equals("positionName")) {
			orderBy = "positionCode";
		} else if (selectCategory.equals("fromDate") || selectCategory.equals("toDate")) {
			orderBy = "registrationDateTime";
		}
		
		//SQL文の準備
		String sql = "SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" + 
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" + 
				"DI.divisionName ,P.positionCode ,P.positionName ,E.positionMemo ,\r\n" + 
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" + 
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" + 
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" + 
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" + 
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n" +
				"ORDER BY " + orderBy + " ASC;";
		
		//データベースからSQLのArrayListの結果をもらって、"employeeList"に入れる
		employeeList = eSysDAO.findEmployee(sql);
		
		//引数"selectCategory"によると格EmployeeBeanから、データを引く
		if (employeeList != null) {
			String item = "";
			for (EmployeeBean e : employeeList) {
				if (selectCategory.equals("baseName")) {
					item = e.getBaseName();
				} else if (selectCategory.equals("departmentName")) {
					item = e.getDepartmentName();
				} else if (selectCategory.equals("divisionName")) {
					item = e.getDivisionName();
				} else if (selectCategory.equals("positionName")) {
					item = e.getPositionName();
				} else if (selectCategory.equals("fromDate") ||selectCategory.equals("toDate")) {
					item = Integer.toString(e.getEmployeeId()/1000);
				}
				
				//複合のオブジェクトを削除
				if (!selectValues.contains(item)) {
					selectValues.add(item);
				}
			}
		}
		
		//選択リスト文を準備する
		output.append("<select name=\"" + selectCategory + "\">");
		output.append("<option value=\"\">---未入力---</option>");
		
		//年月リストの処理
		if (selectCategory.equals("fromDate") || selectCategory.equals("toDate")) {
			for (int i = Integer.parseInt(selectValues.get(0)); i <= Integer.parseInt(selectValues.get(selectValues.size()-1)); i++) {
				if (Integer.toString(i).substring(4).equals("13")) {
					i += 87;
				} else {
					String s = Integer.toString(i);
					//検索したパラメータをまた表示する
					if (selectCategory.equals("fromDate") && i ==eBean.getFromDate()) {
						output.append("<option selected=\"selected\" value=\"" + s + "\">" + s.substring(0, 4) + "年" + s.substring(4) + "月</option>");
					} else if (selectCategory.equals("toDate") && i ==eBean.getToDate()) {
						output.append("<option selected=\"selected\" value=\"" + s + "\">" + s.substring(0, 4) + "年" + s.substring(4) + "月</option>");
					} else {
						output.append("<option value=\"" + s + "\">" + s.substring(0, 4) + "年" + s.substring(4) + "月</option>");
					}
				}
			}
		//他のリスト処理
		} else {
			for (String s : selectValues) {
				//検索したパラメータをまた表示する
				if ((s.equals(eBean.getBaseName())) || (s.equals(eBean.getDepartmentName())) || (s.equals(eBean.getDivisionName())) || (s.equals(eBean.getPositionName()))) {
					output.append("<option selected=\"selected\" value=\"" + s + "\">" + s + "</option>");
				} else {
					output.append("<option value=\"" + s + "\">" + s + "</option>");
				}
			}
		}
		output.append("</select>");
		
		message.add(output.toString());
		return message;
	}
	
	
		
	
	
	
	
	
}

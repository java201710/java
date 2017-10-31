package employee.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import model.Common;
import dao.employee.EmployeeSystemDAO;

public class EmployeeSystemLogic {

	//作成：2017/10/27 香川 雄一
	public ArrayList<String> register(EmployeeBean employeeBean) {
		ArrayList<String> messageList = new ArrayList<String>();
		Common c = new Common();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM employee_view WHERE employeeId = ");
		sql_select.append(employeeBean.getEmployeeId());

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		if (employeeList.size() != 0) {
			messageList.add("e008:社員IDが重複しています");
		}
		else {
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String sql_insert = "INSERT INTO employee(employeeId,password,employeeName,kana,gender,departmentCode,"
					+ "divisionCode,positionCode,positionMemo,naisenNumber,publicCellphoneNumber,adminFlag,"
					+ "registrationDateTime) values('" +
					+employeeBean.getEmployeeId()
					+ "','"
					+ c.sqlSanitizing(employeeBean.getPassword())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getEmployeeName())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getKana())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getGender())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getDepartmentCode())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getDivisionCode())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getPositionCode())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getPositionMemo())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getNaisenNumber())
					+ "','"
					+ c.sqlSanitizing(employeeBean.getPublicCellphoneNumber())
					+ "','"
					+ employeeBean.getAdminFlag()
					+ "','"
					+ f.format(now)
					+ "')";
			if (Dao.updateEmployee(sql_insert) > 0)
				messageList.add("登録できました");
			else
				messageList.add("e009:データベースでエラーが発生しています");

		}

		return messageList;
	}

	//作成：2017/10/27 香川 雄一
	public ArrayList<String> update(EmployeeBean employeeBean) {

		ArrayList<String> messageList = new ArrayList<String>();
		Common c = new Common();
		EmployeeSystemDAO Dao = new EmployeeSystemDAO();

		String sql_update = "UPDATE employee SET password='"
				+ employeeBean.getPassword()
				+ "',employeeName='"
				+ c.sqlSanitizing(employeeBean.getEmployeeName())
				+ "',kana='"
				+ c.sqlSanitizing(employeeBean.getKana())
				+ "',departmentCode='"
				+ employeeBean.getDepartmentCode()
				+ "',divisionCode='"
				+ employeeBean.getDivisionCode()
				+ "',positionCode='"
				+ employeeBean.getPositionCode()
				+ "',positionMemo='"
				+ c.sqlSanitizing(employeeBean.getPositionMemo())
				+ "',naisenNumber='"
				+ c.sqlSanitizing(employeeBean.getNaisenNumber())
				+ "',publicCellphoneNumber='"
				+ c.sqlSanitizing(employeeBean.getPublicCellphoneNumber())
				+ "',adminFlag='"
				+ employeeBean.getAdminFlag()
				+ "' WHERE employeeId = "
				+ employeeBean.getEmployeeId();

		if (Dao.updateEmployee(sql_update) > 0)
			messageList.add("修正できました");
		else
			messageList.add("e015:データベースでエラーが発生しています");

		return messageList;
	}

	//作成：2017/10/27 香川 雄一
	public ArrayList<String> delete(EmployeeBean employeeBean) {

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<String> messageList = new ArrayList<String>();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM employee_view\r\n" +
				"WHERE employeeID = " + employeeBean.getEmployeeId() + ";");
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		if (employeeList.size() == 0) {
			messageList.add("e016:社員IDが存在しません");
		}
		else {

			String sql_delete = "DELETE FROM employee WHERE employeeId = " + employeeBean.getEmployeeId();

			if (Dao.updateEmployee(sql_delete) > 0)
				messageList.add("削除できました");
			else
				messageList.add("e017:データベースでエラーが発生しています");

		}

		return messageList;
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
		String sql = "SELECT * FROM employee_view\r\n" +
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

	//作成：2017/10/30 香川 雄一
	public ArrayList<String> updateUser(EmployeeBean employeeBean, String lastpage, Byte login_adminFlag) {

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<String> messageList = new ArrayList<String>();
		ArrayList<EmployeeBean> employeeList;

		//SQL文の準備
		String sql = "SELECT E.employeeId ,E.password ,E.employeeName ,E.kana ,E.gender ,\r\n" +
				"B.baseCode ,B.baseName ,D.departmentCode ,D.departmentName ,DI.divisionCode ,\r\n" +
				"DI.divisionName ,P.positionCode ,P.positionName ,E.positionMemo ,\r\n" +
				"E.naisenNumber ,E.publicCellphoneNumber ,E.adminFlag ,E.registrationDateTime\r\n" +
				"FROM ((( employee E INNER JOIN department D ON E.departmentCode = D.departmentCode)\r\n" +
				"INNER JOIN base B ON B.baseCode = D.baseCode)\r\n" +
				"INNER JOIN division DI ON E.divisionCode = DI.divisionCode)\r\n" +
				"INNER JOIN position_table P ON E.positionCode = P.positionCode\r\n" +
				"WHERE employeeID = " + employeeBean.getEmployeeId() + ";";

		//データベースからSQLのArrayListの結果をもらって、"employeeList"に入れる。結果は一つはず
		employeeList = Dao.findEmployee(sql);
		// エラー対応
		if (lastpage != null) {
			if (employeeList == null) {
				messageList.add("");
				messageList.add("データベースでエラーが発生しています");
				return messageList;
			} else if (employeeList.size() == 0) {
				messageList.add("");
				messageList.add("該当の社員IDは存在しません");
				return messageList;
			}
		}

		employeeBean.setPassword(employeeList.get(0).password);
		employeeBean.setEmployeeName(employeeList.get(0).employeeName);
		employeeBean.setKana(employeeList.get(0).kana);
		employeeBean.setDepartmentName(employeeList.get(0).departmentName);
		employeeBean.setDivisionName(employeeList.get(0).divisionName);
		employeeBean.setPositionName(employeeList.get(0).positionName);
		employeeBean.setPositionMemo(employeeList.get(0).positionMemo);
		employeeBean.setNaisenNumber(employeeList.get(0).naisenNumber);
		employeeBean.setPublicCellphoneNumber(employeeList.get(0).publicCellphoneNumber);
		employeeBean.setAdminFlag(employeeList.get(0).adminFlag);

		StringBuilder output = new StringBuilder();
		// 管理者の場合
		if (login_adminFlag == 1) {
			output.append("<h1 style='text-align:left'>社員情報 修正</h1>" +
					"<form action='/employeeAdmin/EmployeeSystem' method='post'>" +
					"<p>　社員ID：" + employeeBean.getEmployeeId() +
					"<p>　旧パスワード：<input type='text' name='oldPassword' value=" + employeeBean.getPassword() +
					"><p>　新パスワード：<input type='text' name='newPassword'>" +
					"<p>　名前：<input type='text' name='employeeName' value=" + employeeBean.getEmployeeName() +
					"><p>　ふりがな：<input type='text' name='kana' value=" + employeeBean.getKana() +
					"><p>部署：" +
					createSelectBox("departmentName", employeeBean).get(0) +
					"<p>課：" +
					createSelectBox("divisionName", employeeBean).get(0) +
					"<p>役職：" +
					createSelectBox("positionName", employeeBean).get(0) +
					"<p>　役職詳細：<input type='text' name='positionMemo' value=" + employeeBean.getPositionMemo() +
					"><p>　内線：<input type='text' name='naisenNumber' value=" + employeeBean.getNaisenNumber() +
					"><p>　業務携帯番号：<input type='text' name='publicCellphoneNumber' value="
					+ employeeBean.getPublicCellphoneNumber() +
					"><p>　管理者権限：<input type='text' name='adminFlag' value="
					+ employeeBean.getAdminFlag() +
					"><input type = 'hidden' name='action' value='confirmUpdateUser'>" +
					"<input type = 'hidden' name='employeeId' value=" + employeeBean.getEmployeeId() +
					"><p><input type='submit' value='修正'>" +
					"</form>" +
					"<form action='/employeeAdmin/EmployeeSystem' method='get'>" +
					"<input type='submit' value='戻る'>" +
					"</form>");
		}
		// 一般社員の場合
		else
		{
			output.append("<h1 style='text-align:left'>社員情報 修正</h1>" +
					"<form action='/employeeAdmin/EmployeeSystem' method='post'>" +
					"<p>　社員ID：" + employeeBean.getEmployeeId() +
					"<p>　旧パスワード：<input type='text' name='oldPassword'>" +
					"<p>　新パスワード：<input type='text' name='newPassword'>" +
					"<p>　名前：" + employeeBean.getEmployeeName() +
					"<p>　ふりがな：" + employeeBean.getKana() +
					"<p>部署：" +
					employeeBean.getDepartmentName() +
					"<p>課：" +
					employeeBean.getDivisionName() +
					"<p>役職：" +
					employeeBean.getPositionName() +
					"<p>　役職詳細：" + employeeBean.getPositionMemo() +
					"<p>　内線：" + employeeBean.getNaisenNumber() +
					"<p>　業務携帯番号："
					+ employeeBean.getPublicCellphoneNumber() +
					"<input type = 'hidden' name='action' value='confirmUpdateUser'>" +
					"<input type = 'hidden' name='employeeId' value=" + employeeBean.getEmployeeId() +
					"><p><input type='submit' value='修正'>" +
					"</form>" +
					"<form action='/employeeAdmin/EmployeeSystem' method='get'>" +
					"<input type='submit' value='戻る'>" +
					"</form>");
		}
		messageList.add(output.toString());

		return messageList;
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
		sqlBuilder.append("SELECT * FROM employee_view\r\n");

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
				if (b.getAdminFlag() == 1) {
					tableBuilder.append("<tr id=admin>");
				} else {
					tableBuilder.append("<tr id=nonadmin>");
				}
				if (adminFlag == 1) {
					tableBuilder.append("<td><a href=\"/employeeAdmin/EmployeeSystem?page=updateUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">修正</a></td>"
							+ "    <td><a href=\"/employeeAdmin/EmployeeSystem?page=deleteUser&lastpage&selectedUser=" + b.getEmployeeId() + "\">削除</a></td>");
				}
				tableBuilder.append("<td>" + b.getEmployeeId() + "</td>"
								+ "				<td><a href=\"/employeeAdmin/EmployeeSystem?page=viewUser&selectedUser=" + b.getEmployeeId() + "\">" + b.getEmployeeName() + " </a></td>"
								+ "<td>" + b.getBaseName() + " </td>"
						+ "			<td>" + b.getDepartmentName() + " </td><td>" + b.getDivisionName() + " </td><td>" + b.getPositionName() + " </td>"
						+ "			<td>" + b.getNaisenNumber() + " </td><td>" + b.getPublicCellphoneNumber() + " </td>"
						+	 "</tr>");
			}
			message.add(tableBuilder.toString());
		}
		return message;
	}

	//作成：2017/10/30 香川 雄一
	public ArrayList<String> confirmNewUser(EmployeeBean employeeBean) {

		String employeeId = new Integer(employeeBean.getEmployeeId()).toString();
		String password = employeeBean.getPassword();
		String employeeName = employeeBean.getEmployeeName();

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<String> messageList = new ArrayList<String>();
		Pattern patternId = Pattern.compile("^[0-9]*$");
		Pattern patternPassword = Pattern.compile("^[0-9a-zA-Z]*$");
		Boolean errFlag = false;

		StringBuilder errBuild = new StringBuilder();

		// 入力チェック
		if (employeeId.equals("0")) {
//			messageList.add("社員IDを入力して下さい");
			errBuild.append("社員IDを入力して下さい<br>");
			errFlag = true;
		}

		if (!patternId.matcher(employeeId).matches()) {
//			messageList.add("社員IDは半角数字で入力して下さい");
			errBuild.append("社員IDは半角数字で入力して下さい<br>");
			errFlag = true;
		}

		if (password.equals("")) {
//			messageList.add("パスワードを入力して下さい");
			errBuild.append("パスワードを入力して下さい<br>");
			errFlag = true;
		}

		if (!patternPassword.matcher(password).matches()) {
//			messageList.add("パスワードは半角英数字で入力して下さい");
			errBuild.append("パスワードは半角英数字で入力して下さい<br>");
			errFlag = true;
		}

		if (employeeName.equals("")) {
//			messageList.add("名前を入力して下さい");
			errBuild.append("名前を入力して下さい<br>");
			errFlag = true;
		}

		if (errFlag == true) {
			messageList.add(0, "");
			messageList.add(errBuild.toString());
			return messageList;
		}

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM employee_view WHERE employeeId = ");
		sql_select.append(employeeBean.employeeId);
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		if (employeeList.size() != 0) {
			messageList.add("");
			messageList.add("e003:社員IDが重複しています");
		}
		else {
			//部署コードから名称を取得
			sql_select = new StringBuffer();
			sql_select.append("SELECT * FROM employee_view WHERE departmentCode = ");
			sql_select.append(employeeBean.getDepartmentCode());
			employeeList = Dao.findEmployee(sql_select.toString());
			if(employeeList!=null && employeeList.size() != 0){
				employeeBean.setDepartmentName(employeeList.get(0).getDepartmentName());
			}
			//課コードから名称を取得
			sql_select = new StringBuffer();
			sql_select.append("SELECT * FROM employee_view WHERE divisionCode = ");
			sql_select.append(employeeBean.getDivisionCode());
			employeeList = Dao.findEmployee(sql_select.toString());
			if(employeeList!=null && employeeList.size() != 0){
				employeeBean.setDivisionName(employeeList.get(0).getDivisionName());
			}
			//役職コードから名称を取得
			sql_select = new StringBuffer();
			sql_select.append("SELECT * FROM employee_view WHERE positionCode = ");
			sql_select.append(employeeBean.getPositionCode());
			employeeList = Dao.findEmployee(sql_select.toString());
			if(employeeList!=null && employeeList.size() != 0){
				employeeBean.setPositionName(employeeList.get(0).getPositionName());
			}


			StringBuilder output = new StringBuilder();
			output.append("<table id=dataframe><tr>" +
					"				<td id=rowheader>社員ＩＤ：</td><td>" + employeeBean.getEmployeeId()
					+ "</td><td id=pictureheader>写真：</td><td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>名前：</td><td>" + employeeBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
					"					<table id=pictureframe>" +
					"						<tr>" +
					"							<td><img src=\"img/" + employeeBean.getEmployeeId() + ".jpg\"/></td>" +
					"						</tr>" +
					"					</table>" +
					"				</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>ふりがな：</td><td>" + employeeBean.getKana() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>性別：</td><td>" + employeeBean.getGender() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>部署：</td><td>" + employeeBean.getDepartmentName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>課：</td><td>" + employeeBean.getDivisionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職：</td><td>" + employeeBean.getPositionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職詳細：</td><td>" + employeeBean.getPositionMemo() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>内線：</td><td>" + employeeBean.getNaisenNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>業務携帯番号：</td><td>" + employeeBean.getPublicCellphoneNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td colspan=\"3\"><hr></td>" +
					"			</tr>" +
					"		</table>");
			messageList.add(output.toString());

		}

		return messageList;
	}

	//作成：2017/10/30 香川 雄一
	public ArrayList<String> confirmUpdateUser(EmployeeBean employeeBean, String oldPassword) {

		String employeeId = new Integer(employeeBean.getEmployeeId()).toString();
		String password = employeeBean.getPassword();
		String employeeName = employeeBean.getEmployeeName();

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<String> messageList = new ArrayList<String>();
		Pattern patternPassword = Pattern.compile("^[0-9a-zA-Z]*$");
		Boolean errFlag = false;

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM employee_view WHERE employeeId = ");
		sql_select.append(employeeBean.employeeId);
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		// 入力チェック
		if (!oldPassword.equals(employeeList.get(0).password)) {
			messageList.add("旧パスワードが正しくありません");
			errFlag = true;
		}

		if (oldPassword.equals("")) {
			messageList.add("旧パスワードを入力して下さい");
			errFlag = true;
		}

		if (!patternPassword.matcher(oldPassword).matches()) {
			messageList.add("旧パスワードは半角英数字で入力して下さい");
			errFlag = true;
		}

		if (password.equals("")) {
			messageList.add("新パスワードを入力して下さい");
			errFlag = true;
		}

		if (!patternPassword.matcher(password).matches()) {
			messageList.add("新パスワードは半角英数字で入力して下さい");
			errFlag = true;
		}

		if (employeeName.equals("")) {
			messageList.add("名前を入力して下さい");
			errFlag = true;
		}

		if (errFlag == true) {
			messageList.add(0, "");
			return messageList;
		}

		StringBuilder output = new StringBuilder();
		output.append("<table id=dataframe><tr>" +
				"				<td id=rowheader>社員ＩＤ：</td><td>" + employeeBean.getEmployeeId()
				+ "</td><td id=pictureheader>写真：</td><td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>名前：</td><td>" + employeeBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
				"					<table id=pictureframe>" +
				"						<tr>" +
				"							<td><img src=\"img/" + employeeBean.getEmployeeId() + ".jpg\"/></td>" +
				"						</tr>" +
				"					</table>" +
				"				</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>パスワード：</td><td>" + employeeBean.getPassword() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>ふりがな：</td><td>" + employeeBean.getKana() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>部署：</td><td>" + employeeBean.getDepartmentName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>課：</td><td>" + employeeBean.getDivisionName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>役職：</td><td>" + employeeBean.getPositionName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>役職詳細：</td><td>" + employeeBean.getPositionMemo() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>内線：</td><td>" + employeeBean.getNaisenNumber() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>業務携帯番号：</td><td>" + employeeBean.getPublicCellphoneNumber() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td id=rowheader>管理者権限：</td><td>" + employeeBean.getAdminFlag() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td colspan=\"3\"><hr></td>" +
				"			</tr>" +
				"		</table>" +
				"<form action=\"/employeeAdmin/EmployeeSystem\" method=\"get\">" +
				"<input type = \"hidden\" name=\"action\" value=\"update\"><p>" +
				"<input type = \"hidden\" name=\"departmentCode\" value=" + employeeList.get(0).getDepartmentCode() + "><p>" +
				"<input type = \"hidden\" name=\"divisionCode\" value=" + employeeList.get(0).getDivisionCode() + "><p>" +
				"<input type = \"hidden\" name=\"positionCode\" value=" + employeeList.get(0).getPositionCode() + "><p>" +
				"<input type=\"submit\" value=\"修正\"></form>");
		messageList.add(output.toString());

		return messageList;
	}


	//作成：2017/10/30 香川 雄一
	public ArrayList<String> confirmDeleteUser(EmployeeBean employeeBean) {

		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<String> messageList = new ArrayList<String>();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM employee_view\r\n" +
				"WHERE employeeID = " + employeeBean.getEmployeeId() + ";");
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		//if (employeeList.size() <= 0) {
		if (employeeList == null) {
			messageList.add("");
			messageList.add("e016:社員IDが存在しません");
		}
		else {
			employeeBean = employeeList.get(0);

			StringBuilder output = new StringBuilder();
			output.append("<table id=dataframe><tr>" +
					"				<td id=rowheader>社員ＩＤ：</td><td>" + employeeBean.getEmployeeId()
					+ "</td><td id=pictureheader>写真：</td><td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>名前：</td><td>" + employeeBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
					"					<table id=pictureframe>" +
					"						<tr>" +
					"							<td><img src=\"img/" + employeeBean.getEmployeeId() + ".jpg\"/></td>" +
					"						</tr>" +
					"					</table>" +
					"				</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>パスワード：</td><td>" + employeeBean.getPassword() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>ふりがな：</td><td>" + employeeBean.getKana() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>性別：</td><td>" + employeeBean.getGender() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>部署：</td><td>" + employeeBean.getDepartmentName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>課：</td><td>" + employeeBean.getDivisionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職：</td><td>" + employeeBean.getPositionName() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>役職詳細：</td><td>" + employeeBean.getPositionMemo() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>内線：</td><td>" + employeeBean.getNaisenNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td id=rowheader>業務携帯番号：</td><td>" + employeeBean.getPublicCellphoneNumber() + "</td>" +
					"			</tr>" +
					"			<tr>" +
					"				<td colspan=\"3\"><hr></td>" +
					"			</tr>" +
					"		</table>");
			messageList.add(output.toString());

		}

		return messageList;
	}


    //メソッド名：login
    //引数：EmployeeBean EmployeeBean
    //戻り値：ArrayList<String>
    //修正履歴：
	public ArrayList<String> login(EmployeeBean EmployeeBean){
		EmployeeSystemDAO dao = new EmployeeSystemDAO();
		ArrayList<String> result = new ArrayList<String>();
		//入力された社員ID＋パスワードで社員情報テーブルを検索
		//%d･･･数値, %s･･･文字, %s･･･実数
		String sql = String.format("SELECT *"
				+ " FROM employee AS T1 "
					+ " INNER JOIN department AS T2 ON T1.departmentCode = T2.departmentCode"
					+ " INNER JOIN division AS T3 ON T1.divisionCode = T3.divisionCode"
					+ " INNER JOIN position_table AS T4 ON T1.positionCode = T4.positionCode"
					+ " INNER JOIN base AS T5 ON T2.baseCode = T5.baseCode"
				+ " WHERE T1.employeeId = %d"
				+ " AND T1.password = '%s'",
				EmployeeBean.getEmployeeId(),
				EmployeeBean.getPassword());
		ArrayList<EmployeeBean> employeeBeans = dao.findEmployee(sql);
		if(employeeBeans == null){
			//例外エラー
			//要素(0)：エラーメッセージ
			result.add("e*** データベースでエラーが発生しています");
		}else if(employeeBeans.size()==0){
			//該当する社員データなし
			//要素(0)：エラーメッセージ
			result.add("e001 社員IDまたはパスワードが正しくありません");
		}else{
			//要素(0)：エラーメッセージ
			result.add("");
			//要素(1)以降：ログインユーザー情報をセット
			result.add(Integer.toString(employeeBeans.get(0).getEmployeeId()));	//社員ID
			result.add(employeeBeans.get(0).getEmployeeName());	//氏名
			result.add(Byte.toString(employeeBeans.get(0).getAdminFlag()));	//管理者権限
		}

		return result;
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
		String sql = "SELECT * FROM employee_view\r\n" +
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

		if (employeeList == null) {
			//選択リスト文を準備する
			output.append("<select name=\"" + selectCategory + "\">");
			output.append("<option value=\"\">---未入力---</option>");
			output.append("</select>");
		} else {

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
		}

		message.add(output.toString());
		return message;
	}

//作成：2017/10/30 香川 雄一
	public String selectGenderBox() {
		StringBuffer buf = new StringBuffer();

		//HTML作成
		buf.append("<select name='gender'><option value=男>男</option><option value=女>女</option></select>");

		return buf.toString();
	}

	//作成：2017/10/30 香川 雄一
	public String selectDepartmentBox() {
		StringBuffer buf = new StringBuffer();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM department");
		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		//HTML作成
		buf.append("<select name='departmentName'>");
		for (EmployeeBean e : employeeList) {
			buf.append("<option value=");
			buf.append(e.getDepartmentCode());
			buf.append(">");
			buf.append(e.getDepartmentName());
			buf.append("</option>");
		}
		buf.append("</select>");

		return buf.toString();
	}

	//作成：2017/10/30 香川 雄一
	public String selectDivisionBox() {
		StringBuffer buf = new StringBuffer();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM division");
		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		//HTML作成
		buf.append("<select name='divisionName'>");
		for (EmployeeBean e : employeeList) {
			buf.append("<option value=");
			buf.append(e.getDivisionCode());
			buf.append(">");
			buf.append(e.getDivisionName());
			buf.append("</option>");
		}
		buf.append("</select>");

		return buf.toString();
	}

	//作成：2017/10/30 香川 雄一
	public String selectPositionBox() {
		StringBuffer buf = new StringBuffer();

		StringBuffer sql_select = new StringBuffer();
		sql_select.append("SELECT * FROM position");
		EmployeeSystemDAO Dao = new EmployeeSystemDAO();
		ArrayList<EmployeeBean> employeeList = Dao.findEmployee(sql_select.toString());

		//HTML作成
		buf.append("<select name='positionName'>");
		for (EmployeeBean e : employeeList) {
			buf.append("<option value=");
			buf.append(e.getPositionCode());
			buf.append(">");
			buf.append(e.getPositionName());
			buf.append("</option>");
		}
		buf.append("</select>");

		return buf.toString();
	}






}

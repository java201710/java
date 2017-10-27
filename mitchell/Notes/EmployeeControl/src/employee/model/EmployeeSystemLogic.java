package employee.model;

import java.util.ArrayList;

public class EmployeeSystemLogic {
	//temp variable
	public ArrayList<String> baseList = new ArrayList<String>() {{
	    add("本社");
	    add("名古屋");
	    add("大阪");
	    add("仙台");
	}};
	public ArrayList<String> departmentList = new ArrayList<String>() {{
	    add("経営企画部");
	    add("経理部");
	    add("人事部");
	    add("システム部");
	    add("総務部");
	    add("営業部");
	    add("支店経営企画部");
	    add("支店システム部");
	    add("支店営業部");
	    add("仙台");
	}};
	public ArrayList<String> divisionList = new ArrayList<String>() {{
	    add("推進企画課");
	    add("事業企画課");
	    add("経理課");
	    add("人事課");
	    add("システム課");
	    add("総務課");
	    add("営業1課");
	    add("営業2課");
	    add("営業3課");
	}};
	public ArrayList<String> positionList = new ArrayList<String>() {{
	    add("役員");
	    add("部長");
	    add("課長");
	    add("主任");
	    add("一般社員");
	    add("契約社員");
	    add("派遣社員・パート");
	}};

	public EmployeeSystemLogic() {
	}

	public String createSelectBox(String idName, ArrayList<String> list) {
		StringBuilder output = new StringBuilder();

		int i = 0;
		output.append("<select name=\"" + idName + "\">");
		output.append("<option value = " + i + ">--</option>");
		i++;
		for (String s : list) {
			output.append("<option value = " + i + ">" + s + "</option>");
			i++;
		}
		output.append("</select>");
		return output.toString();
	}

	public EmployeeBean findEmployee (int employeeId) {
		//Should be involving the DAO
		EmployeeBean eBean = new EmployeeBean(employeeId);

		return eBean;
	}

	public String viewUser (EmployeeBean eBean) {
		//TODO: DAO Business
		StringBuilder output = new StringBuilder();

		output.append("<table><tr>" +
				"				<td>社員ＩＤ：</td><td>" + eBean.getEmployeeId() + "</td><td align=\"center\">写真：</td><td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>名前：</td><td>" + eBean.getEmployeeName() + "</td><td rowspan=\"10\">" +
				"					<table border=1 width=300 height=400><tr><td align=\"center\"><img src=\"\"/></td></tr></table>" +
				"				</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>ふりがな：</td><td>" + eBean.getKana() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>性別：</td><td>" + eBean.getGender() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>拠点：</td><td>" + eBean.getBaseName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>部署：</td><td>" + eBean.getDepartmentName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>課：</td><td>" + eBean.getDivisionName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>役職：</td><td>" + eBean.getPositionName() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>役職詳細：</td><td>" + eBean.getPositionMemo() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>内線：</td><td>" + eBean.getNaisenNumber() + "</td>" +
				"			</tr>" +
				"			<tr>" +
				"				<td>業務携帯番号：</td><td>" + eBean.getPublicCellphoneNumber() + "</td>" +
				"			</tr>" +
				"		</table>");
		return output.toString();
	}
}

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
}

package employee.model;

import java.io.Serializable;

public class EmployeeBean implements Serializable {
	private int employeeId;
	private String password = "temp";
	private String employeeName = "ミッチェル";
	private String kana = "temp";
	private String baseName = "temp";
	private String departmentName = "temp";
	private String divisionName = "temp";
	private String positionName = "temp";
	private String positionMemo = "temp";
	private String naisenNumber = "temp";
	private String publicCellphoneNumber = "temp";
	private byte adminFlag = 1;
	private String gender = "男";

	//private int fromDate;
	//private into toDate;

	public EmployeeBean() {
		this(0);
	}

	public EmployeeBean(int employeeId) {
		setEmployeeId(employeeId);
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getNaisenNumber() {
		return naisenNumber;
	}

	public void setNaisenNumber(String naisenNumber) {
		this.naisenNumber = naisenNumber;
	}

	public String getPublicCellphoneNumber() {
		return publicCellphoneNumber;
	}

	public void setPublicCellphoneNumber(String publicCellphoneNumber) {
		this.publicCellphoneNumber = publicCellphoneNumber;
	}

	public byte getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(byte adminFlag) {
		this.adminFlag = adminFlag;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPositionMemo() {
		return positionMemo;
	}

	public void setPositionMemo(String positionMemo) {
		this.positionMemo = positionMemo;
	}

}

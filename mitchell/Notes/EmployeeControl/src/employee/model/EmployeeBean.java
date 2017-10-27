package employee.model;

import java.io.Serializable;

public class EmployeeBean implements Serializable {
	private String employeeId;
	private String password;
	private String employeeName;
	private String baseName;
	private String departmentName;
	private String divisionName;
	private String positionName;
	private String naisenNumber;
	private String publicCellphoneNumber;

	private byte adminFlag = 1;

	//private int fromDate;
	//private into toDate;

	public EmployeeBean() {
		this("0");
	}

	public EmployeeBean(String employeeId) {
		this(employeeId, "");
	}

	public EmployeeBean(String employeeId, String employeeName) {
		this(employeeId, employeeName,"","","","","","", "");
	}

	public EmployeeBean(String employeeId, String password, String employeeName, String baseName,
			String departmentName, String divisionName, String positionName, String naisenNumber,
			String publicCellphoneNumber) {

		setEmployeeId(employeeId);
		setPassword(password);
		setEmployeeName(employeeName);
		setBaseName(baseName);
		setDepartmentName(departmentName);
		setDivisionName(divisionName);
		setPositionName(positionName);
		setNaisenNumber(naisenNumber);
		setPublicCellphoneNumber(publicCellphoneNumber);
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
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

}

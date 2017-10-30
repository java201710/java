package employee.model;

//作成：2017/10/26 向山 憲之
public class EmployeeBean {

	//社員情報テーブル	employee
	int employeeId; //社員ID
	String password; //パスワード
	String employeeName; //名前
	String kana; //ふりがな
	String gender; //性別
	String departmentCode; //部署コード
	String divisionCode; //課コード
	String positionCode; //役職コード
	String positionMemo; //役職詳細
	String naisenNumber; //内線番号
	String publicCellphoneNumber; //業務携帯番号
	byte adminFlag; //管理者フラグ

	public EmployeeBean() {
	}

	//拠点マスタ	base
	String baseCode; //拠点コード
	String baseName; //拠点名

	//部署マスタ	department
	String departmentName; //部署名

	//課マスタ	division
	String divisionName; //課名

	//役職マスタ	position
	String positionName; //役職名

	//その他
	int fromDate; //入社年月（開始）
	int toDate; //入社年月（終了）

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionMemo() {
		return positionMemo;
	}

	public void setPositionMemo(String positionMemo) {
		this.positionMemo = positionMemo;
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

	public String getBaseCode() {
		return baseCode;
	}

	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
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

	public int getFromDate() {
		return fromDate;
	}

	public void setFromDate(int fromDate) {
		this.fromDate = fromDate;
	}

	public int getToDate() {
		return toDate;
	}

	public void setToDate(int toDate) {
		this.toDate = toDate;
	}
}

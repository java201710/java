package dao.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import employee.model.EmployeeBean;

//作成：2017/10/27 香川 雄一
public class EmployeeSystemDAO {

	//DB接続情報を設定する
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String PATH = "jdbc:mysql://localhost/employee_admin"; //接続パス
	private final String ID = "root"; //ログインID
	private final String PW = "root00"; //ログインパスワード

	//変数定義
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	// DBに接続する
	private void startDB() {
		try {
			//JDBCドライバをロードする
			Class.forName(DRIVER);

			//DBへのコネクションを作成する
			conn = DriverManager.getConnection(PATH, ID, PW);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// DBから切断する
	private boolean endDB() {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 取得メソッド
	public ArrayList<EmployeeBean> findEmployee(String sql) {
		// 戻り値の社員情報リスト
		ArrayList<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();

		try {
			startDB();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeBean employee = new EmployeeBean();
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setPassword(rs.getString("password"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setKana(rs.getString("kana"));
				employee.setGender(rs.getString("gender"));
				employee.setDepartmentCode(rs.getString("departmentCode"));
				employee.setDivisionCode(rs.getString("divisionCode"));
				employee.setPositionCode(rs.getString("positionCode"));
				employee.setPositionMemo(rs.getString("positionMemo"));
				employee.setNaisenNumber(rs.getString("naisenNumber"));
				employee.setPublicCellphoneNumber(rs.getString("publicCellphoneNumber"));
				employee.setAdminFlag(rs.getByte("adminFlag"));
				employee.setBaseCode(rs.getString("baseCode"));
				employee.setBaseName(rs.getString("baseName").trim());
				employee.setDepartmentName(rs.getString("departmentName").trim());
				employee.setDivisionName(rs.getString("divisionName").trim());
				employee.setPositionName(rs.getString("positionName").trim());
				//employee.setFromDate(rs.getInt("fromDate"));
				//employee.setToDate(rs.getInt("toDate"));
				employeeList.add(employee);
			}
		} catch (Exception e) {
			//例外発生時の処理
			e.printStackTrace(); //エラー内容をコンソールに出力する
			return null;
		} finally {
			// DBから切断する
			endDB();
		}

		return employeeList;
	}

	// 更新メソッド
	public int updateEmployee(String sql) {

		int result;

		try {
			startDB();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();

		} catch (Exception e) {
			//例外発生時の処理
			e.printStackTrace(); //エラー内容をコンソールに出力する
			return -1;
		} finally {
			// DBから切断する
			endDB();
		}

		return result;
	}
}

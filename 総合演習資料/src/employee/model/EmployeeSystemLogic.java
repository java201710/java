package employee.model;

import java.util.ArrayList;

import dao.employee.EmployeeSystemDAO;

public class EmployeeSystemLogic {
	EmployeeSystemDAO dao = new EmployeeSystemDAO();

	public ArrayList<String> register(EmployeeBean EmployeeBean) {

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

	public ArrayList<String> updateUser(EmployeeBean EmployeeBean,String lastpage) {

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


    //メソッド名：login
    //引数：EmployeeBean EmployeeBean
    //戻り値：ArrayList<String>
    //修正履歴：
	public ArrayList<String> login(EmployeeBean EmployeeBean){
		ArrayList<String> result = new ArrayList<String>();
		//入力された社員ID＋パスワードで社員情報テーブルを検索
		//%d･･･数値, %s･･･文字, %s･･･実数
		String sql = String.format("SELECT *"
				+ " FROM employee AS T1 "
					+ " INNER JOIN department AS T2 ON T1.departmentCode = T2.departmentCode"
					+ " INNER JOIN division AS T3 ON T1.divisionCode = T3.divisionCode"
					+ " INNER JOIN position AS T4 ON T1.positionCode = T4.positionCode"
					+ " INNER JOIN base AS T5 ON T2.baseCode = T5.baseCode"
				+ " WHERE T1.employeeId = %d",
				EmployeeBean.getEmployeeId());
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








}

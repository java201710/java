package employee.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import employee.model.EmployeeBean;
import employee.model.EmployeeSystemLogic;

/**
 * Servlet implementation class EmployeeSystem
 */
@WebServlet("/EmployeeSystem")
//作成：2017/10/26 向山 憲之
public class EmployeeSystem extends HttpServlet {


    public EmployeeSystem() {
    }

    //メソッド名：doGet
    //引数：HttpServletRequest request
	//		HttpServletResponse response
    //戻り値：void
    //修正履歴：2017/11/11 ○○ ××
    //修正履歴：2017/12/12 ○○ ××
    //修正履歴：2018/01/13 ○○ ××
    //修正履歴：2018/02/14 ○○ ××
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//パラメータ受け取り
		String action = request.getParameter("action");
		String page = request.getParameter("page");
		String user = request.getParameter("user");
		String selectedUser  = request.getParameter("selectedUser");
		String lastpage = request.getParameter("lastpage");

		request.setCharacterEncoding("UTF-8");

		HttpSession sessin = request.getSession();
		EmployeeSystemLogic logic = new EmployeeSystemLogic();

		String forwardPath = null;

		//ログイン情報取得
		//ログインユーザー社員ID
		int login_employeeId;
		if(sessin.getAttribute("login_employeeId")==null){
			login_employeeId = 0;
		}else{
			login_employeeId = (Integer)sessin.getAttribute("login_employeeId");
		}
		//ログインユーザー社員名
		String login_employeeName;
		if(sessin.getAttribute("login_ employeeName")==null){
			login_employeeName = "";
		}else{
			login_employeeName = (String)sessin.getAttribute("login_ employeeName");
		}
		//ログインユーザー管理者権限
		byte login_adminFlag;
		if(sessin.getAttribute("login_adminFlag")==null){
			login_adminFlag = 0;
		}else{
			login_adminFlag = (Byte)sessin.getAttribute("login_adminFlag");
		}



		//処理詳細：
		//・パラメータは無しの場合（action=nullかつpage=null）
		if(action == null && page == null){
			if(login_employeeId!=0){
				//【他画面→メイン画面(employeeSystemMain.jsp)】
				//・ログイン済みの場合（セッションスコープにログイン情報あり）
				//・社員一覧取得処理

				//・「EmployeeBean」のインスタンスを生成する
				EmployeeBean employeeBean  =new EmployeeBean();

				//・「EmployeeBean」のオブジェックトをリクエストスコープの”employeeBean”に入れる
				request.setAttribute("employeeBean", employeeBean);

				//・「EmployeeBean」をEmployeeSystemLogicの（searchメソッド）に入れて、呼び出す
				//・EmployeeSystemLogicの（searchメソッド）のＨＴＭＬをもらう
				ArrayList<String>result = logic.search(employeeBean,login_adminFlag);
				if(result.get(0).equals("")){
					//HTMLは空文字“”の場合
					//・エラーメッセージをリクエストスコープに入れる
				}else{
					//HTMLは空文字“”じゃない場合
					//・このＨＴＭＬをリクエストスコープの”html”にいれる
					request.setAttribute("html", result.get(1));
					//・employeeSystemMain.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
				}

			}else{
				//【ログイン機能】
				//【ログイン(login.jsp)】
				//・未ログインの場合（セッションスコープにログイン情報なし）

				//・パラメータ「user」を取得
				//・リクエストパラメータに「user」をセット
				request.setAttribute("user", user);
				//・login.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/login.jsp";
			}

		}



		if(action!=null){
			//【他画面→メイン画面(employeeSystemMain.jsp)】
			//・actionのパラメータによって異なる処理を行う

			if(action.equals("register")){
				//【新規登録機能】
				//【新規確認(confirmRegisterUser.jsp)→メイン画面(employeeSystemMain.jsp)】
				//action=registerの場合

				//・セッションスコープにある「EmployeeBean」をEmployeeSystemLogicの（registerメソッド）に入れて、呼び出す
				//・EmployeeSystemLogicの（registerメソッド）のメッセージをもらう
				ArrayList<String> result = logic.register((EmployeeBean)sessin.getAttribute("EmployeeBean"));

				//・このメッセージをリクエストスコープの”message”にいれる
				request.setAttribute("message", result.get(0));

				//・セッションスコープのアトリビュートを削除：
				//・「EmployeeBean」
				sessin.removeAttribute("EmployeeBean");

			}else if(action.equals("update")){
				//【修正機能】
				//【修正確認(confirmUpdateUser.jsp)→メイン画面(employeeSystemMain.jsp)】
				//action=updateの場合

				//・セッションスコープにある「EmployeeBean」をEmployeeSystemLogicの（updateメソッド）に入れて、呼び出す
				//・EmployeeSystemLogicの（updateメソッド）のメッセージをもらう
				ArrayList<String> result = logic.update((EmployeeBean)sessin.getAttribute("EmployeeBean"));

				//・このメッセージをリクエストスコープの”message”にいれる
				request.setAttribute("message", result.get(0));

				//・セッションスコープのアトリビュートを削除：
				//・「EmployeeBean」
				//・「selectedUser」
				sessin.removeAttribute("EmployeeBean");
				sessin.removeAttribute("selectedUser");


			}else if(action.equals("delete")){
				//【削除機能】
				//【削除確認(confirmDeleteUser.jsp)→メイン画面(employeeSystemMain.jsp)】
				//action=deleteの場合

				//・セッションスコープにある「selectedUser」をEmployeeSystemLogicの（deleteメソッド）に入れて、呼び出す
				//・EmployeeSystemLogicの（deleteメソッド）のメッセージをもらう
				ArrayList<String> result = logic.delete((EmployeeBean)sessin.getAttribute("EmployeeBean"));

				//・このメッセージをリクエストスコープの”message”にいれる
				request.setAttribute("message", result.get(0));

				//・セッションスコープのアトリビュートを削除：
				//・「EmployeeBean」
				//・「selectedUser」
				sessin.removeAttribute("EmployeeBean");
				sessin.removeAttribute("selectedUser");
			}

			//上記の各プロセスが終了後
			//・employeeSystemMain.jspへフォワード転送
			forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";

		}else{
			//action=nullの場合
			//pageのパラメータによって異なる処理を行う
			if(page == null){

			}else if(page.equals("registerUser")){
				//【新規登録機能】
				//【他画面→新規入力(registerUser.jsp)】
				//page=registerUserの場合

				//・registerUser.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/registerUser.jsp";

			}else if(page.equals("viewUser")){
				//【詳細情報表示機能】
				//【他画面→社員詳細情報(employeeViewer.jsp)】
				//page=viewUserの場合

				//・「selectedUser」のパラメータはある場合
				if(selectedUser!=null){
					//・「EmployeeBean」のインスタンスを宣言する
					EmployeeBean employeeBean = new EmployeeBean();

					//・「selectedUser」の値を「EmployeeBean」に入れる
					employeeBean.setEmployeeId(Integer.parseInt(selectedUser));

					//・「EmployeeBean」をEmployeeSystemLogicの（viewUserメソッド）に入れて、呼び出す
					ArrayList<String> result = logic.viewUser(employeeBean);

					//・EmployeeSystemLogicの（viewUserメソッド）のＨＴＭＬをもらう
					//HTMLは空文字“”の場合
					if(result.get(0).length()==0){
						//・エラーメッセージをリクエストスコープに入れる
						request.setAttribute("message", result.get(1));
						//・employeeSystemMain.jspへフォワード転送
						forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
					}else{
						//ＨＴＭＬは空文字“”じゃない場合
						//・「EmployeeBean」をセッションスコープの” EmployeeBean”にセットする。
						sessin.setAttribute("EmployeeBean", employeeBean);
						//・このＨＴＭＬをリクエストスコープの”html”にいれる
						request.setAttribute("html", result.get(0));
						//・employeeViewer.jspへフォワード転送
						forwardPath = "/WEB-INF/employee/employeeViewer.jsp";
					}

				}
			}else if(page.equals("updateUser")){
				//【修正機能】
				//【他画面→修正入力(updateUser.jsp)】
				//page=updateUserの場合

				//・「lastpage」のパラメータはnullの場合

				if(lastpage!=null && lastpage.length()>0){
					//・「lastpage」のパラメータはある場合
					//・「lastpage」の値をセッションスコープの” lastpage”に入れる
					sessin.setAttribute("lastpage", lastpage);
				}

				//・「lastpage」のパラメータは空文字“”の場合

				//・共通処理
				//・「EmployeeBean」のインスタンスを宣言する
				EmployeeBean employeeBean = new EmployeeBean();

				//・「selecteduser」の値を「EmployeeBean」に入れる
				employeeBean.setEmployeeId(Integer.parseInt(selectedUser));

				//・「EmployeeBean」「lastpage」をEmployeeSystemLogicの（updateUserメソッド）に入れて、呼び出す
				ArrayList<String> result = logic.updateUser(employeeBean,lastpage);

				//・EmployeeSystemLogicの（updateUserメソッド）のＨＴＭＬをもらう
				if(result.get(0).length()==0){
					//HTMLは空文字“”の場合
					//・エラーメッセージをリクエストスコープに入れる
					request.setAttribute("message", result.get(1));
					//・employeeSystemMain.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
				}else{
					//ＨＴＭＬは空文字“”じゃない場合
					//・このＨＴＭＬをリクエストスコープの”html”にいれる
					request.setAttribute("html", result.get(0));
					//・updateUser.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/updateUser.jsp";
				}

			}else if(page.equals("deleteUser")){
				//【削除機能】
				//【他画面→削除確認(confirmDeleteUser.jsp)】
				//page=deleteUserの場合

				if(lastpage!=null && lastpage.length()>0){
					//・「lastpage」のパラメータはある場合
					//・「lastpage」の値をセッションスコープの”lastpage”に入れる
					sessin.setAttribute("lastpage", lastpage);
				}

				//・「lastpage」のパラメータは空文字“”の場合

				//・共通処理
				//・「EmployeeBean」のインスタンスを宣言する
				EmployeeBean employeeBean = new EmployeeBean();

				//・「selecteduser」の値を「EmployeeBean」に入れる
				employeeBean.setEmployeeId(Integer.parseInt(selectedUser));

				//・「EmployeeBean」をEmployeeSystemLogicの（deleteメソッド）に入れて、呼び出す
				ArrayList<String> result = logic.delete(employeeBean);

				//・EmployeeSystemLogicの（deleteUserメソッド）のＨＴＭＬをもらう
				if(result.get(0).length()==0){
					//HTMLは空文字“”の場合
					//・エラーメッセージをリクエストスコープに入れる
					request.setAttribute("message", result.get(1));
					//・employeeSystemMain.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
				}else{
					//ＨＴＭＬは空文字“”じゃない場合
					//・このＨＴＭＬをリクエストスコープの”html”にいれる
					request.setAttribute("html", result.get(0));
					//・confirmDeleteUser.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/confirmDeleteUser.jsp";
				}
			}else if(page.equals("login")){
				//【ログアウト機能】
				//【他画面→ログイン確認(login.jsp)】
				//page=loginの場合

				//セッションスコープからログイン情報を削除
				//「login_ employeeId」, 「login_ employeeName」, 「login_adminFlag」
				sessin.removeAttribute("login_ employeeId");
				sessin.removeAttribute("login_ employeeName");
				sessin.removeAttribute("login_adminFlag");

				//login.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/login.jsp";
			}

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}


    //メソッド名：doPost
    //引数：HttpServletRequest request
	//    	HttpServletResponse response
    //戻り値：void
    //修正履歴：
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession sessin = request.getSession();
		EmployeeSystemLogic logic = new EmployeeSystemLogic();

		String forwardPath = null;

		//ログイン情報取得
		//ログインユーザー社員ID
		int login_employeeId;
		if(sessin.getAttribute("login_employeeId")==null){
			login_employeeId = 0;
		}else{
			login_employeeId = (Integer)sessin.getAttribute("login_employeeId");
		}
		//ログインユーザー社員名
		String login_employeeName;
		if(sessin.getAttribute("login_ employeeName")==null){
			login_employeeName = "";
		}else{
			login_employeeName = (String)sessin.getAttribute("login_ employeeName");
		}
		//ログインユーザー管理者権限
		byte login_adminFlag;
		if(sessin.getAttribute("login_adminFlag")==null){
			login_adminFlag = 0;
		}else{
			login_adminFlag = (Byte)sessin.getAttribute("login_adminFlag");
		}

		//処理詳細：
		//・ログインフォームのPOSTでの呼び出しに対応
		//下記のパラメータを受け取る
		//String employeeId, password, action, user, page
		//
		//・検索フォームのPOSTでの呼び出しに対応
		//下記のパラメータを受け取る
		//String baseName, departmentName, divisionName, positionName, fromDate, toDate, action
		//
		//・新規登録フォームのPOSTでの呼び出しに対応
		//下記のパラメータを受け取る
		//String employeeId, password, employeeName, kana, gender, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, action
		//
		//・修正フォームのPOSTでの呼び出しに対応
		//管理用フォームから下記のパラメータを受け取る
		//String　oldPassword, newPassword, employeeName, kana, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, adminFlag, action
		//一般用フォームから下記のパラメータを受け取る
		//String oldPassword, newPassword, action

		String employeeId = request.getParameter("employeeId");
		String password = request.getParameter("password");
		String action = request.getParameter("action");
		String user = request.getParameter("user");
		String page = request.getParameter("page");
		String baseName = request.getParameter("baseName");
		String departmentName = request.getParameter("departmentName");
		String divisionName = request.getParameter("divisionName");
		String positionName = request.getParameter("positionName");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		String employeeName = request.getParameter("employeeName");
		String kana = request.getParameter("kana");
		String gender = request.getParameter("gender");
		String positionMemo = request.getParameter("positionMemo");
		String naisenNumber = request.getParameter("naisenNumber");
		String publicCellphoneNumber = request.getParameter("publicCellphoneNumber");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String adminFlag = request.getParameter("adminFlag");

		//・actionのパラメータによって異なる処理を行う

		if(action.equals("login")){
			//【ログイン機能】
			//・action=loginの場合
			//・ログインフォームのパラメータを受け取る
			//・入力したパラメータとデータベースの一致を探す

			//・「EmployeeBean」のインスタンスを宣言する
			EmployeeBean checkEmployeeBean = new EmployeeBean();

			//・パラメータ「employeeId」、「password」の値を「EmployeeBean」に入れる
			checkEmployeeBean.setEmployeeId(Integer.parseInt(employeeId));
			checkEmployeeBean.setPassword(password);

			//・「EmployeeBean」をEmployeeSystemLogicの（loginメソッド）に入れて、呼び出す
			//・戻り値を「EmployeeBean」にセット
			ArrayList<String> loginResult = logic.login(checkEmployeeBean);

			if(loginResult.size() <= 1){
				//【ログイン失敗】
				//・戻り値＝nullの場合
				//※正しく入力してない場合、エラーメッセージをリクエストスコープに入れて、login.jspへフォワード転送
				request.setAttribute("message", loginResult.get(0));
				forwardPath = "/WEB-INF/employee/login.jsp";
			}else{
				//【ログイン成功】
				//・ログインした社員の社員ID、名前と権限をセッションスコープに入れる
				//「login_ employeeId」, 「login_ employeeName」, 「login_adminFlag」
				sessin.setAttribute("login_ employeeId", loginResult.get(1));
				sessin.setAttribute("login_ employeeName", loginResult.get(2));
				sessin.setAttribute("login_adminFlag", loginResult.get(3));

				if(user.length()==0){
					//【ログイン(login.jsp)→メイン画面(employeeSystemMain.jsp)】
					//※「hiddenのuser」ペラメータが空文字“”の場合：

					//・employeeSystemMain.jspへフォワード転送
					forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
				}else{
					//【ログイン(login.jsp)→社員詳細(employeeViewer.jsp)】
					//※「hiddenのuser」ペラメータが空文字“”じゃないの場合：

					//・「EmployeeBean」のインスタンスを宣言する
					EmployeeBean employeeBean = new EmployeeBean();

					//・「user」の値を「EmployeeBean」に入れる
					employeeBean.setEmployeeId(Integer.parseInt(user));

					//・「EmployeeBean」をEmployeeSystemLogicの（viewUserメソッド）に入れて、呼び出す
					//EmployeeSystemLogicの（viewUserメソッド）のＨＴＭＬをもらう
					ArrayList<String> result = logic.viewUser(employeeBean);

					if(result.get(0).length()==0){
						//HTMLは空文字“”の場合
						//・エラーメッセージをリクエストスコープに入れる
						request.setAttribute("message", result.get(1));
						//・employeeSystemMain.jspへフォワード転送
						forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";
					}else{
						//ＨＴＭＬは空文字“”じゃない場合
						//・このＨＴＭＬをリクエストスコープの”html”にいれる
						request.setAttribute("html", result.get(0));
						//・employeeViewer.jspへフォワード転送
						forwardPath = "/WEB-INF/employee/employeeViewer.jsp";
					}
				}
			}


		}else if(action.equals("search")){
			//【検索機能】
			//・action=searchの場合

			//・検索フォームのパラメータを受け取る
			//・「EmployeeBean」に検索フォームのパラメータを入れる
			//baseName, departmentName, divisionName, positionName, fromDate, toDate, action
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setBaseName(baseName);
			employeeBean.setDepartmentName(departmentName);
			employeeBean.setDivisionName(divisionName);
			employeeBean.setPositionName(positionName);
			employeeBean.setFromDate(Integer.parseInt(fromDate));
			employeeBean.setToDate(Integer.parseInt(toDate));

			//・「EmployeeBean」のオブジェックトをリクエストスコープの”employeeBean”に入れる
			request.setAttribute("employeeBean", employeeBean);

			//・「EmployeeBean」をEmployeeSystemLogicの（searchメソッド）に入れて、呼び出す
			//・EmployeeSystemLogicの（searchメソッド）のＨＴＭＬをもらう
			ArrayList<String> result = logic.search(employeeBean,login_adminFlag);

			if(result.get(0).length()==0){
				//HTMLは空文字“”の場合
				//・エラーメッセージをリクエストスコープに入れる
				request.setAttribute("message", result.get(1));
			}else{
				//HTMLは空文字“”じゃない場合
				//・このＨＴＭＬをリクエストスコープの”html”にいれる
				request.setAttribute("html", result.get(0));
			}
			//・employeeSystemMain.jspへフォワード転送
			forwardPath = "/WEB-INF/employee/employeeSystemMain.jsp";


		}else if(action.equals("confirmNewUser")){
			//【新規登録機能】
			//【新規入力(registerUser.jsp)→新規確認(confirmRegisterUser.jsp)】
			//・action=confirmNewUserの場合

			//・新規登録フォームのパラメータを受け取る
			//・「EmployeeBean」のインスタンスを作って、受け取ったパラメータをインスタンスに入れる。
			//employeeId, password, employeeName, kana, gender, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, action
			EmployeeBean employeeBean = new EmployeeBean();
			employeeBean.setEmployeeId(Integer.parseInt(employeeId));
			employeeBean.setPassword(password);
			employeeBean.setEmployeeName(employeeName);
			employeeBean.setKana(kana);
			employeeBean.setGender(gender);
			employeeBean.setDepartmentName(departmentName);
			employeeBean.setDivisionName(divisionName);
			employeeBean.setPositionName(positionName);
			employeeBean.setPositionMemo(positionMemo);
			employeeBean.setNaisenNumber(naisenNumber);
			employeeBean.setPublicCellphoneNumber(publicCellphoneNumber);

			//・「EmployeeBean」をEmployeeSystemLogicの（confirmNewUserメソッド）に入れて、呼び出す
			//・EmployeeSystemLogicの（confirmNewUserメソッド）のＨＴＭＬをもらう
			ArrayList<String> result = logic.confirmNewUser(employeeBean);

			if(result.get(0).length()==0){
				//HTMLは空文字“”の場合（エラーあり）
				//・エラーメッセージをリクエストスコープに入れる
				request.setAttribute("message", result.get(1));
				//・registerUser.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/registerUser.jsp";
			}else{
				//HTMLは空文字“”じゃない場合（エラーなし）
				//・このＨＴＭＬをリクエストスコープの”html”にいれる
				request.setAttribute("html", result.get(0));
				//・confirmRegisterUser.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/confirmRegisterUser.jsp";
			}

		}else if(action.equals("confirmUpdateUser")){
			//【修正機能】
			//【修正入力(updateUser.jsp)→修正確認(confirmUpdateUser.jsp)】
			//・action=confirmUpdateUserの場合

			//・修正フォームのパラメータを受け取る
			//・「EmployeeBean」のインスタンスを作って、受け取ったパラメータをインスタンスに入れる
			EmployeeBean employeeBean = new EmployeeBean();
			if(login_adminFlag==0){
				//ログインユーザーに管理者権限なし
				//一般用→String oldPassword, newPassword, action
				employeeBean.setPassword(newPassword);
			}else{
				//ログインユーザーに管理者権限あり
				//管理用→String　oldPassword, newPassword, employeeName, kana, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, adminFlag, action
				employeeBean.setPassword(newPassword);
				employeeBean.setEmployeeName(employeeName);
				employeeBean.setKana(kana);
				employeeBean.setGender(gender);
				employeeBean.setDepartmentName(departmentName);
				employeeBean.setDivisionName(divisionName);
				employeeBean.setPositionName(positionName);
				employeeBean.setPositionMemo(positionMemo);
				employeeBean.setNaisenNumber(naisenNumber);
				employeeBean.setPublicCellphoneNumber(publicCellphoneNumber);
			}

			//・「EmployeeBean」をEmployeeSystemLogicの（confirmUpdateUserメソッド）に入れて、呼び出す
			//・EmployeeSystemLogicの（confirmUpdateUserメソッド）のＨＴＭＬをもらう
			ArrayList<String> result = logic.confirmUpdateUser(employeeBean);

			if(result.get(0).length()==0){
				//HTMLは空文字“”の場合（エラーあり）
				//・エラーメッセージをリクエストスコープに入れる
				request.setAttribute("message", result.get(1));
				//・updateUser.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/updateUser.jsp";
			}else{
				//HTMLは空文字“”じゃない場合（エラーなし）
				//・このＨＴＭＬをリクエストスコープの”html”にいれる
				request.setAttribute("html", result.get(0));
				//・confirmUpdateUser.jspへフォワード転送
				forwardPath = "/WEB-INF/employee/confirmUpdateUser.jsp";
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}

}

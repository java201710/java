package employee.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    //修正：2017/11/11 ○○ ××
    //修正：2017/12/12 ○○ ××
    //修正：2018/01/13 ○○ ××
    //修正：2018/02/14 ○○ ××
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null){
			action = "";
		}
		String page = request.getParameter("page");
		if(page == null){
			page = "";
		}

//		処理詳細：
//		・URL、ハイパーリンクでの呼び出しに対応
//
//		・パラメータは無しの場合
//				・employeeSystemMain.jspへフォワード転送

		if(!action.equals("")){
			//
//			・actionのパラメータによって異なる処理を行う
			if(action.equals("register")){
	//			action=registerの場合
	//	・セッションスコープにある「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
	//	・EmployeeSystemLogicの（…メソッド）のメッセージをもらう
	//	・このメッセージをリクエストスコープの”message”にいれる
	//	・セッションスコープのアトリビュートを削除：
	//	・「EmployeeBean」
			}else if(action.equals("updateUser")){
	//			action=updateUserの場合
	//・セッションスコープにある「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
	//・EmployeeSystemLogicの（…メソッド）のメッセージをもらう
	//・このメッセージをリクエストスコープの”message”にいれる
	//・セッションスコープのアトリビュートを削除：
	//・「EmployeeBean」
	//・「selectedUser」
			}else if(action.equals("deleteUser")){

	//			action=deleteUserの場合
	//・セッションスコープにある「selectedUser」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
	//・EmployeeSystemLogicの（…メソッド）のメッセージをもらう
	//・このメッセージをリクエストスコープの”message”にいれる
	//・セッションスコープのアトリビュートを削除：
	//・「EmployeeBean」
	//・「selectedUser」
			}
			//
//			上記の各プロセスが終了後
//			・employeeSystemMain.jspへフォワード転送
		}else{
//			action=nullの場合
//pageのパラメータによって異なる処理を行う

			if(page.equals("registerUser")){
				//page=registerUserの場合
//				・registerUser.jspへフォワード転送
			//
			}else if(page.equals("viewUser")){
				//page=viewUserの場合
//				・「selectedUser」のパラメータはある場合
			//・「EmployeeBean」のインスタンスを宣言する
			//・「user」の値を「EmployeeBean」に入れる
			//・「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
			//・EmployeeSystemLogicの（…メソッド）のＨＴＭＬをもらう
//						HTMLは空文字“”の場合
			//・エラーメッセジ―をリクエストスコープに入れる
			//・employeeSystemMain.jspへフォワード転送
//										ＨＴＭＬは空文字“”じゃない場合
			//・このＨＴＭＬをリクエストスコープの”html”にいれる
			//・employeeViewer.jspへフォワード転送
			//
			}else if(page.equals("updateUser")){
//				page=updateUserの場合
//				・「lastpage」のパラメータはnullの場合
//					・（なにもしない）
//					・updateUser.jspへフォワード転送
//				・「lastpage」のパラメータはある場合
//・「lastpage」の値をセッションスコープの”lastpage”に入れる
//・updateUser.jspへフォワード転送・
//				・「lastpage」のパラメータは空文字“”の場合
//・「EmployeeBean」のインスタンスを宣言する
//・「selecteduser」の値を「EmployeeBean」に入れる
//・「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
//・EmployeeSystemLogicの（…メソッド）のＨＴＭＬをもらう
//		HTMLは空文字“”の場合
//・エラーメッセジ―をリクエストスコープに入れる
//・employeeSystemMain.jspへフォワード転送
//						ＨＴＭＬは空文字“”じゃない場合
//・このＨＴＭＬをリクエストスコープの”html”にいれる
//・updateUser.jspへフォワード転送
//
			}else if(page.equals("deleteUser")){
//				page=deleteUserの場合
//・「lastpage」のパラメータはnullの場合
//						・（なにもしない）
//						・confirmDeleteUser.jspへフォワード転送
//					・「lastpage」のパラメータはある場合
//・「lastpage」の値をセッションスコープの”lastpage”に入れる
//・confirmDeleteUser.jspへフォワード転送・
//					・「lastpage」のパラメータは空文字“”の場合
//・「EmployeeBean」のインスタンスを宣言する
//・「selecteduser」の値を「EmployeeBean」に入れる
//・「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
//・EmployeeSystemLogicの（…メソッド）のＨＴＭＬをもらう
//			HTMLは空文字“”の場合
//・エラーメッセジ―をリクエストスコープに入れる
//・employeeSystemMain.jspへフォワード転送
//							ＨＴＭＬは空文字“”じゃない場合
//・このＨＴＭＬをリクエストスコープの”html”にいれる
//・confirmDeleteUser.jspへフォワード転送
			}





		}




	}


    //メソッド名：doPost
    //引数：HttpServletRequest request
	//    	HttpServletResponse response
    //戻り値：void
    //修正：
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null){
			action = "";
		}
//		処理詳細：
//		・ログインフォームのPOSTでの呼び出しに対応
//		下記のパラメータを受け取る
//		String employeeId, password, action, user, page
//
//		・検索フォームのPOSTでの呼び出しに対応
//		下記のパラメータを受け取る
//		String baseName, departmentName, divisionName, positionName, fromDate, toDate, action
//
//		・新規登録フォームのPOSTでの呼び出しに対応
//		下記のパラメータを受け取る
//		String employeeId, password, employeeName, kana, gender, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, action
//
//		・修正フォームのPOSTでの呼び出しに対応
//		管理用フォームから下記のパラメータを受け取る
//		String　oldPassword, newPassword, employeeName, kana, departmentName, divisionName, positionName, positionMemo, naisenNumber, publicCellphoneNumber, adminFlag, action
//		一般用フォームから下記のパラメータを受け取る
//				String oldPassword, newPassword, action
//
//
//		・actionのパラメータによって異なる処理を行う


		if(action.equals("login")){
//			・action=loginの場合
//			・ログインフォームのパラメータを受け取る
//		・入力したパラメータとデータベースの一致を探す
//		※正しく入力してない場合、エラーメッセジ―をリクエストスコープに入れて、login.jspへフォワード転送
//		・ログインした社員の社員ＩＤと権限にセッションスコープに入れる
//		※「hiddenのuser」ペラメータが空文字“”の場合：
//		・employeeSystemMain.jspへフォワード転送
//					※「hiddenのuser」ペラメータが空文字“”じゃないの場合：
//						・「EmployeeBean」のインスタンスを宣言する
//		・「user」の値を「EmployeeBean」に入れる
//		・「EmployeeBean」をEmployeeSystemLogicの（…メソッド）に入れて、呼び出す
//		EmployeeSystemLogicの（…メソッド）のＨＴＭＬをもらう
//			HTMLは空文字“”の場合
//		・エラーメッセジ―をリクエストスコープに入れる
//		・employeeSystemMain.jspへフォワード転送
//							ＨＴＭＬは空文字“”じゃない場合
//		・このＨＴＭＬをリクエストスコープの”html”にいれる
//		・employeeViewer.jspへフォワード転送
//
		}else if(action.equals("search")){
//			・action=searchの場合
//			・検索フォームのパラメータを受け取る
//			・「EmployeeBean」に検索フォームのパラメータを入れる
//		・「EmployeeBean」のオブジェックトをリクエストスコープの”employeeBean”に入れる
//		employeeSystemMain.jspへフォワード転送
		}else if(action.equals("confirmNewUser")){
//			・action=confirmNewUserの場合
//			・新規登録フォームのパラメータを受け取る
//		・「EmployeeBean」のインスタンスを作って、受け取ったパラメータをインスタンスに入れて、内容を確認する
//		※正しく入力する場合：
//		・「EmployeeBean」のオブジェックトをリクエストスコープの”employeeBean”に入れる
//		・confirmRegisterUser.jspへフォワード転送
//		※正しく入力しない場合：
//		・エラーメッセジ―をリクエストスコープの”message”に入れる
//		・registerUser.jspへフォワード転送
		}else if(action.equals("confirmUpdateUser")){
//			・action=confirmUpdateUserの場合
//			・修正フォームのパラメータを受け取る
//		・「EmployeeBean」のインスタンスを作って、受け取ったパラメータをインスタンスに入れて、内容を確認する
//			※正しく入力する場合：
//		・「EmployeeBean」のオブジェックトをセッションスコープの”employeebean”に入れる
//				・confirmUpdateUser.jspへフォワード転送
//		※正しく入力しない場合：
//		・エラーメッセジ―をリクエストスコープの”message”に入れる
//		・updateUser.jspへフォワード転送
		}






	}

}

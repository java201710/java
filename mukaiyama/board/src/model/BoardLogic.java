package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class BoardLogic {
	private static String adminPass = "root00";	//管理用パスワード

	//新規投稿ボタン用
	public ArrayList<String> add(BoardBean boardBean){
		ArrayList<String> message = new ArrayList<String>();
		boolean normalEnd = true;	//正常終了

		//入力内容チェック
		if(boardBean.getComment()==null || boardBean.getComment().length()==0){
			message.add("コメントを入力してください。");
			normalEnd = false;
		}

		//編集
		if(normalEnd==true){
			if(boardBean.getName()==null || boardBean.getName().length()==0){
				boardBean.setName("ゲスト");	//名前
			}
			if(boardBean.getEmail()==null || boardBean.getEmail().length()==0){
				boardBean.setEmail("なし");		//E-Mail
			}
			Date now = new Date();
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			boardBean.setDateTime(f.format(now));	//投稿日時

			message.add("投稿しました。");
		}

		return message;
	}

	//送信ボタン（掲示板メイン）用
	public ArrayList<String> admin(String pass){
		ArrayList<String> message = new ArrayList<String>();

		//入力内容チェック
		if(pass.equals(adminPass)==false){
			message.add("パスワードが違います。");
		}else{
			message.add("");
		}

		return message;
	}

	//送信ボタン（掲示板管理）用
	public ArrayList<String> del(String id, ArrayList<BoardBean> boardList){
		ArrayList<String> message = new ArrayList<String>();
		boolean normalEnd = false;	//異常終了

		//入力内容チェック
		Pattern pattern = Pattern.compile("[0-9]");
		if(pattern.matcher(id).matches()){
			int intId = Integer.parseInt(id);
			for(BoardBean b:boardList){
				if(b.getId()==intId){
					normalEnd = true;
					message.add("投稿を削除しました。");
					break;
				}
			}
		}
		if(normalEnd == false){
			message.add("IDが違います！存在するIDを半角数字で入力して下さい。");
		}

		return message;
	}
}

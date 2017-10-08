package JyankenObj;

public class Janken_obj {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		//Playerのインスタンス
		Player P1 = new Player("人間",true);
		Player P2 = new Player("コンピューターA",false);
		Player P3 = new Player("コンピューターB",false);
		Player P4 = new Player("コンピューターC",false);
		Player P5 = new Player("コンピューターD",false);
//		Player P6 = new Player("コンピューターE",false);
//		Player P7 = new Player("コンピューターF",false);
//		Player P8 = new Player("コンピューターG",false);
//		Player P9 = new Player("コンピューターH",false);
//		Player P10 = new Player("コンピューターI",false);

		//Judgeのコンストラクタに渡すPlayer型配列の作成
//		Player[] Players = new Player[]{P1,P2,P3};
		Player[] Players = new Player[]{P1,P2,P3,P4,P5};
//		Player[] Players = new Player[]{P1,P2,P3,P4,P5,P6,P7,P8,P9,P10};

		//Judgeのインスタンス
		//Judgeに参加人数を教える→Playerクラスのstaticフィールドに保持
		Judge J1 = new Judge(Players);

		System.out.println("<<<<<ジャンケンを開始します。>>>>>");

		//ジャンケン開始
		J1.fight();

		System.out.println("<<<<<ジャンケンを終了します。>>>>>");
	}

}

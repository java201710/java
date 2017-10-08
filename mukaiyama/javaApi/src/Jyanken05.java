public class Jyanken05 {
	//じゃんけんプログラム05
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String Jyanken[] = { "グー", "チョキ", "パー" };

		String Result[][][] = new String[3][3][3];
		Result[0][0][0] = "アイコ";
		Result[0][0][1] = "AとBの勝ち";
		Result[0][0][2] = "Playerの勝ち";
		Result[0][1][0] = "AとPlayerの勝ち";
		Result[0][1][1] = "Aの勝ち";
		Result[0][1][2] = "アイコ";
		Result[0][2][0] = "Bの勝ち";
		Result[0][2][1] = "アイコ";
		Result[0][2][2] = "BとPlayerの勝ち";
		Result[1][0][0] = "BとPlayerの勝ち";
		Result[1][0][1] = "Bの勝ち";
		Result[1][0][2] = "アイコ";
		Result[1][1][0] = "Playerの勝ち";
		Result[1][1][1] = "アイコ";
		Result[1][1][2] = "AとBの勝ち";
		Result[1][2][0] = "アイコ";
		Result[1][2][1] = "AとPlayerの勝ち";
		Result[1][2][2] = "Aの勝ち";
		Result[2][0][0] = "Aの勝ち";
		Result[2][0][1] = "アイコ";
		Result[2][0][2] = "AとPlayerの勝ち";
		Result[2][1][0] = "アイコ";
		Result[2][1][1] = "BとPlayerの勝ち";
		Result[2][1][2] = "Bの勝ち";
		Result[2][2][0] = "AとBの勝ち";
		Result[2][2][1] = "Playerの勝ち";
		Result[2][2][2] = "アイコ";

		int intA = 0;
		int intB = 0;
		int intPlayer = 0;

		int winCntA = 0;
		int winCntB = 0;
		int winCntPlayer = 0;

		int roundCnt = 0;

		do {
			System.out.println("【"+ ++roundCnt + "回戦】");
			intA = new java.util.Random().nextInt(3);
			intB = new java.util.Random().nextInt(3);
			System.out.println("じゃんけんの手を入力してください。");
			System.out.println("（0:グー / 1:チョキ / 2:パー）");
			intPlayer = new java.util.Scanner(System.in).nextInt();

			System.out.println("  CPU-A→ " + intA + ":" + Jyanken[intA]);
			System.out.println("  CPU-B→ " + intB + ":" + Jyanken[intB]);
			System.out.println("  Player→ " + intPlayer + ":" + Jyanken[intPlayer]);

			System.out.println("結果は、" + Result[intA][intB][intPlayer] + "です。");

			if(Result[intA][intB][intPlayer].indexOf("A")!= -1){
				winCntA++;
			}
			if(Result[intA][intB][intPlayer].indexOf("B")!= -1){
				winCntB++;
			}
			if(Result[intA][intB][intPlayer].indexOf("Player")!= -1){
				winCntPlayer++;
			}
			System.out.println("  CPU-A: " + winCntA + "勝");
			System.out.println("  CPU-B: " + winCntB + "勝");
			System.out.println("  Player: " + winCntPlayer + "勝");
			System.out.println();

		} while (winCntA<3 && winCntB<3 && winCntPlayer<3);
		System.out.println("終了します。");
	}

}

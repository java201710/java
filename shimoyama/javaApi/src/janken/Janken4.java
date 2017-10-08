package janken;
public class Janken4 {
	public static void main(String[] args) {

		//各変数の初期化
		String hand[] = { "グー", "チョキ", "パー" };
		String judge[][][] = {

				//judge[あなた][COM A][COM B]
				//あなたがグー(0)の場合
				{ { "あいこ", "あなたとCOM Aの勝ち", "COM Bの勝ち" },
				{ "あなたとCOM Bの勝ち", "あなたの勝ち", "あいこ" },
				{ "COM Aの勝ち", "あいこ", "COM AとCOM Bの勝ち" } },
				//あなたがチョキ(1)の場合
				{ { "COM AとCOM Bの勝ち", "COM Aの勝ち", "あいこ" },
				{ "COM Bの勝ち", "あいこ", "あなたとCOM Aの勝ち" },
				{ "あいこ", "あなたとCOM Bの勝ち", "あなたの勝ち" } },
				//あなたがパー(2)の場合
				{ { "あなたの勝ち", "あいこ", "あなたとCOM Bの勝ち" },
				{ "あいこ", "COM AとCOM Bの勝ち", "COM Aの勝ち" },
				{ "あなたとCOM Aの勝ち", "COM Bの勝ち", "あいこ" } }
		};

		int comA, comB, player;

		do {
			//じゃんけんの手を決める
			comA = (int) (Math.random() * 3);
			comB = (int) (Math.random() * 3);
			System.out.println("出す手を入力してください。");
			System.out.println("(0:グー/1:チョキ/2:パーを数字で入力)");
			player = new java.util.Scanner(System.in).nextInt();

			//じゃんけんの手を出す
			System.out.println("あなたの手は" + hand[player]);
			System.out.println("COM Aの手は" + hand[comA]);
			System.out.println("COM Bの手は" + hand[comB]);

			//勝者発表
			System.out.println("結果は、" + judge[player][comA][comB] + "です");

		} while (judge[player][comA].equals("あいこ"));
	}
}
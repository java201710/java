package janken;
public class Janken2 {
	public static void main(String[] args) {

		//各変数の初期化
		String hand[] = { "グー", "チョキ", "パー" };
		String judge[][] = { { "あいこ", "あなたの勝ち", "COM Aの勝ち" },
							 { "COM Aの勝ち", "あいこ", "あなたの勝ち" },
							 { "あなたの勝ち", "COM Aの勝ち", "あいこ" } };
		int comA, player;

		do {
			//じゃんけんの手を決める
			comA = (int) (Math.random() * 3);
			System.out.println("出す手を入力してください。");
			System.out.println("(0:グー/1:チョキ/2:パーを数字で入力)");
			player = new java.util.Scanner(System.in).nextInt();

			//じゃんけんの手を出す
			System.out.println("あなたの手は"+hand[player]);
			System.out.println("COM Aの手は"+hand[comA]);

			//勝者発表
			System.out.println("結果は、" + judge[player][comA] + "です");

		} while (judge[player][comA].equals("あいこ"));
	}
}
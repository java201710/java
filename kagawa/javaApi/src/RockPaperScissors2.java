public class RockPaperScissors2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int a;
		int b;
		int you;
		int winA = 0;
		int winB = 0;
		int winYou = 0;

		do {
			int aiko = 0;

			do {

				if (aiko != 0)
					System.out.println("アイコ");

				a = (int) (Math.random() * 3);
				b = (int) (Math.random() * 3);
				do {
					System.out.println("0または1または2でじゃんけんの手を出してください。");
					you = new java.util.Scanner(System.in).nextInt();
				} while (you != 0 && you != 1 && you != 2);

				//Aの出した手を表示
				if (a == 0)
					System.out.println("Aグー");
				else if (a == 1)
					System.out.println("Aチョキ");
				else
					System.out.println("Aパー");

				//Bの出した手を表示
				if (b == 0)
					System.out.println("Bグー");
				else if (b == 1)
					System.out.println("Bチョキ");
				else
					System.out.println("Bパー");

				//人間の出した手を表示
				if (you == 0)
					System.out.println("あなたグー");
				else if (you == 1)
					System.out.println("あなたチョキ");
				else
					System.out.println("あなたパー");

				aiko++;

			} while ((a == b && b == you && you == a) || (a != b && b != you && you != a));

			//3人のじゃんけん結果
			if (a == b) {
				if ((a == 0 && you == 1) || (a == 1 && you == 2) || (a == 2 && you == 0)) {
					winA++;
					winB++;
					System.out.println("勝者はAとB");
				}
				else {
					winYou++;
					System.out.println("勝者はあなた");
				}
			}
			else if (b == you) {
				if ((a == 0 && b == 1) || (a == 1 && b == 2) || (a == 2 && b == 0)) {
					winA++;
					System.out.println("勝者はA");
				}
				else {
					winB++;
					winYou++;
					System.out.println("勝者はBとあなた");
				}
			}
			else {
				if ((a == 0 && b == 1) || (a == 1 && b == 2) || (a == 2 && b == 0)) {
					winA++;
					winYou++;
					System.out.println("勝者はAとあなた");
				}
				else {
					winB++;
					System.out.println("勝者はB");
				}
			}

			System.out.println("Aの点数は" + winA + "、Bの点数は" + winB + "、あなたの点数は" + winYou);

		} while (winA < 3 && winB < 3 && winYou < 3);

	}
}

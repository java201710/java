public class RockPaperScissors {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int winA = 0;
		int winB = 0;
		int winYou = 0;

		do {
			int a = (int) (Math.random() * 3);
			int b = (int) (Math.random() * 3);
			System.out.println("じゃんけんの手を出してください。");
			int you = new java.util.Scanner(System.in).nextInt();
			while (you != 0 && you != 1 && you != 2) {
				System.out.println("0または1または2でじゃんけんの手を出してください。");
				you = new java.util.Scanner(System.in).nextInt();
			}

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

			//結果を表示
			//アイコなら勝者が確定するまで繰り返す
			while ((a == b && b == you && you == a) || (a != b && b != you && you != a)) {
				System.out.println("アイコ");
				a = (int) (Math.random() * 3);
				b = (int) (Math.random() * 3);
				System.out.println("じゃんけんの手を出してください。");
				you = new java.util.Scanner(System.in).nextInt();
				while (you != 0 && you != 1 && you != 2) {
					System.out.println("0または1または2でじゃんけんの手を出してください。");
					you = new java.util.Scanner(System.in).nextInt();
				}

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
			}

			//3人のじゃんけん結果
			if (a == 0) {
				if (b == 0 && you == 1) {
					winA++;
					winB++;
					System.out.println("勝者はAとB");
				}
				else if (b == 0 && you == 2) {
					winYou++;
					System.out.println("勝者はあなた");
				}
				else if (b == 1 && you == 0) {
					winA++;
					winYou++;
					System.out.println("勝者はAとあなた");
				}
				else if (b == 1 && you == 1) {
					winA++;
					System.out.println("勝者はA");
				}
				else if (b == 2 && you == 0) {
					winB++;
					System.out.println("勝者はB");
				}
				else if (b == 2 && you == 2) {
					winB++;
					winYou++;
					System.out.println("勝者はBとあなた");
				}
			}
			else if (a == 1) {
				if (b == 0 && you == 0) {
					winB++;
					winYou++;
					System.out.println("勝者はBとあなた");
				}
				else if (b == 0 && you == 1) {
					winB++;
					System.out.println("勝者はB");
				}
				else if (b == 1 && you == 0) {
					winYou++;
					System.out.println("勝者はあなた");
				}
				else if (b == 1 && you == 2) {
					winA++;
					winB++;
					System.out.println("勝者はAとB");
				}
				else if (b == 2 && you == 1) {
					winA++;
					winYou++;
					System.out.println("勝者はAとあなた");
				}
				else if (b == 2 && you == 2) {
					winA++;
					System.out.println("勝者はA");
				}
			}
			else {
				if (b == 0 && you == 0) {
					winA++;
					System.out.println("勝者はA");
				}
				else if (b == 0 && you == 2) {
					winA++;
					winYou++;
					System.out.println("勝者はAとあなた");
				}
				else if (b == 1 && you == 1) {
					winB++;
					winYou++;
					System.out.println("勝者はBとあなた");
				}
				else if (b == 1 && you == 2) {
					winB++;
					System.out.println("勝者はB");
				}
				else if (b == 2 && you == 0) {
					winA++;
					winB++;
					System.out.println("勝者はAとB");
				}
				else if (b == 2 && you == 1) {
					winYou++;
					System.out.println("勝者はあなた");
				}
			}

			System.out.println("Aの点数は" + winA + "、Bの点数は" + winB + "、あなたの点数は" + winYou);

			/*2人のじゃんけん結果
			if ((a == 0 && you == 1) || (a == 1 && you == 2) || (a == 2 && you == 0))
				System.out.println("勝者はA");
			else
				System.out.println("勝者はあなた");
				*/
		} while (winA < 3 && winB < 3 && winYou < 3);

	}
}

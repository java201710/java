public class JyakenPart4 {

	public static void main(String[] args) {
		int computerA, computerB;
		int player;
		String outputA;
		String outputB;
		String outputC;

		do {
			computerA = (int) (Math.random() * 3);
			computerB = (int) (Math.random() * 3);
			System.out.print("数字を入力してください[0-2]：");
			player = new java.util.Scanner(System.in).nextInt();

			while (player > 3 || player < 0) {
				System.out.print("正しい数字を入力してください[0-2]：");
				player = new java.util.Scanner(System.in).nextInt();
			}

			if (computerA == 0) {
				outputA = "グー";
			} else if (computerA == 1) {
				outputA = "チョキ";
			} else {
				outputA = "パー";
			}

			if (computerB == 0) {
				outputB = "グー";
			} else if (computerB == 1) {
				outputB = "チョキ";
			} else {
				outputB = "パー";
			}

			if (player == 0) {
				outputC = "グー";
			} else if (player == 1) {
				outputC = "チョキ";
			} else {
				outputC = "パー";
			}

			System.out.println("コンピュータAの手は：" + outputA);
			System.out.println("コンピュータBの手は：" + outputB);
			System.out.println("プレイヤーの手は：" + outputC);

			if (outputA.equals(outputB) && outputA.equals(outputC)) {
				System.out.println("結果はアイコです");
			} else if (!outputA.equals(outputB) && !outputB.equals(outputC) && !outputC.equals(outputA)) {
				System.out.println("結果はアイコです");
			} else {
				if (outputA.equals("グー")) {
					if (outputB.equals("グー")) {
						if (outputC.equals("パー")) {
							System.out.println("結果はプレイヤーの勝ちです");
						} else {
							System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
						}
					} else if (outputB.equals("チョキ")) {
						if (outputC.equals("グー")) {
							System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
						} else {
							System.out.println("結果はコンピュータAの勝ちです");
						}
					} else {
						if (outputC.equals("グー")) {
							System.out.println("結果はコンピュータBの勝ちです");
						} else {
							System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
						}
					}
				} else if (outputA.equals("チョキ")) {
					if (outputB.equals("グー")) {
						if (outputC.equals("グー")) {
							System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
						} else {
							System.out.println("結果はコンピュータBの勝ちです");
						}
					} else if (outputB.equals("チョキ")) {
						if (outputC.equals("グー")) {
							System.out.println("結果はプレイヤーの勝ちです");
						} else {
							System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
						}
					} else {
						if (outputC.equals("パー")) {
							System.out.println("結果はコンピュータAの勝ちです");
						} else {
							System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
						}

					}
				} else {
					if (outputB.equals("グー")) {
						if (outputC.equals("グー")) {
							System.out.println("結果はコンピュータAの勝ちです");
						} else {
							System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
						}
					} else if (outputB.equals("チョキ")) {
						if (outputC.equals("チョキ")) {
							System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
						} else {
							System.out.println("結果はコンピュータBの勝ちです");
						}
					} else {
						if (outputC.equals("グー")) {
							System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
						} else {
							System.out.println("結果はプレイヤーの勝ちです");
						}

					}
				}
			}
		} while ((computerA == player && computerA == computerB) ||

				(!(computerA == computerB) && !(computerA == player) && !(computerB == player)));
	}
}

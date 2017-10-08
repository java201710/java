public class JyakenPart5 {

	public static void main(String[] args) {
		int computerA, computerB;
		int player;
		int winsA, winsB, winsC;
		int roundCounter;
		int tieCounter;
		
		String outputA;
		String outputB;
		String outputC;
		boolean winner = false;

		winsA = 0;
		winsB = 0;
		winsC = 0;
		roundCounter = 1;
		do {
			System.out.println("プレイ： " + roundCounter);
			tieCounter = 0;
			
			do {
				if (tieCounter > 0) {
					System.out.println("アイコ： " + tieCounter);
				}
				
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
					tieCounter++;
				} else if (!outputA.equals(outputB) && !outputB.equals(outputC) && !outputC.equals(outputA)) {
					System.out.println("結果はアイコです");
					tieCounter++;
				} else {
					if (outputA.equals("グー")) {
						if (outputB.equals("グー")) {
							if (outputC.equals("パー")) {
								System.out.println("結果はプレイヤーの勝ちです");
								winsC++;
							} else {
								System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
								winsA++;
								winsB++;
							}
						} else if (outputB.equals("チョキ")) {
							if (outputC.equals("グー")) {
								System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
								winsA++;
								winsC++;
							} else {
								System.out.println("結果はコンピュータAの勝ちです");
								winsA++;
							}
						} else {
							if (outputC.equals("グー")) {
								System.out.println("結果はコンピュータBの勝ちです");
								winsB++;
							} else {
								System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
								winsB++;
								winsC++;
							}
						}
					} else if (outputA.equals("チョキ")) {
						if (outputB.equals("グー")) {
							if (outputC.equals("グー")) {
								System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
								winsB++;
								winsC++;
							} else {
								System.out.println("結果はコンピュータBの勝ちです");
								winsB++;
							}
						} else if (outputB.equals("チョキ")) {
							if (outputC.equals("グー")) {
								System.out.println("結果はプレイヤーの勝ちです");
								winsC++;
							} else {
								System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
								winsA++;
								winsB++;
							}
						} else {
							if (outputC.equals("パー")) {
								System.out.println("結果はコンピュータAの勝ちです");
								winsA++;
							} else {
								System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
								winsA++;
								winsC++;
							}

						}
					} else {
						if (outputB.equals("グー")) {
							if (outputC.equals("グー")) {
								System.out.println("結果はコンピュータAの勝ちです");
								winsA++;
							} else {
								System.out.println("結果はコンピュータAとプレイヤーの勝ちです");
								winsA++;
								winsC++;
							}
						} else if (outputB.equals("チョキ")) {
							if (outputC.equals("チョキ")) {
								System.out.println("結果はコンピュータBとプレイヤーの勝ちです");
								winsB++;
								winsC++;
							} else {
								System.out.println("結果はコンピュータBの勝ちです");
								winsB++;
							}
						} else {
							if (outputC.equals("グー")) {
								System.out.println("結果はコンピュータAとコンピュータBの勝ちです");
								winsA++;
								winsB++;
							} else {
								System.out.println("結果はプレイヤーの勝ちです");
								winsC++;
							}

						}
					}
				}
			} while ((computerA == player && computerA == computerB) ||

					(!(computerA == computerB) && !(computerA == player) && !(computerB == player)));
			
			roundCounter++;
			if (winsA == 3 || winsB == 3 || winsC == 3) {
				winner = true;
				
				System.out.println("勝ちは: コンピュータA：" + winsA + " | コンピュータB：" + winsB + " | プレイヤー：" + winsC);
				System.out.println("ジャンケンゲームの結果は： ");
				if (winsA == 3) {
					System.out.println("コンピュータAの勝ち");
				}
				if (winsB == 3) {
					System.out.println("コンピュータBの勝ち");
				}
				if (winsC == 3) {
					System.out.println("プレイヤーの勝ち");
				}

			} else {
				System.out.println("勝ちは: コンピュータA：" + winsA + " | コンピュータB：" + winsB + " | プレイヤー：" + winsC);
				System.out.println("");
			}
		} while (!winner);
	}
}

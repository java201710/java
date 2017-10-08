public class JyakenPart2 {

	public static void main(String[] args) {
		int computerA;
		int computerB;
		String outputA;
		String outputB;

		do {
			computerA = (int) (Math.random() * 3);
			computerB = (int) (Math.random() * 3);

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

			System.out.println("コンピュータAの手は：" + outputA);
			System.out.println("コンピュータBの手は：" + outputB);

			if (outputA.equals("グー")) {
				if (outputB.equals("パー")) {
					System.out.println("結果はコンピュータBの勝ちです");
				} else if (outputB.equals("チョキ")) {
					System.out.println("結果はコンピュータAの勝ちです");
				} else {
					System.out.println("結果はアイコです");
				}
			} else if (outputA.equals("チョキ")) {
				if (outputB.equals("パー")) {
					System.out.println("結果はコンピュータAの勝ちです");
				} else if (outputB.equals("グー")) {
					System.out.println("結果はコンピュータBの勝ちです");
				} else {
					System.out.println("結果はアイコです");
				}
			} else {
				if (outputB.equals("グー")) {
					System.out.println("結果はコンピュータAの勝ちです");
				} else if (outputB.equals("チョキ")) {
					System.out.println("結果はコンピュータBの勝ちです");
				} else {
					System.out.println("結果はアイコです");
				}
			}
		} while (computerA == computerB);

	}
}

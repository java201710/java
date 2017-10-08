public class Dice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int i = 0;
		int j = 0;
		System.out.println("求める合計の数字を入力してください");
		int k = new java.util.Scanner(System.in).nextInt();
		while (k < 3 || k > 18) {
			System.out.println("ありえない合計値が入力されました。3～18までの数値を入力してください。");
			k = new java.util.Scanner(System.in).nextInt();
		}
		for (int w = 1; w <= 6; w++) {
			for (int r = 1; r <= 6; r++) {
				for (int b = 1; b <= 6; b++) {
					if (w + r + b == k) {
						System.out.println("白" + w + "赤" + r + "青" + b);
						j++;
					}
					i++;
				}
			}
		}
		System.out.println("組み合わせの総数は" + i);
		System.out.println("合計が" + k + "の組み合わせの総数は" + j);
	}

}

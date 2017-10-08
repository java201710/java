public class MultiplicationTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int[][] multiplicationTable = new int[9][9];
		for (int i = 1; i < 10; i++) {
			for (int j = 1; j < 10; j++) {
				multiplicationTable[i - 1][j - 1] = i * j;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(multiplicationTable[i][j]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

}

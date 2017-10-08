
public class Main07 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int scores[][] = new int[9][9];

//		for(int i=0; i<9; i++){
//			for(int j=0; j<9; j++){
//				scores[i][j] = (i+1) * (j+1);
//			}
//		}

		for(int i=0; i<scores.length; i++){
			for(int j=0; j<scores[i].length; j++){
				scores[i][j] = (i+1) * (j+1);
			}
		}


		for(int values[] : scores){
			//values[] ← scores[n][0]～scores[n][8]

			for(int value : values){
				//value[] ← scores[n][m]
				System.out.print(value + " ");
			}
			System.out.println();
		}
	}

}

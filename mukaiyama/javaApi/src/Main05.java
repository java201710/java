

public class Main05 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int cnt = 0;
		System.out.println("求める合計の数値を入力してください。");
		int sum = new java.util.Scanner(System.in).nextInt();

		for(int i=1; i<7; i++){
			for(int j=1; j<7; j++){
				for(int k=1; k<7; k++){
					if(i+j+k == sum){
						System.out.println("白" + i + " 赤" + j + " 青" + k);
						cnt++;
					}
				}

			}
		}
		System.out.println("組合せ総数：" + cnt +"件");
	}

}

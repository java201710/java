
public class Jyanken02 {
	//じゃんけんプログラム②
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String Jyanken[] = {"グー","チョキ","パー"};
		String Result[][] = {
				{"アイコ","CPU-Aの勝ち","CPU-Bの勝ち"},
				{"CPU-Bの勝ち","アイコ","CPU-Aの勝ち"},
				{"CPU-Aの勝ち","CPU-Bの勝ち","アイコ"}
		};

		int intA = 0;
		int intB = 0;

		do{
			intA = new java.util.Random().nextInt(3);
			intB = new java.util.Random().nextInt(3);


			System.out.println("CPU-A:" + intA + Jyanken[intA]);
			System.out.println("CPU-B:" + intB + Jyanken[intB]);

			System.out.println("CPU-A VS CPU-B の結果は…");

			System.out.println(Result[intA][intB] + "です");

		}while(intA == intB);

	}


}

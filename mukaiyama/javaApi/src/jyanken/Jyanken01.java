package jyanken;

public class Jyanken01 {
	//じゃんけんプログラム①
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		String Jyanken[] = {"グー","チョキ","パー"};
		String Result[][] = {
				{"アイコ","勝ち","負け"},
				{"負け","アイコ","勝ち"},
				{"勝ち","負け","アイコ"}
		};
//		String Result[][] = {
//				{"0","0","アイコ"},
//				{"0","1","A"},
//				{"0","2","B"},
//				{"1","0","B"},
//				{"1","1","アイコ"},
//				{"1","2","A"},
//				{"2","0","A"},
//				{"2","1","B"},
//				{"2","2","アイコ"}
//				};
//		for (int i = 0; i < Jyanken.length; i++) {
//			System.out.println(Jyanken[i]);
//		}


		int intA = new java.util.Random().nextInt(3);
		int intB = new java.util.Random().nextInt(3);


		System.out.println("CPU-A:" + intA + Jyanken[intA]);
		System.out.println("CPU-B:" + intB + Jyanken[intB]);

		System.out.println("CPU-A VS CPU-B の結果は…");
		if(intA != intB){
			if(Result[intA][intB].equals("勝ち")){
				System.out.println("CPU-Aの" + Result[intA][intB]);
			}
			if(Result[intB][intA].equals("勝ち")){
				System.out.println("CPU-Bの" + Result[intB][intA]);
			}
		}else{
			System.out.println(Result[intA][intB] + "です");
		}

	}


}

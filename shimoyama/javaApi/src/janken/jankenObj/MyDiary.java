package janken.jankenObj;
public class MyDiary {
	public static void main(String[] args) {
		
		//初期化
		int[][] kuku = new int[9][9];

		//九九表を二次元配列に格納
		for (int i = 0; i < kuku.length; i++) {
			for (int s = 0; s < kuku[0].length; s++) {
				kuku[i][s] = (i+1) * (s+1);
			}
		}

		//2次元配列の中身を表示
		for (int k[] : kuku) {
			for (int kk : k) {
				if (kk < 10) {
					System.out.print(" ");
				}
				System.out.print(kk + " ");
			}
			System.out.println("");
		}
	}
}
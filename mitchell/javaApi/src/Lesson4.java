public class Lesson4 {

	public static void main(String[] args) {
		
		int[][] a = new int[9][9];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = (i+1) * (j+1);
			}
		}
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
//				System.out.print(a[i][j]);
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
		
		for(int k[] : a) {
			for (int kk : k) {
				if (kk < 10) {
					System.out.print(" ");
				}
				System.out.print(kk + "\t");
			}
			System.out.println("");
		}
		
	}
}

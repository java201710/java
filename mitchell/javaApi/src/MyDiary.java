public class MyDiary {

	public static void main(String[] args) {

		int age;

		age = 30;

		System.out.println(age);
		//System.out.println("Hello \"Mitchell\"");
		//System.out.println("Hello \nMitchell");

		//System.out.println((int)(Math.random()*6)+1);

		System.out.print("数字を入力してください：");
		int userInput = new java.util.Scanner(System.in).nextInt();
		while (userInput < 3 || userInput > 18) {
			System.out.print("ありえない合計直が入力されました。３～１８までの数直を入力してください：");
			userInput = new java.util.Scanner(System.in).nextInt();
		}

		int counter = 0;
		//int equalTwelve = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 6; j++) {
				for (int k = 1; k <= 6; k++) {
					if ((i + j + k) == 	userInput) {
						System.out.println("白 " + i + "赤 " + j + "青 " + k);
						counter++;
						}
				}
			}
		}
		System.out.println(counter);
		//System.out.println(equalTwelve);
	}
}

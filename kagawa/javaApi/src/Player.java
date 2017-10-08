import java.util.Scanner;

public class Player {
	int janken; //ジャンケンの手（0：グー／1：チョキ／2：パー）
	private String[] jankenTable = { "グー", "チョキ", "パー" };
	String name; //名前
	boolean manFlag; //true：人間／false：コンピュータ

	public Player(String name, boolean manFlag) {
		this.name = name;
		this.manFlag = manFlag;
	}

	public void doJanken(boolean manFlag) {
		if (manFlag == true) {
			do {
				System.out.println("ジャンケンの手を入力してください。（0：グー／1：チョキ／2：パー）");
				this.janken = new Scanner(System.in).nextInt();
			} while (this.janken != 0 && this.janken != 1 && this.janken != 2);
		}
		else
			this.janken = (int) (Math.random() * 3);

		System.out.println(this.name + "の手は" + jankenTable[janken]);
	}
}
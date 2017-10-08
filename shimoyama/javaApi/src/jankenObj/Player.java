package jankenObj;

class Player {
	//じゃんけんの手
	private String[] handPattern = { "グー", "チョキ", "パー" };
	//プレイヤーの名前
	private String name;

	//プレイヤーコンストラクタ
	public Player(int playerNumber) {
		if (playerNumber == 0) {
			this.name = "プレイヤー";
		} else {
			this.name = "コンピュータ" + (playerNumber);
		}

		System.out.println(this.name + "を作成しました。");
	}

	//場に無造作に手を出す(戻り値は出した手)
	public int act() {
		int hand;
		if (this.name.equals("プレイヤー")) {
			System.out.println("出す手を入力してください。");
			System.out.println("(0:グー/1:チョキ/2:パーを数字で入力)");
			hand = new java.util.Scanner(System.in).nextInt();
		} else {
		hand = (int) (Math.random() * 3);
		}
		System.out.println(this.name + "は" + handPattern[hand] + "を出しました。");
		return hand;
	}

	//nameフィールドのgetter
	public String getName() {
		return name;
	}

}

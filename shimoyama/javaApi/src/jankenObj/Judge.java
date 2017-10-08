package jankenObj;

class Judge {
	//場に出たじゃんけんの手
	int[] playersHand;

	//手を出したプレイヤーの名前
	String[] playerName;

	Player[] players;

	//Judgeコンストラクタ
	public Judge() {
		System.out.println("じゃんけんの場を作成しました。");
	}

	//じゃんけんのスタートメソッド
	public void start(Player[] players) {
		this.players = players;
		this.playersHand = new int[players.length];
		this.playerName = new String[players.length];
		do {
			for (int i = 0; i < this.playersHand.length; i++) {
				this.playersHand[i] = players[i].act();
				this.playerName[i] = players[i].getName();
			}
		} while (this.handJudge());
	}

	//じゃんけんの手を判断し勝者を表示
	public boolean handJudge() {
		//各じゃんけんの手の数(0:グー/1:チョキ/2:パー)
		int[] handPattern = { 0, 0, 0 };

		//各じゃんけんの手の数を数える
		handCount(handPattern);

		//アイコのとき
		if (handPattern[0] >= this.playersHand.length ||
				handPattern[1] >= this.playersHand.length ||
				handPattern[2] >= this.playersHand.length) {
			System.out.println("アイコでした。");
			System.out.println("-----------------------------");
			return true;

		} else {
			//勝敗が決まる時
			System.out.println("==============================");
			//チョキとパーの時
			if (handPattern[0] == 0) {
				for (int k = 0; k < this.playersHand.length; k++) {
					if (this.playersHand[k] == 1) {
						System.out.println(this.playerName[k] + "の勝ちです。");
					}
				}
				//グーとパーの時
			} else if (handPattern[1] == 0) {
				for (int k = 0; k < this.playersHand.length; k++) {
					if (this.playersHand[k] == 2) {
						System.out.println(this.playerName[k] + "の勝ちです。");
					}
				}
				//グーとチョキの時
			} else if (handPattern[2] == 0) {
				for (int k = 0; k < this.playersHand.length; k++) {
					if (this.playersHand[k] == 0) {
						System.out.println(this.playerName[k] + "の勝ちです。");
					}
				}
			}
			System.out.println("==============================");	
			return false;
		}
	}
	void handCount(int[] handPattern) {
	//各じゃんけんの手の数を数える
	for (int s = 0; s < this.playersHand.length; s++) {
		if (this.playersHand[s] == 0) {
			handPattern[0]++;
		} else if (this.playersHand[s] == 1) {
			handPattern[1]++;
		} else if (this.playersHand[s] == 2) {
			handPattern[2]++;
		}
	}
	}
	
}

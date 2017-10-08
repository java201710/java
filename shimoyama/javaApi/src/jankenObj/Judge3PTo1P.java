package jankenObj;

import java.util.ArrayList;

class Judge3PTo1P extends Judge {

	//じゃんけんのスタートメソッド
	public void start(Player[] players) {
		this.players = players;
		this.playersHand = new int[this.players.length];
		this.playerName = new String[this.players.length];
		do {
			do {
				for (int i = 0; i < this.playersHand.length; i++) {
					this.playersHand[i] = this.players[i].act();
					this.playerName[i] = this.players[i].getName();
				}
			} while (this.handJudge());
		} while (this.players.length > 1);
	}

	//じゃんけんの手を判断し勝者を表示
	public boolean handJudge() {
		ArrayList<Player> work = new ArrayList<Player>();
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

		} else if (handPattern[0] >= 1 &&
				handPattern[1] >= 1 &&
				handPattern[2] >= 1) {
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
						work.add(players[k]);
					}
				}
				//グーとパーの時
			} else if (handPattern[1] == 0) {
				for (int k = 0; k < this.playersHand.length; k++) {
					if (this.playersHand[k] == 2) {
						System.out.println(this.playerName[k] + "の勝ちです。");
						work.add(players[k]);
					}
				}
				//グーとチョキの時
			} else if (handPattern[2] == 0) {
				for (int k = 0; k < this.playersHand.length; k++) {
					if (this.playersHand[k] == 0) {
						System.out.println(this.playerName[k] + "の勝ちです。");
						work.add(players[k]);
					}
				}
			}
			Player[] playerWork = new Player[work.size()];
			for (int i = 0; i < work.size(); i++) {
				playerWork[i] = work.get(i);
			}
			this.players = playerWork;
			
			for(Player p:players){
				System.out.println(p.getName());
			}
			
			System.out.println("==============================");
			return false;
		}
	}
}

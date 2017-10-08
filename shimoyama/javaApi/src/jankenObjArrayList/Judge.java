package jankenObjArrayList;

import java.util.ArrayList;


class Judge {
	//場に出たじゃんけんの手
//	int[] playersHand;
	ArrayList<Integer> playersHand = new ArrayList<Integer>();

	//手を出したプレイヤーの名前
//	String[] playerName;
	ArrayList<String> playerName = new ArrayList<String>();

	ArrayList<Player> players;

	//Judgeコンストラクタ
	public Judge() {
		System.out.println("じゃんけんの場を作成しました。");
	}

	//じゃんけんのスタートメソッド
	public void start(ArrayList<Player> players) {
		this.players = players;
//		this.playersHand = new int[players.size()];
//		this.playerName = new String[players.size()];
		do {
			for (int i = 0; i < playersHand.size(); i++) {
				this.playersHand.add(players.get(i).act());
				this.playerName.add(players.get(i).getName());
			}
		} while (this.handJudge());
	}

	//じゃんけんの手を判断し勝者を表示
	public boolean handJudge() {
		//各じゃんけんの手の数(0:グー/1:チョキ/2:パー)
		int[] handCount = { 0, 0, 0 };

		//各じゃんけんの手の数を数える
		handCounts(handCount);

		//アイコのとき
		if (handCount[0] >= this.playersHand.size() ||
				handCount[1] >= this.playersHand.size() ||
				handCount[2] >= this.playersHand.size()) {
			System.out.println("アイコでした。");
			System.out.println("-----------------------------");
			return true;

		}else if (handCount[0] >= 1 &&
				handCount[1] >= 1 &&
				handCount[2] >= 1) {
			System.out.println("アイコでした。");
			System.out.println("-----------------------------");
			return true;
		}else {
			//勝敗が決まる時
			System.out.println("==============================");
			//チョキとパーの時
			if (handCount[0] == 0) {
				for (int k = 0; k < this.playersHand.size(); k++) {
					if (playersHand.get(k) == 1) {
						System.out.println(playerName.get(k) + "の勝ちです。");
					}
				}
				//グーとパーの時
			} else if (handCount[1] == 0) {
				for (int k = 0; k < this.playersHand.size(); k++) {
					if (playersHand.get(k) == 2) {
						System.out.println(playerName.get(k) + "の勝ちです。");
					}
				}
				//グーとチョキの時
			} else if (handCount[2] == 0) {
				for (int k = 0; k < this.playersHand.size(); k++) {
					if (playersHand.get(k) == 0) {
						System.out.println(playerName.get(k) + "の勝ちです。");
					}
				}
			}
			System.out.println("==============================");	
			return false;
		}
	}
	void handCounts(int[] handPattern) {
	//各じゃんけんの手の数を数える
	for (int s = 0; s < this.playersHand.size(); s++) {
		if (this.playersHand.get(s) == 0) {
			handPattern[0]++;
		} else if (this.playersHand.get(s) == 1) {
			handPattern[1]++;
		} else if (this.playersHand.get(s) == 2) {
			handPattern[2]++;
		}
	}
	}
	
}

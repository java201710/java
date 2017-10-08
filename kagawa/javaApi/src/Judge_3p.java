public class Judge_3p extends Judge {
	public void judge(Player[] players) {
		int rock = 0;
		int scissors = 0;
		int paper = 0;

		//プレイヤーの出したジャンケンの手をカウント
		for (Player player : players) {
			if (player.janken == 0)
				rock++;
			else if (player.janken == 1)
				scissors++;
			else
				paper++;
		}

		//ジャンケンの手を判断して勝敗宣言（オーバーライド）
		if (rock == players.length || scissors == players.length || paper == players.length
				|| (rock != 0 && scissors != 0 && paper != 0)) {
			System.out.println("アイコです。");
			//再ジャンケン
			start(players);
		}
		else if (rock == 0) {
			for (Player player : players) {
				if (player.janken == 1)
					System.out.println("勝者は" + player.name);
			}
		}
		else if (scissors == 0) {
			for (Player player : players) {
				if (player.janken == 2)
					System.out.println("勝者は" + player.name);
			}
		}
		else {
			for (Player player : players) {
				if (player.janken == 0)
					System.out.println("勝者は" + player.name);
			}
		}

	}
}

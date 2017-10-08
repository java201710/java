public class Judge {

	public void start(Player[] players) {
		System.out.println("ジャンケンを始めます。");
		//手を出させる
		for (Player player : players) {
			player.doJanken(player.manFlag);
		}
		//判断を呼び出す
		judge(players);
	}

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

		//ジャンケンの手を判断して勝敗宣言
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

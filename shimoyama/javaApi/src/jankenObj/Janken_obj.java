package jankenObj;

class Janken_obj {
	public static void main(String[] args) {
		//プレイヤー数
		int member = 7;
		//プレイヤーオブジェクトをオブジェクト配列で作成
		Player[] players = new Player[member];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(i);
		}
		//ジャッジオブジェクトを作成
		Judge3P judge = new Judge3P();

		//ジャッジオブジェクトに人数を教えてじゃんけんを開始させる
		System.out.println(players.length + "人じゃんけんを開始します。");
		System.out.println("==============================");
		judge.start(players);
	}
}
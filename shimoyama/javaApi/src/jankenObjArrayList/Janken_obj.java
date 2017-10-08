package jankenObjArrayList;

import java.util.ArrayList;

class Janken_obj {
	public static void main(String[] args) {
		//プレイヤー数
		int member = 2;
		//プレイヤーオブジェクトをオブジェクト配列で作成
		ArrayList<Player> players = new ArrayList<Player>();
//		Player[] players = new Player[member];
		for (int i = 0; i < member; i++) {
			players.add(new Player(i));
		}
		//ジャッジオブジェクトを作成
		Judge judge = new Judge();

		//ジャッジオブジェクトに人数を教えてじゃんけんを開始させる
		System.out.println(member + "人じゃんけんを開始します。");
		System.out.println("==============================");
		judge.start(players);
	}
}
public class Janken_obj {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Player player1 = new Player("人間A", true);
		Player player2 = new Player("コンピュータA", false);
		Player player3 = new Player("コンピュータB", false);
		//Player player4 = new Player("コンピュータC", false);
		Player[] players = { player1, player2, player3 };

		Judge j = new Judge();
		//Judge_3p j = new Judge_3p();
		j.start(players);

	}

}

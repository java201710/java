package JyankenObj;


public class Player {
////////////////////////////////////////////////////////////
// << 宣言 >>

	//【フィールド】
	private int hand;	//ジャンケンの手
	private String name;	//Playerの名前
	private boolean human;	//true:人、false:コンピューター
	private boolean alive = true;	//true:生き残り、false:脱落

	//【staticフィールド】
	private static String handNames[] = {"グー","チョキ","パー"};
	private static int playerCount = 0;		//生き残っているPlayerの数

////////////////////////////////////////////////////////////
// << setter/getter >>

	public int getHand() {
		return hand;
	}
	public boolean setHand(int hand) {
		if(hand >=0 && hand <= 2){
			this.hand = hand;
		}else{
			return false;
		}
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isHuman() {
		return human;
	}
	public void setHuman(boolean human) {
		this.human = human;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	public static int getPlayerCount() {
		return playerCount;
	}
	public static String getHandName(int hand) {
		return handNames[hand];
	}

////////////////////////////////////////////////////////////
// << コンストラクタ >>

	public Player(String name ,boolean human) {
		this.setName(name);
		this.setHuman(human);
		Player.playerCount++;
	}

////////////////////////////////////////////////////////////
// << メソッド >>

	//【ジャンケンの手を出す】
	public int attack(){
		if(this.isHuman()==false){
			//Playerがコンピューターの場合→ランダムでセット
			this.setHand(new java.util.Random().nextInt(3));
		}else{
			//Playerが人の場合→入力指示
			//0～2以外が入力された場合、再入力
			do {
				System.out.println("ジャンケンの手を入力してください。");
				System.out.println("（0:グー / 1:チョキ / 2:パー）");
			} while (!(this.setHand(new java.util.Scanner(System.in).nextInt())));

		}
		System.out.println(this.name + ":" + this.hand + " " + Player.handNames[this.hand]);
		return this.getHand();
	}

	//【敗退処理】
	public void lose(){
		this.setAlive(false);	//false:脱落
		Player.playerCount--;	//「生き残っているPlayerの数」から自分を引く
	}
}

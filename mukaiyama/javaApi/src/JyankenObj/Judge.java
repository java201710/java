package JyankenObj;

public class Judge {
////////////////////////////////////////////////////////////
// << 宣言 >>
	//【フィールド】
	private Player[] Players;		//プレイヤー情報

	//【staticフィールド】
	private static int handCount_0;	//0：グーの手の数
	private static int handCount_1;	//1：チョキの手の数
	private static int handCount_2;	//2：パーの手の数

////////////////////////////////////////////////////////////
// << setter/getter >>
//	public Player[] getPlayers() {
//		return Players;
//	}
//
//	public void setPlayers(Player[] players) {
//		Players = players;
//	}


////////////////////////////////////////////////////////////
// << コンストラクタ >>
	public Judge(Player[] Players) {
		this.Players = Players;
	}

////////////////////////////////////////////////////////////
// << メソッド >>

	//【ジャンケン制御】
	public void fight(){
		//[繰り返しSTART]最後の一人になるまで繰り返す
		do {
			//ジャンケンの開始を宣言
			System.out.println("【残り" + Player.getPlayerCount() + "名】");
			System.out.println("【ジャンケン、ポン！】");
			//[繰り返しSTART]勝敗がつくまで（アイコの間）繰り返す
			do {
				//ジャンケンの手の種類別の件数をクリア
				Judge.handCount_0 = 0;
				Judge.handCount_1 = 0;
				Judge.handCount_2 = 0;

				//[繰り返しSTART]全Playerにジャンケンの手を出させる
				for(Player fighter : this.Players){
					if(fighter.isAlive()==false){
						//敗退したPlayerは除外
						continue;
					}
					//Playerにジャンケンの手を出させる（Playerのメソッド呼び出し）
					int hand_work = fighter.attack();

					//ジャンケンの手の種類別の件数をカウント
					switch (hand_work) {
					case 0:
						Judge.handCount_0++;
						break;
					case 1:
						Judge.handCount_1++;
						break;
					case 2:
						Judge.handCount_2++;
						break;
					default:
						break;
					}
				}
				//Player全員の手を表示する
//				for(Player fighter : this.Players){
//					if(fighter.isAlive()==false){
//						//敗退したPlayerは除外
//						continue;
//					}
//					System.out.println("  >" + fighter.getName() + ":" + fighter.getHand() + Player.getHandName(fighter.getHand()));
//				}
			} while (this.result()==false);		//勝敗判断を呼び出す 決着→true、アイコ→false
		} while (Player.getPlayerCount() >1);	//最後の一人になるまで繰り返す
	}


	//【勝敗を判断し、勝敗を宣言する】
	public boolean result(){
		if(Judge.handCount_0 >0 && Judge.handCount_1 >0 && Judge.handCount_2 >0){
			//手が3種類→アイコ
			this.showDrow();
			return false;	//アイコ→false
		}else if((Judge.handCount_0 ==Player.getPlayerCount())||
				(Judge.handCount_1 ==Player.getPlayerCount())||
				(Judge.handCount_2 ==Player.getPlayerCount())){
			//手が1種類（いずれかの手の件数＝Playerの生き残り人数）→アイコ
			this.showDrow();
			return false;	//アイコ→false
		}else{
			//アイコ以外
			//[繰り返しSTART]各Playerの勝敗判定
			for(Player fighter : this.Players){
				if(fighter.isAlive()==false){
					//敗退したPlayerは除外
					continue;
				}
				int hand_work = fighter.getHand();
				//手が2種類→勝敗判断
				if(Judge.handCount_0 >0 && Judge.handCount_1 >0){
					//0：グー VS 1：チョキ → 0：グーの勝ち
					if(hand_work == 0){
						this.showWin(fighter.getName());	//勝ち
					}else{
						fighter.lose();	//負け（Playerに敗退通知）
					}
				}else if(Judge.handCount_0 >0 && Judge.handCount_2 >0){
					//0：グー VS 2：パー → 2：パーの勝ち
					if(hand_work == 2){
						this.showWin(fighter.getName());	//勝ち
					}else{
						fighter.lose();	//負け（Playerに敗退通知）
					}
				}else{
					//1：チョキ VS 2：パー → 1：チョキの勝ち
					if(hand_work == 1){
						this.showWin(fighter.getName());	//勝ち
					}else{
						fighter.lose();	//負け（Playerに敗退通知）
					}
				}

			}
		}
		return true;	//アイコ以外→true
	}

	//【勝利宣言】
	public void showWin(String name){
		System.out.println("[" + name + "の勝ち]");
	}

	//【アイコ宣言】
	public void showDrow(){
		System.out.println("【アイコでしょ！】");
	}
}

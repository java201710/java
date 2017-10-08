package ginkouPractice;

public class Choubo {
	int money;	//残高

	//コンストラクター
	public Choubo() {
		// TODO 自動生成されたコンストラクター・スタブ
		this.money = 100000;
	}

	//getter,setter
	private int getMoney() {
		return money;
	}

	private void setMoney(int money) {
		this.money = money;
	}

	//残高の問い合わせ
	public void zandaka(){
		System.out.println("あなたの残高は" + this.getMoney() + "円です。");
	}

	//出金処理

	//入金処理


}

package practicetenzero;

public class Hero {
	private int hp;
	private String name;
	private Sword sword;
	private static int money;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		// 代入前に、代入しようとしている値が妥当かを検査する
		if (name == null) { // nullが設定されそうになった！
			throw new IllegalArgumentException("名前にnullが設定されそうになりました。処理を中断します。");
		}
		if (name.length() <= 1) { // 文字列長が異常（短すぎる）
			throw new IllegalArgumentException(
					"1文字以下の名前が設定されそうになりました。処理を中断します。");
		}
		if (name.length() >= 8) { // 文字列長が異常（長すぎる）
			throw new IllegalArgumentException(
					"8文字以上の名前が設定されそうになりました。処理を中断します。");
		}
		this.name = name; // 検査完了！代入しても大丈夫。
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public Sword getSword() {
		return sword;
	}

	public void setSword(Sword sword) {
		this.sword = sword;
	}

	Hero() {
		this("ダミー");
	}

	Hero(String name) {
		this.hp = 100;
		this.name = name;
	}

	void bye() {
		System.out.println("勇者は別れを告げた");
	}

	private void die() {
		System.out.println(this.name + "は死んでしまった！");
		System.out.println("GAME OVERです。");
	}

	void sleep() {
		this.hp = 100;
		System.out.println(this.name + "は、眠って回復した！");
	}

	public void attack(Matango m) {
		System.out.println(this.name + "の攻撃！");
		System.out.println("お化けキノコ" + m.suffix + "に" + sword.damage
				+ "ポイントのダメージを与えた");
		m.hp -= sword.damage;

		if (m.hp <= 0) {
			System.out.println("お化けキノコ" + m.suffix + "を倒した！");
		} else {
			System.out.println("お化けキノコ" + m.suffix + "から2ポイントの反撃を受けた");
			this.hp -= 2;
			if (this.hp <= 0) {
				this.die();
			}
		}
	}

	void sit(int sec) {
		this.hp += sec;
		System.out.println(this.name + "は、" + sec + "秒すわった！");
		System.out.println("HPが" + sec + "ポイント回復した.");
	}

	void slip() {
		this.hp -= 5;
		System.out.println(this.name + "は転んだ！");
		System.out.println("5のダメージ！");
		if (this.hp <= 0) {
			die();
		}
	}

	void run() {
		System.out.println(this.name + "は、逃げ出した！");
		System.out.println("GAMEOVER");
		System.out.println("最終HPは" + this.hp + "でした");
	}

	static void setRandomMoney() {
		Hero.money = (int) (Math.random() * 1000);
	}
}

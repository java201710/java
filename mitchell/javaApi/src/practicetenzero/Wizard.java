package practicetenzero;

public class Wizard {
	private int hp;
	private int mp;
	private String name;
	private Wand wand;

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if (hp < 0) {
			this.hp = 0;
		} else {
			this.hp = hp;
		}
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		if (mp < 0) {
			throw new IllegalArgumentException("エラーメッセージ");
		} else {
			this.mp = mp;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if ((name == null) || (name.length() < 3)) {
			throw new IllegalArgumentException("エラーメッセージ");
		} else {
			this.name = name;
		}
	}

	public Wand getWand() {
		return wand;
	}

	public void setWand(Wand wand) {
		if (wand == null) {
			throw new IllegalArgumentException("エラーメッセージ");
		} else {
			this.wand = wand;
		}
	}

	public void heal(Hero h) {
		int basePoint = 10; // 基本回復ポイント
		//		int recovPoint = (int) (basePoint * this.wand.power); // 杖による増幅
		int recovPoint = (int) (basePoint * this.wand.getPower());
		h.setHp(h.getHp() + recovPoint); // 勇者のHPを回復させる
		System.out.println(h.getName() + "のHPを" + recovPoint + "回復した!");
	}
}

//throw new IllegalArgumentException ("");
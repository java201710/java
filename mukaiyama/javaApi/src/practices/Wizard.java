package practices;

public class Wizard {
	private int hp;
	private int mp;
	private String name;
	private Wand wand;

	public Wizard() {
		// TODO 自動生成されたコンストラクター・スタブ
		wand = new Wand();	//初期状態 杖未装備の状態を表す（←要らないかも？）
	}

	public void heal(Hero h) {
		int basePoint = 10; // 基本回復ポイント
//		int recovPoint = (int) (basePoint * this.wand.power); // 杖による増幅
		int recovPoint = (int) (basePoint * this.wand.getPower()); // 杖による増幅
		h.setHp(h.getHp() + recovPoint); // 勇者のHPを回復させる
		System.out.println(h.getName() + "のHPを" + recovPoint + "回復した!");
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		if(hp < 0){
			hp = 0;
		}
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		if(mp < 0){
			throw new IllegalArgumentException("MPは0以上の値を設定してください。");
		}
		this.mp = mp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null){
			throw new IllegalArgumentException("魔法使いの名前が入力されていません。");
		}
		if(name.length() < 3){
			throw new IllegalArgumentException("魔法使いの名前は3文字以上で入力してください。");
		}
		this.name = name;
	}

	public Wand getWand() {
		return wand;
	}

	public void setWand(Wand wand) {
		if(wand == null){
			throw new IllegalArgumentException("設定しようとしている杖がnullです。");
		}
		this.wand = wand;
	}


}
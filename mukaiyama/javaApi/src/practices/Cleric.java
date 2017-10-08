package practices;

import java.util.Random;

public class Cleric {
	private String name;
	private int hp = 50;
	private int mp = 10;

//	final int MAX_HP = 50;
//	final int MAX_MP = 10;
	static final int MAX_HP = 50;
	static final int MAX_MP = 10;

	///////////////////////////
	//コンストラクタ
	///////////////////////////

	//指定：name,hp,mp
	Cleric(String name, int hp, int mp){
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}

	//指定：name,hp
	Cleric(String name, int hp){
		this(name,hp,Cleric.MAX_MP);
	}

	//指定：name
	Cleric(String name){
		this(name,Cleric.MAX_HP,Cleric.MAX_MP);
	}

	///////////////////////////
	//メソッド
	///////////////////////////
	public void selfAid(){
		System.out.println(this.name + "はセルフエイドを唱えた！");
//		this.hp = this.MAX_HP;
		this.hp = Cleric.MAX_HP;
		this.mp -= 5;
		System.out.println("HPが最大まで回復した。");
	}

	public int pray(int sec){
		System.out.println(this.name + "は" + sec + "秒間天に祈った！");

		// 論理上の回復量を乱数を用いて決定する
		int recover = new Random().nextInt(2);

		// 実際の回復量を計算する
//		int recoverActual = Math.min(this.MAX_MP - this.mp, recover);
		int recoverActual = Math.min(Cleric.MAX_MP - this.mp, recover);

		this.mp += recoverActual;
		System.out.println("MPが" + recoverActual + "回復した。");
		return recoverActual;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public static int getMaxHp() {
		return MAX_HP;
	}

	public static int getMaxMp() {
		return MAX_MP;
	}


}
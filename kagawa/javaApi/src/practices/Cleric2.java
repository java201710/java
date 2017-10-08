package practices;

import java.util.Random;

public class Cleric2 {
	String name;
	int hp = 50;
	static final int MAX_HP = 50;
	int mp = 10;
	static final int MAX_MP = 10;

	Cleric2(String name, int hp, int mp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
	}

	Cleric2(String name, int hp) {
		//this.name = name;
		//this.hp = hp;
		//this.mp = Cleric2.MAX_MP;
		this(name, hp, Cleric2.MAX_MP);
	}

	Cleric2(String name) {
		//		this.name = name;
		//		this.hp = Cleric2.MAX_HP;
		//		this.mp = Cleric2.MAX_MP;
		this(name, Cleric2.MAX_HP);
	}

	public void selfAid() {
		System.out.println(this.name + "はセルフエイドを唱えた！");
		this.hp = Cleric2.MAX_HP;
		this.mp -= 5;
		System.out.println("HPが最大まで回復した。");
	}

	public int pray(int sec) {
		System.out.println(this.name + "は" + sec + "秒間天に祈った！");

		// 論理上の回復量を乱数を用いて決定する
		int recover = new Random().nextInt(2);

		// 実際の回復量を計算する
		int recoverActual = Math.min(Cleric2.MAX_MP - this.mp, recover);

		this.mp += recoverActual;
		System.out.println("MPが" + recoverActual + "回復した。");
		return recoverActual;
	}
}
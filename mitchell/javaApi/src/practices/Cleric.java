package practices;

import java.util.Random;

public class Cleric {
	String name;
	int hp = 50;
	static final int MAX_HP = 50; 									//「static」を使うと、インスタンスせずにこの変数を使える。
	int mp = 10;
	static final int MAX_MP = 10; 									//「static」を使うと、インスタンスせずにこの変数を使える。

	public void selfAid(){
		System.out.println(this.name + "はセルフエイドを唱えた！");
		this.hp = Cleric.MAX_HP; 									//static変数を正しい書き方。クラス名
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

	Cleric(String name, int a, int b) {
		this.name = name;
		this.hp = a;
		this.mp = b;
	}

	Cleric(String name, int a) {
		this(name, a, Cleric.MAX_MP);
		
//		this.name = name;
//		this.hp = a;
//		this.mp = Cleric.MAX_MP;
	}

	Cleric(String name) {
		this(name, Cleric.MAX_HP);
//		this.name = name;
//		this.hp = Cleric.MAX_HP;
//		this.mp = Cleric.MAX_MP;
	}
}
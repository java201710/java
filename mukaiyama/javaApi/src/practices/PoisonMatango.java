package practices;

public class PoisonMatango extends Matango{
	private int poisonCount = 5;
	public PoisonMatango( char suffix) {
		super(suffix);
	}
	public void attack(Hero h) {
		super.attack(h);
		if ( poisonCount > 0) {
			System.out.println("さらに毒の胞子をばらまいた");
			int dmg = h.hp / 5;
			h.hp -= dmg;
			System.out.println(dmg + "ポイントのダメージをあたえた！");
			this.poisonCount--;
		}
	}
}

package test01Charactor;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		SuperHero sh = new SuperHero();
		SuperHero sh2 = new SuperHero();
		Hero h = sh;
		Charactor ch = sh;

//		System.out.println("SuperHero型→ 名前：" + sh.name + "、HP：" + sh.hp + "  " + sh);
//		System.out.println("Hero型→ 名前：" + h.name + "、HP：" + h.hp + "  " + h);
//		System.out.println("Charactor型→ 名前：" + ch.name + "、HP：" + ch.hp + "  " + ch);

		System.out.println("sh→	" + sh + "	名前：" + sh.name + "、HP：" + sh.hp);
		System.out.println("sh2→	" + sh2 + "	名前：" + sh2.name + "、HP：" + sh2.hp);
		if(sh == sh2){
			System.out.println("sh 等値 sh2");
		}
		if(sh.equals(sh2)){
			System.out.println("sh 等価 sh2");
		}
		if(sh.name.equals(sh2.name)){
			System.out.println("sh.name 等価 sh2.name");
		}
		if(sh.hp.equals(sh2.hp)){
			System.out.println("sh.hp 等価 sh2.hp");
		}

	}

}

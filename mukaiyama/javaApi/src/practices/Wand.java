package practices;

public class Wand {
	private String name;			// 杖の名前
	private double power;		// 杖の魔力


	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name == null){
			throw new IllegalArgumentException("杖の名前が入力されていません。");
		}
		if(name.length() < 3){
			throw new IllegalArgumentException("杖の名前は3文字以上で入力してください。");
		}
		this.name = name;
	}
	public double getPower() {
		return power;
	}
	public void setPower(double power) {
		if(power < 0.5){
			throw new IllegalArgumentException("杖の魔力が小さすぎます。0.5～100.0の間で入力してください。");
		}
		if(power > 100.0){
			throw new IllegalArgumentException("杖の魔力が大きすぎます。0.5～100.0の間で入力してください。");
		}
		this.power = power;
	}


}

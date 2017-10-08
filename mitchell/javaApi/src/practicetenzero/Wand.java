package practicetenzero;

public class Wand {
	private String name; // 杖の名前
	private double power; // 杖の魔力

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

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		if ((power > 100) || (power < 0.5)) {
			throw new IllegalArgumentException("エラーメッセージ");
		} else {
			this.power = power;
		}
	}
}

//throw new IllegalArgumentException ("");
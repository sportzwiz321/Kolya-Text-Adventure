public class Wind extends Element {

	public Wind() {
		super("Wind");
	}

	public boolean superEffective(Character monster) {
		if (monster.getElementType() == "Fire" || monster.getElementType() == "Earth") {
			return true;
		} else {
			return false;
		}
	}

	public boolean notVeryEffective(Character monster) {
		if (monster.getElementType() == "Water" || monster.getElementType() == "Thunder") {
			return true;
		} else {
			return false;
		}
	}

}
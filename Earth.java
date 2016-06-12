public class Earth extends Element {

	public Earth() {
		super("Earth");
	}

	public boolean superEffective(Character monster) {
		if (monster.getElementType() == "Water" || monster.getElementType() == "Thunder") {
			return true;
		} else {
			return false;
		}
	}

	public boolean notVeryEffective(Character monster) {
		if (monster.getElementType() == "Fire" || monster.getElementType() == "Wind") {
			return true;
		} else {
			return false;
		}
	}

}
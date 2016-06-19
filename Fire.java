public class Fire extends Element {

	public Fire() {
		super("Fire");
	}

	public boolean superEffective(Character monster) {
		if (monster.getElementType() == "Earth" || monster.getElementType() == "Thunder") {
			return true;
		} else {
			return false;
		}
	}

	public boolean notVeryEffective(Character monster) {
		if (monster.getElementType() == "Water" || monster.getElementType() == "Wind") {
			return true;
		} else {
			return false;
		}
	}

}
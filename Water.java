public class Water extends Element {

	public Water() {
		super("Water");
	}

	public boolean superEffective(Character monster) {
		if (monster.getElementType() == "Fire" || monster.getElementType() == "Wind") {
			return true;
		} else {
			return false;
		}
	}

	public boolean notVeryEffective(Character monster) {
		if (monster.getElementType() == "Earth" || monster.getElementType() == "Thunder") {
			return true;
		} else {
			return false;
		}
	}

}
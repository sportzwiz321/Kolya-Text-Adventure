public class Thunder extends Element {

	public Thunder() {
		super("Thunder");
	}

	public boolean superEffective(Character monster) {
		if (monster.getElementType() == "Water" || monster.getElementType() == "Wind") {
			return true;
		} else {
			return false;
		}
	}

	public boolean notVeryEffective(Character monster) {
		if (monster.getElementType() == "Fire" || monster.getElementType() == "Earth") {
			return true;
		} else {
			return false;
		}
	}

}
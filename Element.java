public class Element {

	private String type;
	private String[] strengths;
	private String[] weaknesses;

	public Element(String type) {
		this.type = type;
		strengths = null;
		weaknesses = null;
	}

	public boolean superEffective(Character monster) {
		return false;
	}

	public boolean notVeryEffective(Character monster) {
		return false;
	}

	public String elementType() {
		return type;
	}

	public String elementStrengths() {

		if (strengths == null) {
			return null;
		} else {
			StringBuffer strength = new StringBuffer();
			for (int x = 0; x < strengths.length; x++) {
				if (x == strengths.length - 1) {
					strength.append(strengths[x]);
				} else {
					strength.append(strengths[x] + ", ");
				}
			}
			return new String(strength);
		}

	}

	public String elementWeaknesses() {

		if (weaknesses == null) {
			return null;
		} else {
			StringBuffer weakness = new StringBuffer();
			for (int x = 0; x < weaknesses.length; x++) {
				if (x == weaknesses.length - 1) {
					weakness.append(weaknesses[x]);
				} else {
					weakness.append(weaknesses[x] + ", ");
				}
			}
			return new String(weakness);
		}

	}

}
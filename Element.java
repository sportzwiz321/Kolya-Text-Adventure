public class Element {

	String type;

	public Element(String type) {
		this.type = type;
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

}
public class Item {

	private String name;
	private int cost;
	private String stat;
	private int statBoost;

	public Item(String name, int cost, String stat, int statBoost) {

		this.name = name;
		this.cost = cost;
		this.stat = stat;
		this.statBoost = statBoost;

	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public String getStat() {
		return stat;
	}

	public int getStatBoost() {
		return statBoost;
	}

	public String toString() {
		return name + ": " + cost + " gold +" + statBoost + " " + stat;
	}

}
import java.util.Random;

public class Location {

	private String[][] location;
	private String[] names;
	private String[] elements;
	private Character badGuy;
	private String finalName;
	private String locationDescription;

	public Location() {

		location = new String[2][3];
		location[0] = new String[]{"The Forest of Death", "The Great Naruto Bridge", "Konoha"};
		location[1] = new String[]{"All who enters shall die", "Where a great warrior was made", "Home of the greatest shinobi in the world"};
		names = new String[]{ "Ash", "Yugi", "Sierra 117"};
		elements = new String[]{"Fire", "Water", "Earth", "Wind", "Thunder"};
		badGuy = null;
		finalName = null;
		locationDescription = null;

	}

	private String newName() {

		int selection = names.length;
		Random rand = new Random();
		int index = rand.nextInt(selection);
		return names[index];

	}

	private void spawnMonster() {

		int selection = elements.length;
		Random rand = new Random();
		int spawn = rand.nextInt(selection);
		Character alien = new Character(newName());
		switch(elements[spawn]) {
			case "Fire": alien.setElement(new Fire());
						break;
			case "Water": alien.setElement(new Water());
						break;
			case "Earth": alien.setElement(new Earth());
						break;
			case "Wind": alien.setElement(new Wind());
						break;
			case "Thunder": alien.setElement(new Thunder());
						break;
			default:
						break;
		}
		badGuy = alien;


	}

	public String getName() {
		return finalName;
	}

	public String getDescription() {
		if (locationDescription == null) {
			return "Ichiraku Ramen";
		} else {
			return locationDescription;
		}
	}

	public void setName() {
		int selection = location[0].length;
		Random rand = new Random();
		int index = rand.nextInt(selection);
		finalName = location[0][index];
		locationDescription = location[1][index];
	}

	public void setMonster(Character alien) {
		badGuy = alien;
	}

	public Character getNewMonster() {
		if (badGuy == null || !badGuy.isBoss()) {
			spawnMonster();
		}
		return badGuy;
	}

	public Character getCurrentMonster() {
		return badGuy;
	}

}
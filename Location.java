import java.util.Random;

public class Location {

	private String[] monsters;
	private Character badGuy;

	public Location() {

		monsters = new String[]{"Fire", "Water", "Earth", "Wind", "Thunder"};
		badGuy = null;

	}

	private void spawnMonster() {

		int selection = monsters.length;
		Random rand = new Random();
		int spawn = rand.nextInt(selection);
		Character alien = new Character(monsters[spawn] + " Alien");
		switch(monsters[spawn]) {
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
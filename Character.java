import javax.swing.*;

public class Character {

	private String name;
	private Element type;
	private int healthPoints, maximumHealthPoints, attackDamage, defense, level;
	private boolean alive;
	private boolean boss;

	public Character(String name) {

		this.name = name;
		type = null;
		healthPoints = 50;
		maximumHealthPoints = 50;
		attackDamage = 10;
		defense = 0;
		alive = true;
		level = 1;
		boss = false;

	}

	public Character(String name, int healthPoints, int maximumHealthPoints, int attackDamage, int defense) {
		this.name = name;
		type = null;
		this.healthPoints = healthPoints;
		this.maximumHealthPoints = maximumHealthPoints;
		this.attackDamage = attackDamage;
		this.defense = defense;
		alive = true;
		level = 1;
		boss = false;
	}

	public boolean isBoss() {
		return boss;
	}

	public void bossEvolve(int difficulty) {
		boss = true;
		levelUp(difficulty + 4);
	}

	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return name + ", " + type.elementType() + " warrior";
	}

	public boolean isAlive() {
		return alive;
	}

	public Element getElement() {
		if (type != null) {
			return type;
		} else {
			return null;
		}
	}

	public String getElementType() {
		if (type != null) {
			return type.elementType();
		} else {
			return "Normal";
		}
	}

	public String attackStrengths() {

		return type.elementStrengths();

	}

	public String attackWeaknesses() {

		return type.elementWeaknesses();

	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public int getDefense() {
		return defense;
	}

	public int getHealthPoints() {
		return healthPoints;
	}

	public int getMaximumHealthPoints() {
		return maximumHealthPoints;
	}

	public String healthPointsRemaining() {
		return healthPoints + "/" + maximumHealthPoints;
	}

	public int attack(Character enemy) {

		float initialDamage = attackDamage - enemy.defense;

		if (enemy.defense > attackDamage) {
			initialDamage = 0;
		}

		int damage;
		int damageDealt;
		boolean doubleDamage = superEffective(this, enemy);
		boolean halfDamage = notVeryEffective(this, enemy);

		if (halfDamage) {
			damage = (int)Math.round(initialDamage * 0.5);
		} else if (doubleDamage) {
			damage = Math.round(initialDamage * 2);
		} else {
			damage = (int)initialDamage;
		}

		if (enemy.healthPoints >= damage + 1) {

			enemy.healthPoints -= damage;

			damageDealt = damage;

		} else {

			damageDealt = enemy.healthPoints;
			enemy.enterDeath();

		}

		return damageDealt;

	}

	public int heal() {

		int recoveredHP;

		if (healthPoints <= maximumHealthPoints - 15) {

			healthPoints += 15;

			recoveredHP = 15;

		} else {

			recoveredHP = maximumHealthPoints - healthPoints;

			healthPoints = maximumHealthPoints;

		}

		return recoveredHP;

	}

	public boolean superEffective(Character attacker, Character defender) {

		return attacker.getElement().superEffective(defender);

	}

	public boolean notVeryEffective(Character attacker, Character defender) {

		return attacker.getElement().notVeryEffective(defender);

	}

	public void levelUp() {

		level += 1;
		strengthen();

	}

	public void levelUp(int levels) {
		int x = levels;
		while(x > 0) {
			levelUp();
			x--;
		}
	}

	public void changeAD(int change) {
		attackDamage += change;
	}

	public void changeARMOR(int change) {
		defense += change;
	}

	public void changeMAXHP(int change) {
		maximumHealthPoints += change;
		healthPoints += change;
	}

	public void weaken() {

		changeMAXHP(-15);
		changeAD(-3);
		if (defense > 0) {
			changeARMOR(-1);
		}

	}

	public void strengthen() {

		changeMAXHP(15);
		changeAD(3);
		changeARMOR(1);

	}

	public void setElement(Element type) {
		this.type = type;
	}

	public void enterDeath() {
		healthPoints = 0;
		alive = false;
	}

}
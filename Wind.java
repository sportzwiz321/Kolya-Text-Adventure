public class Wind extends Character {

	public Wind(String name) {

		super(name);
		element = "Wind";
		healthPoints = 50;
		maximumHealthPoints = 50;
		attackDamage = 15;
		defense = 5;
		strength1 = "Earth";
		strength2 = "Fire";
		weakness1 = "Water";
		weakness2 = "Thunder";

	}

	// public int attack(Character enemy) {

	// 	float initialDamage = attackDamage - enemy.defense;
	// 	int damage;
	// 	int damageDealt;
	// 	boolean doubleDamage = superEffective(this, enemy);
	// 	boolean halfDamage = notVeryEffective(this, enemy);


	// 	if (halfDamage) {
	// 		damage = (int)Math.round(initialDamage * 0.5);
	// 	} else if (doubleDamage) {
	// 		damage = (int)Math.round(initialDamage * 2);
	// 	} else {
	// 		damage = (int)initialDamage;
	// 	}

	// 	if (enemy.healthPoints >= damage + 1) {

	// 		enemy.healthPoints -= damage;

	// 		damageDealt = damage;

	// 	} else {

	// 		damageDealt = enemy.healthPoints;
	// 		enemy.healthPoints = 0;
	// 		enemy.alive = false;

	// 	}

	// 	return damageDealt;

	// }

}
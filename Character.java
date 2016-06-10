import javax.swing.*;

public class Character {

	public String name;
	public String element;
	public int healthPoints, maximumHealthPoints, attackDamage, defense, experience, expToNextLevel, level, gold;
	public boolean alive;
	public String strength1, strength2, weakness1, weakness2;
	private boolean boss;

	public class Node {
		Character monster;
		Node next;

		public Node(Character enemy) {
			monster = enemy;
			next = null;
		}

	}

	private Node head;
	private Node tail;

	public Character(String name) {

		this.name = name;
		element = "Normal";
		healthPoints = 50;
		maximumHealthPoints = 50;
		attackDamage = 10;
		defense = 0;
		alive = true;
		experience = 0;
		expToNextLevel = 100;
		level = 1;
		head = null;
		tail = null;
		strength1 = null;
		strength2 = null;
		weakness1 = null;
		weakness2 = null;
		gold = 0;
		boss = false;

	}

	public Character(String name, int healthPoints, int maximumHealthPoints, int attackDamage, int defense) {
		this.name = name;
		element = "Normal";
		this.healthPoints = healthPoints;
		this.maximumHealthPoints = maximumHealthPoints;
		this.attackDamage = attackDamage;
		this.defense = defense;
		alive = true;
		experience = 0;
		expToNextLevel = 100;
		level = 1;
		head = null;
		tail = null;
		strength1 = null;
		strength2 = null;
		weakness1 = null;
		weakness2 = null;
		gold = 0;
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

	public boolean isAlive() {
		return alive;
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
			enemy.healthPoints = 0;
			enemy.alive = false;

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

		if (attacker instanceof Fire) {
			if (defender instanceof Earth || defender instanceof Thunder) {
				return true;
			}
		} else if (attacker instanceof Water) {
			if (defender instanceof Fire || defender instanceof Wind) {
				return true;
			}
		} else if (attacker instanceof Earth) {
			if (defender instanceof Water || defender instanceof Thunder) {
				return true;
			}
		} else if (attacker instanceof Wind) {
			if (defender instanceof Earth || defender instanceof Fire) {
				return true;
			}
		} else if (attacker instanceof Thunder) {
			if (defender instanceof Water || defender instanceof Wind) {
				return true;
			}
		}

		return false;

	}

	public boolean notVeryEffective(Character attacker, Character defender) {

		if (attacker instanceof Fire) {
			if (defender instanceof Water || defender instanceof Wind) {
				return true;
			}
		} else if (attacker instanceof Water) {
			if (defender instanceof Earth || defender instanceof Thunder) {
				return true;
			}
		} else if (attacker instanceof Earth) {
			if (defender instanceof Fire || defender instanceof Wind) {
				return true;
			}
		} else if (attacker instanceof Wind) {
			if (defender instanceof Thunder || defender instanceof Water) {
				return true;
			}
		} else if (attacker instanceof Thunder) {
			if (defender instanceof Earth || defender instanceof Fire) {
				return true;
			}
		}

		return false;

	}

	public void empower(Item stick) {

		String boost = name + ", your " + stick.getStat() + " has increased by " + stick.getStatBoost() + " points\n";

		switch(stick.getStat()) {
			case "AD": attackDamage += stick.getStatBoost();
					JOptionPane.showMessageDialog(null, boost + "Your current attack damage is now: " + attackDamage);
				break;
			case "ARMOR": defense += stick.getStatBoost();
					JOptionPane.showMessageDialog(null, boost + "Your current defense is now: " + defense);
				break;
			case "HP": maximumHealthPoints += stick.getStatBoost();
					JOptionPane.showMessageDialog(null, boost + "Your current maximum health points is now: " + maximumHealthPoints);
				break;
			default:
				break;
		}

	}

	public Character battle(Character baddie) {

		String action;

		do {
			
			action = "";

			do {

				action = JOptionPane.showInputDialog(null, "Battle!!! " + baddie.name + " has " + baddie.healthPoints + " remaining.\n You have " + healthPoints + " left.\nWhat do you do?\n1) Fight\n2) Recover");
		
			} while(!(action.equals("1") || action.equals("2")));

			if(action.equals("1")) {
				int damageDealt = attack(baddie);
				if (baddie.alive) {
					JOptionPane.showMessageDialog(null, "You hit " + baddie.name + " with a " + element + " strike for " + damageDealt + " damage\nBaddie has " + baddie.healthPoints + " HP left");
				} else {
					JOptionPane.showMessageDialog(null, "You hit " + baddie.name + " with a final " + element + " strike for " + damageDealt + " damage\nYou have killed a baddie! Congratulations on not dying to a baddie...");
				}
				
			} else if (action.equals("2")) {
				int recoveredHP = heal();
				JOptionPane.showMessageDialog(null, "You heal yourself for " + recoveredHP + " HP\nYou now have " + healthPoints + " HP left");
			}

			if (baddie.alive) {

				int damageReceived = baddie.attack(this);

				if (alive) {
					JOptionPane.showMessageDialog(null, baddie.name + " hit you with a " + baddie.element + " strike for " + damageReceived + " damage\nYou have " + healthPoints + " HP left");
				} else {
					JOptionPane.showMessageDialog(null, baddie.name + " hit you with a final " + baddie.element + " strike for " + damageReceived + " damage\nYou have died to a baddie, go home and re-evaluate the way you make life decisions.");
				}

			}

		} while(!(baddie.healthPoints == 0 || healthPoints == 0));

		if (baddie.healthPoints == 0) {

			if (head == null) {
				head = new Node(baddie);
				tail = head;
			} else {
				tail.next = new Node(baddie);
				tail = tail.next;
			}

			JOptionPane.showMessageDialog(null, "You have killed a " + baddie.element + " baddie named " + baddie.name + ", gained 70 experience points, and looted 5 gold!");
			experience += 70;
			gold += 5;
			if (didLevelUp()) {
				
				levelUp();

				JOptionPane.showMessageDialog(null, "You have leveled up to level " + level);

			}

			if (!(baddie.isBoss())) {
				
				JOptionPane.showMessageDialog(null, "You currently have " + experience + " out of " + expToNextLevel + " experience points and " + gold + " gold.\nYou are currently a level " + level + " " + element + " hero");

				JOptionPane.showMessageDialog(null, "So far, you have killed:\n" + toString());

			}

		} else if (healthPoints == 0) {
			// JOptionPane.showMessageDialog(null, "You are no longer priveleged enough to play this game, this time around.\nBetter luck next time...");
		}

		return baddie;

	}

	public boolean didLevelUp() {

		if (experience >= expToNextLevel) {
			return true;
		}

		return false;

	}

	public void levelUp() {

		level += 1;
		experience -= expToNextLevel;
		expToNextLevel += 25;
		attackDamage += 3;
		defense += 3;
		healthPoints += 20;
		maximumHealthPoints += 20;

	}

	public void levelUp(int levels) {
		int x = levels;
		while(x > 0) {
			levelUp();
			x--;
		}
	}

	public String toString() {

		if (head == null) {
			return "NOBODY.";
		} else {
			StringBuffer killList = new StringBuffer();
			Node c = head;
			while (c != null) {
				killList.append(c.monster.name).append("\n");
				c = c.next;
			}
			return new String(killList);
		}

	}

}
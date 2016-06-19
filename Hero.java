import javax.swing.*;

public class Hero extends Character {

	private int experience, expToNextLevel, gold;

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

	public Hero(String name) {

		super(name);
		experience = 0;
		expToNextLevel = 100;

	}

	public int goldPouch() {
		return gold;
	}

	public boolean didLevelUp() {

		if (experience >= expToNextLevel) {
			return true;
		}

		return false;

	}

	public void levelUp() {
		super.levelUp();
		experience -= expToNextLevel;
		expToNextLevel += 25;
	}

	public Character battle(Character baddie) throws PrematureExitGameException {

		String action;

		do {
			
			action = "";

			do {

				action = JOptionPane.showInputDialog(null, "Battle!!! " + baddie + " has " + baddie.healthPointsRemaining() + " HP\n You have " + healthPointsRemaining() + " HP\nWhat do you do?\n1) Fight\n2) Recover");
		
				if (action == null) {
					JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
					throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
				}

			} while(!(action.equals("1") || action.equals("2")));

			if(action.equals("1")) {
				int damageDealt = attack(baddie);
				if (baddie.isAlive()) {
					JOptionPane.showMessageDialog(null, "You hit " + baddie + " with a " + getElementType() + " strike for " + damageDealt + " damage\nBaddie has " + baddie.healthPointsRemaining() + " HP left");
				} else {
					JOptionPane.showMessageDialog(null, "You hit " + baddie + " with a final " + getElementType() + " strike for " + damageDealt + " damage\nYou have killed a baddie! Congratulations on not dying to a baddie...");
				}
				
			} else if (action.equals("2")) {
				int recoveredHP = heal();
				JOptionPane.showMessageDialog(null, "You heal yourself for " + recoveredHP + " HP\nYou now have " + healthPointsRemaining() + " HP left");
			}

			if (baddie.isAlive()) {

				int damageReceived = baddie.attack(this);

				if (isAlive()) {
					JOptionPane.showMessageDialog(null, baddie + " hit you with a " + baddie.getElementType() + " strike for " + damageReceived + " damage\nYou have " + healthPointsRemaining() + " HP left");
				} else {
					JOptionPane.showMessageDialog(null, baddie + " hit you with a final " + baddie.getElementType() + " strike for " + damageReceived + " damage\nYou have died to a baddie, go home and re-evaluate the way you make life decisions.");
				}

			}

		} while(!(baddie.getHealthPoints() == 0 || getHealthPoints() == 0));

		if (baddie.getHealthPoints() == 0) {

			if (head == null) {
				head = new Node(baddie);
				tail = head;
			} else {
				tail.next = new Node(baddie);
				tail = tail.next;
			}

			JOptionPane.showMessageDialog(null, "You have killed a " + baddie.getElementType() + " baddie named " + baddie.getName() + ", gained 70 experience points, and looted 5 gold!");
			experience += 70;
			gold += 5;
			if (didLevelUp()) {
				
				levelUp();

				JOptionPane.showMessageDialog(null, "You have leveled up to level " + getLevel());

			}

			if (!(baddie.isBoss())) {
				
				JOptionPane.showMessageDialog(null, "You currently have " + experience + " out of " + expToNextLevel + " experience points and " + gold + " gold.\nYou are currently a level " + getLevel() + " " + getElementType() + " hero");

				JOptionPane.showMessageDialog(null, "So far, you have killed:\n" + toString());

			}

		}

		return baddie;

	}

	public void empower(Item stick) {

		gold -= stick.getCost();

		String boost = getName() + ", your " + stick.getStat() + " has increased by " + stick.getStatBoost() + " points\n";

		switch(stick.getStat()) {
			case "AD": changeAD(stick.getStatBoost());
					JOptionPane.showMessageDialog(null, boost + "Your current attack damage is now: " + getAttackDamage());
				break;
			case "ARMOR": changeARMOR(stick.getStatBoost());
					JOptionPane.showMessageDialog(null, boost + "Your current defense is now: " + getDefense());
				break;
			case "HP": changeMAXHP(stick.getStatBoost());
					JOptionPane.showMessageDialog(null, boost + "Your current maximum health points is now: " + getMaximumHealthPoints());
				break;
			default:
				break;
		}

	}

	public void commitSeppuku() {
		JOptionPane.showMessageDialog(null, "You, " + getName() + ", no longer wish to live in this world with us\nYou commit Seppuku and writhe uncontrollably and agonizingly to your death");
		enterDeath();
	}

	public String toString() {

		if (head == null) {
			return "NOBODY.";
		} else {
			StringBuffer killList = new StringBuffer();
			Node c = head;
			while (c != null) {
				killList.append(c.monster + "\n");
				c = c.next;
			}
			return new String(killList);
		}

	}



}
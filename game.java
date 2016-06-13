import javax.swing.*;
import java.util.Random;

public class game {

	public static int xPosition = 0;
	public static int yPosition = 0;
	public static Hero joe;
	public static Character grid[][];

	public static void main(String[] args) throws PrematureExitGameException {
		String name,option;
		String element = "";
		Character baddie;
		int killCount = 0;
		boolean gameWon = false;
		int mapx = 5;
		int mapy = 5;
		int error;
		Store lol = new Store();
		lol.insert(new Item("B.F. Sword", 5, "AD", 5));
		lol.insert(new Item("Infinity Edge", 10, "AD", 10));
		lol.insert(new Item("Giant's Belt", 5, "HP", 5));
		lol.insert(new Item("Warmog's Armor", 10, "HP", 10));
		lol.insert(new Item("Cloth Armor", 3, "ARMOR", 3));
		lol.insert(new Item("Thornmail", 12, "ARMOR", 15));
		Character boss = null;

		do {

			name = JOptionPane.showInputDialog(null, "Welcome Adventurer! Before you enter our realm, do tell us more about yourself.\nWhat is your social security number? Credit card information, home address, date and time of birth...\nBut seriously, let's start with your name, what is it?");

			error = 0;

			if (name == null) {
				JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
				throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
			}else if (!name.equals("")) {
				for (int pos = 0; pos < name.length() ; pos++) {

					String c = name.substring(pos, pos + 1);
					switch(c){
						case "!": case "@": case "#": case "$": case "%": case "^": case "&": case "*": case "(": case ")": case "_": case "+": case "=": case "-": case "`": case "~": case ",": case ".": case "/": case "<": case ">": case "?": case ";": case ":": case "'": case "\"": case "[": case "]": case "{": case "}": case "\\": case "|":  error++;
																		break;
						default:
								break;
					}
				}
			}

			if (error > 0) {
				JOptionPane.showMessageDialog(null, "Your name must be alphanumeric, nothing else shall be accepted");
			} else if (name.equals("")) {
				JOptionPane.showMessageDialog(null, "We do not accept invisible people, so invisible names do not count either");
			}
			

		} while(name.equals("") || error > 0);

		JOptionPane.showMessageDialog(null, "Hello " + name + " a pleasure to make your acquaintance");

		int type;

		do {

			option = "";

			while (!tryParseInt(option)) {

				option = JOptionPane.showInputDialog(null, "What elemental affinity will you allign yourself with?\n1) Fire\n2) Water\n3) Earth\n4) Wind\n5) Thunder");

				if (option == null) {
					JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
					throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
				}

			}

			type = Integer.parseInt(option);

		} while (type > 5 || type < 1);

		joe = new Hero(name);

		if (type == 1) {
			joe.setElement(new Fire());
		} else if (type == 2) {
			joe.setElement(new Water());
		} else if (type == 3) {
			joe.setElement(new Earth());
		} else if (type == 4) {
			joe.setElement(new Wind());
		} else if (type == 5) {
			joe.setElement(new Thunder());
		}

		String difficulty;
		int dif;

		do {

			difficulty = "";

			while(!tryParseInt(difficulty)) {

				difficulty = JOptionPane.showInputDialog(null, "What difficulty would you like to play on?\n1. Easy\n2. Normal\n3. Hard");

				if (difficulty == null) {
					JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
					throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
				}

			}

			dif = Integer.parseInt(difficulty) - 1;

		} while(dif > 2 || dif < 0);

		if (dif == 0) {
			difficulty = "Easy";
		} else if (dif == 1) {
			difficulty = "Normal";
		} else {
			difficulty = "Hard";
		}
		mapx -= dif;
		mapy -= dif;

		JOptionPane.showMessageDialog(null, "Name: " + joe.getName() + "\nElement: " + joe.getElementType() + "\nDifficulty: " + difficulty + "\nHP: " + joe.getHealthPoints() + "\nGold: " + joe.goldPouch() + "\nYou are strong against elements: " + joe.attackStrengths() + "\nYou are weak against elements: " + joe.attackWeaknesses());
		System.out.println(joe.getName());
		System.out.println(joe.getElementType());
		String menuAction;
		grid = new Character[mapx][mapy];

		for (int y = 0 ; y < mapy ; y++) {
			for (int x = 0 ; x < mapx ; x++) {
				Character monster;
				if (x == mapx - 1 && y == mapy - 1) {

					Random rand = new Random();
					int selector = rand.nextInt(5) + 1;

					switch (selector) {
						case 1: monster = new Character("Sasuke, the Avenger Hokage");
								monster.setElement(new Fire());
								break;
						case 2: monster = new Character("Zabuza, the Demon Mizukage");
								monster.setElement(new Water());
								break;
						case 3: monster = new Character("Gaara, the Sand Beastly Kazekage");
								monster.setElement(new Earth());
								break;
						case 4: monster = new Character("Naruto, the Future Kuncklehead Hokage");
								monster.setElement(new Wind());
								break;
						case 5: monster = new Character("Kakashi, the Copycat Hokage");
								monster.setElement(new Thunder());
								break;
						default: monster = new Character("Ninja");
								break;
					}

					monster.bossEvolve(dif);

					boss = monster;

					grid[x][y] = monster;

					System.out.println("We have a " + monster.getName() + " at position " + x + "," + y);

				} else if (!(x == 0 && y == 0)) {
					Random rand = new Random();
					int selector = rand.nextInt(5) + 1;

					switch (selector) {
						case 1: monster = new Character("Blaziken, the Avenger");
								monster.setElement(new Fire());
								break;
						case 2: monster = new Character("Feraligator, the Demon in the Mist");
								monster.setElement(new Water());
								break;
						case 3: monster = new Character("Torterra, the Sand Beast");
								monster.setElement(new Earth());
								break;
						case 4: monster = new Character("Swello, the Future Hokage");
								monster.setElement(new Wind());
								break;
						case 5: monster = new Character("Zapdos, the Copycat Ninja");
								monster.setElement(new Thunder());
								break;
						default: monster = new Character("Ninja");
								break;
					}

					if (dif == 0) {
						monster.weaken();
					} else if (dif == 2) {
						monster.strengthen();
					}
					
					grid[x][y] = monster;

					System.out.println("We have a " + monster.getName() + " at position " + x + "," + y);

				}
				
			}
		}

		boolean starting = true;

		do{

			if (xPosition == 0 && yPosition == 0 && !starting) {
				shop(lol);
			}

			starting = false;

			String options[] = new String[5];

			if (xPosition < mapx - 1) {

				if (options[0] == null) {
					options[0] = "Go East";
				} else if (options[1] == null) {
					options[1] = "Go East";
				} else if (options[2] == null) {
					options[2] = "Go East";
				} else if (options[3] == null) {
					options[3] = "Go East";
				}

			}

			if (yPosition < mapy - 1) {

				if (options[0] == null) {
					options[0] = "Go North";
				} else if (options[1] == null) {
					options[1] = "Go North";
				} else if (options[2] == null) {
					options[2] = "Go North";
				} else if (options[3] == null) {
					options[3] = "Go North";
				}

			}

			if (xPosition > 0) {

				if (options[0] == null) {
					options[0] = "Go West";
				} else if (options[1] == null) {
					options[1] = "Go West";
				} else if (options[2] == null) {
					options[2] = "Go West";
				} else if (options[3] == null) {
					options[3] = "Go West";
				}

			}

			if (yPosition > 0) {

				if (options[0] == null) {
					options[0] = "Go South";
				} else if (options[1] == null) {
					options[1] = "Go South";
				} else if (options[2] == null) {
					options[2] = "Go South";
				} else if (options[3] == null) {
					options[3] = "Go South";
				}

			}

			String choice = "What would you like to do? You are currently at " + xPosition + "," + yPosition + "\n";

			int index = 1;

			for (int j = 0; j < 4 ; j++ ) {
				if (!(options[j] == null)) {

					Character foe;
					switch(options[j]) {

						case "Go East": foe = grid[xPosition + 1][yPosition];
										break;
						case "Go West": foe = grid[xPosition - 1][yPosition];
										break;
						case "Go North":foe = grid[xPosition][yPosition + 1];
										break;
						case "Go South":foe = grid[xPosition][yPosition - 1];
										break;
						default:		foe = new Character("Rock Lee");
										break;

					}

					if (foe == null) {
						choice = choice + index + ". " + options[j] + " to return to the Hidden Leaf Village\n";
					} else if (foe.isAlive()) {
						choice = choice + index + ". " + options[j] + " to fight " + foe.getName() + ", " + foe.getElementType() + " ninja\n";
					} else {
						choice = choice + index + ". " + options[j] + " to the remains of " + foe.getName() + "\n";
					}

					index += 1;
				}
			}

			options[index-1] = "Commit Seppuku";

			choice = choice + index + ". Commit Seppuku";

			String selection;
			int sel = 0;

			if (index == 3) {
				
				do {

					selection = "";

					while(!tryParseInt(selection)) {

						selection = JOptionPane.showInputDialog(null, choice);

						if (selection == null) {
							JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
							throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
						}

					}

					sel = Integer.parseInt(selection);

				} while(sel > 3 || sel < 1);

			} else if (index == 4) {

				do {

					selection = "";

					while(!tryParseInt(selection)) {

						selection = JOptionPane.showInputDialog(null, choice);

						if (selection == null) {
							JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
							throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
						}

					}

					sel = Integer.parseInt(selection);

				} while(sel > 4 || sel < 1);

			} else if (index == 5) {

				do {

					selection = "";

					while(!tryParseInt(selection)) {

						selection = JOptionPane.showInputDialog(null, choice);

						if (selection == null) {
							JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
							throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
						}

					}

					sel = Integer.parseInt(selection);

				} while(sel > 5 || sel < 1);

			}

			move(options[sel-1]);


		} while(!((joe.getHealthPoints() == 0) || (grid[mapy-1][mapx-1].getHealthPoints() == 0)));

		if (grid[mapy-1][mapx-1].getHealthPoints() == 0) {
			JOptionPane.showMessageDialog(null, "You have won the game, by slaying " + boss.getName() + "\nCongratulations!");
			JOptionPane.showMessageDialog(null, "Along the way, you managed to slay:\n" + joe.toString());
		} else if (joe.getHealthPoints() == 0) {
			JOptionPane.showMessageDialog(null, "You are no longer priveleged enough to play this game, this time around.\nBetter luck next time...");
			JOptionPane.showMessageDialog(null, "Before you died, you managed to kill:\n" + joe.toString());
		}

	}

	public static void move(String direction) {

		switch(direction) {

			case "Go East": moveRight();
							break;
			case "Go West": moveLeft();
							break;
			case "Go North": moveUp();
							break;
			case "Go South": moveDown();
							break;
			case "Commit Seppuku":	joe.commitSeppuku();
									break;
			default: JOptionPane.showMessageDialog(null, "You have gone nowhere!");
							break;

		}

	}

	public static void moveRight() {

		xPosition += 1;
		fight();

	}

	public static void moveLeft() {

		xPosition -= 1;
		fight();

	}

	public static void moveUp() {
		
		yPosition += 1;
		fight();

	}

	public static void moveDown() {

		yPosition -= 1;
		fight();

	}

	public static void fight() {
		
		if(yPosition == 0 && xPosition == 0) {
			JOptionPane.showMessageDialog(null, "You have returned to the hidden leaf village.");
			return;
		}

		Character ninja = grid[xPosition][yPosition];

		if(ninja.isAlive() == true){
			JOptionPane.showMessageDialog(null, "A level " + ninja.getLevel() + " " + ninja.getName() + " appears before you");
			ninja = joe.battle(ninja);
		} else {
			JOptionPane.showMessageDialog(null, "Master " + ninja.getName() + "'s dead corpse sits at your feet");
		}

	}

	public static void shop(Store league) throws PrematureExitGameException {

		String input;
		do {
			input = JOptionPane.showInputDialog(null, "Welcome to my humble shop.\nLet's just get this out of the way\nYes, most of my merchandise was ripped from the hands of dead adventurers.\nYou won't find better merchandise anywhere else! I've made sure of that.\nSo would you like to:\n1. Buy Something\n2. Or Leave");
		
			if (input == null) {
				JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
				throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
			}

		} while (!(input.equals("1") || input.equals("2")));
		
	 	if (input.equals("1")) {
			String lolshop;
			String item;
			Item[] hextech = league.itemCostList();
			StringBuffer itemList = new StringBuffer();

			for (int x = 1; x < hextech.length + 1; x++) {
				itemList.append(x + ". " + hextech[x-1] + "\n");
			}

			String lol = new String(itemList);

			int index;

			do {

				lolshop = "";

				while(!tryParseInt(lolshop)) {

					lolshop = JOptionPane.showInputDialog(null, "I'm always happy to take your money!\nYou have: " + joe.goldPouch() + " gold\nThese are the items in my shop:\n" + itemList + "And if you don't want anything, then just\n" + (league.storeSize() + 1) + ". Leave the store");

					if (lolshop == null) {
					
						JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
						throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");

					}

				}

				index = Integer.parseInt(lolshop);

			} while (index > league.storeSize() + 1 || index < 1);
			
			if(index == league.storeSize() + 1) {
				JOptionPane.showMessageDialog(null, "You scamper from the store\nhoping to pillage more gold\nbefore this amazing sale ends");
				return;
			} else {
				Item stick = hextech[index - 1];

				int cost = stick.getCost();
				if (cost > joe.goldPouch()) {
					JOptionPane.showMessageDialog(null, "Insufficient gold for a " + stick.getName() + "\nPlease come again later.");
				} else {
					joe.empower(stick);
					JOptionPane.showMessageDialog(null, "You have bought a " + stick.getName() + " for " + stick.getCost() + " gold\nYou now have " + joe.goldPouch() + " gold remaining.");
				}
			}

		} else if (input.equals("2")) {
			JOptionPane.showMessageDialog(null, "You scamper from the store\nhoping to pillage more gold\nbefore this amazing sale ends");
			return;
		}

	}

	public static boolean tryParseInt(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}



}
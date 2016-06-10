import javax.swing.*;
import java.util.Random;

public class game {

	public static int xPosition = 0;
	public static int yPosition = 0;
	public static Character joe;
	public static Character grid[][];

	public static void main(String[] args) throws PrematureExitGameException {
		String name,option;
		String element = "";
		Character baddie;
		int killCount = 0;
		boolean gameWon = false;
		int mapx = 3;
		int mapy = 3;
		int error;
		Store lol = new Store();
		lol.insert("B.F. Sword", 5, "AD", 5);
		lol.insert("Infinity Edge", 10, "AD", 10);
		lol.insert("Giant's Belt", 5, "HP", 5);
		lol.insert("Warmog's Armor", 10, "HP", 10);
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

		option = "";

		do {

			while (!tryParseInt(option)) {

				option = JOptionPane.showInputDialog(null, "What elemental affinity will you allign yourself with?\n1) Fire\n2) Water\n3) Earth\n4) Wind\n5) Thunder");

				if (option == null) {
					JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
					throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
				}

			}

			int type = Integer.parseInt(option);

		} while (type > 5 || type < 1);

		if (type == 1) {
			joe = new Fire(name);
		} else if (type == 2) {
			joe = new Water(name);
		} else if (type == 3) {
			joe = new Earth(name);
		} else if (type == 4) {
			joe = new Wind(name);
		} else if (type == 5) {
			joe = new Thunder(name);
		}

		String difficulty;

		do {

			difficulty = JOptionPane.showInputDialog(null, "What difficulty would you like to play on?\n1. Easy\n2. Normal\n3. Hard");

			if (option == null) {
				JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
				throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
			}

		} while(!(option.equals("1") || option.equals("2") || option.equals("3")));

		int dif = Integer.parseInt(difficulty) - 1;
		if (dif == 0) {
			difficulty = "Easy";
		} else if (dif == 1) {
			difficulty = "Normal";
		} else {
			difficulty = "Hard";
		}

		JOptionPane.showMessageDialog(null, "Name: " + joe.name + "\nElement: " + joe.element + "\nDifficulty: " + difficulty + "\nHP: " + joe.healthPoints + "\nGold: " + joe.gold + "\nYou are strong against elements: " + joe.strength1 + " and " + joe.strength2 + "\nYou are weak against elements: " + joe.weakness1 + " and " + joe.weakness2);
		System.out.println(joe.name);
		System.out.println(joe.element);
		String menuAction;
		grid = new Character[mapy][mapx];

		for (int y = 0 ; y < mapy ; y++) {
			for (int x = 0 ; x < mapx ; x++) {
				Character monster;
				if (y == mapy - 1 && x == mapx -1) {

					Random rand = new Random();
					int selector = rand.nextInt(5) + 1;

					switch (selector) {
						case 1: monster = new Fire("Sasuke, the Avenger Hokage");
								break;
						case 2: monster = new Water("Zabuza, the Demon Mizukage");
								break;
						case 3: monster = new Earth("Gaara, the Sand Beastly Kazekage");
								break;
						case 4: monster = new Wind("Naruto, the Future Kuncklehead Hokage");
								break;
						case 5: monster = new Thunder("Kakashi, the Copycat Hokage");
								break;
						default: monster = new Character("Ninja");
								break;
					}

					monster.bossEvolve(dif);

					boss = monster;

					grid[y][x] = monster;

					System.out.println("We have a " + monster.name + " at position " + y + "," + x);

				} else if (!(y == 0 && x == 0)) {
					Random rand = new Random();
					int selector = rand.nextInt(5) + 1;

					switch (selector) {
						case 1: monster = new Fire("Sasuke, the Avenger");
								break;
						case 2: monster = new Water("Zabuza, the Demon in the Mist");
								break;
						case 3: monster = new Earth("Gaara, the Sand Beast");
								break;
						case 4: monster = new Wind("Naruto, the Future Hokage");
								break;
						case 5: monster = new Thunder("Kakashi, the Copycat Ninja");
								break;
						default: monster = new Character("Ninja");
								break;
					}

					if (dif > 0) {
						monster.levelUp(dif);
					}
					
					grid[y][x] = monster;

					System.out.println("We have a " + monster.name + " at position " + y + "," + x);

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
					options[0] = "Go South";
				} else if (options[1] == null) {
					options[1] = "Go South";
				} else if (options[2] == null) {
					options[2] = "Go South";
				} else if (options[3] == null) {
					options[3] = "Go South";
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
					options[0] = "Go North";
				} else if (options[1] == null) {
					options[1] = "Go North";
				} else if (options[2] == null) {
					options[2] = "Go North";
				} else if (options[3] == null) {
					options[3] = "Go North";
				}

			}

			String choice = "What would you like to do? You are currently at " + yPosition + "," + xPosition + "\n";

			int index = 1;

			for (int j = 0; j < 4 ; j++ ) {
				if (!(options[j] == null)) {

					Character foe;
					switch(options[j]) {

						case "Go East": foe = grid[yPosition][xPosition + 1];
										break;
						case "Go West": foe = grid[yPosition][xPosition - 1];
										break;
						case "Go North":foe = grid[yPosition - 1][xPosition];
										break;
						case "Go South":foe = grid[yPosition + 1][xPosition];
										break;
						default:		foe = new Character("Rock Lee");
										break;

								}

					if (foe == null) {
						choice = choice + index + ". " + options[j] + " to return to the Hidden Leaf Village\n";
					} else if (foe.alive) {
						choice = choice + index + ". " + options[j] + " to fight " + foe.name + ", " + foe.element + " ninja\n";
					} else {
						choice = choice + index + ". " + options[j] + " to the remains of " + foe.name + "\n";
					}

					index += 1;
				}
			}

			options[index-1] = "Commit Seppuku";

			choice = choice + index + ". Commit Seppuku";

			String selection;

			if (index == 3) {
				
				do{

					selection = JOptionPane.showInputDialog(null, choice);

					if (selection == null) {
						JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
						throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
					}

				} while(!(selection.equals("1") || selection.equals("2") || selection.equals("3")));

				int sel = Integer.parseInt(selection);

				switch(sel) {
					case 1: move(options[0]);
							break;
					case 2: move(options[1]);
							break;
					case 3: move(options[2]);
							break;
					default:
							break;
				}

			} else if (index == 4) {

				do{

					selection = JOptionPane.showInputDialog(null, choice);

					if (selection == null) {
						JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
						throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
					}

				} while(!(selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("4")));

				int sel = Integer.parseInt(selection);

				switch(sel) {
					case 1: move(options[0]);
							break;
					case 2: move(options[1]);
							break;
					case 3: move(options[2]);
							break;
					case 4: move(options[3]);
							break;
					default:
							break;
				}

			} else if (index == 5) {

				do{

					selection = JOptionPane.showInputDialog(null, choice);

					if (selection == null) {
						JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
						throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
					}

				} while(!(selection.equals("1") || selection.equals("2") || selection.equals("3") || selection.equals("4") || selection.equals("5")));

				int sel = Integer.parseInt(selection);

				switch(sel) {
					case 1: move(options[0]);
							break;
					case 2: move(options[1]);
							break;
					case 3: move(options[2]);
							break;
					case 4: move(options[3]);
							break;
					case 5: move(options[4]);
							break;
					default:
							break;
				}

			}


		} while(!((joe.healthPoints == 0) || (grid[mapy-1][mapx-1].healthPoints == 0)));

		if (grid[mapy-1][mapx-1].healthPoints == 0) {
			JOptionPane.showMessageDialog(null, "You have won the game, by slaying " + boss.getName() + "\nCongratulations!");
			JOptionPane.showMessageDialog(null, "Along the way, you managed to slay:\n" + joe.toString());
		} else if (joe.healthPoints == 0) {
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
			case "Commit Seppuku":	joe.healthPoints = 0;
									JOptionPane.showMessageDialog(null, "You, " + joe.name + ", no longer wish to live in this world with us\nYou commit Seppuku and writhe uncontrollably and agonizingly to your death");
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
		
		yPosition -= 1;
		fight();

	}

	public static void moveDown() {

		yPosition += 1;
		fight();

	}

	public static void fight() {
		
		if(yPosition == 0 && xPosition == 0) {
			JOptionPane.showMessageDialog(null, "You have returned to the hidden leaf village.");
			return;
		}

		Character ninja = grid[yPosition][xPosition];

		if(ninja.alive == true){
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
			Item[] hextech = league.itemStats();
			StringBuffer itemList = new StringBuffer();

			for (int x = 1; x < hextech.length + 1; x++) {
				itemList.append(x + ". " + hextech[x-1] + "\n");
			}

			String lol = new String(itemList);

			do {

				while(!tryParseInt(lolshop)) {

					lolshop = JOptionPane.showInputDialog(null, "I'm always happy to take your money!\nYou have: " + joe.gold + " gold\nThese are the items in my shop:\n" + itemList + "And if you don't want anything, then just\n5. Leave the store");

					if (lolshop == null) {
					
						JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
						throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");

					}

				}

				int index = Integer.parseInt(lolshop);

			} while (index > 5 || index < 1);

			do {

				lolshop = JOptionPane.showInputDialog(null, "I'm always happy to take your money!\nYou have: " + joe.gold + " gold\nThese are the items in my shop:\n" + itemList + "And if you don't want anything, then just\n5. Leave the store");

				if (lolshop == null) {
				
					JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
					throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");

				}

			} while(!(lolshop.equals("1") || lolshop.equals("2") || lolshop.equals("3") || lolshop.equals("4") || lolshop.equals("5")));
			
			if (lolshop.equals("5")) {
				JOptionPane.showMessageDialog(null, "You scamper from the store\nhoping to pillage more gold\nbefore this amazing sale ends");
				return;
			} else {

				int index = Integer.parseInt(lolshop);
				Item stick = league.retrieveItem(index);

				int cost = stick.getCost();
				if (cost > joe.gold) {
					JOptionPane.showMessageDialog(null, "Insufficient gold for a " + stick.getName() + "\nPlease come again later.");
				} else {
					joe.gold -= cost;
					JOptionPane.showMessageDialog(null, "You have bought a " + stick.getName() + " for " + stick.getCost() + " gold\nYou now have " + joe.gold + " gold remaining.");
					joe.empower(stick);
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
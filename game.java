import javax.swing.*;
import java.util.Random;

public class game {

	public static int xPosition = 0;
	public static int yPosition = 0;
	public static Character joe;
	public static Character grid[][];

	public static void main(String[] args) throws PrematureExitGameException {
		// String name = JOptionPane.showInputDialog(null, "What is your name?");
		// String option = JOptionPane.showInputDialog(null, "Choose your elemental affinity:\n1) Fire\n2) Water\n3) Earth\n4) Wind\n5) Thunder");
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
				// return
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

		do {

			option = JOptionPane.showInputDialog(null, "What elemental affinity will you allign yourself with?\n1) Fire\n2) Water\n3) Earth\n4) Wind\n5) Thunder");

			if (option == null) {
				JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
				throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
			}

		} while(!(option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4") || option.equals("5")));

		if (option.equals("1")) {
			// element = "Fire";
			joe = new Fire(name);
		} else if (option.equals("2")) {
			// element = "Water";
			joe = new Water(name);
		} else if (option.equals("3")) {
			// element = "Earth";
			joe = new Earth(name);
		} else if (option.equals("4")) {
			// element = "Wind";
			joe = new Wind(name);
		} else if (option.equals("5")) {
			// element = "Thunder";
			joe = new Thunder(name);
		} else {
			// element = "Normal";
			joe = new Character(name);
		}
		// Character joe = new Character(name, element);

		String difficulty;

		do {

			difficulty = JOptionPane.showInputDialog(null, "What difficulty would you like to play on?\n1. Easy\n2. Normal\n3. Hard");

			if (option == null) {
				JOptionPane.showMessageDialog(null, "You have quit the game prematurely, I hope you die.");
				throw new PrematureExitGameException("You have quit the game prematurely, I hope you die.");
			}

		} while(!(option.equals("1") || option.equals("2") || option.equals("3")));

		int dif = Integer.parseInt(difficulty) - 1;


		JOptionPane.showMessageDialog(null, "Name: " + joe.name + "\nElement: " + joe.element + "\nHP: " + joe.healthPoints + "\nGold: " + joe.gold + "\nYou are strong against elements: " + joe.strength1 + " and " + joe.strength2 + "\nYou are weak against elements: " + joe.weakness1 + " and " + joe.weakness2);
		System.out.println(joe.name);
		System.out.println(joe.element);
		String menuAction;
		grid = new Character[mapy][mapx];

		// Water baddie = new Water("Baddie");

		for (int y = 0 ; y < mapy ; y++) {
			for (int x = 0 ; x < mapx ; x++) {
				Character monster;
				if (y == mapy - 1 && x == mapx -1) {

					// monster = new Character("Old Granny Tsunade, the Cranky Current Hokage", 200, 200, 20, 10);


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

					// choice = choice + index + ". " + options[j] + "\n";
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

				// if (selection.equals("1")) {
				// 	move(options[0]);
				// } else if (selection.equals("2")) {
				// 	move(options[1]);
				// } else if (selection.equals("3")) {
				// 	move(options[2]);
				// }

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

				// if (selection.equals("1")) {
				// 	move(options[0]);
				// } else if (selection.equals("2")) {
				// 	move(options[1]);
				// } else if (selection.equals("3")) {
				// 	move(options[2]);
				// } else if (selection.equals("4")) {
				// 	move(options[3]);
				// }

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

				// if (selection.equals("1")) {
				// 	move(options[0]);
				// } else if (selection.equals("2")) {
				// 	move(options[1]);
				// } else if (selection.equals("3")) {
				// 	move(options[2]);
				// } else if (selection.equals("4")) {
				// 	move(options[3]);
				// } else if (selection.equals("5")) {
				// 	move(options[4]);
				// }

			}


		} while(!((joe.healthPoints == 0) || (grid[mapy-1][mapx-1].healthPoints == 0)));

		if (grid[mapy-1][mapx-1].healthPoints == 0) {
			JOptionPane.showMessageDialog(null, "You have won the game, by slaying " + boss.getName() + "\nCongratulations!");
			JOptionPane.showMessageDialog(null, "Along the way, you managed to slay:\n" + joe.toString());
		} else if (joe.healthPoints == 0) {
			JOptionPane.showMessageDialog(null, "You are no longer priveleged enough to play this game, this time around.\nBetter luck next time...");
			JOptionPane.showMessageDialog(null, "Before you died, you managed to kill:\n" + joe.toString());
		}

		// moveRight();

	// 	do {

	// 		do {

	// 			menuAction = JOptionPane.showInputDialog(null, "You have killed " + killCount + " baddie(s) so far. What would you like to do next?\n1. Fight Another Baddie\n2. Fight Da' Boss\n3. Show Me My Stats\n4. Quit Like the Loser that You Are");
			
	// 		} while(!(menuAction.equals("1") || menuAction.equals("2") || menuAction.equals("3") || menuAction.equals("4")));

	// 		if (menuAction.equals("1")) {

	// 			baddie = new Fire("Baddie");

	// 			JOptionPane.showMessageDialog(null, "You (" + joe.name  + ") are now fighting a " + baddie.element + " " + baddie.name);

	// 			joe = battle(joe,baddie);

	// 			if (joe.healthPoints == 0) {
					
	// 				JOptionPane.showMessageDialog(null, "You have died! Therfore, you have lost the privellege to continue playings this game. Sucker!!! :P");

	// 			} else {

	// 				joe.experience += 69;

	// 				gold += 10;

	// 				JOptionPane.showMessageDialog(null, "You have collected 10 gold from killing that " + baddie.element + " " + baddie.name + "\nYou now have " + gold + " pieces of gold.");

	// 				if (joe.didLevelUp()) {

	// 					joe.levelUp();

	// 					JOptionPane.showMessageDialog(null, "You have leveled up to level " + joe.level);

	// 				}

	// 				JOptionPane.showMessageDialog(null, "You currently have " + joe.experience + " out of " + joe.expToNextLevel + " experience points.\nYou are currently a level " + joe.level + " " + joe.element + " hero");

	// 				killCount += 1;

	// 			}

	// 		} else if (menuAction.equals("2")) {

	// 			baddie = new Character("Badass Baddie", 200, 200, 20, 10);

	// 			JOptionPane.showMessageDialog(null, "You are now fighting a " + baddie.element + " " + baddie.name);

	// 			joe = battle(joe, baddie);

	// 			if (joe.healthPoints == 0) {
					
	// 				JOptionPane.showMessageDialog(null, "You have died! Therfore, you have lost the privellege to continue playings this game. Sucker!!! :P");

	// 			} else {

	// 				gameWon = true;
	// 				killCount += 1;

	// 			}

	// 		} else if (menuAction.equals("3")) {
	// 			String nameLabel = "Name: " + joe.name + "\n";
	// 			String elementLabel = "Element: " + joe.element + "\n";
	// 			String levelLabel = "Level: " + joe.level + "\n";
	// 			String baddiesKilledLabel = "Kills: " + killCount + "\n";
	// 			String goldLabel = "Gold: " + gold + "\n";
	// 			String healthLabel = "HP: " + joe.healthPoints + "\n";
	// 			String maximumHealthPointsLabel = "MAX HP: " + joe.maximumHealthPoints + "\n";
	// 			String attackDamageLabel = "ATK: " + joe.attackDamage + "\n";
	// 			String defenseLabel = "DEF: " + joe.defense + "\n";
	// 			String expToNextLevelLabel = "Experience to level up: " + (joe.expToNextLevel - joe.experience);

	// 			JOptionPane.showMessageDialog(null, nameLabel + elementLabel + levelLabel + baddiesKilledLabel + goldLabel + healthLabel + maximumHealthPointsLabel + attackDamageLabel + defenseLabel + expToNextLevelLabel);
	// 		}

	// 	} while(!(menuAction.equals("4") || joe.healthPoints == 0 || gameWon));


	// 	if (menuAction.equals("4")) {

	// 		JOptionPane.showMessageDialog(null, "You are a quitter, sore loser, and I never wanted you to play my game anyways!\nHmph! Good bye loser!");

	// 	} else if (gameWon) {

	// 		JOptionPane.showMessageDialog(null, "Congratulations!! You have beat the game. Go find yourself another game that you are less good at.");
	// 		JOptionPane.showMessageDialog(null, "You needed to kill " + killCount + " baddies to win the game. Can you beat your high score?");

	// 	}
	// 	// JOptionPane.showMessageDialog(null, "You are now fighting a " + baddie.element + " " + baddie.name);

		

	}

	// public static Character battle(Character hero, Character baddie) {

	// 	String action;

	// 	do {

	// 		action = "";

	// 		do {

	// 			action = JOptionPane.showInputDialog(null, "Battle!!! " + baddie.name + " has " + baddie.healthPoints + " remaining.\n You have " + hero.healthPoints + " left.\nWhat do you do?\n1) Fight\n2) Recover");
		
	// 		} while(!(action.equals("1") || action.equals("2")));

	// 		if(action.equals("1")) {
	// 			int damageDealt = hero.attack(baddie);
	// 			if (baddie.alive) {
	// 				JOptionPane.showMessageDialog(null, "You hit " + baddie.name + " with a " + hero.element + " strike for " + damageDealt + " damage\nBaddie has " + baddie.healthPoints + " HP left");
	// 			} else {
	// 				JOptionPane.showMessageDialog(null, "You hit " + baddie.name + " with a final " + hero.element + " strike for " + damageDealt + " damage\nYou have killed a baddie! Congratulations on not dying to a baddie...");
	// 			}
				
	// 		} else if (action.equals("2")) {
	// 			int recoveredHP = hero.heal();
	// 			JOptionPane.showMessageDialog(null, "You heal yourself for " + recoveredHP + " HP\nYou now have " + hero.healthPoints + " HP left");
	// 		}

	// 		if (baddie.alive) {

	// 			int damageReceived = baddie.attack(hero);

	// 			if (hero.alive) {
	// 				JOptionPane.showMessageDialog(null, baddie.name + " hit you with a " + baddie.element + " strike for " + damageReceived + " damage\nYou have " + hero.healthPoints + " HP left");
	// 			} else {
	// 				JOptionPane.showMessageDialog(null, baddie.name + " hit you with a final " + baddie.element + " strike for " + damageReceived + " damage\nYou have died to a baddie, go home and re-evaluate the way you make life decisions.");
	// 			}

	// 		}

	// 	} while(!(baddie.healthPoints == 0 || hero.healthPoints == 0));

	// 	if (baddie.healthPoints == 0) {
	// 		JOptionPane.showMessageDialog(null, "You have killed a " + baddie.element + " baddie named " + baddie.name + " and gained 69 experience points!");
	// 	}

	// 	return hero;

	// }

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
			// Character ninja = grid[yPosition][xPosition];
			// if(ninja.alive == true){
			// 	System.out.println("Master " + ninja.name + " is waiting to fight you");
			// 	ninja = battle(joe, ninja);
			// } else {
			// 	System.out.println("Master " + ninja.name + "'s dead corpse sits at your feet");
			// }

			xPosition += 1;
			fight();

		}

		public static void moveLeft() {
			// Character ninja = grid[yPosition][xPosition];
			// if(ninja.alive == true){
			// 	System.out.println("Master " + ninja.name + " is waiting to fight you");
			// 	ninja = battle(joe, ninja);
			// } else {
			// 	System.out.println("Master " + ninja.name + "'s dead corpse sits at your feet");
			// }
			xPosition -= 1;
			fight();
		}

		public static void moveUp() {
			// Character ninja = grid[yPosition][xPosition];
			// if(ninja.alive == true){
			// 	System.out.println("Master " + ninja.name + " is waiting to fight you");
			// 	joe = battle(joe, ninja);
			// } else {
			// 	System.out.println("Master " + ninja.name + "'s dead corpse sits at your feet");
			// }
			yPosition -= 1;
			fight();
		}

		public static void moveDown() {
			// Character ninja = grid[yPosition][xPosition];
			// if(ninja.alive == true){
			// 	System.out.println("Master " + ninja.name + " is waiting to fight you");
			// 	joe = battle(joe, ninja);
			// } else {
			// 	System.out.println("Master " + ninja.name + "'s dead corpse sits at your feet");
			// }

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
				// JOptionPane.showMessageDialog(null, "Master " + ninja.name + " is waiting to fight you");
				ninja = joe.battle(ninja);
			} else {
				JOptionPane.showMessageDialog(null, "Master " + ninja.name + "'s dead corpse sits at your feet");
			}
			grid[yPosition][xPosition] = ninja;

			if (yPosition == 2 && xPosition == 2 && ninja.alive == false) {
				// JOptionPane.showMessageDialog(null, "You have won the game, by slaying Old Granny Tsunade\nCongratulations!");
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
					// switch(lolshop) {
					// 	case "1": item = "B.F. Sword";
					// 			break;
					// 	case "2": item = "Giant's Belt";
					// 			break;
					// 	case "3": item = "Infinity Edge";
					// 			break;
					// 	case "4": item = "Warmog's Armor";
					// 			break;
					// 	default: item = "Mejai's";
					// 			break;
					// }

					int index = Integer.parseInt(lolshop);
					Item stick = league.retrieveItem(index);
					// item = league.selectItem(index);

					int cost = stick.getCost();
					if (cost > joe.gold) {
						JOptionPane.showMessageDialog(null, "Insufficient gold for a " + stick.getName() + "\nPlease come again later.");
					} else {
						joe.gold -= cost;
						JOptionPane.showMessageDialog(null, "You have bought a " + stick.getName() + " for " + stick.getCost() + " gold\nYou now have " + joe.gold + " gold remaining.");
						joe.empower(stick);
						// switch(stat) {
						// 	case "AD": joe.attackDamage += boost;
						// 			JOptionPane.showMessageDialog(null, "Your current attack damage is now: " + joe.attackDamage);
						// 			break;
						// 	case "ARMOR": joe.defense += boost;
						// 			JOptionPane.showMessageDialog(null, "Your current defense is now: " + joe.defense);
						// 			break;
						// 	case "HP": joe.healthPoints += boost;
						// 			JOptionPane.showMessageDialog(null, "Your current maximum health points is now: " + joe.healthPoints);
						// 			break;
						// 	default:
						// 			break;
						// }
					}

				}


			} else if (input.equals("2")) {
				JOptionPane.showMessageDialog(null, "You scamper from the store\nhoping to pillage more gold\nbefore this amazing sale ends");
				return;
			}

		}

		// public static String[] options() {

		// 	if () {
				
		// 	}

		// }



}
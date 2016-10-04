package cosc344.utils;

import cosc344.models.*;
import cosc344.services.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager extends BaseClass {
	private Scanner scanner;
	private Connection conn;

	private Area area;
	private Hero hero;
	private ArrayList<Monster> monsters;
	private Weapon weapon;
	private int[] attackNumbers; // 1st index is physical, 2nd index is magic.
	private int rounds;

	private AreaService areaService;
	private HeroService heroService;
	private MonsterService monsterService;
	private WeaponService weaponService;

	/**
	 * GameManager constructor with 2 parameters
	 * 
	 * @param scanner
	 *            - scanner object to help with user input
	 * @param conn
	 *            - connection object to help with database connection
	 */
	public GameManager(Scanner scanner, Connection conn) {
		this.scanner = scanner;
		this.conn = conn;
		this.attackNumbers = new int[] { 0, 0 };
		this.rounds = 0;

		// self-inject all the services
		try {
			this.areaService = new AreaService(this.conn);
			this.heroService = new HeroService(this.conn);
			this.monsterService = new MonsterService(this.conn);
			this.weaponService = new WeaponService(this.conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 *  step 1 - display the menu (pickup a hero)
	 */
	public void displayMenu() {
		try {
			// Display the beginning of the game
			SceneGenerator.beginGame();
			printBlankSpace();

			// load all the heroes from the database
			ArrayList<Hero> list = this.heroService.loadAll();

			// print all of them so user can pick up one
			groupPrint(list);

			int heroid = 0;
			boolean mark = false;
			while (!mark) {
				// wait for the user to input
				print("");
				print("Choose your hero by entering the ID:");
				try {
					heroid = scanner.nextInt();
					mark = findID(list, heroid) ? true : false;
				} catch (Exception e) {
					print("Please enter ID, it's a number!");
				}
				scanner.nextLine(); // consume the new line
			}

			// load hero with this id
			this.hero = this.heroService.getObject(heroid);

			// print info
			print("");
			print("===================");
			print("= You have chosen =");
			print("=    Hero No." + Integer.toString(heroid) + "    =");
			print("===================");

			print(this.hero.toString());

			// display the hero
			SceneGenerator.showHero();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * private boolean method searches the hero list to find the specified id
	 * 
	 * @param list
	 *            - list of hero objects to search through
	 * @param id
	 *            - id of the hero to search for in the list
	 * @return true if hero id is found in the list, false if not
	 */
	private boolean findID(ArrayList<Hero> list, int id) {
		for (Hero h : list) {
			if (h.getId() == id)
				return true;
		}
		print("Wrong id, try agagin!");
		return false;
	}

	/**
	 *  step 2 - choose a weapon
	 */
	public void pickWeapon() {
		boolean mark = false;
		try {
			// load all the available areas
			ArrayList<Weapon> list = this.weaponService.loadAllByHeroId(this.hero.getId());

			// print all of them so user can pick up one
			groupPrint(list);

			int weaponId = -1;
			while (!mark) {
				print("");
				// wait for the user to input
				print("Choose your weapon by its Id:");
				try {
					weaponId = scanner.nextInt();
					mark = findWeaponName(list, weaponId) ? true : false;
				} catch (Exception e) {
					print("Please enter ID, it's a number!");
				}
				scanner.nextLine(); // consume the new line
			}

			// load the area which the user choose
			this.weapon = this.weaponService.getObject(weaponId);

			// show the area!
			print("You have chosen this weapon:");
			this.printWeapon(this.weapon);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * private boolean method searches the weapon list for the requested weapon
	 * id
	 * 
	 * @param list
	 *            - list of weapon objects to search through
	 * @param id
	 *            - id of the weapon to search in the list
	 * @return true if weapon id is in the list, false if it is not
	 */
	private boolean findWeaponName(ArrayList<Weapon> list, int id) {
		for (Weapon a : list) {
			if (a.getId() == id) // god damn stupid shit nasty slut java
				return true;
		}
		print("No weapon found, try again!");
		return false;
	}

	/**
	 *  step 3 - choose an area (all quests in the area will be assigned to the
	 *  Hero)
	 */
	public void chooseArea() {
		try {
			printBlankSpace();

			// load all the available areas
			ArrayList<Area> list = this.areaService.loadAll();

			// print all of them so user can pick up one
			groupPrint(list);

			String areaname = "";
			boolean mark = false;
			while (!mark) {
				print("");
				// wait for the user to input
				print("Choose your area by its name:");
				try {
					areaname = scanner.nextLine();
					mark = findAreaName(list, areaname) ? true : false;
				} catch (Exception e) {
					print("Please enter name, it's a string!");
					scanner.nextLine();
				}
			}

			// load the area which the user choose
			this.area = this.areaService.getObject(areaname);

			// load all the monsters reside in this area;
			this.monsters = this.monsterService.loadAllByAreaName(areaname);

			// show the area!
			SceneGenerator.showArea();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Private method returns true or false if areaname is in the list or not
	 * 
	 * @param list
	 *            - list of the current areanames
	 * @param name
	 *            - name of the area to search for in the list
	 * @return true if areaname is found false if not
	 */
	private boolean findAreaName(ArrayList<Area> list, String name) {
		for (Area a : list) {
			if (a.getName().equals(name))
				return true;
		}
		print("No area found, try again!");
		return false;
	}

	
	/**
	 * step 4 - battle (kill one monster for completing one quest)
	 */
	public void battle() {
		printBlankSpace();
		print("============================================");
		print("===           Battle Begins!             ===");
		print("===        Try killing them all!         ===");
		print("===        -*-        ===");
		print("===              COMMANDS:               ===");
		print("===        p   :   physical damage;      ===");
		print("===        m   :   magic damage;         ===");
		print("===        q   :   quit;                 ===");
		print("============================================");

		int monsterId = 0;
		String action = "";
		// battle until user press q;
		while (!isMonsterAllDead(monsterId)) {
			printBlankSpace();
			print("**********************************");
			print("Monster: " + this.monsters.get(monsterId).getName());
			print("HP     : " + this.monsters.get(monsterId).getHitpoints());
			print("**********************************");
			print("");
			this.rounds++;
			print("ROUND " + this.rounds + ": Choose your action:");
			action = scanner.nextLine();
			switch (action) {
			case "p": // physical damage
				this.fightMonster(monsterId, true);
				this.attackNumbers[0]++;
				break;
			case "m": // magic damage
				this.fightMonster(monsterId, false);
				this.attackNumbers[1]++;
				break;
			case "q": // quit
				print("*************");
				print("*YOU COWARD!*");
				print("*************");
				break;
			default:
				print("-------------------------------------");
				print("-WRONG COMMANDS! Try following ones:-");
				print("-       p : physical damage         -");
				print("-       m : magic damage            -");
				print("-       q : quit                    -");
				print("-------------------------------------");
				break;
			}
			if (action.equals("q")) {
				break;
			}
			if (isMonsterDead(monsterId)) { // check if current monster is dead
				printBlankSpace();
				print("********************************");
				print("Congratulations!, you've killed " + this.monsters.get(monsterId).getName());
				print("********************************");

				// add the experiences, equals to level of monster;
				int exp = this.hero.getExp();
				this.hero.setExp(exp + this.monsters.get(monsterId).getLevel());

				// next monster
				monsterId++;
			}
		}
	}

	/**
	 * private method checks if monster is dead
	 * 
	 * @param id
	 *            of the monster to be checked
	 * @return true if monster's hit-points is 0 else false
	 */
	private boolean isMonsterDead(int id) {
		if (this.monsters.get(id).getHitpoints() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * private method checks if monsters are all dead in the area
	 * 
	 * @param id
	 *            - value to check if all the monsters are dead, usually the
	 *            value is 0
	 * @return true if all monsters are dead, false if the size of the list of
	 *         monsters is not 0
	 */
	private boolean isMonsterAllDead(int id) {
		if (id == this.monsters.size()) {
			print("********************************");
			print("* You have killed all monsters!*");
			print("*     Congratulations! Hero!   *");
			print("********************************");
			return true;
		}
		return false;
	}

	/**
	 * private void method is responsible for the fight with the monster monster
	 * fights with either physical or magical damage depending on it's damage
	 * type
	 * 
	 * @param monsterId
	 *            - id of the monster to fight with
	 * @param isPhysical
	 *            - true if monster does physical damage, false if it does
	 *            magical
	 */
	private void fightMonster(int monsterId, boolean isPhysical) {
		int currentMonsterHP = this.monsters.get(monsterId).getHitpoints();
		int damage = isPhysical ? this.weapon.getPdamage() : this.weapon.getMdamage();
		String damageTypeText = isPhysical ? " physical" : " magical";

		print("You use " + this.weapon.getName());
		print("It causes " + damage + damageTypeText + " damage!");
		print(this.damageToText(damage));
		this.monsters.get(monsterId).setHitpoints(currentMonsterHP - damage);
	}

	/**
	 * private String outputs text description of the damage depending on how
	 * much damage is done
	 * 
	 * @param dmg
	 *            the damage value to determine what message to display
	 */
	private String damageToText(int dmg) {
		if (dmg > 65) {
			return "What a mighty move!";
		} else {
			return "A weak attack!";
		}
	}

	/**
	 * Step 5: the method we use to display the result.
	 * (trigger: die or choose 'quit')
	 */
	public void displayReport() {
		try {
			printBlankSpace();
			print("======REPORT======");
			print("Hero Name: " + this.hero.getName());
			print("Hero EXP : " + this.hero.getExp());
			print("");
			print("======ROUNDS======");
			print("    " + this.rounds + " rounds");
			print("==================");
			print("");
			print("======ATTACK======");
			print("Attack   : " + (this.attackNumbers[0] + this.attackNumbers[1]) + " times");
			print("Physical : " + this.attackNumbers[0] + " times");
			print("Magical  : " + this.attackNumbers[1] + " times");
			print("==================");
			print("");
			print("======DAMAGE======");
			print("Physical : " + (this.attackNumbers[0] * this.weapon.getPdamage()));
			print("Magical  : " + (this.attackNumbers[1] * this.weapon.getMdamage()));
			print("==================");
			print("");
			print("=====MONSTERS=====");
			int count = printDeadMonster();
			print("==================");
			print("");
			print("=======AREA=======");
			print("Name     : " + this.area.getName());
			print("Monsters : " + this.monsterService.loadAllByGroupByAreaName(this.area.getName()));
			print("Killed   : " + count);
			print("==================");
		} catch (Exception e) {
			print(e.getMessage());
		}
	}

	/**
	 * private method prints the dead monsters names and returns the count of
	 * dead monsters in the list
	 * 
	 * @return count - number of dead monsters in the monsters list
	 */
	private int printDeadMonster() {
		int count = 0;
		for (int i = 0; i < this.monsters.size(); i++) {
			Monster m = this.monsters.get(i);
			if (m.getHitpoints() == 0) {
				print("Name[" + i + "]  : " + m.getName());
				count++;
			}
		}
		return count;
	}

	/**
	 * private method prints a visual representation of the weapons
	 * 
	 * @param weapon
	 *            - the weapon object to visually represent
	 */
	private void printWeapon(Weapon weapon) {
		if (weapon.getName().contains("Sword") || weapon.getName().contains("Blade")) {
			SceneGenerator.showSword();
		} else if (weapon.getName().contains("Bow")) {
			SceneGenerator.showBow();
		} else {
			SceneGenerator.showAxe();
		}
	}
}

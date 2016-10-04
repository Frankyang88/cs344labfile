package cosc344;

import cosc344.utils.ConnectionManager;
import cosc344.utils.GameManager;
import cosc344.utils.SceneGenerator;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			GameManager gm = new GameManager(scanner, ConnectionManager.getSQLConnection());

			// step 1 - display the menu (pickup a hero)
			gm.displayMenu();

			// step 2 - pick a weapon according to the hero you choose
			gm.pickWeapon();

			// step 3 - choose an area (all quests in the area will be assigned
			// to the Hero)
			gm.chooseArea();

			// step 4 - battle (kill one monster for completing one quest)
			gm.battle();

			// step 5 - display the result (trigger: die or choose 'quit')
			gm.displayReport();

			// step 7 - create a json for this report

			// Close scanner and database connection
			scanner.close();
			ConnectionManager.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
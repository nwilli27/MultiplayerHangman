package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Functionality to assign a random word for the game.
 * @author Nolan W
 */
public class Dictionary {
	
	/**
	 * Returns a word randomly from the dictionary.
	 * @precondition: none
	 * @return a random word for the game.
	 */
	public static String getNewWordToGuess() {

		var dictionary = new ArrayList<String>();
		
		if (dictionary.isEmpty()) {
			try {
				File file = new File("dictionary.txt");
				Scanner sc = new Scanner(file);

				while (sc.hasNextLine()) {
					dictionary.add(sc.nextLine().trim());
				}
				Random random = new Random();
				sc.close();
				return dictionary.get(random.nextInt(dictionary.size() - 1));

			} catch (Exception exc) {
				System.out.println(exc.getStackTrace());
			}
		} else {

			Random random = new Random();
			return dictionary.get(random.nextInt(dictionary.size() - 1));
		}

		return null;
	}
}

package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Functionality to assign a random word for the game.
 * @author Nolan W, Carsen B, Tristen R
 */
public class Dictionary {
	
	private List<String> dictionary;
	
	/**
	 * Constructor for dictionary.
	 * @precondition none
	 * @postcondition this.dictionary.size() == 0
	 */
	public Dictionary() {
		this.dictionary = new ArrayList<String>();
	}
	
	/**
	 * Returns a word randomly from the dictionary.
	 * @precondition: none
	 * @return a random word for the game.
	 */
	public String getNewWordToGuess() {

		if (this.dictionary.isEmpty()) {
			try {
				File file = new File("dictionary.txt");
				Scanner sc = new Scanner(file);

				while (sc.hasNextLine()) {
					this.dictionary.add(sc.nextLine().trim());
				}
				Random random = new Random();
				sc.close();
				return this.dictionary.get(random.nextInt(this.dictionary.size() - 1));

			} catch (Exception exc) {
				System.out.println(exc.getStackTrace());
			}
		} else {

			Random random = new Random();
			return this.dictionary.get(random.nextInt(this.dictionary.size() - 1));
		}

		return null;
	}
}

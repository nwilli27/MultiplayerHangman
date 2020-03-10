package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

	private WordManager wordManager;
	private ArrayList<String> dictionary;

	public GameManager() {
		this.wordManager = new WordManager();
		this.dictionary = new ArrayList<String>();
	}

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

	public void handleGuess(String character) {
		
		this.wordManager.makeGuess(character);
		ClientManager.broadcastGuessUpdate(this.wordManager.formattedCurrentWord(), character, this.wordManager.getInvalidGuessCounter());

		if (this.wordManager.isOutOfGuesses()) {
			
			ClientManager.broadcastMessage("gameOver");
		} else if (this.wordManager.isWordComplete()) {
			
			ClientManager.broadcastMessage("wordComplete");
		}
		
		ClientManager.switchToNextClientTurn();
	}

	public void handleTimeout(String username) {
		// TODO
	}

	public void handleDisconnect(String username) {

		ClientManager.handleClientDisconnect(username);
	}

}

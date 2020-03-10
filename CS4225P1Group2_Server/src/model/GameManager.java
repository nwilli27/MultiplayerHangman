package model;

import java.io.File;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameManager {

	private Stickman stickman;
	private ClientManager clientManager;
	private WordManager wordManager;
	private ArrayList<String> dictionary;

	public GameManager() {
		this.stickman = new Stickman();
		this.clientManager = new ClientManager();
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

	public void handleCharacterGuess(String character) {
		var isValidGuess = this.wordManager.guessCharacter(character);

		if (isValidGuess) {

			// Notify all clients to update ui with valid character
		} else {

			this.stickman.enableNextBodyPart();

			// Notify all clients of the current clients guess like "Client guessed
			// <character>" then replicate changes in ui
		}
	}

	public void handleSentenceGuess(String sentence) {

		var isValidGuess = this.wordManager.guessSentence(sentence);

		if (isValidGuess) {

			// Notify all clients the current client who guessed right and that the game is
			// over
		} else {

			this.stickman.enableNextBodyPart();

			if (this.stickman.areAllBodyPartsActive()) {

				// Notify clients that they have lost and toggle last body part to update view
			}
		}
	}

	public void handleTimeout(String username) {

	}

	public void handleDisconnect(String username) {

		// Disconnect
		this.clientManager.disconnectClient(username);

		// Notify all other clients that the <username> client disconnected
	}

}

package model;

import enums.MessageType;

/**
 * Manages the current Game state.
 * @author Nolan W, Carson B, Tristen R
 */
public class GameManager {

	private Dictionary dictionary;
	private WordManager wordManager;

	/**
	 * Constructor for the Game Manager.
	 * @precondition: none
	 * @postcondition: this.dictionary.size() == 0
	 */
	public GameManager() {
		this.wordManager = new WordManager();
		this.dictionary = new Dictionary();
		this.wordManager.setWord(this.dictionary.getNewWordToGuess());
	}

	/**
	 * Handles a client character/word guess.
	 * Hands guess to word manager and then broadcasts the results to all clients.
	 * Checks if out of guesses or if word is complete and broadcasts outcome.
	 * @precondition: guess != null
	 * @param guess the guess made by a client
	 */
	public void handleGuess(String guess) {
		
		if (guess == null) {
			throw new IllegalArgumentException("Guess can not be null");
		}
		
		ClientManager.incrementCurrentClientGuessCount();
		this.wordManager.makeGuess(guess);
		ClientManager.broadcastGuessUpdate(this.wordManager.getFormattedWord(), guess, this.wordManager.getInvalidGuessCount());
		this.checkGameState();
	}
	
	/**
	 * Handles a client disconnect of the specified user name.
	 * @precondition: user name != null
	 * @param username the user to disconnect
	 */
	public void handleDisconnect(String username) {

		if (username == null) {
			throw new IllegalArgumentException("Username can not be null");
		}
		
		ClientManager.handleClientDisconnect(username);
	}

	/**
	 * Handles the process for a timeout
	 * @precondition username != null
	 * @param username the client to timeout
	 */
	public void handleTimeout(String username) {
		
		if (username == null) {
			throw new IllegalArgumentException("Username can not be null.");
		}
	}
	
	/**
	 * Sends the client of type user name everything needed to fill there ui 
	 * with current game information.
	 * @param username Username of the user
	 */
	public void sendNewUserGameState(String username) {
		
		ClientManager.sendClientGameState(username, this.wordManager.getFormattedWord(), this.wordManager.getCombinedGuessedCharacters(), this.wordManager.getInvalidGuessCount());
	}
	
	/**
	 * Broadcasts a message alerting users of a new user joining
	 * @param username Username of the new user
	 */
	public void handleNewUser(String username) {
		
		ClientManager.broadcastMessage(MessageType.NewUser, username);
	}

	private void checkGameState() {
		if (this.wordManager.isWordComplete()) {
			
			ClientManager.broadcastWinner();
		} else if (!this.wordManager.isOutOfGuesses() || !this.wordManager.isWordComplete()) {
			
			ClientManager.switchToNextClientTurn();
		}
	}

}

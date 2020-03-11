package model;

/**
 * Manages the current Game state.
 * @author Nolan W, Carsen B, Tristen R
 */
public class GameManager {

	private WordManager wordManager;

	/**
	 * Constructor for the Game Manager.
	 * @precondition: none
	 * @postcondition: this.dictionary.size() == 0
	 */
	public GameManager() {
		this.wordManager = new WordManager();
		this.wordManager.setWord(Dictionary.getNewWordToGuess());
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

	public void handleTimeout(String username) {
		// TODO
	}

	private void checkGameState() {
		if (!this.wordManager.isOutOfGuesses() || !this.wordManager.isWordComplete()) {
			
			ClientManager.switchToNextClientTurn();
		} 
	}

}

package model;

public class GameManager {

	private Stickman stickman;
	private ClientManager clientManager;
	private WordManager wordManager;
	
	public GameManager() {
		this.stickman = new Stickman();
		this.clientManager = new ClientManager();
		this.wordManager = new WordManager();
	}
	
	public void handleCharacterGuess(String character) {
		var isValidGuess = this.wordManager.guessCharacter(character);
		
		if (isValidGuess) {
			
			// Notify all clients to update ui with valid character
		} else {
			
			this.stickman.enableNextBodyPart();
			
			// Notify all clients of the current clients guess like "Client guessed <character>" then replicate changes in ui
		}
	}
	
	public void handleSentenceGuess(String sentence) {
		
		var isValidGuess = this.wordManager.guessSentence(sentence);
		
		if (isValidGuess) {
			
			// Notify all clients the current client who guessed right and that the game is over
		} else {
			
			this.stickman.enableNextBodyPart();
			
			if (this.stickman.areAllBodyPartsActive()) {
				
				// Notify clients that they have lost and toggle last body part to update view
			}
		}
	}
	
}

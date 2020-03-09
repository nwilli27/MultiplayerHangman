package model;

public class GameManager {

	private Stickman stickman;
	private ClientManager clientManager;
	private WordManager wordManager;
	
	public GameManager()
	{
		this.stickman = new Stickman();
		this.clientManager = new ClientManager();
		this.wordManager = new WordManager();
	}
	
	public boolean handleCharacterGuess(String character)
	{
		var isValidGuess = this.wordManager.guessCharacter(character);
		
		return false;
	}
	
}

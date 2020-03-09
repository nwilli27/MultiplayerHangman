package model;

public class Client {

	private String username;
	
	public Client(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void notifyCharacterGuessed(String guessedCharacter) {
		// maybe?
	}
	
}

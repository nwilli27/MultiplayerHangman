package model;

public class RequestHandler {

	private GameManager gameManager;
	
	private static String DELIMITER = "-";
	
	public RequestHandler() {
		
		this.gameManager = new GameManager();
	}
	
	public void handleRequest(String request) {
		
		var parsedRequest = request.split(DELIMITER);
		var requestType = parsedRequest[0];
		
		switch (requestType)
		{
			case "guess":
				var guessedCharacter = parsedRequest[1];
				this.gameManager.handleCharacterGuess(guessedCharacter);
				
			case "sentence":
				var guessedSentence = parsedRequest[1];
				this.gameManager.handleSentenceGuess(guessedSentence);
				
			case "timeout":
		
		}
	}
}

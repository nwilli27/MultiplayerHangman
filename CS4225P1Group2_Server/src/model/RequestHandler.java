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
				break;
				
			case "sentence":
				var guessedSentence = parsedRequest[1];
				this.gameManager.handleSentenceGuess(guessedSentence);
				break;
				
			case "timeout":
				var timeoutUser = parsedRequest[1];
				this.gameManager.handleTimeout(timeoutUser);
				break;
			
			case "disconnect":
				var disconnectUser = parsedRequest[1];
				// Disconnect user and notify all clients of disconnection
				break;
				
		}
	}
}

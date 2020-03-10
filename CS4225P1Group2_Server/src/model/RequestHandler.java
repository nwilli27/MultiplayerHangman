package model;

public class RequestHandler {

	private static GameManager gameManager;
	private static String DELIMITER = "#";
	
	public void handleRequest(String request) {
		
		var parsedRequest = request.split(DELIMITER);
		var requestType = parsedRequest[0];
		
		switch (requestType)
		{
			case "guess":
				var guessedCharacter = parsedRequest[1];
				gameManager.handleGuess(guessedCharacter);
				break;
			
			case "timeout":
				var timeoutUser = parsedRequest[1];
				gameManager.handleTimeout(timeoutUser);
				break;
			
			case "disconnect":
				var disconnectUser = parsedRequest[1];
				gameManager.handleDisconnect(disconnectUser);
				break;
				
		}
	}
}

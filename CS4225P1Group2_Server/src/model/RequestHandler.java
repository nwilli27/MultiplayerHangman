package model;

/**
 * Handles request to manage game state and clients.
 * @author Nolan W, Carsen B, Tristen R
 */
public class RequestHandler {

	private static GameManager gameManager;
	private static String DELIMITER = "#";
	
	/**
	 * Initializes the game.
	 */
	public static void intializeGame() {
		gameManager = new GameManager();
	}
	
	/**
	 * Parses the request and handles different requests from clients.
	 * @precondition: none
	 * @param request
	 */
	public static void handleRequest(Message request) {
		
		var parsedRequest = request.getMessage().split(DELIMITER);
		
		switch (request.getType())
		{
			case Guess:
				var guessedCharacter = parsedRequest[1];
				gameManager.handleGuess(guessedCharacter);
				break;
			
			case Timeout:
				var timeoutUser = parsedRequest[1];
				gameManager.handleTimeout(timeoutUser);
				break;
			
			case Disconnect:
				var disconnectUser = parsedRequest[1];
				gameManager.handleDisconnect(disconnectUser);
				break;
				
			default:
				break;
				
		}
	}
}

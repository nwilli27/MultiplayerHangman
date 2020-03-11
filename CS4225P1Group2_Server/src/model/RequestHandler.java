package model;

/**
 * Handles request to manage game state and clients.
 * @author Nolan W, Carson B, Tristen R
 */
public class RequestHandler {

	private static GameManager gameManager;
	private static final String DELIMITER = "#";
	
	/**
	 * Initializes the game.
	 */
	public static void intializeGame() {
		gameManager = new GameManager();
	}
	
	/**
	 * Parses the request and handles different requests from clients.
	 * @precondition: none
	 * @param request message to parse
	 * @param userWhoRequest the one who requested
	 */
	public static void handleRequest(Message request, String userWhoRequest) {
		
		var parsedRequest = request.getMessage().split(DELIMITER);
		
		switch (request.getType()) {
		
			case NewUser:
				var username = parsedRequest[0];
				gameManager.handleNewUser(username);
				gameManager.sendNewUserGameState(username);
				break;
		
			case Guess:
				var guessedCharacter = parsedRequest[0];
				gameManager.handleGuess(guessedCharacter);
				break;
			
			case Disconnect:
				gameManager.handleDisconnect(userWhoRequest);
				break;
				
			default:
				break;
				
		}
	}
}

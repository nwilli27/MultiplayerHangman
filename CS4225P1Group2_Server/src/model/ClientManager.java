package model;

import java.util.ArrayList;
import java.util.List;

import enums.MessageType;

/**
 * Handles interaction with all the Client Handlers
 * @author Nolan W, Carson B, Tristen R
 */
public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 2;
	private static final int NUDGE_TIME = 15;
	private static final int TIMEOUT_TIME = 10;
	
	private static List<ClientHandler> clients;
	private static ClientHandler currentClient;
	
	/**
	 * Sends a message to all Clients.
	 * 
	 * @precondition: message != null
	 * @param message to be sent
	 * @param type the type of message
	 */
	public static void broadcastMessage(MessageType type, String message) {
		
		if (message == null) {
			throw new IllegalArgumentException("Message can not be null to send.");
		}

		for (var client : clients) {
			client.sendMessage(type, message);
		}
	}
	
	/**
	 * Handles a client disconnection. It removes a client of the user name and
	 * then broadcasts a message to all other clients.
	 * 
	 * @precondition: user name != null
	 * @param username the user name to remove
	 */
	public static void handleClientDisconnect(String username) {
		
		if (username == null) {
			throw new IllegalArgumentException("Can not disconnect client of null username.");
		}
		
		switchToNextClientTurn();
		var clientToDisconnect = getClient(username);
		clients.remove(clientToDisconnect);
		clientToDisconnect.closeStreams();
		broadcastMessage(MessageType.Disconnect, clientToDisconnect.getUsername());
	}
	
	/**
	 * Sends a message to all clients of a new guess made by a client.
	 * 
	 * @precondition: formattedWord && guess != null
	 * @param formattedWord the word to display to all clients
	 * @param guess the guess made by the current client
	 * @param bodyCount the body parts needed to display to clients
	 */
	public static void broadcastGuessUpdate(String formattedWord, String guess, int bodyCount) {
		
		broadcastMessage(MessageType.GuessUpdate, currentClient.getUsername() + "#" + guess + "#" + formattedWord);
		broadcastMessage(MessageType.BodyCount, String.valueOf(bodyCount));
	}
	
	/**
	 * Sends the current game state to a specified user name (client).
	 * 
	 * @param username the user to give the game state to
	 * @param formattedWord the current word status
	 * @param characters the guessed characters combined into a string
	 * @param bodyCount the # of body parts to enable on stick man
	 */
	public static void sendClientGameState(String username, String formattedWord, String characters, int bodyCount) {
		
		var client = getClient(username);
		client.sendMessage(MessageType.GameState, formattedWord + "#" + characters + "#" + bodyCount);
	}
	
	/**
	 * Adds a client handler to the list of clients. If this is the 
	 * first client added, they're made the current client.
	 * @precondition: client != null
	 * @param client to add
	 * @return true if added; otherwise false
	 */
	public static boolean addClient(ClientHandler client) {
		
		if (client == null) {
			throw new IllegalArgumentException("Can not add a null client.");
		}
		
		checkToSetCurrentClient(client);
		return clients.add(client);
	}
	
	/**
	 * Returns whether there is a max number of clients.
	 * @precondition none
	 * @return true if clients size == max client size
	 */
	public static boolean hasMaxClients() {
		return clients.size() == MAX_CLIENT_COUNT;
	}
	
	/**
	 * Switches to next clients turn in order of how they joined the server.
	 * @precondition: none
	 * @postcondition: currentClient == currentClient.getIndex + 1
	 */
	public static void switchToNextClientTurn() {
		var totalClientMax = clients.size() - 1;
		var currentClientIndex = clients.indexOf(currentClient);
		
		if (currentClientIndex == totalClientMax) {
			currentClient = clients.get(0);
		} else {
			currentClient = clients.get(++currentClientIndex);
		}
		
		var nudgeTimer = new TimedMessage(NUDGE_TIME);
		nudgeTimer.setNudgeTask(currentClient.getUsername(), currentClient.getGuessCount());
		sendNextGuessMessage();
	}

	/**
	 * Returns the current client.
	 * @return the current client.
	 */
	public static ClientHandler getCurrentClient() {
		return currentClient;
	}
	
	/**
	 * Initializes the static list of clients.
	 * @precondition: none
	 * @postcondition: clients.size() == 0
	 */
	public static void initializeClientList() {
		clients = new ArrayList<ClientHandler>();
	}

	/**
	 * Returns whether the clients exists in the list of clients.
	 * @precondition: username != null
	 * @param username the name to check for
	 * @return true: client does exist; otherwise false
	 */
	public static boolean doesClientExists(String username) {
		
		for (var client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Sends the current client a nudge they only have 
	 * a set time before being disconnected.
	 * 
	 * @precondition none
	 * @postcondition none
	 * @param previousGuessCount the previous guess count
	 * @param username Username of the user
	 */
	public static void sendCurrentClientNudge(String username, int previousGuessCount) {
		
		if (currentClient.getUsername().equalsIgnoreCase(username) && currentClient.getGuessCount() == previousGuessCount) {
			
			currentClient.sendMessage(MessageType.Nudge, "");
			var timeoutTimer = new TimedMessage(TIMEOUT_TIME);
			timeoutTimer.setTimeoutTask(currentClient.getUsername(), currentClient.getGuessCount());
		}
	}
	
	/**
	 * Disconnects the current client from the server.
	 * 
	 * @precondition none
	 * @postcondition 
	 * @param previousGuessCount the previous guess count
	 * @param username Username of the user
	 */
	public static void disconnectCurrentClient(String username, int previousGuessCount) {
		
		if (currentClient.getUsername().equalsIgnoreCase(username) && currentClient.getGuessCount() == previousGuessCount) {
			
			currentClient.closeStreams();
			clients.remove(currentClient);
			broadcastMessage(MessageType.UserTimeout, username);
			switchToNextClientTurn();
		}
	}
	
	/**
	 * Broadcasts to all clients that the current user has finished the word or
	 * guessed the correct word.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public static void broadcastWinner() {
		
		broadcastMessage(MessageType.UserWon, currentClient.getUsername());
	}
	
	/**
	 * Increments the current client guess count.
	 * @precondition none
	 * @postcondition currentClient.getGuessCount()++
	 */
	public static void incrementCurrentClientGuessCount() {
		
		currentClient.incrementGuessCount();
	}
	
	private static void sendNextGuessMessage() {
		
		for (var client : clients) {
			
			if (client.equals(currentClient)) {	
				client.sendMessage(MessageType.YourGuessTurn, "");
			} else {
				client.sendMessage(MessageType.OtherGuessTurn, currentClient.getUsername());
			}
		}
	}
	
	private static ClientHandler getClient(String username) {
		
		for (var client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return client;
			}
		}
		
		return null;
	}

	private static void checkToSetCurrentClient(ClientHandler client) {
		
		if (clients.size() == 0) {
			currentClient = client;
			client.sendMessage(MessageType.YourGuessTurn, "");
		}
	}
	
}

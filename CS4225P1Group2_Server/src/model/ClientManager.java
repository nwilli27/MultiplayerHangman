package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles interaction with all the Client Handlers
 * @author Nolan W, Carsen B, Tristen R
 */
public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 4;
	
	private static List<ClientHandler> clients;
	private static ClientHandler currentClient;
	
	/**
	 * Sends a message to all Clients.
	 * @precondition: message != null
	 * @param message to be sent
	 */
	public static void broadcastMessage(String message) {
		
		if (message == null) {
			throw new IllegalArgumentException("Message can not be null to send.");
		}
		
		var messageSerialized = new Message(message);
		
		for (var client : clients) {

			try {
				var outputStream = client.getOutputStream();
				outputStream.writeObject(messageSerialized);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Handles a client disconnection. It removes a client of the user name and
	 * then broadcasts a message to all other clients.
	 * @precondition: user name != null
	 * @param username the user name to remove
	 */
	public static void handleClientDisconnect(String username) {
		
		if (username == null) {
			throw new IllegalArgumentException("Can not disconnect client of null username.");
		}
		
		var clientToDisconnect = getClient(username);
		clients.remove(clientToDisconnect);
		broadcastMessage("disconnect#" + username);
	}
	
	/**
	 * Sends a message to all clients of a new guess made by a client.
	 * @precondition: formattedWord && guess != null
	 * @param formattedWord the word to display to all clients
	 * @param guess the guess made by the current client
	 * @param bodyCount the body parts needed to display to clients
	 */
	public static void broadcastGuessUpdate(String formattedWord, String guess, int bodyCount) {
		
		if (guess.length() > 1) {
			broadcastMessage("wordGuess#" + guess + "#" + currentClient.getUsername());
		} else {
			broadcastMessage("characterGuess#" + guess + "#" + currentClient.getUsername());
		}
		
		broadcastMessage("updatedWord#" + formattedWord);
		broadcastMessage("bodyCount#" + bodyCount);
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
		
		if (currentClientIndex == totalClientMax)
		{
			currentClient = clients.get(0);
		}
		else
		{
			currentClient = clients.get(++currentClientIndex);
		}
		
		broadcastMessage("clientChoosing#" + currentClient.getUsername());
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
	
	private static ClientHandler getClient(String username) {
		
		for (var client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return client;
			}
		}
		
		return null;
	}

	private static void checkToSetCurrentClient(ClientHandler client) {
		
		if (clients.size() == 0)
		{
			currentClient = client;
		}
	}
	
}

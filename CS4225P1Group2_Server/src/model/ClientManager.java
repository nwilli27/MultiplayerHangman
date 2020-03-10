package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 4;
	
	private static List<ClientHandler> clients;
	private static ClientHandler currentClient;
	
	public ClientManager() {
		clients = new ArrayList<ClientHandler>();
	}
	
	public static void broadcastMessage(String message) {
		
		var messageSerialized = new Message(message);
		
		for (var client : clients) {

			try {
				var outputStream = client.getOutgoingMessages();
				outputStream.writeObject(messageSerialized);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void handleClientDisconnect(String username) {
		
		var clientToDisconnect = getClient(username);
		clients.remove(clientToDisconnect);
		broadcastMessage("disconnect#" + username);
	}
	
	public static void broadcastGuessUpdate(String formattedWord, String guess, int bodyCount) {
		
		if (guess.length() > 1) {
			broadcastMessage("wordGuess#" + guess + "#" + currentClient.getUsername());
		} else {
			broadcastMessage("characterGuess#" + guess + "#" + currentClient.getUsername());
		}
		
		broadcastMessage("updatedWord#" + formattedWord);
		broadcastMessage("bodyCount#" + bodyCount);
	}
	
	public static boolean addClient(ClientHandler client) {
		checkToSetCurrentClient(client);
		
		if (doesClientExists(client.getUsername()))
		{
			return false;
		}
		
		return clients.add(client);
	}
	
	public static boolean hasMaxClients() {
		return clients.size() == MAX_CLIENT_COUNT;
	}
	
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

	public static ClientHandler getCurrentClient() {
		return currentClient;
	}
	
	private static ClientHandler getClient(String username) {
		
		for (var client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return client;
			}
		}
		
		return null;
	}
	
	public static boolean doesClientExists(String username) {
		
		for (var client : clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}

	private static void checkToSetCurrentClient(ClientHandler client) {
		
		if (clients.size() == 0)
		{
			currentClient = client;
		}
	}

	public static void initializeClientList() {
		clients = new ArrayList<ClientHandler>();
	}
	
}

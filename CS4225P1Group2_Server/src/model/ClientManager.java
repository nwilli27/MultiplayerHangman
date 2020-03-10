package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 4;
	
	public static List<ClientHandler> Clients;
	private ClientHandler currentClient;
	
	public ClientManager() {
		Clients = new ArrayList<ClientHandler>();
	}
	
	public void broadcastMessage(String message) {
		
		var messageSerialized = new Message(message);
		
		for (var client : Clients) {

			try {
				var outputStream = client.getOutgoingMessages();
				outputStream.writeObject(messageSerialized);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleClientDisconnect(String username) {
		
		var clientToDisconnect = this.getClient(username);
		Clients.remove(clientToDisconnect);
		this.broadcastMessage("disconnect#" + username);
	}
	
	public void broadcastGuessUpdate(String formattedWord, String guess, int bodyCount) {
		
		if (guess.length() > 1) {
			this.broadcastMessage("wordGuess#" + guess + "#" + this.currentClient.getUsername());
		} else {
			this.broadcastMessage("characterGuess#" + guess + "#" + this.currentClient.getUsername());
		}
		
		this.broadcastMessage("updatedWord#" + formattedWord);
		this.broadcastMessage("bodyCount#" + bodyCount);
	}
	
	public boolean addClient(ClientHandler client) {
		this.checkToSetCurrentClient(client);
		
		if (this.doesClientExists(client.getUsername()))
		{
			return false;
		}
		
		return Clients.add(client);
	}
	
	public boolean hasMaxClients() {
		return Clients.size() == MAX_CLIENT_COUNT;
	}
	
	public void switchToNextClientTurn() {
		var totalClientMax = Clients.size() - 1;
		var currentClientIndex = Clients.indexOf(this.currentClient);
		
		if (currentClientIndex == totalClientMax)
		{
			this.currentClient = Clients.get(0);
		}
		else
		{
			this.currentClient = Clients.get(++currentClientIndex);
		}
		
		this.broadcastMessage("clientChoosing#" + this.currentClient.getUsername());
	}

	public ClientHandler getCurrentClient() {
		return this.currentClient;
	}
	
	private ClientHandler getClient(String username) {
		
		for (var client : Clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return client;
			}
		}
		
		return null;
	}
	
	public boolean doesClientExists(String username) {
		
		for (var client : Clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}

	private void checkToSetCurrentClient(ClientHandler client) {
		
		if (Clients.size() == 0)
		{
			this.currentClient = client;
		}
	}
	
}

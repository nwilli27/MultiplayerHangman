package model;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 4;
	
	public static List<ClientHandler> Clients;
	private ClientHandler currentClient;
	
	public ClientManager() {
		Clients = new ArrayList<ClientHandler>();
	}
	
	public void disconnectClient(String username) {
		
		var clientToDisconnect = this.getClient(username);
		// Disconnect from server
	}
	
	public void sendMessageToClient(String username, String message) {
		
		var client = this.getClient(username);
		
	}
	
	public boolean addClient(ClientHandler client) {
		this.checkToSetCurrentClient(client);
		
		if (this.doesClientExists(client.getUsername()))
		{
			return false;
		}
		
		return this.Clients.add(client);
	}
	
	public boolean hasMaxClients() {
		return this.Clients.size() == MAX_CLIENT_COUNT;
	}
	
	public void switchToNextClientTurn() {
		var totalClientMax = this.Clients.size() - 1;
		var currentClientIndex = this.Clients.indexOf(this.currentClient);
		
		if (currentClientIndex == totalClientMax)
		{
			this.currentClient = this.Clients.get(0);
		}
		else
		{
			this.currentClient = this.Clients.get(++currentClientIndex);
		}
	}

	public ClientHandler getCurrentClient() {
		return this.currentClient;
	}
	
	private ClientHandler getClient(String username) {
		
		for (var client : this.Clients) {
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
		
		if (this.Clients.size() == 0)
		{
			this.currentClient = client;
		}
	}
	
}

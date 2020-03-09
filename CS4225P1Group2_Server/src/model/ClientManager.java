package model;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private static final int MAX_CLIENT_COUNT = 4;
	
	private List<Client> clients;
	private Client currentClient;
	
	public ClientManager() {
		this.clients = new ArrayList<Client>();
	}
	
	public void disconnectClient(String username) {
		
		var clientToDisconnect = this.getClient(username);
		// Disconnect from server
	}
	
	public boolean addClient(Client client) {
		this.checkToSetCurrentClient(client);
		
		if (this.doesClientExists(client.getUsername()))
		{
			return false;
		}
		
		return this.clients.add(client);
	}
	
	public boolean hasMaxClients() {
		return this.clients.size() == MAX_CLIENT_COUNT;
	}
	
	public void switchToNextClientTurn() {
		var totalClientMax = this.clients.size() - 1;
		var currentClientIndex = this.clients.indexOf(this.currentClient);
		
		if (currentClientIndex == totalClientMax)
		{
			this.currentClient = this.clients.get(0);
		}
		else
		{
			this.currentClient = this.clients.get(++currentClientIndex);
		}
	}

	public Client getCurrentClient() {
		return this.currentClient;
	}
	
	private Client getClient(String username) {
		
		for (var client : this.clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return client;
			}
		}
		
		return null;
	}
	
	private boolean doesClientExists(String username) {
		
		for (var client : this.clients) {
			if (client.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		
		return false;
	}

	private void checkToSetCurrentClient(Client client) {
		
		if (this.clients.size() == 0)
		{
			this.currentClient = client;
		}
	}
	
}

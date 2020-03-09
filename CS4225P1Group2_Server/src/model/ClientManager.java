package model;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private List<Client> clients;
	private Client currentClient;
	
	public ClientManager() 
	{
		this.clients = new ArrayList<Client>();
	}

	public List<Client> getClients() {
		return this.clients;
	}
	
	public boolean addClient(Client client)
	{
		this.checkToSetCurrentClient(client);
		
		if (this.doesClientExists(client.getUsername()))
		{
			return false;
		}
		
		return this.clients.add(client);
	}
	
	private boolean doesClientExists(String username)
	{
		for (var client : this.clients)
		{
			if (client.getUsername().equalsIgnoreCase(username))
			{
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
	
	public void switchToNextClientTurn()
	{
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
	
}

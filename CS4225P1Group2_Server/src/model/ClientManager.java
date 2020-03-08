package model;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

	private List<Client> clients;
	
	public ClientManager() 
	{
		this.clients = new ArrayList<Client>();
	}

	public List<Client> getClients() {
		return this.clients;
	}
	
	public boolean addClient(Client client)
	{
		return this.clients.add(client);
	}
	
}

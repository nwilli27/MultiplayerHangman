package controller;

import clientConnection.ClientConnection;

public class Controller {

	private static ClientConnection client;
	
	public Controller() {
		client = new ClientConnection();
	}

	public void handleGuess(String guess) {
		
		client.sendRequest("guess-" + guess);
		
	}

	public void handleLogin(String username) {
		client.sendRequest("login-" + username);
	}



	
}

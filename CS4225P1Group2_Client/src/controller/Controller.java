package controller;

import model.ClientConnection;

public class Controller {

	private static ClientConnection CLIENT = new ClientConnection();
	
	public Controller() {
		
	}

	public void handleGuess(String guess) {
		CLIENT.sendRequest("guess#" + guess);
	}

	public void handleLogin(String username) {	 
		CLIENT.sendRequest("login#" + username);
	}

	
}
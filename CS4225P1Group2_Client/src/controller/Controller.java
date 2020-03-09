package controller;

import clientConnection.ClientConnection;

public class Controller {

	private static final ClientConnection CLIENT = new ClientConnection();
	
	public Controller() {
		
	}

	public void handleGuess(String guess) {
		
		CLIENT.sendRequest("guess-" + guess);
		
	}

	public void handleLogin(String username) {
		System.out.println(CLIENT.sendRequest("login-" + username));
	}



	
}

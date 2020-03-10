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
		CLIENT.sendRequest("login#" + username); //TODO remove print
	}

	public void handleIncomingMessages(String message) {
		switch (message) {
		case "nudge":
			this.handleNudge();
			break;
		case "playerExit":
			this.handlePlayerExit();
			break;
		case "playerJoin":
			this.handlePlayerJoin();
			break;
		}
	}

	private void handlePlayerJoin() {
		// TODO Auto-generated method stub
		
	}

	private void handlePlayerExit() {
		// TODO Auto-generated method stub
		
	}

	private void handleNudge() {
		// TODO Auto-generated method stub
		
	}


	
}

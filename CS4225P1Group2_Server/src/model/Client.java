package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private String username;
	protected Socket socket;
	protected PrintWriter out;
	protected BufferedReader in;
	
	public Client(String username, Socket socket) {
		this.username = username;
		this.socket = socket;
		
		this.initializeInAndOut(socket);
	}

	private void initializeInAndOut(Socket socket) {
		try {
			this.out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return this.username;
	}
	
	public void notifyCharacterGuessed(String guessedCharacter) {
		// maybe?
	}
	
	public void sendMessage() {
		
	}
	
}

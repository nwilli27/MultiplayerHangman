package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class handles interactions with a distinct client.
 * @author Nolan W, Carsen B, Tristen R
 */
public class ClientHandler implements Runnable {

	private String username;

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	/**
	 * Constructor for a handler that interacts with a client.
	 * @param username
	 * @param clientSocket
	 * @param output
	 */
	public ClientHandler(String username, ObjectOutputStream output, ObjectInputStream input) {
		this.username = username;
		this.outputStream = output;
		this.inputStream = input;
	}

	/**
	 * Returns the clients username.
	 * @precondition: none
	 * @return the client username.
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * Returns the handlers output stream.
	 * @precondition: none
	 * @return the handlers output stream.
	 */
	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}
	
	/**
	 * Sends a message to the client.
	 * @precondition none
	 * @param message to send
	 */
	public void sendMessage(String message) {
		
		var serializedMessage = new Message(message);
		
		try {
			this.outputStream.writeObject(serializedMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Implemented by runnable, 
	 */
	@Override
	public void run() {
		
		System.out.println(this.username + " is now in its own thread running.");
		
		try {
			
			var incomingRequest = this.inputStream.readObject().toString();
			RequestHandler.handleRequest(incomingRequest);
			
		} catch (ClassNotFoundException error) {
			
			error.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}

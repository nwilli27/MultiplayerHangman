package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class handles interactions with a distinct client.
 * @author Nolan W
 */
public class ClientHandler implements Runnable {

	private String username;
	private RequestHandler requestHandler;
	
	private Socket socket;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	/**
	 * Constructor for a handler that interacts with a client.
	 * @param username
	 * @param clientSocket
	 * @param output
	 */
	public ClientHandler(String username, Socket clientSocket, ObjectOutputStream output) {
		this.username = username;
		this.socket = clientSocket;
		this.outputStream = output;
		this.requestHandler = new RequestHandler();
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
	public ObjectOutputStream getOutgoingMessages() {
		return outputStream;
	}

	/**
	 * Implemented by runnable, 
	 */
	@Override
	public void run() {
		
		System.out.println(this.username + " is now in its own thread running.");
		
		this.setupStreams();
		
		try {
			
			var incomingRequest = this.inputStream.readObject().toString();
			requestHandler.handleRequest(incomingRequest);
			
		} catch (ClassNotFoundException error) {
			
			error.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	private void setupStreams() {
		
		try {
			this.inputStream = new ObjectInputStream(this.socket.getInputStream());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.SocketException;

import enums.MessageType;

/**
 * Class handles interactions with a distinct client.
 * @author Nolan W, Carson B, Tristen R
 */
public class ClientHandler implements Runnable {

	private String username;
	private int guessCount;

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	/**
	 * Constructor for a handler that interacts with a client.
	 * @param username name of client
	 * @param output the stream for output
	 * @param input the stream for input
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
		return this.outputStream;
	}
	
	/**
	 * Sends a message to the client.
	 * @precondition none
	 * @param type of message
	 * @param message to send
	 */
	public void sendMessage(MessageType type, String message) {
		
		var serializedMessage = new Message(type, message);
		
		try {
			this.outputStream.writeObject(serializedMessage);
			this.outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Closes the in and out streams of the current client.
	 * 
	 * @precondition none
	 * @postcondition none
	 */
	public void closeStreams() {
		
		try {
			
			this.inputStream.close();
			this.outputStream.close();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * Increments the clients guess counter by 1.
	 * @postcondition this.guessCounter += 1
	 */
	public void incrementGuessCount() {
		this.guessCount++;
	}
	
	/**
	 * Returns the total # of guesses
	 * @return the guess counter 
	 */
	public int getGuessCount() {
		return this.guessCount;
	}

	/**
	 * Implemented by runnable, 
	 */
	@Override
	public void run() {
		
		while (true) {
			
			try {
				
				var incomingRequest = (Message) this.inputStream.readObject();
				RequestHandler.handleRequest(incomingRequest, this.username);
				
			} catch (SocketException err) {
				
				System.out.println("The client " + this.username + " disconnected.");
				this.closeStreams();
				
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}

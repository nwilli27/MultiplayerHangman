package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.LoginPage;
import view.MainPage;

/**
 * Class that sends and receives messages from server
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class ClientConnection implements Runnable {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;

	private static String username;

	private MainPage controller;

	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	/**
	 * Creates a listener object with the given username and controller
	 * 
	 * @param name username of the person logged in
	 * @param con  controller for the mainpage
	 */
	public ClientConnection() {

	}

	@Override
	public void run() {

		while(true) {
			
		}
	}

	private void handleResponse(String response, String responseType) throws IOException {
		switch (responseType) {
		case "nudge":
			this.controller.showServerMessage("");
			break;
		case "playerConnect":
			this.controller.showServerMessage("User " + response + " has joined the game.");
			break;
		case "playerExit":
			this.controller.showServerMessage("User" + response + " has left the game.");
			break;
		case "incorrectGuess":
			this.controller.showServerMessage("");
			this.controller.handleNextWrongGuess();
			break;
		case "correctGuess":
			this.controller.showServerMessage("");
			break;
		case "wordGuess":
			this.controller.showServerMessage(response + " was guessed.");
		case "characterGuess":
			this.controller.updateGuessedCharacters();
		case "taken":
			break;
		default:
			break;
		}

	}

	/**
	 * Sends a message to the server
	 * 
	 * @param msg Message to be sent
	 * @throws IOException Exception
	 */
	public void send(String msg) {
		try {
			var message = new Message(msg);
			this.output.writeObject(message);
			this.output.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public String read() {

		Message message = null;
		try {
			message = (Message) this.input.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return message.getMessage();

	}

	public void initializeStreams() {
		try {
			this.socket = new Socket(HOST, PORT);
			output = new ObjectOutputStream(this.socket.getOutputStream());
			input = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
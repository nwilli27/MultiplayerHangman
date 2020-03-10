package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.LoginController;
import view.MainPageController;

/**
 * Class that sends and receives messages from server
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class Listener implements Runnable {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;

	private static String username;

	private MainPageController controller;

	private Socket socket;
	private static ObjectOutputStream oos;
	private InputStream is;
	private ObjectInputStream input;
	private OutputStream outputStream;

	/**
	 * Creates a listener object with the given username and controller
	 * @param name username of the person logged in
	 * @param con controller for the mainpage
	 */
	public Listener(String name, MainPageController con) {
		username = name;
		this.controller = con;
	}

	@Override
	public void run() {

		try {
			this.socket = new Socket(HOST, PORT);
			this.outputStream = this.socket.getOutputStream();
			oos = new ObjectOutputStream(this.outputStream);
			this.is = this.socket.getInputStream();
			this.input = new ObjectInputStream(this.is);
		} catch (IOException e) {
			System.out.println("Could not connect to server");
		}
		System.out.println("Connection accepted " + this.socket.getInetAddress() + ":" + this.socket.getPort());

		try {
			connect();
			System.out.println("Sockets in and out ready!");
			while (this.socket.isConnected()) {
				Message incomingMessage = null;
				incomingMessage = (Message) this.input.readObject();
				var message = incomingMessage.getMessage();

				if (message != null && this.controller != null) {
					System.out.println("Message recieved: " + message);
					var splitMessage = message.split("#");
					var responseType = splitMessage[0];
					var response = splitMessage[1];

					this.handleResponse(response, responseType);

				}
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private void handleResponse(String response, String responseType) throws IOException {
		switch (responseType) {
			case "nudge":
				this.controller.showServerMessage("");
				break;
			case "playerConnect":
				LoginController.getInstance().showScene();
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
				LoginController.getInstance()
					.showServerMessage("The username: " + response + " has been taken. Please try another.");
				break;
			default:
				break;
		}

	}

	/**
	 * Sends a message to the server
	 * @param msg Message to be sent
	 * @throws IOException Exception
	 */
	public static void send(String msg) throws IOException {
		oos.writeObject(msg);
		oos.flush();
	}

	/**
	 * Sends an initial log in message to server
	 * @throws IOException exception
	 */
	public static void connect() throws IOException {
		Message message = new Message("login#" + username);
		oos.writeObject(message);
	}

}
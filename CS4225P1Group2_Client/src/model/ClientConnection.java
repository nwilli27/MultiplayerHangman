package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import enums.MessageType;
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

	private static ArrayList<Message> incomingMessages;

	/**
	 * Creates a listener object with the given username and controller
	 * 
	 * @param name username of the person logged in
	 * @param con  controller for the mainpage
	 */
	public ClientConnection() {
		incomingMessages = new ArrayList<Message>();
	}

	@Override
	public void run() {

		while (true) {
			var message = this.read();
			if (message != null) {
				ClientConnection.incomingMessages.add(message);
			}
		}
	}

	/**
	 * Sends a message to the server
	 * 
	 * @param msg Message to be sent
	 * @throws IOException Exception
	 */
	public void send(MessageType type, String msg) {
		try {
			var message = new Message(type, msg);
			this.output.writeObject(message);
			this.output.flush();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public Message getFirstOfMessage(MessageType type) {

		Message firstMessage = null;
		var tempList = new ArrayList<Message>(ClientConnection.incomingMessages);
		for (var message : tempList) {

			if (message.getType() == type) {
				if (!message.isCompleted()) {
					if (type == MessageType.GuessUpdate || type == MessageType.BodyCount) {
						this.removeMessage(type);
					}
					return message;
				}
			}
		}
		return firstMessage;
	}

	private void removeMessage(MessageType type) {
		for (var message : ClientConnection.incomingMessages) {

			if (message.getType() == type) {
				if (!message.isCompleted()) {
					if (type == MessageType.GuessUpdate) {

						ClientConnection.incomingMessages.remove(message);
						return;
					}

					if (type == MessageType.BodyCount) {

						ClientConnection.incomingMessages.remove(message);
						return;
					}
				}
			}
		}
	}

	private Message read() {

		Message message = null;
		try {
			message = (Message) this.input.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return message;

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
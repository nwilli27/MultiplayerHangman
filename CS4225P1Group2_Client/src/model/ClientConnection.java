package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import enums.MessageType;

/**
 * Class that sends and receives messages from server
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class ClientConnection implements Runnable {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;

	private Socket socket;
	private ObjectOutputStream output;
	private ObjectInputStream input;

	private static ArrayList<Message> incomingMessages;

	/**
	 * Creates a client connection object and initializes the incoming messages
	 * 
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
	 * @param type The type of message being sent
	 * @param msg  The content of the message being sent
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

	/**
	 * Gets the first message of the specified type from incoming messages
	 * 
	 * @param type Type of message to look for
	 * @return Returns the first message of the specified type from incoming
	 *         messages
	 */
	public Message getFirstOfMessage(MessageType type) {

		Message firstMessage = null;
		var tempList = new ArrayList<Message>(ClientConnection.incomingMessages);
		this.removeMessage(type);
		for (var message : tempList) {

			if (message.getType() == type) {
				if (!message.isCompleted()) {
					firstMessage = message;
					break;
				}
			}
		}
		return firstMessage;
	}

	private void removeMessage(MessageType type) {
		for (var message : ClientConnection.incomingMessages) {

			if (message.getType() == type) {
				if (!message.isCompleted()) {
					if (type == MessageType.GuessUpdate || type == MessageType.BodyCount) {

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

	/**
	 * Creates a socket with the specified host and port
	 */
	public void initializeStreams() {
		try {
			this.socket = new Socket(HOST, PORT);
			this.output = new ObjectOutputStream(this.socket.getOutputStream());
			this.input = new ObjectInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}

	}

}
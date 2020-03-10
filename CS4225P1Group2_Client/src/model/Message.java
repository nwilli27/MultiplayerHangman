package model;

import java.io.Serializable;

/**
 * Message class to be serialized and sent to server
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	/**
	 * Creates a message object with the given string as the message
	 * @param message Message to be sent
	 */
	public Message(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the message to be sent
	 * @return the message to be sent
	 */
	public String getMessage() {
		return this.message;
	}
}

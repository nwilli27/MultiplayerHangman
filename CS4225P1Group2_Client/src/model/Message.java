package model;

import java.io.Serializable;

import enums.MessageType;

/**
 * Encapsulates a message to be serializable.
 * @author Nolan W, Carson B, Tristen R
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;
	private MessageType type;

	/**
	 * Constructor to initialize a message.
	 * @precondition none
	 * @param message to send
	 */
	public Message(MessageType type, String message) {
		this.message = message;
		this.type = type;
	}
	
	/**
	 * Returns the message.
	 * @return the messsage.
	 */
	public String getMessage() {
		return this.message;
	}
	
	/**
	 * Returns the type of message.
	 * @return the type of message.
	 */
	public MessageType getType() {
		return this.type;
	}
}

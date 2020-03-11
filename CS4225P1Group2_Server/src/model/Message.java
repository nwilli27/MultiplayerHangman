package model;

import java.io.Serializable;

/**
 * Encapsulates a message to be serializable.
 * @author Nolan W, Carsen B, Tristen R
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String message;

	/**
	 * Constructor to initialize a message.
	 * @precondition none
	 * @param message to send
	 */
	public Message(String message) {
		this.message = message;
	}
	
	/**
	 * Returns the message.
	 * @return the messsage.
	 */
	public String getMessage() {
		return this.message;
	}
}

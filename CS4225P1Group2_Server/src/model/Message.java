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
	private boolean isCompleted;

	/**
	 * Constructor to initialize a message.
	 * @precondition none
	 * @param message to send
	 * @param type the type of message
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
	
	/**
	 * Returns if the message has been completed.
	 * @return true if completed; otherwise false
	 */
	public boolean isCompleted() {
		return this.isCompleted;
	}
	
	/**
	 * Sets if the message has been completed.
	 * @precondition none
	 * @postcondition this.isCompleted = condition
	 * @param condition the condition to set
	 */
	public void setIsCompleted(boolean condition) {
		this.isCompleted = condition;
	}
	
}

package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	public Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}

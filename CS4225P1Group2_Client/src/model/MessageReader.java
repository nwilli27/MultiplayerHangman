package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.Controller;

public class MessageReader implements Runnable {

	
	private ObjectInputStream inputMessage;
	
	private String message;
	
	private Controller controller;
	
	
	
	public MessageReader(ObjectInputStream input) {
		this.inputMessage = input;
		this.controller = new Controller();
	}
	
	
	
	@Override
	public void run() {
		
		while (true) {

			try {
				var message = (String) this.inputMessage.readObject();
				this.controller.handleIncomingMessages(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

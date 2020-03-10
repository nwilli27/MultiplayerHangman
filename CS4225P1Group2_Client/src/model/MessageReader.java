package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MessageReader implements Runnable {

	
	private ObjectInputStream inputMessage;
	
	private String message;
	
	private MessageHandler messageHandler;
	
	
	
	public MessageReader(ObjectInputStream input) {
		this.inputMessage = input;
		this.messageHandler = new MessageHandler();
	}
	
	
	
	@Override
	public void run() {
		
		while (true) {

			try {
				var message = (String) this.inputMessage.readObject();
				this.messageHandler.handleMessage(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

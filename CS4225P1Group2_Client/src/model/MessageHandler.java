package model;

public class MessageHandler {
	
	
	

	public void handleMessage(String message) {
		if(message.equalsIgnoreCase("nudge")) {
			System.out.println("Successful");
		}
		
	}

}

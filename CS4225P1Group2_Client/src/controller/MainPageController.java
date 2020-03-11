package controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.Message;

public class MainPageController {

	
	private static TextArea messageFromServer;
	private static TextArea guessedLetters;
	private static Button guessButton;
	
	
	public MainPageController(TextArea messageFromServerText, TextArea guessedLettersTextArea, Button guessBtn) {
		messageFromServer = messageFromServerText;
		guessedLetters = guessedLettersTextArea;
		guessButton = guessBtn;
	}


	public void handleGuess(String guess) {
		LoginController.getClient().send("guess#" + guess);
		
		var incomingMessage = LoginController.getClient().read();
		
		if(incomingMessage.split("#")[0] == "characterGuess") {
			messageFromServer.setText("Hello");
		}
		
	}

}

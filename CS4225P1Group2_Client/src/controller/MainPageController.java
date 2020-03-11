package controller;

import java.util.concurrent.TimeUnit;

import enums.MessageType;
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
		LoginController.getClient().send(MessageType.Guess, guess);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		var incomingGuessMessage = LoginController.getClient().getFirstOfMessage(MessageType.GuessValue);
		incomingGuessMessage.setIsCompleted(true);
		
		var incomingUpdateMessage = LoginController.getClient().getFirstOfMessage(MessageType.UpdatedWord);
		incomingUpdateMessage.setIsCompleted(true);
		
		var incomingBodyCountMessage = LoginController.getClient().getFirstOfMessage(MessageType.BodyCount);
		incomingBodyCountMessage.setIsCompleted(true);
		
		var formattedGuess = incomingGuessMessage.getMessage().split("#")[0];
		var username = incomingGuessMessage.getMessage().split("#")[1];
		messageFromServer.appendText("User " + username + " guessed " + formattedGuess + "..." + System.lineSeparator());
		
		
	}
	
	public static void newUserLoggedin(String message) {
		messageFromServer.appendText(message + " has logged in..." + System.lineSeparator());
		
	}
	

}

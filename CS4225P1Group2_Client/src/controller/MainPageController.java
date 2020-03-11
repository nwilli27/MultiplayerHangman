package controller;

import java.util.concurrent.TimeUnit;

import enums.MessageType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.Message;

public class MainPageController {

	
	private static TextArea messageFromServer;
	private static TextArea guessedLetters;
	private static Button guessButton;
	private static Text wordGuessText;
	
	
	public MainPageController(TextArea messageFromServerText, TextArea guessedLettersTextArea, Button guessBtn, Text wordGuessBox) {
		messageFromServer = messageFromServerText;
		guessedLetters = guessedLettersTextArea;
		guessButton = guessBtn;
		wordGuessText = wordGuessBox;
	}


	public int handleGuess(String guess) {
		LoginController.getClient().send(MessageType.Guess, guess);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		var bodyCount = 0;
		var incomingGuessMessage = LoginController.getClient().getFirstOfMessage(MessageType.GuessUpdate);
		if(incomingGuessMessage != null) {
			var userGuess = incomingGuessMessage.getMessage().split("#")[1];
			var username = incomingGuessMessage.getMessage().split("#")[0];
			var formattedWord = incomingGuessMessage.getMessage().split("#")[2];
			 bodyCount = Integer.parseInt(incomingGuessMessage.getMessage().split("#")[3]);
			
			messageFromServer.appendText("User " + username + " guessed " + userGuess + "..." + System.lineSeparator());
			wordGuessText.setText(formattedWord);
			incomingGuessMessage.setIsCompleted(true);
		}
		

		return bodyCount;
		
		
	}
	
	public static void newUserLoggedin(String message) {
		messageFromServer.appendText(message + " has logged in..." + System.lineSeparator());
		
	}
	
	public static void userGuessed(String username, String userGuess) {
		messageFromServer.appendText(username + " guessed "  + userGuess + "..." + System.lineSeparator());
		
	}
	
	public static void addWordGuess(String word) {
		wordGuessText.setText(word);
		
	}
	
	

}

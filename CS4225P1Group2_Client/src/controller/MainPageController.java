package controller;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import enums.MessageType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import model.Message;
import view.BodyPart;

public class MainPageController {

	private static TextArea messageFromServer;
	private static TextArea guessedLetters;
	private static Button guessButton;
	private static Text wordGuessText;
	private static ArrayList<BodyPart> bodyParts;

	public MainPageController(TextArea messageFromServerText, TextArea guessedLettersTextArea, Button guessBtn,
			Text wordGuessBox, ArrayList<BodyPart> parts) {
		messageFromServer = messageFromServerText;
		guessedLetters = guessedLettersTextArea;
		guessButton = guessBtn;
		wordGuessText = wordGuessBox;
		bodyParts = parts;
	}

	public void handleGuess(String guess) {
		LoginController.getClient().send(MessageType.Guess, guess);


	}

	private static void showBodyParts(int amount) {
		for (int i = 0; i < amount; i++) {
			var currentBodyPart = bodyParts.get(i);
			currentBodyPart.enableShapes();
		}

	}

	public static void newUserLoggedin(String message) {
		messageFromServer.appendText(message + " has logged in..." + System.lineSeparator());

	}

	public static void userGuessed(String username, String userGuess) {
		messageFromServer.appendText(username + " guessed " + userGuess + "..." + System.lineSeparator());

	}

	public static void addWordGuess(String word) {
		wordGuessText.setText(word);

	}
	
	public static void bodyPartGuesses(int count) {
		showBodyParts(count);
	}
	

}

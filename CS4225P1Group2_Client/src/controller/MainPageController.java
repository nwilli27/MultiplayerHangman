package controller;

import java.util.ArrayList;
import enums.MessageType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import view.BodyPart;

/**
 * Controller class for the main log in page
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class MainPageController {

	private static TextArea messageFromServer;
	private static TextArea guessedLetters;
	private static Button guessButton;
	private static Text wordGuessText;
	private static ArrayList<BodyPart> bodyParts;
	private static Text gameStatus;

	/**
	 * Creates MainPageController object with given ui elements
	 * 
	 * @param messageFromServerText  Text area for server text
	 * @param guessedLettersTextArea Text area for guessed letters
	 * @param guessBtn               Button for guessing
	 * @param wordGuessBox           Word that users are guessing
	 * @param gameStatusText         the current status of the game
	 * @param parts                  Body parts for the man in the gui
	 */
	public MainPageController(TextArea messageFromServerText, TextArea guessedLettersTextArea, Button guessBtn,
			Text wordGuessBox, Text gameStatusText, ArrayList<BodyPart> parts) {
		messageFromServer = messageFromServerText;
		guessedLetters = guessedLettersTextArea;
		guessButton = guessBtn;
		wordGuessText = wordGuessBox;
		bodyParts = parts;
		gameStatus = gameStatusText;
	}

	/**
	 * Sends a guess message to the server
	 * 
	 * @param guess The message to send to the server as a guess
	 */
	public void handleGuess(String guess) {
		LoginController.getClient().send(MessageType.Guess, guess);

	}

	private static void showBodyParts(int amount) {
		for (int i = 0; i < amount; i++) {
			var currentBodyPart = bodyParts.get(i);
			currentBodyPart.enableShapes();
		}
		if (amount >= 6) {
			guessButton.setDisable(true);
			alertForTurn("The game is over, you have lost");
			gameStatus.setText("GAME OVER");
			gameStatus.setVisible(true);
		}

	}

	/**
	 * Shows a message alerting players that a new player has joined
	 * 
	 * @param message Message alerting players that a new player has joined
	 */
	public static void handleNewUserLogin(String message) {
		messageFromServer.appendText(message + " has logged in..." + System.lineSeparator());

	}
	
	/**
	 * Logs the current user out of the game
	 */
	public static void handleLogOut() {
		LoginController.getClient().send(MessageType.Disconnect, "");
	}

	/**
	 * Appends text to the guessed letters and message from server alerting of a
	 * guess
	 * 
	 * @param username  The user guessing
	 * @param userGuess The guess the user made
	 */
	public static void userGuessed(String username, String userGuess) {
		if (!guessedLetters.getText().contains(userGuess) && userGuess.length() == 1) {

			guessedLetters.appendText(userGuess + System.lineSeparator());
		}
		messageFromServer.appendText(username + " guessed " + userGuess + "..." + System.lineSeparator());

	}

	/**
	 * Alerts the user who's turn it is
	 * 
	 * @param message The message to display
	 */
	public static void alertForTurn(String message) {
		messageFromServer.appendText(message + "..." + System.lineSeparator());
	}

	/**
	 * Sets up the game with the word to guess, body, and characters guessed
	 * 
	 * @param formattedWord The formatted word to guess
	 * @param charsGuessed  Characters already guessed
	 * @param bodyCount     Number of body parts to show
	 */
	public static void setUpGame(String formattedWord, String charsGuessed, int bodyCount) {
		addWordGuess(formattedWord);
		bodyPartGuesses(bodyCount);

		var letters = charsGuessed.split("");
		for (var letter : letters) {
			guessedLetters.appendText(letter + System.lineSeparator());
		}
	}

	/**
	 * Enables the guess button
	 */
	public static void enableButton() {
		guessButton.setDisable(false);
	}

	/**
	 * Sets the word guess text
	 * 
	 * @param word THe word to be set
	 */
	public static void addWordGuess(String word) {
		wordGuessText.setText(word);

	}

	/**
	 * Handles showing the body parts based on the count
	 * 
	 * @param count The number of body parts to show
	 */
	public static void bodyPartGuesses(int count) {
		showBodyParts(count);
	}

	/**
	 * Handles if a user wins the game
	 * 
	 * @param name The user name of the winner
	 */
	public static void gameWon(String name) {

		guessButton.setDisable(true);
		alertForTurn(name + " has won the game");
		gameStatus.setText(name.toUpperCase() + " HAS WON THE GAME");
		gameStatus.setVisible(true);
	}

}

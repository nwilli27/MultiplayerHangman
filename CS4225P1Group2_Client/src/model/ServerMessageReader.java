package model;

import controller.LoginController;
import controller.MainPageController;
import enums.MessageType;
import javafx.application.Platform;

/**
 * Class that listens for specific responses from the server
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class ServerMessageReader implements Runnable {

	@Override
	public void run() {

		while (true) {

			var loggedIn = LoginController.getClient().getFirstOfMessage(MessageType.ValidUser);
			var initialState = LoginController.getClient().getFirstOfMessage(MessageType.GameState);
			var guess = LoginController.getClient().getFirstOfMessage(MessageType.GuessUpdate);
			var part = LoginController.getClient().getFirstOfMessage(MessageType.BodyCount);
			var turn = LoginController.getClient().getFirstOfMessage(MessageType.YourGuessTurn);
			var otherTurn = LoginController.getClient().getFirstOfMessage(MessageType.OtherGuessTurn);
			var winner = LoginController.getClient().getFirstOfMessage(MessageType.UserWon);

			this.handleLoggedIn(loggedIn);

			this.handleInitialState(initialState);

			this.handleUserTurn(turn);

			this.handleUserGuess(guess);

			this.handleBodyPartCount(part);

			this.handleOtherTurn(otherTurn);
			
			this.handleWinner(winner);

		}

	}

	private void handleOtherTurn(Message otherTurn) {
		if (otherTurn != null) {

			otherTurn.setIsCompleted(true);

			Platform.runLater(() -> {
				MainPageController.alertForTurn(otherTurn.getMessage() + " is now choosing");
			});

		}
	}

	private void handleBodyPartCount(Message part) {
		if (part != null) {

			var message = part.getMessage();
			var count = Integer.parseInt(message);
			Platform.runLater(() -> {
				MainPageController.bodyPartGuesses(count);
			});

		}
	}

	private void handleUserGuess(Message guess) {
		if (guess != null) {

			var splitMessage = guess.getMessage().split("#");
			var username = splitMessage[0];
			var userGuess = splitMessage[1];
			var formattedWord = splitMessage[2];
			Platform.runLater(() -> {
				MainPageController.userGuessed(username, userGuess);
				MainPageController.addWordGuess(formattedWord);
			});

		}
	}

	private void handleUserTurn(Message turn) {
		if (turn != null) {

			turn.setIsCompleted(true);

			Platform.runLater(() -> {
				MainPageController.enableButton();
				MainPageController.alertForTurn("You are now choosing");
			});

		}
	}

	private void handleInitialState(Message initialState) {
		if (initialState != null) {
			initialState.setIsCompleted(true);
			var messageSplit = initialState.getMessage().split("#");
			var formattedWord = messageSplit[0];
			var charsGuessed = messageSplit[1];
			var bodyCount = Integer.parseInt(messageSplit[2]);

			Platform.runLater(() -> {
				MainPageController.setUpGame(formattedWord, charsGuessed, bodyCount);
			});
		}
	}

	private void handleWinner(Message winner) {
		if (winner != null) {
			winner.setIsCompleted(true);

			var name = winner.getMessage();

			Platform.runLater(() -> {
				MainPageController.gameWon(name);
			});
		}
	}

	private void handleLoggedIn(Message loggedIn) {
		if (loggedIn != null) {

			var user = LoginController.getClient().getFirstOfMessage(MessageType.NewUser);

			if (user != null) {

				user.setIsCompleted(true);

				Platform.runLater(() -> {
					MainPageController.handleNewUserLogin(user.getMessage());
				});
			}

		}
	}

}

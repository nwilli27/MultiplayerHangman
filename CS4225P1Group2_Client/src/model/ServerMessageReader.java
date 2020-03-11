package model;

import controller.LoginController;
import controller.MainPageController;
import enums.MessageType;
import javafx.application.Platform;

public class ServerMessageReader implements Runnable {

	@Override
	public void run() {


		while (true) {

			var loggedIn = LoginController.getClient().getFirstOfMessage(MessageType.ValidUser);
			var initialState = LoginController.getClient().getFirstOfMessage(MessageType.GameState);
			var guess = LoginController.getClient().getFirstOfMessage(MessageType.GuessUpdate);
			var part = LoginController.getClient().getFirstOfMessage(MessageType.BodyCount);
			var turn = LoginController.getClient().getFirstOfMessage(MessageType.YourGuessTurn);

			if (loggedIn != null) {

				var user = LoginController.getClient().getFirstOfMessage(MessageType.NewUser);

				if (user != null) {

					user.setIsCompleted(true);

					Platform.runLater(() -> {
						MainPageController.newUserLoggedin(user.getMessage());
					});
				}

			}

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
			
			if (turn != null) {

				turn.setIsCompleted(true);

				Platform.runLater(() -> {
					MainPageController.enableButton();
				});

			}

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

			if (part != null) {

				var message = part.getMessage();
				var count = Integer.parseInt(message);
				Platform.runLater(() -> {
					MainPageController.bodyPartGuesses(count);
				});

			}

		}

	}

}

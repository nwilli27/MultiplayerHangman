package model;

import controller.LoginController;
import controller.MainPageController;
import enums.MessageType;
import javafx.application.Platform;

public class ServerMessageReader implements Runnable {

	@Override
	public void run() {

		Message previousGuess = null;

		while (true) {

			var loggedIn = LoginController.getClient().getFirstOfMessage(MessageType.ValidUser);
			var guess = LoginController.getClient().getFirstOfMessage(MessageType.GuessUpdate);
			var part = LoginController.getClient().getFirstOfMessage(MessageType.BodyCount);

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

			if (loggedIn != null) {

				var user = LoginController.getClient().getFirstOfMessage(MessageType.NewUser);

				if (user != null) {

					user.setIsCompleted(true);

					Platform.runLater(() -> {
						MainPageController.newUserLoggedin(user.getMessage());
					});
				}

			}

		}

	}

}

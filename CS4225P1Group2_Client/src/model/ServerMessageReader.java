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

			if (loggedIn != null) {

				var user = LoginController.getClient().getFirstOfMessage(MessageType.NewUser);

				if (user != null) {

					user.setIsCompleted(true);

					Platform.runLater(() -> { MainPageController.newUserLoggedin(user.getMessage()); } );
				}
			}

		}

	}

}

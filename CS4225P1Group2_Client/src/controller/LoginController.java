package controller;

import java.util.concurrent.TimeUnit;

import enums.MessageType;
import javafx.scene.text.Text;
import model.ClientConnection;
import model.ServerMessageReader;

/**
 * Controller for the login page
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class LoginController {

	private static Text errorText;
	private static Text usernameTakenText;
	private static ClientConnection client;

	private Thread[] threadPool;

	/**
	 * Creates a login controller object with the given text fields
	 * @param error Error Text
	 * @param usernameTaken username Taken text
	 */
	public LoginController(Text error, Text usernameTaken) {
		errorText = error;
		usernameTakenText = usernameTaken;
		this.threadPool = new Thread[2];

	}

	/**
	 * Handles the log in of a user
	 * @param username username of the user
	 * @return True if log in was successful, false if not
	 */
	public boolean handleLogin(String username) {
		this.startThreads();
		client.send(MessageType.Login, username);
		
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		var validMsg = client.getFirstOfMessage(MessageType.ValidUser);
		var usernameTakenMsg = client.getFirstOfMessage(MessageType.TakenUser);
		var maxUsersMsg = client.getFirstOfMessage(MessageType.MaxUsers);
		
		if (validMsg != null) {
			client.send(MessageType.NewUser, username);
			return true;
		} else if (usernameTakenMsg != null) {
			usernameTakenMsg.setIsCompleted(true);
			usernameTakenText.setVisible(true);
			usernameTakenText.setText("Sorry that username is taken");
			return  false;
		} else if (maxUsersMsg != null) {
			maxUsersMsg.setIsCompleted(true);
			errorText.setVisible(true);
			errorText.setText("Server is currently full");
			return false;
		} else {
			return false;
		}
		
		
	}

	/**
	 * Returns the client
	 * @return Returns the client
	 */
	public static ClientConnection getClient() {
		return client;
	}

	private void startThreads() {

		client = new ClientConnection();
		client.initializeStreams();
		ServerMessageReader reader = new ServerMessageReader();
		this.threadPool[0] = new Thread(client);
		this.threadPool[1] = new Thread(reader);
		

		this.threadPool[0].start();
		this.threadPool[1].start();
	}

}

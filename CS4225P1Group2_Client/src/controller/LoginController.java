package controller;

import java.io.IOException;

import enums.MessageType;
import javafx.scene.text.Text;
import model.ClientConnection;
import model.Message;
import model.ServerMessageReader;

public class LoginController {

	private static Text errorText;
	private static Text usernameTakenText;
	private static ClientConnection client;

	private Thread[] threadPool;

	public LoginController(Text error, Text usernameTaken) {
		errorText = error;
		usernameTakenText = usernameTaken;
		this.threadPool = new Thread[2];

	}

	public boolean handleLogin(String username) {
		this.startThreads();
		client.send(MessageType.Login, username);

		return (client.getFirstOfMessage("playerConnect") != null);
	}

	public static ClientConnection getClient() {
		return client;
	}

	private void startThreads() {

		client = new ClientConnection();
		client.initializeStreams();
		ServerMessageReader reader = new ServerMessageReader();
		this.threadPool[0] = new Thread(reader);
		this.threadPool[1] = new Thread(client);

		this.threadPool[0].start();
		this.threadPool[1].start();
	}

}

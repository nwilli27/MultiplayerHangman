package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import enums.MessageType;

/**
 * Class holds the client handling and initialization of the game.
 * @author Nolan W, Carsen B, Tristen R
 */
public class Server implements Runnable {

	private static final int PORT = 4225;
	private ServerSocket serverSocket;
	private Socket clientSocket;
	
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	
	/**
	 * Constructor for Server
	 */
	public Server() {
		ClientManager.initializeClientList();
		RequestHandler.intializeGame();
		this.setupSocket();
	}
	
	/**
	 * Handles first connection with new clients and handles their requests.
	 * @precondition: none
	 * @postcondition: none
	 */
	@Override
	public void run() {
		
		try {	
			
			while (true) {
				
				try {
					
					this.clientSocket = this.serverSocket.accept();
					this.serverInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
					this.serverOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());

				} catch (UnknownHostException e) {
					System.err.println("Problem with the host.");
				} catch (IOException e) {
					System.err.println("Unable to connect; very likely that the server was not started.");
				}
				
				this.handleRequest();
			}
			
		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:  " + e);
		}
	}
	
	private void handleRequest() throws ClassNotFoundException, IOException {
		
		if (this.clientSocket != null && this.serverOutputStream != null && this.serverInputStream != null) {

			var request = (Message) this.serverInputStream.readObject();
			var requestMsg = request.getMessage();
			var requestTokens = requestMsg.split("#");
			
			switch (request.getType()) {
			
				case Login:
					var username = requestTokens[0];
					this.handleCreateClientRequest(username);
					
				default:
					// do other request
			}
		}
	}

	private void handleCreateClientRequest(String username) throws IOException {
		
		if (ClientManager.hasMaxClients()) {
			
			this.sendConnectingClientMsg(MessageType.MaxUsers, username);
			
		} else {
			
			if (!ClientManager.doesClientExists(username)) {
			
				var client = new ClientHandler(username, this.serverOutputStream, this.serverInputStream);
				ClientManager.addClient(client);
				var thread = new Thread(client);
				thread.start();
				
				this.sendConnectingClientMsg(MessageType.ValidUser, "");
				ClientManager.broadcastMessage(MessageType.NewUser, username);
				
			} else {

				this.sendConnectingClientMsg(MessageType.TakenUser, username);
			}
		}
	}
	
	private void sendConnectingClientMsg(MessageType type, String message) throws IOException {
		
		this.serverOutputStream.writeObject(new Message(type, message));
	}
	
	private void setupSocket() {

		try {
			this.serverSocket = new ServerSocket(PORT);
			System.out.println("Server is running...");
		} catch (Exception e) {
			System.err.println("IOException:  " + e + " -- most probably the server is already started.");
			System.exit(-1);
		}
	}

}

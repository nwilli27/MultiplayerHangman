package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Runnable {

	private static final int PORT = 4225;
	private ServerSocket serverSocket;
	
	private Socket clientSocket;
	private ObjectInputStream serverInputStream;
	private ObjectOutputStream serverOutputStream;
	
	private Socket gameSocket;
	private ObjectInputStream gameInputStream;
	private ObjectOutputStream gameOutputStream;
	
	private ClientManager clientManager;
	
	public Server() {
		this.clientManager = new ClientManager();
		this.setupSocket();
	}
	
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
			var requestType = requestTokens[0];
			
			switch (requestType) {
			
				case "login":
					var username = requestTokens[1];
					this.handleCreateClientRequest(username);
					
				default:
					// do other request
			}
		}
		
	}

	private void handleCreateClientRequest(String username) throws IOException {
		
		if (this.clientManager.hasMaxClients()) {
			
			// Notify client that "Game room full - please try again later."
			
		} else {
			
			if (!this.clientManager.doesClientExists(username)) {
			
				var client = new ClientHandler(username, this.clientSocket, this.serverOutputStream);
				this.clientManager.addClient(client);
				var thread = new Thread(client);
				thread.start();
				
				this.clientManager.broadcastMessage("playerConnect#" + username);
				
			} else {

				this.sendMessage("taken#" + username);
			}
		}
	}
	
	private void sendMessage(String message) throws IOException {
		
		this.serverOutputStream.writeObject(new Message(message));
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

	/**
	 * Stops the server
	 * 
	 * @precondition none
	 * @postcondition Server is stopped
	 * 
	 */
	public void stop() {
		try {
			this.serverSocket.close();
			this.clientSocket.close();
			this.serverInputStream.close();
			this.serverOutputStream.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void setupServerRequestStreams() {

		try {
			this.clientSocket = this.serverSocket.accept();
			this.serverInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
			this.serverOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());

		} catch (UnknownHostException e) {
			System.err.println("Problem with the host.");
		} catch (IOException e) {
			System.err.println("Unable to connect; very likely that the server was not started.");
		}
	}
	
	private void setupGameRequestStreams() {

		try {
			this.gameSocket = this.serverSocket.accept();
			this.gameInputStream = new ObjectInputStream(this.clientSocket.getInputStream());
			this.gameOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());

		} catch (UnknownHostException e) {
			System.err.println("Problem with the host.");
		} catch (IOException e) {
			System.err.println("Unable to connect; very likely that the server was not started.");
		}
	}

}

package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Server {

	private RequestHandler requestHandler;
	private static final int PORT = 4225;

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private ObjectInputStream incomingMessages;
	private ObjectOutputStream outgoingMessages;

	public Server() {
		this.requestHandler = new RequestHandler();
	}

	public void Run() {
		// Run server endlessly handling request until some form of end occurs (unknown atm)
		this.setupSocket();

		try {

			while (true) {

				this.setupStreams();
				this.handleRequestResponse();
			}

		} catch (IOException e) {
			System.err.println("IOException:  " + e);
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:  " + e);
		}
	}

	private void handleRequestResponse() throws ClassNotFoundException, IOException { //TODO Probably refactored into two methods maybe

		if (this.clientSocket != null && this.outgoingMessages != null && this.incomingMessages != null) {

			System.out.println("Server - Reading request from client...");

			var incomingRequest = (String) this.incomingMessages.readObject(); // TODO This needs to be a Message class probably
			
			this.requestHandler.handleRequest(incomingRequest);

			System.out.println("Server - handling request...");

			this.outgoingMessages.writeObject(incomingRequest); // TODO
		}
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
			this.incomingMessages.close();
			this.outgoingMessages.close();
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private void setupStreams() {

		try {
			this.clientSocket = this.serverSocket.accept();
			this.incomingMessages = new ObjectInputStream(this.clientSocket.getInputStream());
			this.outgoingMessages = new ObjectOutputStream(this.clientSocket.getOutputStream());

		} catch (UnknownHostException e) {
			System.err.println("Problem with the host.");
		} catch (IOException e) {
			System.err.println("Unable to connect; very likely that the server was not started.");
		}
	}
}

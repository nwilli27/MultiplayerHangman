package clientConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientConnection {
	
	
	private static final String HOST = "localhost";
	private static final int PORT = 4225;



	private ObjectInputStream incomingMessages;
	private ObjectOutputStream outgoingMessages;
	private Socket clientSocket;

	/**
	 * Creates a client object
	 * 
	 * @precondition fileName != null && fileName != empty
	 * @postcondition matrixReader is created with the passed in file name
	 * 
	 * @param fileName The name of the file containing matrices
	 * 
	 */
	public ClientConnection() {
	}
	
	private void printMessage(String message) {
		
		var threadName = Thread.currentThread().getName();
		System.out.println("Client " + threadName + " - " + message);
	}

	public void sendRequest(String request) { //TODO Probably change String to some message class that implements serializable

			this.setupStreams();

			if (this.clientSocket != null && this.outgoingMessages != null && this.incomingMessages != null) {

				try {

					this.outgoingMessages.writeObject(request);
					this.handleMatrixResult();

				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} 

	private void handleMatrixResult() {

		try {
			this.incomingMessages.readObject();

			this.printErrMessage("Session ended." + System.lineSeparator());

			
			this.outgoingMessages.flush();
//			this.outgoingMessages.close();
//			this.incomingMessages.close();
//			this.clientSocket.close();

		} catch (IOException e) {

			this.printErrMessage("IOException: " + e);

		} catch (ClassNotFoundException e) {

			this.printErrMessage("ClassNotFoundException: " + e);
		}
	}
	
	private void printErrMessage(String message) {
		
		var threadName = Thread.currentThread().getName();
		System.err.println("Client " + threadName + " - " + message);
	}

	private void setupStreams() {

		try {

			this.clientSocket = new Socket(HOST, PORT);
			this.outgoingMessages = new ObjectOutputStream(this.clientSocket.getOutputStream());
			this.incomingMessages = new ObjectInputStream(this.clientSocket.getInputStream());
			this.printMessage("Connecting to server...");

		} catch (UnknownHostException e) {
			this.printErrMessage("Problem with the host.");
		} catch (IOException e) {
			this.printErrMessage("Unable to connect; very likely that the server was not started.");
		}
	}
	

}

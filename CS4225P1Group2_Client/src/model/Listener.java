package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import view.LoginController;
import view.MainPageController;

public class Listener implements Runnable {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;
	
	private static String username;
	
	private MainPageController controller;
	
    private Socket socket;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;
	
	
	public Listener(String name, MainPageController con) {
		username = name;
		this.controller = con;
	}



	@Override
	public void run() {
		
		try {
            socket = new Socket(HOST, PORT);
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
        } catch (IOException e) {
        	System.out.println("Could not connect to server");
        }
        System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            connect();
            System.out.println("Sockets in and out ready!");
            while (socket.isConnected()) {
                Message incomingMessage = null;
                incomingMessage = (Message) input.readObject();   
                var message = incomingMessage.getMessage();
                
                if (message != null) {
                    System.out.println("Message recieved: " + message);
                    var splitMessage = message.split("#");
                    var responseType = splitMessage[0];
                    var response = splitMessage[1];
                    switch (responseType) {
                        case "nudge":
                            controller.showServerMessage(message);
                            break;
                        case "playerConnect":
                        	LoginController.getInstance().showScene();
                            controller.showServerMessage("User " + response + " has joined the game.");
                            break;
                        case "playerExit":
                            controller.showServerMessage("User" + response + " has left the game.");
                            break;
                        case "incorrectGuess":
                            controller.showServerMessage(message);
                            controller.handleNextWrongGuess();
                            break;
                        case "correctGuess":
                            controller.showServerMessage(message);
                            break;
                        case "repeatGuess":
                            controller.showServerMessage(message);
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
	
	public static void send(String msg) throws IOException {
		oos.writeObject(msg);
		oos.flush();
	}
	
	public static void connect() throws IOException {
        Message message = new Message("login#" + username);
        oos.writeObject(message);
    }
		
	
}
package controller;

import model.Server;

/**
 * Program launch point.
 * @author Nolan W, Carson B, Tristen R
 */
public class Main {

	public static void main(String[] args) {
		
		Server server = new Server();
		new Thread(server).start();
	}

}

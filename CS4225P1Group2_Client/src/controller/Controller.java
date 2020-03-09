package controller;

import java.util.ArrayList;


public class Controller {

	
	
	private static Controller instance;


	private Controller() {

	}

	/**
	 * Returns an instance of the data class
	 * 
	 * @precondition none
	 * @postcondition none
	 * @return the instance of the data class
	 */
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}

		return instance;
	}
	
}

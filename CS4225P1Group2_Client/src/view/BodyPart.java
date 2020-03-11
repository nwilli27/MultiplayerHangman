package view;

import java.util.ArrayList;

import javafx.scene.shape.Shape;

/**
 * Class to hold all of the visual hangman objects for the wrong guesses
 * 
 * @author Carson Bedrosian, Tristen Rivera, Nolan Williams
 *
 */
public class BodyPart {

	private ArrayList<Shape> parts;

	/**
	 * Constructor for the body part class. Sets this.parts to an empty list
	 */
	public BodyPart() {
		this.parts = new ArrayList<Shape>();
	}

	/**
	 * gets all of the shapes
	 * 
	 * @return All the shape parts in the class
	 */
	public ArrayList<Shape> getParts() {
		return this.parts;
	}

	/**
	 * Adds a shape to the list of shapes
	 * 
	 * @param part The part to add
	 */
	public void addPart(Shape part) {
		this.parts.add(part);
	}

	/**
	 * Sets all the shape objects in the class to visible
	 */
	public void enableShapes() {
		for (var part : this.parts) {
			part.setVisible(true);
		}
	}

}

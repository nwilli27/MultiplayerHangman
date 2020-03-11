package view;

import java.util.ArrayList;

import javafx.scene.shape.Shape;

public class BodyPart {
	
	private ArrayList<Shape> parts;
	
	public BodyPart() {
		this.parts = new ArrayList<Shape>();
	}

	public ArrayList<Shape> getParts() {
		return parts;
	}

	public void addPart(Shape part) {
		this.parts.add(part);
	}
	
	public void enableShapes() {
		for (var part : this.parts) {
			part.setVisible(true);
		}
	}
	

}

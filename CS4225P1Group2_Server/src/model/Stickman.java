package model;

import java.util.ArrayList;
import java.util.List;
import enums.BodyType;

public class Stickman {

	private List<BodyPart> bodyParts;
	private int bodyPartCounter;
	
	public Stickman() {
		this.initializeBodyParts();
	}
	
	public void enableNextBodyPart() {
		
		this.bodyPartCounter++;
		
		for (int i = 0; i < this.bodyPartCounter; i++) {
			var bodyPart = this.bodyParts.get(i);
			bodyPart.setIsActive(true);
		}
	}
	
	public BodyPart getRecentBodyPart() {
		return this.bodyParts.get(this.bodyPartCounter);
	}
	
	public boolean areAllBodyPartsActive() {
		
		for (var bodyPart : this.bodyParts) {
			if (!bodyPart.isActive()) {
				return false;
			}
		}
		
		return true;
	}
	
	private void initializeBodyParts() {
		
		this.bodyParts = new ArrayList<BodyPart>();
		this.bodyParts.add(new BodyPart(BodyType.Head));
		this.bodyParts.add(new BodyPart(BodyType.Body));
		this.bodyParts.add(new BodyPart(BodyType.LeftArm));
		this.bodyParts.add(new BodyPart(BodyType.RightArm));
		this.bodyParts.add(new BodyPart(BodyType.LeftLeg));
		this.bodyParts.add(new BodyPart(BodyType.RightLeg));
	}

}

package model;

import enums.BodyType;

public class BodyPart {

	private boolean isActive;
	private BodyType bodyType;
	
	public BodyPart(BodyType bodyType) {
		this.bodyType = bodyType;
	}
	
	public void setIsActive(boolean condition) {
		this.isActive = condition;
	}

	public boolean isActive() {
		return this.isActive;
	}

	public BodyType getBodyType() {
		return this.bodyType;
	}
	
	
}

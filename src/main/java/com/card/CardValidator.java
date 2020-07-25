package com.card;

public abstract class CardValidator implements ICardValidator {
	
	protected ICardValidator nextValidator;
	
	public void setNextValidator(ICardValidator nextValidator) {
		this.nextValidator = nextValidator;
	}
	
	public ICardValidator getNextValidator() {
		return nextValidator;
	}
}

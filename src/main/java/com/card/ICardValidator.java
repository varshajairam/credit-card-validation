package com.card;

public interface ICardValidator {
	
	void setNextValidator(ICardValidator nextValidator);
	
	ICardValidator getNextValidator();
	
	String validateRequest(String request);
}
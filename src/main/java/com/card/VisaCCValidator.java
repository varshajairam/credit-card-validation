package com.card;

public class VisaCCValidator extends CardValidator{

	@Override
	public String validateRequest(String request) {
		if((request.length() == 13 || request.length() == 16) && request.charAt(0) == '4') {
			return "Visa";
		}
		
		if(getNextValidator() instanceof ICardValidator) {
			return nextValidator.validateRequest(request);
		} else {
			return "Invalid";
		}
	}
	
}

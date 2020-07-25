package com.card;

public class DiscoverCCValidator extends CardValidator {
	
	@Override
	public String validateRequest(String request) {
		if(request.length() == 16 && request.substring(0, 4).equals("6011")) {
			return "Discover";
		}
		
		if(getNextValidator() instanceof ICardValidator) {
			return nextValidator.validateRequest(request);
		} else {
			return "Invalid";
		}
		
	}

}

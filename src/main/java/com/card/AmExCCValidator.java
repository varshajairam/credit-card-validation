package com.card;

public class AmExCCValidator extends CardValidator {
	
	@Override
	public String validateRequest(String request) {
		if(request.length() == 15 && request.charAt(0) == '3' && (request.charAt(1) == '4' || request.charAt(1) == '7')) {
			return "AmericanExpress";
		}
		
		if(getNextValidator() instanceof ICardValidator) {
			return nextValidator.validateRequest(request);
		} else {
			return "Invalid";
		}
		
	}
	
	
}

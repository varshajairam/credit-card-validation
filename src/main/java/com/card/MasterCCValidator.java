package com.card;

public class MasterCCValidator extends CardValidator {
	
	@Override
	public String validateRequest(String request) {
		try {
			if(request.length() >= 2) {
				int secondDigit = request.charAt(1) - '0';
				if(request.length() == 16 && request.charAt(0) == '5' && secondDigit >= 1 && secondDigit <= 5) {			
					return "MasterCard";
				}
				
				if(getNextValidator() instanceof ICardValidator) {
					return nextValidator.validateRequest(request);
				}
			}
		} catch(Exception e) {
			throw e;
		}
		return "Invalid";
	}

}

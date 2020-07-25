package com.card;

public class CreditCard {
	
	private String CardNumber;
	private String CardType;
	private String Error;
	private String ExpirationDate;
	private String NameOfCardholder;
	
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}	
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getError() {
		return Error;
	}
	public void setError(String error) {
		Error = error;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}
	public String getNameOfCardholder() {
		return NameOfCardholder;
	}
	public void setNameOfCardholder(String nameOfCardholder) {
		NameOfCardholder = nameOfCardholder;
	}
	
}


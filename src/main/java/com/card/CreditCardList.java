package com.card;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class CreditCardList {
	
	@XStreamImplicit(itemFieldName="row")
	private List<CreditCard> creditCard = new ArrayList<CreditCard>();

	public List<CreditCard> getCard() {
		return creditCard;
	}

	public void setCard(List<CreditCard> creditCard) {
		this.creditCard = creditCard;
	}
}
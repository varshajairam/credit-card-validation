package com.card;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

public class XMLFileOperator extends FileOperator {

	XStream xstream;
	
	public XMLFileOperator() {
		xstream = new XStream();
	}
	
	@Override
	List<String> parseFile(File file, String filename) throws Exception {
		List<String> cards = new ArrayList<>();
		
		xstream.addPermission(NoTypePermission.NONE);
		// allow some basics
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.allowTypeHierarchy(Collection.class);
		// allow any type from the same package
		xstream.allowTypesByWildcard(new String[] {
		    CreditCardList.class.getPackage().getName()+".*",
		    CreditCard.class.getPackage().getName()+".*",
		});
		
		xstream.alias("root", CreditCardList.class);
		xstream.alias("row", CreditCard.class);
		xstream.autodetectAnnotations(true);
		try {
			CreditCardList creditCardList = (CreditCardList) xstream.fromXML(file);
			creditCardList.getCard().forEach(row -> {
				if(String.valueOf(row.getCardNumber()).matches("[0-9]+") && String.valueOf(row.getCardNumber()).length() <= 19) {
					cards.add(String.valueOf((row.getCardNumber())));
				} else {
					cards.add("0");
				}
			});
		} catch(NumberFormatException e) {
			throw e;
		}
		
		
		return cards;
	}

	@Override
	void createOutputFile(String filename, List<String[]> lines) throws IOException {
		CreditCardList creditCardList = new CreditCardList();
		List<CreditCard> creditCards = new ArrayList<>();
		lines.forEach(line -> {
			CreditCard card = new CreditCard();
			card.setCardNumber(line[0]);
			card.setCardType(line[1]);
			card.setError(line[2]);
			creditCards.add(card);
		});
		
		creditCardList.setCard(creditCards);
		String xml = xstream.toXML(creditCardList);
		FileWriter fw = new FileWriter(CreditCardConstants.PATH + filename);
	    fw.write(xml);
	    fw.close();
	}
}

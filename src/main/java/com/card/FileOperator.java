package com.card;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;

public abstract class FileOperator {
	private List<String> cards;
	
	final void performFileOperations(String inputFilename, String outputFilename) throws Exception {
		File inputFile = openFile(inputFilename);
		List<String> cards = parseFile(inputFile, inputFilename);
		List<String[]> lines = validateCardNumber(cards);
		createOutputFile(outputFilename, lines);
	}
	
	public File openFile(String filename) {
		File file = new File(CreditCardConstants.PATH + filename);
		return file;
	}
	
	abstract List<String> parseFile(File file, String filename) throws Exception;
	
	public List<String[]> validateCardNumber(List<String> cards) {
		List<String[]> lines = new ArrayList<>();
		ICardValidator cardValidator = Client.getCardValidator();
		
		for(String cardNo: cards) {
			try {
				String cardType = "", error = "";
				String[] line;
				
				if(cardNo.equals("0")) {
					cardType = "Invalid";
					error = "InvalidCardNumber";
				} else {
					cardType = cardValidator.validateRequest(cardNo);
					
					if(cardType != "Invalid") {
						error = "None";
					} else {
						cardNo = "0";
						error = "InvalidCardNumber";
					}
				}
				line = new String[] {cardNo, cardType, error};
				lines.add(line);
//				Long card = Long.parseLong(cardNo);
//				if(card.toString().length() > 19) {
//					cardType = "Invalid";
//					error = "InvalidCardNumber";
//				} else {
//					cardType = cardValidator.validateRequest(cardNo);
//					
//					if(cardType != "Invalid") {
//						error = "None";
//					} else {
//						cardNo = "0";
//						error = "InvalidCardNumber";
//					}
//					line = new String[] {cardNo, cardType, error};
//					lines.add(line);
//				}
			} catch(NumberFormatException e) {
				throw e; 
			}
		}
		return lines;
	}
	
	abstract void createOutputFile(String filename, List<String[]> lines) throws IOException;
	
	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}
	
}

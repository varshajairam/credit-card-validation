package com.card;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVWriter;

public class CSVFileOperator extends FileOperator {
	
	@Override	
	public List<String> parseFile(File file, String filename) throws Exception {		
		try {
			Scanner sc = new Scanner(file);
			if(sc.hasNext()) {
				String[] headers = sc.nextLine().split(",");
				
				if(!(Arrays.asList(headers).contains("CardNumber") && Arrays.asList(headers).contains("ExpirationDate") && Arrays.asList(headers).contains("NameOfCardholder"))) {
					sc.close();
					throw new Exception("Missing headers in input file. CardNumber, ExpirationDate and NameOfCardholder are mandatory.");
				}
				
			}
			setCards(new ArrayList<String>());
			
			while(sc.hasNext()) {
				String row = sc.next();
				String cardNo = row.split(",")[0];
				if(cardNo.matches("[0-9]+") && cardNo.length() <= 19) {
					getCards().add(row.split(",")[0]);
				} else {
					getCards().add("0");
				}
				setCards(getCards());
			}
			
			sc.close();
		} catch(Exception e) {
			throw e;
		}
		
		return getCards();
	}
	
	@Override
	public void createOutputFile(String filename, List<String[]> lines) throws IOException {
		
		File outputFile = new File(CreditCardConstants.PATH + filename);
		FileWriter csvFile;
		try {
			csvFile = new FileWriter(outputFile);
			CSVWriter writer = new CSVWriter(csvFile);
			String[] headers = {"CardNumber", "CardType", "Error"};
			writer.writeNext(headers, false);
			lines.forEach(line -> {
				writer.writeNext(line, false);
			});
			writer.close();
		} catch (IOException e) {
			throw e;
		}	
		
	}
}

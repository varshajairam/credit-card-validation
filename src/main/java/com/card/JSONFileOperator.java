package com.card;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFileOperator extends FileOperator {

	@Override
	List<String> parseFile(File file, String filename) throws Exception {
		JSONParser parser = new JSONParser();
		List<String> cards = new ArrayList<>();
		try (FileReader fileReader = new FileReader(file)) {

			JSONArray jsonArray = (JSONArray) parser.parse(fileReader);
        	
        	for(Object object : jsonArray) {
        		JSONObject jsonObj = (JSONObject) object;
        		CreditCard card = new CreditCard();
        		if(jsonObj.get("CardNumber") != null) {
        			card.setCardNumber(String.valueOf((jsonObj.get("CardNumber"))));
        		} else {
        			throw new Exception("Parsing failure. Input file is missing the CardNumber tag.");
        		}
        		if(jsonObj.get("ExpirationDate") != null) {
    			    card.setExpirationDate((String)jsonObj.get("ExpirationDate"));
    			}
    			if(jsonObj.get("NameOfCardholder") != null) {
    				card.setNameOfCardholder((String) jsonObj.get("NameOfCardholder"));
    			}
    			
    			if(String.valueOf(card.getCardNumber()).matches("[0-9]+") && String.valueOf(card.getCardNumber()).length() <= 19) {
    				cards.add(String.valueOf(card.getCardNumber()));
    			} else {
    				cards.add("0");
    			}
			    
        	}
        }
		return cards;
	}

	@SuppressWarnings("unchecked")
	@Override
	void createOutputFile(String filename, List<String[]> lines) throws IOException {
		JSONArray list = new JSONArray();
		
		lines.forEach(line -> {
			JSONObject obj = new JSONObject();
	        obj.put("CardNumber", Long.valueOf(line[0]));
	        obj.put("CardType", line[1]);
	        obj.put("Error", line[2]);
	        list.add(obj);
		});

        try (FileWriter file = new FileWriter(CreditCardConstants.PATH + filename)) {
        	Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(list);
            file.write(json);
        } catch (IOException e) {
            throw e;
        }
	}

	
}

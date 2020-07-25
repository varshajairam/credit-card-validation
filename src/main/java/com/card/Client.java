package com.card;

import org.apache.commons.io.FilenameUtils;

public class Client {
	
	public static void main(String[] args) {
		try {			
			validateFiles(args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void validateFiles(String[] args) throws Exception {
		String inputFilename = "", outputFilename = "";
		if(args.length == 2) {
			inputFilename = args[0];
			outputFilename = args[1];
			
			if(!(FilenameUtils.getExtension(inputFilename).equals(FilenameUtils.getExtension(outputFilename)))) {
				throw new Exception("Both files formats need to match.");
			} else {
				IFileOperatorFactory factory = new FileOperatorFactory();
				FileOperator operator = factory.createFileOperator(FilenameUtils.getExtension(inputFilename));
				operator.performFileOperations(inputFilename, outputFilename);
			}
		} else {
			throw new Exception("Expecting two file names, found " + args.length + ".");
		}
	}
	
	public static ICardValidator getCardValidator() {
		
		ICardValidator visaCCValidator = new VisaCCValidator();
		ICardValidator masterCCValidator = new MasterCCValidator();
		ICardValidator amexCCValidator = new AmExCCValidator();
		ICardValidator discoverCCValidator = new DiscoverCCValidator();
		
		visaCCValidator.setNextValidator(masterCCValidator);
		masterCCValidator.setNextValidator(amexCCValidator);
		amexCCValidator.setNextValidator(discoverCCValidator);
		
		return visaCCValidator;		
	}
}

package com.card;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClientTest {

	@Test
	void testFileOperatorFactoryValid() {		
		assertThrows(Exception.class, () -> {
			IFileOperatorFactory factory = new FileOperatorFactory();
			factory.createFileOperator("csv");
		});
	}
	
	@Test
	void testFileOperatorFactoryInvalid() {
		assertThrows(Exception.class, () -> {
			IFileOperatorFactory factory = new FileOperatorFactory();
			factory.createFileOperator("py");
		});
	}
	
	@Test
	void testValidateFilesValid() {
		assertThrows(Exception.class, () -> {
			String[] args = {"sample.csv", "output.csv"};
			Client.validateFiles(args);
		});
	}
	
	@Test
	void testValidateFilesInvalid() {
		assertThrows(Exception.class, () -> {
			String[] args = {"sample.csv"};
			Client.validateFiles(args);
		});
	}
	
	@Test
	void testMasterCardValid() {
		ICardValidator masterCCValidator = new MasterCCValidator();
		assertEquals(masterCCValidator.validateRequest("5410000000000000"), "MasterCard");
	}
	
	@Test
	void testVisaValid() {
		ICardValidator visaCCValidator = new VisaCCValidator();
		assertEquals(visaCCValidator.validateRequest("4120000000000"), "Visa");
	}
	
	@Test
	void testAmExValid() {
		ICardValidator amExCCValidator = new AmExCCValidator();
		assertEquals(amExCCValidator.validateRequest("341000000000000"), "AmericanExpress");
	}
	
	@Test
	void testDiscoverValid() {
		ICardValidator discoverCCValidator = new DiscoverCCValidator();
		assertEquals(discoverCCValidator.validateRequest("6011111111111117"), "Discover");
	}
	
	@Test
	void testMasterCardInvalid() {
		ICardValidator masterCCValidator = new MasterCCValidator();	
		assertEquals(masterCCValidator.validateRequest("541000000000"), "Invalid");
	}
	
	@Test
	void testVisaInvalid() {
		ICardValidator visaCCValidator = new VisaCCValidator();
		assertEquals(visaCCValidator.validateRequest("9120000000000"), "Invalid");
	}
	
	@Test
	void testAmExInvalid() {
		ICardValidator amExCCValidator = new AmExCCValidator();
		assertEquals(amExCCValidator.validateRequest("341000000"), "Invalid");
	}
	
	@Test
	void testDiscoverInvalid() {
		ICardValidator discoverCCValidator = new DiscoverCCValidator();
		assertEquals(discoverCCValidator.validateRequest("6010111111111117"), "Invalid");
	}
	
	
}

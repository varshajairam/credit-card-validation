package com.card;

public class FileOperatorFactory implements IFileOperatorFactory {
	
	FileOperator fileOperator;
	
	public FileOperator createFileOperator(String fileExtension) throws Exception {
		if(fileExtension.equals("csv")) {
			fileOperator = new CSVFileOperator();
		}  else if(fileExtension.equals("json")) {
			fileOperator = new JSONFileOperator();
		} else if(fileExtension.equals("xml")) {
			fileOperator = new XMLFileOperator();
		} else {
			throw new Exception("Invalid file format. Accepted formats are XML, CSV and JSON.");
		}
		
		return fileOperator;
	}
	
}

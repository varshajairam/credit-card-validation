package com.card;

public interface IFileOperatorFactory {
	
	FileOperator createFileOperator(String fileExtension) throws Exception;
}

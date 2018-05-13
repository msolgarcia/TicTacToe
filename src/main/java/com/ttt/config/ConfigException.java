package com.ttt.config;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class ConfigException extends Exception {
	
	List<String> errorMessages;
	
	public ConfigException(List<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}
	
	public ConfigException(String errorMessage, Exception e) {
		super(e);
		errorMessages = Arrays.asList(errorMessage); 
	}

	public List<String> getErrors(){
		return errorMessages;
	}
}

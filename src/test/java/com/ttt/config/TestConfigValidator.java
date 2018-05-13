package com.ttt.config;

import java.util.Properties;

import org.junit.Test;


public class TestConfigValidator {


	@Test
	public void validateProperties() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "3");
		prop.setProperty("players.player1", "0");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesNoPlaygroundSize() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("players.player1", "0");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesInvalidTypePlaygroundSize() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "T");
		prop.setProperty("players.player1", "0");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesBiggerPlaygroundSize() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "11");
		prop.setProperty("players.player1", "0");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesSmallerPlaygroundSize() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "2");
		prop.setProperty("players.player1", "0");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesDuplicatedPlayerId() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "3");
		prop.setProperty("players.player1", "Y");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}
	
	@Test(expected = ConfigException.class)
	public void validatePropertiesEmptyPlayerId() throws ConfigException {
		
		Properties prop = new Properties();
		prop.setProperty("playGround.size", "3");
		prop.setProperty("players.player1", " ");
		prop.setProperty("players.player2", "Y");
		prop.setProperty("players.computer", "X");
		
		ConfigValidator.validateProperties(prop);
	}

}

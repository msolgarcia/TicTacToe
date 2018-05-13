package com.ttt.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
	
	public static final String PLAY_GROUND_SIZE = "playGround.size";
	public static final String PLAYERS = "players.";
	public static final String PLAYER_1 = "player1";
	public static final String PLAYER_2 = "player2";
	public static final String COMPUTER = "computer";
	
	private Properties prop;
	
	public Config() throws ConfigException {
		loadProperties();
		ConfigValidator.validateProperties(prop);
	}
	
	public void setProperties(Properties prop) {
		this.prop = prop;
	}

	private void loadProperties() throws ConfigException {
		prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("resources/application.properties");
			prop.load(input);
		} catch (IOException ex) {
			throw new ConfigException("Error trying to upload config", ex);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw new ConfigException("Error trying to upload config", e);
				}
			}
		}
	}

	public String getPlayerOne() {
		return prop.getProperty(PLAYERS + PLAYER_1);
	}
	
	public String getPlayerTwo() {
		return prop.getProperty(PLAYERS + PLAYER_2);
	}
	
	public String getPlayerComputer() {
		return prop.getProperty(PLAYERS + COMPUTER);
	}
	
	public Integer getPlaygroundSize() {
		return Integer.valueOf(prop.getProperty(PLAY_GROUND_SIZE));
	}
}

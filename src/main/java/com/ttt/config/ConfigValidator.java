package com.ttt.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigValidator {
	
	private static final int MAX_SIZE = 10;
	private static final int MIN_SIZE = 3;
	
	public static void validateProperties(Properties prop) throws ConfigException {

		List<String> errors = new ArrayList<String>(); 
		validateSize(prop, errors);
		validatePlayers(prop, errors);
		
		if(!errors.isEmpty()) {
			throw new ConfigException(errors);
		}
		
	}

	private static void validateSize(Properties prop, List<String> errors) {
		
		if(prop.getProperty(Config.PLAY_GROUND_SIZE) == null) {
			errors.add("Playground size is not set.");
		} else {
			try {
				Integer size = Integer.valueOf(prop.getProperty(Config.PLAY_GROUND_SIZE));
				if (size < MIN_SIZE || size > MAX_SIZE) {
					errors.add(String.format("Invilid playground size. Valid size between %d and %d",MIN_SIZE,MAX_SIZE));
				}
			} catch (NumberFormatException e) {
				errors.add("Playground size incorrect type, it must be a integer.");
			}
		}
	}
	
	private static void validatePlayers(Properties prop, List<String> errors) {
		
		validatePlayer(prop, Config.PLAYER_1, errors);
		validatePlayer(prop, Config.PLAYER_2, errors);
		validatePlayer(prop, Config.COMPUTER, errors);
		
		if (prop.getProperty(Config.PLAYERS + Config.PLAYER_1) == prop.getProperty(Config.PLAYERS + Config.PLAYER_2)
				|| prop.getProperty(Config.PLAYERS + Config.PLAYER_1) == prop.getProperty(Config.PLAYERS + Config.COMPUTER)
				|| prop.getProperty(Config.PLAYERS + Config.PLAYER_2) == prop.getProperty(Config.PLAYERS + Config.COMPUTER)) {
			
			errors.add(String.format("Player ids can not be duplicated. Player 1: %s, Player 2: %s, Computer: %s",
					prop.getProperty(Config.PLAYERS + Config.PLAYER_1), prop.getProperty(Config.PLAYERS + Config.PLAYER_2),
					prop.getProperty(Config.PLAYERS + Config.COMPUTER)));
		}
		
	}
	
	private static void validatePlayer(Properties prop, String player, List<String> errors) {
		
		if(prop.getProperty(Config.PLAYERS + player) == null) {
			errors.add(String.format("Player %s character is not set.", player));	
			
		} else if(prop.getProperty(Config.PLAYERS + player).trim().isEmpty() || prop.getProperty(Config.PLAYERS + player).length() > 1){
			errors.add(String.format("Player '%s' character is not valid. Player must be one char size.", player));
		}
	}

}

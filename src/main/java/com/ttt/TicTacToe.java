package com.ttt;

import com.ttt.config.Config;
import com.ttt.config.ConfigException;
import com.ttt.graphicInterface.ConsoleInterface;
import com.ttt.graphicInterface.Interface;
import com.ttt.player.BegginerAIPlayer;
import com.ttt.player.HumanPlayer;
import com.ttt.player.Player;
import com.ttt.playground.Playground;

public class TicTacToe {

	public static void main(String[] args) {
		
		Config config;
		Interface gInterface = new ConsoleInterface();
		
		try {
			config = new Config();
			
			Player playerOne = new HumanPlayer(gInterface, config.getPlayerOne(), "Player One");
			Player playerTwo = new HumanPlayer(gInterface, config.getPlayerTwo(), "Player Two");
			Player computer = new BegginerAIPlayer(config.getPlayerComputer(), "Computer");
			
			Playground playground = new Playground(config.getPlaygroundSize());
			
			
			Game game = new Game(gInterface, playground, playerOne, playerTwo, computer);
			game.play();
			
		} catch (ConfigException e) {
			e.getErrors().stream().forEach(error -> gInterface.displayError(error));
			System.exit(1);
		}
		
		
	}

}

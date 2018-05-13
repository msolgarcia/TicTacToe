package com.ttt;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ttt.graphicInterface.Interface;
import com.ttt.player.Player;
import com.ttt.playground.InvalidPosException;
import com.ttt.playground.Playground;

public class Game {
	
	private Playground playground;
	private Map<String, Player> players;
	private Map<Integer, String> playerIndexes;
	private Interface gInterface;
	private int playerIndex;
	
	public Game(Interface gInterface, Playground playground, Player playerOne, Player playerTwo, Player computer) {

		this.gInterface = gInterface;
		this.playground = playground;
		
		playerIndexes = new HashMap<Integer, String>();

		players = new HashMap<String, Player>();
		players.put(playerOne.getId(), playerOne);
		playerIndexes.put(0, playerOne.getId());
		players.put(playerTwo.getId(), playerTwo);
		playerIndexes.put(1, playerTwo.getId());
		players.put(computer.getId(), computer);
		playerIndexes.put(2, computer.getId());

		Random randomGenerator = new Random();
		playerIndex = randomGenerator.nextInt(players.size());
	}
	
	public void play() {
		
		gInterface.wellcome(players, playground.getSize());
		gInterface.drawPlayground(playground);
		
		while(!playground.isFinished()) {
			Player nextPlayer = getNextPlayer();
			gInterface.announceNextPlayerTurn(nextPlayer);
			
			boolean played = false; 
			while(!played) {
				try {
					nextPlayer.play(playground);					
					played = true;
				} catch (InvalidPosException e) {
					gInterface.displayError(e.getMessage());
				}
			}
			gInterface.drawPlayground(playground);
		}
		if(playground.getWinner() != null) {
			gInterface.announceWinner(players.get(playground.getWinner()));
		}
	}

	private Player getNextPlayer() {
		int currentPlayer = playerIndex;
		playerIndex = (playerIndex + 1) % players.size();
		return players.get(playerIndexes.get(currentPlayer));
	}

}

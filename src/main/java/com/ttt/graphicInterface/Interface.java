package com.ttt.graphicInterface;

import java.util.Map;

import com.ttt.player.Player;
import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

public interface Interface {

	public void drawPlayground(Playground playground); 
	
	public Pos getPosition();
	
	public void displayError(String message);

	public void announceNextPlayerTurn(Player nextPlayer);

	public void announceWinner(Player player);

	public void wellcome(Map<String, Player> players, Integer playgroundSize);
	
}

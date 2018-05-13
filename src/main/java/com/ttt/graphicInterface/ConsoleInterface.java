package com.ttt.graphicInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.ttt.player.Player;
import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

public class ConsoleInterface implements Interface {

	@Override
	public void drawPlayground(Playground playground) {
		
		if(playground.isFinished()) {
			System.out.println();
			System.out.println();
			System.out.println("-----GAME IS OVER-----");
			System.out.println();
		}
		
		int size = playground.getSize();
		System.out.print("- ");
		for(int i = 1 ; i <= size ; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for(int i = 1 ; i <= size ; i++) {
			System.out.print(i + " ");
			for(int j = 1 ; j <= size ; j++) {
				String value = playground.getValue(i, j) != null ? playground.getValue(i, j) : "-";
				System.out.print( value + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	

	@Override
	public Pos getPosition() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Pos pos = null;
		while(pos == null) {
	        try {
	        	System.out.println("Please enter the position(x,y) to play and press enter:");
				String s = br.readLine();
				if(s.trim().isEmpty() || !s.contains(",")) {
					displayError("Invalid position, valid input x,y, x and y numeric.");
					
				} else {
					String[]coords = s.trim().split(",");
					if(coords.length != 2) {
						displayError("Invalid coordinates position, valid input x,y, x and y numeric.");
					} else {
						pos = new Pos(Integer.valueOf(coords[0]), Integer.valueOf(coords[1]));
					}
				}
			} catch (IOException e) {
				displayError("Error trying to get play position");
			} catch (NumberFormatException e) {
				displayError("Invalid coordinate value, valid input x,y, x and y numeric.");
			}
		}
		return pos;
	}

	@Override
	public void displayError(String message) {
		System.out.println((char)27 + "[31m" + "Error:" + message);
		System.out.print((char)27 + "[30m");
		System.out.println();
		
	}

	@Override
	public void announceNextPlayerTurn(Player nextPlayer) {
		System.out.println();
		System.out.println(String.format("%s turn ", nextPlayer.getName()));
		System.out.println();
	}

	@Override
	public void announceWinner(Player player) {
		System.out.println();
		System.out.println("THE WINNER IS:");
		System.out.println(String.format("Player: %s with: %s", player.getName(), player.getId()));
		System.out.println();
		
	}


	@Override
	public void wellcome(Map<String, Player> players, Integer playgroundSize) {
		System.out.println();
		System.out.println(String.format("-----TIC TAC TOE #%d-----", playgroundSize));
		System.out.println();
		for(Player player : players.values()) {
			System.out.println(String.format("%s with character %s", player.getName(), player.getId()));
		}
		System.out.println();
	}
}

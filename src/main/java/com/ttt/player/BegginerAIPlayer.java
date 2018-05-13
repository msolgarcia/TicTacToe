package com.ttt.player;

import java.util.List;
import java.util.Random;

import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

public class BegginerAIPlayer extends AIPlayer{

	public BegginerAIPlayer(String id, String name) {
		super(id, name);
	}

	@Override
	protected Pos getPosToPlay(Playground playground) {
		
		List<Pos> emptyCells = playground.getEmptyCells();
		if(emptyCells != null && !emptyCells.isEmpty()) {
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(emptyCells.size());
			return emptyCells.get(randomInt);
		}
		return null;
	}

}

package com.ttt.player;

import com.ttt.graphicInterface.Interface;
import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

public class HumanPlayer extends Player{

	private Interface gInterface;
	
	public HumanPlayer(Interface gInterface, String id, String name) {
		super(id, name);
		this.gInterface = gInterface;
	}
	
	@Override
	protected Pos getPosToPlay(Playground playGround) {
		return gInterface.getPosition();
	}

}

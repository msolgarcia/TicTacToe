package com.ttt.player;

import com.ttt.playground.InvalidPosException;
import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

public abstract class Player {
	
	private String name;
	private String id;
	
	public Player(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void play(Playground playGround) throws InvalidPosException {
		playGround.play(getPosToPlay(playGround), id);
	}
	
	protected abstract Pos getPosToPlay(Playground playGround);

}

package com.ttt.playground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Playground {
	
	private static final int MIN_POS = 1;
	private String[][] board;
	private Map<Integer, Pos> emptyCells;
	private int size;
	private String winner;
	
	public Playground(int boardSize) {
		this.size = boardSize;
		board = new String[size][size];
		initEmptyCells();
	}
	
	private void initEmptyCells() {
		emptyCells = new HashMap<Integer, Pos>();
		for(int i = 1 ; i <= size ; i++) {
			for(int j = 1 ; j <= size ; j++) {
				emptyCells.put(getKey(i, j), new Pos(i, j));
			}
		}
	}

	public void play(Pos pos, String playerId) throws InvalidPosException{
		
		validatePos(pos);
		board[pos.getX()-1][pos.getY()-1] = playerId;
		emptyCells.remove(getKey(pos));
		if(wins(pos, playerId)) {
			winner = playerId;
		}
	}

	private boolean wins(Pos pos, String id) {

		if(checkY(pos, id)) {
			return true;
		}
		
		if(checkX(pos, id)) {
			return true;
		}
		
		if(pos.getX() == pos.getY() || pos.getX() + pos.getY() == size + 1) {
			return checkDiagonals(id);
		}
		
		return false;
	}

	private boolean checkY(Pos pos, String id) {
		boolean same = true;
		int i = 0;
		while (same && i < size) {
			if(board[pos.getX()-1][i] != id) {
				same = false;
			}
			i++;
		}
		
		if(same) {
			return true;
		}
		return false;
	}
	
	private boolean checkX(Pos pos, String id) {
		boolean same = true;
		int i = 0;
		while (same && i < size) {
			if(board[i][pos.getY()-1] != id) {
				same = false;
			}
			i++;
		}
		
		if(same) {
			return true;
		}
		return false;
	}

	private boolean checkDiagonals(String id) {
		
		boolean same = true;
		int i = 0;
		while (same && i < size) {
			if(board[i][i] != id) {
				same = false;
			}
			i++;
		}
		
		if(same) {
			return true;
		}
		
		same = true;
		i = 0;		
		while (same && i < size) {
			int j = size - 1 - i;
			if(board[i][j] != id) {
				same = false;
			}
			i++;
		}
		
		if(same) {
			return true;
		}
		return false;
	}

	private void validatePos(Pos pos) throws InvalidPosException {
		
		if(pos == null) {
			throw new InvalidPosException("Invalid position. Position can not be an empty position");
		}
		
		if(pos.getX() > size || pos.getX() < MIN_POS
				|| pos.getY() > size || pos.getY() < MIN_POS) {
			throw new InvalidPosException(String.format("Invalid position. Values mus be between %d and %d", MIN_POS, size));
		}
		
		if(!isEmptyCell(pos)) {
			throw new InvalidPosException("Cell is not empty.");
		}
	}
	
	private boolean isEmptyCell(Pos pos) {
		return emptyCells.get(getKey(pos)) != null;
	}

	public List<Pos> getEmptyCells(){
		return new ArrayList<Pos>(emptyCells.values());
	}
	
	public boolean isFinished() {
		return winner != null || emptyCells.isEmpty();
	}
	
	public String getWinner() {
		return winner;
	}
	
	private Integer getKey(Pos pos) {
		return getKey(pos.getX(), pos.getY());
	}
	
	private Integer getKey(int x, int y) {
		return x*100 + y;
	}
	
	public Integer getSize() {
		return size;
	}

	public String getValue(int i, int j) {
		return board[i-1][j-1];
	}

}

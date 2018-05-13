package com.ttt.playground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestPlayground {
	
	@Test
	public void testWinHorizontal() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 9, false);
		playAndValidatePos(playground, new Pos(1,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(1,3), "X", 7, true);
		
		playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(2,1), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(2,3), "X", 7, true);
		
		playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(3,1), "X", 9, false);
		playAndValidatePos(playground, new Pos(3,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,3), "X", 7, true);
		
		playground = new Playground(5);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 25, false);
		playAndValidatePos(playground, new Pos(1,2), "X", 24, false);
		playAndValidatePos(playground, new Pos(1,3), "X", 23, false);
		playAndValidatePos(playground, new Pos(1,4), "X", 22, false);
		playAndValidatePos(playground, new Pos(1,5), "X", 21, true);
		
	}
	
	@Test
	public void testWinVertical() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,1), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,1), "X", 7, true);
		
		playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,2), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,2), "X", 7, true);
		
		playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,3), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,3), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,3), "X", 7, true);
		
		playground = new Playground(5);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 25, false);
		playAndValidatePos(playground, new Pos(2,1), "X", 24, false);
		playAndValidatePos(playground, new Pos(3,1), "X", 23, false);
		playAndValidatePos(playground, new Pos(4,1), "X", 22, false);
		playAndValidatePos(playground, new Pos(5,1), "X", 21, true);
		
	}
	
	@Test
	public void testWinDiagonal() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,3), "X", 7, true);
		
		playground = new Playground(5);
		
		playAndValidatePos(playground, new Pos(1,1), "X", 25, false);
		playAndValidatePos(playground, new Pos(2,2), "X", 24, false);
		playAndValidatePos(playground, new Pos(3,3), "X", 23, false);
		playAndValidatePos(playground, new Pos(4,4), "X", 22, false);
		playAndValidatePos(playground, new Pos(5,5), "X", 21, true);
		
	}
	
	@Test
	public void testWinOpDiagonal() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playAndValidatePos(playground, new Pos(1,3), "X", 9, false);
		playAndValidatePos(playground, new Pos(2,2), "X", 8, false);
		playAndValidatePos(playground, new Pos(3,1), "X", 7, true);
		
		playground = new Playground(5);
		
		playAndValidatePos(playground, new Pos(1,5), "X", 25, false);
		playAndValidatePos(playground, new Pos(2,4), "X", 24, false);
		playAndValidatePos(playground, new Pos(3,3), "X", 23, false);
		playAndValidatePos(playground, new Pos(4,2), "X", 22, false);
		playAndValidatePos(playground, new Pos(5,1), "X", 21, true);
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testOutOfBoundMinX() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(new Pos(0, 2), "X");
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testOutOfBoundMaxX() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(new Pos(4, 2), "X");
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testOutOfBoundMinY() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(new Pos(1, 0), "X");
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testOutOfBoundMaxY() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(new Pos(1, 4), "X");
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testPlayUsedCell() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(new Pos(1, 1), "X");
		playground.play(new Pos(1, 1), "X");
		
	}
	
	@Test(expected = InvalidPosException.class)
	public void testPlayNullPos() throws InvalidPosException {
		
		Playground playground = new Playground(3);
		
		playground.play(null, "X");
		
	}
	
	private void playAndValidatePos(Playground playground, Pos pos, String id, int noEmptyCells, boolean wins) throws InvalidPosException {
		
		List<Pos> emptyCells = playground.getEmptyCells();
		
		assertEquals(noEmptyCells, emptyCells.size());
		assertTrue(emptyCells.contains(pos));

		playground.play(pos, id);
		
		emptyCells = playground.getEmptyCells();
		assertEquals(noEmptyCells - 1, emptyCells.size());
		assertFalse(emptyCells.contains(pos));
		assertEquals(id, playground.getValue(pos.getX(), pos.getY()));
		if(wins) {
			assertNotNull(playground.getWinner());
			assertEquals(id, playground.getWinner());
		} else {
			assertNull(playground.getWinner());
		}
	}

}

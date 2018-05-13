package com.ttt.player;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ttt.playground.Playground;
import com.ttt.playground.Pos;

@RunWith(MockitoJUnitRunner.class)
public class TestBegginerAIPlayer {

	@Mock
	private Playground playground;
	
	private BegginerAIPlayer player;
	
	@Before
	public void CreatePlayer() {
		
		player = new BegginerAIPlayer("X", "Computer");
	}
	
	@Test
	public void testNotEmptyCells() {
		
		when(playground.getEmptyCells()).thenReturn(Collections.emptyList());
		
		Pos pos = player.getPosToPlay(playground);
		
		assertNull(pos);
	}
	
	@Test
	public void testNullEmptyCells() {
		
		when(playground.getEmptyCells()).thenReturn(null);
		
		Pos pos = player.getPosToPlay(playground);
		
		assertNull(pos);
	}
	
	@Test
	public void testSelectOneEmptyCell() {
		
		List<Pos> emptyCells = Arrays.asList(new Pos(1,1), new Pos(1,2));
		
		when(playground.getEmptyCells()).thenReturn(emptyCells);
		
		Pos pos = player.getPosToPlay(playground);
		
		assertNotNull(pos);
		assertTrue(emptyCells.contains(pos));
	}

}

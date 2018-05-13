package com.ttt;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ttt.graphicInterface.Interface;
import com.ttt.player.Player;
import com.ttt.playground.InvalidPosException;
import com.ttt.playground.Playground;

@RunWith(MockitoJUnitRunner.class)
public class TestGame {

	@Mock
	private Playground playground;
	
	@Mock
	private Interface gInterface;
	
	@Mock(name="Player One")
	private Player playerOne;
	
	@Mock(name="Player Two")
	private Player playerTwo;
	
	@Mock(name="Computer")
	private Player computer;
	
	private Game game;
	
	@Before
	public void CreateGame() {
		
		when(playerOne.getId()).thenReturn("X", "X");
		when(playerTwo.getId()).thenReturn("Y", "Y");
		when(computer.getId()).thenReturn("O", "O");
		
		game = new Game(gInterface, playground, playerOne, playerTwo, computer);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void play() throws InvalidPosException {
		
		when(playground.isFinished()).thenReturn(false, false, false, true);
		when(playground.getWinner()).thenReturn("X", "X");
		
		game.play();
		
		verify(gInterface).wellcome((Map<String, Player>) any(Map.class), anyInt());
		verify(gInterface, times(3)).announceNextPlayerTurn((Player) any(Player.class));
		verify(playerOne).play(playground);
		verify(playerTwo).play(playground);
		verify(computer).play(playground);
		verify(gInterface, times(4)).drawPlayground(playground);
		verify(gInterface).announceWinner((Player) any(Player.class));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void playWithInvalidPositionError() throws InvalidPosException {
		
		when(playground.isFinished()).thenReturn(false, false, false, true);
		when(playground.getWinner()).thenReturn("X", "X");
		doThrow(InvalidPosException.class).doNothing().when(playerOne).play(playground);
		
		game.play();
		
		verify(gInterface).wellcome((Map<String, Player>) any(Map.class), anyInt());
		verify(gInterface, times(3)).announceNextPlayerTurn((Player) any(Player.class));
		verify(playerTwo).play(playground);
		verify(computer).play(playground);
		verify(gInterface, times(4)).drawPlayground(playground);
		verify(gInterface).announceWinner((Player) any(Player.class));
		verify(gInterface).displayError(null);
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void playWithoutWinners() throws InvalidPosException {
		
		when(playground.isFinished()).thenReturn(false, false, false, true);
		when(playground.getWinner()).thenReturn(null);
		
		game.play();
		
		verify(gInterface).wellcome((Map<String, Player>) any(Map.class), anyInt());
		verify(gInterface, times(3)).announceNextPlayerTurn((Player) any(Player.class));
		verify(playerOne).play(playground);
		verify(playerTwo).play(playground);
		verify(computer).play(playground);
		verify(gInterface, times(4)).drawPlayground(playground);
		
	}

}

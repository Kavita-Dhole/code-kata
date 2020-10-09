package com.tictactoe.kata;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.tictactoe.kata.game.GameStateEnum;
import com.tictactoe.kata.game.TicTacToeGameProcessor;
import com.tictactoe.kata.game.TicTacToeKataRunner;

public class TicTacToeKataApplicationTests {
	private TicTacToeGameProcessor ticTacToe;
	private TicTacToeKataRunner ticTacToeKataRunner;

	@Before
	public void setUp() {
		ticTacToe = new TicTacToeGameProcessor();
		ticTacToeKataRunner= new TicTacToeKataRunner();
	}

	@Test
	public void testCrossMarking() {
		ticTacToe.markCross(0, 1);

		assertEquals(true, ticTacToe.isCross(0, 1));
	}

	@Test
	public void testMarkCrossOnBlank() {
		boolean crossPlaced = ticTacToe.markCross(1, 2);

		assertEquals(true, crossPlaced);
	}

	@Test
	public void testMarkingCrossOverExistingMarkedCross() {
		ticTacToe.markCross(2, 1);
		boolean crossPlaced = ticTacToe.markCross(2, 1);

		assertEquals(false, crossPlaced);
	}

	@Test
	public void testCircleMarking() {
		ticTacToe.markCircle(0, 1);

		assertEquals(true, ticTacToe.isCircle(0, 1));
	}

	@Test
	public void testMarkCircleOnBlank() {
		boolean circlePlaced = ticTacToe.markCircle(1, 2);

		assertEquals(true, circlePlaced);
	}

	@Test
	public void testMarkingCircleOverExistingMarkedCircle() {
		ticTacToe.markCircle(2, 2);
		boolean circlePlaced = ticTacToe.markCircle(2, 2);

		assertEquals(false, circlePlaced);
	}

	@Test
	public void testMarkingCircleOverExistingMarkedCross() {
		ticTacToe.markCross(2, 2);
		boolean circlePlaced = ticTacToe.markCircle(2, 2);

		assertEquals(false, circlePlaced);
	}

	@Test
	public void testMarkingCrossOverExistingMarkedCircle() {
		ticTacToe.markCircle(2, 1);
		boolean crossPlaced = ticTacToe.markCross(2, 1);

		assertEquals(false, crossPlaced);
	}

	@Test
	public void testThreeCircleMarkedInARowWins() {
		markCircleInSameRow();
		assertWinnerWithCircleState();
	}

	@Test
	public void testThreeCrossMarkedInARowWins() {
		markCrossInSameRow();
		assertWinnerWithCrossState();
	}

	@Test
	public void testThreeCircleMarkedInAColWins() {
		markCircleInSameColumn();
		assertWinnerWithCircleState();
	}

	@Test
	public void testThreeCrossMarkedInAColWins() {
		markCrossInSameColumn();
		assertWinnerWithCrossState();
	}

	@Test
	public void testThreeCircleMarkedDiagonally() {
		markCircleDiagonally();
		assertWinnerWithCircleState();
	}

	@Test
	public void testThreeCrossMarkedDiagonally() {
		markCrossDiagonally();
		assertWinnerWithCrossState();
	}

	@Test
	public void testThreeCircleMarkedReverseDiagonally() {
		markCircleReverseDiagonally();
		assertWinnerWithCircleState();
	}

	@Test
	public void testThreeCrossMarkedReverseDiagonally() {
		markCrossReverseDiagonally();
		assertWinnerWithCrossState();
	}

	private void markCircleInSameRow() {
		ticTacToe.markCircle(0, 0);
		ticTacToe.markCircle(0, 1);
		ticTacToe.markCircle(0, 2);
	}

	private void markCrossInSameRow() {
		ticTacToe.markCross(0, 0);
		ticTacToe.markCross(0, 1);
		ticTacToe.markCross(0, 2);
	}

	private void markCircleInSameColumn() {
		ticTacToe.markCircle(0, 0);
		ticTacToe.markCircle(1, 0);
		ticTacToe.markCircle(2, 0);
	}

	private void markCrossInSameColumn() {
		ticTacToe.markCross(0, 0);
		ticTacToe.markCross(1, 0);
		ticTacToe.markCross(2, 0);
	}

	private void markCircleDiagonally() {
		ticTacToe.markCircle(0, 0);
		ticTacToe.markCircle(1, 1);
		ticTacToe.markCircle(2, 2);
	}

	private void markCrossDiagonally() {
		ticTacToe.markCross(0, 0);
		ticTacToe.markCross(1, 1);
		ticTacToe.markCross(2, 2);
	}

	private void markCircleReverseDiagonally() {
		ticTacToe.markCircle(0, 2);
		ticTacToe.markCircle(1, 1);
		ticTacToe.markCircle(2, 0);
	}

	private void markCrossReverseDiagonally() {
		ticTacToe.markCross(0, 2);
		ticTacToe.markCross(1, 1);
		ticTacToe.markCross(2, 0);
	}

	private void assertWinnerWithCrossState() {
		assertEquals(GameStateEnum.CROSS, ticTacToe.getWinner());
		assertEquals(true, ticTacToe.isFinished());
	}

	private void assertWinnerWithCircleState() {
		assertEquals(GameStateEnum.CIRCLE, ticTacToe.getWinner());
		assertEquals(true, ticTacToe.isFinished());
	}
}
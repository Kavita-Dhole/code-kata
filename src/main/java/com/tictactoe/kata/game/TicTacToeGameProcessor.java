package com.tictactoe.kata.game;

import org.springframework.stereotype.Component;

@Component
public class TicTacToeGameProcessor {
	int lastRow = 2;
	int lastColumn = 2;
	
	private GameStateEnum[][] board = new GameStateEnum[3][3];
	
	private GameStateEnum winner = GameStateEnum.BLANK;

	public TicTacToeGameProcessor() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = GameStateEnum.BLANK;
			}
		}
	}
	
	public boolean markCross(int row, int col) {
		return mark(row, col, GameStateEnum.CROSS);
	}
	
	public boolean markCircle(int row, int col) {
		return mark(row, col, GameStateEnum.CIRCLE);
	}

	private boolean mark(int row, int col, GameStateEnum gameState) {
		if (board[row][col] == GameStateEnum.BLANK) {
			board[row][col] = gameState;		
			
			determineWinner(row, col, gameState);
			return true;
		}
		return false;
	}
	
	public void determineWinner(int row, int col, GameStateEnum gameState) {
		validateThreeSameStateInRow(row, gameState);	
		validateThreeSameStateInColumn(col, gameState);
		validateForThreeSameStateInDiagonal(row, col, gameState);
		validateForThreeSameStateInReverseDiagonal(row, col, gameState);
	}
	
	private void validateThreeSameStateInRow(int row, GameStateEnum gameState) {
		for (int col = 0; col < board.length; col++) {
			if (board[row][col] != gameState) {
				break;
			}

			if (col == lastColumn) {
				winner = gameState;
			}
		}
	}
	
	private void validateThreeSameStateInColumn(int col, GameStateEnum gameState) {
		for (int row = 0; row < board.length; row++) {
			if (board[row][col] != gameState) {
				break;
			}

			if (row == lastRow) {
				winner = gameState;
			}
		}
	}
	
	private void validateForThreeSameStateInDiagonal(int row, int col, GameStateEnum gameState) {
		if (row == col) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][i] != gameState) {
					break;
				}

				if (i == lastRow) {
					winner = gameState;
				}
			}
		}
	}
	
	private void validateForThreeSameStateInReverseDiagonal(int row, int col, GameStateEnum gameState) {
		if (row + col == lastRow) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][2 - i] != gameState) {
					break;
				}

				if (i == lastRow) {
					winner = gameState;
				}
			}
		}
	}

	public GameStateEnum getWinner() {
		return this.winner;
	}

	public GameStateEnum[][] getBoard() {
		return this.board;
	}
	
	public boolean isCross(int row, int col) {
		return board[row][col] == GameStateEnum.CROSS;
	}
	
	public boolean isCircle(int row, int col) {
		return board[row][col] == GameStateEnum.CIRCLE;
	}
	
	public boolean isFinished() {
		return this.winner != GameStateEnum.BLANK;
	}
}

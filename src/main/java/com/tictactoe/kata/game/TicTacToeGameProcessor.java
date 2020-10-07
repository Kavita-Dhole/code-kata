package com.tictactoe.kata.game;

import org.springframework.stereotype.Component;

/**
 * Tic Tac Toe game processing class that calculate outcome of game based on
 * both player choices.
 */
@Component
public class TicTacToeGameProcessor {

	private GameStateEnum[][] board = new GameStateEnum[3][3];
	
	private GameStateEnum winner = GameStateEnum.BLANK;

	// Initialize board array with blank state of game in constructor
	public TicTacToeGameProcessor() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = GameStateEnum.BLANK;
			}
		}
	}
	
	/**
	 * Mark cross in game board and change state of that square to cross and return
	 * boolean if state changes successfully
	 * 
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean markCross(int row, int col) {
		return mark(row, col, GameStateEnum.CROSS);
	}
	
	/**
	 * Mark circle in game board and change state of that square to circle and
	 * return boolean if state changes successfully
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean markCircle(int row, int col) {
		return mark(row, col, GameStateEnum.CIRCLE);
	}

	/**
	 * Mark cross in game board and change state of that square to cross and return
	 * boolean if state changes successfully
	 * 
	 * @param row
	 * @param col
	 * @param gameState
	 * @return boolean
	 */
	private boolean mark(int row, int col, GameStateEnum gameState) {
		if (board[row][col] == GameStateEnum.BLANK) {
			board[row][col] = gameState;		
			
			// Checks whether winner has been found post every players chance of marking
			setWinner(row, col, gameState);
			return true;
		}
		return false;
	}
	
	/**
	 * This method finds winner after each player plays and marks O or X based on
	 * condition of winner based on Tic Tac Toe game rules
	 * 
	 * @param row
	 * @param col
	 * @param gameState
	 */
	public void setWinner(int row, int col, GameStateEnum gameState) {
		validateThreeSameStateInRow(row, gameState);	
		validateThreeSameStateInColumn(col, gameState);
		validateForThreeSameStateInDiagonal(row, col, gameState);
		validateForThreeSameStateInReverseDiagonal(row, col, gameState);
	}
	
	/**
	 * This method finds that if one row has same sign and changes winner state to
	 * that sign like O or X
	 * 
	 * @param row
	 * @param gameState
	 */
	private void validateThreeSameStateInRow(int row, GameStateEnum gameState) {
		for (int col = 0; col < board.length; col++) {
			if (board[row][col] != gameState) {
				break;
			}

			if (col == 2) {
				winner = gameState;
			}
		}
	}
	
	/**
	 * This method finds that if one column has same sign and changes winner state
	 * to that sign like O or X
	 * 
	 * @param col
	 * @param gameState
	 */
	private void validateThreeSameStateInColumn(int col, GameStateEnum gameState) {
		for (int row = 0; row < board.length; row++) {
			if (board[row][col] != gameState) {
				break;
			}

			if (row == 2) {
				winner = gameState;
			}
		}
	}
	
	/**
	 * This method finds that if diagonally (0, 0), (1, 1), (2, 2) state on board
	 * has same sign and changes winner state to that sign like O or X
	 * 
	 * @param row
	 * @param col
	 * @param gameState
	 */
	private void validateForThreeSameStateInDiagonal(int row, int col, GameStateEnum gameState) {
		if (row == col) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][i] != gameState) {
					break;
				}

				if (i == 2) {
					winner = gameState;
				}
			}
		}
	}
	
	/**
	 * This method finds that if diagonally in reverse order (2, 0), (1, 1), (0, 2)
	 * state on board has same sign and changes winner state to that sign like O or X
	 * 
	 * @param row
	 * @param col
	 * @param gameState
	 */
	private void validateForThreeSameStateInReverseDiagonal(int row, int col, GameStateEnum gameState) {
		if (row + col == 2) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][2 - i] != gameState) {
					break;
				}

				if (i == 2) {
					winner = gameState;
				}
			}
		}
	}

	/**
	 * This method returns state of winner of any time
	 * 
	 * @return GameStateEnum the winner
	 */
	public GameStateEnum getWinner() {
		return this.winner;
	}

		
	/**
	 * This method gives current state of board at any time
	 * 
	 * @return GameStateEnum[][]
	 */
	public GameStateEnum[][] getBoard() {
		return this.board;
	}

	/**
	 * This method finds whether at any location on board is marked with cross or
	 * not
	 * 
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean isCross(int row, int col) {
		return board[row][col] == GameStateEnum.CROSS;
	}
	
	/**
	 * This method finds whether at any location on board is marked with circle or
	 * not
	 * 
	 * @param row
	 * @param col
	 * @return boolean
	 */
	public boolean isCircle(int row, int col) {
		return board[row][col] == GameStateEnum.CIRCLE;
	}
	
	/**
	 * This method used to check whether winner has been found after every chance
	 * played by player. If state of winner is changed from Blank to Cicrle and
	 * Cross we can say that one of player has won the game.
	 * 
	 * @return boolean
	 */
	public boolean isFinished() {
		return this.winner != GameStateEnum.BLANK;
	}
}

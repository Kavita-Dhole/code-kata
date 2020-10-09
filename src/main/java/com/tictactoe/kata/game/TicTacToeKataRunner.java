package com.tictactoe.kata.game;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tictactoe.kata.game.player.Player;

@Component
public class TicTacToeKataRunner implements CommandLineRunner {

	@Autowired
	@Qualifier("Player1")
	private Player player1;

	@Autowired
	@Qualifier("Player2")
	private Player player2;

	@Autowired
	private TicTacToeGameProcessor ticTacToeGame;
	
	int count;

	@Override
	public void run(String... args) throws IOException {
		
		String newline = System.getProperty("line.separator");
		
		printGameRules();
		
		try (Scanner inputReader = new Scanner(System.in)) {		
			printGameStartState(newline, inputReader);
			
			boolean isFirst = false;
			System.out.println(newline);
			
			isFirst = getFirstPlayer(newline, isFirst);
			
			if (isFirst) {
				do {
					if(!playerMove(player1, inputReader, 1)) {
						break;
					}
					
					if(!playerMove(player2, inputReader, 2)) {
						break;
					}
				} while (true);					
			} else {
				do {
					if(!playerMove(player2, inputReader, 1)) {
						break;
					}
					
					if(!playerMove(player1, inputReader, 2)) {
						break;
					}			
				} while (true);	
			}	
			
			printFinalOutcome(newline);			
		} catch (Exception ex) {
			System.out.format("Fatal Exception Occurred, Please restart game Error - %s", ex.getMessage());
		}
	}

	private void printFinalOutcome(String newline) {
		System.out.println("\nFinal Game Outcome Reached ");
		printGameBoardState();
		System.out.println(newline);	
		
		GameStateEnum winner = ticTacToeGame.getWinner();
		if (ticTacToeGame.isFinished() && winner != GameStateEnum.BLANK) {	
			if (winner == player1.getShape()) {
				System.out.format("TIC TAC TOE GAME WINNER IS %s", player1.getPlayerName());
			}
			
			if (winner == player2.getShape()) {
				System.out.format("TIC TAC TOE GAME WINNER IS %s", player2.getPlayerName());
			}
		} else {
			System.out.println("TIC TAC TOE GAME IS DRAWN");
		}
		System.out.println(newline);
	}

	private void printGameRules() {
		StringBuilder gameRules = new StringBuilder();
		gameRules.append("RULES OF TIC TAC TOE GAME \n");
		gameRules.append(" 1) X always goes first. \n");
		gameRules.append(" 2) Players cannot play on a played position. \n");
		gameRules.append(" 3) Players alternate placing X's and O's on the board until either. \n");
		gameRules.append(" 4) One player has three in a row, horizontally, vertically or diagonally. \n");
		gameRules.append(" 5) All nine squares are filled. \n");
		gameRules.append(" 6) If a player is able to draw three X's or three O's in a row, that player wins. \n");
		gameRules.append(" 7) If all nine squares are filled and neither player has three in a row, the game is a draw. \n");
		gameRules.append(" 8) Please enter row and column position values in integer values less than 2. \n");
		
		System.out.println(gameRules.toString());
	}

	private boolean getFirstPlayer(String newline, boolean isFirst) {
		if (player1.getShape() == GameStateEnum.CROSS) {
			System.out.println(player1.getPlayerName() + " will go first due to shape X assignment in toss");
			isFirst = true;
		} else {
			System.out.println(player2.getPlayerName() + " will go first due to shape X assignment in toss");			
		}
		System.out.println(newline);
		return isFirst;
	}

	private boolean playerMove(Player player, Scanner inputReader, int playerMove) {
		String newline = System.getProperty("line.separator");
		boolean isMarked;
		int row;
		int col;
		do {
			System.out.println(player.getPlayerName() + " : Please enter Row position");
			row = inputReader.nextInt();
			System.out.println(player.getPlayerName() + " : Please enter Column position");
			col = inputReader.nextInt();
			
			if (row <=2 && col <= 2) {
				isMarked = drawMark(row, col, playerMove);
				count++;
				
				if (!isMarked) {
					System.out.println("\n Row & Column position is already occupied, Please re-enter.. \n");
				} else {
					break;
				}				
			} else {
				System.out.println("\n Incorrect Row & Column values provided, Please re-enter \n");
			}												
		} while (true);
		
		if (ticTacToeGame.isFinished() || count == 9) {						
			return false;
		}
		
		System.out.println("\n Current Status - ");
		printGameBoardState();
		System.out.println(newline);
		return true;
	}

	private boolean drawMark(int row, int col, int player) {
		boolean isMarked;
		isMarked= (player == 1) ? ticTacToeGame.markCross(row, col) : ticTacToeGame.markCircle(row, col);
		return isMarked;
	}
	
	private void printGameStartState(String newline, Scanner inputReader) {
		do {				
			System.out.println("Please enter first player name : ");
			String playerName1 = inputReader.nextLine();
			player1.setPlayerName(playerName1);
			
			System.out.println(newline);
			
			System.out.println("Please enter second player name : ");
			String playerName2 = inputReader.nextLine();
			player2.setPlayerName(playerName2);					
			
			System.out.println("Assigning shapes O/X to players based on toss !!");
			if (Math.random() < 0.5) {
				System.out.println("O assigned to player 1 & X assigned to player 2");
				player1.setShape(GameStateEnum.CIRCLE);
				player2.setShape(GameStateEnum.CROSS);
			} else {
				System.out.println("X assigned to player 1 & O assigned to player 2");
				player1.setShape(GameStateEnum.CROSS);
				player2.setShape(GameStateEnum.CIRCLE);
			}
			
			if (playerName1.trim().equalsIgnoreCase(playerName2.trim())) {
				System.out.println("Both player names are same, please enter two different players!!!");
			} else {								
				break;
			}	
		} while (true);		
		
		System.out.println("\nTIC TAC TOE GAME STARTED \nInitial State of The Game \n");	
		printGameBoardState();
	}		
	
	private void printGameBoardState() {
		GameStateEnum[][] board = ticTacToeGame.getBoard();
		
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				if (GameStateEnum.CIRCLE == board[row][col]) {
					System.out.print(" O ");
				} else if (GameStateEnum.CROSS == board[row][col]) {
					System.out.print(" X ");
				} else {
					System.out.print(" - ");
				}				
			}
			System.out.println("\n \n");
		}
	}
}

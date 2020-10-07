package com.tictactoe.kata.game;

import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tictactoe.kata.game.player.Player;

import static com.tictactoe.kata.game.util.Constants.ENTER_ROW;
import static com.tictactoe.kata.game.util.Constants.ENTER_COLUMN;
import static com.tictactoe.kata.game.util.Constants.POSITION_OCCUPIED;
import static com.tictactoe.kata.game.util.Constants.INCORRECT_ROW_COL_VALUE;

/**
 * Class that allows user to input from command line for both player and outcome
 * of game displayed on command line
 */
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

	@Override
	public void run(String... args) throws IOException {
		
		String newline = System.getProperty("line.separator");
		
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
		
		try (Scanner inputReader = new Scanner(System.in)) {		
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
			
			boolean isFirst = false;
			boolean isMarked = false;
			int row = 0;
			int col = 0;
			int count = 0;
			System.out.println(newline);
			
			if (player1.getShape() == GameStateEnum.CROSS) {
				System.out.println(player1.getPlayerName() + " will go first due to shape X assignment in toss");
				isFirst = true;
			} else {
				System.out.println(player2.getPlayerName() + " will go first due to shape X assignment in toss");			
			}
			System.out.println(newline);
			
			if (isFirst) {
				do {
					do {
						System.out.println(player1.getPlayerName() + ENTER_ROW);
						row = inputReader.nextInt();
						System.out.println(player1.getPlayerName() + ENTER_COLUMN);
						col = inputReader.nextInt();
					
						if (row <=2 && col <= 2) {
							isMarked = ticTacToeGame.markCross(row, col);
							count++;
							
							if (!isMarked) {
								System.out.println(POSITION_OCCUPIED);
							} else {
								break;
							}
						} else {
							System.out.println(INCORRECT_ROW_COL_VALUE);
						}
						
					} while (true);						
					
					if (ticTacToeGame.isFinished() || count == 8) {						
						break;
					}
						
					System.out.println(newline);					
					do {
						System.out.println(player2.getPlayerName() + ENTER_ROW);
						row = inputReader.nextInt();
						System.out.println(player2.getPlayerName() + ENTER_COLUMN);
						col = inputReader.nextInt();
						
						if (row <=2 && col <= 2) {
							isMarked= ticTacToeGame.markCircle(row, col);
							count++;
							
							if (!isMarked) {
								System.out.println(POSITION_OCCUPIED);
							} else {
								break;
							}				
						} else {
							System.out.println(INCORRECT_ROW_COL_VALUE);
						}												
					} while (true);					
					
					if (ticTacToeGame.isFinished() || count == 8) {						
						break;
					}	
					
					System.out.println("\n Current Status - ");
					printGameBoardState();
					System.out.println(newline);
					
				} while (true);					
			} else {
				do {
					do {
						System.out.println(player2.getPlayerName() + ENTER_ROW);
						row = inputReader.nextInt();
						System.out.println(player2.getPlayerName() + ENTER_COLUMN);
						col = inputReader.nextInt();
						
						if (row <=2 && col <= 2) {
							isMarked= ticTacToeGame.markCross(row, col);
							count++;
							
							if (!isMarked) {
								System.out.println(POSITION_OCCUPIED);
							} else {
								break;
							}		
						} else {
							System.out.println(INCORRECT_ROW_COL_VALUE);
						}
									
					} while (true);						
					
					if (ticTacToeGame.isFinished() || count == 8) {						
						break;
					}
						
					System.out.println(newline);
					
					do {
						System.out.println(player1.getPlayerName() + ENTER_ROW);
						row = inputReader.nextInt();
						System.out.println(player1.getPlayerName() + ENTER_COLUMN);
						col = inputReader.nextInt();
						isMarked= ticTacToeGame.markCircle(row, col);
						count++;
						
						if (row <= 2 && col <= 2) {
							if (!isMarked) {
								System.out.println(POSITION_OCCUPIED);
							} else {
								break;
							}			
						} else {
							System.out.println(INCORRECT_ROW_COL_VALUE);
						}													
					} while (true);					
					
					if (ticTacToeGame.isFinished() || count == 8) {						
						break;
					}	
					
					System.out.println("\nCurrent Status - ");
					printGameBoardState();
					System.out.println(newline);					
				} while (true);	
			}	
			
			System.out.println("\nFinal Game Outcome Reached :: ");
			printGameBoardState();
			System.out.println(newline);	
			if (ticTacToeGame.isFinished()) {
				GameStateEnum winner = ticTacToeGame.getWinner();
				if (winner == player1.getShape()) {
					System.out.format("TIC TAC TOE GAME WINNER IS %s", player1.getPlayerName());
				}
				
				if (winner == player2.getShape()) {
					System.out.format("TIC TAC TOE GAME WINNER IS %s", player2.getPlayerName());
				}
				
				if (winner == GameStateEnum.BLANK) {
					System.out.println("TIC TAC TOE GAME IS DRAWN");
				}
			}
			System.out.println(newline);			
		} catch (Exception ex) {
			System.out.format("Fatal Exception Occurred, Please restart game Error - %s", ex.getMessage());
		}
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

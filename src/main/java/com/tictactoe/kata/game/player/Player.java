package com.tictactoe.kata.game.player;

import org.springframework.stereotype.Component;

import com.tictactoe.kata.game.GameStateEnum;

/**
 * 
 * This player class which keeps track of player name and score
 *
 */
@Component
public class Player {
	
	private String playerName;
	
	private GameStateEnum shape;

	/**
	 * Getter method to get player name
	 * 
	 * @return String
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Setter method to set player name
	 * 
	 * @param playerName
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}	
	
	/**
	 * Getter method to get player shape choice
	 * 
	 * @return 
	 */
	public GameStateEnum getShape() {
		return shape;
	}
	
	/**
	 * Setter method to set player score
	 * 
	 * @param shape
	 */
	public void setShape(GameStateEnum shape) {
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", shape=" + shape + "]";
	}
}

package com.tictactoe.kata.game.player;

import org.springframework.stereotype.Component;

import com.tictactoe.kata.game.GameStateEnum;

@Component
public class Player {
	
	private String playerName;
	
	private GameStateEnum shape;

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}	
	
	public GameStateEnum getShape() {
		return shape;
	}
	
	public void setShape(GameStateEnum shape) {
		this.shape = shape;
	}

	@Override
	public String toString() {
		return "Player [playerName=" + playerName + ", shape=" + shape + "]";
	}
}

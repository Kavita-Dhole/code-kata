package com.tictactoe.kata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tictactoe.kata.game.player.Player;

@Configuration
public class TicTacToeKataConfig {
	
	@Bean("Player1")
	public Player playerOneConfig() {
		return new Player();
	}
	
	@Bean("Player2")
	public Player playerSecondConfig() {
		return new Player();
	}
}

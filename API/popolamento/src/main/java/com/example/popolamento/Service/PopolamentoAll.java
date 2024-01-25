package com.example.popolamento.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class PopolamentoAll {
	private final ScoresService scoresService ;
	private final PopolamentoService popolamentoService;
	private final GamesService gameService;
	private final TeamService teamService;
	private final TeamStatService teamStatService;
	private final PlayerStatService playerStatService;
	private final PlayerService playerService;

	public void team(){
		teamService.popolamento();
		System.out.println("popolamento team");
	}
	public void scores() throws IOException, InterruptedException {
		scoresService.popolamento();
		System.out.println("popolamento scores");
	}
	public void classifica() {
		popolamentoService.popolamento();
	}

	public void games() {
		gameService.popolamento();
		System.out.println("popolamento games");
	}
	public void playerStat() throws IOException, InterruptedException {
		playerStatService.popolamento();
		System.out.println("popolamento playerStat");
	}
	public void teamStat() throws IOException, InterruptedException {
		teamStatService.popolamento();
		System.out.println("popolamento teamStat");
	}

	public void player() throws IOException, InterruptedException {
		playerService.popolamento();
		System.out.println("popolamento player");
	}


}

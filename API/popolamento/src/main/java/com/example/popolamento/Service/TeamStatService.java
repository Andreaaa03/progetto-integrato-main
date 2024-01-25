package com.example.popolamento.Service;

import com.example.popolamento.Entity.Season;
import com.example.popolamento.Entity.TeamsStatistics;
import com.example.popolamento.Repository.TeamRepository;
import com.example.popolamento.Repository.TeamStatRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class TeamStatService {
	private final TeamRepository teamRepository;
	private final PlayerStatService playerStatService;

	private final TeamStatRepository teamStatRepository;

	public TeamStatService(TeamRepository teamRepository, PlayerStatService playerStatService, TeamStatRepository teamStatRepository) {
		this.teamRepository = teamRepository;
		this.playerStatService = playerStatService;
		this.teamStatRepository = teamStatRepository;
	}


	public void popolamento(){
		List<Integer> idTeam = new ArrayList<Integer> ();

		teamRepository.findAll().forEach(team -> {
			idTeam.add(team.getId());
		});

	for (Integer integer : idTeam) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(playerStatService.url("https://v2.nba.api-sports.io/teams/statistics?id="+integer+"&season=2023")).path("response");

			if (jsonNode.isArray()) {
				// Itera sugli oggetti nel file JSON
				for (JsonNode Node : jsonNode) {
					TeamsStatistics teamsStatistics = new TeamsStatistics();

					teamsStatistics.setTeam(teamRepository.findById(integer).get());
					Season season = new Season();
					season.setYear(Node.path("season").asInt());
//					TODO: da gestire meglio implementando un metodo per avere piu stagioni
					season.setId(1);
					teamsStatistics.setSeason(season);
					teamsStatistics.setGames(Node.path("games").asInt());

				teamsStatistics.setFastBreakPoints(Node.path("fastBreakPoints").asInt());
				teamsStatistics.setPointsInPaint(Node.path("pointsInPaint").asInt());
				teamsStatistics.setBiggestLead(Node.path("biggestLead").asInt());
				teamsStatistics.setSecondChancePoints(Node.path("secondChancePoints").asInt());
				teamsStatistics.setPointsOffTurnover(Node.path("pointsOffTurnovers").asInt());
				teamsStatistics.setGames(Node.path("games").asInt());
				teamsStatistics.setPoints(Node.path("points").asInt());
				teamsStatistics.setFgm(Node.path("fgm").asInt());
				teamsStatistics.setFga(Node.path("fga").asInt());
				teamsStatistics.setFgp(Double.parseDouble(Node.path("fgp").asText()));
				teamsStatistics.setFtm(Node.path("ftm").asInt());
				teamsStatistics.setFta(Node.path("fta").asInt());
				teamsStatistics.setFtp(Double.parseDouble(Node.path("ftp").asText()));
				teamsStatistics.setTpm(Node.path("tpm").asInt());
				teamsStatistics.setTpa(Node.path("tpa").asInt());
				teamsStatistics.setTpp(Double.parseDouble(Node.path("tpp").asText()));
				teamsStatistics.setOffReb(Node.path("offReb").asInt());
				teamsStatistics.setDefReb(Node.path("defReb").asInt());
				teamsStatistics.setTotReb(Node.path("totReb").asInt());
				teamsStatistics.setAssists(Node.path("assists").asInt());
				teamsStatistics.setPFouls(Node.path("pFouls").asInt());
				teamsStatistics.setSteals(Node.path("steals").asInt());
				teamsStatistics.setTurnovers(Node.path("turnovers").asInt());
				teamsStatistics.setBlocks(Node.path("blocks").asInt());
				teamsStatistics.setPlusMinus(Node.path("plusMinus").asInt());

				 teamStatRepository.save(teamsStatistics);

				}
			}
			Thread.sleep(10000);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}

package com.example.popolamento.Service;

import com.example.popolamento.Entity.Games;
import com.example.popolamento.Entity.Scores;
import com.example.popolamento.Repository.GameRepository;
import com.example.popolamento.Repository.ScoreRepository;
import com.example.popolamento.Repository.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class ScoresService {


	private final PlayerStatService playerStatService;
	private final GameRepository gamesRepository;
	private final ScoreRepository scoreRepository;
	private final TeamRepository teamsRepository;

	public ScoresService(PlayerStatService playerStatService, GameRepository gamesRepository, ScoreRepository scoreRepository, TeamRepository teamsRepository) {
		this.playerStatService = playerStatService;
		this.gamesRepository = gamesRepository;
		this.scoreRepository = scoreRepository;
		this.teamsRepository = teamsRepository;
	}

	private JsonNode ricorsivo(int anno) throws IOException, InterruptedException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(playerStatService.url("https://v2.nba.api-sports.io/games?season=" + anno)).path("response");
		if (jsonNode.get(0) == null){
			return ricorsivo(anno-1);
		}
		if (jsonNode.get(0).path("season").asInt() == anno){
			return jsonNode;
		}
		else {
			return ricorsivo(anno-1);
		}

	}
	@Transactional
public void popolamento() throws IOException, InterruptedException {
	JsonNode jsonNode = ricorsivo(LocalDate.now().getYear());
	System.out.println("popolamento scores"+ jsonNode.isArray());

	if (jsonNode.isArray()) {
		for (JsonNode Node : jsonNode) {
			Scores scores = new Scores();

			Optional<Games> game = Optional.ofNullable(gamesRepository.findByGameId(Node.path("id").asInt()))
					.map(games -> {
						scores.setGame(games);
						System.out.println("Game found");
						teamsRepository.findById(Node.get("teams").path("home").get("id").asInt()).ifPresentOrElse(
								teams -> {
									System.out.println("home Team found");
									scores.setTeam(teams);
									scores.setPoints(Node.get("scores").path("home").get("points").asInt());
									scores.setWin(Node.get("scores").path("home").get("win").asInt());
									scores.setLoss(Node.get("scores").path("home").get("loss").asInt());
									scores.setSeriesWin(Node.get("scores").path("home").path("series").get("win").asInt());
									scores.setSeriesLoss(Node.get("scores").path("home").path("series").get("loss").asInt());
									System.out.println("home Score set");
									scoreRepository.insertScore(scores.getGame().getGameId(),
											scores.getTeam().getId(),
											scores.getWin(),
											scores.getLoss(),
											scores.getSeriesWin(),
											scores.getSeriesLoss(),
											scores.getPoints());
									System.out.println("home Score saved");
								},
								() -> {
									System.out.println("Team not found");
								}
						);
						scores.setGame(games);
						teamsRepository.findById(Node.get("teams").path("visitors").get("id").asInt()).ifPresentOrElse(
								teams -> {
									System.out.println("away Team found");
									scores.setTeam(teams);
									scores.setPoints(Node.get("scores").path("visitors").get("points").asInt());
									scores.setWin(Node.get("scores").path("visitors").get("win").asInt());
									scores.setLoss(Node.get("scores").path("visitors").get("loss").asInt());
									scores.setSeriesWin(Node.get("scores").path("visitors").path("series").get("win").asInt());
									scores.setSeriesLoss(Node.get("scores").path("visitors").path("series").get("loss").asInt());
									scoreRepository.insertScore(scores.getGame().getGameId(),
											scores.getTeam().getId(),
											scores.getWin(),
											scores.getLoss(),
											scores.getSeriesWin(),
											scores.getSeriesLoss(),
											scores.getPoints());

								},
								() -> {
									System.out.println("away Team not found");
								}
						);
						return Optional.of(games);
					}).orElseGet(() -> {
						System.out.println("Game not found");
						return Optional.empty();
					});
			}
		}
	}
}



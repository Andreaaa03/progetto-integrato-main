package com.example.popolamento.Service;

import com.example.popolamento.Entity.*;
import com.example.popolamento.Repository.GameRepository;
import com.example.popolamento.Repository.PlayerRepository;
import com.example.popolamento.Repository.PlayerStatRepository;
import com.example.popolamento.Repository.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerStatService {
	private final PlayerStatRepository PlayerStatRepository;
	private final PlayerRepository playerRepository;
	private final TeamRepository teamRepository;
	private final GameRepository gameRepository;
	private final HttpClient httpClient = HttpClient.newHttpClient();

	public PlayerStatService(com.example.popolamento.Repository.PlayerStatRepository playerStatRepository, PlayerRepository playerRepository, TeamRepository teamRepository, GameRepository gameRepository) {
		PlayerStatRepository = playerStatRepository;
		this.playerRepository = playerRepository;
		this.teamRepository = teamRepository;
		this.gameRepository = gameRepository;
	}

	public String url(String url) throws IOException, InterruptedException {
		URI uri = URI.create(url);

		HttpRequest request = HttpRequest.newBuilder(uri)
				.header("x-rapidapi-host", "v2.nba.api-sports.io")
				.header("x-rapidapi-key", "92aff9514509ecd24613bdc77c14b666")
				.GET()
				.build();

		// Send the HTTP request and get the response
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.body());
		return response.body();

	}

	public void popolamento(){
		List<Integer> idTeam = new ArrayList<>();
		idTeam.addAll(teamRepository.findAll().stream().map(Teams::getId).toList());

		for (Integer integer : idTeam) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(url("https://v2.nba.api-sports.io/players/statistics?season=2023&team="+integer));
			Thread.sleep(3000);
			if (!jsonNode.path("errors").isEmpty()){
				System.out.println("stop per 10 minuti");
				Thread.sleep(600000);
				jsonNode = objectMapper.readTree(url("https://v2.nba.api-sports.io/players/statistics?season=2023&team="+integer)).path("response");
			}else {
				jsonNode = jsonNode.path("response");
			}

//			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/statPlayXteam4.json"));
			// Assicurati che il nodo principale sia un array
			if (jsonNode.isArray()) {
				// Itera sugli oggetti nel file JSON
				for (JsonNode Node : jsonNode) {

					PlayerStatistics playerStatistics = new PlayerStatistics();
					Player player = new Player();
					player.setId(Node.path("player").path("id").asInt());

//					TODO: da gestire meglio implementando un metodo che controlla se il player è già presente nel db altrimenti lo aggiunge
					Optional<Player> player1 = playerRepository.findById(player.getId());
					if (player1.isPresent()) {

						playerStatistics.setPlayer(player);
					}
					else {
						continue;
					}



					Optional<Teams> teams = teamRepository.findById(Node.path("team").path("id").asInt());
					System.out.println("va");
					System.out.println(teams.isPresent());
					System.out.println(teams.get().getTeamName());
					if (teams.isPresent()) {
						playerStatistics.setTeam(teams.get());
					}
					else {
						continue;
					}
					System.out.println("va");
					System.out.println("va");

					Optional<Games> games = gameRepository.findById(Node.path("game").path("id").asInt());
					if (games.isPresent()) {
						playerStatistics.setGame(games.get());
					}
					else {
						continue;
					}
					System.out.println("va");

					playerStatistics.setPoints(Node.path("points").asInt());
					playerStatistics.setPos(Node.path("pos").asText());
					if(Node.path("min").asText().equals("-") || Node.path("min").asText().equals("--")){
						playerStatistics.setMin("00:00");
					}else {
						playerStatistics.setMin(Node.path("min").asText());
					}

					playerStatistics.setFgm(Node.path("fgm").asInt());
					playerStatistics.setFga(Node.path("fga").asInt());
					playerStatistics.setFgp(Node.path("fgp").asDouble());
					playerStatistics.setFtm(Node.path("ftm").asInt());
					playerStatistics.setFta(Node.path("fta").asInt());
					playerStatistics.setFtp(Node.path("ftp").asDouble());
					playerStatistics.setTpm(Node.path("tpm").asInt());
					playerStatistics.setTpa(Node.path("tpa").asInt());
					playerStatistics.setTpp(Node.path("tpp").asDouble());
					playerStatistics.setOffReb(Node.path("offReb").asInt());
					playerStatistics.setDefReb(Node.path("defReb").asInt());
					playerStatistics.setTotReb(Node.path("totReb").asInt());
					playerStatistics.setAssists(Node.path("assists").asInt());
					playerStatistics.setPFouls(Node.path("pFouls").asInt());
					playerStatistics.setSteals(Node.path("steals").asInt());
					playerStatistics.setTurnovers(Node.path("turnovers").asInt());
					playerStatistics.setBlocks(Node.path("blocks").asInt());
					playerStatistics.setPlusMinus(Node.path("plusMinus").asInt());

					// Salva l'oggetto nel database
					PlayerStatRepository.save(playerStatistics);


				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}

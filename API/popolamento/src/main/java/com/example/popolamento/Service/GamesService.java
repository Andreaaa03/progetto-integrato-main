package com.example.popolamento.Service;

import com.example.popolamento.Entity.Games;
import com.example.popolamento.Entity.Season;
import com.example.popolamento.Entity.Teams;
import com.example.popolamento.Repository.GameRepository;
import com.example.popolamento.Repository.SeasonRepository;
import com.example.popolamento.Repository.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Service
public class GamesService {
	private final GameRepository gamesRepository;
	private final TeamRepository teamRepository;
	private final SeasonRepository seasonRepository;

	public GamesService(GameRepository gamesRepository, TeamRepository teamRepository, SeasonRepository seasonRepository) {
		this.gamesRepository = gamesRepository;
		this.teamRepository = teamRepository;
		this.seasonRepository = seasonRepository;
	}


	public void popolamento() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/gameXseason2023.json"));

			// Assicurati che il nodo principale sia un array
			if (jsonNode.isArray()) {
				// Itera sugli oggetti nel file JSON
				for (JsonNode Node : jsonNode) {
					Games games = new Games();
					games.setGameId(Node.path("id").asInt());

					 Season season = seasonRepository.findByYear(Node.path("season").asInt());
					 System.out.println(season.getYear()+" "+season.getId());
					if(season == null){
						continue;
					}
					games.setSeason(season);
					System.out.println("va primo");

					Optional<Teams> teams = teamRepository.findById(Node.path("teams").path("home").path("id").asInt());

					Optional<Teams> teams1 = teamRepository.findById(Node.path("teams").path("visitors").path("id").asInt());


					if(teams.isEmpty() || teams1.isEmpty()){
						System.out.println("vuoto");
						continue;
					}
					Teams team = teams.get();
					Teams team1 = teams1.get();
					System.out.println("va secondo");

					if(! (team.isNbaFranchise() && team1.isNbaFranchise()) ){
						System.out.println("non nba");
						continue;
					}
					else {
						System.out.println("nba");
					}


					games.setHomeTeam(team);
					games.setAwayTeam(team1);
					System.out.println("va terzo");


					if (Node.path("date").path("start").isNull()) {
						games.setStartDate(null);
					} else {
						games.setStartDate(Node.path("date").path("start").asText());
					}

					System.out.println("va quarto");
					if (Node.path("date").path("end").isNull()) {
						games.setEndDate(null);
					} else {
						games.setEndDate(Node.path("date").path("end").asText());
					}
					System.out.println("va quinto");

					games.setStage(Node.path("stage").asInt());
					games.setClock(null);
					games.setHalftime(null);
					games.setStatus(Node.path("status").path("long").asText());
					games.setCurrentPeriod(Node.path("status").path("period").asInt());
					games.setTotalPeriods(Node.path("status").path("maxRegular").asInt());
					games.setAreaName(Node.path("arena").path("name").asText());
					games.setArenaCity(Node.path("arena").path("city").asText());
					games.setArenaState(Node.path("arena").path("state").asText());
					games.setArenaCountry(Node.path("arena").path("country").asText());
					System.out.println("va sesto");

					games.setQ1a(isNull(Node.path("scores").path("visitors").path("linescore").get(0)));
					games.setQ2a(isNull(Node.path("scores").path("visitors").path("linescore").get(1)));
					games.setQ3a(isNull(Node.path("scores").path("visitors").path("linescore").get(2)));
					games.setQ4a(isNull(Node.path("scores").path("visitors").path("linescore").get(3)));
					games.setQ1h(isNull(Node.path("scores").path("home").path("linescore").get(0)));
					games.setQ2h(isNull(Node.path("scores").path("home").path("linescore").get(1)));
					games.setQ3h(isNull(Node.path("scores").path("home").path("linescore").get(2)));
					games.setQ4h(isNull(Node.path("scores").path("home").path("linescore").get(3)));

					System.out.println(games.getQ1a()+" score 1 quarto");

					System.out.println("va ultimo");
					System.out.println(games.getSeason().getId() +" "+ games.getSeason().getYear());

					gamesRepository.saveAndFlush(games);
					System.out.println("Object Games games saved in the DB");

			}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
			}
	}
public int isNull(JsonNode numero){
	return numero == null ? 0 : numero.asInt();
	}

}

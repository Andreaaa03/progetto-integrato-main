package com.example.popolamento.Service;

import com.example.popolamento.Entity.TeamStandings;
import com.example.popolamento.Repository.TeamStandingsRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PopolamentoService {
	private final TeamStandingsRepository teamStandingsRepository;

	@SneakyThrows
	@Autowired
	public PopolamentoService(TeamStandingsRepository teamStandingsRepository) {
		this.teamStandingsRepository = teamStandingsRepository;
	}



	public void popolamento() {

		int count = 0;
		try {
			// Carica il file JSON
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/standings.json"));

			// Assicurati che il nodo principale sia un array
			if (jsonNode.isArray()) {
				// Itera sugli oggetti nel file JSON
				for (JsonNode teamNode : jsonNode) {
					// Estrai le informazioni desiderate
					Integer teamId = teamNode.path("team").path("id").asInt();

					count++;
					TeamStandings teamStandings = new TeamStandings();
					teamStandings.setTeamId(teamId);
					teamStandings.setSeasonId(1);
					teamStandings.setConferenceName(teamNode.path("conference").path("name").asText());
					teamStandings.setConferenceRank(teamNode.path("conference").path("rank").asInt());
					teamStandings.setConferenceWin(teamNode.path("conference").path("win").asInt());
					teamStandings.setConferenceLoss(teamNode.path("conference").path("loss").asInt());
					teamStandings.setDivisionGamesBehind(teamNode.path("division").path("gamesBehind").asDouble());



					teamStandings.setHomeWin(teamNode.path("win").path("home").asInt());
					teamStandings.setAwayWin(teamNode.path("win").path("away").asInt());
					teamStandings.setTotalWin(teamNode.path("win").path("total").asInt());
					teamStandings.setWinPercentage(teamNode.path("win").path("percentage").asDouble());
					teamStandings.setLastTenWin(teamNode.path("win").path("lastTen").asInt());
					teamStandings.setHomeLoss(teamNode.path("loss").path("home").asInt());
					teamStandings.setAwayLoss(teamNode.path("loss").path("away").asInt());
					teamStandings.setTotalLoss(teamNode.path("loss").path("total").asInt());
					teamStandings.setLossPercentage(teamNode.path("loss").path("percentage").asDouble());


					teamStandings.setLastTenLoss(teamNode.path("lastTen").path("loss").asInt());
					teamStandings.setGamesBehind(teamNode.path("gamesBehind").asDouble());
					teamStandings.setStreak(teamNode.path("streak").asInt());
					teamStandings.setWinStreak(teamNode.path("isWinStreak").asBoolean());

					// Salva l'oggetto nel database
					teamStandingsRepository.save(teamStandings);
					System.out.println("TeamStandings " + count + " salvato.");


				}
			} else {
				System.out.println("Il nodo principale non è un array.");
			}
		} catch (
				IOException e) {
			e.printStackTrace();
		}

	}


}

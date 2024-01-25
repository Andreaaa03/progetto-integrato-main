package com.example.popolamento.Service;

import com.example.popolamento.Entity.League;
import com.example.popolamento.Entity.Teams;
import com.example.popolamento.Repository.TeamRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class TeamService {
	private final TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public void popolamento() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/json/teams.json"));

			// Assicurati che il nodo principale sia un array
			if (jsonNode.isArray()) {

				// Itera sugli oggetti nel file JSON
				for (JsonNode Node : jsonNode) {
					Teams teams = new Teams();
					teams.setId(Node.path("id").asInt());
					teams.setTeamName(Node.path("name").asText());
					teams.setNickname(Node.path("nickname").asText());
					teams.setCode(Node.path("code").asText());
					teams.setCity(Node.path("city").asText());
					teams.setLogo(Node.path("logo").asText());
					teams.setAllStar(Node.path("allStar").asBoolean());
					teams.setNbaFranchise(Node.path("nbaFranchise").asBoolean());
					String conference = Node.path("leagues").path("standard").path("conference").asText();

					String division = Node.path("leagues").path("standard").path("division").asText();
					if(!teams.isNbaFranchise()){
						System.out.println("non e nba");
						continue;
					}
					League league = new League();
					switch (conference){
						case "East":

							switch (division){
								case "Atlantic":
									league.setId(1);
									break;
								case "Southeast":
									league.setId(2);
									break;
								case "Central":
									league.setId(4);
									break;
								case "East":
									league.setId(10);
									break;
							}
							break;
						case "West":
							switch (division){
								case "Atlantic":
									league.setId(5);
									break;
								case "Central":
									league.setId(6);
									break;
								case "Northwest":
									league.setId(7);
									break;
								case "Pacific":
									league.setId(8);
									break;
								case "Southwest":
									league.setId(9);
									break;
								case "West":
									league.setId(11);
									break;
							}
							break;
						default:
							league.setId(3);
							break;
					}
					teams.setLeague(league);

					System.out.println("league: "+league.getId());
					System.out.println("teams id:"+teams.getId());
					if (teams.isNbaFranchise()){
						teamRepository.save(teams);}

				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}
}


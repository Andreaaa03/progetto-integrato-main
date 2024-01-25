package com.example.popolamento.Service;

import com.example.popolamento.Entity.Player;
import com.example.popolamento.Repository.PlayerRepository;
import com.example.popolamento.Repository.TeamRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final PlayerStatService playerStatService;
	private final TeamRepository teamRepository;

	public PlayerService(PlayerRepository playerRepository, PlayerStatService playerStatService, TeamRepository teamRepository) {
		this.playerRepository = playerRepository;
		this.playerStatService = playerStatService;
		this.teamRepository = teamRepository;
	}

	public void popolamento() {
		List<Integer> idTeam = new ArrayList<Integer>();

		teamRepository.findAll().forEach(team -> {
			idTeam.add(team.getId());
		});
		Integer count = 0;
		Integer count2 = 0;
		for (Integer integer : idTeam) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(playerStatService.url("https://v2.nba.api-sports.io/players?season=2023&team="+integer));
				if (!jsonNode.path("errors").isEmpty()){
//					stop per 10 minuti
					Thread.sleep(600000);
					jsonNode = objectMapper.readTree(playerStatService.url("https://v2.nba.api-sports.io/players?season=2023&team="+integer));
				}
				count++;
				System.out.println("team number: "+count);
				jsonNode = jsonNode.path("response");
				for (JsonNode Node : jsonNode) {
					Player p = new Player();
					count2++;
					System.out.println("player number: "+count2);

					p.setId(Node.path("id").asInt());
					p.setTeam(teamRepository.findById(integer).get());

					p.setFirstName(Node.path("firstname").asText());
					p.setLastName(Node.path("lastname").asText());
//					se birthdate è null metti 0001-01-01
					System.out.println(Node.path("birth").path("date").asText());
					if (Node.path("birth").path("date").asText().equals("null")) {
						p.setBirthDate("0001-01-01");
					} else {
						p.setBirthDate(Node.path("birth").path("date").asText());
					}
					p.setBirthCountry(Node.path("birth").path("country").asText());

					p.setNbaStart(Year.of(Node.path("nba").path("start").asInt()));
					p.setNbaPro(Node.path("nba").path("pro").asInt());

					p.setHeightFeet(Node.path("height").path("feets").asInt());
					p.setHeightInches(Node.path("height").path("inches").asInt());
					p.setHeightMeters(Node.path("height").path("meters").asDouble());

					p.setWeightPounds(Node.path("weight").path("pounds").asInt());
					p.setWeightKilograms(Node.path("weight").path("kilograms").asDouble());

					p.setCollege(Node.path("college").asText());
					p.setLastAffiliation(Node.path("affiliation").asText());

					p.setNumeroMaglia(Node.path("leagues").path("standard").path("jersey").asInt());

					playerRepository.saveAndFlush(p);
					System.out.println("salvato"+ p.getId());
				}


			} catch (JsonMappingException e) {
				throw new RuntimeException(e);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

	}
}

package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.TeamsResponse;
import com.slamDunkers.SlamStats.Service.TeamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamsController {
	@Autowired
	private final TeamsService service;
	@Autowired
	public TeamsController(TeamsService service) {
		this.service = service;
	}



	@GetMapping("/All")
	public List<TeamsResponse> getAllTeamsAsPayload() {
		List<Teams> teams = service.selezionaTuttiTeams();
		List<TeamsResponse> teamsResponses = new ArrayList<>();
		for (Teams team : teams) {
			teamsResponses.add(team.toTeamsResponse());
		}
		return teamsResponses;
	}

	@GetMapping("/squadra")
	public TeamsResponse getTeamById(int id) {
		Optional<Teams> team =service.selezionaTeamById(id);
		if(team.isPresent()) {
			return team.get().toTeamsResponse();
		}
		else
			return null;
	}
}

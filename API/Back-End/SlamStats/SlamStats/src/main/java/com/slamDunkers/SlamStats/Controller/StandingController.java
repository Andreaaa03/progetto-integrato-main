package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.TeamStandings;
import com.slamDunkers.SlamStats.Payload.Response.TeamStandingsResponse;
import com.slamDunkers.SlamStats.Service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/standings")
@CrossOrigin
public class StandingController {
	@Autowired
	private StandingsService service;

	@Autowired
	public StandingController(StandingsService service) {
		this.service = service;
	}
	@GetMapping("/All")
	public List<TeamStandingsResponse> getStandings() {
		return service.selezionaTuttiTeams();
	}
}

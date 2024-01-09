package com.slamDunkers.SlamStats.Controller;


import com.slamDunkers.SlamStats.Payload.Response.TeamStatisticsResponse;
import com.slamDunkers.SlamStats.Service.TeamsStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teams")
@CrossOrigin
public class TeamsStatisticsController {
	@Autowired
	private final TeamsStatisticsService service;
	@Autowired
	public TeamsStatisticsController(TeamsStatisticsService service) {
		this.service = service;
	}
	@GetMapping("/statistics")
	public TeamStatisticsResponse getTeamsStatisticsById(Integer id) {
		return service.selezionaTeamsStatisticsById(id);
	}
}

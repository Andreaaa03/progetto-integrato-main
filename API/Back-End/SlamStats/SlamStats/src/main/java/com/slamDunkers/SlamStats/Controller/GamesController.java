package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Payload.Response.CalendarioDateResponse;
import com.slamDunkers.SlamStats.Payload.Response.PartitaStatResponse;
import com.slamDunkers.SlamStats.Service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
@CrossOrigin
public class GamesController {
	@Autowired
	private GamesService service;
	@Autowired
	public GamesController(GamesService service) {
		this.service = service;
	}

	@GetMapping("/All")
	public List<CalendarioDateResponse> getGames() {
		return service.getGames();
	}

	@GetMapping("/date")
	public List<CalendarioDateResponse> getGameByDate(String date) {
		return service.getGameByDate(date);
	}

	@GetMapping("/Last20")
	public List<CalendarioDateResponse> getLast20(LocalDate date) {
		return service.getLast20FromDate(date);
	}

	@GetMapping("/teamId")
	public List<CalendarioDateResponse> calendarioFiltrato(int teamId) {
		return service.partiteGiocateSquadra(teamId);
	}

	@GetMapping("/gameId")
	public Optional<List<CalendarioDateResponse>> getGameById(Integer id) {
		return service.findById(id);
	}

	@GetMapping("/partitaStat")
	public PartitaStatResponse getPartitaStat(Integer idPartita) {
		return service.partitaStatResponse(idPartita);
	}

}

package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Payload.Response.CalendarioDateResponse;
import com.slamDunkers.SlamStats.Payload.Response.CommentiResponse;
import com.slamDunkers.SlamStats.Payload.Response.PartitaStatResponse;
import com.slamDunkers.SlamStats.Service.CommentiService;
import com.slamDunkers.SlamStats.Service.GamesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/games")
@CrossOrigin
public class GamesController {
	private final GamesService service;
	private final CommentiService commentiService;
	public GamesController(GamesService service, CommentiService commentiService) {
		this.service = service;
        this.commentiService = commentiService;
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
	public Optional<List<CalendarioDateResponse>> getGameById(@RequestParam Integer id) {
		return service.findById(id);
	}

	@GetMapping("/partitaStat")
	public PartitaStatResponse getPartitaStat(Integer idPartita) {
		return service.partitaStatResponse(idPartita);
	}

	@GetMapping("/commentiPartita")
	public List<CommentiResponse> getPartitaCommenti(Integer id) {
		return commentiService.commentiPartita(id);
	}
}

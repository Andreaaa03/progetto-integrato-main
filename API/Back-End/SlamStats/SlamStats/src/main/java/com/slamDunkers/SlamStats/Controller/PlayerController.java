package com.slamDunkers.SlamStats.Controller;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import com.slamDunkers.SlamStats.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
@CrossOrigin
public class PlayerController {
	@Autowired
	private PlayerService service;
	@Autowired
	public PlayerController(PlayerService service) {
		this.service = service;
	}

	@GetMapping("/All")
	public List<Player> getPlayers() {
		return service.selezzionaTuttiGiocatori();
	}

	@GetMapping("/Id")
	public PlayerResponse getPlayer(int playerId) {
		return service.selezionaGiocatore(playerId);
	}

	@GetMapping("/teamId")
	public List<PlayerResponse> getPlayersByTeamName(int id) {
		return service.selezionaGiocatoriPerSquadra(id);
	}
}

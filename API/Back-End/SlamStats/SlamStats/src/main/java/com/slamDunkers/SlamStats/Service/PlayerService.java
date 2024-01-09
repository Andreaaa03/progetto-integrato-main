package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import com.slamDunkers.SlamStats.Repository.PlayerRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	private PlayerRepository playerRepository;
	private TeamsRepository teamsRepository;

	@Autowired
	public PlayerService(PlayerRepository playerRepository,TeamsRepository teamsRepository ){
		this.playerRepository =playerRepository;
		this.teamsRepository = teamsRepository;
	}

	public List<Player> selezzionaTuttiGiocatori(){return playerRepository.findAll();}

	public PlayerResponse selezionaGiocatore(int playerId){
		return playerRepository.findById(playerId).toPlayerResponse();
	}

	public List<PlayerResponse> selezionaGiocatoriPerSquadra(int teamId) {

		Optional<Teams> teamFound = teamsRepository.findById(teamId);
		if (teamFound.isPresent()) {
			List<PlayerResponse> playerResponseList = new ArrayList<>();
			for (Player player : playerRepository.findByTeam(teamFound)) {
				playerResponseList.add(player.toPlayerResponse());
			}
			return playerResponseList;
		} else {
			return null;
		}
	}


}

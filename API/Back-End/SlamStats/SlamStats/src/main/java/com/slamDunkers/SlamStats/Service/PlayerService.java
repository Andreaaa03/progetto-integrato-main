package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Repository.PlayerRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Player selezionaGiocatore(int playerId){return playerRepository.findById(playerId);}

	public List<Player> selezionaGiocatoriPerSquadra(String teamName) {

		Optional<Teams> teamFound = teamsRepository.findByTeamName(teamName);
		if (teamFound.isPresent()) {
			return playerRepository.findByTeam(teamFound);
		} else {
			return null;
		}
	}

//
//	public List<Player> selezionaGiocatoriPerSquadra(String teamName){return playerRepository.findByTeamName(teamName);}


}

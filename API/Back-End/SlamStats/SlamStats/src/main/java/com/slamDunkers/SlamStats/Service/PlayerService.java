package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import com.slamDunkers.SlamStats.Payload.Response.PlayerStatisticsResponse;
import com.slamDunkers.SlamStats.Payload.Response.ToResponse;
import com.slamDunkers.SlamStats.Repository.PlayerRepository;
import com.slamDunkers.SlamStats.Repository.PlayerStatRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	private final PlayerRepository playerRepository;
	private final TeamsRepository teamsRepository;
	private final PlayerStatRepository playerStatRepository;
	private final ToResponse toResponse;
	@Autowired
	public PlayerService(PlayerRepository playerRepository, TeamsRepository teamsRepository, PlayerStatRepository playerStatRepository, ToResponse toResponse){
		this.playerRepository =playerRepository;
		this.teamsRepository = teamsRepository;
		this.playerStatRepository = playerStatRepository;
		this.toResponse = toResponse;
	}

	public List<Player> selezionaTuttiGiocatori(){return playerRepository.findAll();}

	public PlayerResponse selezionaGiocatore(int playerId){
		return toPlayerResponse(playerRepository.findById(playerId));
	}

	public Integer somma(List<Integer> input) {
		List<Integer> x = new ArrayList<>(input);
		int somma = 0;
		for (Integer integer : x) {
			somma += integer;
		}
		return somma;
	}

	public Integer media(List<Integer> input) {
		int x = somma(input);
		if (x == 0) {
			return 0;
		}
		else {
			return x / input.size();
		}
	}

	public List<PlayerResponse> selezionaGiocatoriPerSquadra(int teamId) {

		Optional<Teams> teamFound = teamsRepository.findById(teamId);
		if (teamFound.isPresent()) {
			List<PlayerResponse> playerResponseList = new ArrayList<>();
			for (Player player : playerRepository.findByTeam(teamFound)) {
				playerResponseList.add(toPlayerResponse(player));
			}
			return playerResponseList;
		} else {
			return Collections.emptyList();
		}
	}
	public PlayerResponse toPlayerResponse(Player player) {
		PlayerResponse playerResponse = new PlayerResponse();
		playerResponse.playerId = player.getId();
		playerResponse.team = player.getTeam().toTeamsResponse();
		playerResponse.firstName = player.getFirstName();
		playerResponse.lastName = player.getLastName();
		playerResponse.birthDate = player.getBirthDate().toString();
//		trovare l'età del player
		LocalDate now = LocalDate.now();
		LocalDate birthDate = player.getBirthDate();
		int year = now.getYear() - birthDate.getYear();
		int month = now.getMonthValue() - birthDate.getMonthValue();
		int day = now.getDayOfMonth() - birthDate.getDayOfMonth();
		if (month < 0 || (month == 0 && day < 0)) {
			year--;
		}
		playerResponse.age = year;


		playerResponse.birthCountry = player.getBirthCountry();
		playerResponse.nbaStart = player.getNbaStart().getValue();
		playerResponse.nbaPro = player.getNbaPro();
		playerResponse.heightFeet = player.getHeightFeet();
		playerResponse.heightInches = player.getHeightInches();
		playerResponse.heightMeters = player.getHeightMeters();
		playerResponse.weightPounds = player.getWeightPounds();
		playerResponse.weightKg = player.getWeightKilograms();
		playerResponse.college = player.getCollege();
		playerResponse.affiliation= player.getLastAffiliation();
		playerResponse.numeroMaglia = player.getNumeroMaglia();

		List<PlayerStatistics> playerStatisticsList = playerStatRepository.findByPlayerId(player.getId());
		playerResponse.setPoints(somma(playerStatisticsList.stream().map(PlayerStatistics::getPoints).toList()));
		playerResponse.setAssists(somma(playerStatisticsList.stream().map(PlayerStatistics::getAssists).toList()));
		if (playerStatisticsList.isEmpty()) {
			playerResponse.setPosizione("N/A");
		}
		else {
			playerResponse.setPosizione(playerStatisticsList.get(0).getPos());
		}
		playerResponse.setStatistics(toResponse.toPlayerStatisticsResponse(player.getId()));



		return playerResponse;
	}


}

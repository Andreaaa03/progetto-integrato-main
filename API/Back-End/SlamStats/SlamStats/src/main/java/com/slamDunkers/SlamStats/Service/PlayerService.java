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

/**
 * This method retrieves a specific player's details.
 *
 * @param playerId The ID of the player whose details are to be retrieved.
 * @return A PlayerResponse object representing the player's details.
 * If no player is found with the specified ID, the method returns null.
 */
public PlayerResponse selezionaGiocatore(int playerId){
 return toPlayerResponse(playerRepository.findById(playerId));
}

/**
 * This method retrieves all players from a specific team.
 *
 * @param teamId The ID of the team whose players are to be retrieved.
 * @return A list of PlayerResponse objects, each representing a player from the specified team.
 * If no players are found for the specified team, the method returns an empty list.
 */
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

/**
 * This method converts a Player object into a PlayerResponse object.
 *
 * @param player The Player object to be converted.
 * @return A PlayerResponse object containing the details of the player.
 * The PlayerResponse object includes the player's ID, team, first name, last name, birth date, age, birth country,
 * NBA start year, NBA pro status, height (in feet, inches, and meters), weight (in pounds and kilograms),
 * college, last affiliation, jersey number, total points, total assists, position, and statistics from the last 5 games.
 * If the player has no statistics from the last 5 games, the statistics field in the PlayerResponse object is set to an empty list.
 */
public PlayerResponse toPlayerResponse(Player player) {
	PlayerResponse playerResponse = new PlayerResponse();
	playerResponse.playerId = player.getId();
	playerResponse.team = player.getTeam().toTeamsResponse();
	playerResponse.firstName = player.getFirstName();
	playerResponse.lastName = player.getLastName();
	playerResponse.birthDate = player.getBirthDate().toString();
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
	List<PlayerStatistics> playerStatisticsList = playerStatRepository.findLast5Games(player.getId());
	for (PlayerStatistics playerStatistics : playerStatisticsList) {
		playerResponse.points += playerStatistics.getPoints();
		playerResponse.assists += playerStatistics.getAssists();
	}
	playerResponse.posizione = playerStatisticsList.get(0).getPos();
	playerResponse.setStatistics(playerStatisticsList.stream().map(PlayerStatistics::toPlayerStatisticsResponse).toList());
	if (playerResponse.getStatistics().size() == 0) {
		playerResponse.setStatistics(Collections.emptyList());
	}
	return playerResponse;
}


}

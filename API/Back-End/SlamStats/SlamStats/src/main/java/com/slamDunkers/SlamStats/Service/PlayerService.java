package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Player;
import com.slamDunkers.SlamStats.Entity.PlayerStatistics;
import com.slamDunkers.SlamStats.Entity.Teams;
import com.slamDunkers.SlamStats.Payload.Response.PlayerResponse;
import com.slamDunkers.SlamStats.Repository.PlayerRepository;
import com.slamDunkers.SlamStats.Repository.PlayerStatRepository;
import com.slamDunkers.SlamStats.Repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
	private PlayerRepository playerRepository;
	private TeamsRepository teamsRepository;

	private final PlayerStatRepository playerStatRepository;
	@Autowired
	public PlayerService(PlayerRepository playerRepository, TeamsRepository teamsRepository, PlayerStatRepository playerStatRepository){
		this.playerRepository =playerRepository;
		this.teamsRepository = teamsRepository;
		this.playerStatRepository = playerStatRepository;
	}

	public List<Player> selezzionaTuttiGiocatori(){

		return playerRepository.findAll();
	}

	public PlayerResponse selezionaGiocatore(int playerId){
		PlayerResponse player = toPlayerResponse( playerRepository.findById(playerId));
//		List<PlayerStatistics> playerStatisticsList = playerStatRepository.findByPlayerId(playerId);
//		player.setPoints(media(playerStatisticsList.stream().map(PlayerStatistics::getPoints).toList()));
//		player.setAssists(media(playerStatisticsList.stream().map(PlayerStatistics::getAssists).toList()));

//		player.setFgm(media(playerStatisticsList.stream().map(PlayerStatistics::getFgm).toList()));
//		player.setFga(media(playerStatisticsList.stream().map(PlayerStatistics::getFga).toList()));
//
//		player.setFtm(media(playerStatisticsList.stream().map(PlayerStatistics::getFtm).toList()));
//		player.setFta(media(playerStatisticsList.stream().map(PlayerStatistics::getFta).toList()));
//
//		player.setTpm(media(playerStatisticsList.stream().map(PlayerStatistics::getTpm).toList()));
//		player.setTpa(media(playerStatisticsList.stream().map(PlayerStatistics::getTpa).toList()));
//
//		player.setOffReb(media(playerStatisticsList.stream().map(PlayerStatistics::getOffReb).toList()));
//		player.setDefReb(media(playerStatisticsList.stream().map(PlayerStatistics::getDefReb).toList()));
//		player.setTotReb(media(playerStatisticsList.stream().map(PlayerStatistics::getTotReb).toList()));
//		player.setPFouls(media(playerStatisticsList.stream().map(PlayerStatistics::getPFouls).toList()));
//		player.setSteals(media(playerStatisticsList.stream().map(PlayerStatistics::getSteals).toList()));
//		player.setTurnovers(media(playerStatisticsList.stream().map(PlayerStatistics::getTurnovers).toList()));
//		player.setBlocks(media(playerStatisticsList.stream().map(PlayerStatistics::getBlocks).toList()));
//		player.setPlusMinus(media(playerStatisticsList.stream().map(PlayerStatistics::getPlusMinus).toList()));

//		player.setPosizione(playerStatisticsList.get(0).getPos());

		return player;
	}

	public Integer somma(List<Integer> input) {
		List<Integer> x = new ArrayList<>();
		for (Integer integer : input) {
			x.add(integer);
		}
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
			int media = x / input.size();
			return media;
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
			return null;
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
		int Year = now.getYear() - birthDate.getYear();
		int Month = now.getMonthValue() - birthDate.getMonthValue();
		int Day = now.getDayOfMonth() - birthDate.getDayOfMonth();
		if (Month < 0 || (Month == 0 && Day < 0)) {
			Year--;
		}
		int age = Year;
		playerResponse.age = age;


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
		if (playerStatisticsList.size() == 0) {
			playerResponse.setPosizione("N/A");
		}
		else {
			playerResponse.setPosizione(playerStatisticsList.get(0).getPos());
		}



		return playerResponse;
	}


}

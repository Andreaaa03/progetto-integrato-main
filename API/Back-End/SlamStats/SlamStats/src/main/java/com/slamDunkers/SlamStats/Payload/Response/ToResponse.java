package com.slamDunkers.SlamStats.Payload.Response;

import com.slamDunkers.SlamStats.Entity.*;
import com.slamDunkers.SlamStats.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ToResponse {

	private final TeamsStatisticsRepository repository;
	private final StandingsRepository standings;
	private final GamesRepository gameRepository;
	private final ScoreRepository scoreRepository;
	private final PlayerStatRepository playerStatRepository;



	@Autowired
	public ToResponse(TeamsStatisticsRepository repository, StandingsRepository standings, GamesRepository gameRepository, ScoreRepository scoreRepository, PlayerStatRepository playerStatRepository) {
		this.repository = repository;
		this.standings = standings;
		this.gameRepository = gameRepository;
		this.scoreRepository = scoreRepository;
		this.playerStatRepository = playerStatRepository;
	}


	public TeamStatisticsResponse toTeamsStatisticsResponse(Integer id) {
		Optional<TeamsStatistics> teamsStatistic = Optional.ofNullable(repository.findByTeamId(id));
		TeamStandings teamStandings = standings.findByTeamId(id);
		if(teamsStatistic.isEmpty()) {
			return null;
		}
		TeamsStatistics teamsStatistics = teamsStatistic.get();

		TeamStatisticsResponse response = new TeamStatisticsResponse();

		response.setTeam(teamsStatistics.getTeam().toTeamsResponse());
		response.setSeason(teamsStatistics.getSeason().getYear());
		response.setGames(teamsStatistics.getGames());
		response.setFastBreakPoints(teamsStatistics.getFastBreakPoints());
		response.setPointsInPaint(teamsStatistics.getPointsInPaint());
		response.setBiggestLead(teamsStatistics.getBiggestLead());
		response.setSecondChancePoints(teamsStatistics.getSecondChancePoints());
		response.setPointsOffTurnover(teamsStatistics.getPointsOffTurnover());
		response.setPoints(teamsStatistics.getPoints());
		response.setFgm(teamsStatistics.getFgm());
		response.setFga(teamsStatistics.getFga());
		response.setFgp(teamsStatistics.getFgp());
		response.setFtm(teamsStatistics.getFtm());
		response.setFta(teamsStatistics.getFta());
		response.setFtp(teamsStatistics.getFtp());
		response.setTpm(teamsStatistics.getTpm());
		response.setTpa(teamsStatistics.getTpa());
		response.setTpp(teamsStatistics.getTpp());
		response.setOffReb(teamsStatistics.getOffReb());
		response.setDefReb(teamsStatistics.getDefReb());
		response.setTotReb(teamsStatistics.getTotReb());
		response.setAssists(teamsStatistics.getAssists());
		response.setSteals(teamsStatistics.getSteals());
		response.setTurnovers(teamsStatistics.getTurnovers());
		response.setBlocks(teamsStatistics.getBlocks());
		response.setPlusMinus(teamsStatistics.getPlusMinus());
		response.setPfouls(teamsStatistics.getPFouls());
		response.setConferenceRank(teamStandings.getConferenceRank());
		return response;
		}

	public TeamStatGameResponse toPartitaStatResponse(List<PlayerStatistics> stat, QaurtiScoreResponse qaurtiScoreResponse){
		TeamStatGameResponse response = new TeamStatGameResponse();
		List<Integer> fgm = new ArrayList<>();
		List<Integer> fga = new ArrayList<>();
		List<Double> fgp = new ArrayList<>();
		List<Integer> ftm = new ArrayList<>();
		List<Integer> fta = new ArrayList<>();
		List<Double> ftp = new ArrayList<>();
		List<Integer> tpm = new ArrayList<>();
		List<Integer> tpa = new ArrayList<>();
		List<Double> tpp = new ArrayList<>();
		List<Integer> offReb = new ArrayList<>();
		List<Integer> defReb = new ArrayList<>();
		List<Integer> totReb = new ArrayList<>();
		List<Integer> assists = new ArrayList<>();
		List<Integer> pFouls = new ArrayList<>();
		List<Integer> steals = new ArrayList<>();
		List<Integer> turnovers = new ArrayList<>();
		List<Integer> blocks = new ArrayList<>();
		List<Integer> plusMinus = new ArrayList<>();

		List<PlayerStatisticsResponse> playersStatistics = new ArrayList<>();


		for (PlayerStatistics p : stat){
			fgm.add(p.getFgm());
			fga.add(p.getFga());
			fgp.add(p.getFgp());
			ftm.add(p.getFtm());
			fta.add(p.getFta());
			ftp.add(p.getFtp());
			tpm.add(p.getTpm());
			tpa.add(p.getTpa());
			tpp.add(p.getTpp());
			offReb.add(p.getOffReb());
			defReb.add(p.getDefReb());
			totReb.add(p.getTotReb());
			assists.add(p.getAssists());
			pFouls.add(p.getPFouls());
			steals.add(p.getSteals());
			turnovers.add(p.getTurnovers());
			blocks.add(p.getBlocks());
			plusMinus.add(p.getPlusMinus());
			playersStatistics.add(p.toPlayerStatisticsResponse());
		}
		response.setFga(sommaInt(fga));
		response.setFgm(sommaInt(fgm));
		response.setFgp(sommaDouble(fgp));
		response.setFta(sommaInt(fta));
		response.setFtm(sommaInt(ftm));
		response.setFtp(sommaDouble(ftp));
		response.setTpa(sommaInt(tpa));
		response.setTpm(sommaInt(tpm));
		response.setTpp(sommaDouble(tpp));
		response.setOffReb(sommaInt(offReb));
		response.setDefReb(sommaInt(defReb));
		response.setTotReb(sommaInt(totReb));
		response.setAssists(sommaInt(assists));
		response.setPFouls(sommaInt(pFouls));
		response.setSteals(sommaInt(steals));
		response.setTurnovers(sommaInt(turnovers));
		response.setBlocks(sommaInt(blocks));
		response.setPlusMinus(sommaInt(plusMinus));
		response.setPlayersStatistics(playersStatistics);
		response.setQaurtiScoreResponse(qaurtiScoreResponse);
		response.toMap();
		return response;
	}


	public PartitaStatResponse toPartitaStatResponse(Games game){

		PartitaStatResponse partitaStatResponse = new PartitaStatResponse();

		partitaStatResponse.setCalendarioDateResponse(toCalendarioDateResponse(Collections.singletonList(game)).get(0));

		var stat = playerStatRepository.findByGameAndTeam(game, game.getHomeTeam());
		QaurtiScoreResponse qaurtiScoreResponse = new QaurtiScoreResponse();
		qaurtiScoreResponse.setQ1Score(game.getQ1h());
		qaurtiScoreResponse.setQ2Score(game.getQ2h());
		qaurtiScoreResponse.setQ3Score(game.getQ3h());
		qaurtiScoreResponse.setQ4Score(game.getQ4h());
		partitaStatResponse.setHomeTeam(toPartitaStatResponse(stat,qaurtiScoreResponse));


		stat = playerStatRepository.findByGameAndTeam(game, game.getAwayTeam());
		QaurtiScoreResponse qaurtiScoreResponse1 = new QaurtiScoreResponse();
		qaurtiScoreResponse1.setQ1Score(game.getQ1a());
		qaurtiScoreResponse1.setQ2Score(game.getQ2a());
		qaurtiScoreResponse1.setQ3Score(game.getQ3a());
		qaurtiScoreResponse1.setQ4Score(game.getQ4a());
		partitaStatResponse.setAwayTeam(toPartitaStatResponse(stat,qaurtiScoreResponse1));

		return partitaStatResponse;
	}

	public List<CalendarioDateResponse> toCalendarioDateResponse(List<Games> games){
		List<CalendarioDateResponse> calendarioResponseList = new ArrayList<>();
		for (Games game : games)
		{
			CalendarioDateResponse calendario = new CalendarioDateResponse() ;
			calendario.setGameid(game.getId());
			calendario.setGameStartDate(game.getStartDate());


			if (game.getHomeTeam() != null) {
				calendario.setTeamHomeName(game.getHomeTeam().getTeamName());
				calendario.setConferenceNameHome(game.getHomeTeam().getLeague().getConference());
				calendario.setDivisionNameHome(game.getHomeTeam().getLeague().getDivision());
				calendario.setTeamIdHome(game.getHomeTeam().getId());
				calendario.setAllStarHome(game.getHomeTeam().isAllStar());
				calendario.setNicknameHome(game.getHomeTeam().getNickname());
				calendario.setLogoHome(game.getHomeTeam().getLogo());
				calendario.setCityHome(game.getHomeTeam().getCity());
				calendario.setCodeHome(game.getHomeTeam().getCode());

				if (scoreRepository.findByGameAndTeam(game, game.getHomeTeam()) != null) {
					calendario.setScoreTeamHome(scoreRepository.findByGameAndTeam(game, game.getHomeTeam()).getPoints());
				}
			}
			else { calendario.setTeamHomeName(" "); }


			if (game.getAwayTeam() != null) {
				calendario.setTeamAwayName(game.getAwayTeam().getTeamName());
				calendario.setConferenceNameAway(game.getAwayTeam().getLeague().getConference());
				calendario.setDivisionNameAway(game.getAwayTeam().getLeague().getDivision());
				calendario.setTeamIdAway(game.getAwayTeam().getId());
				calendario.setAllStarAway(game.getAwayTeam().isAllStar());
				calendario.setNicknameAway(game.getAwayTeam().getNickname());
				calendario.setLogoAway(game.getAwayTeam().getLogo());
				calendario.setCityAway(game.getAwayTeam().getCity());
				calendario.setCodeAway(game.getAwayTeam().getCode());
				if (scoreRepository.findByGameAndTeam(game, game.getAwayTeam()) != null){
					calendario.setScoreTeamAway(scoreRepository.findByGameAndTeam(game, game.getAwayTeam()).getPoints());
				}


			}
			else { calendario.setTeamHomeName(" "); }

			calendarioResponseList.add(calendario);
		}
		return calendarioResponseList;
	}

	public TeamStandingsResponse toTeamStandingsResponse(TeamStandings team) {
		TeamStandingsResponse response = new TeamStandingsResponse();
		response.setTeam(team.getTeam().toTeamsResponse());
		response.setSeason(team.getSeason().getYear());
		response.setConferenceRank(team.getConferenceRank());
		response.setConferenceWin(team.getConferenceWin());
		response.setConferenceLoss(team.getConferenceLoss());
		response.setDivisionGamesBehind(team.getDivisionGamesBehind());
		response.setHomeWin(team.getHomeWin());
		response.setAwayWin(team.getAwayWin());
		response.setTotalWin(team.getTotalWin());
		response.setWinPercentage(team.getWinPercentage());
		response.setLastTenWin(team.getLastTenWin());
		response.setHomeLoss(team.getHomeLoss());
		response.setAwayLoss(team.getAwayLoss());
		response.setTotalLoss(team.getTotalLoss());
		response.setLastTenLoss(team.getLastTenLoss());
		response.setGamesBehind(team.getGamesBehind());

		return response;
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
		playerResponse.setPoints(sommaInt(playerStatisticsList.stream().map(PlayerStatistics::getPoints).toList()));
		playerResponse.setAssists(sommaInt(playerStatisticsList.stream().map(PlayerStatistics::getAssists).toList()));
		if (playerStatisticsList.isEmpty()) {
			playerResponse.setPosizione("N/A");
		}
		else {
			playerResponse.setPosizione(playerStatisticsList.get(0).getPos());
		}



		return playerResponse;
	}

	public PlayerStatisticsResponse toPlayerStatisticsResponse(int idGiocatore){
		PlayerStatisticsResponse response = new PlayerStatisticsResponse();
		List<PlayerStatistics> playerStatistics = playerStatRepository.findByPlayerId(idGiocatore);
		if (playerStatistics.isEmpty()){
//			returna un oggetto con tutti i parametri 0
			return response;
		}
		response.setId(playerStatistics.get(0).getPlayer().getId());
		response.setNome(playerStatistics.get(0).getPlayer().getFirstName() + " " + playerStatistics.get(0).getPlayer().getLastName());
		response.setPos(playerStatistics.get(0).getPos());
		List<Integer> punti = new ArrayList<>();
		List<Double> minuti = new ArrayList<>();
		List<Integer> fgm = new ArrayList<>();
		List<Integer> fga = new ArrayList<>();
		List<Double> fgp = new ArrayList<>();
		List<Integer> ftm = new ArrayList<>();
		List<Integer> fta = new ArrayList<>();
		List<Double> ftp = new ArrayList<>();
		List<Integer> tpm = new ArrayList<>();
		List<Integer> tpa = new ArrayList<>();
		List<Double> tpp = new ArrayList<>();
		List<Integer> offReb = new ArrayList<>();
		List<Integer> defReb = new ArrayList<>();
		List<Integer> totReb = new ArrayList<>();
		List<Integer> assists = new ArrayList<>();
		List<Integer> pFouls = new ArrayList<>();
		List<Integer> steals = new ArrayList<>();
		List<Integer> turnovers = new ArrayList<>();
		List<Integer> blocks = new ArrayList<>();
		List<Integer> plusMinus = new ArrayList<>();


		for (PlayerStatistics p : playerStatistics){
			punti.add(p.getPoints());
			minuti.add(toDouble(p.getMin()));
			fgm.add(p.getFgm());
			fga.add(p.getFga());
			fgp.add(p.getFgp());
			ftm.add(p.getFtm());
			fta.add(p.getFta());
			ftp.add(p.getFtp());
			tpm.add(p.getTpm());
			tpa.add(p.getTpa());
			tpp.add(p.getTpp());
			offReb.add(p.getOffReb());
			defReb.add(p.getDefReb());
			totReb.add(p.getTotReb());
			assists.add(p.getAssists());
			pFouls.add(p.getPFouls());
			steals.add(p.getSteals());
			turnovers.add(p.getTurnovers());
			blocks.add(p.getBlocks());
			plusMinus.add(p.getPlusMinus());
		}
		response.setPoints(sommaInt(punti));
		response.setFgm(sommaInt(fgm));
		response.setFga(sommaInt(fga));
		response.setFgp(sommaDouble(fgp));
		response.setFtm(sommaInt(ftm));
		response.setFta(sommaInt(fta));
		response.setFtp(sommaDouble(ftp));
		response.setTpm(sommaInt(tpm));
		response.setTpa(sommaInt(tpa));
		response.setTpp(sommaDouble(tpp));
		response.setOffReb(sommaInt(offReb));
		response.setDefReb(sommaInt(defReb));
		response.setTotReb(sommaInt(totReb));
		response.setAssists(sommaInt(assists));
		response.setPFouls(sommaInt(pFouls));
		response.setSteals(sommaInt(steals));
		response.setTurnovers(sommaInt(turnovers));
		response.setBlocks(sommaInt(blocks));
		response.setPlusMinus(sommaInt(plusMinus));
		String minutiGiocatiString = String.valueOf(sommaDouble(minuti));
		String[] minutiGiocatiStringArray = minutiGiocatiString.split("\\.");
		String minutiGiocatiStringFinal = minutiGiocatiStringArray[0] + ":" + minutiGiocatiStringArray[1];
		response.setMin(minutiGiocatiStringFinal);
		return response;
	}

	public TeamsResponse toTeamsResponse(Teams team) {

		if (team.getLeague().getDivision() == null) {
			TeamsResponse teamsResponse = new TeamsResponse(
					team.getId(),
					team.getTeamName(),
					team.getCity(),
					team.getLogo(),
					team.getNickname(),
					team.isAllStar(),
					team.isNbaFranchise(),
					team.getLeague().getConference(),
					team.getCode()
			);
			return teamsResponse;

		}
		TeamsResponse teamsResponse = new TeamsResponse(
				team.getId(),
				team.getTeamName(),
				team.getCity(),
				team.getLogo(),
				team.getNickname(),
				team.isAllStar(),
				team.isNbaFranchise(),
				team.getLeague().getConference(),
				team.getLeague().getDivision(),
				team.getCode()

		);
		return teamsResponse;



	}

//	TODO: creare un file con i metodi tools

	public int sommaInt(List<Integer> lista) {
		int somma = 0;
		for (Integer integer : lista) {
			somma += integer;
		}
		return somma;
	}
	public double sommaDouble(List<Double> lista) {
		double somma = 0;
		for (Double integer : lista) {
			somma += integer;
		}
		return somma;
	}
	public double toDouble(String stringa) {
		String[] stringaArray = stringa.split(":");
		double numero = Double.parseDouble(stringaArray[0]) + (Double.parseDouble(stringaArray[1])/60);
		return numero;
	}

}

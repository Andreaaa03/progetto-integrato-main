package com.slamDunkers.SlamStats.Payload.Response;

import com.slamDunkers.SlamStats.Entity.*;
import com.slamDunkers.SlamStats.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static java.lang.Math.round;

@Service
public class ToResponse {
    private final TeamsStatisticsRepository repository;
    private final StandingsRepository standings;
    private final ScoreRepository scoreRepository;
    private final PlayerStatRepository playerStatRepository;

    @Autowired
    public ToResponse(TeamsStatisticsRepository repository, StandingsRepository standings, ScoreRepository scoreRepository, PlayerStatRepository playerStatRepository) {
        this.repository = repository;
        this.standings = standings;
        this.scoreRepository = scoreRepository;
        this.playerStatRepository = playerStatRepository;
    }


    /**
     * This method converts a team's statistics into a response format.
     *
     * @param id The ID of the team whose statistics are to be converted.
     * @return A TeamStatisticsResponse object containing the team's statistics.
     * The TeamStatisticsResponse object includes details such as the team's games, fast break points, points in paint, biggest lead,
     * second chance points, points off turnover, points, field goals made, field goals attempted, field goal percentage,
     * free throws made, free throws attempted, free throw percentage, three-pointers made, three-pointers attempted,
     * three-point percentage, offensive rebounds, defensive rebounds, total rebounds, assists, steals, turnovers, blocks,
     * plus-minus, personal fouls, and conference rank.
     * If no team statistics are found with the specified ID, the method returns null.
     */
    public TeamStatisticsResponse toTeamsStatisticsResponse(Integer id) {
        Optional<TeamsStatistics> teamsStatistic = Optional.ofNullable(repository.findByTeamId(id));
        TeamStandings teamStandings = standings.findByTeamId(id);
        if (teamsStatistic.isEmpty()) {
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

    /**
     * This method converts a list of PlayerStatistics and a QaurtiScoreResponse into a TeamStatGameResponse format.
     *
     * @param stat A list of PlayerStatistics objects. Each PlayerStatistics object represents the statistics of a player in a game.
     * @param qaurtiScoreResponse A QaurtiScoreResponse object representing the quarter scores of a game.
     * @return A TeamStatGameResponse object containing the team's game statistics and the players' statistics.
     * The TeamStatGameResponse object includes details such as the field goals made, field goals attempted, field goal percentage,
     * free throws made, free throws attempted, free throw percentage, three-pointers made, three-pointers attempted,
     * three-point percentage, offensive rebounds, defensive rebounds, total rebounds, assists, personal fouls, steals,
     * turnovers, blocks, plus-minus, and the players' statistics.
     * The method retrieves these details from the list of PlayerStatistics and sets them in the TeamStatGameResponse object.
     * It also sets the quarter scores in the TeamStatGameResponse object.
     */
    public TeamStatGameResponse toPartitaStatResponse(List<PlayerStatistics> stat, QaurtiScoreResponse qaurtiScoreResponse) {
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

        for (PlayerStatistics p : stat) {
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

    /**
     * This method converts a game's statistics into a response format.
     *
     * @param game The Games object whose statistics are to be converted.
     * @return A PartitaStatResponse object containing the game's statistics.
     * The PartitaStatResponse object includes details such as the calendar date response, home team statistics, and away team statistics.
     * The method retrieves the player statistics for the home and away teams of the game and converts them into a response format.
     * It also sets the quarter scores for the home and away teams in the response.
     * If no player statistics are found for the home or away team, the method returns a PartitaStatResponse object with the respective team's statistics set to null.
     */
    public PartitaStatResponse toPartitaStatResponse(Games game) {

        PartitaStatResponse partitaStatResponse = new PartitaStatResponse();

        partitaStatResponse.setCalendarioDateResponse(toCalendarioDateResponse(Collections.singletonList(game)).get(0));

        var stat = playerStatRepository.findByGameAndTeam(game, game.getHomeTeam());
        QaurtiScoreResponse qaurtiScoreResponse = new QaurtiScoreResponse();
        qaurtiScoreResponse.setQ1Score(game.getQ1h());
        qaurtiScoreResponse.setQ2Score(game.getQ2h());
        qaurtiScoreResponse.setQ3Score(game.getQ3h());
        qaurtiScoreResponse.setQ4Score(game.getQ4h());
        partitaStatResponse.setHomeTeam(toPartitaStatResponse(stat, qaurtiScoreResponse));


        stat = playerStatRepository.findByGameAndTeam(game, game.getAwayTeam());
        QaurtiScoreResponse qaurtiScoreResponse1 = new QaurtiScoreResponse();
        qaurtiScoreResponse1.setQ1Score(game.getQ1a());
        qaurtiScoreResponse1.setQ2Score(game.getQ2a());
        qaurtiScoreResponse1.setQ3Score(game.getQ3a());
        qaurtiScoreResponse1.setQ4Score(game.getQ4a());

        partitaStatResponse.setAwayTeam(toPartitaStatResponse(stat, qaurtiScoreResponse1));

        return partitaStatResponse;
    }

    /**
     * This method converts a list of games into a response format.
     *
     * @param games The list of Games objects to be converted. Each Games object represents a game.
     * @return A list of CalendarioDateResponse objects, each representing a game's details.
     * The CalendarioDateResponse object includes details such as the game's ID, start date, home team's details, and away team's details.
     * The home and away team's details include the team's name, conference, division, ID, all-star status, nickname, logo, city, code, and score.
     * The method retrieves the score for the home and away teams of the game and sets it in the CalendarioDateResponse object.
     * If no score is found for the home or away team, the method sets the respective team's score to null.
     * If the home or away team is null, the method sets the respective team's name to " ".
     */
    public List<CalendarioDateResponse> toCalendarioDateResponse(List<Games> games) {
        List<CalendarioDateResponse> calendarioResponseList = new ArrayList<>();
        for (Games game : games) {
            CalendarioDateResponse calendario = new CalendarioDateResponse();
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
            } else {
                calendario.setTeamHomeName(" ");
            }
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
                if (scoreRepository.findByGameAndTeam(game, game.getAwayTeam()) != null) {
                    calendario.setScoreTeamAway(scoreRepository.findByGameAndTeam(game, game.getAwayTeam()).getPoints());
                }
            } else {
                calendario.setTeamHomeName(" ");
            }
            calendarioResponseList.add(calendario);
        }
        return calendarioResponseList;
    }

    /**
     * This method converts a TeamStandings object into a TeamStandingsResponse format.
     *
     * @param team The TeamStandings object to be converted. It represents the standings of a team.
     * @return A TeamStandingsResponse object containing the team's standings.
     * The TeamStandingsResponse object includes details such as the team's details, season year, conference rank, conference win,
     * conference loss, division games behind, home win, away win, total win, win percentage, last ten win, home loss, away loss,
     * total loss, last ten loss, and games behind.
     * The method retrieves these details from the TeamStandings object and sets them in the TeamStandingsResponse object.
     */
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

    /**
     * This method converts a Player object into a PlayerResponse format.
     *
     * @param player The Player object to be converted. It represents a player's details.
     * @return A PlayerResponse object containing the player's details.
     * The PlayerResponse object includes details such as the player's ID, team, first name, last name, birth date, age, birth country,
     * NBA start year, NBA professional status, height (in feet, inches, and meters), weight (in pounds and kilograms), college, affiliation,
     * jersey number, total points, total assists, and position.
     * The method retrieves these details from the Player object and sets them in the PlayerResponse object.
     * It also calculates the player's age based on the current date and the player's birth date.
     * The method retrieves the player's statistics and sets the total points and assists in the PlayerResponse object.
     * If no player statistics are found, the method sets the player's position to "N/A".
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
        playerResponse.affiliation = player.getLastAffiliation();
        playerResponse.numeroMaglia = player.getNumeroMaglia();
        playerResponse.setFotoPng(player.getFoto1());
        playerResponse.setFotoAvif(player.getFoto2());
        playerResponse.setFotoWebp(player.getFoto3());

        List<PlayerStatistics> playerStatisticsList = playerStatRepository.findLast5Games(player.getId());

        System.out.println(playerStatisticsList.get(0).getPos());


        playerResponse.setPoints(sommaInt(playerStatisticsList.stream().map(PlayerStatistics::getPoints).toList()));
        playerResponse.setAssists(sommaInt(playerStatisticsList.stream().map(PlayerStatistics::getAssists).toList()));
        playerResponse.setStatistics(playerStatisticsList.stream().map(PlayerStatistics::toPlayerStatisticsResponse).toList() );
        if (playerStatisticsList.isEmpty()) {
            playerResponse.setPosizione("N/A");
        } else {
            playerResponse.setPosizione(playerStatisticsList.get(0).getPos());
        }
        return playerResponse;
    }


    /**
     * This method converts a Teams object into a TeamsResponse format.
     *
     * @param team The Teams object to be converted. It represents a team's details.
     * @return A TeamsResponse object containing the team's details.
     * The TeamsResponse object includes details such as the team's ID, team name, city, logo, nickname, all-star status, NBA franchise status,
     * conference, division (if available), and code.
     * The method retrieves these details from the Teams object and sets them in the TeamsResponse object.
     * If the team's division is null, the method does not include the division in the TeamsResponse object.
     */
    public TeamsResponse toTeamsResponse(Teams team) {

        if (team.getLeague().getDivision() == null) {
            return new TeamsResponse(
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

        }
        return new TeamsResponse(
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
    }

    /**
     * This method converts a Commenti object into a CommentiResponse format.
     *
     * @param commenti The Commenti object to be converted. It represents a comment's details.
     * @return A CommentiResponse object containing the comment's details.
     * The CommentiResponse object includes details such as the comment's ID, username of the user who made the comment, the text of the comment,
     * the day of the week when the comment was made, the day of the month when the comment was made, and the exact time when the comment was made.
     * The method retrieves these details from the Commenti object and sets them in the CommentiResponse object.
     * It also formats the date and time of the comment into a more readable format.
     */
    public CommentiResponse toCommentiResponse(Commenti commenti) {
        CommentiResponse commentiResponse = new CommentiResponse();
        commentiResponse.setId(commenti.getId());
        commentiResponse.setUsername(commenti.getIdUtente().getUsername());
        commentiResponse.setCommento(commenti.getTesto());

        LocalDateTime ldt = commenti.getData();
        commentiResponse.setGiorno(ldt.getDayOfWeek().toString());
        commentiResponse.setNGiorno(String.valueOf(ldt.getDayOfMonth()));
        commentiResponse.setOra(ldt.getHour() + ":" + ldt.getMinute() + ":" + ldt.getSecond());

        return commentiResponse;
    }

    /**
     * This utility method calculates the sum of all integers in a list.
     *
     * @param lista The list of integers to be summed up.
     * @return The sum of all integers in the list.
     * The method iterates through each integer in the list and adds it to a sum variable.
     * If the list is empty, the method returns 0.
     */
    public int sommaInt(List<Integer> lista) {
        int somma = 0;
        for (Integer integer : lista) {
            somma += integer;
        }
        return somma;
    }

    /**
     * This utility method calculates the sum of all doubles in a list.
     *
     * @param lista The list of doubles to be summed up.
     * @return The sum of all doubles in the list, rounded to the nearest whole number.
     * The method iterates through each double in the list and adds it to a sum variable.
     * If the list is empty, the method returns 0.
     */
    public double sommaDouble(List<Double> lista) {
        double somma = 0;
        for (Double integer : lista) {
            somma += integer;
        }
        return round(somma);
    }

    /**
     * This utility method converts a string representation of time into a double.
     *
     * @param stringa The string to be converted. It represents time in the format "hours:minutes".
     * @return A double representing the time in hours. The minutes are converted into a fraction of an hour.
     * The method splits the input string into hours and minutes using the ":" delimiter.
     * It then parses the hours and minutes into doubles and calculates the total time in hours.
     * If the input string does not contain a ":", the method will throw a NumberFormatException.
     */
    public double toDouble(String stringa) {
        String[] stringaArray = stringa.split(":");
        double numero = Double.parseDouble(stringaArray[0]) + (Double.parseDouble(stringaArray[1]) / 60);
        return numero;
    }


    /**
     * This method converts an array of Objects into an AmicoResponse object.
     *
     * @param utente An array of Objects where each element represents a property of the AmicoResponse.
     *               The array is expected to follow this order:
     *               utente[0] - seguito (Integer): The ID of the user being followed.
     *               utente[1] - username (String): The username of the user being followed.
     *               utente[2] - amico (Boolean): A flag indicating if the user is a friend.
     *               utente[3] - role (String): The role of the user being followed.
     *               utente[4] - follower (Long): The number of followers of the user.
     *               utente[5] - following (Long): The number of users that the user is following.
     * @return An AmicoResponse object containing the user's details.
     */
    public AmicoResponse toAmicoresponse(Object[] utente) {
        AmicoResponse amico = new AmicoResponse();
        amico.setSeguito((Integer) utente[0]);
        amico.setUsername((String) utente[1]);
        amico.setAmico((Boolean) utente[2]);
        amico.setRole((String) utente[3]);
        amico.setFollower((Long) utente[4]);
        amico.setFollowing((Long) utente[5]);
        return amico;
    }
}

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 05, 2024 alle 11:06
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nba`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `id` int(11) NOT NULL,
  `testo` text NOT NULL,
  `id_commento_padre` int(11) DEFAULT NULL,
  `id_utente` int(11) NOT NULL,
  `id_games` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `commenti`
--

INSERT INTO `commenti` (`id`, `testo`, `id_commento_padre`, `id_utente`, `id_games`) VALUES
(1, 'testo di prova', NULL, 1, 3),
(2, 'commento al commento prova', 1, 2, 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `conference`
--

CREATE TABLE `conference` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `conference`
--

INSERT INTO `conference` (`ID`, `name`) VALUES
(1, 'East'),
(2, 'West'),
(3, 'California'),
(4, 'Utah'),
(5, 'Summer League'),
(6, 'International');

-- --------------------------------------------------------

--
-- Struttura della tabella `division`
--

CREATE TABLE `division` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `division`
--

INSERT INTO `division` (`ID`, `name`) VALUES
(1, 'Atlantic'),
(2, 'Southeast'),
(3, 'Central'),
(4, 'Northwest'),
(5, 'Pacific'),
(6, 'Southwest'),
(7, 'Utah');

-- --------------------------------------------------------

--
-- Struttura della tabella `games`
--

CREATE TABLE `games` (
  `id` int(11) NOT NULL,
  `league_ID` int(11) NOT NULL,
  `season_ID` int(11) NOT NULL,
  `home_team` int(11) NOT NULL,
  `away_team` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime DEFAULT NULL,
  `stage` int(11) DEFAULT 0,
  `clock` time DEFAULT NULL,
  `halftime` tinyint(1) DEFAULT 0,
  `status` varchar(50) DEFAULT 'da iniziare',
  `current_period` int(11) DEFAULT 0,
  `total_periods` int(11) DEFAULT 0,
  `area_name` varchar(100) DEFAULT NULL,
  `arena_city` varchar(100) DEFAULT NULL,
  `arena_state` varchar(2) DEFAULT NULL,
  `arena_country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `games`
--

INSERT INTO `games` (`id`, `league_ID`, `season_ID`, `home_team`, `away_team`, `start_date`, `end_date`, `stage`, `clock`, `halftime`, `status`, `current_period`, `total_periods`, `area_name`, `arena_city`, `arena_state`, `arena_country`) VALUES
(3, 1, 1, 1, 2, '2023-12-24 00:59:55', NULL, 0, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL),
(4, 1, 1, 2, 1, '2023-12-24 00:59:55', NULL, 0, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL),
(5, 1, 1, 2, 4, '2023-12-24 00:59:55', NULL, 0, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL),
(6, 1, 1, 4, 1, '2023-12-24 00:59:55', NULL, 0, NULL, 0, NULL, 0, 0, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `league`
--

CREATE TABLE `league` (
  `id` int(11) NOT NULL,
  `conference_id` int(11) DEFAULT NULL,
  `division_id` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `league`
--

INSERT INTO `league` (`id`, `conference_id`, `division_id`, `name`) VALUES
(1, 1, 1, 'East-Atlantic'),
(2, 1, 2, 'East-Southeast'),
(3, 6, NULL, 'Internetional\r\n');

-- --------------------------------------------------------

--
-- Struttura della tabella `player`
--

CREATE TABLE `player` (
  `id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `birth_country` varchar(100) NOT NULL,
  `nba_start` year(4) NOT NULL,
  `nba_pro` int(11) NOT NULL,
  `height_feet` int(11) NOT NULL,
  `height_inches` int(11) NOT NULL,
  `height_meters` decimal(10,2) NOT NULL,
  `weight_pounds` int(11) NOT NULL,
  `weight_kg` decimal(10,2) NOT NULL,
  `college` varchar(100) NOT NULL,
  `affiliation` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `player`
--

INSERT INTO `player` (`id`, `team_id`, `first_name`, `last_name`, `birth_date`, `birth_country`, `nba_start`, `nba_pro`, `height_feet`, `height_inches`, `height_meters`, `weight_pounds`, `weight_kg`, `college`, `affiliation`) VALUES
(1, 1, 'Richaun', 'Holmes', '1993-10-15', 'USA', '2015', 6, 6, 10, 2.08, 235, 106.60, 'Bowling Green', 'Bowling Green/USA'),
(382, 1, 'Dejounte', 'Murray', '1996-09-19', 'USA', '2016', 4, 6, 4, 1.93, 180, 81.60, 'Washington', 'Washington/USA');

-- --------------------------------------------------------

--
-- Struttura della tabella `player_statistics`
--

CREATE TABLE `player_statistics` (
  `player_ID` int(11) NOT NULL,
  `teams_ID` int(11) NOT NULL,
  `games_ID` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `pos` varchar(2) NOT NULL,
  `min` time NOT NULL,
  `fgm` int(11) NOT NULL,
  `fga` int(11) NOT NULL,
  `fgp` decimal(3,2) NOT NULL,
  `ftm` int(11) NOT NULL,
  `fta` int(11) NOT NULL,
  `ftp` decimal(3,2) NOT NULL,
  `tpm` int(11) NOT NULL,
  `tpa` int(11) NOT NULL,
  `tpp` decimal(3,2) NOT NULL,
  `off_reb` int(11) NOT NULL,
  `def_reb` int(11) NOT NULL,
  `tot_reb` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `p_fouls` int(11) NOT NULL,
  `steals` int(11) NOT NULL,
  `turnovers` int(11) NOT NULL,
  `blocks` int(11) NOT NULL,
  `plus_minus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struttura della tabella `scores`
--

CREATE TABLE `scores` (
  `games_ID` int(11) NOT NULL,
  `teams_ID` int(11) NOT NULL,
  `win` int(11) NOT NULL,
  `loss` int(11) NOT NULL,
  `series_win` int(11) NOT NULL,
  `series_loss` int(11) NOT NULL,
  `q1` int(11) NOT NULL,
  `q2` int(11) NOT NULL,
  `q3` int(11) NOT NULL,
  `q4` int(11) NOT NULL,
  `ot1` int(11) NOT NULL,
  `ot2` int(11) NOT NULL,
  `ot3` int(11) NOT NULL,
  `ot4` int(11) NOT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `scores`
--

INSERT INTO `scores` (`games_ID`, `teams_ID`, `win`, `loss`, `series_win`, `series_loss`, `q1`, `q2`, `q3`, `q4`, `ot1`, `ot2`, `ot3`, `ot4`, `points`) VALUES
(3, 1, 1, 0, 5, 3, 30, 30, 30, 30, 30, 30, 30, 30, 240),
(3, 2, 1, 0, 5, 3, 20, 30, 30, 30, 30, 30, 30, 30, 230),
(4, 1, 1, 0, 5, 3, 30, 30, 30, 30, 30, 30, 30, 30, 250),
(4, 2, 1, 0, 5, 3, 20, 30, 30, 30, 30, 30, 30, 30, 230),
(5, 2, 1, 0, 5, 3, 20, 30, 30, 30, 30, 30, 30, 30, 30),
(5, 4, 1, 0, 5, 3, 20, 30, 30, 30, 30, 30, 30, 30, 40),
(6, 1, 1, 0, 5, 3, 30, 30, 30, 30, 30, 30, 30, 30, 290),
(6, 4, 1, 0, 5, 3, 30, 30, 30, 30, 30, 30, 30, 30, 80);

-- --------------------------------------------------------

--
-- Struttura della tabella `season`
--

CREATE TABLE `season` (
  `ID` int(11) NOT NULL,
  `year` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `season`
--

INSERT INTO `season` (`ID`, `year`) VALUES
(1, 2023);

-- --------------------------------------------------------

--
-- Struttura della tabella `teams`
--

CREATE TABLE `teams` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `code` varchar(3) NOT NULL,
  `city` varchar(100) NOT NULL,
  `logo` varchar(500) NOT NULL,
  `all_star` tinyint(1) NOT NULL,
  `nba_franchise` tinyint(1) NOT NULL,
  `league_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `teams`
--

INSERT INTO `teams` (`id`, `name`, `nickname`, `code`, `city`, `logo`, `all_star`, `nba_franchise`, `league_id`) VALUES
(1, 'Atlanta Hawks', 'Hawks', 'ATL', 'Atlanta', 'https://upload.wikimedia.org/wikipedia/fr/e/ee/Hawks_2016.png', 0, 1, 2),
(2, 'Boston Celtics', 'Celtics', 'BOS', 'Boston', 'https://upload.wikimedia.org/wikipedia/fr/thumb/6/65/Celtics_de_Boston_logo.svg/1024px-Celtics_de_Boston_logo.svg.png', 0, 1, 1),
(3, 'Brisbane Bullets', 'Bullets', 'BNE', 'Brisbane', 'https://upload.wikimedia.org/wikipedia/fr/thumb/1/1b/Brisbane_Bullets_1992.png/130px-Brisbane_Bullets_1992.png', 0, 0, 3),
(4, 'Brooklyn Nets', 'Nets', 'BKN', 'Brooklyn', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Brooklyn_Nets_newlogo.svg/130px-Brooklyn_Nets_newlogo.svg.png', 0, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `teams_statistics`
--

CREATE TABLE `teams_statistics` (
  `team_ID` int(11) NOT NULL,
  `season_ID` int(11) NOT NULL,
  `games` int(11) NOT NULL,
  `fast_break_points` int(11) NOT NULL,
  `points_in_paint` int(11) NOT NULL,
  `biggest_lead` int(11) NOT NULL,
  `second_chance_points` int(11) NOT NULL,
  `points_off_turnover` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `fgm` int(11) NOT NULL,
  `fga` int(11) NOT NULL,
  `fgp` decimal(4,1) NOT NULL,
  `ftm` int(11) NOT NULL,
  `fta` int(11) NOT NULL,
  `ftp` decimal(4,1) NOT NULL,
  `tpm` int(11) NOT NULL,
  `tpa` int(11) NOT NULL,
  `tpp` decimal(4,1) NOT NULL,
  `off_reb` int(11) NOT NULL,
  `def_reb` int(11) NOT NULL,
  `tot_reb` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `p_fouls` int(11) NOT NULL,
  `steals` int(11) NOT NULL,
  `turnovers` int(11) NOT NULL,
  `blocks` int(11) NOT NULL,
  `plus_minus` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `teams_statistics`
--

INSERT INTO `teams_statistics` (`team_ID`, `season_ID`, `games`, `fast_break_points`, `points_in_paint`, `biggest_lead`, `second_chance_points`, `points_off_turnover`, `points`, `fgm`, `fga`, `fgp`, `ftm`, `fta`, `ftp`, `tpm`, `tpa`, `tpp`, `off_reb`, `def_reb`, `tot_reb`, `assists`, `p_fouls`, `steals`, `turnovers`, `blocks`, `plus_minus`) VALUES
(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1.2, 1, 1, 1.5, 1, 1, 5.3, 1, 1, 1, 1, 1, 1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `team_standings`
--

CREATE TABLE `team_standings` (
  `team_ID` int(11) NOT NULL,
  `season_ID` int(11) NOT NULL,
  `conference_name` varchar(10) NOT NULL,
  `conference_rank` int(11) NOT NULL,
  `conference_win` int(11) NOT NULL,
  `conference_loss` int(11) NOT NULL,
  `division_games_behind` decimal(4,1) NOT NULL,
  `home_win` int(11) NOT NULL,
  `away_win` int(11) NOT NULL,
  `total_win` int(11) NOT NULL,
  `win_percentage` decimal(4,3) NOT NULL,
  `last_ten_win` int(11) NOT NULL,
  `home_loss` int(11) NOT NULL,
  `away_loss` int(11) NOT NULL,
  `total_loss` int(11) NOT NULL,
  `loss_percentage` decimal(4,3) NOT NULL,
  `last_ten_loss` int(11) NOT NULL,
  `games_behind` decimal(4,1) NOT NULL,
  `streak` int(11) NOT NULL,
  `is_win_streak` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `team_standings`
--

INSERT INTO `team_standings` (`team_ID`, `season_ID`, `conference_name`, `conference_rank`, `conference_win`, `conference_loss`, `division_games_behind`, `home_win`, `away_win`, `total_win`, `win_percentage`, `last_ten_win`, `home_loss`, `away_loss`, `total_loss`, `loss_percentage`, `last_ten_loss`, `games_behind`, `streak`, `is_win_streak`) VALUES
(1, 1, 'East', 2, 12, 3, 0.0, 9, 5, 12, 8.500, 5, 3, 1, 3, 4.000, 1, 3.0, 4, 1),
(2, 1, 'East', 3, 12, 3, 0.0, 9, 5, 12, 8.300, 5, 3, 1, 3, 4.000, 1, 3.0, 4, 1),
(4, 1, 'Weast', 1, 12, 3, 0.0, 9, 5, 12, 8.700, 5, 3, 1, 3, 4.000, 1, 3.0, 4, 1);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_commento_padre` (`id_commento_padre`),
  ADD KEY `id_games` (`id_games`);

--
-- Indici per le tabelle `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `division`
--
ALTER TABLE `division`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `games`
--
ALTER TABLE `games`
  ADD PRIMARY KEY (`id`),
  ADD KEY `league_ID` (`league_ID`),
  ADD KEY `season_ID` (`season_ID`),
  ADD KEY `home_team` (`home_team`),
  ADD KEY `away_team` (`away_team`);

--
-- Indici per le tabelle `league`
--
ALTER TABLE `league`
  ADD PRIMARY KEY (`id`),
  ADD KEY `conference_ID` (`conference_id`),
  ADD KEY `division_ID` (`division_id`);

--
-- Indici per le tabelle `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`id`),
  ADD KEY `team_id` (`team_id`);

--
-- Indici per le tabelle `player_statistics`
--
ALTER TABLE `player_statistics`
  ADD PRIMARY KEY (`player_ID`,`teams_ID`,`games_ID`),
  ADD KEY `games_ID` (`games_ID`),
  ADD KEY `teams_ID` (`teams_ID`);

--
-- Indici per le tabelle `scores`
--
ALTER TABLE `scores`
  ADD PRIMARY KEY (`games_ID`,`teams_ID`),
  ADD KEY `teams_ID` (`teams_ID`);

--
-- Indici per le tabelle `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`),
  ADD KEY `league_id` (`league_id`);

--
-- Indici per le tabelle `teams_statistics`
--
ALTER TABLE `teams_statistics`
  ADD PRIMARY KEY (`team_ID`,`season_ID`),
  ADD KEY `season_ID` (`season_ID`);

--
-- Indici per le tabelle `team_standings`
--
ALTER TABLE `team_standings`
  ADD PRIMARY KEY (`team_ID`,`season_ID`),
  ADD KEY `season_ID` (`season_ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `conference`
--
ALTER TABLE `conference`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `division`
--
ALTER TABLE `division`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT per la tabella `games`
--
ALTER TABLE `games`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `league`
--
ALTER TABLE `league`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT per la tabella `player`
--
ALTER TABLE `player`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=383;

--
-- AUTO_INCREMENT per la tabella `season`
--
ALTER TABLE `season`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `teams`
--
ALTER TABLE `teams`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `commenti`
--
ALTER TABLE `commenti`
  ADD CONSTRAINT `commenti_ibfk_1` FOREIGN KEY (`id_commento_padre`) REFERENCES `commenti` (`id`),
  ADD CONSTRAINT `commenti_ibfk_2` FOREIGN KEY (`id_games`) REFERENCES `games` (`ID`);

--
-- Limiti per la tabella `games`
--
ALTER TABLE `games`
  ADD CONSTRAINT `games_ibfk_1` FOREIGN KEY (`league_ID`) REFERENCES `league` (`ID`),
  ADD CONSTRAINT `games_ibfk_2` FOREIGN KEY (`season_ID`) REFERENCES `season` (`ID`),
  ADD CONSTRAINT `games_ibfk_3` FOREIGN KEY (`home_team`) REFERENCES `teams` (`ID`),
  ADD CONSTRAINT `games_ibfk_4` FOREIGN KEY (`away_team`) REFERENCES `teams` (`ID`);

--
-- Limiti per la tabella `league`
--
ALTER TABLE `league`
  ADD CONSTRAINT `league_ibfk_1` FOREIGN KEY (`conference_id`) REFERENCES `conference` (`ID`),
  ADD CONSTRAINT `league_ibfk_2` FOREIGN KEY (`division_id`) REFERENCES `division` (`ID`);

--
-- Limiti per la tabella `player`
--
ALTER TABLE `player`
  ADD CONSTRAINT `player_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `teams` (`ID`);

--
-- Limiti per la tabella `player_statistics`
--
ALTER TABLE `player_statistics`
  ADD CONSTRAINT `player_statistics_ibfk_1` FOREIGN KEY (`games_ID`) REFERENCES `games` (`ID`),
  ADD CONSTRAINT `player_statistics_ibfk_3` FOREIGN KEY (`teams_ID`) REFERENCES `teams` (`ID`),
  ADD CONSTRAINT `player_statistics_ibfk_4` FOREIGN KEY (`player_ID`) REFERENCES `player` (`id`);

--
-- Limiti per la tabella `scores`
--
ALTER TABLE `scores`
  ADD CONSTRAINT `scores_ibfk_1` FOREIGN KEY (`games_ID`) REFERENCES `games` (`ID`),
  ADD CONSTRAINT `scores_ibfk_2` FOREIGN KEY (`teams_ID`) REFERENCES `teams` (`ID`);

--
-- Limiti per la tabella `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `teams_ibfk_1` FOREIGN KEY (`league_id`) REFERENCES `league` (`ID`);

--
-- Limiti per la tabella `teams_statistics`
--
ALTER TABLE `teams_statistics`
  ADD CONSTRAINT `teams_statistics_ibfk_1` FOREIGN KEY (`team_ID`) REFERENCES `teams` (`ID`),
  ADD CONSTRAINT `teams_statistics_ibfk_2` FOREIGN KEY (`season_ID`) REFERENCES `season` (`ID`);

--
-- Limiti per la tabella `team_standings`
--
ALTER TABLE `team_standings`
  ADD CONSTRAINT `team_standings_ibfk_1` FOREIGN KEY (`season_ID`) REFERENCES `season` (`ID`),
  ADD CONSTRAINT `team_standings_ibfk_2` FOREIGN KEY (`team_ID`) REFERENCES `teams` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
Use nba;

CREATE TABLE IF NOT EXISTS `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` varchar(255) NOT NULL,
  `riassunto` varchar(255) NOT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `creazione` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `numero_foto` int(11) NOT NULL,
  `tipo` varchar(11) NOT NULL,
  `colori` varchar(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `commenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `testo` text NOT NULL,
  `id_commento_padre` int(11) DEFAULT NULL,
  `id_utente` int(11) NOT NULL,
  `id_games` int(11) DEFAULT NULL,
  `blog` int(11) DEFAULT NULL,
  `creazione` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `id_commento_padre` (`id_commento_padre`),
  KEY `id_games` (`id_games`),
  KEY `blog` (`blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `conference` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `division` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `games` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `arena_state` varchar(20) DEFAULT NULL,
  `arena_country` varchar(50) DEFAULT NULL,
  `q1h` int(11) NOT NULL,
  `q2h` int(11) NOT NULL,
  `q3h` int(11) NOT NULL,
  `q4h` int(11) NOT NULL,
  `q1a` int(11) NOT NULL,
  `q2a` int(11) NOT NULL,
  `q3a` int(11) NOT NULL,
  `q4a` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `away_team` (`away_team`),
  KEY `home_team` (`home_team`),
  KEY `season_ID` (`season_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `league` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `conference_id` int(11) DEFAULT NULL,
  `division_id` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conference_ID` (`conference_id`),
  KEY `division_ID` (`division_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `paragrafo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_blog` int(11) NOT NULL,
  `titolo_paragrafo` varchar(255) DEFAULT NULL,
  `testo_paragrafo` text DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_blog` (`id_blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `birth_country` varchar(100) DEFAULT NULL,
  `nba_start` year(4) DEFAULT NULL,
  `nba_pro` int(11) DEFAULT NULL,
  `height_feet` int(11) DEFAULT NULL,
  `height_inches` int(11) DEFAULT NULL,
  `height_meters` decimal(10,2) DEFAULT NULL,
  `weight_pounds` int(11) DEFAULT NULL,
  `weight_kg` decimal(10,2) DEFAULT NULL,
  `college` varchar(100) DEFAULT NULL,
  `affiliation` varchar(100) DEFAULT NULL,
  `numero_maglia` int(11) NOT NULL,
  `foto1` varchar(255) DEFAULT NULL,
  `foto2` varchar(255) DEFAULT NULL,
  `foto3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `team_id` (`team_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `player_statistics` (
  `player_ID` int(11) NOT NULL,
  `teams_id` int(11) NOT NULL,
  `game_id` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  `pos` varchar(2) NOT NULL,
  `min` time NOT NULL,
  `fgm` int(11) NOT NULL,
  `fga` int(11) NOT NULL,
  `fgp` double NOT NULL,
  `ftm` int(11) NOT NULL,
  `fta` int(11) NOT NULL,
  `ftp` double NOT NULL,
  `tpm` int(11) NOT NULL,
  `tpa` int(11) NOT NULL,
  `tpp` double NOT NULL,
  `off_reb` int(11) NOT NULL,
  `def_reb` int(11) NOT NULL,
  `tot_reb` int(11) NOT NULL,
  `assists` int(11) NOT NULL,
  `p_fouls` int(11) NOT NULL,
  `steals` int(11) NOT NULL,
  `turnovers` int(11) NOT NULL,
  `blocks` int(11) NOT NULL,
  `plus_minus` int(11) NOT NULL,
  PRIMARY KEY (`player_ID`,`teams_id`,`game_id`),
  KEY `games_ID` (`game_id`),
  KEY `teams_ID` (`teams_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `role` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `scores` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `games_ID` int(11) NOT NULL,
  `teams_ID` int(11) NOT NULL,
  `win` int(11) NOT NULL,
  `loss` int(11) NOT NULL,
  `series_win` int(11) NOT NULL,
  `series_loss` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `games_ID` (`games_ID`),
  KEY `teams_ID` (`teams_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `season` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `seguiti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seguito` int(11) NOT NULL,
  `seguace` int(11) NOT NULL,
  `amico` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `seguace` (`seguace`),
  KEY `seguito` (`seguito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `sondaggio` (
  `utente` int(11) NOT NULL,
  `partita` int(11) NOT NULL,
  `scommessa` int(11) NOT NULL,
  PRIMARY KEY (`utente`,`partita`),
  KEY `scommessa` (`scommessa`),
  KEY `partita` (`partita`),
  KEY `utente` (`utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag` varchar(69) NOT NULL,
  `blog` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `blog` (`blog`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `code` varchar(3) NOT NULL,
  `city` varchar(100) NOT NULL,
  `logo` varchar(500) NOT NULL,
  `all_star` tinyint(1) NOT NULL,
  `nba_franchise` tinyint(1) NOT NULL,
  `league_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `league_id` (`league_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `teams_statistics` (
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
  `plus_minus` int(11) NOT NULL,
  PRIMARY KEY (`team_ID`,`season_ID`),
  KEY `season_ID` (`season_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `team_standings` (
  `team_ID` int(11) NOT NULL,
  `season_ID` int(11) NOT NULL,
  `conference_name` varchar(10) NOT NULL,
  `conference_rank` int(11) NOT NULL,
  `conference_win` int(11) NOT NULL,
  `conference_loss` int(11) NOT NULL,
  `division_games_behind` decimal(4,1) DEFAULT NULL,
  `home_win` int(11) NOT NULL,
  `away_win` int(11) NOT NULL,
  `total_win` int(11) NOT NULL,
  `win_percentage` decimal(4,3) DEFAULT NULL,
  `last_ten_win` int(11) NOT NULL,
  `home_loss` int(11) NOT NULL,
  `away_loss` int(11) NOT NULL,
  `total_loss` int(11) NOT NULL,
  `loss_percentage` decimal(4,3) DEFAULT NULL,
  `last_ten_loss` int(11) NOT NULL,
  `games_behind` decimal(4,1) DEFAULT NULL,
  `streak` int(11) NOT NULL,
  `is_win_streak` tinyint(1) NOT NULL,
  PRIMARY KEY (`team_ID`,`season_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `birth_date` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `pswd` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT 2,
  `data_iscrizione` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `numero_telefono` varchar(14) NOT NULL,
  `follower` int(11) NOT NULL DEFAULT 0,
  `username` varchar(255) NOT NULL,
  `sesso` varchar(255) NOT NULL,
  `seguiti` int(11) DEFAULT NULL,
  `iscrizione` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `utente_preferiti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente` int(11) NOT NULL,
  `team` int(11) DEFAULT NULL,
  `articolo` int(11) DEFAULT NULL,
  `data` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `team` (`team`),
  KEY `utente` (`utente`),
  KEY `articolo` (`articolo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


ALTER TABLE `commenti`
  ADD CONSTRAINT IF NOT EXISTS `commenti_ibfk_1` FOREIGN KEY (`id_games`) REFERENCES `games` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `commenti_ibfk_2` FOREIGN KEY (`blog`) REFERENCES `blog` (`id`);

ALTER TABLE `games`
  ADD CONSTRAINT IF NOT EXISTS `games_ibfk_1` FOREIGN KEY (`away_team`) REFERENCES `teams` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `games_ibfk_2` FOREIGN KEY (`home_team`) REFERENCES `teams` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `games_ibfk_3` FOREIGN KEY (`season_ID`) REFERENCES `season` (`id`);

ALTER TABLE `paragrafo`
  ADD CONSTRAINT IF NOT EXISTS `paragrafo_ibfk_1` FOREIGN KEY (`id_blog`) REFERENCES `blog` (`id`);

ALTER TABLE `seguiti`
  ADD CONSTRAINT IF NOT EXISTS `seguiti_ibfk_1` FOREIGN KEY (`seguace`) REFERENCES `utente` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `seguiti_ibfk_2` FOREIGN KEY (`seguito`) REFERENCES `utente` (`id`);

ALTER TABLE `sondaggio`
  ADD CONSTRAINT IF NOT EXISTS `sondaggio_ibfk_1` FOREIGN KEY (`scommessa`) REFERENCES `teams` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `sondaggio_ibfk_2` FOREIGN KEY (`utente`) REFERENCES `utente` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `sondaggio_ibfk_3` FOREIGN KEY (`partita`) REFERENCES `games` (`id`);

ALTER TABLE `tag`
  ADD CONSTRAINT IF NOT EXISTS `tag_ibfk_1` FOREIGN KEY (`blog`) REFERENCES `blog` (`id`);

ALTER TABLE `utente_preferiti`
  ADD CONSTRAINT IF NOT EXISTS `utente_preferiti_ibfk_1` FOREIGN KEY (`team`) REFERENCES `teams` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `utente_preferiti_ibfk_2` FOREIGN KEY (`utente`) REFERENCES `utente` (`id`),
  ADD CONSTRAINT IF NOT EXISTS `utente_preferiti_ibfk_3` FOREIGN KEY (`articolo`) REFERENCES `blog` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

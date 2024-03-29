-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 09, 2024 alle 22:34
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

--
-- Dump dei dati per la tabella `team_standings`
--

INSERT INTO `team_standings` (`team_ID`, `season_ID`, `conference_name`, `conference_rank`, `conference_win`, `conference_loss`, `division_games_behind`, `home_win`, `away_win`, `total_win`, `win_percentage`, `last_ten_win`, `home_loss`, `away_loss`, `total_loss`, `loss_percentage`, `last_ten_loss`, `games_behind`, `streak`, `is_win_streak`) VALUES
(1, 1, 'east', 10, 14, 19, 5.5, 5, 9, 14, 0.424, 5, 9, 10, 19, 0.576, 0, 5.5, 2, 0),
(2, 1, 'east', 1, 26, 7, 0.0, 16, 10, 26, 0.788, 8, 0, 7, 7, 0.212, 0, 0.0, 1, 0),
(4, 1, 'east', 9, 15, 20, 12.0, 9, 6, 15, 0.429, 2, 8, 12, 20, 0.571, 0, 12.0, 5, 0),
(5, 1, 'east', 13, 8, 24, 11.0, 4, 4, 8, 0.250, 1, 11, 13, 24, 0.750, 0, 11.0, 1, 0),
(6, 1, 'east', 11, 15, 21, 10.5, 11, 4, 15, 0.417, 5, 9, 12, 21, 0.583, 0, 10.5, 2, 0),
(7, 1, 'east', 8, 19, 15, 5.5, 10, 9, 19, 0.559, 6, 8, 7, 15, 0.441, 0, 5.5, 1, 0),
(8, 1, 'west', 7, 20, 15, 1.0, 9, 11, 20, 0.571, 4, 7, 8, 15, 0.429, 0, 1.0, 1, 0),
(9, 1, 'west', 3, 25, 11, 0.5, 14, 11, 25, 0.694, 8, 3, 8, 11, 0.306, 0, 0.5, 2, 0),
(10, 1, 'east', 15, 3, 31, 21.5, 2, 1, 3, 0.088, 1, 14, 17, 31, 0.912, 0, 21.5, 2, 0),
(11, 1, 'west', 11, 16, 18, 5.5, 10, 6, 16, 0.471, 6, 9, 9, 18, 0.529, 0, 5.5, 1, 0),
(14, 1, 'west', 8, 17, 15, 2.5, 14, 3, 17, 0.531, 4, 5, 10, 15, 0.469, 0, 2.5, 2, 0),
(15, 1, 'east', 5, 19, 14, 5.0, 10, 9, 19, 0.576, 6, 7, 7, 14, 0.424, 0, 5.0, 5, 0),
(16, 1, 'west', 4, 21, 12, 0.0, 14, 7, 21, 0.636, 8, 4, 8, 12, 0.364, 0, 0.0, 4, 0),
(17, 1, 'west', 10, 17, 18, 5.0, 11, 6, 17, 0.486, 2, 5, 13, 18, 0.514, 0, 5.0, 3, 0),
(19, 1, 'west', 13, 11, 23, 9.5, 3, 8, 11, 0.324, 5, 13, 10, 23, 0.676, 0, 9.5, 1, 0),
(20, 1, 'east', 4, 20, 14, 0.0, 9, 11, 20, 0.588, 6, 6, 8, 14, 0.412, 0, 0.0, 1, 0),
(21, 1, 'east', 2, 25, 10, 0.0, 16, 9, 25, 0.714, 7, 3, 7, 10, 0.286, 0, 0.0, 1, 0),
(22, 1, 'west', 1, 24, 9, 0.0, 14, 10, 24, 0.727, 6, 2, 7, 9, 0.273, 0, 0.0, 2, 0),
(23, 1, 'west', 6, 21, 14, 0.0, 12, 9, 21, 0.600, 7, 7, 7, 14, 0.400, 0, 0.0, 4, 0),
(24, 1, 'east', 6, 19, 15, 7.5, 10, 9, 19, 0.559, 5, 4, 11, 15, 0.441, 0, 7.5, 2, 0),
(25, 1, 'west', 2, 23, 10, 1.0, 14, 9, 23, 0.697, 8, 5, 5, 10, 0.303, 0, 1.0, 1, 0),
(26, 1, 'east', 7, 19, 15, 1.0, 12, 7, 19, 0.559, 3, 4, 11, 15, 0.441, 0, 1.0, 3, 0),
(27, 1, 'east', 3, 23, 10, 3.0, 13, 10, 23, 0.697, 7, 4, 6, 10, 0.303, 0, 3.0, 1, 0),
(28, 1, 'west', 9, 18, 16, 3.5, 10, 8, 18, 0.529, 5, 10, 6, 16, 0.471, 0, 3.5, 1, 0),
(29, 1, 'west', 14, 9, 24, 15.0, 5, 4, 9, 0.273, 3, 11, 13, 24, 0.727, 0, 15.0, 2, 0),
(30, 1, 'west', 5, 20, 13, 1.0, 12, 8, 20, 0.606, 6, 6, 7, 13, 0.394, 0, 1.0, 1, 0),
(31, 1, 'west', 15, 5, 29, 15.5, 2, 3, 5, 0.147, 1, 15, 14, 29, 0.853, 0, 15.5, 4, 0),
(38, 1, 'east', 12, 14, 20, 12.5, 9, 5, 14, 0.412, 4, 9, 11, 20, 0.588, 0, 12.5, 2, 0),
(40, 1, 'west', 12, 16, 19, 9.0, 11, 5, 16, 0.457, 7, 5, 14, 19, 0.543, 0, 9.0, 3, 0),
(41, 1, 'east', 14, 6, 27, 13.5, 3, 3, 6, 0.182, 3, 11, 16, 27, 0.818, 0, 13.5, 2, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

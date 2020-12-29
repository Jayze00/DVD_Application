-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 29. Dez 2020 um 01:34
-- Server-Version: 10.4.14-MariaDB
-- PHP-Version: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `dvd_application`
--
CREATE DATABASE IF NOT EXISTS `dvd_application` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `dvd_application`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_collection`
--

DROP TABLE IF EXISTS `tbl_collection`;
CREATE TABLE IF NOT EXISTS `tbl_collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collection_name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` int(11) NOT NULL,
  `completed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_collection_dvd_mapping`
--

DROP TABLE IF EXISTS `tbl_collection_dvd_mapping`;
CREATE TABLE IF NOT EXISTS `tbl_collection_dvd_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_collection` int(11) NOT NULL,
  `fk_dvd` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_collection` (`fk_collection`),
  KEY `fk_dvd` (`fk_dvd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_dvd`
--

DROP TABLE IF EXISTS `tbl_dvd`;
CREATE TABLE IF NOT EXISTS `tbl_dvd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `release_date` date NOT NULL,
  `trailer_link` text COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cover` blob DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_dvd_genre_mapping`
--

DROP TABLE IF EXISTS `tbl_dvd_genre_mapping`;
CREATE TABLE IF NOT EXISTS `tbl_dvd_genre_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fk_dvd` int(11) NOT NULL,
  `fk_genre` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dvd` (`fk_dvd`),
  KEY `fk_genre` (`fk_genre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tbl_genre`
--

DROP TABLE IF EXISTS `tbl_genre`;
CREATE TABLE IF NOT EXISTS `tbl_genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `tbl_collection_dvd_mapping`
--
ALTER TABLE `tbl_collection_dvd_mapping`
  ADD CONSTRAINT `tbl_collection_dvd_mapping_ibfk_1` FOREIGN KEY (`fk_dvd`) REFERENCES `tbl_dvd` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_collection_dvd_mapping_ibfk_2` FOREIGN KEY (`fk_collection`) REFERENCES `tbl_collection` (`id`) ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `tbl_dvd_genre_mapping`
--
ALTER TABLE `tbl_dvd_genre_mapping`
  ADD CONSTRAINT `tbl_dvd_genre_mapping_ibfk_1` FOREIGN KEY (`fk_genre`) REFERENCES `tbl_genre` (`id`) ON UPDATE NO ACTION,
  ADD CONSTRAINT `tbl_dvd_genre_mapping_ibfk_2` FOREIGN KEY (`fk_dvd`) REFERENCES `tbl_dvd` (`id`) ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

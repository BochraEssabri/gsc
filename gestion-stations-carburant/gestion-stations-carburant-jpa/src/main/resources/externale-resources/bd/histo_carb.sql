

-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 17 déc. 2020 à 00:59
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gsc`
--

-- --------------------------------------------------------

--
-- Structure de la table `histo_carb`
--

DROP TABLE IF EXISTS `histo_carb`;
CREATE TABLE IF NOT EXISTS `histo_carb` (

  `id_histo_carb` int(11) NOT NULL AUTO_INCREMENT,
  `id_station` int(11) NOT NULL,
  `id_carburant` int(11) NOT NULL,
  `date` date NOT NULL,
  `prix` decimal(20,4) DEFAULT NULL,
  
  PRIMARY KEY (`id_histo_carb`),
  
  UNIQUE(`id_station`, `id_carburant`,  `date`)
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `histo_carb`
--
ALTER TABLE `histo_carb`
  ADD CONSTRAINT `histo_carb_ibfk_1` FOREIGN KEY (`id_station`) REFERENCES `station` (`id_station`) ON DELETE CASCADE,
  ADD CONSTRAINT `histo_carb_ibfk_2` FOREIGN KEY (`id_carburant`) REFERENCES `carburant` (`id_carburant`) ON DELETE CASCADE;
COMMIT;

--
-- Déchargement des données de la table `histo_carb`
--

INSERT INTO `histo_carb` (`id_histo_carb`, `id_station`, `id_carburant`, `date`, `prix`) VALUES
(1, 1, 1, '2020-12-14', '9.3000'),
(2, 1, 1, '2020-12-15', '9.4000'),
(3, 1, 1, '2020-12-16', '9.4500'),
(4, 1, 1, '2020-12-17', '9.5500'),
(5, 1, 3, '2020-12-14', '9.0000'),
(6, 1, 3, '2020-12-15', '9.2000'),
(7, 1, 3, '2020-12-16', '9.2500'),
(8, 1, 3, '2020-12-17', '9.3500'),
(9, 2, 1, '2020-12-14', '9.3000'),
(10, 2, 1, '2020-12-15', '9.4000'),
(11, 2, 1, '2020-12-16', '9.4500'),
(12, 2, 1, '2020-12-17', '9.5500'),
(13, 2, 2, '2020-12-14', '8.3000'),
(14, 2, 2, '2020-12-15', '8.4000'),
(15, 2, 2, '2020-12-16', '8.4500'),
(16, 2, 2, '2020-12-17', '8.5500'),
(17, 2, 3, '2020-12-14', '9.1000'),
(18, 2, 3, '2020-12-15', '9.2000'),
(19, 2, 3, '2020-12-16', '9.2500'),
(20, 2, 3, '2020-12-17', '9.2500'),
(21, 3, 1, '2020-12-14', '9.2000'),
(22, 3, 1, '2020-12-15', '9.3500'),
(23, 3, 1, '2020-12-16', '9.4000'),
(24, 3, 1, '2020-12-17', '9.5500'),
(25, 3, 2, '2020-12-14', '8.2000'),
(26, 3, 2, '2020-12-15', '8.3500'),
(27, 3, 2, '2020-12-16', '8.4000'),
(28, 3, 2, '2020-12-17', '8.5500'),
(29, 3, 3, '2020-12-14', '9.0000'),
(30, 3, 3, '2020-12-15', '9.1500'),
(31, 3, 3, '2020-12-16', '9.2000'),
(32, 3, 3, '2020-12-17', '9.3500'),
(33, 4, 1, '2020-12-14', '9.5500'),
(34, 4, 1, '2020-12-15', '9.4500'),
(35, 4, 1, '2020-12-16', '9.4000'),
(36, 4, 1, '2020-12-17', '9.6000'),
(37, 4, 2, '2020-12-14', '8.5500'),
(38, 4, 2, '2020-12-15', '8.4500'),
(39, 4, 2, '2020-12-16', '8.4000'),
(40, 4, 2, '2020-12-17', '8.6000'),
(41, 4, 3, '2020-12-14', '9.3500'),
(42, 4, 3, '2020-12-15', '9.2500'),
(43, 4, 3, '2020-12-16', '9.2000'),
(44, 4, 3, '2020-12-17', '9.6000'),
(45, 3, 3, '2020-12-19', '8.3000');



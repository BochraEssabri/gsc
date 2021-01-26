
---- certains probl�mes r�solus sur JDBC ---------
-- Les caract�res doivent �tre encod�s en UTF8 general c
-- Moteur d'execution: innoDB pour assuer l'identification
-- des relations entre les tables par le serveur.

 
 
DROP TABLE IF EXISTS `carburant`;
CREATE TABLE IF NOT EXISTS `carburant` (
  `id_carburant` int(11) NOT NULL AUTO_INCREMENT,
  `nom_carburant` varchar(32) CHARACTER SET utf8 NOT NULL,
  `description` text CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_carburant`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- D�chargement des donn�es de la table `carburant`
--

INSERT INTO `carburant` (`id_carburant`, `nom_carburant`, `description`) VALUES
(1, 'Essence', 'carburants repr�sent�es par des cercles comportant la lettre E (comme �thanol). Les essences sans-plomb 95 et 98, qui contiennent 5% d��thanol, sont donc d�sign�es par la d�nomination � E5 �. De m�me, le SP95-E10 est dor�navant appel� seulement E10.\r\nQuant au super �thanol, compos� de 85% d��thanol, \"il est nomm� E85.'),
(2, 'Gazole', 'sont repr�sent�s par des carr�s dans lesquels on peut lire la lettre B (comme biodiesel). Ainsi, le gazole que vous utilisez actuellement contient un maximum de 7% de biocarburant, il est donc d�sign� par le code � B7 �. Deux nouveaux carburants diesel devraient bient�t �tre disponibles en stations-services : un gazole avec un maximum de 10% de biocarburant nomm� � B10 � et un diesel synth�tique indiqu� par les lettres � XTL �.'),
(3, 'Essence SP95 E10', 'Ne covient pas avec des ancienes v�hicules.'),
(4, 'Electricit�2', 'Renovrble'),
(5, 'solaire', 'solaire');

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
  UNIQUE KEY `id_station` (`id_station`,`id_carburant`,`date`),
  ADD CONSTRAINT `histo_carb_ibfk_1` FOREIGN KEY (`id_station`) REFERENCES `station` (`id_station`) ON DELETE CASCADE,
  ADD CONSTRAINT `histo_carb_ibfk_2` FOREIGN KEY (`id_carburant`) REFERENCES `carburant` (`id_carburant`) ON DELETE CASCADE;
COMMIT;
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

--
-- D�chargement des donn�es de la table `histo_carb`
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
(45, 3, 3, '2020-12-19', '8.3000'),
(46, 5, 4, '2020-12-21', '15.0000'),
(47, 6, 5, '2020-12-21', '9.3300'),
(48, 6, 5, '2020-12-19', '9.3300'),
(49, 6, 5, '2020-12-15', '9.3300'),
(50, 6, 5, '2020-12-14', '9.3300'),
(53, 6, 5, '2020-12-12', '9.3300');

-- --------------------------------------------------------

--
-- Structure de la table `station`
--

DROP TABLE IF EXISTS `station`;
CREATE TABLE IF NOT EXISTS `station` (
  `id_station` int(11) NOT NULL AUTO_INCREMENT,
  `nom_station` varchar(64) NOT NULL,
  `ville` varchar(64) NOT NULL,
  `adresse` text NOT NULL,
  PRIMARY KEY (`id_station`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- D�chargement des donn�es de la table `station`
--

INSERT INTO `station` (`id_station`, `nom_station`, `ville`, `adresse`) VALUES
(1, 'TOTAL', 'Tanger', 'ROUTE AOUAMA'),
(2, 'PETROM', 'Tanger', 'BOULEVARD DES FORCES ARMEES ROYALES'),
(3, '	Oilibya\r\n', 'Tetouan', 'AVENUE MOULAY EL ABBASS'),
(4, 'SHELL', 'Tetouan', 'AVENUE HASSAN II'),
(5, 'TLyon', 'Lyon', 'Lyon2'),
(6, 'station afriquia', 'Nador', '5 rue princ..');

--
-- Contraintes pour les tables d�charg�es
--

--
-- Contraintes pour la table `histo_carb`
--
ALTER TABLE `histo_carb`
  ADD CONSTRAINT `histo_carb_ibfk_1` FOREIGN KEY (`id_station`) REFERENCES `station` (`id_station`) ON DELETE CASCADE,
  ADD CONSTRAINT `histo_carb_ibfk_2` FOREIGN KEY (`id_carburant`) REFERENCES `carburant` (`id_carburant`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

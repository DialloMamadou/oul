-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 15 Novembre 2019 à 20:30
-- Version du serveur: 5.5.24-log
-- Version de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `reservation`
--

-- --------------------------------------------------------

--
-- Structure de la table `annulation`
--

CREATE TABLE IF NOT EXISTS `annulation` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `motif` varchar(100) NOT NULL,
  `sejourconcerne` int(100) NOT NULL,
  `clientannule` int(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `motif` (`motif`),
  KEY `clientannule` (`clientannule`),
  KEY `sejourconcerne` (`sejourconcerne`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

CREATE TABLE IF NOT EXISTS `centre` (
  `id_centre` int(10) NOT NULL AUTO_INCREMENT,
  `nom_centre` varchar(30) NOT NULL,
  `capacite` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_centre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `centre`
--

INSERT INTO `centre` (`id_centre`, `nom_centre`, `capacite`) VALUES
(1, 'mangoux', 100),
(2, 'combloux', 100),
(3, 'arsenier', 100);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(20) NOT NULL,
  `prenom_client` varchar(20) NOT NULL,
  `groupe_client` int(10) NOT NULL,
  `numero` varchar(10) NOT NULL DEFAULT '0',
  `observation` varchar(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `adresse` varchar(80) NOT NULL DEFAULT '',
  `code_postale` varchar(8) NOT NULL DEFAULT '""',
  `datenaissance` varchar(15) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`),
  KEY `groupe_client` (`groupe_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id`, `nom_client`, `prenom_client`, `groupe_client`, `numero`, `observation`, `email`, `adresse`, `code_postale`, `datenaissance`) VALUES
(4, 'louis', 'Bouffine', 8, '0', '', '', '', '""', '""'),
(5, 'richard', 'justine ', 10, '0', '', '', '', '""', '""'),
(6, 'bona', 'justine ', 9, '0', '', '', '', '""', '""'),
(7, 'rabiot', 'Louisa', 10, '0', '', '', '', '""', '""'),
(8, 'smaila', 'kanoute', 11, '0', '', '', '', '""', '""'),
(9, 'mahamoud', 'malik', 9, '0683938046', 'aucun', 'malik@outlook.fr', '60bd jean rostand', '45800', '2008-11-14'),
(10, 'a', 'a', 8, '10', '10', '10', '10', '10', '2019-11-09'),
(11, 'mahamoud', 'malik', 9, 'aucune', 'aucune', 'aucune', 'AUCUNE', 'aucune', '2019-11-10'),
(12, 'ballda', 'abdallah', 8, '078595856', 'rien', 'rien', 'rien', 'rien', '2019-11-15');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` int(10) NOT NULL AUTO_INCREMENT,
  `nom_groupe` varchar(20) NOT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`id_groupe`, `nom_groupe`) VALUES
(8, 'individuelle'),
(9, 'epaf'),
(10, 'evry'),
(11, 'decade'),
(12, 'mairie saint clout');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE IF NOT EXISTS `inscription` (
  `id_inscription` int(10) NOT NULL AUTO_INCREMENT,
  `paiement` varchar(30) NOT NULL,
  `date_inscription` varchar(20) NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  `depart` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id_inscription`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Structure de la table `sejour`
--

CREATE TABLE IF NOT EXISTS `sejour` (
  `id_sejour` int(11) NOT NULL AUTO_INCREMENT,
  `duree` int(11) NOT NULL,
  `date_debut` varchar(10) NOT NULL,
  `date_fin` varchar(10) NOT NULL,
  `type_sejour` varchar(40) NOT NULL,
  `centre_id` int(11) NOT NULL,
  `prix` int(5) NOT NULL DEFAULT '0',
  `age_min` int(3) NOT NULL DEFAULT '0',
  `age_max` int(3) NOT NULL DEFAULT '0',
  `capacite` int(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_sejour`),
  KEY `centre_id` (`centre_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `sejour`
--

INSERT INTO `sejour` (`id_sejour`, `duree`, `date_debut`, `date_fin`, `type_sejour`, `centre_id`, `prix`, `age_min`, `age_max`, `capacite`) VALUES
(1, 8, '2019-11-12', '2019-11-17', 'la belle au boit', 1, 300, 10, 13, 0),
(2, 7, '2019-11-12', '2019-11-19', 'sous le soleil', 1, 400, 10, 13, 0),
(3, 14, '2019-11-12', '2019-11-26', 'sous le soleil', 1, 400, 10, 13, 0),
(4, 14, '2019-11-12', '2019-11-26', 'mange avec les rois', 3, 400, 10, 13, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `type`) VALUES
(1, 'admin', 'admin', 'admin');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `annulation`
--
ALTER TABLE `annulation`
  ADD CONSTRAINT `annulation_ibfk_1` FOREIGN KEY (`sejourconcerne`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `annulation_ibfk_2` FOREIGN KEY (`clientannule`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`groupe_client`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `inscription_ibfk_1` FOREIGN KEY (`code_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inscription_ibfk_2` FOREIGN KEY (`id_sejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sejour`
--
ALTER TABLE `sejour`
  ADD CONSTRAINT `sejour_ibfk_1` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`id_centre`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

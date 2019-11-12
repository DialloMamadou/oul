-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mar 29 Octobre 2019 à 12:17
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
CREATE DATABASE reservation;
USE reservation;
-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

CREATE TABLE IF NOT EXISTS `centre` (
  `id_centre` int(10) NOT NULL AUTO_INCREMENT,
  `nom_centre` varchar(30) NOT NULL,
  PRIMARY KEY (`id_centre`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `centre`
--

INSERT INTO `centre` (`id_centre`, `nom_centre`) VALUES
(4, 'le meilleur'),
(5, 'caroumz'),
(6, 'combleux');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(20) NOT NULL,
  `prenom_client` varchar(20) NOT NULL,
  `age_client` int(3) NOT NULL,
  `groupe_client` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `groupe_client` (`groupe_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id`, `nom_client`, `prenom_client`, `age_client`, `groupe_client`) VALUES
(3, 'malik', 'mahamoud', 13, 2);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` int(10) NOT NULL AUTO_INCREMENT,
  `nom_groupe` varchar(20) NOT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `groupe`
--

INSERT INTO `groupe` (`id_groupe`, `nom_groupe`) VALUES
(2, 'a'),
(3, 'b'),
(4, 'c'),
(5, 'epaf'),
(6, 'individuelle');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE IF NOT EXISTS `inscription` (
  `id_inscription` int(10) NOT NULL AUTO_INCREMENT,
  `paiement` varchar(30) NOT NULL,
  `observation` varchar(30) NOT NULL,
  `date_inscription` date NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  PRIMARY KEY (`id_inscription`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `inscription`
--

INSERT INTO `inscription` (`id_inscription`, `paiement`, `observation`, `date_inscription`, `code_client`, `id_sejour`) VALUES
(2, '1', '1', '2019-10-26', 3, 2),
(3, '1', '1', '2019-10-27', 3, 2),
(4, 'a', 'a', '2019-10-27', 3, 3);

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
  PRIMARY KEY (`id_sejour`),
  KEY `centre_id` (`centre_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `sejour`
--

INSERT INTO `sejour` (`id_sejour`, `duree`, `date_debut`, `date_fin`, `type_sejour`, `centre_id`) VALUES
(2, 1, '2019-10-26', '2019-10-31', 'la belle au boit', 4),
(3, 2, '2019-10-26', '2019-10-27', 'danse', 5);

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

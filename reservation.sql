-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Jeu 12 Décembre 2019 à 13:18
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
-- Structure de la table `batch_job_execution`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INSTANCE_ID` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution`
--

INSERT INTO `batch_job_execution` (`JOB_EXECUTION_ID`, `VERSION`, `JOB_INSTANCE_ID`, `CREATE_TIME`, `START_TIME`, `END_TIME`, `STATUS`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`, `JOB_CONFIGURATION_LOCATION`) VALUES
(0, 2, 0, '2019-12-11 14:35:48', '2019-12-11 14:35:48', '2019-12-11 14:35:48', 'FAILED', 'FAILED', '', '2019-12-11 14:35:48', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_context`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution_context`
--

INSERT INTO `batch_job_execution_context` (`JOB_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(0, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_params`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint(20) DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXECUTION_ID` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_execution_params`
--

INSERT INTO `batch_job_execution_params` (`JOB_EXECUTION_ID`, `TYPE_CD`, `KEY_NAME`, `STRING_VAL`, `DATE_VAL`, `LONG_VAL`, `DOUBLE_VAL`, `IDENTIFYING`) VALUES
(0, 'LONG', 'run.id', '', '1970-01-01 01:00:00', 1, 0, 'Y');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_seq`
--

CREATE TABLE IF NOT EXISTS `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_instance`
--

CREATE TABLE IF NOT EXISTS `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_job_instance`
--

INSERT INTO `batch_job_instance` (`JOB_INSTANCE_ID`, `VERSION`, `JOB_NAME`, `JOB_KEY`) VALUES
(0, 0, 'importUserJob', '853d3449e311f40366811cbefb3d93d7');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_seq`
--

CREATE TABLE IF NOT EXISTS `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint(20) DEFAULT NULL,
  `READ_COUNT` bigint(20) DEFAULT NULL,
  `FILTER_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_COUNT` bigint(20) DEFAULT NULL,
  `READ_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint(20) DEFAULT NULL,
  `ROLLBACK_COUNT` bigint(20) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_step_execution`
--

INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`, `VERSION`, `STEP_NAME`, `JOB_EXECUTION_ID`, `START_TIME`, `END_TIME`, `STATUS`, `COMMIT_COUNT`, `READ_COUNT`, `FILTER_COUNT`, `WRITE_COUNT`, `READ_SKIP_COUNT`, `WRITE_SKIP_COUNT`, `PROCESS_SKIP_COUNT`, `ROLLBACK_COUNT`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`) VALUES
(0, 2, 'step1', 0, '2019-12-11 14:35:48', '2019-12-11 14:35:48', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Failed to initialize the reader\r\n	at org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader.open(AbstractItemCountingItemStreamItemReader.java:153)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy45.run(Unknown Source)\r\n	at org.springframework.boot.autoconfigure.batch.JobLauncherC', '2019-12-11 14:35:48');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_context`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `batch_step_execution_context`
--

INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(0, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_seq`
--

CREATE TABLE IF NOT EXISTS `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
-- Structure de la table `departement`
--

CREATE TABLE IF NOT EXISTS `departement` (
  `code` varchar(20) NOT NULL,
  `nom` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Contenu de la table `inscription`
--

INSERT INTO `inscription` (`id_inscription`, `paiement`, `date_inscription`, `code_client`, `id_sejour`, `depart`) VALUES
(5, '300', '18-11-2019', 11, 1, 'Orleans'),
(11, '300', '18-11-2019', 11, 1, 'Orleans'),
(12, '300', '18-11-2019', 9, 1, 'Orleans'),
(13, '140', '20-11-2019', 9, 1, 'Orleans'),
(14, '40', '2', 10, 1, '1'),
(15, '60', '2', 10, 1, '1'),
(16, '50', '1', 9, 1, '1'),
(17, '100', '4', 7, 1, '1'),
(18, '300', '4', 7, 1, '1'),
(19, '50', '4', 7, 1, '1'),
(20, '50', '4', 7, 1, '1'),
(21, '60', '2', 10, 1, '1'),
(22, '90', '3', 11, 1, '1'),
(23, '60', '1', 9, 1, '1');

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `person_id` int(10) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(10) NOT NULL,
  `last_name` varchar(10) NOT NULL,
  `email` varchar(10) NOT NULL,
  `age` int(10) NOT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `person`
--

INSERT INTO `person` (`person_id`, `first_name`, `last_name`, `email`, `age`) VALUES
(1, 'malik', 'mahamouda', '@gmail', 13);

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int(10) NOT NULL AUTO_INCREMENT,
  `date_reservation` varchar(20) NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  `depart` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id_reservation`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `sejour`
--

INSERT INTO `sejour` (`id_sejour`, `duree`, `date_debut`, `date_fin`, `type_sejour`, `centre_id`, `prix`, `age_min`, `age_max`, `capacite`) VALUES
(1, 8, '2019-11-12', '2019-11-17', 'la belle au boit', 1, 300, 10, 13, 100),
(2, 7, '2019-11-12', '2019-11-19', 'sous le soleil', 1, 400, 10, 13, 0),
(3, 14, '2019-11-12', '2019-11-26', 'sous le soleil', 1, 400, 10, 13, 0),
(4, 14, '2019-11-12', '2019-11-26', 'mange avec les rois', 3, 400, 10, 13, 0),
(5, 7, '2019-11-18', '2019-11-23', 'sous le soleil', 1, 200, 10, 12, 10);

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

-- --------------------------------------------------------

--
-- Structure de la table `usser`
--

CREATE TABLE IF NOT EXISTS `usser` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `usser`
--

INSERT INTO `usser` (`id`, `name`) VALUES
(1, 'malik'),
(2, 'john');

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
-- Contraintes pour la table `batch_job_execution`
--
ALTER TABLE `batch_job_execution`
  ADD CONSTRAINT `batch_job_execution_ibfk_1` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `batch_job_execution_context`
--
ALTER TABLE `batch_job_execution_context`
  ADD CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_job_execution_params`
--
ALTER TABLE `batch_job_execution_params`
  ADD CONSTRAINT `batch_job_execution_params_ibfk_1` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `batch_step_execution`
--
ALTER TABLE `batch_step_execution`
  ADD CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`);

--
-- Contraintes pour la table `batch_step_execution_context`
--
ALTER TABLE `batch_step_execution_context`
  ADD CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`);

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
-- Contraintes pour la table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`code_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`id_sejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `sejour`
--
ALTER TABLE `sejour`
  ADD CONSTRAINT `sejour_ibfk_1` FOREIGN KEY (`centre_id`) REFERENCES `centre` (`id_centre`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;


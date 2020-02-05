-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 17 jan. 2020 à 15:15
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `reservation`
--

-- --------------------------------------------------------

--
-- Structure de la table `annulation`
--

DROP TABLE IF EXISTS `annulation`;
CREATE TABLE IF NOT EXISTS `annulation` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `motif` varchar(100) NOT NULL,
  `idsejour` int(20) NOT NULL,
  `idclient` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `motif` (`motif`),
  KEY `idsejour` (`idsejour`),
  KEY `idclient` (`idclient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `associationgroupesejour`
--

DROP TABLE IF EXISTS `associationgroupesejour`;
CREATE TABLE IF NOT EXISTS `associationgroupesejour` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `prix_unitaire` int(20) NOT NULL,
  `groupe` int(20) NOT NULL,
  `id_sejour` int(20) NOT NULL,
  `nbplace` int(3) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `groupe` (`groupe`),
  KEY `sejour` (`id_sejour`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `associationgroupesejour`
--

INSERT INTO `associationgroupesejour` (`id`, `prix_unitaire`, `groupe`, `id_sejour`, `nbplace`) VALUES
(1, 300, 3, 4, 10),
(3, 200, 1, 1, 10),
(4, 300, 1, 1, 30),
(5, 250, 3, 1, 70),
(6, 150, 2, 4, 100),
(7, 600, 1, 2, 23),
(8, 200, 1, 4, 75),
(9, 200, 1, 4, 76),
(10, 200, 1, 4, 80),
(11, 66, 1, 1, 60),
(12, 200, 1, 3, 35),
(13, 230, 1, 2, 35),
(14, 1, 1, 2, 3),
(15, 23, 1, 2, 23),
(16, 23, 1, 2, 23),
(17, 2, 1, 2, 2),
(18, 2, 1, 2, 2),
(19, 1, 1, 2, 1),
(20, 5, 1, 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution`
--

DROP TABLE IF EXISTS `batch_job_execution`;
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
-- Déchargement des données de la table `batch_job_execution`
--

INSERT INTO `batch_job_execution` (`JOB_EXECUTION_ID`, `VERSION`, `JOB_INSTANCE_ID`, `CREATE_TIME`, `START_TIME`, `END_TIME`, `STATUS`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`, `JOB_CONFIGURATION_LOCATION`) VALUES
(0, 2, 0, '2019-12-17 03:32:14', '2019-12-17 03:32:14', '2019-12-17 03:32:15', 'COMPLETED', 'COMPLETED', '', '2019-12-17 03:32:15', NULL),
(1, 2, 1, '2019-12-29 18:49:17', '2019-12-29 18:49:17', '2019-12-29 18:49:18', 'FAILED', 'FAILED', '', '2019-12-29 18:49:18', NULL),
(2, 2, 2, '2019-12-29 18:49:48', '2019-12-29 18:49:48', '2019-12-29 18:49:48', 'COMPLETED', 'COMPLETED', '', '2019-12-29 18:49:48', NULL),
(3, 2, 3, '2019-12-30 10:46:03', '2019-12-30 10:46:03', '2019-12-30 10:46:03', 'COMPLETED', 'COMPLETED', '', '2019-12-30 10:46:03', NULL),
(4, 2, 4, '2019-12-30 11:25:30', '2019-12-30 11:25:30', '2019-12-30 11:25:30', 'COMPLETED', 'COMPLETED', '', '2019-12-30 11:25:30', NULL),
(5, 2, 5, '2020-01-02 13:36:19', '2020-01-02 13:36:19', '2020-01-02 13:36:19', 'COMPLETED', 'COMPLETED', '', '2020-01-02 13:36:19', NULL),
(6, 2, 6, '2020-01-02 13:36:40', '2020-01-02 13:36:40', '2020-01-02 13:36:40', 'COMPLETED', 'COMPLETED', '', '2020-01-02 13:36:40', NULL),
(7, 2, 7, '2020-01-03 09:44:47', '2020-01-03 09:44:47', '2020-01-03 09:44:47', 'COMPLETED', 'COMPLETED', '', '2020-01-03 09:44:47', NULL),
(8, 2, 8, '2020-01-06 14:52:24', '2020-01-06 14:52:24', '2020-01-06 14:52:24', 'COMPLETED', 'COMPLETED', '', '2020-01-06 14:52:24', NULL),
(9, 2, 9, '2020-01-06 15:40:32', '2020-01-06 15:40:32', '2020-01-06 15:40:33', 'COMPLETED', 'COMPLETED', '', '2020-01-06 15:40:33', NULL),
(10, 2, 10, '2020-01-12 18:13:43', '2020-01-12 18:13:43', '2020-01-12 18:13:45', 'FAILED', 'FAILED', '', '2020-01-12 18:13:45', NULL),
(11, 1, 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', NULL, 'STARTED', 'UNKNOWN', '', '2020-01-12 18:13:46', NULL),
(12, 2, 12, '2020-01-12 18:14:15', '2020-01-12 18:14:15', '2020-01-12 18:14:20', 'FAILED', 'FAILED', '', '2020-01-12 18:14:20', NULL),
(13, 1, 13, '2020-01-12 18:14:21', '2020-01-12 18:14:21', NULL, 'STARTED', 'UNKNOWN', '', '2020-01-12 18:14:21', NULL),
(14, 2, 14, '2020-01-12 18:15:02', '2020-01-12 18:15:02', '2020-01-12 18:15:05', 'FAILED', 'FAILED', '', '2020-01-12 18:15:05', NULL),
(15, 1, 15, '2020-01-12 18:15:04', '2020-01-12 18:15:04', NULL, 'STARTED', 'UNKNOWN', '', '2020-01-12 18:15:04', NULL),
(16, 2, 16, '2020-01-12 18:16:01', '2020-01-12 18:16:01', '2020-01-12 18:16:02', 'COMPLETED', 'COMPLETED', '', '2020-01-12 18:16:02', NULL),
(17, 1, 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', NULL, 'STARTED', 'UNKNOWN', '', '2020-01-12 18:27:55', NULL),
(18, 1, 18, '2020-01-12 20:13:36', '2020-01-12 20:13:36', NULL, 'STARTED', 'UNKNOWN', '', '2020-01-12 20:13:36', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_context`
--

DROP TABLE IF EXISTS `batch_job_execution_context`;
CREATE TABLE IF NOT EXISTS `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_job_execution_context`
--

INSERT INTO `batch_job_execution_context` (`JOB_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(0, '{}', NULL),
(1, '{}', NULL),
(2, '{}', NULL),
(3, '{}', NULL),
(4, '{}', NULL),
(5, '{}', NULL),
(6, '{}', NULL),
(7, '{}', NULL),
(8, '{}', NULL),
(9, '{}', NULL),
(10, '{}', NULL),
(11, '{}', NULL),
(12, '{}', NULL),
(13, '{}', NULL),
(14, '{}', NULL),
(15, '{}', NULL),
(16, '{}', NULL),
(17, '{}', NULL),
(18, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_params`
--

DROP TABLE IF EXISTS `batch_job_execution_params`;
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
-- Déchargement des données de la table `batch_job_execution_params`
--

INSERT INTO `batch_job_execution_params` (`JOB_EXECUTION_ID`, `TYPE_CD`, `KEY_NAME`, `STRING_VAL`, `DATE_VAL`, `LONG_VAL`, `DOUBLE_VAL`, `IDENTIFYING`) VALUES
(0, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 1, 0, 'Y'),
(1, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 2, 0, 'Y'),
(2, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 3, 0, 'Y'),
(3, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 4, 0, 'Y'),
(4, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 5, 0, 'Y'),
(5, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 6, 0, 'Y'),
(6, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 7, 0, 'Y'),
(7, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 8, 0, 'Y'),
(8, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 9, 0, 'Y'),
(9, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 10, 0, 'Y'),
(10, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 11, 0, 'Y'),
(11, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 12, 0, 'Y'),
(12, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 13, 0, 'Y'),
(13, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 14, 0, 'Y'),
(14, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 15, 0, 'Y'),
(15, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 16, 0, 'Y'),
(16, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 17, 0, 'Y'),
(17, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 18, 0, 'Y'),
(18, 'LONG', 'run.id', '', '1970-01-01 00:00:00', 19, 0, 'Y');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_execution_seq`
--

DROP TABLE IF EXISTS `batch_job_execution_seq`;
CREATE TABLE IF NOT EXISTS `batch_job_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_job_execution_seq`
--

INSERT INTO `batch_job_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(18, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_instance`
--

DROP TABLE IF EXISTS `batch_job_instance`;
CREATE TABLE IF NOT EXISTS `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint(20) NOT NULL,
  `VERSION` bigint(20) DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_job_instance`
--

INSERT INTO `batch_job_instance` (`JOB_INSTANCE_ID`, `VERSION`, `JOB_NAME`, `JOB_KEY`) VALUES
(0, 0, 'exportUserJob', '853d3449e311f40366811cbefb3d93d7'),
(1, 0, 'exportUserJob', 'e070bff4379694c0210a51d9f6c6a564'),
(2, 0, 'exportUserJob', 'a3364faf893276dea0caacefbf618db5'),
(3, 0, 'exportUserJob', '47c0a8118b74165a864b66d37c7b6cf5'),
(4, 0, 'exportUserJob', 'ce148f5f9c2bf4dc9bd44a7a5f64204c'),
(5, 0, 'exportUserJob', 'bd0034040292bc81e6ccac0e25d9a578'),
(6, 0, 'exportUserJob', '597815c7e4ab1092c1b25130aae725cb'),
(7, 0, 'exportUserJob', 'f55a96b11012be4fcfb6cf005435182d'),
(8, 0, 'exportUserJob', '96a5ed9bac43e779455f3e71c0f64840'),
(9, 0, 'exportUserJob', '1aac4f3e74894b78fa3ce5d8a25e1ef0'),
(10, 0, 'exportUserJob', '604bbfc4c68cb1f903780c2853ad4801'),
(11, 0, 'exportUserJob', '556ebe34220b4032509f2581356ba47c'),
(12, 0, 'exportUserJob', 'edc440efb5ddd2a3b2622f16a12bf105'),
(13, 0, 'exportUserJob', 'f3d5e568c384ee72cba8bc6a51057fe4'),
(14, 0, 'exportUserJob', '378ef1ecb81cf9edac4ab119bdab9d9d'),
(15, 0, 'exportUserJob', 'e073471cc312cadef424c3be7915c0af'),
(16, 0, 'exportUserJob', '46ba78a99abf1e2fba4a8861749d7572'),
(17, 0, 'exportUserJob', 'b88d31b704adf9f94fe9d4ccff795708'),
(18, 0, 'exportUserJob', '64d4e6d635ee3ad949314224afce46c2');

-- --------------------------------------------------------

--
-- Structure de la table `batch_job_seq`
--

DROP TABLE IF EXISTS `batch_job_seq`;
CREATE TABLE IF NOT EXISTS `batch_job_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_job_seq`
--

INSERT INTO `batch_job_seq` (`ID`, `UNIQUE_KEY`) VALUES
(18, '0');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution`
--

DROP TABLE IF EXISTS `batch_step_execution`;
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
-- Déchargement des données de la table `batch_step_execution`
--

INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`, `VERSION`, `STEP_NAME`, `JOB_EXECUTION_ID`, `START_TIME`, `END_TIME`, `STATUS`, `COMMIT_COUNT`, `READ_COUNT`, `FILTER_COUNT`, `WRITE_COUNT`, `READ_SKIP_COUNT`, `WRITE_SKIP_COUNT`, `PROCESS_SKIP_COUNT`, `ROLLBACK_COUNT`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`) VALUES
(0, 5, 'step1', 0, '2019-12-17 03:32:14', '2019-12-17 03:32:15', 'COMPLETED', 3, 22, 0, 22, 0, 0, 0, 0, 'COMPLETED', '', '2019-12-17 03:32:15'),
(1, 2, 'step1', 1, '2019-12-29 18:49:18', '2019-12-29 18:49:18', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Could not convert resource to file: [class path resource [event.csv]]\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.getOutputState(AbstractFileItemWriter.java:370)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.open(AbstractFileItemWriter.java:308)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.jav', '2019-12-29 18:49:18'),
(2, 4, 'step1', 2, '2019-12-29 18:49:48', '2019-12-29 18:49:48', 'COMPLETED', 2, 10, 0, 10, 0, 0, 0, 0, 'COMPLETED', '', '2019-12-29 18:49:48'),
(3, 4, 'step1', 3, '2019-12-30 10:46:03', '2019-12-30 10:46:03', 'COMPLETED', 2, 10, 0, 10, 0, 0, 0, 0, 'COMPLETED', '', '2019-12-30 10:46:03'),
(4, 4, 'step1', 4, '2019-12-30 11:25:30', '2019-12-30 11:25:30', 'COMPLETED', 2, 11, 0, 11, 0, 0, 0, 0, 'COMPLETED', '', '2019-12-30 11:25:30'),
(5, 4, 'step1', 5, '2020-01-02 13:36:19', '2020-01-02 13:36:19', 'COMPLETED', 2, 14, 0, 14, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-02 13:36:19'),
(6, 4, 'step1', 6, '2020-01-02 13:36:40', '2020-01-02 13:36:40', 'COMPLETED', 2, 15, 0, 15, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-02 13:36:40'),
(7, 4, 'step1', 7, '2020-01-03 09:44:47', '2020-01-03 09:44:47', 'COMPLETED', 2, 19, 0, 19, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-03 09:44:47'),
(8, 4, 'step1', 8, '2020-01-06 14:52:24', '2020-01-06 14:52:24', 'COMPLETED', 2, 19, 0, 19, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-06 14:52:24'),
(9, 4, 'step1', 9, '2020-01-06 15:40:33', '2020-01-06 15:40:33', 'COMPLETED', 2, 19, 0, 19, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-06 15:40:33'),
(10, 6, 'step1', 10, '2020-01-12 18:13:43', '2020-01-12 18:13:43', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:43'),
(11, 6, 'step1', 10, '2020-01-12 18:13:43', '2020-01-12 18:13:43', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:43'),
(12, 6, 'step1', 10, '2020-01-12 18:13:43', '2020-01-12 18:13:43', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:43'),
(13, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:44', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:44'),
(14, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:44', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:44'),
(15, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:44', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:44'),
(16, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:44', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:44'),
(17, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:44', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:44'),
(18, 6, 'step1', 10, '2020-01-12 18:13:44', '2020-01-12 18:13:45', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:45'),
(19, 6, 'step1', 10, '2020-01-12 18:13:45', '2020-01-12 18:13:45', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:45'),
(20, 6, 'step1', 10, '2020-01-12 18:13:45', '2020-01-12 18:13:45', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:45'),
(21, 2, 'step1', 10, '2020-01-12 18:13:45', '2020-01-12 18:13:45', 'FAILED', 0, 10, 0, 0, 0, 0, 0, 1, 'FAILED', 'org.springframework.batch.support.transaction.FlushFailedException: Could not write to output buffer\r\n	at org.springframework.batch.support.transaction.TransactionAwareBufferedWriter$1.beforeCommit(TransactionAwareBufferedWriter.java:111)\r\n	at org.springframework.transaction.support.TransactionSynchronizationUtils.triggerBeforeCommit(TransactionSynchronizationUtils.java:96)\r\n	at org.springframework.transaction.support.AbstractPlatformTransactionManager.triggerBeforeCommit(AbstractPlatformTransactionManager.java:920)\r\n	at org.springframework.transaction.support.AbstractPlatformTransactionManager.processCommit(AbstractPlatformTransactionManager.java:728)\r\n	at org.springframework.transaction.support.AbstractPlatformTransactionManager.commit(AbstractPlatformTransactionManager.java:712)\r\n	at sun.reflect.GeneratedMethodAccessor41.invoke(Unknown Source)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\r\n	at org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration$PassthruAdvice.invoke(SimpleBatchConfiguration.java:127)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\r\n	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)\r\n	at com.sun.proxy.$Proxy59.commit(Unknown Source)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:152)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step.AbstractSte', '2020-01-12 18:13:45'),
(22, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(23, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(24, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(25, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(26, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(27, 6, 'step1', 11, '2020-01-12 18:13:46', '2020-01-12 18:13:46', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:46'),
(28, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(29, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(30, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(31, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(32, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(33, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(34, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(35, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(36, 6, 'step1', 11, '2020-01-12 18:13:47', '2020-01-12 18:13:47', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:47'),
(37, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(38, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(39, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(40, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(41, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(42, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(43, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(44, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(45, 6, 'step1', 11, '2020-01-12 18:13:48', '2020-01-12 18:13:48', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:48'),
(46, 6, 'step1', 11, '2020-01-12 18:13:49', '2020-01-12 18:13:49', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:49'),
(47, 6, 'step1', 11, '2020-01-12 18:13:49', '2020-01-12 18:13:49', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:49'),
(48, 6, 'step1', 11, '2020-01-12 18:13:49', '2020-01-12 18:13:49', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:49'),
(49, 6, 'step1', 11, '2020-01-12 18:13:49', '2020-01-12 18:13:49', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:49'),
(50, 6, 'step1', 11, '2020-01-12 18:13:49', '2020-01-12 18:13:50', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:50'),
(51, 6, 'step1', 11, '2020-01-12 18:13:50', '2020-01-12 18:13:50', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:50'),
(52, 6, 'step1', 11, '2020-01-12 18:13:50', '2020-01-12 18:13:50', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:50'),
(53, 6, 'step1', 11, '2020-01-12 18:13:50', '2020-01-12 18:13:51', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:51'),
(54, 6, 'step1', 11, '2020-01-12 18:13:51', '2020-01-12 18:13:51', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:51'),
(55, 6, 'step1', 11, '2020-01-12 18:13:51', '2020-01-12 18:13:51', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:51'),
(56, 6, 'step1', 11, '2020-01-12 18:13:51', '2020-01-12 18:13:52', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:13:52'),
(57, 6, 'step1', 12, '2020-01-12 18:14:15', '2020-01-12 18:14:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:15'),
(58, 6, 'step1', 12, '2020-01-12 18:14:15', '2020-01-12 18:14:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:15'),
(59, 6, 'step1', 12, '2020-01-12 18:14:15', '2020-01-12 18:14:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:16'),
(60, 6, 'step1', 12, '2020-01-12 18:14:16', '2020-01-12 18:14:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:16'),
(61, 6, 'step1', 12, '2020-01-12 18:14:16', '2020-01-12 18:14:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:16'),
(62, 6, 'step1', 12, '2020-01-12 18:14:16', '2020-01-12 18:14:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:16'),
(63, 6, 'step1', 12, '2020-01-12 18:14:16', '2020-01-12 18:14:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:16'),
(64, 6, 'step1', 12, '2020-01-12 18:14:16', '2020-01-12 18:14:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:17'),
(65, 6, 'step1', 12, '2020-01-12 18:14:17', '2020-01-12 18:14:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:17'),
(66, 6, 'step1', 12, '2020-01-12 18:14:17', '2020-01-12 18:14:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:17'),
(67, 6, 'step1', 12, '2020-01-12 18:14:17', '2020-01-12 18:14:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:17'),
(68, 6, 'step1', 12, '2020-01-12 18:14:17', '2020-01-12 18:14:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:17'),
(69, 6, 'step1', 12, '2020-01-12 18:14:17', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(70, 6, 'step1', 12, '2020-01-12 18:14:18', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(71, 6, 'step1', 12, '2020-01-12 18:14:18', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(72, 6, 'step1', 12, '2020-01-12 18:14:18', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(73, 6, 'step1', 12, '2020-01-12 18:14:18', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(74, 6, 'step1', 12, '2020-01-12 18:14:18', '2020-01-12 18:14:18', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:18'),
(75, 6, 'step1', 12, '2020-01-12 18:14:19', '2020-01-12 18:14:19', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:19'),
(76, 6, 'step1', 12, '2020-01-12 18:14:19', '2020-01-12 18:14:19', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:19'),
(77, 6, 'step1', 12, '2020-01-12 18:14:19', '2020-01-12 18:14:19', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:19'),
(78, 6, 'step1', 12, '2020-01-12 18:14:19', '2020-01-12 18:14:20', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:20'),
(79, 3, 'step1', 12, '2020-01-12 18:14:20', '2020-01-12 18:14:20', 'FAILED', 1, 20, 0, 10, 0, 0, 0, 1, 'FAILED', 'org.springframework.batch.item.WriterNotOpenException: Writer must be open before it can be written to\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.write(AbstractFileItemWriter.java:238)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.writeItems(SimpleChunkProcessor.java:193)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.doWrite(SimpleChunkProcessor.java:159)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.write(SimpleChunkProcessor.java:294)\r\n	at org.springframework.batch.core.step.item.SimpleChunkProcessor.process(SimpleChunkProcessor.java:217)\r\n	at org.springframework.batch.core.step.item.ChunkOrientedTasklet.execute(ChunkOrientedTasklet.java:77)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:407)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$ChunkTransactionCallback.doInTransaction(TaskletStep.java:331)\r\n	at org.springframework.transaction.support.TransactionTemplate.execute(TransactionTemplate.java:140)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep$2.doInChunkContext(TaskletStep.java:273)\r\n	at org.springframework.batch.core.scope.context.StepContextRepeatCallback.doInIteration(StepContextRepeatCallback.java:82)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.getNextResult(RepeatTemplate.java:375)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.executeInternal(RepeatTemplate.java:215)\r\n	at org.springframework.batch.repeat.support.RepeatTemplate.iterate(RepeatTemplate.java:145)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.doExecute(TaskletStep.java:258)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:208)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.ru', '2020-01-12 18:14:20'),
(80, 6, 'step1', 13, '2020-01-12 18:14:21', '2020-01-12 18:14:21', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:21'),
(81, 6, 'step1', 13, '2020-01-12 18:14:21', '2020-01-12 18:14:21', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:21'),
(82, 6, 'step1', 13, '2020-01-12 18:14:21', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(83, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(84, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(85, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(86, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(87, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(88, 6, 'step1', 13, '2020-01-12 18:14:22', '2020-01-12 18:14:22', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:22'),
(89, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(90, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(91, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(92, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(93, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(94, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:23', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:23'),
(95, 6, 'step1', 13, '2020-01-12 18:14:23', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(96, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(97, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(98, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(99, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(100, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(101, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(102, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(103, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(104, 6, 'step1', 13, '2020-01-12 18:14:24', '2020-01-12 18:14:24', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:24'),
(105, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(106, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(107, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(108, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(109, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(110, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(111, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(112, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(113, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(114, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:25', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:25'),
(115, 6, 'step1', 13, '2020-01-12 18:14:25', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(116, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(117, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(118, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(119, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(120, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(121, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(122, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(123, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(124, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(125, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:26', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:26'),
(126, 6, 'step1', 13, '2020-01-12 18:14:26', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(127, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(128, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(129, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(130, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(131, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(132, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(133, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(134, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(135, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(136, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:27', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:27'),
(137, 6, 'step1', 13, '2020-01-12 18:14:27', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(138, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(139, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(140, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(141, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(142, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(143, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(144, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(145, 6, 'step1', 13, '2020-01-12 18:14:28', '2020-01-12 18:14:28', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:28'),
(146, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(147, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(148, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(149, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(150, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(151, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(152, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(153, 6, 'step1', 13, '2020-01-12 18:14:29', '2020-01-12 18:14:29', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:29'),
(154, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:30', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:30'),
(155, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:30', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:30'),
(156, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:30', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:30'),
(157, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:30', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:30'),
(158, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:30', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:30'),
(159, 6, 'step1', 13, '2020-01-12 18:14:30', '2020-01-12 18:14:31', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:31'),
(160, 6, 'step1', 13, '2020-01-12 18:14:31', '2020-01-12 18:14:31', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:31'),
(161, 6, 'step1', 13, '2020-01-12 18:14:31', '2020-01-12 18:14:31', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:31'),
(162, 6, 'step1', 13, '2020-01-12 18:14:31', '2020-01-12 18:14:31', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:31'),
(163, 6, 'step1', 13, '2020-01-12 18:14:31', '2020-01-12 18:14:31', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:14:31'),
(164, 6, 'step1', 14, '2020-01-12 18:15:02', '2020-01-12 18:15:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:02'),
(165, 6, 'step1', 14, '2020-01-12 18:15:02', '2020-01-12 18:15:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:03'),
(166, 6, 'step1', 14, '2020-01-12 18:15:03', '2020-01-12 18:15:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:03'),
(167, 6, 'step1', 14, '2020-01-12 18:15:03', '2020-01-12 18:15:04', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:04'),
(168, 6, 'step1', 14, '2020-01-12 18:15:04', '2020-01-12 18:15:04', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:04'),
(169, 6, 'step1', 14, '2020-01-12 18:15:04', '2020-01-12 18:15:04', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:04'),
(170, 6, 'step1', 14, '2020-01-12 18:15:04', '2020-01-12 18:15:04', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:04'),
(171, 2, 'step1', 14, '2020-01-12 18:15:04', '2020-01-12 18:15:05', 'FAILED', 0, 0, 0, 0, 0, 0, 0, 0, 'FAILED', 'org.springframework.batch.item.ItemStreamException: Output file was not created: [C:\\Users\\ilyas\\Music\\dernierechancespring\\target\\classes\\event.csv]\r\n	at org.springframework.batch.item.util.FileUtils.setUpOutputFile(FileUtils.java:67)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter$OutputState.initializeBufferedWriter(AbstractFileItemWriter.java:553)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter$OutputState.access$000(AbstractFileItemWriter.java:385)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.doOpen(AbstractFileItemWriter.java:319)\r\n	at org.springframework.batch.item.support.AbstractFileItemWriter.open(AbstractFileItemWriter.java:309)\r\n	at org.springframework.batch.item.support.CompositeItemStream.open(CompositeItemStream.java:103)\r\n	at org.springframework.batch.core.step.tasklet.TaskletStep.open(TaskletStep.java:311)\r\n	at org.springframework.batch.core.step.AbstractStep.execute(AbstractStep.java:205)\r\n	at org.springframework.batch.core.job.SimpleStepHandler.handleStep(SimpleStepHandler.java:148)\r\n	at org.springframework.batch.core.job.flow.JobFlowExecutor.executeStep(JobFlowExecutor.java:68)\r\n	at org.springframework.batch.core.job.flow.support.state.StepState.handle(StepState.java:68)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.resume(SimpleFlow.java:169)\r\n	at org.springframework.batch.core.job.flow.support.SimpleFlow.start(SimpleFlow.java:144)\r\n	at org.springframework.batch.core.job.flow.FlowJob.doExecute(FlowJob.java:137)\r\n	at org.springframework.batch.core.job.AbstractJob.execute(AbstractJob.java:319)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher$1.run(SimpleJobLauncher.java:147)\r\n	at org.springframework.core.task.SyncTaskExecutor.execute(SyncTaskExecutor.java:50)\r\n	at org.springframework.batch.core.launch.support.SimpleJobLauncher.run(SimpleJobLauncher.java:140)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n	at java.lang.reflect.Method.invoke(Method.java:498)\r\n	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198)\r\n	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMe', '2020-01-12 18:15:05'),
(172, 6, 'step1', 15, '2020-01-12 18:15:05', '2020-01-12 18:15:05', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:05'),
(173, 6, 'step1', 15, '2020-01-12 18:15:05', '2020-01-12 18:15:05', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:05'),
(174, 6, 'step1', 15, '2020-01-12 18:15:05', '2020-01-12 18:15:05', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:05'),
(175, 6, 'step1', 15, '2020-01-12 18:15:05', '2020-01-12 18:15:06', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:06'),
(176, 6, 'step1', 15, '2020-01-12 18:15:06', '2020-01-12 18:15:06', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:06'),
(177, 6, 'step1', 15, '2020-01-12 18:15:06', '2020-01-12 18:15:06', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:06'),
(178, 6, 'step1', 15, '2020-01-12 18:15:06', '2020-01-12 18:15:06', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:06'),
(179, 6, 'step1', 15, '2020-01-12 18:15:06', '2020-01-12 18:15:06', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:06'),
(180, 6, 'step1', 15, '2020-01-12 18:15:06', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(181, 6, 'step1', 15, '2020-01-12 18:15:07', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(182, 6, 'step1', 15, '2020-01-12 18:15:07', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(183, 6, 'step1', 15, '2020-01-12 18:15:07', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(184, 6, 'step1', 15, '2020-01-12 18:15:07', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(185, 6, 'step1', 15, '2020-01-12 18:15:07', '2020-01-12 18:15:07', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:07'),
(186, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(187, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(188, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(189, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(190, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(191, 6, 'step1', 15, '2020-01-12 18:15:08', '2020-01-12 18:15:08', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:08'),
(192, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:09', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:09'),
(193, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:09', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:09'),
(194, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:09', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:09'),
(195, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:09', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:09'),
(196, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:09', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:09'),
(197, 6, 'step1', 15, '2020-01-12 18:15:09', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:10'),
(198, 6, 'step1', 15, '2020-01-12 18:15:10', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:10'),
(199, 6, 'step1', 15, '2020-01-12 18:15:10', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:10'),
(200, 6, 'step1', 15, '2020-01-12 18:15:10', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:10'),
(201, 6, 'step1', 15, '2020-01-12 18:15:10', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:10'),
(202, 6, 'step1', 15, '2020-01-12 18:15:10', '2020-01-12 18:15:10', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(203, 6, 'step1', 15, '2020-01-12 18:15:11', '2020-01-12 18:15:11', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(204, 6, 'step1', 15, '2020-01-12 18:15:11', '2020-01-12 18:15:11', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(205, 6, 'step1', 15, '2020-01-12 18:15:11', '2020-01-12 18:15:11', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(206, 6, 'step1', 15, '2020-01-12 18:15:11', '2020-01-12 18:15:11', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(207, 6, 'step1', 15, '2020-01-12 18:15:11', '2020-01-12 18:15:11', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:11'),
(208, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:12', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:12'),
(209, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:12', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:12'),
(210, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:12', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:12'),
(211, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:12', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:12'),
(212, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:12', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:12'),
(213, 6, 'step1', 15, '2020-01-12 18:15:12', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(214, 6, 'step1', 15, '2020-01-12 18:15:13', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(215, 6, 'step1', 15, '2020-01-12 18:15:13', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(216, 6, 'step1', 15, '2020-01-12 18:15:13', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(217, 6, 'step1', 15, '2020-01-12 18:15:13', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(218, 6, 'step1', 15, '2020-01-12 18:15:13', '2020-01-12 18:15:13', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:13'),
(219, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:14', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:14'),
(220, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:14', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:14'),
(221, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:14', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:14'),
(222, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:14', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:14'),
(223, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:14', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:14'),
(224, 6, 'step1', 15, '2020-01-12 18:15:14', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(225, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(226, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(227, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(228, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(229, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(230, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:15', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:15'),
(231, 6, 'step1', 15, '2020-01-12 18:15:15', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(232, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(233, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(234, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(235, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(236, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(237, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:16', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:16'),
(238, 6, 'step1', 15, '2020-01-12 18:15:16', '2020-01-12 18:15:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:17'),
(239, 6, 'step1', 15, '2020-01-12 18:15:17', '2020-01-12 18:15:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:17'),
(240, 6, 'step1', 15, '2020-01-12 18:15:17', '2020-01-12 18:15:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:17'),
(241, 6, 'step1', 15, '2020-01-12 18:15:17', '2020-01-12 18:15:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:17'),
(242, 6, 'step1', 15, '2020-01-12 18:15:17', '2020-01-12 18:15:17', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:15:17'),
(243, 6, 'step1', 16, '2020-01-12 18:16:01', '2020-01-12 18:16:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:16:02'),
(244, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(245, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(246, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(247, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(248, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(249, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:55', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:55'),
(250, 6, 'step1', 17, '2020-01-12 18:27:55', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(251, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(252, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(253, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(254, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(255, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(256, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(257, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(258, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:56', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:56'),
(259, 6, 'step1', 17, '2020-01-12 18:27:56', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(260, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(261, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(262, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(263, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(264, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(265, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(266, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(267, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(268, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:57', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:57'),
(269, 6, 'step1', 17, '2020-01-12 18:27:57', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(270, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58');
INSERT INTO `batch_step_execution` (`STEP_EXECUTION_ID`, `VERSION`, `STEP_NAME`, `JOB_EXECUTION_ID`, `START_TIME`, `END_TIME`, `STATUS`, `COMMIT_COUNT`, `READ_COUNT`, `FILTER_COUNT`, `WRITE_COUNT`, `READ_SKIP_COUNT`, `WRITE_SKIP_COUNT`, `PROCESS_SKIP_COUNT`, `ROLLBACK_COUNT`, `EXIT_CODE`, `EXIT_MESSAGE`, `LAST_UPDATED`) VALUES
(271, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(272, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(273, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(274, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(275, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(276, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(277, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:58', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:58'),
(278, 6, 'step1', 17, '2020-01-12 18:27:58', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(279, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(280, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(281, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(282, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(283, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(284, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(285, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(286, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(287, 6, 'step1', 17, '2020-01-12 18:27:59', '2020-01-12 18:27:59', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:27:59'),
(288, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(289, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(290, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(291, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(292, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(293, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(294, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(295, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(296, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(297, 6, 'step1', 17, '2020-01-12 18:28:00', '2020-01-12 18:28:00', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:00'),
(298, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(299, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(300, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(301, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(302, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(303, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(304, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(305, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(306, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(307, 6, 'step1', 17, '2020-01-12 18:28:01', '2020-01-12 18:28:01', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:01'),
(308, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(309, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(310, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(311, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(312, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(313, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(314, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(315, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(316, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(317, 6, 'step1', 17, '2020-01-12 18:28:02', '2020-01-12 18:28:02', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:02'),
(318, 6, 'step1', 17, '2020-01-12 18:28:03', '2020-01-12 18:28:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:03'),
(319, 6, 'step1', 17, '2020-01-12 18:28:03', '2020-01-12 18:28:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:03'),
(320, 6, 'step1', 17, '2020-01-12 18:28:03', '2020-01-12 18:28:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:03'),
(321, 6, 'step1', 17, '2020-01-12 18:28:03', '2020-01-12 18:28:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:03'),
(322, 6, 'step1', 17, '2020-01-12 18:28:03', '2020-01-12 18:28:03', 'COMPLETED', 4, 31, 0, 31, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 18:28:03'),
(323, 1, 'step1', 17, '2020-01-12 18:28:03', NULL, 'STARTED', 0, 0, 0, 0, 0, 0, 0, 0, 'EXECUTING', '', '2020-01-12 18:28:03'),
(324, 3, 'step1', 18, '2020-01-12 20:13:36', '2020-01-12 20:13:36', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:36'),
(325, 3, 'step1', 18, '2020-01-12 20:13:36', '2020-01-12 20:13:36', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:36'),
(326, 3, 'step1', 18, '2020-01-12 20:13:36', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(327, 3, 'step1', 18, '2020-01-12 20:13:37', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(328, 3, 'step1', 18, '2020-01-12 20:13:37', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(329, 3, 'step1', 18, '2020-01-12 20:13:37', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(330, 3, 'step1', 18, '2020-01-12 20:13:37', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(331, 3, 'step1', 18, '2020-01-12 20:13:37', '2020-01-12 20:13:37', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:37'),
(332, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(333, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(334, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(335, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(336, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(337, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(338, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(339, 3, 'step1', 18, '2020-01-12 20:13:38', '2020-01-12 20:13:38', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:38'),
(340, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(341, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(342, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(343, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(344, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(345, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(346, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(347, 3, 'step1', 18, '2020-01-12 20:13:39', '2020-01-12 20:13:39', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:39'),
(348, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(349, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(350, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(351, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(352, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(353, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(354, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(355, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(356, 3, 'step1', 18, '2020-01-12 20:13:40', '2020-01-12 20:13:40', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:40'),
(357, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(358, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(359, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(360, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(361, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(362, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(363, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(364, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(365, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(366, 3, 'step1', 18, '2020-01-12 20:13:41', '2020-01-12 20:13:41', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:41'),
(367, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(368, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(369, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(370, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(371, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(372, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(373, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(374, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(375, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(376, 3, 'step1', 18, '2020-01-12 20:13:42', '2020-01-12 20:13:42', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:42'),
(377, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(378, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(379, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(380, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(381, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(382, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(383, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(384, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(385, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(386, 3, 'step1', 18, '2020-01-12 20:13:43', '2020-01-12 20:13:43', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:43'),
(387, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(388, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(389, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(390, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(391, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(392, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(393, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(394, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(395, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:44', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:44'),
(396, 3, 'step1', 18, '2020-01-12 20:13:44', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(397, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(398, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(399, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(400, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(401, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(402, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(403, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(404, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(405, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(406, 3, 'step1', 18, '2020-01-12 20:13:45', '2020-01-12 20:13:45', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:45'),
(407, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(408, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(409, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(410, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(411, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(412, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(413, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(414, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(415, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(416, 3, 'step1', 18, '2020-01-12 20:13:46', '2020-01-12 20:13:46', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:46'),
(417, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(418, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(419, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(420, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(421, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(422, 3, 'step1', 18, '2020-01-12 20:13:47', '2020-01-12 20:13:47', 'COMPLETED', 1, 2, 0, 2, 0, 0, 0, 0, 'COMPLETED', '', '2020-01-12 20:13:47'),
(423, 1, 'step1', 18, '2020-01-12 20:13:47', NULL, 'STARTED', 0, 0, 0, 0, 0, 0, 0, 0, 'EXECUTING', '', '2020-01-12 20:13:47');

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_context`
--

DROP TABLE IF EXISTS `batch_step_execution_context`;
CREATE TABLE IF NOT EXISTS `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint(20) NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_step_execution_context`
--

INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(0, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1357],\"JdbcCursorItemReader.read.count\":23,\"FlatFileItemWriter.written\":[\"java.lang.Long\",22],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(1, '{}', NULL),
(2, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",550],\"JdbcCursorItemReader.read.count\":11,\"FlatFileItemWriter.written\":[\"java.lang.Long\",10],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(3, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",550],\"JdbcCursorItemReader.read.count\":11,\"FlatFileItemWriter.written\":[\"java.lang.Long\",10],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(4, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",594],\"JdbcCursorItemReader.read.count\":12,\"FlatFileItemWriter.written\":[\"java.lang.Long\",11],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(5, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",747],\"JdbcCursorItemReader.read.count\":15,\"FlatFileItemWriter.written\":[\"java.lang.Long\",14],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(6, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",809],\"JdbcCursorItemReader.read.count\":16,\"FlatFileItemWriter.written\":[\"java.lang.Long\",15],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(7, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1023],\"JdbcCursorItemReader.read.count\":20,\"FlatFileItemWriter.written\":[\"java.lang.Long\",19],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(8, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1023],\"JdbcCursorItemReader.read.count\":20,\"FlatFileItemWriter.written\":[\"java.lang.Long\",19],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(9, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1023],\"JdbcCursorItemReader.read.count\":20,\"FlatFileItemWriter.written\":[\"java.lang.Long\",19],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(10, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(11, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(12, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(13, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(14, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(15, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(16, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(17, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(18, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(19, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(20, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(21, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",0],\"JdbcCursorItemReader.read.count\":0,\"FlatFileItemWriter.written\":[\"java.lang.Long\",0],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(22, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(23, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(24, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(25, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(26, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(27, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(28, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(29, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(30, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(31, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(32, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(33, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(34, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(35, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(36, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(37, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(38, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(39, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(40, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(41, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(42, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(43, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(44, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(45, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(46, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(47, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(48, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(49, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(50, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(51, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(52, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(53, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(54, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(55, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(56, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(57, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(58, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(59, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(60, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(61, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(62, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(63, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(64, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(65, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(66, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(67, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(68, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(69, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(70, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(71, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(72, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(73, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(74, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(75, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(76, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(77, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(78, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(79, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",550],\"JdbcCursorItemReader.read.count\":10,\"FlatFileItemWriter.written\":[\"java.lang.Long\",10],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(80, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(81, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(82, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(83, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(84, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(85, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(86, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(87, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(88, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(89, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(90, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(91, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(92, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(93, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(94, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(95, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(96, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(97, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(98, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(99, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(100, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(101, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(102, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(103, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(104, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(105, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(106, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(107, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(108, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(109, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(110, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(111, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(112, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(113, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(114, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(115, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(116, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(117, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(118, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(119, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(120, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(121, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(122, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(123, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(124, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(125, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(126, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(127, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(128, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(129, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(130, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(131, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(132, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(133, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(134, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(135, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(136, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(137, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(138, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(139, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(140, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(141, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(142, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(143, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(144, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(145, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(146, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(147, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(148, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(149, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(150, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(151, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(152, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(153, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(154, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(155, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(156, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(157, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(158, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(159, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(160, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(161, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(162, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(163, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(164, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(165, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(166, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(167, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(168, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(169, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(170, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(171, '{}', NULL),
(172, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(173, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(174, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(175, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(176, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(177, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(178, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(179, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(180, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(181, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(182, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(183, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(184, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(185, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(186, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(187, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(188, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(189, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(190, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(191, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(192, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(193, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(194, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(195, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(196, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(197, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(198, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(199, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(200, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(201, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(202, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(203, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(204, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(205, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(206, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(207, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(208, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(209, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(210, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(211, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(212, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(213, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(214, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(215, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(216, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(217, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(218, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(219, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(220, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(221, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(222, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(223, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(224, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(225, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(226, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(227, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(228, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(229, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(230, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(231, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(232, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(233, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(234, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(235, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(236, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(237, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(238, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(239, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(240, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(241, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(242, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(243, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(244, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(245, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(246, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(247, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(248, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(249, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(250, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(251, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(252, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(253, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(254, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(255, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(256, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(257, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(258, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(259, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(260, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(261, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(262, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(263, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(264, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(265, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(266, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(267, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(268, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(269, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(270, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(271, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(272, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(273, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(274, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(275, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(276, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(277, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(278, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(279, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(280, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(281, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(282, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(283, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(284, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(285, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(286, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(287, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(288, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(289, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(290, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(291, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(292, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(293, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(294, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL);
INSERT INTO `batch_step_execution_context` (`STEP_EXECUTION_ID`, `SHORT_CONTEXT`, `SERIALIZED_CONTEXT`) VALUES
(295, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(296, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(297, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(298, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(299, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(300, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(301, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(302, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(303, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(304, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(305, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(306, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(307, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(308, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(309, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(310, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(311, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(312, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(313, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(314, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(315, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(316, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(317, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(318, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(319, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(320, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(321, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(322, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",1609],\"JdbcCursorItemReader.read.count\":32,\"FlatFileItemWriter.written\":[\"java.lang.Long\",31],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(323, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",0],\"JdbcCursorItemReader.read.count\":0,\"FlatFileItemWriter.written\":[\"java.lang.Long\",0],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(324, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(325, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(326, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(327, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(328, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(329, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(330, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(331, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(332, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(333, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(334, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(335, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(336, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(337, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(338, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(339, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(340, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(341, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(342, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(343, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(344, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(345, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(346, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(347, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(348, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(349, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(350, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(351, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(352, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(353, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(354, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(355, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(356, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(357, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(358, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(359, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(360, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(361, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(362, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(363, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(364, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(365, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(366, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(367, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(368, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(369, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(370, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(371, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(372, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(373, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(374, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(375, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(376, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(377, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(378, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(379, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(380, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(381, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(382, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(383, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(384, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(385, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(386, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(387, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(388, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(389, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(390, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(391, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(392, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(393, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(394, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(395, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(396, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(397, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(398, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(399, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(400, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(401, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(402, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(403, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(404, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(405, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(406, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(407, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(408, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(409, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(410, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(411, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(412, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(413, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(414, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(415, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(416, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(417, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(418, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(419, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(420, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(421, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(422, '{\"FlatFileItemWriter.current.count\":[\"java.lang.Long\",90],\"JdbcCursorItemReader.read.count\":3,\"FlatFileItemWriter.written\":[\"java.lang.Long\",2],\"batch.taskletType\":\"org.springframework.batch.core.step.item.ChunkOrientedTasklet\",\"batch.stepType\":\"org.springframework.batch.core.step.tasklet.TaskletStep\"}', NULL),
(423, '{}', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `batch_step_execution_seq`
--

DROP TABLE IF EXISTS `batch_step_execution_seq`;
CREATE TABLE IF NOT EXISTS `batch_step_execution_seq` (
  `ID` bigint(20) NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `batch_step_execution_seq`
--

INSERT INTO `batch_step_execution_seq` (`ID`, `UNIQUE_KEY`) VALUES
(423, '0');

-- --------------------------------------------------------

--
-- Structure de la table `centre`
--

DROP TABLE IF EXISTS `centre`;
CREATE TABLE IF NOT EXISTS `centre` (
  `id_centre` int(10) NOT NULL AUTO_INCREMENT,
  `nom_centre` varchar(30) NOT NULL,
  `capacite` int(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_centre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `centre`
--

INSERT INTO `centre` (`id_centre`, `nom_centre`, `capacite`) VALUES
(1, 'Ingrannes', 60),
(2, 'Croq', 100);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(20) NOT NULL,
  `prenom_client` varchar(20) NOT NULL,
  `groupe_client` int(10) NOT NULL,
  `numero` varchar(10) NOT NULL DEFAULT '0',
  `observation` varchar(40) NOT NULL,
  `email` varchar(60) NOT NULL,
  `adresse` varchar(80) NOT NULL DEFAULT '',
  `code_postale` varchar(20) NOT NULL DEFAULT '""',
  `datenaissance` varchar(15) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id`),
  KEY `groupe_client` (`groupe_client`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom_client`, `prenom_client`, `groupe_client`, `numero`, `observation`, `email`, `adresse`, `code_postale`, `datenaissance`) VALUES
(1, 'malik', 'mahamoud', 1, '0683938046', 'mr abdallah', 'malikabdallah75019@gmail.com', '69 bd jean rostand', '45800', '2019-10-25'),
(2, 'gyldan', 'percy', 1, '0683938046', 'mr abdallah', 'malikabdallah75019@gmail.com', '69 bd jean rostand', '45800', '2019-10-25'),
(3, 'jones', 'jones', 3, '0693804554', 'aucune', 'a@outlook.fr', '69 bd ', '45000', '2020-01-09'),
(4, 'jones', 'jones', 3, 'jones', 'jones', 'jones', 'jones', 'jones', '2020-01-12'),
(5, 'diallo', 'mcd', 1, '0000000000', 'maman', 'mon mail', 'rue vendome', '45000', '2008-11-20'),
(6, 'diaby', 'moussa', 1, '777777', 'papa', 'email', 'orleans', '45000', '2020-01-06'),
(7, 'baby', 'baba', 3, '8888', 'baba', 'bba', 'adress', '362', '2020-01-08'),
(8, 'baby', 'baba', 3, '8888', 'baba', 'bba', 'adress', '362', '2020-01-08'),
(9, 'mcd', 'mcd', 1, '77', 'maman', 'Email', 'orleans', '45100', '2019-12-30'),
(10, 'mayata', 'mayata', 3, '8888', 'papa', 'papa', 'mayata', '45000', '2019-12-31'),
(11, 'Cire', 'Diallo', 3, '224', 'maman', 'maman', 'Guinee', '90000', '2019-12-30'),
(12, 'Doura', 'bah', 3, '221', 'papa', 'papaE', 'Senegal', '80000', '2019-12-31'),
(13, 'Moussa', 'Diop', 8, '454', 'parent1', 'p1', 'sen', '10 sen', '2019-12-31');

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `codeclient` varchar(20) NOT NULL,
  `codesejour` varchar(20) NOT NULL,
  `evenementa` varchar(30) NOT NULL,
  `somme` varchar(30) NOT NULL,
  `dateevenement` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id`, `codeclient`, `codesejour`, `evenementa`, `somme`, `dateevenement`) VALUES
(1, '1', '4', 'paiement inscription', '380', '12-01-2020'),
(2, '11', '4', 'paiement inscription', '200', '16-01-2020');

-- --------------------------------------------------------

--
-- Structure de la table `evenement_mairie`
--

DROP TABLE IF EXISTS `evenement_mairie`;
CREATE TABLE IF NOT EXISTS `evenement_mairie` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupe` varchar(25) NOT NULL,
  `sejour` varchar(25) NOT NULL,
  `evenement` varchar(25) NOT NULL,
  `somme` varchar(25) NOT NULL,
  `date` varchar(20) NOT NULL,
  `methode` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement_mairie`
--

INSERT INTO `evenement_mairie` (`id`, `groupe`, `sejour`, `evenement`, `somme`, `date`, `methode`) VALUES
(1, '11', '8', 'paiement_mairie', '100', '12-01-2020', 'CarteBleu'),
(2, '13', '9', 'paiement_mairie', '1000', '12-01-2020', 'CarteBleu'),
(3, '3', '4', 'paiement_mairie', '3000', '12-01-2020', 'Cheque'),
(4, '', '', 'paiement_mairie', '22', '15-01-2020', 'CarteBleu'),
(5, '1', '1', 'paiement_mairie', '2000', '16-01-2020', 'CarteBleu'),
(6, '3', '4', 'paiement_mairie', '10', '16-01-2020', 'BonCaf'),
(7, '1', '1', 'paiement_mairie', '', '16-01-2020', 'Cheque'),
(8, '3', '1', 'paiement_mairie', '500', '16-01-2020', 'CarteBleu'),
(9, '3', '1', 'paiement_mairie', 'ee', '16-01-2020', 'Cheque'),
(10, '2', '4', 'paiement_mairie', '1000', '16-01-2020', 'CarteBleu'),
(11, '2', '4', 'paiement_mairie', '1000', '16-01-2020', 'CarteBleu'),
(12, '2', '4', 'paiement_mairie', '5000', '17-01-2020', 'Cheque');

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

DROP TABLE IF EXISTS `groupe`;
CREATE TABLE IF NOT EXISTS `groupe` (
  `id_groupe` int(10) NOT NULL AUTO_INCREMENT,
  `nom_groupe` varchar(20) NOT NULL,
  `tiers` varchar(10) NOT NULL,
  PRIMARY KEY (`id_groupe`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`id_groupe`, `nom_groupe`, `tiers`) VALUES
(1, 'epaf', '\"\"'),
(2, 'mairie evry', '\"\"'),
(3, 'marseille', '\"\"'),
(8, 'Groupe 1', 'Grp1'),
(11, 'Groupe 2', 'Grp2');

-- --------------------------------------------------------

--
-- Structure de la table `groupe_sejour_client`
--

DROP TABLE IF EXISTS `groupe_sejour_client`;
CREATE TABLE IF NOT EXISTS `groupe_sejour_client` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `id_groupe` int(20) NOT NULL,
  `id_sejour` int(20) NOT NULL,
  `id_client` int(20) NOT NULL,
  `depart` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `groupe_sejour_client`
--

INSERT INTO `groupe_sejour_client` (`id`, `id_groupe`, `id_sejour`, `id_client`, `depart`) VALUES
(1, 3, 4, 4, 'Montargis'),
(2, 1, 1, 5, 'Orleans'),
(3, 1, 1, 6, 'Montargis'),
(4, 3, 4, 7, 'Orleans'),
(5, 3, 4, 8, 'Orleans'),
(6, 1, 1, 9, 'Orleans'),
(7, 3, 4, 10, 'Montargis'),
(8, 3, 4, 11, 'Orleans'),
(9, 3, 4, 12, 'Montargis');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

DROP TABLE IF EXISTS `inscription`;
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`id_inscription`, `paiement`, `date_inscription`, `code_client`, `id_sejour`, `depart`) VALUES
(1, '380', '12-01-2020', 1, 4, 'Orleans'),
(2, '0', '12-01-2020', 4, 4, 'Montargis'),
(3, '0', '16-01-2020', 7, 4, 'Orleans'),
(4, '0', '16-01-2020', 8, 4, 'Orleans'),
(5, '0', '16-01-2020', 10, 4, 'Montargis'),
(6, '0', '16-01-2020', 5, 1, 'Orleans'),
(7, '0', '16-01-2020', 6, 1, 'Montargis'),
(9, '0', '16-01-2020', 11, 4, 'Orleans'),
(10, '0', '16-01-2020', 12, 4, 'Montargis'),
(11, '0', '16-01-2020', 9, 1, 'Orleans'),
(12, '200', '16-01-2020', 11, 4, 'Orleans');

-- --------------------------------------------------------

--
-- Structure de la table `paiement_mairie`
--

DROP TABLE IF EXISTS `paiement_mairie`;
CREATE TABLE IF NOT EXISTS `paiement_mairie` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `groupe` varchar(30) NOT NULL,
  `paiement` varchar(30) NOT NULL,
  `sejour` varchar(30) NOT NULL,
  `methode` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `paiement_mairie`
--

INSERT INTO `paiement_mairie` (`id`, `groupe`, `paiement`, `sejour`, `methode`) VALUES
(1, '3', '3000', '4', 'Cheque'),
(2, '', '22', '', 'CarteBleu'),
(3, '1', '2000', '1', 'CarteBleu'),
(4, '3', '10', '4', 'BonCaf'),
(5, '1', '', '1', 'Cheque'),
(6, '3', '500', '1', 'CarteBleu'),
(7, '3', 'ee', '1', 'Cheque'),
(8, '2', '1000', '4', 'CarteBleu'),
(9, '2', '1000', '4', 'CarteBleu'),
(10, '2', '5000', '4', 'Cheque');

-- --------------------------------------------------------

--
-- Structure de la table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reservation` int(10) NOT NULL AUTO_INCREMENT,
  `date_reservation` varchar(20) NOT NULL,
  `code_client` int(10) NOT NULL,
  `id_sejour` int(11) NOT NULL,
  `depart` varchar(10) NOT NULL DEFAULT '""',
  PRIMARY KEY (`id_reservation`),
  KEY `code_client` (`code_client`),
  KEY `id_sejour` (`id_sejour`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservation`
--

INSERT INTO `reservation` (`id_reservation`, `date_reservation`, `code_client`, `id_sejour`, `depart`) VALUES
(2, '14-01-2020', 4, 4, 'inconnu'),
(3, '14-01-2020', 4, 4, 'inconnu'),
(4, '14-01-2020', 4, 4, 'inconnu'),
(5, '14-01-2020', 4, 4, 'inconnu'),
(6, '14-01-2020', 4, 4, 'inconnu'),
(7, '14-01-2020', 4, 4, 'inconnu'),
(8, '14-01-2020', 4, 4, 'inconnu'),
(9, '14-01-2020', 4, 4, 'inconnu'),
(10, '14-01-2020', 4, 4, 'inconnu'),
(11, '14-01-2020', 4, 4, 'inconnu'),
(12, '14-01-2020', 4, 4, 'inconnu'),
(13, '14-01-2020', 4, 4, 'inconnu'),
(14, '14-01-2020', 4, 4, 'inconnu'),
(15, '14-01-2020', 4, 4, 'inconnu'),
(16, '14-01-2020', 4, 4, 'inconnu'),
(17, '14-01-2020', 4, 4, 'inconnu'),
(18, '14-01-2020', 4, 4, 'inconnu'),
(19, '14-01-2020', 4, 4, 'inconnu'),
(20, '14-01-2020', 4, 4, 'inconnu');

-- --------------------------------------------------------

--
-- Structure de la table `reservationgroupe`
--

DROP TABLE IF EXISTS `reservationgroupe`;
CREATE TABLE IF NOT EXISTS `reservationgroupe` (
  `id` int(30) DEFAULT NULL,
  `sejour` int(30) NOT NULL,
  `groupe` int(30) NOT NULL,
  `place` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sejour`
--

DROP TABLE IF EXISTS `sejour`;
CREATE TABLE IF NOT EXISTS `sejour` (
  `id_sejour` int(11) NOT NULL AUTO_INCREMENT,
  `duree` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `type_sejour` varchar(40) NOT NULL,
  `centre_id` int(11) NOT NULL,
  `prix` int(5) NOT NULL DEFAULT 0,
  `age_min` int(3) NOT NULL DEFAULT 0,
  `age_max` int(3) NOT NULL DEFAULT 0,
  `capacite` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_sejour`),
  KEY `centre_id` (`centre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `sejour`
--

INSERT INTO `sejour` (`id_sejour`, `duree`, `date_debut`, `date_fin`, `type_sejour`, `centre_id`, `prix`, `age_min`, `age_max`, `capacite`) VALUES
(1, 13, '2019-07-08', '2019-07-27', 'forêt et animaux', 1, 560, 6, 9, 0),
(2, 13, '2019-08-05', '2019-08-17', 'forêt et animaux', 1, 560, 6, 9, 0),
(3, 13, '2019-08-19', '2019-08-31', 'forêt et animaux', 1, 560, 6, 9, 0),
(4, 7, '2019-07-08', '2019-07-14', 'un sejour chez les indiens', 2, 380, 10, 13, 0),
(5, 12, '2019-08-05', '2019-08-16', 'un sejour chez les indiens', 2, 650, 10, 13, 0);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  `type` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `login`, `password`, `type`) VALUES
(1, 'admin', 'admin', 'admin');

-- --------------------------------------------------------

--
-- Structure de la table `userr`
--

DROP TABLE IF EXISTS `userr`;
CREATE TABLE IF NOT EXISTS `userr` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `userr`
--

INSERT INTO `userr` (`id`, `name`) VALUES
(1, 'Jack Rutorial demo 1'),
(2, 'Jack Rutorial demo 2'),
(3, 'Jack Rutorial demo 3');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annulation`
--
ALTER TABLE `annulation`
  ADD CONSTRAINT `annulation_ibfk_1` FOREIGN KEY (`idsejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `annulation_ibfk_2` FOREIGN KEY (`idclient`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `associationgroupesejour`
--
ALTER TABLE `associationgroupesejour`
  ADD CONSTRAINT `associationgroupesejour_ibfk_1` FOREIGN KEY (`groupe`) REFERENCES `groupe` (`id_groupe`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `associationgroupesejour_ibfk_2` FOREIGN KEY (`id_sejour`) REFERENCES `sejour` (`id_sejour`) ON DELETE CASCADE ON UPDATE CASCADE;

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
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

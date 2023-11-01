CREATE DATABASE  IF NOT EXISTS `projet_jeu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projet_jeu`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: projet_jeu
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `attaque`
--

DROP TABLE IF EXISTS `attaque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attaque` (
  `idAttaque` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `dateNais` varchar(45) NOT NULL,
  `salaire` double unsigned NOT NULL,
  `talent` varchar(45) NOT NULL,
  `nbPoints` int unsigned NOT NULL,
  `equipe` int unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`idAttaque`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attaque`
--

LOCK TABLES `attaque` WRITE;
/*!40000 ALTER TABLE `attaque` DISABLE KEYS */;
INSERT INTO `attaque` VALUES (1,'Titi','Toto','25 Octobre 2002',500,'Débutant',50,0),(2,'Tarte','Thomas','27 Août 1985',500,'Débutant',50,0),(3,'Tremblay','Roger','2 Décembre 1995',500,'Débutant',50,0),(4,'Brent','Julien','4 Avril 1994',800,'Débutant',75,0),(5,'Desnoyers','Basil','5 Mai 1995',450,'Débutant',40,0),(6,'Daigle','Matis','16 Février 2000',550,'Débutant ',60,0),(7,'Vaillancourt','Thomas','4 Décembre 2005',850,'Intermédiaire',100,0),(8,'Verville','Henri','11 Mars 1984',875,'Intermédiaire',115,0),(9,'Perron','Matheo','7 Octobre 1974',900,'Intermédiaire',95,0),(10,'Bouffart','Dorien','8 Juillet 1986',750,'Intermédiaire',105,0),(11,'Gouin','Noah','22 Juin 1992',1100,'Intermédiaire',110,0),(12,'Comtois','Eliott','16 Septembre 2001',1100,'Intermédiaire',112,0),(13,'Bisson','Etienne','2 Novembre 1989',1250,'Intermédiaire',125,0),(14,'Doiron','James','8 Mai 1999',1111,'Intermédiaire',111,0),(15,'Dufour','Michel','17 Juin 2001',1300,'Intermédiaire',130,0),(16,'Dasilva','Hugo','17 Janvier 1998',1400,'Intermédiaire',140,0),(17,'Allaire','Charles','2 Février 200',1500,'Expérimenté',150,0),(18,'Gosselin','Louka','7 Mars 2000',1800,'Expérimenté',180,0),(19,'Moisan','Ryan','25 Mai 2002',1550,'Expérimenté',175,0),(20,'Lamothe','Dominic','2 Février 2002',2000,'Expérimenté',200,0),(21,'Du Bois','Harry','8 Mars 1979',1475,'Expérimenté',157,0),(22,'Lamontagne','Alex','1 Janvier 1980',1900,'Expérimenté',190,0);
/*!40000 ALTER TABLE `attaque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `defense`
--

DROP TABLE IF EXISTS `defense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `defense` (
  `idDefense` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `dateNais` varchar(45) NOT NULL,
  `salaire` double unsigned NOT NULL,
  `talent` varchar(45) NOT NULL,
  `nbPoints` int unsigned NOT NULL,
  `equipe` int unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`idDefense`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `defense`
--

LOCK TABLES `defense` WRITE;
/*!40000 ALTER TABLE `defense` DISABLE KEYS */;
INSERT INTO `defense` VALUES (1,'Gros','Minet','25 Octobre 1988',1000,'Intermédiaire',100,0),(2,'Doyon','Martin','18 Avril 2003',1000,'Intermédiaire',100,0),(3,'Gonthier','Alex','2 Décembre 1995',500,'Débutant',50,0),(4,'Létourneau','Frédéric','4 Avril 1994',800,'Débutant',75,0),(5,'Després','Anatole','5 Mai 1995',450,'Débutant',40,0),(6,'Villeneuve','Florian','16 Février 2000',550,'Débutant',60,0),(7,'Blanchard','Nicolas','4 Décembre 2005',850,'Intermédiaire',100,0),(8,'Belley','William','11 Mars 1984',875,'Intermédiaire',115,0),(9,'Arsenault','Vincent','7 Octobre 1974',900,'Intermédiaire',95,0),(10,'Léveillé','Simon','8 Juillet 1986',750,'Intermédiaire',105,0),(11,'Claire','Antoine','22 Juin 1992',1100,'Intermédiaire',110,0),(12,'Lachance','François','16 Septembre 2001',1100,'Intermédiaire',112,0),(13,'Généreux','Laurent','2 Novembre 1989',1250,'Intermédiaire',125,0),(14,'Doiron','James','8 Mai 1999',1111,'Intermédiaire',111,0),(15,'Poulin','Louis','17 Juin 2001',1300,'Intermédiaire',130,0),(16,'Ledoux','Mathieu','17 Janvier 1998',1400,'Intermédiaire',140,0),(17,'Gervais','Joel','2 Février 2000',1500,'Expérimenté',150,0),(18,'Cyr','David','7 Mars 2000',1800,'Expérimenté',180,0),(19,'Joie','Joseph','25 Mai 2002',1550,'Expérimenté',175,0),(20,'Bissonnette','Leon','2 Février 2002',2000,'Expérimenté',200,0),(21,'Morgan','Arthur','15 Mai 1987',1800,'Expérimenté',188,0),(22,'Sénécal','Emmanuel','1 Janvier 1980',1900,'Expérimenté',190,0);
/*!40000 ALTER TABLE `defense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entraineur`
--

DROP TABLE IF EXISTS `entraineur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entraineur` (
  `idEntraineur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `dateNais` varchar(45) NOT NULL,
  `salaire` double unsigned NOT NULL,
  `talent` varchar(45) NOT NULL,
  `nbPoints` int unsigned NOT NULL,
  `equipe` int unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`idEntraineur`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entraineur`
--

LOCK TABLES `entraineur` WRITE;
/*!40000 ALTER TABLE `entraineur` DISABLE KEYS */;
INSERT INTO `entraineur` VALUES (1,'House','Robert','25 Juin 2020',1738,'Avancé',138,0),(2,'Sungo','Ku','25 Octobre 2002',1500,'Intermédiaire',140,0);
/*!40000 ALTER TABLE `entraineur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipes`
--

DROP TABLE IF EXISTS `equipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipes` (
  `idEquipes` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `budget` double NOT NULL,
  `nbPoints` int unsigned DEFAULT NULL,
  `nbAttaque` int unsigned DEFAULT NULL,
  `nbDefense` int unsigned DEFAULT NULL,
  `nbGardien` int unsigned DEFAULT NULL,
  `nbEntraineur` int unsigned DEFAULT NULL,
  PRIMARY KEY (`idEquipes`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipes`
--

LOCK TABLES `equipes` WRITE;
/*!40000 ALTER TABLE `equipes` DISABLE KEYS */;
INSERT INTO `equipes` VALUES (1,'Les Champions',85000,NULL,NULL,NULL,NULL,NULL),(2,'Les Audacieux',63000,NULL,NULL,NULL,NULL,NULL),(3,'Les Nouveaux',20000,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `equipes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gardien`
--

DROP TABLE IF EXISTS `gardien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gardien` (
  `idGardien` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `dateNais` varchar(45) NOT NULL,
  `salaire` double unsigned NOT NULL,
  `talent` varchar(45) NOT NULL,
  `nbPoints` int unsigned NOT NULL,
  `equipe` int unsigned NOT NULL DEFAULT 0,
  PRIMARY KEY (`idGardien`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gardien`
--

LOCK TABLES `gardien` WRITE;
/*!40000 ALTER TABLE `gardien` DISABLE KEYS */;
INSERT INTO `gardien` VALUES (1,'Tom','Timtim','18 Janvier 1984',2000,'Avancé',100,0),(2,'Jerry','Tomtom','30 Décembre 2000',2000,'Intermédiaire',100,0),(3,'Meunier','Damien','1 Janvier 2000',2200,'Intermédiaire',105,0),(4,'Simard','Daniel','4 Avril 1990',2050,'Intermédiaire',110,0);
/*!40000 ALTER TABLE `gardien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'projet_jeu'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-08 22:06:55

-- MariaDB dump 10.19  Distrib 10.4.22-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: DISKODENDA
-- ------------------------------------------------------
-- Server version	10.4.22-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `DISKODENDA`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `diskodenda` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `DISKODENDA`;

--
-- Table structure for table `abestia`
--

DROP TABLE IF EXISTS `abestia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `abestia` (
  `IZENA` varchar(15) NOT NULL,
  `ZENBAKIA` int(11) DEFAULT NULL,
  `DISKOK` int(11) NOT NULL,
  PRIMARY KEY (`IZENA`,`DISKOK`),
  KEY `DISKOK` (`DISKOK`),
  CONSTRAINT `abestia_ibfk_1` FOREIGN KEY (`DISKOK`) REFERENCES `disko` (`KODEA`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abestia`
--

LOCK TABLES `abestia` WRITE;
/*!40000 ALTER TABLE `abestia` DISABLE KEYS */;
INSERT INTO `abestia` VALUES ('18',2,7),('1917',1,9),('8 Letters',1,5),('Al mar',4,4),('Alma',3,16),('Am I Wrong',1,3),('Animals',2,10),('Atrapado',1,6),('Bad Liar',5,11),('Bertsoa',5,12),('Beude',4,13),('Boomerang',2,11),('Choker',2,1),('Choose',3,5),('Cool Out',4,11),('Dalai Lama',3,9),('De acero',1,14),('Deltoya',2,4),('Deltoya',3,14),('Despertar',3,6),('Don\'t Panic',1,2),('Easier',3,8),('Egun Bat',2,12),('Euritan Dantzan',1,12),('Feelings',5,10),('Feo',5,4),('Fireproof',5,7),('First Love',4,3),('Fool\'s Gold',4,7),('Friends',4,5),('Fuego',2,6),('Gaia',1,16),('Garabatos',3,4),('Girl Almighty',3,7),('Good Day',1,1),('Hard',5,5),('Hyacinth House',5,15),('Infrasoinuak',2,13),('JC',4,9),('Katedral Bat',5,13),('L\'America',4,15),('L.A. Woman',3,15),('La conquista',2,16),('Last Supper',2,17),('Lie',2,3),('Loretxoa',4,12),('Los globos',5,6),('Love Her Madly',2,15),('Lucha contigo',2,14),('Lunatic',1,17),('Machine',3,11),('MAMA',5,3),('Maps',1,10),('Natural',1,11),('No Shame',1,8),('Old Me',2,8),('Papel secante',5,14),('Pobre siri',4,6),('Saturday',5,1),('Sentia',5,9),('Shiver',2,2),('Shy Away',3,1),('Si te vas',4,16),('Sparks',4,2),('Spies',3,2),('Spoiler!',3,13),('Steal My Girl',1,7),('Stigma',3,3),('Sugar',4,10),('Sunny D',3,17),('Talk',2,5),('Teeth',4,8),('The Changeling',1,15),('The Outside',4,1),('Tu',2,9),('Unkiss Me',3,10),('Viene y va',1,4),('Volando solo',4,14),('Wildflower',5,8),('Yellow',5,2),('Zer da?',3,12),('Zuri',1,13);
/*!40000 ALTER TABLE `abestia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disko`
--

DROP TABLE IF EXISTS `disko`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disko` (
  `IZENA` varchar(15) DEFAULT NULL,
  `KODEA` int(11) NOT NULL,
  `PREZIOA` float DEFAULT NULL,
  `SALDUTAKOKOPIAK` int(11) DEFAULT NULL,
  `TALDEK` int(11) DEFAULT NULL,
  PRIMARY KEY (`KODEA`),
  KEY `TALDEK` (`TALDEK`),
  CONSTRAINT `disko_ibfk_1` FOREIGN KEY (`TALDEK`) REFERENCES `talde` (`KODEA`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disko`
--

LOCK TABLES `disko` WRITE;
/*!40000 ALTER TABLE `disko` DISABLE KEYS */;
INSERT INTO `disko` VALUES ('Scaled and Icy',1,16.69,50,1),('Parachutes',2,13.5,50,2),('Wings',3,17.89,50,3),('Fitografia',4,14.2,50,4),('8 Letters',5,15.66,50,5),('Fuego',6,12.99,50,6),('Four',7,19.99,50,7),('Calm',8,18.8,50,8),('Aidalai',9,16.5,50,9),('V',10,13.77,50,10),('Origins',11,14.75,50,11),('Euritan Dantzan',12,15.65,50,12),('Infrasoinuak',13,14.8,50,13),('Deltoya',14,17.45,50,14),('L.A. Woman',15,12.99,50,15),('Gaia',16,15.35,50,16),('Lady Jesus',17,12.22,50,17);
/*!40000 ALTER TABLE `disko` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gira`
--

DROP TABLE IF EXISTS `gira`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gira` (
  `HASDATA` date NOT NULL,
  `BUKDATA` date DEFAULT NULL,
  `TALDEK` int(11) NOT NULL,
  PRIMARY KEY (`HASDATA`,`TALDEK`),
  KEY `TALDEK` (`TALDEK`),
  CONSTRAINT `gira_ibfk_1` FOREIGN KEY (`TALDEK`) REFERENCES `talde` (`KODEA`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gira`
--

LOCK TABLES `gira` WRITE;
/*!40000 ALTER TABLE `gira` DISABLE KEYS */;
INSERT INTO `gira` VALUES ('2014-04-25','2014-10-05',7),('2015-02-07','2015-10-31',7),('2015-03-18','2016-12-06',3),('2017-08-13','2017-09-15',8),('2018-08-25','2019-09-07',3),('2018-10-16','2019-06-30',1),('2020-10-03','2021-04-08',15),('2021-11-15','2022-06-06',14),('2022-02-04','2022-11-12',9),('2022-02-14','2022-11-07',15),('2022-03-14','2022-12-23',13),('2022-03-28','2022-07-23',4),('2022-05-04','2022-12-10',8),('2022-05-04','2022-09-15',11),('2022-05-05','2022-10-22',17),('2022-05-06','2022-10-29',2),('2022-05-06','2022-10-11',6),('2022-05-06','2022-08-27',10),('2022-05-28','2022-09-24',1),('2022-05-28','2022-07-30',12),('2022-06-05','2023-01-10',16),('2022-06-17','2022-08-24',5);
/*!40000 ALTER TABLE `gira` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lekua`
--

DROP TABLE IF EXISTS `lekua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lekua` (
  `IZENA` varchar(15) NOT NULL,
  `HIRIA` varchar(15) NOT NULL,
  `HERRIALDEA` varchar(15) NOT NULL,
  PRIMARY KEY (`IZENA`,`HIRIA`,`HERRIALDEA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lekua`
--

LOCK TABLES `lekua` WRITE;
/*!40000 ALTER TABLE `lekua` DISABLE KEYS */;
INSERT INTO `lekua` VALUES ('A. Le Coq Arena','Tallinn','Estonia'),('Arena Riga','Riga','Letonia'),('Aviva','Dublin','Irlanda'),('Dodger','San Francisco','AEB'),('E. Balduino','Bruselas','Belgika'),('E. LFF','Vilna','Lituania'),('E. Olinpikoa','Atenas','Grezia'),('E. Olinpikoa','Berlin','Alemania'),('E. Olinpikoa','Kiev','Ukrania'),('E. Olinpikoa','Paris','Frantzia'),('E. Olinpikoa','Roma','Italia'),('E. Olinpikoa','Seul','Hego Korea'),('E. Olinpikoa','Tokio','Japon'),('Friends Arena','Estokolmo','Suedia'),('Intility Arena','Oslo','Noruega'),('Matmut','Burdeos','Frantzia'),('Wembley','Londres','BH');
/*!40000 ALTER TABLE `lekua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lekuan_jo`
--

DROP TABLE IF EXISTS `lekuan_jo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lekuan_jo` (
  `IZENA` varchar(15) NOT NULL,
  `HIRIA` varchar(15) NOT NULL,
  `HERRIALDEA` varchar(15) NOT NULL,
  `HASDATA` date NOT NULL,
  `TALDEK` int(11) NOT NULL,
  `PREZIOA` float DEFAULT 0,
  `SALDUTAKOSARRERAK` int(11) DEFAULT 0,
  PRIMARY KEY (`IZENA`,`HIRIA`,`HERRIALDEA`,`HASDATA`,`TALDEK`),
  KEY `HASDATA` (`HASDATA`,`TALDEK`),
  CONSTRAINT `lekuan_jo_ibfk_1` FOREIGN KEY (`IZENA`, `HIRIA`, `HERRIALDEA`) REFERENCES `lekua` (`IZENA`, `HIRIA`, `HERRIALDEA`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lekuan_jo_ibfk_2` FOREIGN KEY (`HASDATA`, `TALDEK`) REFERENCES `gira` (`HASDATA`, `TALDEK`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lekuan_jo`
--

LOCK TABLES `lekuan_jo` WRITE;
/*!40000 ALTER TABLE `lekuan_jo` DISABLE KEYS */;
INSERT INTO `lekuan_jo` VALUES ('A. Le Coq Arena','Tallinn','Estonia','2022-05-06',10,25,100),('Arena Riga','Riga','Letonia','2022-02-04',9,28,100),('Aviva','Dublin','Irlanda','2018-10-16',1,30,100),('Aviva','Dublin','Irlanda','2022-03-28',4,25,100),('Aviva','Dublin','Irlanda','2022-06-05',16,30,100),('Dodger','San Francisco','AEB','2022-05-05',17,30,100),('Dodger','San Francisco','AEB','2022-05-06',6,28,100),('E. Balduino','Bruselas','Belgika','2014-04-25',7,25,100),('E. LFF','Vilna','Lituania','2022-05-04',11,28,100),('E. LFF','Vilna','Lituania','2022-05-06',2,25,100),('E. Olinpikoa','Atenas','Grezia','2017-08-13',8,25,100),('E. Olinpikoa','Berlin','Alemania','2018-10-16',1,25,100),('E. Olinpikoa','Berlin','Alemania','2021-11-15',14,20,100),('E. Olinpikoa','Kiev','Ukrania','2022-05-28',12,25,100),('E. Olinpikoa','Paris','Frantzia','2020-10-03',15,28,100),('E. Olinpikoa','Paris','Frantzia','2022-05-28',1,28,100),('E. Olinpikoa','Roma','Italia','2022-03-14',13,30,100),('E. Olinpikoa','Seul','Hego Korea','2018-08-25',3,50,100),('E. Olinpikoa','Tokio','Japon','2015-03-18',3,20,100),('E. Olinpikoa','Tokio','Japon','2018-08-25',3,40,100),('Friends Arena','Estokolmo','Suedia','2015-02-07',7,36,100),('Intility Arena','Oslo','Noruega','2022-05-04',8,20,100),('Matmut','Burdeos','Frantzia','2022-02-14',15,25,100),('Wembley','Londres','BH','2015-03-18',3,28,100),('Wembley','Londres','BH','2022-05-28',1,20,100),('Wembley','Londres','BH','2022-06-17',5,30,100);
/*!40000 ALTER TABLE `lekuan_jo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partaidea`
--

DROP TABLE IF EXISTS `partaidea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partaidea` (
  `IZENA` varchar(15) DEFAULT NULL,
  `KODEA` int(11) NOT NULL,
  `TALDEK` int(11) DEFAULT NULL,
  PRIMARY KEY (`KODEA`),
  KEY `TALDEK` (`TALDEK`),
  CONSTRAINT `partaidea_ibfk_1` FOREIGN KEY (`TALDEK`) REFERENCES `talde` (`KODEA`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partaidea`
--

LOCK TABLES `partaidea` WRITE;
/*!40000 ALTER TABLE `partaidea` DISABLE KEYS */;
INSERT INTO `partaidea` VALUES ('Tyler Joseph',11,1),('Josh Dun',12,1),('Nick Thomas',13,1),('Chris Martin',21,2),('Guy Berryman',22,2),('Phil Harvey',23,2),('Jon Buckland',24,2),('Will Champion',25,2),('Jin',31,3),('Suga',32,3),('J-Hope',33,3),('RM',34,3),('Jimin',35,3),('V',36,3),('Jungkook',37,3),('Fito Cabrales',41,4),('Carlos Raya',42,4),('Javier Alzola',43,4),('Coki Gimenez',44,4),('A. Climent',45,4),('Corbyn Besson',51,5),('Daniel Seavey',52,5),('Zachary Herron',53,5),('Jack Avery',54,5),('Jonah Marais',55,5),('David Muinoz',61,6),('Jose Muinoz',62,6),('Harry Styles',71,7),('Zayn Malik',72,7),('Louis Tomlinson',73,7),('Liam Payne',74,7),('Niall Horan',75,7),('Luke Hemmings',81,8),('Calum Hood',82,8),('Ashton Irwin ',83,8),('M. Clifford',84,8),('Ana Torroja',91,9),('Nacho Cano',92,9),('Jose M. Cano',93,9),('R. Rietveld',94,9),('Adam Levine',101,10),('James Valentine',102,10),('Matt Flynn',103,10),('P. J. Morton',104,10),('J. Carmichael',105,10),('Sam Farrar',106,10),('Dan Reynolds',111,11),('Ben McKee',112,11),('D. W. Sermon',113,11),('Daniel Platzman',114,11),('Alex Sardui',121,12),('Mikel Caballero',122,12),('Haimar Arejita',123,12),('Gaizka Salazar',124,12),('Gorka Urbizu',131,13),('Galder Izagirre',132,13),('Aitor Goikoetxe',133,13),('Roberto Iniesta',141,14),('Inaki Anton',142,14),('J. I. Cantera',143,14),('Miguel Colino',144,14),('John Densmore',151,15),('Robby Krieger',152,15),('J. Dominguez',161,16),('Patricia Tapia',162,16),('J. Hernandez',163,16),('Carlos Prieto',164,16),('Taylor Upsahl',171,17);
/*!40000 ALTER TABLE `partaidea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produktorea`
--

DROP TABLE IF EXISTS `produktorea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produktorea` (
  `IZENA` varchar(15) DEFAULT NULL,
  `KODEA` int(11) NOT NULL,
  `TELEFONOA` int(11) DEFAULT NULL,
  PRIMARY KEY (`KODEA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produktorea`
--

LOCK TABLES `produktorea` WRITE;
/*!40000 ALTER TABLE `produktorea` DISABLE KEYS */;
INSERT INTO `produktorea` VALUES ('George Martin',1,666),('Max Martin',2,333),('Dr. Dre',3,222),('Rick Rubin',4,111),('Nile Rodgers',5,444),('Sylvia Massy',6,888),('Imogen Heap',7,999),('F. O\'Connell',8,777),('Hit-Boy',9,555),('Metro Booming',10,123),('Jetsonmade',11,456),('Wondagurl',12,789),('The Alchemist',13,987),('J. White Did It',14,654),('Quay Global',15,321),('Z3n',16,101);
/*!40000 ALTER TABLE `produktorea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talde`
--

DROP TABLE IF EXISTS `talde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `talde` (
  `KODEA` int(11) NOT NULL,
  `IZENA` varchar(15) NOT NULL,
  `DESKRIBAPENA` varchar(15) DEFAULT NULL,
  `PRODKODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`KODEA`),
  UNIQUE KEY `IZENA` (`IZENA`),
  KEY `PRODKODE` (`PRODKODE`),
  CONSTRAINT `talde_ibfk_1` FOREIGN KEY (`PRODKODE`) REFERENCES `produktorea` (`KODEA`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talde`
--

LOCK TABLES `talde` WRITE;
/*!40000 ALTER TABLE `talde` DISABLE KEYS */;
INSERT INTO `talde` VALUES (1,'21 Pilots','Electropop',13),(2,'Coldplay','Alternative',4),(3,'BTS','KPOP',6),(4,'Fito&Fitipaldis','Spanish Rock',15),(5,'Why Don\'t We','Pop',8),(6,'Estopa','Rock',5),(7,'One Direction','Pop',1),(8,'5SOS','Pop',2),(9,'Mecano','Pop',10),(10,'Maroon 5','Pop',3),(11,'Imagine Dragons','Alternative',16),(12,'Gatibu','Rock',11),(13,'Berri Txarrak','Rock',14),(14,'Extremoduro','Rock',12),(15,'The Doors','Rock',7),(16,'Mago de Oz','Spanish Folk',9),(17,'Upsahl','Pop',1);
/*!40000 ALTER TABLE `talde` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-05  0:35:36

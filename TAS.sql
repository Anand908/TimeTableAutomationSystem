-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: tas
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `advancetable`
--

DROP TABLE IF EXISTS `advancetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `advancetable` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `Class` varchar(25) DEFAULT NULL,
  `Section` varchar(25) DEFAULT NULL,
  `Subject` varchar(25) DEFAULT NULL,
  `Teacher` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advancetable`
--

LOCK TABLES `advancetable` WRITE;
/*!40000 ALTER TABLE `advancetable` DISABLE KEYS */;
INSERT INTO `advancetable` VALUES (26,'1','A','Financial Management','Abdul Rub'),(30,'1','A','Programming with Java','Vineesh  Cutting');
/*!40000 ALTER TABLE `advancetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classdetails`
--

DROP TABLE IF EXISTS `classdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classdetails` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `ClassID` varchar(10) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classdetails`
--

LOCK TABLES `classdetails` WRITE;
/*!40000 ALTER TABLE `classdetails` DISABLE KEYS */;
INSERT INTO `classdetails` VALUES (4,'4','Class 4'),(6,'6','Class 6'),(7,'7','Class 7'),(8,'8','Class 8'),(9,'9','Class 9');
/*!40000 ALTER TABLE `classdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroomdetails`
--

DROP TABLE IF EXISTS `classroomdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroomdetails` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `ClassId` varchar(10) NOT NULL,
  `Section` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ClassId`),
  UNIQUE KEY `SN` (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroomdetails`
--

LOCK TABLES `classroomdetails` WRITE;
/*!40000 ALTER TABLE `classroomdetails` DISABLE KEYS */;
INSERT INTO `classroomdetails` VALUES (19,'1','A,B,C'),(10,'10','A,B,C'),(9,'11','A,B'),(8,'12','A,B,C,D'),(18,'2','A,B,C,D'),(16,'3','A,B,C,D,E'),(17,'4','A,B'),(15,'5','A,B'),(14,'6','A,B,C,D'),(13,'7','A'),(12,'8','A,B,C'),(11,'9','A,B,C,D'),(1,'LKG','A,B'),(7,'UKG','A,B');
/*!40000 ALTER TABLE `classroomdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `labdetails`
--

DROP TABLE IF EXISTS `labdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `labdetails` (
  `SR` bigint NOT NULL AUTO_INCREMENT,
  `labName` varchar(30) DEFAULT NULL,
  `labID` varchar(15) NOT NULL,
  PRIMARY KEY (`labID`),
  UNIQUE KEY `SR` (`SR`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `labdetails`
--

LOCK TABLES `labdetails` WRITE;
/*!40000 ALTER TABLE `labdetails` DISABLE KEYS */;
INSERT INTO `labdetails` VALUES (1,'Science','1'),(2,'Physics','2'),(4,'Chemistry','21'),(3,'Biology','3'),(8,'Basic Electronics','Lab1'),(7,'Computer Lab','Lab3'),(9,'Anand','lab5'),(6,'Electronics Lab','LAB6');
/*!40000 ALTER TABLE `labdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectclassdetails`
--

DROP TABLE IF EXISTS `subjectclassdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectclassdetails` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) DEFAULT NULL,
  `ClassID` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectclassdetails`
--

LOCK TABLES `subjectclassdetails` WRITE;
/*!40000 ALTER TABLE `subjectclassdetails` DISABLE KEYS */;
INSERT INTO `subjectclassdetails` VALUES (14,'S12','1'),(15,'M12','2'),(16,'S12','2'),(17,'M12','1'),(18,'S12','2'),(19,'M12','5');
/*!40000 ALTER TABLE `subjectclassdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjectdetails`
--

DROP TABLE IF EXISTS `subjectdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjectdetails` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `Code` varchar(10) DEFAULT NULL,
  `Title` varchar(255) DEFAULT NULL,
  `Credit` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjectdetails`
--

LOCK TABLES `subjectdetails` WRITE;
/*!40000 ALTER TABLE `subjectdetails` DISABLE KEYS */;
INSERT INTO `subjectdetails` VALUES (1,'BAM 431','Financial Management','4(3+1+0)'),(2,'CSIT 423','Programming with Java','5(3+0+4)'),(3,'CSIT 504','Internet and Web Technologies','5(3+0+4)'),(4,'CSIT 505','Relational Database Management System','4(2+1+2)'),(5,'CSIT 511','Principles of Computer Network','5(3+1+2)'),(6,'CSIT 515','Principles of Software Engineering','4(3+1+0)');
/*!40000 ALTER TABLE `subjectdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacherdetails`
--

DROP TABLE IF EXISTS `teacherdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacherdetails` (
  `SN` bigint NOT NULL AUTO_INCREMENT,
  `FName` varchar(15) DEFAULT NULL,
  `LName` varchar(15) DEFAULT NULL,
  `collegeId` varchar(15) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`collegeId`),
  UNIQUE KEY `SN` (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacherdetails`
--

LOCK TABLES `teacherdetails` WRITE;
/*!40000 ALTER TABLE `teacherdetails` DISABLE KEYS */;
INSERT INTO `teacherdetails` VALUES (27,'Dileep','Kumar','Emp64','dileep@shiats.edu.in'),(1,'Vineesh ','Cutting','Emp65','vineesh@shiats.edu.in'),(26,'Abdul','Rub','Emp66','abdul@shiats.edu.in'),(28,'Mohit ','Paul','Emp67','mohit@shiats.edu.in'),(29,'Mudita','Srivastav','Emp68','mudita@shiats.edu.in'),(30,'Wilson','Jeberson','Emp69','wilson@shiats.edu.in'),(31,'Prateek','Singh','Emp70','prateek@gmail.com'),(32,'Satya','Mishra','Emp71','satya.prakash@shiats.edu.in');
/*!40000 ALTER TABLE `teacherdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachersubjectdetails`
--

DROP TABLE IF EXISTS `teachersubjectdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teachersubjectdetails` (
  `SN` int NOT NULL AUTO_INCREMENT,
  `CollegeId` varchar(30) DEFAULT NULL,
  `Code` varchar(10) DEFAULT NULL,
  `ClassID` varchar(10) NOT NULL,
  `Section` varchar(10) NOT NULL,
  PRIMARY KEY (`SN`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachersubjectdetails`
--

LOCK TABLES `teachersubjectdetails` WRITE;
/*!40000 ALTER TABLE `teachersubjectdetails` DISABLE KEYS */;
INSERT INTO `teachersubjectdetails` VALUES (12,'Ajendra 123','M12','3','Section B'),(15,'Abhishek Emp96','H12','5','Section B'),(16,'Ajendra 123','M12','8','Section B'),(17,'Ajendra 123','M12','9','Section C');
/*!40000 ALTER TABLE `teachersubjectdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `timetableclass`
--

DROP TABLE IF EXISTS `timetableclass`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timetableclass` (
  `SR` bigint NOT NULL AUTO_INCREMENT,
  `s00` varchar(50) DEFAULT NULL,
  `s01` varchar(50) DEFAULT NULL,
  `s02` varchar(50) DEFAULT NULL,
  `s03` varchar(50) DEFAULT NULL,
  `s04` varchar(50) DEFAULT NULL,
  `s05` varchar(50) DEFAULT NULL,
  `s10` varchar(50) DEFAULT NULL,
  `s11` varchar(50) DEFAULT NULL,
  `s12` varchar(50) DEFAULT NULL,
  `s13` varchar(50) DEFAULT NULL,
  `s14` varchar(50) DEFAULT NULL,
  `s15` varchar(50) DEFAULT NULL,
  `s20` varchar(50) DEFAULT NULL,
  `s21` varchar(50) DEFAULT NULL,
  `s22` varchar(50) DEFAULT NULL,
  `s23` varchar(50) DEFAULT NULL,
  `s24` varchar(50) DEFAULT NULL,
  `s25` varchar(50) DEFAULT NULL,
  `s30` varchar(50) DEFAULT NULL,
  `s31` varchar(50) DEFAULT NULL,
  `s32` varchar(50) DEFAULT NULL,
  `s33` varchar(50) DEFAULT NULL,
  `s34` varchar(50) DEFAULT NULL,
  `s35` varchar(50) DEFAULT NULL,
  `s40` varchar(50) DEFAULT NULL,
  `s41` varchar(50) DEFAULT NULL,
  `s42` varchar(50) DEFAULT NULL,
  `s43` varchar(50) DEFAULT NULL,
  `s44` varchar(50) DEFAULT NULL,
  `s45` varchar(50) DEFAULT NULL,
  `s50` varchar(50) DEFAULT NULL,
  `s51` varchar(50) DEFAULT NULL,
  `s52` varchar(50) DEFAULT NULL,
  `s53` varchar(50) DEFAULT NULL,
  `s54` varchar(50) DEFAULT NULL,
  `s55` varchar(50) DEFAULT NULL,
  `s60` varchar(50) DEFAULT NULL,
  `s61` varchar(50) DEFAULT NULL,
  `s62` varchar(50) DEFAULT NULL,
  `s63` varchar(50) DEFAULT NULL,
  `s64` varchar(50) DEFAULT NULL,
  `s65` varchar(50) DEFAULT NULL,
  `s70` varchar(50) DEFAULT NULL,
  `s71` varchar(50) DEFAULT NULL,
  `s72` varchar(50) DEFAULT NULL,
  `s73` varchar(50) DEFAULT NULL,
  `s74` varchar(50) DEFAULT NULL,
  `s75` varchar(50) DEFAULT NULL,
  `s80` varchar(50) DEFAULT NULL,
  `s81` varchar(50) DEFAULT NULL,
  `s82` varchar(50) DEFAULT NULL,
  `s83` varchar(50) DEFAULT NULL,
  `s84` varchar(50) DEFAULT NULL,
  `s85` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timetableclass`
--

LOCK TABLES `timetableclass` WRITE;
/*!40000 ALTER TABLE `timetableclass` DISABLE KEYS */;
/*!40000 ALTER TABLE `timetableclass` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-15  6:15:26

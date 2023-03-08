-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: crs_g
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
-- Table structure for table `payment_online`
--

DROP TABLE IF EXISTS `payment_online`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_online` (
  `referenceid` varchar(45) NOT NULL,
  `card` tinyint NOT NULL,
  `cardnum` varchar(45) NOT NULL,
  `cardtype` varchar(45) NOT NULL,
  `modeoftransfer` varchar(45) NOT NULL,
  `accountnumber` varchar(45) NOT NULL,
  `ifscode` varchar(45) NOT NULL,
  PRIMARY KEY (`referenceid`),
  UNIQUE KEY `referenceid_UNIQUE` (`referenceid`),
  CONSTRAINT `referenceid_onp` FOREIGN KEY (`referenceid`) REFERENCES `payment` (`referenceid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_online`
--

LOCK TABLES `payment_online` WRITE;
/*!40000 ALTER TABLE `payment_online` DISABLE KEYS */;
INSERT INTO `payment_online` VALUES ('3e7509d7-8754-45ae-8acd-b0c24c63ae66',0,'NA','NA','asd','123123','asd'),('6a48d895-b615-4434-8881-a4fb4a0e9107',1,'1234','visa','NA','NA','NA'),('a2e15ffc-378e-45e8-8650-d22770f56c6b',0,'NA','NA','imps','12344','sbin02'),('dce45db9-0c3d-4dd1-b2db-5d2a8f9f969f',0,'NA','NA','imps','1234','sbi01');
/*!40000 ALTER TABLE `payment_online` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-03 13:28:50

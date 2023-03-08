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
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `referenceid` varchar(45) NOT NULL,
  `studentid` varchar(45) NOT NULL,
  `mode` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`referenceid`),
  UNIQUE KEY `referenceid_UNIQUE` (`referenceid`),
  KEY `payment_studentidfk_idx` (`studentid`),
  CONSTRAINT `payment_studentidfk` FOREIGN KEY (`studentid`) REFERENCES `student` (`studentid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES ('20ebaca8-d9b4-498c-9db6-3f4645ba9b55','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0),('3e7509d7-8754-45ae-8acd-b0c24c63ae66','be8547de-6c1e-4a38-a36c-6a99d7736be5','ONLINE','PAID',0),('599f4f4f-e9ea-4516-87e9-09f995562517','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0),('67f9502d-2540-45d9-8830-94fa87c058ab','928c45a5-db9b-479c-8871-84d8b26c4437','OFFLINE','PAID',0),('6a48d895-b615-4434-8881-a4fb4a0e9107','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0),('8a343ae4-9d65-4ff3-ae5e-29c5d79eee85','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0),('a2e15ffc-378e-45e8-8650-d22770f56c6b','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0),('dce45db9-0c3d-4dd1-b2db-5d2a8f9f969f','928c45a5-db9b-479c-8871-84d8b26c4437','ONLINE','PAID',0);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
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

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `userid` varchar(45) NOT NULL,
  `studentid` varchar(45) NOT NULL,
  `studentname` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  `age` int NOT NULL,
  `bloodgroup` varchar(45) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `phnum` varchar(45) NOT NULL,
  `isapproved` tinyint NOT NULL DEFAULT '0',
  `numcourses` varchar(45) NOT NULL DEFAULT '0',
  `semester` int DEFAULT NULL,
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `studentid_UNIQUE` (`studentid`),
  CONSTRAINT `studentidfk` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('41f59816-9b88-44f6-b4c5-18d9afa93ec2','1ca19bfa-2273-4517-9ffc-524f513af1f0','amy','IT',39,'A+','SSB','8126688804',1,'0',4),('58760ef5-dfb2-4d5e-a5ad-46e460388ba9','be8547de-6c1e-4a38-a36c-6a99d7736be5','kd','knd',21,'a','sds','9087654321',1,'0',4),('6137fd59-0eeb-4c56-a2de-13e615df25dc','32637060-aec3-4c4a-bb75-a8721d382424','ff','coe',12,'b+','abc','1234567890',0,'0',0),('891656be-67e1-4501-b9f3-f6ad4534f227','928c45a5-db9b-479c-8871-84d8b26c4437','Student_2','COE',22,'A-','abc','1234',1,'0',2),('95f02b2c-1c00-47e8-a336-2a832a18b097','4da3112c-ec4a-4885-b977-f9cc84a7f40f','Prathamesh','Computer Science',21,'O+','Prashant ','9811410026',0,'0',0),('96dc643d-1d2a-425d-9bcc-4a5e6b5a82af','75791bd8-0213-47f8-8cdb-49e90dc93420','Student_1','COE',22,'A+','ABC','123',1,'0',3),('a008c4a4-f579-4ad9-bf85-73053c18946e','6d5df35c-4dea-4d93-bfdf-229df2687b19','asf','c',12,'as','afs','1234567890',1,'0',0),('c4a878f8-9b0a-4164-a05b-ed8e05d7c8cc','257169c6-98b2-4d35-b94c-d0b119160751','doll','coe',11,'a+','abc','1234',1,'0',0),('f884c44d-3d3b-49a5-91fc-a6a600bfbfec','bf3de21d-3550-4e86-8b9d-0bdf1542eef6','simi','17',12,'b+','abc','1234567890',1,'0',4);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-03-03 13:28:49

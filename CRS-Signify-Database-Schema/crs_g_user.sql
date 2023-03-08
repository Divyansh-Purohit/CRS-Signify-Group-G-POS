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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userid` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `doj` date NOT NULL,
  `roleid` int NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  KEY `roleidfk_idx` (`roleid`),
  CONSTRAINT `roleidfk` FOREIGN KEY (`roleid`) REFERENCES `role` (`roleid`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('166b6e3e-2b5f-457c-95c9-ac1c66e7961d','p3','p3','del','2023-02-24',3),('1a203930-1431-4433-b776-e5d261957b20','pas','pas','pas','2023-02-24',3),('20470e07-49c1-4bcd-9dd3-99090d94f0aa','p1','p1','blr','2023-02-24',3),('2792d48d-25d7-4c85-b8d2-d9b6e36f2002','bbb','bbb','dfbd','2023-02-24',3),('2a368597-8033-4351-b94a-1b34b82e9683','faltu','abc','203','2023-02-23',3),('2f00b443-df99-4ca3-961b-bb12377af273','da','sa','aa','2023-02-24',3),('34cddb4e-0538-4632-8e15-5d5adbd01fbd','dp','dp','dp','2023-02-24',3),('41f59816-9b88-44f6-b4c5-18d9afa93ec2','amy','amy','delhi','2023-02-23',1),('49335556-9907-4b25-bc11-a244e2a28ac4','frt','frt','frt','2023-02-24',2),('4978308c-7f05-4b5b-90e1-9706341df49b','MUM','abc','MUM','2023-02-23',3),('4bbc4e98-c038-4bf1-a7e9-bbad423a35c3','p4','p4','del','2023-02-24',3),('4f374576-03d4-4a5e-a206-37168068c21c','abc','abc','203','2023-02-23',3),('5015a270-ea53-4a29-995a-c6d5b583bdfc','po','po','po','2023-02-24',3),('55dcd9ef-5387-46fd-967b-0ad490475ab9','sam','sad','wer','2023-02-24',3),('5612ff57-24a1-4ef4-b3a0-933ce149ca69','asfg','asfg','asfg','2023-02-24',3),('58760ef5-dfb2-4d5e-a5ad-46e460388ba9','kd','ks','blr','2023-02-24',1),('6137fd59-0eeb-4c56-a2de-13e615df25dc','ff','ff','ff','2023-02-24',1),('63b2b8eb-a971-42d4-8658-a80dd77dc383','ddd','ddd','ddd','2023-02-24',3),('6a890f7d-486e-4446-a90d-e8e8c5bdc9db','fff','ddd','sasa','2023-02-24',2),('6d51fdec-777e-4b2d-a708-8afe2bc6d6ba','aaa','aaa','aaa','2023-02-23',3),('72079ed6-0e08-48ba-9be2-5e0161b755bc','p2','p2','blr','2023-02-24',3),('7a70a975-7080-4b5e-a060-61ffab984494','zzz','zzz','zzz','2023-02-24',3),('82325e4b-0ebf-4698-ae2e-5bfe141c385c','bbb','bbb','asf','2023-02-23',2),('864c4256-3795-4c99-a8b7-5af8fc16564f','admin','admin','blr','2023-02-24',2),('891656be-67e1-4501-b9f3-f6ad4534f227','Student_2','abc','DEL','2023-02-23',1),('89317b64-b38a-4bab-b565-789100a8741d','bbb','bbb','bbb','2023-02-23',2),('910cf89f-33e9-4c73-9e0b-2715a7328065','ADMIN_1','abc','BLR','2023-02-23',2),('95f02b2c-1c00-47e8-a336-2a832a18b097','Prathamesh','Prathamesh','123, Powergrid','2023-02-24',1),('96dc643d-1d2a-425d-9bcc-4a5e6b5a82af','Student_1','abc','BLR','2023-02-23',1),('97cb57c9-548d-4ae2-b072-4567b134084b','pq','pq','pq','2023-02-24',3),('9804a275-d06a-4af8-aa6a-1ace6c3e2a07','ccc','ccc','ccc','2023-02-24',3),('a008c4a4-f579-4ad9-bf85-73053c18946e','asf','asf','asd','2023-02-24',1),('bdfadb39-b069-43c9-bc1c-4bd45901483b','sweety','abc','121','2023-02-22',2),('c4a878f8-9b0a-4164-a05b-ed8e05d7c8cc','doll','abc','abc','2023-02-23',1),('ca1470d6-0454-428b-b0a9-d0be60545e44','lll','ppp','ld','2023-02-24',3),('cd917e4f-9d4a-4580-87f5-5403a7321c10','der','der','der','2023-02-24',3),('cec3e701-42d0-4636-aa95-9af02050ab09','qqq','qqq','qqq','2023-02-24',3),('d4d5e4f8-44bc-436d-b8d9-48a4c1956dd9','2','2','2','2023-02-23',1),('divyanshpurohit','Divyansh','abc','blr','2023-02-23',2),('f884c44d-3d3b-49a5-91fc-a6a600bfbfec','simi','abc','blr','2023-02-24',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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

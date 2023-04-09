CREATE DATABASE  IF NOT EXISTS `certificate-system` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `certificate-system`;
-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
--
-- Host: 127.0.0.1    Database: certificate-system
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
-- Table structure for table `gift_certificates`
--

DROP TABLE IF EXISTS `gift_certificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift_certificates` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text,
  `price` float NOT NULL,
  `duration` int NOT NULL,
  `create_date` timestamp NOT NULL,
  `last_update_date` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift_certificates`
--

LOCK TABLES `gift_certificates` WRITE;
/*!40000 ALTER TABLE `gift_certificates` DISABLE KEYS */;
INSERT INTO `gift_certificates` VALUES (1,'Birthday card for Roman','Special brth day card for today, for Romans birth day',20,1,'2023-04-08 17:21:59','2023-04-08 17:21:59'),(2,'New Year for Roman','Card for new year',10,320,'2023-04-08 17:30:21','2023-04-08 17:30:21'),(3,'Happy valentines for Roman','Card for valentines',10,320,'2023-04-08 17:30:29','2023-04-08 17:30:29');
/*!40000 ALTER TABLE `gift_certificates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift_certificates_tags`
--

DROP TABLE IF EXISTS `gift_certificates_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gift_certificates_tags` (
  `gift_certificate_id` int unsigned NOT NULL,
  `tag_id` int unsigned NOT NULL,
  KEY `fk_gift_certificates_tags_gift_certificates_idx` (`gift_certificate_id`),
  KEY `fk_gift_certificates_tags_tags1_idx` (`tag_id`),
  CONSTRAINT `fk_gift_certificates_tags_gift_certificates` FOREIGN KEY (`gift_certificate_id`) REFERENCES `gift_certificates` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_gift_certificates_tags_tags1` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift_certificates_tags`
--

LOCK TABLES `gift_certificates_tags` WRITE;
/*!40000 ALTER TABLE `gift_certificates_tags` DISABLE KEYS */;
INSERT INTO `gift_certificates_tags` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `gift_certificates_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (2,'birth-day'),(1,'easter');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'certificate-system'
--

--
-- Dumping routines for database 'certificate-system'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-09 16:00:59

-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: user_dir
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `grade` int NOT NULL,
  `school_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `school_id` (`school_id`),
  CONSTRAINT `class_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'A',1,5),(2,'B',1,5),(3,'C',1,5),(4,'D',1,5),(5,'A',2,5),(6,'B',2,5),(7,'C',2,5),(8,'D',2,5),(9,'А',3,5),(10,'B',3,5),(11,'C',3,5),(12,'D',3,5),(13,'A',4,5),(14,'B',4,5),(15,'C',4,5),(16,'D',4,5),(17,'A',5,5),(18,'B',5,5),(19,'C',5,5),(20,'D',5,5),(21,'A',6,5),(22,'B',6,5),(23,'C',6,5),(24,'D',6,5),(25,'A',7,5),(26,'B',7,5),(27,'C',7,5),(28,'D',7,5),(29,'A',8,5),(30,'B',8,5),(31,'C',8,5),(32,'D',8,5),(33,'A',9,5),(34,'B',9,5),(35,'C',9,5),(36,'D',9,5),(37,'A',10,5),(38,'B',10,5),(39,'C',10,5),(40,'D',10,5),(41,'A',11,5),(42,'B',11,5),(43,'C',11,5),(44,'D',11,5),(45,'A',12,5),(46,'B',12,5),(47,'C',12,5),(48,'D',12,5);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_students`
--

DROP TABLE IF EXISTS `class_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_students` (
  `class_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`class_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `class_students_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `class_students_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_students`
--

LOCK TABLES `class_students` WRITE;
/*!40000 ALTER TABLE `class_students` DISABLE KEYS */;
INSERT INTO `class_students` VALUES (38,7),(38,9),(1,13),(34,14),(1,15),(1,17),(38,38),(41,39),(1,40);
/*!40000 ALTER TABLE `class_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_teachers`
--

DROP TABLE IF EXISTS `class_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_teachers` (
  `class_id` bigint NOT NULL,
  `teacher_id` bigint NOT NULL,
  PRIMARY KEY (`class_id`,`teacher_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `class_teachers_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `class_teachers_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_teachers`
--

LOCK TABLES `class_teachers` WRITE;
/*!40000 ALTER TABLE `class_teachers` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grade` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,2),(2,3),(3,4),(4,5),(5,6);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parent_student`
--

DROP TABLE IF EXISTS `parent_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parent_student` (
  `user_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`student_id`),
  KEY `fk_student` (`student_id`),
  CONSTRAINT `fk_parent` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_student` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parent_student`
--

LOCK TABLES `parent_student` WRITE;
/*!40000 ALTER TABLE `parent_student` DISABLE KEYS */;
INSERT INTO `parent_student` VALUES (37,35);
/*!40000 ALTER TABLE `parent_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_DIRECTOR'),(5,'ROLE_PARENT'),(4,'ROLE_STUDENT'),(3,'ROLE_TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school`
--

DROP TABLE IF EXISTS `school`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `director_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school`
--

LOCK TABLES `school` WRITE;
/*!40000 ALTER TABLE `school` DISABLE KEYS */;
INSERT INTO `school` VALUES (5,'22 G.S.Rakovski','NDK',27),(6,'Jackson Peterson 55','ul.prqsnaMarula 17',31);
/*!40000 ALTER TABLE `school` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_students`
--

DROP TABLE IF EXISTS `school_students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_students` (
  `school_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`school_id`,`student_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `school_students_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`) ON DELETE CASCADE,
  CONSTRAINT `school_students_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_students`
--

LOCK TABLES `school_students` WRITE;
/*!40000 ALTER TABLE `school_students` DISABLE KEYS */;
INSERT INTO `school_students` VALUES (6,35);
/*!40000 ALTER TABLE `school_students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_subject`
--

DROP TABLE IF EXISTS `school_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_subject` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_subject`
--

LOCK TABLES `school_subject` WRITE;
/*!40000 ALTER TABLE `school_subject` DISABLE KEYS */;
INSERT INTO `school_subject` VALUES (1,'Mathematics'),(2,'Science'),(3,'History'),(4,'Geography'),(5,'English'),(6,'Physical Education'),(7,'Chemistry'),(8,'Biology'),(9,'Physics'),(10,'Art'),(11,'Music'),(12,'Computer Science'),(13,'Economics'),(14,'Political Science'),(15,'Psychology'),(16,'Sociology'),(17,'Philosophy'),(18,'Environmental Science'),(19,'Business Studies'),(20,'Accounting'),(21,'Marketing'),(22,'French'),(23,'Spanish'),(24,'German'),(25,'Latin'),(26,'Chinese'),(27,'Japanese'),(28,'Drama'),(29,'Photography'),(30,'Engineering'),(31,'Robotics'),(32,'Astronomy'),(33,'Health Education'),(34,'Nutrition'),(35,'Religious Studies'),(36,'Ethics');
/*!40000 ALTER TABLE `school_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `school_teachers`
--

DROP TABLE IF EXISTS `school_teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_teachers` (
  `school_id` bigint NOT NULL,
  `teacher_id` bigint NOT NULL,
  PRIMARY KEY (`school_id`,`teacher_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `school_teachers_ibfk_1` FOREIGN KEY (`school_id`) REFERENCES `school` (`id`),
  CONSTRAINT `school_teachers_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `school_teachers`
--

LOCK TABLES `school_teachers` WRITE;
/*!40000 ALTER TABLE `school_teachers` DISABLE KEYS */;
INSERT INTO `school_teachers` VALUES (5,30),(6,32);
/*!40000 ALTER TABLE `school_teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_grades`
--

DROP TABLE IF EXISTS `student_grades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_grades` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `grade_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  `school_subject_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `grade_id` (`grade_id`),
  KEY `student_id` (`student_id`),
  KEY `school_subject_id` (`school_subject_id`),
  CONSTRAINT `student_grades_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`),
  CONSTRAINT `student_grades_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`),
  CONSTRAINT `student_grades_ibfk_3` FOREIGN KEY (`school_subject_id`) REFERENCES `school_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_grades`
--

LOCK TABLES `student_grades` WRITE;
/*!40000 ALTER TABLE `student_grades` DISABLE KEYS */;
INSERT INTO `student_grades` VALUES (1,3,16,4),(2,5,38,12),(3,5,18,11),(4,4,39,11),(5,1,7,1),(6,1,7,1),(7,1,7,1),(8,4,17,4);
/*!40000 ALTER TABLE `student_grades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `student_id` varchar(8) DEFAULT NULL,
  `school_id` bigint DEFAULT NULL,
  `class_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_unique_email` (`email`),
  UNIQUE KEY `student_id` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Qna','Stoeva','$2a$10$.LrmLbkJolpMiQ6v3i/Te.ndeEV6OM.7zPHUGEPqmmJYxYGKPQX76','ROLE_ADMIN','qna@gmail.com',NULL,5,NULL),(3,'Makov','daGreat','$2a$10$6MLtn.aB6lvS4fDt6yZCu.IyWLLgsbCwWUz1nmX63k/Yf9WCcwQtq','ROLE_ADMIN','admin@example.com',NULL,5,NULL),(5,'Jack','Escobar','$2a$10$APMygbAqv/n7drlKZPenHe1LBI5okJriBvkqlr.0mdkC/MiZ8Zmau','ROLE_TEACHER','sopola76@abv.bg',NULL,5,NULL),(7,'Qvor','Geq','$2a$10$CWqDBuU7/Qk9MWJTXvEKieuPKKSynt1BIVqkL0WEMaPd6L3ulisE2','ROLE_STUDENT','azsymgei@example.com','Z910431',5,38),(8,'Jack','Kishimoto','$2a$10$70tV8VbIeUb9t2ncYapuWuN3xlUkx.786pyLDvEyNZxsRp6QezWA6','ROLE_STUDENT','JK@abv.bg','X416435',5,NULL),(9,'Qvor','Buchvata','$2a$10$kgd2pcvd5o0rAycjYALfou8JDv1kSErM33nbDGwx7uKN6hjM6zAqG','ROLE_STUDENT','QB@abv.bg','G712551',5,38),(10,'Masashi','Makov','$2a$10$Z7Ck7j7dLmCefP0Dy8..ue8CMyQv9k2f2oQn0T/yA.80Bl9lWKMYi','ROLE_STUDENT','Mm@MAKOV.COM','D364034',5,NULL),(11,'Qna','Petrow','$2a$10$cc2yKv1AgUDEzv4mqiBANep6xAsaDOwg6DAlPVnUS0XnhSHh4XqBq','ROLE_STUDENT','QP@abv.bg','Q266170',5,NULL),(12,'Qna','Escobar','$2a$10$yPdFnztQTc1Pw5adzpSAOenVLkK1X8kwrjHiVbVlAi61B3tlcHBr2','ROLE_STUDENT','QK@abv.com','X120533',5,NULL),(13,'Jack','Makov','$2a$10$8WGfX4gMO7o1653ssL3rOefkOGlOFrL48by5GA2w16gxAGfkPUwvi','ROLE_STUDENT','MakJak@gmail.com','V434752',5,1),(14,'Lachezar','Makov','$2a$10$ISP6pg47/dmUxTeoscTr6eMxpCEI1tKGgkaCe6ZAtoRRXuNjs/vtW','ROLE_STUDENT','blackkitty69lmao@gmail.com','Q592933',5,NULL),(15,'Jack','Escobar','$2a$10$6Lz7uwLsiDjzu9avBM4zvumevXeMZoc/S8hOb0M.EEtL7nO5k9Psi','ROLE_STUDENT','admin1@example.com','U296789',5,1),(16,'Jackson','Makov','$2a$10$br5ryMM6RzhMiauwdsr/Zexn4hiIjwFq2SXMAWKoLseH.aubIFuGq','ROLE_STUDENT','MakJak@abv.bg','V093623',5,NULL),(17,'Jack','Quince','$2a$10$AF6FBoNjBEktGFH.xCNe6uVROfulrs6EBs3He9k8HK1Cu0QDKYEKu','ROLE_STUDENT','JQK@abv.bg','O366374',5,1),(18,'Vencaka','Petrow','$2a$10$jgWSfcl4qe.Km4p9DOxVsuMq4u/DAU2FoCFICo6.bLaJfyCxZUnAu','ROLE_STUDENT','vpetrow@abv.bg','R016269',5,NULL),(20,'Qna','Makov','$2a$10$ccsRX1TScGOSqx7ZbEuXquiSCTSmRmW6McydSvA1yDgM9RXtjHSkq','ROLE_STUDENT','qmko@abv.bg','W969532',5,38),(21,'Masashi','Quince','$2a$10$tJ8AtwD7DEJaDL2pbSDGFOYQb7BHC8uQVCA0aVUOYNOLZhDhObi6S','ROLE_STUDENT','masashito@abv.bg','H172121',5,NULL),(22,'Qna','LubenovskaQnovita','$2a$10$mlknpVakb/MZoOlOLfR9B.Wwtzb246mn3NU7KZ8GjpNnzUoCXjYUu','ROLE_STUDENT','qlubenow@abv.bg','J018731',5,NULL),(23,'Jack','Kishimoto','$2a$10$MqjKr9LKILRABW5IGUps6.U65vto64MjW53wLlIze4y8hQSKvHBmm','ROLE_DIRECTOR','qhikimoto@abv.bg',NULL,5,NULL),(25,'Leeroy','Jenkins','$2a$10$XxDCzRxV8dEsiZGRG6ifjuR3KBL3LDMfIIt13xesUy4VavpLIqr2i','ROLE_DIRECTOR','LJnk@abv.bg',NULL,5,NULL),(27,'Benjy','Fishy','$2a$10$IeMxZYwAWNGOjQ.Wll1owe9hBO3Mqr6Of7lCGw0FOyqH9vJmzU/W.','ROLE_DIRECTOR','Benjyfishy@gmail.benhyfishy',NULL,5,NULL),(30,'Jack','Escobar','$2a$10$wAyCSG2sb1GLSb/vIXyBkOtcHq0j99vqm/W9g8G9fakQfm804Ich2','ROLE_TEACHER','jko@abv.bg',NULL,5,NULL),(31,'Masashi','Kishimoto','$2a$10$zoScZoPJSGbGA4RL7DlevOhJw4B959snPQK/6DB0rb6gts1Mb7A3S','ROLE_DIRECTOR','masashko@abv.bg',NULL,6,NULL),(32,'Qna','Makov','$2a$10$xyhBxmEcqECwBuG6iULIxukcF.uzIJnUx3gSbgI0k/GWhdKI/FvvK','ROLE_TEACHER','snoop@dog.com',NULL,6,NULL),(33,'Vencaka','Fishy','$2a$10$qJeb5paM80x/ICuGAq2Fr.AkSz.mzfAYGIWTWmbGhbJuTFRFpbIgq','ROLE_TEACHER','IOUO@YFG.COM',NULL,5,NULL),(35,'Iskra','Peneva','$2a$10$r4KjmyTapldFITvYRKR8G.F3xxK2x7wuvp9wBY6CJx6zVdp13OSUS','ROLE_STUDENT','Ipeneva@gmail.com','D136243',6,NULL),(37,'Qna','Qnakieva','$2a$10$28R5UgS6UHLDxQwpmnlnV..dq94zWSfOYvu03LcfZJUlyHzCwfukq','ROLE_PARENT','QnaQnakieva17612@gmail.com','35',6,NULL),(38,'Lachezar','Makov','$2a$10$dAq3FxEt0yoIdXCSTy5T6e6X/KGlNxpZQNJLcayus6QIR5hRYcMGq','ROLE_STUDENT','lmakov@abv.bg','S476478',5,38),(39,'Qna','Markova','$2a$10$Stus0acg.98j1rQE20vtn.Vx4d7ybLzz8ASwYZKO2P2.BmenLB6Wy','ROLE_STUDENT','qMarjkova@abv.bg','A180995',5,41),(40,'Isabella','Morena','$2a$10$kEFsPRjm2HO2O9r/u7MOduhhnxnlS0uoiOF4k9SHUrzC1KPGHedR2','ROLE_STUDENT','IbmORE@abv.bg','S046257',5,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,4,'ROLE_STUDENT'),(3,1,'ROLE_ADMIN'),(5,3,'ROLE_TEACHER'),(7,4,NULL),(8,4,NULL),(9,4,NULL),(10,4,NULL),(11,4,NULL),(12,4,NULL),(13,4,NULL),(14,4,NULL),(15,4,NULL),(16,4,NULL),(17,4,NULL),(18,4,NULL),(20,4,NULL),(21,4,NULL),(22,4,NULL),(23,2,NULL),(25,2,NULL),(27,2,NULL),(30,3,NULL),(31,2,NULL),(32,3,NULL),(33,3,NULL),(35,4,NULL),(37,5,NULL),(38,4,NULL),(39,4,NULL),(40,4,NULL);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_school_subjects`
--

DROP TABLE IF EXISTS `user_school_subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_school_subjects` (
  `user_id` bigint NOT NULL,
  `school_subject_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`school_subject_id`),
  KEY `school_subject_id` (`school_subject_id`),
  CONSTRAINT `user_school_subjects_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_school_subjects_ibfk_2` FOREIGN KEY (`school_subject_id`) REFERENCES `school_subject` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_school_subjects`
--

LOCK TABLES `user_school_subjects` WRITE;
/*!40000 ALTER TABLE `user_school_subjects` DISABLE KEYS */;
INSERT INTO `user_school_subjects` VALUES (32,1),(40,1),(5,2),(40,2),(40,3),(40,4),(40,7),(33,12),(40,12),(40,17),(40,20),(40,21),(40,22);
/*!40000 ALTER TABLE `user_school_subjects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-04 14:59:17
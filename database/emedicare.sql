-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: emedicare
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cdata_appointments`
--

DROP TABLE IF EXISTS `cdata_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdata_appointments` (
  `no` int(11) NOT NULL auto_increment,
  `userid` varchar(30) DEFAULT NULL,
  `appointdate` datetime DEFAULT NULL,
  `header` varchar(50) DEFAULT NULL,
  `content` longtext,
  `status` char(1) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdata_appointments`
--

LOCK TABLES `cdata_appointments` WRITE;
/*!40000 ALTER TABLE `cdata_appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdata_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cdata_userlog`
--

DROP TABLE IF EXISTS `cdata_userlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdata_userlog` (
  `userid` varchar(30) NOT NULL,
  `id` varchar(45) NOT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdata_userlog`
--

LOCK TABLES `cdata_userlog` WRITE;
/*!40000 ALTER TABLE `cdata_userlog` DISABLE KEYS */;
INSERT INTO `cdata_userlog` VALUES ('system','1','2018-07-05 21:52:12'),('U0000002','1','2018-07-05 21:52:27'),('U0000002','10','2018-07-05 23:14:35'),('U0000002','11','2018-07-05 23:14:40'),('U0000002','12','2018-07-05 23:14:42'),('U0000002','13','2018-07-05 23:14:45'),('U0000002','14','2018-07-05 23:15:52'),('U0000002','15','2018-07-05 23:22:22'),('U0000002','16','2018-07-07 17:08:55'),('U0000002','17','2018-07-07 17:09:25'),('U0000002','18','2018-07-07 17:17:49'),('U0000002','19','2018-07-07 17:57:17'),('system','2','2018-07-05 21:58:10'),('U0000002','2','2018-07-05 21:59:35'),('U0000002','20','2018-07-07 18:02:01'),('U0000002','21','2018-07-07 18:03:42'),('U0000002','22','2018-07-07 18:05:32'),('U0000002','23','2018-07-07 18:07:21'),('U0000002','24','2018-07-07 18:08:38'),('U0000002','25','2018-07-07 18:10:34'),('U0000002','26','2018-07-07 18:12:26'),('U0000002','27','2018-07-07 18:13:25'),('system','3','2018-07-05 21:59:06'),('U0000002','3','2018-07-05 22:01:04'),('U0000002','4','2018-07-05 22:06:33'),('U0000002','5','2018-07-05 22:06:48'),('U0000002','6','2018-07-05 22:07:18'),('U0000002','7','2018-07-05 22:10:17'),('U0000002','8','2018-07-05 22:38:16'),('U0000002','9','2018-07-05 22:38:21');
/*!40000 ALTER TABLE `cdata_userlog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cdata_visithistory`
--

DROP TABLE IF EXISTS `cdata_visithistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cdata_visithistory` (
  `no` int(11) NOT NULL,
  `userid` varchar(30) DEFAULT NULL,
  `visitdate` date DEFAULT NULL,
  `content` longtext,
  `delflg` char(1) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通院履歴テーブル';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cdata_visithistory`
--

LOCK TABLES `cdata_visithistory` WRITE;
/*!40000 ALTER TABLE `cdata_visithistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `cdata_visithistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mstr_user`
--

DROP TABLE IF EXISTS `mstr_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mstr_user` (
  `userid` varchar(30) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `delflg` varchar(1) DEFAULT NULL,
  `begintime` time(4) DEFAULT NULL,
  `endtime` time(4) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL COMMENT '0：超级管理员\n1：管理员\n2：普通用户',
  `authflg` char(1) DEFAULT '2',
  `birthday` date DEFAULT NULL,
  `mail` varchar(50) DEFAULT NULL,
  `openid` varchar(50) DEFAULT NULL,
  `rest` varchar(10) DEFAULT '1',
  `lang` char(1) DEFAULT NULL,
  `address` longtext,
  `telnum` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mstr_user`
--

LOCK TABLES `mstr_user` WRITE;
/*!40000 ALTER TABLE `mstr_user` DISABLE KEYS */;
INSERT INTO `mstr_user` VALUES ('admin','系统超级管理员','admin','0','10:00:00.0000','17:00:00.0000','M','2','1900-01-01','',NULL,'1','0',' ',' '),('system','系统超级管理员','system','0','10:00:00.0000','17:00:00.0000','M','0','1900-01-01','',NULL,'1','0',' ',' '),('U0000002','本田慶応','111111','0','10:00:00.0000','17:00:00.0000','F','2','1989-12-11','',NULL,'1','2','埼玉県芝園町３−４−９０４4','080-2314-12876');
/*!40000 ALTER TABLE `mstr_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-07 18:16:43

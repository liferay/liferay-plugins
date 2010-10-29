-- MySQL dump 10.13  Distrib 5.1.42, for Win64 (unknown)
--
-- Host: localhost    Database: lportal_trunk
-- ------------------------------------------------------
-- Server version	5.1.42-community

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
-- Table structure for table `account_`
--

DROP TABLE IF EXISTS `account_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_` (
  `accountId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentAccountId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `legalName` varchar(75) DEFAULT NULL,
  `legalId` varchar(75) DEFAULT NULL,
  `legalType` varchar(75) DEFAULT NULL,
  `sicCode` varchar(75) DEFAULT NULL,
  `tickerSymbol` varchar(75) DEFAULT NULL,
  `industry` varchar(75) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `size_` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_`
--

LOCK TABLES `account_` WRITE;
/*!40000 ALTER TABLE `account_` DISABLE KEYS */;
INSERT INTO `account_` VALUES (10132,10130,0,'','2010-06-08 07:10:55','2010-06-08 07:10:55',0,'liferay.com','','','','','','','','');
/*!40000 ALTER TABLE `account_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `street1` varchar(75) DEFAULT NULL,
  `street2` varchar(75) DEFAULT NULL,
  `street3` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zip` varchar(75) DEFAULT NULL,
  `regionId` bigint(20) DEFAULT NULL,
  `countryId` bigint(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `mailing` tinyint(4) DEFAULT NULL,
  `primary_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `IX_93D5AD4E` (`companyId`),
  KEY `IX_ABD7DAC0` (`companyId`,`classNameId`),
  KEY `IX_71CB1123` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_923BD178` (`companyId`,`classNameId`,`classPK`,`mailing`),
  KEY `IX_9226DBB4` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_5BC8B0D4` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsdelivery`
--

DROP TABLE IF EXISTS `announcementsdelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsdelivery` (
  `deliveryId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `email` tinyint(4) DEFAULT NULL,
  `sms` tinyint(4) DEFAULT NULL,
  `website` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`deliveryId`),
  UNIQUE KEY `IX_BA4413D5` (`userId`,`type_`),
  KEY `IX_6EDB9600` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsdelivery`
--

LOCK TABLES `announcementsdelivery` WRITE;
/*!40000 ALTER TABLE `announcementsdelivery` DISABLE KEYS */;
INSERT INTO `announcementsdelivery` VALUES (10299,10130,10165,'general',0,0,1),(10300,10130,10165,'news',0,0,1),(10301,10130,10165,'test',0,0,1),(10308,10130,10302,'general',0,0,0),(10309,10130,10302,'news',0,0,0),(10310,10130,10302,'test',0,0,0),(10317,10130,10311,'general',0,0,0),(10318,10130,10311,'news',0,0,0),(10319,10130,10311,'test',0,0,0);
/*!40000 ALTER TABLE `announcementsdelivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsentry`
--

DROP TABLE IF EXISTS `announcementsentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsentry` (
  `uuid_` varchar(75) DEFAULT NULL,
  `entryId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `title` varchar(75) DEFAULT NULL,
  `content` longtext,
  `url` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `displayDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `alert` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  KEY `IX_A6EF0B81` (`classNameId`,`classPK`),
  KEY `IX_14F06A6B` (`classNameId`,`classPK`,`alert`),
  KEY `IX_D49C2E66` (`userId`),
  KEY `IX_1AFBDE08` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsentry`
--

LOCK TABLES `announcementsentry` WRITE;
/*!40000 ALTER TABLE `announcementsentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcementsentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsflag`
--

DROP TABLE IF EXISTS `announcementsflag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsflag` (
  `flagId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `entryId` bigint(20) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`flagId`),
  UNIQUE KEY `IX_4539A99C` (`userId`,`entryId`,`value`),
  KEY `IX_9C7EB9F` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsflag`
--

LOCK TABLES `announcementsflag` WRITE;
/*!40000 ALTER TABLE `announcementsflag` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcementsflag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetcategory`
--

DROP TABLE IF EXISTS `assetcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetcategory` (
  `uuid_` varchar(75) DEFAULT NULL,
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentCategoryId` bigint(20) DEFAULT NULL,
  `leftCategoryId` bigint(20) DEFAULT NULL,
  `rightCategoryId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `title` longtext,
  `vocabularyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `IX_BE4DF2BF` (`parentCategoryId`,`name`,`vocabularyId`),
  UNIQUE KEY `IX_E8D019AA` (`uuid_`,`groupId`),
  KEY `IX_E639E2F6` (`groupId`),
  KEY `IX_D61ABE08` (`name`,`vocabularyId`),
  KEY `IX_7BB1826B` (`parentCategoryId`),
  KEY `IX_9DDD15EA` (`parentCategoryId`,`name`),
  KEY `IX_B185E980` (`parentCategoryId`,`vocabularyId`),
  KEY `IX_4D37BB00` (`uuid_`),
  KEY `IX_287B1F89` (`vocabularyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetcategory`
--

LOCK TABLES `assetcategory` WRITE;
/*!40000 ALTER TABLE `assetcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetcategoryproperty`
--

DROP TABLE IF EXISTS `assetcategoryproperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetcategoryproperty` (
  `categoryPropertyId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `key_` varchar(75) DEFAULT NULL,
  `value` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`categoryPropertyId`),
  UNIQUE KEY `IX_DBD111AA` (`categoryId`,`key_`),
  KEY `IX_99DA856` (`categoryId`),
  KEY `IX_8654719F` (`companyId`),
  KEY `IX_52340033` (`companyId`,`key_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetcategoryproperty`
--

LOCK TABLES `assetcategoryproperty` WRITE;
/*!40000 ALTER TABLE `assetcategoryproperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetcategoryproperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetentries_assetcategories`
--

DROP TABLE IF EXISTS `assetentries_assetcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetentries_assetcategories` (
  `entryId` bigint(20) NOT NULL,
  `categoryId` bigint(20) NOT NULL,
  PRIMARY KEY (`entryId`,`categoryId`),
  KEY `IX_A188F560` (`categoryId`),
  KEY `IX_E119938A` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetentries_assetcategories`
--

LOCK TABLES `assetentries_assetcategories` WRITE;
/*!40000 ALTER TABLE `assetentries_assetcategories` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetentries_assetcategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetentries_assettags`
--

DROP TABLE IF EXISTS `assetentries_assettags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetentries_assettags` (
  `entryId` bigint(20) NOT NULL,
  `tagId` bigint(20) NOT NULL,
  PRIMARY KEY (`entryId`,`tagId`),
  KEY `IX_2ED82CAD` (`entryId`),
  KEY `IX_B2A61B55` (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetentries_assettags`
--

LOCK TABLES `assetentries_assettags` WRITE;
/*!40000 ALTER TABLE `assetentries_assettags` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetentries_assettags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetentry`
--

DROP TABLE IF EXISTS `assetentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetentry` (
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `mimeType` varchar(75) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` longtext,
  `summary` longtext,
  `url` longtext,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `priority` double DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  `socialInformationEquity` double DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  UNIQUE KEY `IX_1E9D371D` (`classNameId`,`classPK`),
  KEY `IX_7306C60` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetentry`
--

LOCK TABLES `assetentry` WRITE;
/*!40000 ALTER TABLE `assetentry` DISABLE KEYS */;
INSERT INTO `assetentry` VALUES (10170,10161,10130,10165,'Test Test','2010-06-08 07:10:57','2010-06-08 07:13:38',10045,10165,0,NULL,NULL,NULL,NULL,'','Michael Han','','','',0,0,0,0,0),(10307,10161,10130,10165,'Michael Han','2010-06-08 07:14:00','2010-06-08 07:51:12',10045,10302,0,NULL,NULL,NULL,NULL,'','Angela Kim','','','',0,0,0,0,0),(10316,10161,10130,10165,'Michael Han','2010-06-08 07:14:26','2010-06-08 07:50:52',10045,10311,0,NULL,NULL,NULL,NULL,'','Paul Hinz','','','',0,0,0,0,0),(12021,10154,10130,10165,'Michael Han','2010-06-08 07:52:15','2010-06-08 07:52:15',10127,12020,1,NULL,NULL,NULL,NULL,'text/html','FrontPage','','','',0,0,0,1,0);
/*!40000 ALTER TABLE `assetentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetlink`
--

DROP TABLE IF EXISTS `assetlink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetlink` (
  `linkId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `entryId1` bigint(20) DEFAULT NULL,
  `entryId2` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  PRIMARY KEY (`linkId`),
  KEY `IX_128516C8` (`entryId1`),
  KEY `IX_56E0AB21` (`entryId1`,`entryId2`),
  KEY `IX_8F542794` (`entryId1`,`entryId2`,`type_`),
  KEY `IX_14D5A20D` (`entryId1`,`type_`),
  KEY `IX_12851A89` (`entryId2`),
  KEY `IX_91F132C` (`entryId2`,`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetlink`
--

LOCK TABLES `assetlink` WRITE;
/*!40000 ALTER TABLE `assetlink` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetlink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assettag`
--

DROP TABLE IF EXISTS `assettag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assettag` (
  `tagId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `assetCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`tagId`),
  KEY `IX_7C9E46BA` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assettag`
--

LOCK TABLES `assettag` WRITE;
/*!40000 ALTER TABLE `assettag` DISABLE KEYS */;
/*!40000 ALTER TABLE `assettag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assettagproperty`
--

DROP TABLE IF EXISTS `assettagproperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assettagproperty` (
  `tagPropertyId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `tagId` bigint(20) DEFAULT NULL,
  `key_` varchar(75) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tagPropertyId`),
  UNIQUE KEY `IX_2C944354` (`tagId`,`key_`),
  KEY `IX_DFF1F063` (`companyId`),
  KEY `IX_13805BF7` (`companyId`,`key_`),
  KEY `IX_3269E180` (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assettagproperty`
--

LOCK TABLES `assettagproperty` WRITE;
/*!40000 ALTER TABLE `assettagproperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `assettagproperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assettagstats`
--

DROP TABLE IF EXISTS `assettagstats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assettagstats` (
  `tagStatsId` bigint(20) NOT NULL,
  `tagId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `assetCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`tagStatsId`),
  UNIQUE KEY `IX_56682CC4` (`tagId`,`classNameId`),
  KEY `IX_50702693` (`classNameId`),
  KEY `IX_9464CA` (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assettagstats`
--

LOCK TABLES `assettagstats` WRITE;
/*!40000 ALTER TABLE `assettagstats` DISABLE KEYS */;
/*!40000 ALTER TABLE `assettagstats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetvocabulary`
--

DROP TABLE IF EXISTS `assetvocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetvocabulary` (
  `uuid_` varchar(75) DEFAULT NULL,
  `vocabularyId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `title` longtext,
  `description` longtext,
  `settings_` longtext,
  PRIMARY KEY (`vocabularyId`),
  UNIQUE KEY `IX_C0AAD74D` (`groupId`,`name`),
  UNIQUE KEY `IX_1B2B8792` (`uuid_`,`groupId`),
  KEY `IX_B22D908C` (`companyId`),
  KEY `IX_B6B8CA0E` (`groupId`),
  KEY `IX_55F58818` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetvocabulary`
--

LOCK TABLES `assetvocabulary` WRITE;
/*!40000 ALTER TABLE `assetvocabulary` DISABLE KEYS */;
INSERT INTO `assetvocabulary` VALUES ('02aab37c-b26d-42b3-bdaa-8ec664bf9cf3',12025,10154,10130,10133,' ','2010-06-08 07:52:16','2010-06-08 07:52:16','Topic','<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><Title language-id=\"en_US\">Topic</Title></root>','','');
/*!40000 ALTER TABLE `assetvocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogsentry`
--

DROP TABLE IF EXISTS `blogsentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blogsentry` (
  `uuid_` varchar(75) DEFAULT NULL,
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `title` varchar(150) DEFAULT NULL,
  `urlTitle` varchar(150) DEFAULT NULL,
  `content` longtext,
  `displayDate` datetime DEFAULT NULL,
  `allowPingbacks` tinyint(4) DEFAULT NULL,
  `allowTrackbacks` tinyint(4) DEFAULT NULL,
  `trackbacks` longtext,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  UNIQUE KEY `IX_DB780A20` (`groupId`,`urlTitle`),
  UNIQUE KEY `IX_1B1040FD` (`uuid_`,`groupId`),
  KEY `IX_72EF6041` (`companyId`),
  KEY `IX_430D791F` (`companyId`,`displayDate`),
  KEY `IX_BB0C2905` (`companyId`,`displayDate`,`status`),
  KEY `IX_EB2DCE27` (`companyId`,`status`),
  KEY `IX_8CACE77B` (`companyId`,`userId`),
  KEY `IX_A5F57B61` (`companyId`,`userId`,`status`),
  KEY `IX_81A50303` (`groupId`),
  KEY `IX_621E19D` (`groupId`,`displayDate`),
  KEY `IX_F0E73383` (`groupId`,`displayDate`,`status`),
  KEY `IX_1EFD8EE9` (`groupId`,`status`),
  KEY `IX_FBDE0AA3` (`groupId`,`userId`,`displayDate`),
  KEY `IX_DA04F689` (`groupId`,`userId`,`displayDate`,`status`),
  KEY `IX_49E15A23` (`groupId`,`userId`,`status`),
  KEY `IX_69157A4D` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogsentry`
--

LOCK TABLES `blogsentry` WRITE;
/*!40000 ALTER TABLE `blogsentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogsentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogsstatsuser`
--

DROP TABLE IF EXISTS `blogsstatsuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blogsstatsuser` (
  `statsUserId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `entryCount` int(11) DEFAULT NULL,
  `lastPostDate` datetime DEFAULT NULL,
  `ratingsTotalEntries` int(11) DEFAULT NULL,
  `ratingsTotalScore` double DEFAULT NULL,
  `ratingsAverageScore` double DEFAULT NULL,
  PRIMARY KEY (`statsUserId`),
  UNIQUE KEY `IX_82254C25` (`groupId`,`userId`),
  KEY `IX_90CDA39A` (`companyId`,`entryCount`),
  KEY `IX_43840EEB` (`groupId`),
  KEY `IX_28C78D5C` (`groupId`,`entryCount`),
  KEY `IX_BB51F1D9` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogsstatsuser`
--

LOCK TABLES `blogsstatsuser` WRITE;
/*!40000 ALTER TABLE `blogsstatsuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `blogsstatsuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmarksentry`
--

DROP TABLE IF EXISTS `bookmarksentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmarksentry` (
  `uuid_` varchar(75) DEFAULT NULL,
  `entryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` longtext,
  `comments` longtext,
  `visits` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  UNIQUE KEY `IX_EAA02A91` (`uuid_`,`groupId`),
  KEY `IX_E52FF7EF` (`groupId`),
  KEY `IX_5200100C` (`groupId`,`folderId`),
  KEY `IX_E2E9F129` (`groupId`,`userId`),
  KEY `IX_B670BA39` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarksentry`
--

LOCK TABLES `bookmarksentry` WRITE;
/*!40000 ALTER TABLE `bookmarksentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmarksentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookmarksfolder`
--

DROP TABLE IF EXISTS `bookmarksfolder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookmarksfolder` (
  `uuid_` varchar(75) DEFAULT NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentFolderId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `IX_DC2F8927` (`uuid_`,`groupId`),
  KEY `IX_2ABA25D7` (`companyId`),
  KEY `IX_7F703619` (`groupId`),
  KEY `IX_967799C0` (`groupId`,`parentFolderId`),
  KEY `IX_451E7AE3` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmarksfolder`
--

LOCK TABLES `bookmarksfolder` WRITE;
/*!40000 ALTER TABLE `bookmarksfolder` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmarksfolder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `browsertracker`
--

DROP TABLE IF EXISTS `browsertracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `browsertracker` (
  `browserTrackerId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `browserKey` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`browserTrackerId`),
  UNIQUE KEY `IX_E7B95510` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `browsertracker`
--

LOCK TABLES `browsertracker` WRITE;
/*!40000 ALTER TABLE `browsertracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `browsertracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calevent`
--

DROP TABLE IF EXISTS `calevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calevent` (
  `uuid_` varchar(75) DEFAULT NULL,
  `eventId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `title` varchar(75) DEFAULT NULL,
  `description` longtext,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `durationHour` int(11) DEFAULT NULL,
  `durationMinute` int(11) DEFAULT NULL,
  `allDay` tinyint(4) DEFAULT NULL,
  `timeZoneSensitive` tinyint(4) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `repeating` tinyint(4) DEFAULT NULL,
  `recurrence` longtext,
  `remindBy` int(11) DEFAULT NULL,
  `firstReminder` int(11) DEFAULT NULL,
  `secondReminder` int(11) DEFAULT NULL,
  PRIMARY KEY (`eventId`),
  UNIQUE KEY `IX_5CCE79C8` (`uuid_`,`groupId`),
  KEY `IX_D6FD9496` (`companyId`),
  KEY `IX_12EE4898` (`groupId`),
  KEY `IX_4FDDD2BF` (`groupId`,`repeating`),
  KEY `IX_FCD7C63D` (`groupId`,`type_`),
  KEY `IX_F6006202` (`remindBy`),
  KEY `IX_C1AD2122` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calevent`
--

LOCK TABLES `calevent` WRITE;
/*!40000 ALTER TABLE `calevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `calevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classname_`
--

DROP TABLE IF EXISTS `classname_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classname_` (
  `classNameId` bigint(20) NOT NULL,
  `value` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`classNameId`),
  UNIQUE KEY `IX_B27A301F` (`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classname_`
--

LOCK TABLES `classname_` WRITE;
/*!40000 ALTER TABLE `classname_` DISABLE KEYS */;
INSERT INTO `classname_` VALUES (10001,'com.liferay.counter.model.Counter'),(10002,'com.liferay.portal.kernel.workflow.WorkflowTask'),(10003,'com.liferay.portal.model.Account'),(10004,'com.liferay.portal.model.Address'),(10005,'com.liferay.portal.model.BrowserTracker'),(10006,'com.liferay.portal.model.ClassName'),(10007,'com.liferay.portal.model.Company'),(10008,'com.liferay.portal.model.Contact'),(10009,'com.liferay.portal.model.Country'),(10010,'com.liferay.portal.model.EmailAddress'),(10011,'com.liferay.portal.model.Group'),(10012,'com.liferay.portal.model.Image'),(10013,'com.liferay.portal.model.Layout'),(10014,'com.liferay.portal.model.LayoutPrototype'),(10015,'com.liferay.portal.model.LayoutSet'),(10016,'com.liferay.portal.model.LayoutSetPrototype'),(10017,'com.liferay.portal.model.ListType'),(10018,'com.liferay.portal.model.Lock'),(10019,'com.liferay.portal.model.MembershipRequest'),(10023,'com.liferay.portal.model.Organization'),(10020,'com.liferay.portal.model.OrgGroupPermission'),(10021,'com.liferay.portal.model.OrgGroupRole'),(10022,'com.liferay.portal.model.OrgLabor'),(10024,'com.liferay.portal.model.PasswordPolicy'),(10025,'com.liferay.portal.model.PasswordPolicyRel'),(10026,'com.liferay.portal.model.PasswordTracker'),(10027,'com.liferay.portal.model.Permission'),(10028,'com.liferay.portal.model.Phone'),(10029,'com.liferay.portal.model.PluginSetting'),(10030,'com.liferay.portal.model.Portlet'),(10031,'com.liferay.portal.model.PortletItem'),(10032,'com.liferay.portal.model.PortletPreferences'),(10033,'com.liferay.portal.model.Region'),(10034,'com.liferay.portal.model.Release'),(10035,'com.liferay.portal.model.Resource'),(10036,'com.liferay.portal.model.ResourceAction'),(10037,'com.liferay.portal.model.ResourceCode'),(10038,'com.liferay.portal.model.ResourcePermission'),(10039,'com.liferay.portal.model.Role'),(10040,'com.liferay.portal.model.ServiceComponent'),(10041,'com.liferay.portal.model.Shard'),(10042,'com.liferay.portal.model.Subscription'),(10043,'com.liferay.portal.model.Team'),(10044,'com.liferay.portal.model.Ticket'),(10045,'com.liferay.portal.model.User'),(10046,'com.liferay.portal.model.UserGroup'),(10047,'com.liferay.portal.model.UserGroupGroupRole'),(10048,'com.liferay.portal.model.UserGroupRole'),(10049,'com.liferay.portal.model.UserIdMapper'),(10050,'com.liferay.portal.model.UserTracker'),(10051,'com.liferay.portal.model.UserTrackerPath'),(10052,'com.liferay.portal.model.WebDAVProps'),(10053,'com.liferay.portal.model.Website'),(10054,'com.liferay.portal.model.WorkflowDefinitionLink'),(10055,'com.liferay.portal.model.WorkflowInstanceLink'),(10256,'com.liferay.portal.workflow.kaleo.model.KaleoAction'),(10257,'com.liferay.portal.workflow.kaleo.model.KaleoDefinition'),(10258,'com.liferay.portal.workflow.kaleo.model.KaleoInstance'),(10259,'com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken'),(10260,'com.liferay.portal.workflow.kaleo.model.KaleoLog'),(10261,'com.liferay.portal.workflow.kaleo.model.KaleoNode'),(10262,'com.liferay.portal.workflow.kaleo.model.KaleoNotification'),(10263,'com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient'),(10264,'com.liferay.portal.workflow.kaleo.model.KaleoTask'),(10265,'com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment'),(10266,'com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken'),(10267,'com.liferay.portal.workflow.kaleo.model.KaleoTransition'),(10056,'com.liferay.portlet.announcements.model.AnnouncementsDelivery'),(10057,'com.liferay.portlet.announcements.model.AnnouncementsEntry'),(10058,'com.liferay.portlet.announcements.model.AnnouncementsFlag'),(10059,'com.liferay.portlet.asset.model.AssetCategory'),(10060,'com.liferay.portlet.asset.model.AssetCategoryProperty'),(10061,'com.liferay.portlet.asset.model.AssetEntry'),(10062,'com.liferay.portlet.asset.model.AssetLink'),(10063,'com.liferay.portlet.asset.model.AssetTag'),(10064,'com.liferay.portlet.asset.model.AssetTagProperty'),(10065,'com.liferay.portlet.asset.model.AssetTagStats'),(10066,'com.liferay.portlet.asset.model.AssetVocabulary'),(10067,'com.liferay.portlet.blogs.model.BlogsEntry'),(10068,'com.liferay.portlet.blogs.model.BlogsStatsUser'),(10069,'com.liferay.portlet.bookmarks.model.BookmarksEntry'),(10070,'com.liferay.portlet.bookmarks.model.BookmarksFolder'),(10071,'com.liferay.portlet.calendar.model.CalEvent'),(10072,'com.liferay.portlet.documentlibrary.model.DLFileEntry'),(10073,'com.liferay.portlet.documentlibrary.model.DLFileRank'),(10074,'com.liferay.portlet.documentlibrary.model.DLFileShortcut'),(10075,'com.liferay.portlet.documentlibrary.model.DLFileVersion'),(10076,'com.liferay.portlet.documentlibrary.model.DLFolder'),(10077,'com.liferay.portlet.expando.model.ExpandoColumn'),(10078,'com.liferay.portlet.expando.model.ExpandoRow'),(10079,'com.liferay.portlet.expando.model.ExpandoTable'),(10080,'com.liferay.portlet.expando.model.ExpandoValue'),(10081,'com.liferay.portlet.imagegallery.model.IGFolder'),(10082,'com.liferay.portlet.imagegallery.model.IGImage'),(10083,'com.liferay.portlet.journal.model.JournalArticle'),(10084,'com.liferay.portlet.journal.model.JournalArticleImage'),(10085,'com.liferay.portlet.journal.model.JournalArticleResource'),(10086,'com.liferay.portlet.journal.model.JournalContentSearch'),(10087,'com.liferay.portlet.journal.model.JournalFeed'),(10088,'com.liferay.portlet.journal.model.JournalStructure'),(10089,'com.liferay.portlet.journal.model.JournalTemplate'),(10090,'com.liferay.portlet.messageboards.model.MBBan'),(10091,'com.liferay.portlet.messageboards.model.MBCategory'),(10092,'com.liferay.portlet.messageboards.model.MBDiscussion'),(10093,'com.liferay.portlet.messageboards.model.MBMailingList'),(10094,'com.liferay.portlet.messageboards.model.MBMessage'),(10095,'com.liferay.portlet.messageboards.model.MBMessageFlag'),(10096,'com.liferay.portlet.messageboards.model.MBStatsUser'),(10097,'com.liferay.portlet.messageboards.model.MBThread'),(10098,'com.liferay.portlet.polls.model.PollsChoice'),(10099,'com.liferay.portlet.polls.model.PollsQuestion'),(10100,'com.liferay.portlet.polls.model.PollsVote'),(10101,'com.liferay.portlet.ratings.model.RatingsEntry'),(10102,'com.liferay.portlet.ratings.model.RatingsStats'),(10103,'com.liferay.portlet.shopping.model.ShoppingCart'),(10104,'com.liferay.portlet.shopping.model.ShoppingCategory'),(10105,'com.liferay.portlet.shopping.model.ShoppingCoupon'),(10106,'com.liferay.portlet.shopping.model.ShoppingItem'),(10107,'com.liferay.portlet.shopping.model.ShoppingItemField'),(10108,'com.liferay.portlet.shopping.model.ShoppingItemPrice'),(10109,'com.liferay.portlet.shopping.model.ShoppingOrder'),(10110,'com.liferay.portlet.shopping.model.ShoppingOrderItem'),(10111,'com.liferay.portlet.social.model.SocialActivity'),(10112,'com.liferay.portlet.social.model.SocialEquityAssetEntry'),(10113,'com.liferay.portlet.social.model.SocialEquityHistory'),(10114,'com.liferay.portlet.social.model.SocialEquityLog'),(10115,'com.liferay.portlet.social.model.SocialEquitySetting'),(10116,'com.liferay.portlet.social.model.SocialEquityUser'),(10117,'com.liferay.portlet.social.model.SocialRelation'),(10118,'com.liferay.portlet.social.model.SocialRequest'),(10119,'com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion'),(10120,'com.liferay.portlet.softwarecatalog.model.SCLicense'),(10121,'com.liferay.portlet.softwarecatalog.model.SCProductEntry'),(10122,'com.liferay.portlet.softwarecatalog.model.SCProductScreenshot'),(10123,'com.liferay.portlet.softwarecatalog.model.SCProductVersion'),(10124,'com.liferay.portlet.tasks.model.TasksProposal'),(10125,'com.liferay.portlet.tasks.model.TasksReview'),(10126,'com.liferay.portlet.wiki.model.WikiNode'),(10127,'com.liferay.portlet.wiki.model.WikiPage'),(10128,'com.liferay.portlet.wiki.model.WikiPageResource');
/*!40000 ALTER TABLE `classname_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyId` bigint(20) NOT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  `webId` varchar(75) DEFAULT NULL,
  `key_` longtext,
  `virtualHost` varchar(75) DEFAULT NULL,
  `mx` varchar(75) DEFAULT NULL,
  `homeURL` longtext,
  `logoId` bigint(20) DEFAULT NULL,
  `system` tinyint(4) DEFAULT NULL,
  `maxUsers` int(11) DEFAULT NULL,
  PRIMARY KEY (`companyId`),
  UNIQUE KEY `IX_975996C0` (`virtualHost`),
  UNIQUE KEY `IX_EC00543C` (`webId`),
  KEY `IX_38EFE3FD` (`logoId`),
  KEY `IX_12566EC2` (`mx`),
  KEY `IX_35E3E7C6` (`system`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (10130,10132,'liferay.com','rO0ABXNyABRqYXZhLnNlY3VyaXR5LktleVJlcL35T7OImqVDAgAETAAJYWxnb3JpdGhtdAASTGphdmEvbGFuZy9TdHJpbmc7WwAHZW5jb2RlZHQAAltCTAAGZm9ybWF0cQB+AAFMAAR0eXBldAAbTGphdmEvc2VjdXJpdHkvS2V5UmVwJFR5cGU7eHB0AANERVN1cgACW0Ks8xf4BghU4AIAAHhwAAAACNqA1fHj40MydAADUkFXfnIAGWphdmEuc2VjdXJpdHkuS2V5UmVwJFR5cGUAAAAAAAAAABIAAHhyAA5qYXZhLmxhbmcuRW51bQAAAAAAAAAAEgAAeHB0AAZTRUNSRVQ=','localhost','liferay.com','',0,0,0);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_`
--

DROP TABLE IF EXISTS `contact_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_` (
  `contactId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `accountId` bigint(20) DEFAULT NULL,
  `parentContactId` bigint(20) DEFAULT NULL,
  `firstName` varchar(75) DEFAULT NULL,
  `middleName` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `prefixId` int(11) DEFAULT NULL,
  `suffixId` int(11) DEFAULT NULL,
  `male` tinyint(4) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `smsSn` varchar(75) DEFAULT NULL,
  `aimSn` varchar(75) DEFAULT NULL,
  `facebookSn` varchar(75) DEFAULT NULL,
  `icqSn` varchar(75) DEFAULT NULL,
  `jabberSn` varchar(75) DEFAULT NULL,
  `msnSn` varchar(75) DEFAULT NULL,
  `mySpaceSn` varchar(75) DEFAULT NULL,
  `skypeSn` varchar(75) DEFAULT NULL,
  `twitterSn` varchar(75) DEFAULT NULL,
  `ymSn` varchar(75) DEFAULT NULL,
  `employeeStatusId` varchar(75) DEFAULT NULL,
  `employeeNumber` varchar(75) DEFAULT NULL,
  `jobTitle` varchar(100) DEFAULT NULL,
  `jobClass` varchar(75) DEFAULT NULL,
  `hoursOfOperation` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`contactId`),
  KEY `IX_66D496A3` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_`
--

LOCK TABLES `contact_` WRITE;
/*!40000 ALTER TABLE `contact_` DISABLE KEYS */;
INSERT INTO `contact_` VALUES (10134,10130,10133,'','2010-06-08 07:10:55','2010-06-08 07:10:55',10132,0,'','','',0,0,1,'2010-06-08 07:10:55','','','','','','','','','','','','','','',''),(10166,10130,10165,'','2010-06-08 07:10:56','2010-06-08 07:13:38',10132,0,'Michael','','Han',0,0,1,'1970-01-01 00:00:00','','','','','','','','','','','','','Director of Operations','',''),(10303,10130,10165,'Michael Han','2010-06-08 07:14:00','2010-06-08 07:51:12',10132,0,'Angela','','Kim',0,0,0,'1970-01-01 00:00:00','','','','','','','','','','','','','Corporate Counsel','',''),(10312,10130,10165,'Michael Han','2010-06-08 07:14:26','2010-06-08 07:50:52',10132,0,'Paul','','Hinz',0,0,1,'1970-01-01 00:00:00','','','','','','','','','','','','','CMO','','');
/*!40000 ALTER TABLE `contact_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counter`
--

DROP TABLE IF EXISTS `counter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `counter` (
  `name` varchar(75) NOT NULL,
  `currentId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counter`
--

LOCK TABLES `counter` WRITE;
/*!40000 ALTER TABLE `counter` DISABLE KEYS */;
INSERT INTO `counter` VALUES ('com.liferay.counter.model.Counter',14000),('com.liferay.portal.model.Layout#10147#true',1),('com.liferay.portal.model.Layout#10154#false',3),('com.liferay.portal.model.Layout#10167#false',1),('com.liferay.portal.model.Layout#10167#true',1),('com.liferay.portal.model.Permission',2000),('com.liferay.portal.model.Resource',2000),('com.liferay.portal.model.ResourceAction',2000),('com.liferay.portal.model.ResourcePermission',4000),('com.liferay.portlet.social.model.SocialActivity',2000);
/*!40000 ALTER TABLE `counter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `countryId` bigint(20) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `a2` varchar(75) DEFAULT NULL,
  `a3` varchar(75) DEFAULT NULL,
  `number_` varchar(75) DEFAULT NULL,
  `idd_` varchar(75) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`countryId`),
  UNIQUE KEY `IX_717B97E1` (`a2`),
  UNIQUE KEY `IX_717B9BA2` (`a3`),
  UNIQUE KEY `IX_19DA007B` (`name`),
  KEY `IX_25D734CD` (`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'Canada','CA','CAN','124','001',1),(2,'China','CN','CHN','156','086',1),(3,'France','FR','FRA','250','033',1),(4,'Germany','DE','DEU','276','049',1),(5,'Hong Kong','HK','HKG','344','852',1),(6,'Hungary','HU','HUN','348','036',1),(7,'Israel','IL','ISR','376','972',1),(8,'Italy','IT','ITA','380','039',1),(9,'Japan','JP','JPN','392','081',1),(10,'South Korea','KR','KOR','410','082',1),(11,'Netherlands','NL','NLD','528','031',1),(12,'Portugal','PT','PRT','620','351',1),(13,'Russia','RU','RUS','643','007',1),(14,'Singapore','SG','SGP','702','065',1),(15,'Spain','ES','ESP','724','034',1),(16,'Turkey','TR','TUR','792','090',1),(17,'Vietnam','VM','VNM','704','084',1),(18,'United Kingdom','GB','GBR','826','044',1),(19,'United States','US','USA','840','001',1),(20,'Afghanistan','AF','AFG','4','093',1),(21,'Albania','AL','ALB','8','355',1),(22,'Algeria','DZ','DZA','12','213',1),(23,'American Samoa','AS','ASM','16','684',1),(24,'Andorra','AD','AND','20','376',1),(25,'Angola','AO','AGO','24','244',1),(26,'Anguilla','AI','AIA','660','264',1),(27,'Antarctica','AQ','ATA','10','672',1),(28,'Antigua','AG','ATG','28','268',1),(29,'Argentina','AR','ARG','32','054',1),(30,'Armenia','AM','ARM','51','374',1),(31,'Aruba','AW','ABW','533','297',1),(32,'Australia','AU','AUS','36','061',1),(33,'Austria','AT','AUT','40','043',1),(34,'Azerbaijan','AZ','AZE','31','994',1),(35,'Bahamas','BS','BHS','44','242',1),(36,'Bahrain','BH','BHR','48','973',1),(37,'Bangladesh','BD','BGD','50','880',1),(38,'Barbados','BB','BRB','52','246',1),(39,'Belarus','BY','BLR','112','375',1),(40,'Belgium','BE','BEL','56','032',1),(41,'Belize','BZ','BLZ','84','501',1),(42,'Benin','BJ','BEN','204','229',1),(43,'Bermuda','BM','BMU','60','441',1),(44,'Bhutan','BT','BTN','64','975',1),(45,'Bolivia','BO','BOL','68','591',1),(46,'Bosnia-Herzegovina','BA','BIH','70','387',1),(47,'Botswana','BW','BWA','72','267',1),(48,'Brazil','BR','BRA','76','055',1),(49,'British Virgin Islands','VG','VGB','92','284',1),(50,'Brunei','BN','BRN','96','673',1),(51,'Bulgaria','BG','BGR','100','359',1),(52,'Burkina Faso','BF','BFA','854','226',1),(53,'Burma (Myanmar)','MM','MMR','104','095',1),(54,'Burundi','BI','BDI','108','257',1),(55,'Cambodia','KH','KHM','116','855',1),(56,'Cameroon','CM','CMR','120','237',1),(57,'Cape Verde Island','CV','CPV','132','238',1),(58,'Cayman Islands','KY','CYM','136','345',1),(59,'Central African Republic','CF','CAF','140','236',1),(60,'Chad','TD','TCD','148','235',1),(61,'Chile','CL','CHL','152','056',1),(62,'Christmas Island','CX','CXR','162','061',1),(63,'Cocos Islands','CC','CCK','166','061',1),(64,'Colombia','CO','COL','170','057',1),(65,'Comoros','KM','COM','174','269',1),(66,'Republic of Congo','CD','COD','180','242',1),(67,'Democratic Republic of Congo','CG','COG','178','243',1),(68,'Cook Islands','CK','COK','184','682',1),(69,'Costa Rica','CR','CRI','188','506',1),(70,'Croatia','HR','HRV','191','385',1),(71,'Cuba','CU','CUB','192','053',1),(72,'Cyprus','CY','CYP','196','357',1),(73,'Czech Republic','CZ','CZE','203','420',1),(74,'Denmark','DK','DNK','208','045',1),(75,'Djibouti','DJ','DJI','262','253',1),(76,'Dominica','DM','DMA','212','767',1),(77,'Dominican Republic','DO','DOM','214','809',1),(78,'Ecuador','EC','ECU','218','593',1),(79,'Egypt','EG','EGY','818','020',1),(80,'El Salvador','SV','SLV','222','503',1),(81,'Equatorial Guinea','GQ','GNQ','226','240',1),(82,'Eritrea','ER','ERI','232','291',1),(83,'Estonia','EE','EST','233','372',1),(84,'Ethiopia','ET','ETH','231','251',1),(85,'Faeroe Islands','FO','FRO','234','298',1),(86,'Falkland Islands','FK','FLK','238','500',1),(87,'Fiji Islands','FJ','FJI','242','679',1),(88,'Finland','FI','FIN','246','358',1),(89,'French Guiana','GF','GUF','254','594',1),(90,'French Polynesia','PF','PYF','258','689',1),(91,'Gabon','GA','GAB','266','241',1),(92,'Gambia','GM','GMB','270','220',1),(93,'Georgia','GE','GEO','268','995',1),(94,'Ghana','GH','GHA','288','233',1),(95,'Gibraltar','GI','GIB','292','350',1),(96,'Greece','GR','GRC','300','030',1),(97,'Greenland','GL','GRL','304','299',1),(98,'Grenada','GD','GRD','308','473',1),(99,'Guadeloupe','GP','GLP','312','590',1),(100,'Guam','GU','GUM','316','671',1),(101,'Guatemala','GT','GTM','320','502',1),(102,'Guinea','GN','GIN','324','224',1),(103,'Guinea-Bissau','GW','GNB','624','245',1),(104,'Guyana','GY','GUY','328','592',1),(105,'Haiti','HT','HTI','332','509',1),(106,'Honduras','HN','HND','340','504',1),(107,'Iceland','IS','ISL','352','354',1),(108,'India','IN','IND','356','091',1),(109,'Indonesia','ID','IDN','360','062',1),(110,'Iran','IR','IRN','364','098',1),(111,'Iraq','IQ','IRQ','368','964',1),(112,'Ireland','IE','IRL','372','353',1),(113,'Ivory Coast','CI','CIV','384','225',1),(114,'Jamaica','JM','JAM','388','876',1),(115,'Jordan','JO','JOR','400','962',1),(116,'Kazakhstan','KZ','KAZ','398','007',1),(117,'Kenya','KE','KEN','404','254',1),(118,'Kiribati','KI','KIR','408','686',1),(119,'Kuwait','KW','KWT','414','965',1),(120,'North Korea','KP','PRK','408','850',1),(121,'Kyrgyzstan','KG','KGZ','471','996',1),(122,'Laos','LA','LAO','418','856',1),(123,'Latvia','LV','LVA','428','371',1),(124,'Lebanon','LB','LBN','422','961',1),(125,'Lesotho','LS','LSO','426','266',1),(126,'Liberia','LR','LBR','430','231',1),(127,'Libya','LY','LBY','434','218',1),(128,'Liechtenstein','LI','LIE','438','423',1),(129,'Lithuania','LT','LTU','440','370',1),(130,'Luxembourg','LU','LUX','442','352',1),(131,'Macau','MO','MAC','446','853',1),(132,'Macedonia','MK','MKD','807','389',1),(133,'Madagascar','MG','MDG','450','261',1),(134,'Malawi','MW','MWI','454','265',1),(135,'Malaysia','MY','MYS','458','060',1),(136,'Maldives','MV','MDV','462','960',1),(137,'Mali','ML','MLI','466','223',1),(138,'Malta','MT','MLT','470','356',1),(139,'Marshall Islands','MH','MHL','584','692',1),(140,'Martinique','MQ','MTQ','474','596',1),(141,'Mauritania','MR','MRT','478','222',1),(142,'Mauritius','MU','MUS','480','230',1),(143,'Mayotte Island','YT','MYT','175','269',1),(144,'Mexico','MX','MEX','484','052',1),(145,'Micronesia','FM','FSM','583','691',1),(146,'Moldova','MD','MDA','498','373',1),(147,'Monaco','MC','MCO','492','377',1),(148,'Mongolia','MN','MNG','496','976',1),(149,'Montenegro','ME','MNE','499','382',1),(150,'Montserrat','MS','MSR','500','664',1),(151,'Morocco','MA','MAR','504','212',1),(152,'Mozambique','MZ','MOZ','508','258',1),(153,'Namibia','NA','NAM','516','264',1),(154,'Nauru','NR','NRU','520','674',1),(155,'Nepal','NP','NPL','524','977',1),(156,'Netherlands Antilles','AN','ANT','530','599',1),(157,'New Caledonia','NC','NCL','540','687',1),(158,'New Zealand','NZ','NZL','554','064',1),(159,'Nicaragua','NI','NIC','558','505',1),(160,'Niger','NE','NER','562','227',1),(161,'Nigeria','NG','NGA','566','234',1),(162,'Niue','NU','NIU','570','683',1),(163,'Norfolk Island','NF','NFK','574','672',1),(164,'Norway','NO','NOR','578','047',1),(165,'Oman','OM','OMN','512','968',1),(166,'Pakistan','PK','PAK','586','092',1),(167,'Palau','PW','PLW','585','680',1),(168,'Palestine','PS','PSE','275','970',1),(169,'Panama','PA','PAN','591','507',1),(170,'Papua New Guinea','PG','PNG','598','675',1),(171,'Paraguay','PY','PRY','600','595',1),(172,'Peru','PE','PER','604','051',1),(173,'Philippines','PH','PHL','608','063',1),(174,'Poland','PL','POL','616','048',1),(175,'Puerto Rico','PR','PRI','630','787',1),(176,'Qatar','QA','QAT','634','974',1),(177,'Reunion Island','RE','REU','638','262',1),(178,'Romania','RO','ROU','642','040',1),(179,'Rwanda','RW','RWA','646','250',1),(180,'St. Helena','SH','SHN','654','290',1),(181,'St. Kitts','KN','KNA','659','869',1),(182,'St. Lucia','LC','LCA','662','758',1),(183,'St. Pierre & Miquelon','PM','SPM','666','508',1),(184,'St. Vincent','VC','VCT','670','784',1),(185,'San Marino','SM','SMR','674','378',1),(186,'Sao Tome & Principe','ST','STP','678','239',1),(187,'Saudi Arabia','SA','SAU','682','966',1),(188,'Senegal','SN','SEN','686','221',1),(189,'Serbia','RS','SRB','688','381',1),(190,'Seychelles','SC','SYC','690','248',1),(191,'Sierra Leone','SL','SLE','694','249',1),(192,'Slovakia','SK','SVK','703','421',1),(193,'Slovenia','SI','SVN','705','386',1),(194,'Solomon Islands','SB','SLB','90','677',1),(195,'Somalia','SO','SOM','706','252',1),(196,'South Africa','ZA','ZAF','710','027',1),(197,'Sri Lanka','LK','LKA','144','094',1),(198,'Sudan','SD','SDN','736','095',1),(199,'Suriname','SR','SUR','740','597',1),(200,'Swaziland','SZ','SWZ','748','268',1),(201,'Sweden','SE','SWE','752','046',1),(202,'Switzerland','CH','CHE','756','041',1),(203,'Syria','SY','SYR','760','963',1),(204,'Taiwan','TW','TWN','158','886',1),(205,'Tajikistan','TJ','TJK','762','992',1),(206,'Tanzania','TZ','TZA','834','255',1),(207,'Thailand','TH','THA','764','066',1),(208,'Togo','TG','TGO','768','228',1),(209,'Tonga','TO','TON','776','676',1),(210,'Trinidad & Tobago','TT','TTO','780','868',1),(211,'Tunisia','TN','TUN','788','216',1),(212,'Turkmenistan','TM','TKM','795','993',1),(213,'Turks & Caicos','TC','TCA','796','649',1),(214,'Tuvalu','TV','TUV','798','688',1),(215,'Uganda','UG','UGA','800','256',1),(216,'Ukraine','UA','UKR','804','380',1),(217,'United Arab Emirates','AE','ARE','784','971',1),(218,'Uruguay','UY','URY','858','598',1),(219,'Uzbekistan','UZ','UZB','860','998',1),(220,'Vanuatu','VU','VUT','548','678',1),(221,'Vatican City','VA','VAT','336','039',1),(222,'Venezuela','VE','VEN','862','058',1),(223,'Wallis & Futuna','WF','WLF','876','681',1),(224,'Western Samoa','EH','ESH','732','685',1),(225,'Yemen','YE','YEM','887','967',1),(226,'Zambia','ZM','ZMB','894','260',1),(227,'Zimbabwe','ZW','ZWE','716','263',1);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cyrususer`
--

DROP TABLE IF EXISTS `cyrususer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyrususer` (
  `userId` varchar(75) NOT NULL,
  `password_` varchar(75) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyrususer`
--

LOCK TABLES `cyrususer` WRITE;
/*!40000 ALTER TABLE `cyrususer` DISABLE KEYS */;
/*!40000 ALTER TABLE `cyrususer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cyrusvirtual`
--

DROP TABLE IF EXISTS `cyrusvirtual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cyrusvirtual` (
  `emailAddress` varchar(75) NOT NULL,
  `userId` varchar(75) NOT NULL,
  PRIMARY KEY (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cyrusvirtual`
--

LOCK TABLES `cyrusvirtual` WRITE;
/*!40000 ALTER TABLE `cyrusvirtual` DISABLE KEYS */;
/*!40000 ALTER TABLE `cyrusvirtual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dlfileentry`
--

DROP TABLE IF EXISTS `dlfileentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dlfileentry` (
  `uuid_` varchar(75) DEFAULT NULL,
  `fileEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `versionUserId` bigint(20) DEFAULT NULL,
  `versionUserName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` longtext,
  `version` varchar(75) DEFAULT NULL,
  `size_` bigint(20) DEFAULT NULL,
  `readCount` int(11) DEFAULT NULL,
  `extraSettings` longtext,
  PRIMARY KEY (`fileEntryId`),
  UNIQUE KEY `IX_5391712` (`groupId`,`folderId`,`name`),
  UNIQUE KEY `IX_ED5CA615` (`groupId`,`folderId`,`title`),
  UNIQUE KEY `IX_BC2E7E6A` (`uuid_`,`groupId`),
  KEY `IX_4CB1B2B4` (`companyId`),
  KEY `IX_F4AF5636` (`groupId`),
  KEY `IX_93CF8193` (`groupId`,`folderId`),
  KEY `IX_43261870` (`groupId`,`userId`),
  KEY `IX_64F0FE40` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dlfileentry`
--

LOCK TABLES `dlfileentry` WRITE;
/*!40000 ALTER TABLE `dlfileentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `dlfileentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dlfilerank`
--

DROP TABLE IF EXISTS `dlfilerank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dlfilerank` (
  `fileRankId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fileRankId`),
  UNIQUE KEY `IX_CE705D48` (`companyId`,`userId`,`folderId`,`name`),
  KEY `IX_40B56512` (`folderId`,`name`),
  KEY `IX_BAFB116E` (`groupId`,`userId`),
  KEY `IX_EED06670` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dlfilerank`
--

LOCK TABLES `dlfilerank` WRITE;
/*!40000 ALTER TABLE `dlfilerank` DISABLE KEYS */;
/*!40000 ALTER TABLE `dlfilerank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dlfileshortcut`
--

DROP TABLE IF EXISTS `dlfileshortcut`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dlfileshortcut` (
  `uuid_` varchar(75) DEFAULT NULL,
  `fileShortcutId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `toFolderId` bigint(20) DEFAULT NULL,
  `toName` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`fileShortcutId`),
  UNIQUE KEY `IX_FDB4A946` (`uuid_`,`groupId`),
  KEY `IX_B0051937` (`groupId`,`folderId`),
  KEY `IX_ECCE311D` (`groupId`,`folderId`,`status`),
  KEY `IX_55C736AC` (`groupId`,`toFolderId`,`toName`),
  KEY `IX_346A0992` (`groupId`,`toFolderId`,`toName`,`status`),
  KEY `IX_4831EBE4` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dlfileshortcut`
--

LOCK TABLES `dlfileshortcut` WRITE;
/*!40000 ALTER TABLE `dlfileshortcut` DISABLE KEYS */;
/*!40000 ALTER TABLE `dlfileshortcut` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dlfileversion`
--

DROP TABLE IF EXISTS `dlfileversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dlfileversion` (
  `fileVersionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` longtext,
  `version` varchar(75) DEFAULT NULL,
  `size_` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`fileVersionId`),
  UNIQUE KEY `IX_2F8FED9C` (`groupId`,`folderId`,`name`,`version`),
  KEY `IX_B413F1EC` (`groupId`,`folderId`,`name`),
  KEY `IX_94E784D2` (`groupId`,`folderId`,`name`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dlfileversion`
--

LOCK TABLES `dlfileversion` WRITE;
/*!40000 ALTER TABLE `dlfileversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `dlfileversion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dlfolder`
--

DROP TABLE IF EXISTS `dlfolder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dlfolder` (
  `uuid_` varchar(75) DEFAULT NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentFolderId` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` longtext,
  `lastPostDate` datetime DEFAULT NULL,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `IX_902FD874` (`groupId`,`parentFolderId`,`name`),
  UNIQUE KEY `IX_3CC1DED2` (`uuid_`,`groupId`),
  KEY `IX_A74DB14C` (`companyId`),
  KEY `IX_F2EA1ACE` (`groupId`),
  KEY `IX_49C37475` (`groupId`,`parentFolderId`),
  KEY `IX_51556082` (`parentFolderId`,`name`),
  KEY `IX_CBC408D8` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dlfolder`
--

LOCK TABLES `dlfolder` WRITE;
/*!40000 ALTER TABLE `dlfolder` DISABLE KEYS */;
/*!40000 ALTER TABLE `dlfolder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emailaddress`
--

DROP TABLE IF EXISTS `emailaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emailaddress` (
  `emailAddressId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `address` varchar(75) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `primary_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`emailAddressId`),
  KEY `IX_1BB072CA` (`companyId`),
  KEY `IX_49D2DEC4` (`companyId`,`classNameId`),
  KEY `IX_551A519F` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_2A2CB130` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_7B43CD8` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emailaddress`
--

LOCK TABLES `emailaddress` WRITE;
/*!40000 ALTER TABLE `emailaddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `emailaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expandocolumn`
--

DROP TABLE IF EXISTS `expandocolumn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expandocolumn` (
  `columnId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `tableId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `defaultData` longtext,
  `typeSettings` longtext,
  PRIMARY KEY (`columnId`),
  UNIQUE KEY `IX_FEFC8DA7` (`tableId`,`name`),
  KEY `IX_A8C0CBE8` (`tableId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expandocolumn`
--

LOCK TABLES `expandocolumn` WRITE;
/*!40000 ALTER TABLE `expandocolumn` DISABLE KEYS */;
/*!40000 ALTER TABLE `expandocolumn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expandorow`
--

DROP TABLE IF EXISTS `expandorow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expandorow` (
  `rowId_` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `tableId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`rowId_`),
  UNIQUE KEY `IX_81EFBFF5` (`tableId`,`classPK`),
  KEY `IX_D3F5D7AE` (`tableId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expandorow`
--

LOCK TABLES `expandorow` WRITE;
/*!40000 ALTER TABLE `expandorow` DISABLE KEYS */;
/*!40000 ALTER TABLE `expandorow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expandotable`
--

DROP TABLE IF EXISTS `expandotable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expandotable` (
  `tableId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`tableId`),
  UNIQUE KEY `IX_37562284` (`companyId`,`classNameId`,`name`),
  KEY `IX_B5AE8A85` (`companyId`,`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expandotable`
--

LOCK TABLES `expandotable` WRITE;
/*!40000 ALTER TABLE `expandotable` DISABLE KEYS */;
/*!40000 ALTER TABLE `expandotable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expandovalue`
--

DROP TABLE IF EXISTS `expandovalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expandovalue` (
  `valueId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `tableId` bigint(20) DEFAULT NULL,
  `columnId` bigint(20) DEFAULT NULL,
  `rowId_` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `data_` longtext,
  PRIMARY KEY (`valueId`),
  UNIQUE KEY `IX_9DDD21E5` (`columnId`,`rowId_`),
  UNIQUE KEY `IX_D27B03E7` (`tableId`,`columnId`,`classPK`),
  KEY `IX_B29FEF17` (`classNameId`,`classPK`),
  KEY `IX_F7DD0987` (`columnId`),
  KEY `IX_9112A7A0` (`rowId_`),
  KEY `IX_F0566A77` (`tableId`),
  KEY `IX_1BD3F4C` (`tableId`,`classPK`),
  KEY `IX_CA9AFB7C` (`tableId`,`columnId`),
  KEY `IX_B71E92D5` (`tableId`,`rowId_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expandovalue`
--

LOCK TABLES `expandovalue` WRITE;
/*!40000 ALTER TABLE `expandovalue` DISABLE KEYS */;
/*!40000 ALTER TABLE `expandovalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_`
--

DROP TABLE IF EXISTS `group_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group_` (
  `groupId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `creatorUserId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `parentGroupId` bigint(20) DEFAULT NULL,
  `liveGroupId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `type_` int(11) DEFAULT NULL,
  `typeSettings` longtext,
  `friendlyURL` varchar(100) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`groupId`),
  UNIQUE KEY `IX_D0D5E397` (`companyId`,`classNameId`,`classPK`),
  UNIQUE KEY `IX_5DE0BE11` (`companyId`,`classNameId`,`liveGroupId`,`name`),
  UNIQUE KEY `IX_5BDDB872` (`companyId`,`friendlyURL`),
  UNIQUE KEY `IX_BBCA55B` (`companyId`,`liveGroupId`,`name`),
  UNIQUE KEY `IX_5AA68501` (`companyId`,`name`),
  KEY `IX_ABA5CEC2` (`companyId`),
  KEY `IX_16218A38` (`liveGroupId`),
  KEY `IX_7B590A7A` (`type_`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_`
--

LOCK TABLES `group_` WRITE;
/*!40000 ALTER TABLE `group_` DISABLE KEYS */;
INSERT INTO `group_` VALUES (10147,10130,10133,10011,10147,0,0,'Control Panel','',3,'','/control_panel',1),(10154,10130,10133,10011,10154,0,0,'Guest','',1,'','/guest',1),(10161,10130,10133,10007,10130,0,0,'10130','',0,'','/null',1),(10167,10130,10165,10045,10165,0,0,'10165','',0,'','/michael.han',1),(10304,10130,10302,10045,10302,0,0,'10302','',0,'','/angela.kim',1),(10313,10130,10311,10045,10311,0,0,'10311','',0,'','/paul.hinz',1);
/*!40000 ALTER TABLE `group_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_orgs`
--

DROP TABLE IF EXISTS `groups_orgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_orgs` (
  `groupId` bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  PRIMARY KEY (`groupId`,`organizationId`),
  KEY `IX_75267DCA` (`groupId`),
  KEY `IX_6BBB7682` (`organizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_orgs`
--

LOCK TABLES `groups_orgs` WRITE;
/*!40000 ALTER TABLE `groups_orgs` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups_orgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_permissions`
--

DROP TABLE IF EXISTS `groups_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_permissions` (
  `groupId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`groupId`,`permissionId`),
  KEY `IX_C48736B` (`groupId`),
  KEY `IX_EC97689D` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_permissions`
--

LOCK TABLES `groups_permissions` WRITE;
/*!40000 ALTER TABLE `groups_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_roles`
--

DROP TABLE IF EXISTS `groups_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_roles` (
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`groupId`,`roleId`),
  KEY `IX_84471FD2` (`groupId`),
  KEY `IX_3103EF3D` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_roles`
--

LOCK TABLES `groups_roles` WRITE;
/*!40000 ALTER TABLE `groups_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups_usergroups`
--

DROP TABLE IF EXISTS `groups_usergroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups_usergroups` (
  `groupId` bigint(20) NOT NULL,
  `userGroupId` bigint(20) NOT NULL,
  PRIMARY KEY (`groupId`,`userGroupId`),
  KEY `IX_31FB749A` (`groupId`),
  KEY `IX_3B69160F` (`userGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups_usergroups`
--

LOCK TABLES `groups_usergroups` WRITE;
/*!40000 ALTER TABLE `groups_usergroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups_usergroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igfolder`
--

DROP TABLE IF EXISTS `igfolder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `igfolder` (
  `uuid_` varchar(75) DEFAULT NULL,
  `folderId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentFolderId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`folderId`),
  UNIQUE KEY `IX_9BBAFB1E` (`groupId`,`parentFolderId`,`name`),
  UNIQUE KEY `IX_B10EFD68` (`uuid_`,`groupId`),
  KEY `IX_60214CF6` (`companyId`),
  KEY `IX_206498F8` (`groupId`),
  KEY `IX_1A605E9F` (`groupId`,`parentFolderId`),
  KEY `IX_F73C0982` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igfolder`
--

LOCK TABLES `igfolder` WRITE;
/*!40000 ALTER TABLE `igfolder` DISABLE KEYS */;
/*!40000 ALTER TABLE `igfolder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igimage`
--

DROP TABLE IF EXISTS `igimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `igimage` (
  `uuid_` varchar(75) DEFAULT NULL,
  `imageId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `folderId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `smallImageId` bigint(20) DEFAULT NULL,
  `largeImageId` bigint(20) DEFAULT NULL,
  `custom1ImageId` bigint(20) DEFAULT NULL,
  `custom2ImageId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`imageId`),
  UNIQUE KEY `IX_E97342D9` (`uuid_`,`groupId`),
  KEY `IX_E597322D` (`custom1ImageId`),
  KEY `IX_D9E0A34C` (`custom2ImageId`),
  KEY `IX_63820A7` (`groupId`),
  KEY `IX_8956B2C4` (`groupId`,`folderId`),
  KEY `IX_AAE8DF83` (`groupId`,`folderId`,`name`),
  KEY `IX_BE79E1E1` (`groupId`,`userId`),
  KEY `IX_64F0B572` (`largeImageId`),
  KEY `IX_D3D32126` (`smallImageId`),
  KEY `IX_265BB0F1` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igimage`
--

LOCK TABLES `igimage` WRITE;
/*!40000 ALTER TABLE `igimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `igimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `imageId` bigint(20) NOT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `text_` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  `size_` int(11) DEFAULT NULL,
  PRIMARY KEY (`imageId`),
  KEY `IX_6A925A4D` (`size_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalarticle`
--

DROP TABLE IF EXISTS `journalarticle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalarticle` (
  `uuid_` varchar(75) DEFAULT NULL,
  `id_` bigint(20) NOT NULL,
  `resourcePrimKey` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `articleId` varchar(75) DEFAULT NULL,
  `version` double DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `urlTitle` varchar(150) DEFAULT NULL,
  `description` longtext,
  `content` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `structureId` varchar(75) DEFAULT NULL,
  `templateId` varchar(75) DEFAULT NULL,
  `displayDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `reviewDate` datetime DEFAULT NULL,
  `indexable` tinyint(4) DEFAULT NULL,
  `smallImage` tinyint(4) DEFAULT NULL,
  `smallImageId` bigint(20) DEFAULT NULL,
  `smallImageURL` longtext,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_85C52EEC` (`groupId`,`articleId`,`version`),
  UNIQUE KEY `IX_3463D95B` (`uuid_`,`groupId`),
  KEY `IX_DFF98523` (`companyId`),
  KEY `IX_323DF109` (`companyId`,`status`),
  KEY `IX_9356F865` (`groupId`),
  KEY `IX_68C0F69C` (`groupId`,`articleId`),
  KEY `IX_4D5CD982` (`groupId`,`articleId`,`status`),
  KEY `IX_2E207659` (`groupId`,`structureId`),
  KEY `IX_8DEAE14E` (`groupId`,`templateId`),
  KEY `IX_22882D02` (`groupId`,`urlTitle`),
  KEY `IX_D2D249E8` (`groupId`,`urlTitle`,`status`),
  KEY `IX_3E2765FC` (`resourcePrimKey`,`status`),
  KEY `IX_EF9B7028` (`smallImageId`),
  KEY `IX_F029602F` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalarticle`
--

LOCK TABLES `journalarticle` WRITE;
/*!40000 ALTER TABLE `journalarticle` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalarticle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalarticleimage`
--

DROP TABLE IF EXISTS `journalarticleimage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalarticleimage` (
  `articleImageId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `articleId` varchar(75) DEFAULT NULL,
  `version` double DEFAULT NULL,
  `elInstanceId` varchar(75) DEFAULT NULL,
  `elName` varchar(75) DEFAULT NULL,
  `languageId` varchar(75) DEFAULT NULL,
  `tempImage` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`articleImageId`),
  UNIQUE KEY `IX_103D6207` (`groupId`,`articleId`,`version`,`elInstanceId`,`elName`,`languageId`),
  KEY `IX_3B51BB68` (`groupId`),
  KEY `IX_158B526F` (`groupId`,`articleId`,`version`),
  KEY `IX_D4121315` (`tempImage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalarticleimage`
--

LOCK TABLES `journalarticleimage` WRITE;
/*!40000 ALTER TABLE `journalarticleimage` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalarticleimage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalarticleresource`
--

DROP TABLE IF EXISTS `journalarticleresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalarticleresource` (
  `resourcePrimKey` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `articleId` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`resourcePrimKey`),
  UNIQUE KEY `IX_88DF994A` (`groupId`,`articleId`),
  KEY `IX_F8433677` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalarticleresource`
--

LOCK TABLES `journalarticleresource` WRITE;
/*!40000 ALTER TABLE `journalarticleresource` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalarticleresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalcontentsearch`
--

DROP TABLE IF EXISTS `journalcontentsearch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalcontentsearch` (
  `contentSearchId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `privateLayout` tinyint(4) DEFAULT NULL,
  `layoutId` bigint(20) DEFAULT NULL,
  `portletId` varchar(200) DEFAULT NULL,
  `articleId` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`contentSearchId`),
  UNIQUE KEY `IX_C3AA93B8` (`groupId`,`privateLayout`,`layoutId`,`portletId`,`articleId`),
  KEY `IX_9207CB31` (`articleId`),
  KEY `IX_6838E427` (`groupId`,`articleId`),
  KEY `IX_20962903` (`groupId`,`privateLayout`),
  KEY `IX_7CC7D73E` (`groupId`,`privateLayout`,`articleId`),
  KEY `IX_B3B318DC` (`groupId`,`privateLayout`,`layoutId`),
  KEY `IX_7ACC74C9` (`groupId`,`privateLayout`,`layoutId`,`portletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalcontentsearch`
--

LOCK TABLES `journalcontentsearch` WRITE;
/*!40000 ALTER TABLE `journalcontentsearch` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalcontentsearch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalfeed`
--

DROP TABLE IF EXISTS `journalfeed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalfeed` (
  `uuid_` varchar(75) DEFAULT NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `feedId` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `structureId` varchar(75) DEFAULT NULL,
  `templateId` varchar(75) DEFAULT NULL,
  `rendererTemplateId` varchar(75) DEFAULT NULL,
  `delta` int(11) DEFAULT NULL,
  `orderByCol` varchar(75) DEFAULT NULL,
  `orderByType` varchar(75) DEFAULT NULL,
  `targetLayoutFriendlyUrl` varchar(255) DEFAULT NULL,
  `targetPortletId` varchar(75) DEFAULT NULL,
  `contentField` varchar(75) DEFAULT NULL,
  `feedType` varchar(75) DEFAULT NULL,
  `feedVersion` double DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_65576CBC` (`groupId`,`feedId`),
  UNIQUE KEY `IX_39031F51` (`uuid_`,`groupId`),
  KEY `IX_35A2DB2F` (`groupId`),
  KEY `IX_50C36D79` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalfeed`
--

LOCK TABLES `journalfeed` WRITE;
/*!40000 ALTER TABLE `journalfeed` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalfeed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journalstructure`
--

DROP TABLE IF EXISTS `journalstructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journalstructure` (
  `uuid_` varchar(75) DEFAULT NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `structureId` varchar(75) DEFAULT NULL,
  `parentStructureId` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `xsd` longtext,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_AB6E9996` (`groupId`,`structureId`),
  UNIQUE KEY `IX_42E86E58` (`uuid_`,`groupId`),
  KEY `IX_B97F5608` (`groupId`),
  KEY `IX_CA0BD48C` (`groupId`,`parentStructureId`),
  KEY `IX_8831E4FC` (`structureId`),
  KEY `IX_6702CA92` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journalstructure`
--

LOCK TABLES `journalstructure` WRITE;
/*!40000 ALTER TABLE `journalstructure` DISABLE KEYS */;
/*!40000 ALTER TABLE `journalstructure` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journaltemplate`
--

DROP TABLE IF EXISTS `journaltemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journaltemplate` (
  `uuid_` varchar(75) DEFAULT NULL,
  `id_` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `templateId` varchar(75) DEFAULT NULL,
  `structureId` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `xsl` longtext,
  `langType` varchar(75) DEFAULT NULL,
  `cacheable` tinyint(4) DEFAULT NULL,
  `smallImage` tinyint(4) DEFAULT NULL,
  `smallImageId` bigint(20) DEFAULT NULL,
  `smallImageURL` longtext,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_E802AA3C` (`groupId`,`templateId`),
  UNIQUE KEY `IX_62D1B3AD` (`uuid_`,`groupId`),
  KEY `IX_77923653` (`groupId`),
  KEY `IX_1701CB2B` (`groupId`,`structureId`),
  KEY `IX_25FFB6FA` (`smallImageId`),
  KEY `IX_1B12CA20` (`templateId`),
  KEY `IX_2857419D` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journaltemplate`
--

LOCK TABLES `journaltemplate` WRITE;
/*!40000 ALTER TABLE `journaltemplate` DISABLE KEYS */;
/*!40000 ALTER TABLE `journaltemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleoaction`
--

DROP TABLE IF EXISTS `kaleo_kaleoaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleoaction` (
  `kaleoActionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `kaleoNodeName` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `executionType` varchar(20) DEFAULT NULL,
  `script` longtext,
  `scriptLanguage` varchar(75) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`kaleoActionId`),
  KEY `IX_D764E9A1` (`kaleoDefinitionId`),
  KEY `IX_B2A93512` (`kaleoNodeId`,`executionType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleoaction`
--

LOCK TABLES `kaleo_kaleoaction` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleoaction` DISABLE KEYS */;
INSERT INTO `kaleo_kaleoaction` VALUES (10278,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10277,'APPROVED','Approve Asset','','onEntry','\n					\n						Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus(\"approved\"), workflowContext);\n					\n				','javascript',0);
/*!40000 ALTER TABLE `kaleo_kaleoaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleodefinition`
--

DROP TABLE IF EXISTS `kaleo_kaleodefinition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleodefinition` (
  `kaleoDefinitionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `title` longtext,
  `description` varchar(2000) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  `startKaleoNodeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`kaleoDefinitionId`),
  KEY `IX_2B7BEC2E` (`companyId`),
  KEY `IX_2F7F569B` (`companyId`,`active_`),
  KEY `IX_87D3E16D` (`companyId`,`name`),
  KEY `IX_2C5DD83C` (`companyId`,`name`,`active_`),
  KEY `IX_CC4EDF3B` (`companyId`,`name`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleodefinition`
--

LOCK TABLES `kaleo_kaleodefinition` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleodefinition` DISABLE KEYS */;
INSERT INTO `kaleo_kaleodefinition` VALUES (10269,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13','Single Approver','Single Approver','A single approver can approve a workflow asset.',1,1,10279);
/*!40000 ALTER TABLE `kaleo_kaleodefinition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleoinstance`
--

DROP TABLE IF EXISTS `kaleo_kaleoinstance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleoinstance` (
  `kaleoInstanceId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoDefinitionName` varchar(200) DEFAULT NULL,
  `kaleoDefinitionVersion` int(11) DEFAULT NULL,
  `rootKaleoInstanceTokenId` bigint(20) DEFAULT NULL,
  `className` varchar(200) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `completionDate` datetime DEFAULT NULL,
  `workflowContext` longtext,
  PRIMARY KEY (`kaleoInstanceId`),
  KEY `IX_EC4CB399` (`companyId`,`kaleoDefinitionName`,`kaleoDefinitionVersion`,`completionDate`),
  KEY `IX_5EB130E2` (`kaleoDefinitionId`),
  KEY `IX_664E0519` (`kaleoDefinitionId`,`completed`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleoinstance`
--

LOCK TABLES `kaleo_kaleoinstance` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleoinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `kaleo_kaleoinstance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleoinstancetoken`
--

DROP TABLE IF EXISTS `kaleo_kaleoinstancetoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleoinstancetoken` (
  `kaleoInstanceTokenId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoInstanceId` bigint(20) DEFAULT NULL,
  `parentKaleoInstanceTokenId` bigint(20) DEFAULT NULL,
  `currentKaleoNodeId` bigint(20) DEFAULT NULL,
  `currentKaleoNodeName` varchar(200) DEFAULT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `completionDate` datetime DEFAULT NULL,
  PRIMARY KEY (`kaleoInstanceTokenId`),
  KEY `IX_45F8175C` (`companyId`,`parentKaleoInstanceTokenId`),
  KEY `IX_D5BA57FA` (`companyId`,`parentKaleoInstanceTokenId`,`completionDate`),
  KEY `IX_949FA455` (`kaleoDefinitionId`),
  KEY `IX_E324C3D7` (`kaleoInstanceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleoinstancetoken`
--

LOCK TABLES `kaleo_kaleoinstancetoken` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleoinstancetoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `kaleo_kaleoinstancetoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleolog`
--

DROP TABLE IF EXISTS `kaleo_kaleolog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleolog` (
  `kaleoLogId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoInstanceId` bigint(20) DEFAULT NULL,
  `kaleoInstanceTokenId` bigint(20) DEFAULT NULL,
  `kaleoTaskInstanceTokenId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `kaleoNodeName` varchar(200) DEFAULT NULL,
  `terminalKaleoNode` tinyint(4) DEFAULT NULL,
  `kaleoActionId` bigint(20) DEFAULT NULL,
  `kaleoActionName` varchar(200) DEFAULT NULL,
  `kaleoActionDescription` varchar(2000) DEFAULT NULL,
  `previousKaleoNodeId` bigint(20) DEFAULT NULL,
  `previousKaleoNodeName` varchar(200) DEFAULT NULL,
  `previousAssigneeClassName` varchar(200) DEFAULT NULL,
  `previousAssigneeClassPK` bigint(20) DEFAULT NULL,
  `currentAssigneeClassName` varchar(200) DEFAULT NULL,
  `currentAssigneeClassPK` bigint(20) DEFAULT NULL,
  `type_` varchar(50) DEFAULT NULL,
  `comment` varchar(2000) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `duration` bigint(20) DEFAULT NULL,
  `workflowContext` longtext,
  PRIMARY KEY (`kaleoLogId`),
  KEY `IX_D9FD3CB5` (`kaleoDefinitionId`),
  KEY `IX_31CCF437` (`kaleoInstanceId`),
  KEY `IX_37870D24` (`kaleoInstanceTokenId`,`kaleoNodeId`,`type_`),
  KEY `IX_5FD03F99` (`kaleoInstanceTokenId`,`type_`),
  KEY `IX_B0413637` (`kaleoTaskInstanceTokenId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleolog`
--

LOCK TABLES `kaleo_kaleolog` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleolog` DISABLE KEYS */;
/*!40000 ALTER TABLE `kaleo_kaleolog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleonode`
--

DROP TABLE IF EXISTS `kaleo_kaleonode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleonode` (
  `kaleoNodeId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `type_` varchar(20) DEFAULT NULL,
  `initial` tinyint(4) DEFAULT NULL,
  `terminal` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`kaleoNodeId`),
  KEY `IX_ECA77F9F` (`companyId`,`kaleoDefinitionId`),
  KEY `IX_78616515` (`kaleoDefinitionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleonode`
--

LOCK TABLES `kaleo_kaleonode` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleonode` DISABLE KEYS */;
INSERT INTO `kaleo_kaleonode` VALUES (10270,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,'REVIEW_CONTENT','','TASK',0,0),(10277,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,'APPROVED','','STATE',0,1),(10279,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,'CREATED','','STATE',1,0);
/*!40000 ALTER TABLE `kaleo_kaleonode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleonotification`
--

DROP TABLE IF EXISTS `kaleo_kaleonotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleonotification` (
  `kaleoNotificationId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `kaleoNodeName` varchar(200) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `executionType` varchar(20) DEFAULT NULL,
  `template` longtext,
  `templateLanguage` varchar(75) DEFAULT NULL,
  `notificationTypes` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`kaleoNotificationId`),
  KEY `IX_3BDEF6CC` (`kaleoDefinitionId`),
  KEY `IX_7A83C847` (`kaleoNodeId`,`executionType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleonotification`
--

LOCK TABLES `kaleo_kaleonotification` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleonotification` DISABLE KEYS */;
INSERT INTO `kaleo_kaleonotification` VALUES (10271,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10270,'REVIEW_CONTENT','Review Content Notification','','onAssignment','You have a new asset waiting for your review in the workflow.','text','email');
/*!40000 ALTER TABLE `kaleo_kaleonotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleonotificationrecipient`
--

DROP TABLE IF EXISTS `kaleo_kaleonotificationrecipient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleonotificationrecipient` (
  `kaleoNotificationRecipientId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNotificationId` bigint(20) DEFAULT NULL,
  `recipientClassName` varchar(75) DEFAULT NULL,
  `recipientClassPK` bigint(20) DEFAULT NULL,
  `recipientRoleType` int(11) DEFAULT NULL,
  `address` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`kaleoNotificationRecipientId`),
  KEY `IX_7F26068B` (`kaleoDefinitionId`),
  KEY `IX_21EE3763` (`kaleoNotificationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleonotificationrecipient`
--

LOCK TABLES `kaleo_kaleonotificationrecipient` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleonotificationrecipient` DISABLE KEYS */;
/*!40000 ALTER TABLE `kaleo_kaleonotificationrecipient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleotask`
--

DROP TABLE IF EXISTS `kaleo_kaleotask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleotask` (
  `kaleoTaskId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `dueDateDuration` double DEFAULT NULL,
  `dueDateScale` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`kaleoTaskId`),
  KEY `IX_4977BD72` (`kaleoDefinitionId`),
  KEY `IX_61E22421` (`kaleoNodeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleotask`
--

LOCK TABLES `kaleo_kaleotask` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleotask` DISABLE KEYS */;
INSERT INTO `kaleo_kaleotask` VALUES (10272,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10270,'REVIEW_CONTENT','',0,'');
/*!40000 ALTER TABLE `kaleo_kaleotask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleotaskassignment`
--

DROP TABLE IF EXISTS `kaleo_kaleotaskassignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleotaskassignment` (
  `kaleoTaskAssignmentId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `kaleoTaskId` bigint(20) DEFAULT NULL,
  `assigneeClassName` varchar(200) DEFAULT NULL,
  `assigneeClassPK` bigint(20) DEFAULT NULL,
  `defaultAssignment` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`kaleoTaskAssignmentId`),
  KEY `IX_BCE28DE5` (`assigneeClassName`,`kaleoTaskId`),
  KEY `IX_572B5825` (`kaleoDefinitionId`),
  KEY `IX_5EDD8F17` (`kaleoTaskId`),
  KEY `IX_787D75A7` (`kaleoTaskId`,`defaultAssignment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleotaskassignment`
--

LOCK TABLES `kaleo_kaleotaskassignment` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleotaskassignment` DISABLE KEYS */;
INSERT INTO `kaleo_kaleotaskassignment` VALUES (10273,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10270,10272,'com.liferay.portal.model.Role',10274,1),(10275,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10270,10272,'com.liferay.portal.model.Role',10276,0);
/*!40000 ALTER TABLE `kaleo_kaleotaskassignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleotaskinstancetoken`
--

DROP TABLE IF EXISTS `kaleo_kaleotaskinstancetoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleotaskinstancetoken` (
  `kaleoTaskInstanceTokenId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoInstanceId` bigint(20) DEFAULT NULL,
  `kaleoInstanceTokenId` bigint(20) DEFAULT NULL,
  `kaleoTaskId` bigint(20) DEFAULT NULL,
  `kaleoTaskName` varchar(200) DEFAULT NULL,
  `assigneeClassName` varchar(200) DEFAULT NULL,
  `assigneeClassPK` bigint(20) DEFAULT NULL,
  `completionUserId` bigint(20) DEFAULT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `completionDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  `workflowContext` longtext,
  PRIMARY KEY (`kaleoTaskInstanceTokenId`),
  KEY `IX_A9FF7F44` (`companyId`),
  KEY `IX_672441C9` (`groupId`,`companyId`,`assigneeClassName`,`assigneeClassPK`,`completed`),
  KEY `IX_40C87C3A` (`kaleoDefinitionId`),
  KEY `IX_26FC50FC` (`kaleoInstanceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleotaskinstancetoken`
--

LOCK TABLES `kaleo_kaleotaskinstancetoken` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleotaskinstancetoken` DISABLE KEYS */;
/*!40000 ALTER TABLE `kaleo_kaleotaskinstancetoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaleo_kaleotransition`
--

DROP TABLE IF EXISTS `kaleo_kaleotransition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaleo_kaleotransition` (
  `kaleoTransitionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(200) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `kaleoDefinitionId` bigint(20) DEFAULT NULL,
  `kaleoNodeId` bigint(20) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `sourceKaleoNodeId` bigint(20) DEFAULT NULL,
  `sourceKaleoNodeName` varchar(200) DEFAULT NULL,
  `targetKaleoNodeId` bigint(20) DEFAULT NULL,
  `targetKaleoNodeName` varchar(200) DEFAULT NULL,
  `defaultTransition` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`kaleoTransitionId`),
  KEY `IX_47129C62` (`kaleoDefinitionId`),
  KEY `IX_E90AF711` (`kaleoNodeId`),
  KEY `IX_462C6BF5` (`kaleoNodeId`,`defaultTransition`),
  KEY `IX_8499F610` (`kaleoNodeId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaleo_kaleotransition`
--

LOCK TABLES `kaleo_kaleotransition` WRITE;
/*!40000 ALTER TABLE `kaleo_kaleotransition` DISABLE KEYS */;
INSERT INTO `kaleo_kaleotransition` VALUES (10280,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10270,'Approve Asset','',10270,'REVIEW_CONTENT',10277,'APPROVED',1),(10281,0,10130,10133,' ','2010-06-08 07:11:13','2010-06-08 07:11:13',10269,10279,'Review Content','',10279,'CREATED',10270,'REVIEW_CONTENT',1);
/*!40000 ALTER TABLE `kaleo_kaleotransition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layout`
--

DROP TABLE IF EXISTS `layout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `layout` (
  `plid` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `privateLayout` tinyint(4) DEFAULT NULL,
  `layoutId` bigint(20) DEFAULT NULL,
  `parentLayoutId` bigint(20) DEFAULT NULL,
  `name` longtext,
  `title` longtext,
  `description` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `typeSettings` longtext,
  `hidden_` tinyint(4) DEFAULT NULL,
  `friendlyURL` varchar(255) DEFAULT NULL,
  `iconImage` tinyint(4) DEFAULT NULL,
  `iconImageId` bigint(20) DEFAULT NULL,
  `themeId` varchar(75) DEFAULT NULL,
  `colorSchemeId` varchar(75) DEFAULT NULL,
  `wapThemeId` varchar(75) DEFAULT NULL,
  `wapColorSchemeId` varchar(75) DEFAULT NULL,
  `css` longtext,
  `priority` int(11) DEFAULT NULL,
  `layoutPrototypeId` bigint(20) DEFAULT NULL,
  `dlFolderId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`plid`),
  UNIQUE KEY `IX_BC2C4231` (`groupId`,`privateLayout`,`friendlyURL`),
  UNIQUE KEY `IX_7162C27C` (`groupId`,`privateLayout`,`layoutId`),
  KEY `IX_C7FBC998` (`companyId`),
  KEY `IX_FAD05595` (`dlFolderId`),
  KEY `IX_C099D61A` (`groupId`),
  KEY `IX_705F5AA3` (`groupId`,`privateLayout`),
  KEY `IX_6DE88B06` (`groupId`,`privateLayout`,`parentLayoutId`),
  KEY `IX_1A1B61D2` (`groupId`,`privateLayout`,`type_`),
  KEY `IX_23922F7D` (`iconImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layout`
--

LOCK TABLES `layout` WRITE;
/*!40000 ALTER TABLE `layout` DISABLE KEYS */;
INSERT INTO `layout` VALUES (10150,10147,10130,1,1,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Control Panel</name></root>','','','control_panel','',0,'/manage',0,0,'','','','','',0,0,0),(10157,10154,10130,0,1,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Welcome</name></root>','','','portlet','layout-template-id=2_columns_ii\ncolumn-1=58,\n',0,'/home',0,0,'','','','','',0,0,0),(10286,10167,10130,1,1,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Welcome</name></root>','','','portlet','layout-template-id=2_columns_ii\ncolumn-2=29,8,\ncolumn-1=82,23,11,\n',0,'/home',0,0,'','','','','',0,0,0),(10290,10167,10130,0,1,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Welcome</name></root>','','','portlet','layout-template-id=2_columns_ii\ncolumn-2=33,\ncolumn-1=82,3,\n',0,'/home',0,0,'','','','','',0,0,0),(12002,10154,10130,0,2,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Blogs</name></root>','','','portlet','layout-template-id=2_columns_ii\ncolumn-2=33\n',0,'/blogs',0,0,'','','','','',1,0,0),(12006,10154,10130,0,3,0,'<?xml version=\'1.0\' encoding=\'UTF-8\'?><root available-locales=\"en_US\" default-locale=\"en_US\"><name language-id=\"en_US\">Wiki</name></root>','','','portlet','layout-template-id=2_columns_ii\ncolumn-2=36\n',0,'/wiki',0,0,'','','','','',2,0,0);
/*!40000 ALTER TABLE `layout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layoutprototype`
--

DROP TABLE IF EXISTS `layoutprototype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `layoutprototype` (
  `layoutPrototypeId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `name` longtext,
  `description` longtext,
  `settings_` longtext,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`layoutPrototypeId`),
  KEY `IX_30616AAA` (`companyId`),
  KEY `IX_557A639F` (`companyId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layoutprototype`
--

LOCK TABLES `layoutprototype` WRITE;
/*!40000 ALTER TABLE `layoutprototype` DISABLE KEYS */;
/*!40000 ALTER TABLE `layoutprototype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layoutset`
--

DROP TABLE IF EXISTS `layoutset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `layoutset` (
  `layoutSetId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `privateLayout` tinyint(4) DEFAULT NULL,
  `logo` tinyint(4) DEFAULT NULL,
  `logoId` bigint(20) DEFAULT NULL,
  `themeId` varchar(75) DEFAULT NULL,
  `colorSchemeId` varchar(75) DEFAULT NULL,
  `wapThemeId` varchar(75) DEFAULT NULL,
  `wapColorSchemeId` varchar(75) DEFAULT NULL,
  `css` longtext,
  `pageCount` int(11) DEFAULT NULL,
  `virtualHost` varchar(75) DEFAULT NULL,
  `settings_` longtext,
  `layoutSetPrototypeId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`layoutSetId`),
  UNIQUE KEY `IX_48550691` (`groupId`,`privateLayout`),
  KEY `IX_A40B8BEC` (`groupId`),
  KEY `IX_5ABC2905` (`virtualHost`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layoutset`
--

LOCK TABLES `layoutset` WRITE;
/*!40000 ALTER TABLE `layoutset` DISABLE KEYS */;
INSERT INTO `layoutset` VALUES (10148,10147,10130,1,0,0,'classic','01','mobile','01','',1,'','',0),(10149,10147,10130,0,0,0,'classic','01','mobile','01','',0,'','',0),(10155,10154,10130,1,0,0,'classic','01','mobile','01','',0,'','',0),(10156,10154,10130,0,0,0,'classic','01','mobile','01','',3,'','',0),(10162,10161,10130,1,0,0,'classic','01','mobile','01','',0,'','',0),(10163,10161,10130,0,0,0,'classic','01','mobile','01','',0,'','',0),(10168,10167,10130,1,0,0,'classic','01','mobile','01','',1,'','',0),(10169,10167,10130,0,0,0,'classic','01','mobile','01','',1,'','',0),(10305,10304,10130,1,0,0,'classic','01','mobile','01','',0,'','',0),(10306,10304,10130,0,0,0,'classic','01','mobile','01','',0,'','',0),(10314,10313,10130,1,0,0,'classic','01','mobile','01','',0,'','',0),(10315,10313,10130,0,0,0,'classic','01','mobile','01','',0,'','',0);
/*!40000 ALTER TABLE `layoutset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `layoutsetprototype`
--

DROP TABLE IF EXISTS `layoutsetprototype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `layoutsetprototype` (
  `layoutSetPrototypeId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `name` longtext,
  `description` longtext,
  `settings_` longtext,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`layoutSetPrototypeId`),
  KEY `IX_55F63D98` (`companyId`),
  KEY `IX_9178FC71` (`companyId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `layoutsetprototype`
--

LOCK TABLES `layoutsetprototype` WRITE;
/*!40000 ALTER TABLE `layoutsetprototype` DISABLE KEYS */;
/*!40000 ALTER TABLE `layoutsetprototype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listtype`
--

DROP TABLE IF EXISTS `listtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listtype` (
  `listTypeId` int(11) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`listTypeId`),
  KEY `IX_2932DD37` (`type_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listtype`
--

LOCK TABLES `listtype` WRITE;
/*!40000 ALTER TABLE `listtype` DISABLE KEYS */;
INSERT INTO `listtype` VALUES (10000,'Billing','com.liferay.portal.model.Account.address'),(10001,'Other','com.liferay.portal.model.Account.address'),(10002,'P.O. Box','com.liferay.portal.model.Account.address'),(10003,'Shipping','com.liferay.portal.model.Account.address'),(10004,'E-mail','com.liferay.portal.model.Account.emailAddress'),(10005,'E-mail 2','com.liferay.portal.model.Account.emailAddress'),(10006,'E-mail 3','com.liferay.portal.model.Account.emailAddress'),(10007,'Fax','com.liferay.portal.model.Account.phone'),(10008,'Local','com.liferay.portal.model.Account.phone'),(10009,'Other','com.liferay.portal.model.Account.phone'),(10010,'Toll-Free','com.liferay.portal.model.Account.phone'),(10011,'TTY','com.liferay.portal.model.Account.phone'),(10012,'Intranet','com.liferay.portal.model.Account.website'),(10013,'Public','com.liferay.portal.model.Account.website'),(11000,'Business','com.liferay.portal.model.Contact.address'),(11001,'Other','com.liferay.portal.model.Contact.address'),(11002,'Personal','com.liferay.portal.model.Contact.address'),(11003,'E-mail','com.liferay.portal.model.Contact.emailAddress'),(11004,'E-mail 2','com.liferay.portal.model.Contact.emailAddress'),(11005,'E-mail 3','com.liferay.portal.model.Contact.emailAddress'),(11006,'Business','com.liferay.portal.model.Contact.phone'),(11007,'Business Fax','com.liferay.portal.model.Contact.phone'),(11008,'Mobile','com.liferay.portal.model.Contact.phone'),(11009,'Other','com.liferay.portal.model.Contact.phone'),(11010,'Pager','com.liferay.portal.model.Contact.phone'),(11011,'Personal','com.liferay.portal.model.Contact.phone'),(11012,'Personal Fax','com.liferay.portal.model.Contact.phone'),(11013,'TTY','com.liferay.portal.model.Contact.phone'),(11014,'Dr.','com.liferay.portal.model.Contact.prefix'),(11015,'Mr.','com.liferay.portal.model.Contact.prefix'),(11016,'Mrs.','com.liferay.portal.model.Contact.prefix'),(11017,'Ms.','com.liferay.portal.model.Contact.prefix'),(11020,'II','com.liferay.portal.model.Contact.suffix'),(11021,'III','com.liferay.portal.model.Contact.suffix'),(11022,'IV','com.liferay.portal.model.Contact.suffix'),(11023,'Jr.','com.liferay.portal.model.Contact.suffix'),(11024,'PhD.','com.liferay.portal.model.Contact.suffix'),(11025,'Sr.','com.liferay.portal.model.Contact.suffix'),(11026,'Blog','com.liferay.portal.model.Contact.website'),(11027,'Business','com.liferay.portal.model.Contact.website'),(11028,'Other','com.liferay.portal.model.Contact.website'),(11029,'Personal','com.liferay.portal.model.Contact.website'),(12000,'Billing','com.liferay.portal.model.Organization.address'),(12001,'Other','com.liferay.portal.model.Organization.address'),(12002,'P.O. Box','com.liferay.portal.model.Organization.address'),(12003,'Shipping','com.liferay.portal.model.Organization.address'),(12004,'E-mail','com.liferay.portal.model.Organization.emailAddress'),(12005,'E-mail 2','com.liferay.portal.model.Organization.emailAddress'),(12006,'E-mail 3','com.liferay.portal.model.Organization.emailAddress'),(12007,'Fax','com.liferay.portal.model.Organization.phone'),(12008,'Local','com.liferay.portal.model.Organization.phone'),(12009,'Other','com.liferay.portal.model.Organization.phone'),(12010,'Toll-Free','com.liferay.portal.model.Organization.phone'),(12011,'TTY','com.liferay.portal.model.Organization.phone'),(12012,'Administrative','com.liferay.portal.model.Organization.service'),(12013,'Contracts','com.liferay.portal.model.Organization.service'),(12014,'Donation','com.liferay.portal.model.Organization.service'),(12015,'Retail','com.liferay.portal.model.Organization.service'),(12016,'Training','com.liferay.portal.model.Organization.service'),(12017,'Full Member','com.liferay.portal.model.Organization.status'),(12018,'Provisional Member','com.liferay.portal.model.Organization.status'),(12019,'Intranet','com.liferay.portal.model.Organization.website'),(12020,'Public','com.liferay.portal.model.Organization.website');
/*!40000 ALTER TABLE `listtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lock_`
--

DROP TABLE IF EXISTS `lock_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lock_` (
  `uuid_` varchar(75) DEFAULT NULL,
  `lockId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `className` varchar(75) DEFAULT NULL,
  `key_` varchar(200) DEFAULT NULL,
  `owner` varchar(75) DEFAULT NULL,
  `inheritable` tinyint(4) DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`lockId`),
  KEY `IX_228562AD` (`className`,`key_`),
  KEY `IX_E3F1286B` (`expirationDate`),
  KEY `IX_13C5CD3A` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lock_`
--

LOCK TABLES `lock_` WRITE;
/*!40000 ALTER TABLE `lock_` DISABLE KEYS */;
/*!40000 ALTER TABLE `lock_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbban`
--

DROP TABLE IF EXISTS `mbban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbban` (
  `banId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `banUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`banId`),
  UNIQUE KEY `IX_8ABC4E3B` (`groupId`,`banUserId`),
  KEY `IX_69951A25` (`banUserId`),
  KEY `IX_5C3FF12A` (`groupId`),
  KEY `IX_48814BBA` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbban`
--

LOCK TABLES `mbban` WRITE;
/*!40000 ALTER TABLE `mbban` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbcategory`
--

DROP TABLE IF EXISTS `mbcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbcategory` (
  `uuid_` varchar(75) DEFAULT NULL,
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentCategoryId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `threadCount` int(11) DEFAULT NULL,
  `messageCount` int(11) DEFAULT NULL,
  `lastPostDate` datetime DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `IX_F7D28C2F` (`uuid_`,`groupId`),
  KEY `IX_BC735DCF` (`companyId`),
  KEY `IX_BB870C11` (`groupId`),
  KEY `IX_ED292508` (`groupId`,`parentCategoryId`),
  KEY `IX_C2626EDB` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbcategory`
--

LOCK TABLES `mbcategory` WRITE;
/*!40000 ALTER TABLE `mbcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbdiscussion`
--

DROP TABLE IF EXISTS `mbdiscussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbdiscussion` (
  `discussionId` bigint(20) NOT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `threadId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`discussionId`),
  UNIQUE KEY `IX_33A4DE38` (`classNameId`,`classPK`),
  UNIQUE KEY `IX_B5CA2DC` (`threadId`),
  KEY `IX_79D0120B` (`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbdiscussion`
--

LOCK TABLES `mbdiscussion` WRITE;
/*!40000 ALTER TABLE `mbdiscussion` DISABLE KEYS */;
INSERT INTO `mbdiscussion` VALUES (10153,10013,10150,10152),(10160,10013,10157,10159),(10289,10013,10286,10288),(10293,10013,10290,10292),(12005,10013,12002,12004),(12009,10013,12006,12008),(12024,10127,12020,12023);
/*!40000 ALTER TABLE `mbdiscussion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbmailinglist`
--

DROP TABLE IF EXISTS `mbmailinglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbmailinglist` (
  `uuid_` varchar(75) DEFAULT NULL,
  `mailingListId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `emailAddress` varchar(75) DEFAULT NULL,
  `inProtocol` varchar(75) DEFAULT NULL,
  `inServerName` varchar(75) DEFAULT NULL,
  `inServerPort` int(11) DEFAULT NULL,
  `inUseSSL` tinyint(4) DEFAULT NULL,
  `inUserName` varchar(75) DEFAULT NULL,
  `inPassword` varchar(75) DEFAULT NULL,
  `inReadInterval` int(11) DEFAULT NULL,
  `outEmailAddress` varchar(75) DEFAULT NULL,
  `outCustom` tinyint(4) DEFAULT NULL,
  `outServerName` varchar(75) DEFAULT NULL,
  `outServerPort` int(11) DEFAULT NULL,
  `outUseSSL` tinyint(4) DEFAULT NULL,
  `outUserName` varchar(75) DEFAULT NULL,
  `outPassword` varchar(75) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`mailingListId`),
  UNIQUE KEY `IX_76CE9CDD` (`groupId`,`categoryId`),
  UNIQUE KEY `IX_E858F170` (`uuid_`,`groupId`),
  KEY `IX_BFEB984F` (`active_`),
  KEY `IX_4115EC7A` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbmailinglist`
--

LOCK TABLES `mbmailinglist` WRITE;
/*!40000 ALTER TABLE `mbmailinglist` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbmailinglist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbmessage`
--

DROP TABLE IF EXISTS `mbmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbmessage` (
  `uuid_` varchar(75) DEFAULT NULL,
  `messageId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `threadId` bigint(20) DEFAULT NULL,
  `parentMessageId` bigint(20) DEFAULT NULL,
  `subject` varchar(75) DEFAULT NULL,
  `body` longtext,
  `attachments` tinyint(4) DEFAULT NULL,
  `anonymous` tinyint(4) DEFAULT NULL,
  `priority` double DEFAULT NULL,
  `allowPingbacks` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`messageId`),
  UNIQUE KEY `IX_8D12316E` (`uuid_`,`groupId`),
  KEY `IX_51A8D44D` (`classNameId`,`classPK`),
  KEY `IX_F6687633` (`classNameId`,`classPK`,`status`),
  KEY `IX_B1432D30` (`companyId`),
  KEY `IX_1AD93C16` (`companyId`,`status`),
  KEY `IX_5B153FB2` (`groupId`),
  KEY `IX_1073AB9F` (`groupId`,`categoryId`),
  KEY `IX_4257DB85` (`groupId`,`categoryId`,`status`),
  KEY `IX_B674AB58` (`groupId`,`categoryId`,`threadId`),
  KEY `IX_385E123E` (`groupId`,`categoryId`,`threadId`,`status`),
  KEY `IX_ED39AC98` (`groupId`,`status`),
  KEY `IX_8EB8C5EC` (`groupId`,`userId`),
  KEY `IX_377858D2` (`groupId`,`userId`,`status`),
  KEY `IX_75B95071` (`threadId`),
  KEY `IX_A7038CD7` (`threadId`,`parentMessageId`),
  KEY `IX_9DC8E57` (`threadId`,`status`),
  KEY `IX_7A040C32` (`userId`),
  KEY `IX_C57B16BC` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbmessage`
--

LOCK TABLES `mbmessage` WRITE;
/*!40000 ALTER TABLE `mbmessage` DISABLE KEYS */;
INSERT INTO `mbmessage` VALUES ('1e9d2234-586d-4c5d-8e7b-1cb796e99712',10151,10147,10130,10133,' ','2010-06-08 07:10:56','2010-06-08 07:10:56',10013,10150,-1,10152,0,'10150','10150',0,1,0,0,0,10133,' ','2010-06-08 07:10:56'),('e65c6bb3-7103-42ca-bd19-31ae9a2782f8',10158,10154,10130,10133,' ','2010-06-08 07:10:56','2010-06-08 07:10:56',10013,10157,-1,10159,0,'10157','10157',0,1,0,0,0,10133,' ','2010-06-08 07:10:56'),('23b9b7f4-4117-4eee-882b-774c670a1986',10287,10167,10130,10165,'Test Test','2010-06-08 07:13:04','2010-06-08 07:13:04',10013,10286,-1,10288,0,'10286','10286',0,0,0,0,0,10165,'Test Test','2010-06-08 07:13:05'),('cf7d8398-a4d4-48d8-a247-3d3fd0bccdfd',10291,10167,10130,10165,'Test Test','2010-06-08 07:13:05','2010-06-08 07:13:05',10013,10290,-1,10292,0,'10290','10290',0,0,0,0,0,10165,'Test Test','2010-06-08 07:13:05'),('15891de0-059d-4098-837e-00432a6ffbee',12003,10154,10130,10165,'Michael Han','2010-06-08 07:51:38','2010-06-08 07:51:38',10013,12002,-1,12004,0,'12002','12002',0,0,0,0,0,10165,'Michael Han','2010-06-08 07:51:38'),('6de417fc-91a6-49f5-86ce-9e3717f0e349',12007,10154,10130,10165,'Michael Han','2010-06-08 07:51:41','2010-06-08 07:51:41',10013,12006,-1,12008,0,'12006','12006',0,0,0,0,0,10165,'Michael Han','2010-06-08 07:51:41'),('92cc2694-7989-47a9-8991-44c9d8fd1c0e',12022,10154,10130,10165,'Michael Han','2010-06-08 07:52:15','2010-06-08 07:52:15',10127,12020,-1,12023,0,'12020','12020',0,0,0,0,0,10165,'Michael Han','2010-06-08 07:52:15');
/*!40000 ALTER TABLE `mbmessage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbmessageflag`
--

DROP TABLE IF EXISTS `mbmessageflag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbmessageflag` (
  `messageFlagId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `threadId` bigint(20) DEFAULT NULL,
  `messageId` bigint(20) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`messageFlagId`),
  UNIQUE KEY `IX_E9EB6194` (`userId`,`messageId`,`flag`),
  KEY `IX_D180D4AE` (`messageId`),
  KEY `IX_A6973A8E` (`messageId`,`flag`),
  KEY `IX_C1C9A8FD` (`threadId`),
  KEY `IX_3CFD579D` (`threadId`,`flag`),
  KEY `IX_7B2917BE` (`userId`),
  KEY `IX_2EA537D7` (`userId`,`threadId`,`flag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbmessageflag`
--

LOCK TABLES `mbmessageflag` WRITE;
/*!40000 ALTER TABLE `mbmessageflag` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbmessageflag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbstatsuser`
--

DROP TABLE IF EXISTS `mbstatsuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbstatsuser` (
  `statsUserId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `messageCount` int(11) DEFAULT NULL,
  `lastPostDate` datetime DEFAULT NULL,
  PRIMARY KEY (`statsUserId`),
  UNIQUE KEY `IX_9168E2C9` (`groupId`,`userId`),
  KEY `IX_A00A898F` (`groupId`),
  KEY `IX_FAB5A88B` (`groupId`,`messageCount`),
  KEY `IX_847F92B5` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbstatsuser`
--

LOCK TABLES `mbstatsuser` WRITE;
/*!40000 ALTER TABLE `mbstatsuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbstatsuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbthread`
--

DROP TABLE IF EXISTS `mbthread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mbthread` (
  `threadId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `rootMessageId` bigint(20) DEFAULT NULL,
  `messageCount` int(11) DEFAULT NULL,
  `viewCount` int(11) DEFAULT NULL,
  `lastPostByUserId` bigint(20) DEFAULT NULL,
  `lastPostDate` datetime DEFAULT NULL,
  `priority` double DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`threadId`),
  KEY `IX_41F6DC8A` (`categoryId`,`priority`),
  KEY `IX_95C0EA45` (`groupId`),
  KEY `IX_9A2D11B2` (`groupId`,`categoryId`),
  KEY `IX_50F1904A` (`groupId`,`categoryId`,`lastPostDate`),
  KEY `IX_485F7E98` (`groupId`,`categoryId`,`status`),
  KEY `IX_E1E7142B` (`groupId`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbthread`
--

LOCK TABLES `mbthread` WRITE;
/*!40000 ALTER TABLE `mbthread` DISABLE KEYS */;
INSERT INTO `mbthread` VALUES (10152,10147,-1,10151,1,0,0,'2010-06-08 07:10:56',0,0,10133,' ','2010-06-08 07:10:56'),(10159,10154,-1,10158,1,0,0,'2010-06-08 07:10:56',0,0,10133,' ','2010-06-08 07:10:56'),(10288,10167,-1,10287,1,0,10165,'2010-06-08 07:13:05',0,0,10165,'Test Test','2010-06-08 07:13:05'),(10292,10167,-1,10291,1,0,10165,'2010-06-08 07:13:05',0,0,10165,'Test Test','2010-06-08 07:13:05'),(12004,10154,-1,12003,1,0,10165,'2010-06-08 07:51:38',0,0,10165,'Michael Han','2010-06-08 07:51:38'),(12008,10154,-1,12007,1,0,10165,'2010-06-08 07:51:41',0,0,10165,'Michael Han','2010-06-08 07:51:41'),(12023,10154,-1,12022,1,0,10165,'2010-06-08 07:52:15',0,0,10165,'Michael Han','2010-06-08 07:52:15');
/*!40000 ALTER TABLE `mbthread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membershiprequest`
--

DROP TABLE IF EXISTS `membershiprequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membershiprequest` (
  `membershipRequestId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `comments` longtext,
  `replyComments` longtext,
  `replyDate` datetime DEFAULT NULL,
  `replierUserId` bigint(20) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  PRIMARY KEY (`membershipRequestId`),
  KEY `IX_8A1CC4B` (`groupId`),
  KEY `IX_C28C72EC` (`groupId`,`statusId`),
  KEY `IX_66D70879` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membershiprequest`
--

LOCK TABLES `membershiprequest` WRITE;
/*!40000 ALTER TABLE `membershiprequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `membershiprequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organization_`
--

DROP TABLE IF EXISTS `organization_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organization_` (
  `organizationId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `parentOrganizationId` bigint(20) DEFAULT NULL,
  `leftOrganizationId` bigint(20) DEFAULT NULL,
  `rightOrganizationId` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `recursable` tinyint(4) DEFAULT NULL,
  `regionId` bigint(20) DEFAULT NULL,
  `countryId` bigint(20) DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `comments` longtext,
  PRIMARY KEY (`organizationId`),
  UNIQUE KEY `IX_E301BDF5` (`companyId`,`name`),
  KEY `IX_834BCEB6` (`companyId`),
  KEY `IX_418E4522` (`companyId`,`parentOrganizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organization_`
--

LOCK TABLES `organization_` WRITE;
/*!40000 ALTER TABLE `organization_` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orggrouppermission`
--

DROP TABLE IF EXISTS `orggrouppermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orggrouppermission` (
  `organizationId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`organizationId`,`groupId`,`permissionId`),
  KEY `IX_A425F71A` (`groupId`),
  KEY `IX_6C53DA4E` (`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orggrouppermission`
--

LOCK TABLES `orggrouppermission` WRITE;
/*!40000 ALTER TABLE `orggrouppermission` DISABLE KEYS */;
/*!40000 ALTER TABLE `orggrouppermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orggrouprole`
--

DROP TABLE IF EXISTS `orggrouprole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orggrouprole` (
  `organizationId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`organizationId`,`groupId`,`roleId`),
  KEY `IX_4A527DD3` (`groupId`),
  KEY `IX_AB044D1C` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orggrouprole`
--

LOCK TABLES `orggrouprole` WRITE;
/*!40000 ALTER TABLE `orggrouprole` DISABLE KEYS */;
/*!40000 ALTER TABLE `orggrouprole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orglabor`
--

DROP TABLE IF EXISTS `orglabor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orglabor` (
  `orgLaborId` bigint(20) NOT NULL,
  `organizationId` bigint(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `sunOpen` int(11) DEFAULT NULL,
  `sunClose` int(11) DEFAULT NULL,
  `monOpen` int(11) DEFAULT NULL,
  `monClose` int(11) DEFAULT NULL,
  `tueOpen` int(11) DEFAULT NULL,
  `tueClose` int(11) DEFAULT NULL,
  `wedOpen` int(11) DEFAULT NULL,
  `wedClose` int(11) DEFAULT NULL,
  `thuOpen` int(11) DEFAULT NULL,
  `thuClose` int(11) DEFAULT NULL,
  `friOpen` int(11) DEFAULT NULL,
  `friClose` int(11) DEFAULT NULL,
  `satOpen` int(11) DEFAULT NULL,
  `satClose` int(11) DEFAULT NULL,
  PRIMARY KEY (`orgLaborId`),
  KEY `IX_6AF0D434` (`organizationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orglabor`
--

LOCK TABLES `orglabor` WRITE;
/*!40000 ALTER TABLE `orglabor` DISABLE KEYS */;
/*!40000 ALTER TABLE `orglabor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordpolicy`
--

DROP TABLE IF EXISTS `passwordpolicy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwordpolicy` (
  `passwordPolicyId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `defaultPolicy` tinyint(4) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `changeable` tinyint(4) DEFAULT NULL,
  `changeRequired` tinyint(4) DEFAULT NULL,
  `minAge` bigint(20) DEFAULT NULL,
  `checkSyntax` tinyint(4) DEFAULT NULL,
  `allowDictionaryWords` tinyint(4) DEFAULT NULL,
  `minAlphanumeric` int(11) DEFAULT NULL,
  `minLength` int(11) DEFAULT NULL,
  `minLowerCase` int(11) DEFAULT NULL,
  `minNumbers` int(11) DEFAULT NULL,
  `minSymbols` int(11) DEFAULT NULL,
  `minUpperCase` int(11) DEFAULT NULL,
  `history` tinyint(4) DEFAULT NULL,
  `historyCount` int(11) DEFAULT NULL,
  `expireable` tinyint(4) DEFAULT NULL,
  `maxAge` bigint(20) DEFAULT NULL,
  `warningTime` bigint(20) DEFAULT NULL,
  `graceLimit` int(11) DEFAULT NULL,
  `lockout` tinyint(4) DEFAULT NULL,
  `maxFailure` int(11) DEFAULT NULL,
  `lockoutDuration` bigint(20) DEFAULT NULL,
  `requireUnlock` tinyint(4) DEFAULT NULL,
  `resetFailureCount` bigint(20) DEFAULT NULL,
  `resetTicketMaxAge` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`passwordPolicyId`),
  UNIQUE KEY `IX_3FBFA9F4` (`companyId`,`name`),
  KEY `IX_2C1142E` (`companyId`,`defaultPolicy`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordpolicy`
--

LOCK TABLES `passwordpolicy` WRITE;
/*!40000 ALTER TABLE `passwordpolicy` DISABLE KEYS */;
INSERT INTO `passwordpolicy` VALUES (10164,10130,10133,' ','2010-06-08 07:10:56','2010-06-08 07:10:56',1,'Default Password Policy','Default Password Policy',1,0,0,0,1,0,6,0,1,0,1,0,6,0,8640000,86400,0,0,3,0,1,600,86400);
/*!40000 ALTER TABLE `passwordpolicy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordpolicyrel`
--

DROP TABLE IF EXISTS `passwordpolicyrel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwordpolicyrel` (
  `passwordPolicyRelId` bigint(20) NOT NULL,
  `passwordPolicyId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`passwordPolicyRelId`),
  KEY `IX_C3A17327` (`classNameId`,`classPK`),
  KEY `IX_CD25266E` (`passwordPolicyId`),
  KEY `IX_ED7CF243` (`passwordPolicyId`,`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordpolicyrel`
--

LOCK TABLES `passwordpolicyrel` WRITE;
/*!40000 ALTER TABLE `passwordpolicyrel` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwordpolicyrel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordtracker`
--

DROP TABLE IF EXISTS `passwordtracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passwordtracker` (
  `passwordTrackerId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `password_` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`passwordTrackerId`),
  KEY `IX_326F75BD` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordtracker`
--

LOCK TABLES `passwordtracker` WRITE;
/*!40000 ALTER TABLE `passwordtracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `passwordtracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_`
--

DROP TABLE IF EXISTS `permission_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_` (
  `permissionId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `actionId` varchar(75) DEFAULT NULL,
  `resourceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`permissionId`),
  UNIQUE KEY `IX_4D19C2B8` (`actionId`,`resourceId`),
  KEY `IX_F090C113` (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_`
--

LOCK TABLES `permission_` WRITE;
/*!40000 ALTER TABLE `permission_` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone` (
  `phoneId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `number_` varchar(75) DEFAULT NULL,
  `extension` varchar(75) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `primary_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`phoneId`),
  KEY `IX_9F704A14` (`companyId`),
  KEY `IX_A2E4AFBA` (`companyId`,`classNameId`),
  KEY `IX_9A53569` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_812CE07A` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_F202B9CE` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pluginsetting`
--

DROP TABLE IF EXISTS `pluginsetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pluginsetting` (
  `pluginSettingId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `pluginId` varchar(75) DEFAULT NULL,
  `pluginType` varchar(75) DEFAULT NULL,
  `roles` longtext,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`pluginSettingId`),
  UNIQUE KEY `IX_7171B2E8` (`companyId`,`pluginId`,`pluginType`),
  KEY `IX_B9746445` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pluginsetting`
--

LOCK TABLES `pluginsetting` WRITE;
/*!40000 ALTER TABLE `pluginsetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `pluginsetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollschoice`
--

DROP TABLE IF EXISTS `pollschoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollschoice` (
  `uuid_` varchar(75) DEFAULT NULL,
  `choiceId` bigint(20) NOT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`choiceId`),
  UNIQUE KEY `IX_D76DD2CF` (`questionId`,`name`),
  KEY `IX_EC370F10` (`questionId`),
  KEY `IX_6660B399` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollschoice`
--

LOCK TABLES `pollschoice` WRITE;
/*!40000 ALTER TABLE `pollschoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `pollschoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollsquestion`
--

DROP TABLE IF EXISTS `pollsquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollsquestion` (
  `uuid_` varchar(75) DEFAULT NULL,
  `questionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `title` longtext,
  `description` longtext,
  `expirationDate` datetime DEFAULT NULL,
  `lastVoteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`questionId`),
  UNIQUE KEY `IX_F3C9F36` (`uuid_`,`groupId`),
  KEY `IX_9FF342EA` (`groupId`),
  KEY `IX_51F087F4` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollsquestion`
--

LOCK TABLES `pollsquestion` WRITE;
/*!40000 ALTER TABLE `pollsquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `pollsquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pollsvote`
--

DROP TABLE IF EXISTS `pollsvote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pollsvote` (
  `voteId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `questionId` bigint(20) DEFAULT NULL,
  `choiceId` bigint(20) DEFAULT NULL,
  `voteDate` datetime DEFAULT NULL,
  PRIMARY KEY (`voteId`),
  UNIQUE KEY `IX_1BBFD4D3` (`questionId`,`userId`),
  KEY `IX_D5DF7B54` (`choiceId`),
  KEY `IX_12112599` (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pollsvote`
--

LOCK TABLES `pollsvote` WRITE;
/*!40000 ALTER TABLE `pollsvote` DISABLE KEYS */;
/*!40000 ALTER TABLE `pollsvote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portlet`
--

DROP TABLE IF EXISTS `portlet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portlet` (
  `id_` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `portletId` varchar(200) DEFAULT NULL,
  `roles` longtext,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `IX_12B5E51D` (`companyId`,`portletId`),
  KEY `IX_80CC9508` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portlet`
--

LOCK TABLES `portlet` WRITE;
/*!40000 ALTER TABLE `portlet` DISABLE KEYS */;
INSERT INTO `portlet` VALUES (10171,10130,'27','',1),(10172,10130,'98','',1),(10173,10130,'152','',1),(10174,10130,'66','',1),(10175,10130,'134','',1),(10176,10130,'130','',1),(10177,10130,'122','',1),(10178,10130,'36','',1),(10179,10130,'129','',1),(10180,10130,'26','',1),(10181,10130,'104','',1),(10182,10130,'100','',1),(10183,10130,'19','',1),(10184,10130,'64','',1),(10185,10130,'153','',1),(10186,10130,'128','',1),(10187,10130,'29','',1),(10188,10130,'154','',1),(10189,10130,'148','',1),(10190,10130,'8','',1),(10191,10130,'11','',1),(10192,10130,'58','',1),(10193,10130,'155','',1),(10194,10130,'71','',1),(10195,10130,'97','',1),(10196,10130,'39','',1),(10197,10130,'85','',1),(10198,10130,'118','',1),(10199,10130,'79','',1),(10200,10130,'107','',1),(10201,10130,'30','',1),(10202,10130,'147','',1),(10203,10130,'48','',1),(10204,10130,'125','',1),(10205,10130,'146','',1),(10206,10130,'62','',1),(10207,10130,'108','',1),(10208,10130,'84','',1),(10209,10130,'101','',1),(10210,10130,'121','',1),(10211,10130,'37','',1),(10212,10130,'143','',1),(10213,10130,'77','',1),(10214,10130,'6','',1),(10215,10130,'115','',1),(10216,10130,'56','',1),(10217,10130,'16','',1),(10218,10130,'111','',1),(10219,10130,'3','',1),(10220,10130,'23','',1),(10221,10130,'20','',1),(10222,10130,'83','',1),(10223,10130,'99','',1),(10224,10130,'70','',1),(10225,10130,'141','',1),(10226,10130,'9','',1),(10227,10130,'28','',1),(10228,10130,'137','',1),(10229,10130,'47','',1),(10230,10130,'15','',1),(10231,10130,'116','',1),(10232,10130,'82','',1),(10233,10130,'151','',1),(10234,10130,'54','',1),(10235,10130,'132','',1),(10236,10130,'34','',1),(10237,10130,'61','',1),(10238,10130,'73','',1),(10239,10130,'31','',1),(10240,10130,'136','',1),(10241,10130,'50','',1),(10242,10130,'127','',1),(10243,10130,'25','',1),(10244,10130,'24','',1),(10245,10130,'150','',1),(10246,10130,'33','',1),(10247,10130,'126','',1),(10248,10130,'114','',1),(10249,10130,'149','',1),(10250,10130,'67','',1),(10251,10130,'110','',1),(10252,10130,'59','',1),(10253,10130,'135','',1),(10254,10130,'131','',1),(10255,10130,'102','',1);
/*!40000 ALTER TABLE `portlet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portletitem`
--

DROP TABLE IF EXISTS `portletitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portletitem` (
  `portletItemId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `portletId` varchar(75) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`portletItemId`),
  KEY `IX_96BDD537` (`groupId`,`classNameId`),
  KEY `IX_D699243F` (`groupId`,`name`,`portletId`,`classNameId`),
  KEY `IX_2C61314E` (`groupId`,`portletId`),
  KEY `IX_E922D6C0` (`groupId`,`portletId`,`classNameId`),
  KEY `IX_8E71167F` (`groupId`,`portletId`,`classNameId`,`name`),
  KEY `IX_33B8CE8D` (`groupId`,`portletId`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portletitem`
--

LOCK TABLES `portletitem` WRITE;
/*!40000 ALTER TABLE `portletitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `portletitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portletpreferences`
--

DROP TABLE IF EXISTS `portletpreferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portletpreferences` (
  `portletPreferencesId` bigint(20) NOT NULL,
  `ownerId` bigint(20) DEFAULT NULL,
  `ownerType` int(11) DEFAULT NULL,
  `plid` bigint(20) DEFAULT NULL,
  `portletId` varchar(200) DEFAULT NULL,
  `preferences` longtext,
  PRIMARY KEY (`portletPreferencesId`),
  UNIQUE KEY `IX_C7057FF7` (`ownerId`,`ownerType`,`plid`,`portletId`),
  KEY `IX_E4F13E6E` (`ownerId`,`ownerType`,`plid`),
  KEY `IX_F15C1C4F` (`plid`),
  KEY `IX_D340DB76` (`plid`,`portletId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portletpreferences`
--

LOCK TABLES `portletpreferences` WRITE;
/*!40000 ALTER TABLE `portletpreferences` DISABLE KEYS */;
INSERT INTO `portletpreferences` VALUES (10129,0,1,0,'LIFERAY_PORTAL','<portlet-preferences />'),(10135,10130,1,0,'LIFERAY_PORTAL','<portlet-preferences />'),(10282,0,3,10157,'103','<portlet-preferences />'),(10284,0,3,10157,'58','<portlet-preferences />'),(10285,10133,4,0,'LIFERAY_PORTAL','<portlet-preferences />'),(10294,10165,4,0,'LIFERAY_PORTAL','<portlet-preferences><preference><name>79#users-order-by-col</name><value>last-name</value></preference><preference><name>79#users-order-by-type</name><value>asc</value></preference></portlet-preferences>'),(10295,0,3,10157,'145','<portlet-preferences />'),(10296,0,3,10150,'87','<portlet-preferences />'),(10297,0,3,10150,'145','<portlet-preferences />'),(10298,0,3,10150,'125','<portlet-preferences />'),(12001,0,3,10157,'88','<portlet-preferences />'),(12010,0,3,12002,'103','<portlet-preferences />'),(12011,0,3,12002,'145','<portlet-preferences />'),(12012,0,3,12002,'87','<portlet-preferences />'),(12013,0,3,12002,'33','<portlet-preferences />'),(12014,0,3,12006,'103','<portlet-preferences />'),(12015,0,3,12006,'145','<portlet-preferences />'),(12016,0,3,12006,'87','<portlet-preferences />'),(12017,0,3,12006,'36','<portlet-preferences />'),(12026,0,3,10150,'128','<portlet-preferences />');
/*!40000 ALTER TABLE `portletpreferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_blob_triggers`
--

DROP TABLE IF EXISTS `quartz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_blob_triggers` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_blob_triggers`
--

LOCK TABLES `quartz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `quartz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_calendars`
--

DROP TABLE IF EXISTS `quartz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_calendars` (
  `CALENDAR_NAME` varchar(80) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_calendars`
--

LOCK TABLES `quartz_calendars` WRITE;
/*!40000 ALTER TABLE `quartz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_cron_triggers`
--

DROP TABLE IF EXISTS `quartz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_cron_triggers` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `CRON_EXPRESSION` varchar(80) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_cron_triggers`
--

LOCK TABLES `quartz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `quartz_cron_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_fired_triggers`
--

DROP TABLE IF EXISTS `quartz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_fired_triggers` (
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `INSTANCE_NAME` varchar(80) NOT NULL,
  `FIRED_TIME` bigint(20) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(80) DEFAULT NULL,
  `JOB_GROUP` varchar(80) DEFAULT NULL,
  `IS_STATEFUL` tinyint(4) DEFAULT NULL,
  `REQUESTS_RECOVERY` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_ID`),
  KEY `IX_804154AF` (`INSTANCE_NAME`),
  KEY `IX_BAB9A1F7` (`JOB_GROUP`),
  KEY `IX_ADEE6A17` (`JOB_NAME`),
  KEY `IX_64B194F2` (`TRIGGER_GROUP`),
  KEY `IX_5FEABBC` (`TRIGGER_NAME`),
  KEY `IX_20D8706C` (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_fired_triggers`
--

LOCK TABLES `quartz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `quartz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_job_details`
--

DROP TABLE IF EXISTS `quartz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_job_details` (
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `DESCRIPTION` varchar(120) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(128) NOT NULL,
  `IS_DURABLE` tinyint(4) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `IS_STATEFUL` tinyint(4) NOT NULL,
  `REQUESTS_RECOVERY` tinyint(4) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_job_details`
--

LOCK TABLES `quartz_job_details` WRITE;
/*!40000 ALTER TABLE `quartz_job_details` DISABLE KEYS */;
INSERT INTO `quartz_job_details` VALUES ('com.liferay.portlet.social.messaging.CheckEquityLogMessageListener','com.liferay.portlet.social.messaging.CheckEquityLogMessageListener',NULL,'com.liferay.portal.scheduler.job.MessageSenderJob',0,0,0,0,'¨Ì\0sr\0org.quartz.JobDataMapü∞ÉËø©∞À\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMapÇË√˚≈](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMapÊ.≠(v\nŒ\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap⁄¡√`—\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0messagesr\0+com.liferay.portal.kernel.messaging.MessageôªAofgÑ^\0L\0_destinationNamet\0Ljava/lang/String;L\0_payloadt\0Ljava/lang/Object;L\0_responseDestinationNameq\0~\0	L\0_responseIdq\0~\0	L\0_valuesq\0~\0xpppppsq\0~\0?@\0\0\0\0\0w\0\0\0\0\0\0t\0receiver_keyt\0Öcom.liferay.portlet.social.messaging.CheckEquityLogMessageListener:com.liferay.portlet.social.messaging.CheckEquityLogMessageListenerxt\0descriptionpt\0destinationt\0\Zliferay/scheduler_dispatchx\0');
/*!40000 ALTER TABLE `quartz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_job_listeners`
--

DROP TABLE IF EXISTS `quartz_job_listeners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_job_listeners` (
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `JOB_LISTENER` varchar(80) NOT NULL,
  PRIMARY KEY (`JOB_NAME`,`JOB_GROUP`,`JOB_LISTENER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_job_listeners`
--

LOCK TABLES `quartz_job_listeners` WRITE;
/*!40000 ALTER TABLE `quartz_job_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_job_listeners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_locks`
--

DROP TABLE IF EXISTS `quartz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_locks` (
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_locks`
--

LOCK TABLES `quartz_locks` WRITE;
/*!40000 ALTER TABLE `quartz_locks` DISABLE KEYS */;
INSERT INTO `quartz_locks` VALUES ('CALENDAR_ACCESS'),('JOB_ACCESS'),('MISFIRE_ACCESS'),('STATE_ACCESS'),('TRIGGER_ACCESS');
/*!40000 ALTER TABLE `quartz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `quartz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_paused_trigger_grps` (
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  PRIMARY KEY (`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_paused_trigger_grps`
--

LOCK TABLES `quartz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `quartz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_scheduler_state`
--

DROP TABLE IF EXISTS `quartz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_scheduler_state` (
  `INSTANCE_NAME` varchar(80) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(20) NOT NULL,
  `CHECKIN_INTERVAL` bigint(20) NOT NULL,
  PRIMARY KEY (`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_scheduler_state`
--

LOCK TABLES `quartz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `quartz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_simple_triggers`
--

DROP TABLE IF EXISTS `quartz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_simple_triggers` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `REPEAT_COUNT` bigint(20) NOT NULL,
  `REPEAT_INTERVAL` bigint(20) NOT NULL,
  `TIMES_TRIGGERED` bigint(20) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_simple_triggers`
--

LOCK TABLES `quartz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `quartz_simple_triggers` DISABLE KEYS */;
INSERT INTO `quartz_simple_triggers` VALUES ('com.liferay.portlet.social.messaging.CheckEquityLogMessageListener','com.liferay.portlet.social.messaging.CheckEquityLogMessageListener',-1,86400000,1);
/*!40000 ALTER TABLE `quartz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_trigger_listeners`
--

DROP TABLE IF EXISTS `quartz_trigger_listeners`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_trigger_listeners` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `TRIGGER_LISTENER` varchar(80) NOT NULL,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_LISTENER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_trigger_listeners`
--

LOCK TABLES `quartz_trigger_listeners` WRITE;
/*!40000 ALTER TABLE `quartz_trigger_listeners` DISABLE KEYS */;
/*!40000 ALTER TABLE `quartz_trigger_listeners` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartz_triggers`
--

DROP TABLE IF EXISTS `quartz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quartz_triggers` (
  `TRIGGER_NAME` varchar(80) NOT NULL,
  `TRIGGER_GROUP` varchar(80) NOT NULL,
  `JOB_NAME` varchar(80) NOT NULL,
  `JOB_GROUP` varchar(80) NOT NULL,
  `IS_VOLATILE` tinyint(4) NOT NULL,
  `DESCRIPTION` varchar(120) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(20) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(20) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(20) NOT NULL,
  `END_TIME` bigint(20) DEFAULT NULL,
  `CALENDAR_NAME` varchar(80) DEFAULT NULL,
  `MISFIRE_INSTR` int(11) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IX_F7655CC3` (`NEXT_FIRE_TIME`),
  KEY `IX_9955EFB5` (`TRIGGER_STATE`),
  KEY `IX_8040C593` (`TRIGGER_STATE`,`NEXT_FIRE_TIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartz_triggers`
--

LOCK TABLES `quartz_triggers` WRITE;
/*!40000 ALTER TABLE `quartz_triggers` DISABLE KEYS */;
INSERT INTO `quartz_triggers` VALUES ('com.liferay.portlet.social.messaging.CheckEquityLogMessageListener','com.liferay.portlet.social.messaging.CheckEquityLogMessageListener','com.liferay.portlet.social.messaging.CheckEquityLogMessageListener','com.liferay.portlet.social.messaging.CheckEquityLogMessageListener',0,NULL,1276069810762,1275983410762,5,'WAITING','SIMPLE',1275983410762,0,NULL,0,'');
/*!40000 ALTER TABLE `quartz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingsentry`
--

DROP TABLE IF EXISTS `ratingsentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratingsentry` (
  `entryId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `score` double DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  UNIQUE KEY `IX_B47E3C11` (`userId`,`classNameId`,`classPK`),
  KEY `IX_16184D57` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingsentry`
--

LOCK TABLES `ratingsentry` WRITE;
/*!40000 ALTER TABLE `ratingsentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratingsentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingsstats`
--

DROP TABLE IF EXISTS `ratingsstats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratingsstats` (
  `statsId` bigint(20) NOT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `totalEntries` int(11) DEFAULT NULL,
  `totalScore` double DEFAULT NULL,
  `averageScore` double DEFAULT NULL,
  PRIMARY KEY (`statsId`),
  UNIQUE KEY `IX_A6E99284` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingsstats`
--

LOCK TABLES `ratingsstats` WRITE;
/*!40000 ALTER TABLE `ratingsstats` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratingsstats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region`
--

DROP TABLE IF EXISTS `region`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region` (
  `regionId` bigint(20) NOT NULL,
  `countryId` bigint(20) DEFAULT NULL,
  `regionCode` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`regionId`),
  KEY `IX_2D9A426F` (`active_`),
  KEY `IX_16D87CA7` (`countryId`),
  KEY `IX_11FB3E42` (`countryId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region`
--

LOCK TABLES `region` WRITE;
/*!40000 ALTER TABLE `region` DISABLE KEYS */;
INSERT INTO `region` VALUES (1001,1,'AB','Alberta',1),(1002,1,'BC','British Columbia',1),(1003,1,'MB','Manitoba',1),(1004,1,'NB','New Brunswick',1),(1005,1,'NL','Newfoundland and Labrador',1),(1006,1,'NT','Northwest Territories',1),(1007,1,'NS','Nova Scotia',1),(1008,1,'NU','Nunavut',1),(1009,1,'ON','Ontario',1),(1010,1,'PE','Prince Edward Island',1),(1011,1,'QC','Quebec',1),(1012,1,'SK','Saskatchewan',1),(1013,1,'YT','Yukon',1),(3001,3,'A','Alsace',1),(3002,3,'B','Aquitaine',1),(3003,3,'C','Auvergne',1),(3004,3,'P','Basse-Normandie',1),(3005,3,'D','Bourgogne',1),(3006,3,'E','Bretagne',1),(3007,3,'F','Centre',1),(3008,3,'G','Champagne-Ardenne',1),(3009,3,'H','Corse',1),(3010,3,'GF','Guyane',1),(3011,3,'I','Franche Comt√©',1),(3012,3,'GP','Guadeloupe',1),(3013,3,'Q','Haute-Normandie',1),(3014,3,'J','√éle-de-France',1),(3015,3,'K','Languedoc-Roussillon',1),(3016,3,'L','Limousin',1),(3017,3,'M','Lorraine',1),(3018,3,'MQ','Martinique',1),(3019,3,'N','Midi-Pyr√©n√©es',1),(3020,3,'O','Nord Pas de Calais',1),(3021,3,'R','Pays de la Loire',1),(3022,3,'S','Picardie',1),(3023,3,'T','Poitou-Charentes',1),(3024,3,'U','Provence-Alpes-C√¥te-d\'Azur',1),(3025,3,'RE','R√©union',1),(3026,3,'V','Rh√¥ne-Alpes',1),(4001,4,'BW','Baden-W√ºrttemberg',1),(4002,4,'BY','Bayern',1),(4003,4,'BE','Berlin',1),(4004,4,'BR','Brandenburg',1),(4005,4,'HB','Bremen',1),(4006,4,'HH','Hamburg',1),(4007,4,'HE','Hessen',1),(4008,4,'MV','Mecklenburg-Vorpommern',1),(4009,4,'NI','Niedersachsen',1),(4010,4,'NW','Nordrhein-Westfalen',1),(4011,4,'RP','Rheinland-Pfalz',1),(4012,4,'SL','Saarland',1),(4013,4,'SN','Sachsen',1),(4014,4,'ST','Sachsen-Anhalt',1),(4015,4,'SH','Schleswig-Holstein',1),(4016,4,'TH','Th√ºringen',1),(8001,8,'AG','Agrigento',1),(8002,8,'AL','Alessandria',1),(8003,8,'AN','Ancona',1),(8004,8,'AO','Aosta',1),(8005,8,'AR','Arezzo',1),(8006,8,'AP','Ascoli Piceno',1),(8007,8,'AT','Asti',1),(8008,8,'AV','Avellino',1),(8009,8,'BA','Bari',1),(8010,8,'BT','Barletta-Andria-Trani',1),(8011,8,'BL','Belluno',1),(8012,8,'BN','Benevento',1),(8013,8,'BG','Bergamo',1),(8014,8,'BI','Biella',1),(8015,8,'BO','Bologna',1),(8016,8,'BZ','Bolzano',1),(8017,8,'BS','Brescia',1),(8018,8,'BR','Brindisi',1),(8019,8,'CA','Cagliari',1),(8020,8,'CL','Caltanissetta',1),(8021,8,'CB','Campobasso',1),(8022,8,'CI','Carbonia-Iglesias',1),(8023,8,'CE','Caserta',1),(8024,8,'CT','Catania',1),(8025,8,'CZ','Catanzaro',1),(8026,8,'CH','Chieti',1),(8027,8,'CO','Como',1),(8028,8,'CS','Cosenza',1),(8029,8,'CR','Cremona',1),(8030,8,'KR','Crotone',1),(8031,8,'CN','Cuneo',1),(8032,8,'EN','Enna',1),(8033,8,'FM','Fermo',1),(8034,8,'FE','Ferrara',1),(8035,8,'FI','Firenze',1),(8036,8,'FG','Foggia',1),(8037,8,'FC','Forli-Cesena',1),(8038,8,'FR','Frosinone',1),(8039,8,'GE','Genova',1),(8040,8,'GO','Gorizia',1),(8041,8,'GR','Grosseto',1),(8042,8,'IM','Imperia',1),(8043,8,'IS','Isernia',1),(8044,8,'AQ','L\'Aquila',1),(8045,8,'SP','La Spezia',1),(8046,8,'LT','Latina',1),(8047,8,'LE','Lecce',1),(8048,8,'LC','Lecco',1),(8049,8,'LI','Livorno',1),(8050,8,'LO','Lodi',1),(8051,8,'LU','Lucca',1),(8052,8,'MC','Macerata',1),(8053,8,'MN','Mantova',1),(8054,8,'MS','Massa-Carrara',1),(8055,8,'MT','Matera',1),(8056,8,'MA','Medio Campidano',1),(8057,8,'ME','Messina',1),(8058,8,'MI','Milano',1),(8059,8,'MO','Modena',1),(8060,8,'MZ','Monza',1),(8061,8,'NA','Napoli',1),(8062,8,'NO','Novara',1),(8063,8,'NU','Nuoro',1),(8064,8,'OG','Ogliastra',1),(8065,8,'OT','Olbia-Tempio',1),(8066,8,'OR','Oristano',1),(8067,8,'PD','Padova',1),(8068,8,'PA','Palermo',1),(8069,8,'PR','Parma',1),(8070,8,'PV','Pavia',1),(8071,8,'PG','Perugia',1),(8072,8,'PU','Pesaro e Urbino',1),(8073,8,'PE','Pescara',1),(8074,8,'PC','Piacenza',1),(8075,8,'PI','Pisa',1),(8076,8,'PT','Pistoia',1),(8077,8,'PN','Pordenone',1),(8078,8,'PZ','Potenza',1),(8079,8,'PO','Prato',1),(8080,8,'RG','Ragusa',1),(8081,8,'RA','Ravenna',1),(8082,8,'RC','Reggio Calabria',1),(8083,8,'RE','Reggio Emilia',1),(8084,8,'RI','Rieti',1),(8085,8,'RN','Rimini',1),(8086,8,'RM','Roma',1),(8087,8,'RO','Rovigo',1),(8088,8,'SA','Salerno',1),(8089,8,'SS','Sassari',1),(8090,8,'SV','Savona',1),(8091,8,'SI','Siena',1),(8092,8,'SR','Siracusa',1),(8093,8,'SO','Sondrio',1),(8094,8,'TA','Taranto',1),(8095,8,'TE','Teramo',1),(8096,8,'TR','Terni',1),(8097,8,'TO','Torino',1),(8098,8,'TP','Trapani',1),(8099,8,'TN','Trento',1),(8100,8,'TV','Treviso',1),(8101,8,'TS','Trieste',1),(8102,8,'UD','Udine',1),(8103,8,'VA','Varese',1),(8104,8,'VE','Venezia',1),(8105,8,'VB','Verbano-Cusio-Ossola',1),(8106,8,'VC','Vercelli',1),(8107,8,'VR','Verona',1),(8108,8,'VV','Vibo Valentia',1),(8109,8,'VI','Vicenza',1),(8110,8,'VT','Viterbo',1),(15001,15,'AN','Andalusia',1),(15002,15,'AR','Aragon',1),(15003,15,'AS','Asturias',1),(15004,15,'IB','Balearic Islands',1),(15005,15,'PV','Basque Country',1),(15006,15,'CN','Canary Islands',1),(15007,15,'CB','Cantabria',1),(15008,15,'CL','Castile and Leon',1),(15009,15,'CM','Castile-La Mancha',1),(15010,15,'CT','Catalonia',1),(15011,15,'CE','Ceuta',1),(15012,15,'EX','Extremadura',1),(15013,15,'GA','Galicia',1),(15014,15,'LO','La Rioja',1),(15015,15,'M','Madrid',1),(15016,15,'ML','Melilla',1),(15017,15,'MU','Murcia',1),(15018,15,'NA','Navarra',1),(15019,15,'VC','Valencia',1),(19001,19,'AL','Alabama',1),(19002,19,'AK','Alaska',1),(19003,19,'AZ','Arizona',1),(19004,19,'AR','Arkansas',1),(19005,19,'CA','California',1),(19006,19,'CO','Colorado',1),(19007,19,'CT','Connecticut',1),(19008,19,'DC','District of Columbia',1),(19009,19,'DE','Delaware',1),(19010,19,'FL','Florida',1),(19011,19,'GA','Georgia',1),(19012,19,'HI','Hawaii',1),(19013,19,'ID','Idaho',1),(19014,19,'IL','Illinois',1),(19015,19,'IN','Indiana',1),(19016,19,'IA','Iowa',1),(19017,19,'KS','Kansas',1),(19018,19,'KY','Kentucky ',1),(19019,19,'LA','Louisiana ',1),(19020,19,'ME','Maine',1),(19021,19,'MD','Maryland',1),(19022,19,'MA','Massachusetts',1),(19023,19,'MI','Michigan',1),(19024,19,'MN','Minnesota',1),(19025,19,'MS','Mississippi',1),(19026,19,'MO','Missouri',1),(19027,19,'MT','Montana',1),(19028,19,'NE','Nebraska',1),(19029,19,'NV','Nevada',1),(19030,19,'NH','New Hampshire',1),(19031,19,'NJ','New Jersey',1),(19032,19,'NM','New Mexico',1),(19033,19,'NY','New York',1),(19034,19,'NC','North Carolina',1),(19035,19,'ND','North Dakota',1),(19036,19,'OH','Ohio',1),(19037,19,'OK','Oklahoma ',1),(19038,19,'OR','Oregon',1),(19039,19,'PA','Pennsylvania',1),(19040,19,'PR','Puerto Rico',1),(19041,19,'RI','Rhode Island',1),(19042,19,'SC','South Carolina',1),(19043,19,'SD','South Dakota',1),(19044,19,'TN','Tennessee',1),(19045,19,'TX','Texas',1),(19046,19,'UT','Utah',1),(19047,19,'VT','Vermont',1),(19048,19,'VA','Virginia',1),(19049,19,'WA','Washington',1),(19050,19,'WV','West Virginia',1),(19051,19,'WI','Wisconsin',1),(19052,19,'WY','Wyoming',1),(32001,32,'ACT','Australian Capital Territory',1),(32002,32,'NSW','New South Wales',1),(32003,32,'NT','Northern Territory',1),(32004,32,'QLD','Queensland',1),(32005,32,'SA','South Australia',1),(32006,32,'TAS','Tasmania',1),(32007,32,'VIC','Victoria',1),(32008,32,'WA','Western Australia',1),(202001,202,'AG','Aargau',1),(202002,202,'AR','Appenzell Ausserrhoden',1),(202003,202,'AI','Appenzell Innerrhoden',1),(202004,202,'BL','Basel-Landschaft',1),(202005,202,'BS','Basel-Stadt',1),(202006,202,'BE','Bern',1),(202007,202,'FR','Fribourg',1),(202008,202,'GE','Geneva',1),(202009,202,'GL','Glarus',1),(202010,202,'GR','Graub√ºnden',1),(202011,202,'JU','Jura',1),(202012,202,'LU','Lucerne',1),(202013,202,'NE','Neuch√¢tel',1),(202014,202,'NW','Nidwalden',1),(202015,202,'OW','Obwalden',1),(202016,202,'SH','Schaffhausen',1),(202017,202,'SZ','Schwyz',1),(202018,202,'SO','Solothurn',1),(202019,202,'SG','St. Gallen',1),(202020,202,'TG','Thurgau',1),(202021,202,'TI','Ticino',1),(202022,202,'UR','Uri',1),(202023,202,'VS','Valais',1),(202024,202,'VD','Vaud',1),(202025,202,'ZG','Zug',1),(202026,202,'ZH','Z√ºrich',1);
/*!40000 ALTER TABLE `region` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release_`
--

DROP TABLE IF EXISTS `release_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `release_` (
  `releaseId` bigint(20) NOT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `servletContextName` varchar(75) DEFAULT NULL,
  `buildNumber` int(11) DEFAULT NULL,
  `buildDate` datetime DEFAULT NULL,
  `verified` tinyint(4) DEFAULT NULL,
  `testString` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`releaseId`),
  KEY `IX_8BD6BCA7` (`servletContextName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_`
--

LOCK TABLES `release_` WRITE;
/*!40000 ALTER TABLE `release_` DISABLE KEYS */;
INSERT INTO `release_` VALUES (1,'2010-06-08 02:10:41','2010-06-08 07:50:06','portal',6002,'2010-06-08 00:00:00',1,'You take the blue pill, the story ends, you wake up in your bed and believe whatever you want to believe. You take the red pill, you stay in Wonderland, and I show you how deep the rabbit hole goes.');
/*!40000 ALTER TABLE `release_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_`
--

DROP TABLE IF EXISTS `resource_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_` (
  `resourceId` bigint(20) NOT NULL,
  `codeId` bigint(20) DEFAULT NULL,
  `primKey` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resourceId`),
  UNIQUE KEY `IX_67DE7856` (`codeId`,`primKey`),
  KEY `IX_2578FBD3` (`codeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_`
--

LOCK TABLES `resource_` WRITE;
/*!40000 ALTER TABLE `resource_` DISABLE KEYS */;
/*!40000 ALTER TABLE `resource_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resourceaction`
--

DROP TABLE IF EXISTS `resourceaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resourceaction` (
  `resourceActionId` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actionId` varchar(75) DEFAULT NULL,
  `bitwiseValue` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`resourceActionId`),
  UNIQUE KEY `IX_EDB9986E` (`name`,`actionId`),
  KEY `IX_81F2DB09` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourceaction`
--

LOCK TABLES `resourceaction` WRITE;
/*!40000 ALTER TABLE `resourceaction` DISABLE KEYS */;
INSERT INTO `resourceaction` VALUES (1,'152','ACCESS_IN_CONTROL_PANEL',2),(2,'152','CONFIGURATION',4),(3,'152','VIEW',1),(4,'98','ACCESS_IN_CONTROL_PANEL',2),(5,'98','ADD_TO_PAGE',4),(6,'98','CONFIGURATION',8),(7,'98','VIEW',1),(8,'com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion','DELETE',2),(9,'com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion','PERMISSIONS',4),(10,'com.liferay.portlet.softwarecatalog.model.SCFrameworkVersion','UPDATE',8),(11,'com.liferay.portlet.softwarecatalog','ADD_FRAMEWORK_VERSION',2),(12,'com.liferay.portlet.softwarecatalog','ADD_PRODUCT_ENTRY',4),(13,'com.liferay.portlet.softwarecatalog.model.SCLicense','DELETE',2),(14,'com.liferay.portlet.softwarecatalog.model.SCLicense','PERMISSIONS',4),(15,'com.liferay.portlet.softwarecatalog.model.SCLicense','UPDATE',8),(16,'com.liferay.portlet.softwarecatalog.model.SCLicense','VIEW',1),(17,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','ADD_DISCUSSION',2),(18,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','DELETE',4),(19,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','DELETE_DISCUSSION',8),(20,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','PERMISSIONS',16),(21,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','UPDATE',32),(22,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','UPDATE_DISCUSSION',64),(23,'com.liferay.portlet.softwarecatalog.model.SCProductEntry','VIEW',1),(24,'27','VIEW',1),(25,'27','ADD_TO_PAGE',2),(26,'27','CONFIGURATION',4),(27,'66','VIEW',1),(28,'66','ADD_TO_PAGE',2),(29,'66','CONFIGURATION',4),(30,'88','VIEW',1),(31,'88','ADD_TO_PAGE',2),(32,'88','CONFIGURATION',4),(33,'87','VIEW',1),(34,'87','ADD_TO_PAGE',2),(35,'87','CONFIGURATION',4),(36,'134','ACCESS_IN_CONTROL_PANEL',2),(37,'134','CONFIGURATION',4),(38,'134','VIEW',1),(39,'com.liferay.portal.model.Layout','ADD_DISCUSSION',2),(40,'com.liferay.portal.model.Layout','DELETE',4),(41,'com.liferay.portal.model.Layout','DELETE_DISCUSSION',8),(42,'com.liferay.portal.model.Layout','UPDATE',16),(43,'com.liferay.portal.model.Layout','UPDATE_DISCUSSION',32),(44,'com.liferay.portal.model.Layout','VIEW',1),(45,'com.liferay.portal.model.LayoutSetPrototype','DELETE',2),(46,'com.liferay.portal.model.LayoutSetPrototype','PERMISSIONS',4),(47,'com.liferay.portal.model.LayoutSetPrototype','UPDATE',8),(48,'com.liferay.portal.model.LayoutSetPrototype','VIEW',1),(49,'com.liferay.portal.model.LayoutPrototype','DELETE',2),(50,'com.liferay.portal.model.LayoutPrototype','PERMISSIONS',4),(51,'com.liferay.portal.model.LayoutPrototype','UPDATE',8),(52,'com.liferay.portal.model.LayoutPrototype','VIEW',1),(53,'com.liferay.portal.model.Team','ASSIGN_MEMBERS',2),(54,'com.liferay.portal.model.Team','DELETE',4),(55,'com.liferay.portal.model.Team','PERMISSIONS',8),(56,'com.liferay.portal.model.Team','UPDATE',16),(57,'com.liferay.portal.model.Team','VIEW',1),(58,'com.liferay.portal.model.Group','APPROVE_PROPOSAL',2),(59,'com.liferay.portal.model.Group','ASSIGN_MEMBERS',4),(60,'com.liferay.portal.model.Group','ASSIGN_REVIEWER',8),(61,'com.liferay.portal.model.Group','ASSIGN_USER_ROLES',16),(62,'com.liferay.portal.model.Group','DELETE',32),(63,'com.liferay.portal.model.Group','MANAGE_ANNOUNCEMENTS',64),(64,'com.liferay.portal.model.Group','MANAGE_ARCHIVED_SETUPS',128),(65,'com.liferay.portal.model.Group','MANAGE_LAYOUTS',256),(66,'com.liferay.portal.model.Group','MANAGE_STAGING',512),(67,'com.liferay.portal.model.Group','MANAGE_TEAMS',1024),(68,'com.liferay.portal.model.Group','PERMISSIONS',2048),(69,'com.liferay.portal.model.Group','PUBLISH_STAGING',4096),(70,'com.liferay.portal.model.Group','PUBLISH_TO_REMOTE',8192),(71,'com.liferay.portal.model.Group','UPDATE',16384),(72,'com.liferay.portlet.tasks.model.TasksProposal','ADD_DISCUSSION',2),(73,'com.liferay.portlet.tasks.model.TasksProposal','DELETE',4),(74,'com.liferay.portlet.tasks.model.TasksProposal','DELETE_DISCUSSION',8),(75,'com.liferay.portlet.tasks.model.TasksProposal','UPDATE',16),(76,'com.liferay.portlet.tasks.model.TasksProposal','UPDATE_DISCUSSION',32),(77,'com.liferay.portlet.tasks.model.TasksProposal','VIEW',1),(78,'130','ACCESS_IN_CONTROL_PANEL',2),(79,'130','CONFIGURATION',4),(80,'130','VIEW',1),(81,'122','VIEW',1),(82,'122','ADD_TO_PAGE',2),(83,'122','CONFIGURATION',4),(84,'36','ADD_TO_PAGE',2),(85,'36','CONFIGURATION',4),(86,'36','VIEW',1),(87,'com.liferay.portlet.wiki.model.WikiPage','ADD_DISCUSSION',2),(88,'com.liferay.portlet.wiki.model.WikiPage','DELETE',4),(89,'com.liferay.portlet.wiki.model.WikiPage','DELETE_DISCUSSION',8),(90,'com.liferay.portlet.wiki.model.WikiPage','PERMISSIONS',16),(91,'com.liferay.portlet.wiki.model.WikiPage','SUBSCRIBE',32),(92,'com.liferay.portlet.wiki.model.WikiPage','UPDATE',64),(93,'com.liferay.portlet.wiki.model.WikiPage','UPDATE_DISCUSSION',128),(94,'com.liferay.portlet.wiki.model.WikiPage','VIEW',1),(95,'com.liferay.portlet.wiki.model.WikiNode','ADD_ATTACHMENT',2),(96,'com.liferay.portlet.wiki.model.WikiNode','ADD_PAGE',4),(97,'com.liferay.portlet.wiki.model.WikiNode','DELETE',8),(98,'com.liferay.portlet.wiki.model.WikiNode','IMPORT',16),(99,'com.liferay.portlet.wiki.model.WikiNode','PERMISSIONS',32),(100,'com.liferay.portlet.wiki.model.WikiNode','SUBSCRIBE',64),(101,'com.liferay.portlet.wiki.model.WikiNode','UPDATE',128),(102,'com.liferay.portlet.wiki.model.WikiNode','VIEW',1),(103,'com.liferay.portlet.wiki','ADD_NODE',2),(104,'com.liferay.portlet.wiki','PERMISSIONS',4),(105,'129','ACCESS_IN_CONTROL_PANEL',2),(106,'129','CONFIGURATION',4),(107,'129','VIEW',1),(108,'com.liferay.portal.model.PasswordPolicy','ASSIGN_MEMBERS',2),(109,'com.liferay.portal.model.PasswordPolicy','DELETE',4),(110,'com.liferay.portal.model.PasswordPolicy','PERMISSIONS',8),(111,'com.liferay.portal.model.PasswordPolicy','UPDATE',16),(112,'com.liferay.portal.model.PasswordPolicy','VIEW',1),(113,'26','VIEW',1),(114,'26','ADD_TO_PAGE',2),(115,'26','CONFIGURATION',4),(116,'104','VIEW',1),(117,'104','ADD_TO_PAGE',2),(118,'104','CONFIGURATION',4),(119,'100','VIEW',1),(120,'100','ADD_TO_PAGE',2),(121,'100','CONFIGURATION',4),(122,'153','ACCESS_IN_CONTROL_PANEL',2),(123,'153','CONFIGURATION',4),(124,'153','VIEW',1),(125,'64','VIEW',1),(126,'64','ADD_TO_PAGE',2),(127,'64','CONFIGURATION',4),(128,'19','ACCESS_IN_CONTROL_PANEL',2),(129,'19','ADD_TO_PAGE',4),(130,'19','CONFIGURATION',8),(131,'19','VIEW',1),(132,'com.liferay.portlet.messageboards.model.MBCategory','ADD_FILE',2),(133,'com.liferay.portlet.messageboards.model.MBCategory','ADD_MESSAGE',4),(134,'com.liferay.portlet.messageboards.model.MBCategory','ADD_SUBCATEGORY',8),(135,'com.liferay.portlet.messageboards.model.MBCategory','DELETE',16),(136,'com.liferay.portlet.messageboards.model.MBCategory','LOCK_THREAD',32),(137,'com.liferay.portlet.messageboards.model.MBCategory','MOVE_THREAD',64),(138,'com.liferay.portlet.messageboards.model.MBCategory','PERMISSIONS',128),(139,'com.liferay.portlet.messageboards.model.MBCategory','REPLY_TO_MESSAGE',256),(140,'com.liferay.portlet.messageboards.model.MBCategory','SUBSCRIBE',512),(141,'com.liferay.portlet.messageboards.model.MBCategory','UPDATE',1024),(142,'com.liferay.portlet.messageboards.model.MBCategory','UPDATE_THREAD_PRIORITY',2048),(143,'com.liferay.portlet.messageboards.model.MBCategory','VIEW',1),(144,'com.liferay.portlet.messageboards','ADD_CATEGORY',2),(145,'com.liferay.portlet.messageboards','ADD_FILE',4),(146,'com.liferay.portlet.messageboards','ADD_MESSAGE',8),(147,'com.liferay.portlet.messageboards','BAN_USER',16),(148,'com.liferay.portlet.messageboards','MOVE_THREAD',32),(149,'com.liferay.portlet.messageboards','LOCK_THREAD',64),(150,'com.liferay.portlet.messageboards','PERMISSIONS',128),(151,'com.liferay.portlet.messageboards','REPLY_TO_MESSAGE',256),(152,'com.liferay.portlet.messageboards','SUBSCRIBE',512),(153,'com.liferay.portlet.messageboards','UPDATE_THREAD_PRIORITY',1024),(154,'com.liferay.portlet.messageboards.model.MBMessage','DELETE',2),(155,'com.liferay.portlet.messageboards.model.MBMessage','PERMISSIONS',4),(156,'com.liferay.portlet.messageboards.model.MBMessage','SUBSCRIBE',8),(157,'com.liferay.portlet.messageboards.model.MBMessage','UPDATE',16),(158,'com.liferay.portlet.messageboards.model.MBMessage','VIEW',1),(159,'128','ACCESS_IN_CONTROL_PANEL',2),(160,'128','CONFIGURATION',4),(161,'128','VIEW',1),(162,'com.liferay.portal.model.Role','ASSIGN_MEMBERS',2),(163,'com.liferay.portal.model.Role','DEFINE_PERMISSIONS',4),(164,'com.liferay.portal.model.Role','DELETE',8),(165,'com.liferay.portal.model.Role','MANAGE_ANNOUNCEMENTS',16),(166,'com.liferay.portal.model.Role','PERMISSIONS',32),(167,'com.liferay.portal.model.Role','UPDATE',64),(168,'com.liferay.portal.model.Role','VIEW',1),(169,'86','VIEW',1),(170,'86','ADD_TO_PAGE',2),(171,'86','CONFIGURATION',4),(172,'120','VIEW',1),(173,'120','ADD_TO_PAGE',2),(174,'120','CONFIGURATION',4),(175,'29','ADD_TO_PAGE',2),(176,'29','CONFIGURATION',4),(177,'29','VIEW',1),(178,'154','ACCESS_IN_CONTROL_PANEL',2),(179,'154','CONFIGURATION',4),(180,'154','VIEW',1),(181,'148','VIEW',1),(182,'148','ADD_TO_PAGE',2),(183,'148','CONFIGURATION',4),(184,'124','VIEW',1),(185,'124','ADD_TO_PAGE',2),(186,'124','CONFIGURATION',4),(187,'11','ADD_TO_PAGE',2),(188,'11','CONFIGURATION',4),(189,'11','VIEW',1),(190,'8','ACCESS_IN_CONTROL_PANEL',2),(191,'8','ADD_TO_PAGE',4),(192,'8','CONFIGURATION',8),(193,'8','VIEW',1),(194,'com.liferay.portlet.calendar','ADD_EVENT',2),(195,'com.liferay.portlet.calendar','EXPORT_ALL_EVENTS',4),(196,'com.liferay.portlet.calendar','PERMISSIONS',8),(197,'com.liferay.portlet.calendar.model.CalEvent','ADD_DISCUSSION',2),(198,'com.liferay.portlet.calendar.model.CalEvent','DELETE',4),(199,'com.liferay.portlet.calendar.model.CalEvent','DELETE_DISCUSSION',8),(200,'com.liferay.portlet.calendar.model.CalEvent','PERMISSIONS',16),(201,'com.liferay.portlet.calendar.model.CalEvent','UPDATE',32),(202,'com.liferay.portlet.calendar.model.CalEvent','UPDATE_DISCUSSION',64),(203,'com.liferay.portlet.calendar.model.CalEvent','VIEW',1),(204,'58','ADD_TO_PAGE',2),(205,'58','CONFIGURATION',4),(206,'58','VIEW',1),(207,'155','ACCESS_IN_CONTROL_PANEL',2),(208,'155','VIEW',1),(209,'155','CONFIGURATION',4),(210,'97','VIEW',1),(211,'97','ADD_TO_PAGE',2),(212,'97','CONFIGURATION',4),(213,'71','ADD_TO_PAGE',2),(214,'71','CONFIGURATION',4),(215,'71','VIEW',1),(216,'39','VIEW',1),(217,'39','ADD_TO_PAGE',2),(218,'39','CONFIGURATION',4),(219,'85','ADD_TO_PAGE',2),(220,'85','CONFIGURATION',4),(221,'85','VIEW',1),(222,'118','VIEW',1),(223,'118','ADD_TO_PAGE',2),(224,'118','CONFIGURATION',4),(225,'107','VIEW',1),(226,'107','ADD_TO_PAGE',2),(227,'107','CONFIGURATION',4),(228,'79','CONFIGURATION',2),(229,'79','VIEW',1),(230,'79','ADD_TO_PAGE',4),(231,'30','VIEW',1),(232,'30','ADD_TO_PAGE',2),(233,'30','CONFIGURATION',4),(234,'147','ACCESS_IN_CONTROL_PANEL',2),(235,'147','CONFIGURATION',4),(236,'147','VIEW',1),(237,'48','VIEW',1),(238,'48','ADD_TO_PAGE',2),(239,'48','CONFIGURATION',4),(240,'125','ACCESS_IN_CONTROL_PANEL',2),(241,'125','CONFIGURATION',4),(242,'125','EXPORT_USER',8),(243,'125','VIEW',1),(244,'com.liferay.portal.model.User','DELETE',2),(245,'com.liferay.portal.model.User','IMPERSONATE',4),(246,'com.liferay.portal.model.User','PERMISSIONS',8),(247,'com.liferay.portal.model.User','UPDATE',16),(248,'com.liferay.portal.model.User','VIEW',1),(249,'144','VIEW',1),(250,'144','ADD_TO_PAGE',2),(251,'144','CONFIGURATION',4),(252,'146','ACCESS_IN_CONTROL_PANEL',2),(253,'146','CONFIGURATION',4),(254,'146','VIEW',1),(255,'62','VIEW',1),(256,'62','ADD_TO_PAGE',2),(257,'62','CONFIGURATION',4),(258,'108','VIEW',1),(259,'108','ADD_TO_PAGE',2),(260,'108','CONFIGURATION',4),(261,'139','ADD_EXPANDO',2),(262,'139','CONFIGURATION',4),(263,'139','VIEW',1),(264,'139','ADD_TO_PAGE',8),(265,'com.liferay.portlet.expando.model.ExpandoColumn','DELETE',2),(266,'com.liferay.portlet.expando.model.ExpandoColumn','PERMISSIONS',4),(267,'com.liferay.portlet.expando.model.ExpandoColumn','UPDATE',8),(268,'com.liferay.portlet.expando.model.ExpandoColumn','VIEW',1),(269,'84','ADD_ENTRY',2),(270,'84','ADD_TO_PAGE',4),(271,'84','CONFIGURATION',8),(272,'84','VIEW',1),(273,'com.liferay.portlet.announcements.model.AnnouncementsEntry','DELETE',2),(274,'com.liferay.portlet.announcements.model.AnnouncementsEntry','UPDATE',4),(275,'com.liferay.portlet.announcements.model.AnnouncementsEntry','VIEW',1),(276,'101','VIEW',1),(277,'101','ADD_TO_PAGE',2),(278,'101','CONFIGURATION',4),(279,'121','VIEW',1),(280,'121','ADD_TO_PAGE',2),(281,'121','CONFIGURATION',4),(282,'49','VIEW',1),(283,'49','ADD_TO_PAGE',2),(284,'49','CONFIGURATION',4),(285,'143','VIEW',1),(286,'143','ADD_TO_PAGE',2),(287,'143','CONFIGURATION',4),(288,'37','VIEW',1),(289,'37','ADD_TO_PAGE',2),(290,'37','CONFIGURATION',4),(291,'77','VIEW',1),(292,'77','ADD_TO_PAGE',2),(293,'77','CONFIGURATION',4),(294,'115','VIEW',1),(295,'115','ADD_TO_PAGE',2),(296,'115','CONFIGURATION',4),(297,'6','VIEW',1),(298,'6','ADD_TO_PAGE',2),(299,'6','CONFIGURATION',4),(300,'56','ADD_TO_PAGE',2),(301,'56','CONFIGURATION',4),(302,'56','VIEW',1),(303,'142','VIEW',1),(304,'142','ADD_TO_PAGE',2),(305,'142','CONFIGURATION',4),(306,'111','VIEW',1),(307,'111','ADD_TO_PAGE',2),(308,'111','CONFIGURATION',4),(309,'16','PREFERENCES',2),(310,'16','GUEST_PREFERENCES',4),(311,'16','VIEW',1),(312,'16','ADD_TO_PAGE',8),(313,'16','CONFIGURATION',16),(314,'3','VIEW',1),(315,'3','ADD_TO_PAGE',2),(316,'3','CONFIGURATION',4),(317,'20','ACCESS_IN_CONTROL_PANEL',2),(318,'20','ADD_TO_PAGE',4),(319,'20','CONFIGURATION',8),(320,'20','VIEW',1),(321,'com.liferay.portlet.documentlibrary.model.DLFolder','ACCESS',2),(322,'com.liferay.portlet.documentlibrary.model.DLFolder','ADD_DOCUMENT',4),(323,'com.liferay.portlet.documentlibrary.model.DLFolder','ADD_SHORTCUT',8),(324,'com.liferay.portlet.documentlibrary.model.DLFolder','ADD_SUBFOLDER',16),(325,'com.liferay.portlet.documentlibrary.model.DLFolder','DELETE',32),(326,'com.liferay.portlet.documentlibrary.model.DLFolder','PERMISSIONS',64),(327,'com.liferay.portlet.documentlibrary.model.DLFolder','UPDATE',128),(328,'com.liferay.portlet.documentlibrary.model.DLFolder','VIEW',1),(329,'com.liferay.portlet.documentlibrary','ADD_DOCUMENT',2),(330,'com.liferay.portlet.documentlibrary','ADD_FOLDER',4),(331,'com.liferay.portlet.documentlibrary','ADD_SHORTCUT',8),(332,'com.liferay.portlet.documentlibrary','PERMISSIONS',16),(333,'com.liferay.portlet.documentlibrary','VIEW',1),(334,'com.liferay.portlet.documentlibrary.model.DLFileEntry','ADD_DISCUSSION',2),(335,'com.liferay.portlet.documentlibrary.model.DLFileEntry','DELETE',4),(336,'com.liferay.portlet.documentlibrary.model.DLFileEntry','DELETE_DISCUSSION',8),(337,'com.liferay.portlet.documentlibrary.model.DLFileEntry','PERMISSIONS',16),(338,'com.liferay.portlet.documentlibrary.model.DLFileEntry','UPDATE',32),(339,'com.liferay.portlet.documentlibrary.model.DLFileEntry','UPDATE_DISCUSSION',64),(340,'com.liferay.portlet.documentlibrary.model.DLFileEntry','VIEW',1),(341,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','ADD_DISCUSSION',2),(342,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','DELETE',4),(343,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','DELETE_DISCUSSION',8),(344,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','PERMISSIONS',16),(345,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','UPDATE',32),(346,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','UPDATE_DISCUSSION',64),(347,'com.liferay.portlet.documentlibrary.model.DLFileShortcut','VIEW',1),(348,'23','VIEW',1),(349,'23','ADD_TO_PAGE',2),(350,'23','CONFIGURATION',4),(351,'145','VIEW',1),(352,'145','ADD_TO_PAGE',2),(353,'145','CONFIGURATION',4),(354,'com.liferay.portlet.asset','ADD_CATEGORY',2),(355,'com.liferay.portlet.asset','ADD_VOCABULARY',4),(356,'com.liferay.portlet.asset','ADD_TAG',8),(357,'com.liferay.portlet.asset.model.AssetCategory','ADD_CATEGORY',2),(358,'com.liferay.portlet.asset.model.AssetCategory','DELETE',4),(359,'com.liferay.portlet.asset.model.AssetCategory','PERMISSIONS',8),(360,'com.liferay.portlet.asset.model.AssetCategory','UPDATE',16),(361,'com.liferay.portlet.asset.model.AssetCategory','VIEW',1),(362,'com.liferay.portlet.asset.model.AssetVocabulary','DELETE',2),(363,'com.liferay.portlet.asset.model.AssetVocabulary','PERMISSIONS',4),(364,'com.liferay.portlet.asset.model.AssetVocabulary','UPDATE',8),(365,'com.liferay.portlet.asset.model.AssetVocabulary','VIEW',1),(366,'83','ADD_ENTRY',2),(367,'83','ADD_TO_PAGE',4),(368,'83','CONFIGURATION',8),(369,'83','VIEW',1),(370,'99','VIEW',1),(371,'99','ADD_TO_PAGE',2),(372,'99','CONFIGURATION',4),(373,'com.liferay.portlet.asset.model.AssetTag','DELETE',2),(374,'com.liferay.portlet.asset.model.AssetTag','PERMISSIONS',4),(375,'com.liferay.portlet.asset.model.AssetTag','UPDATE',8),(376,'com.liferay.portlet.asset.model.AssetTag','VIEW',1),(377,'70','VIEW',1),(378,'70','ADD_TO_PAGE',2),(379,'70','CONFIGURATION',4),(380,'141','VIEW',1),(381,'141','ADD_TO_PAGE',2),(382,'141','CONFIGURATION',4),(383,'9','VIEW',1),(384,'9','ADD_TO_PAGE',2),(385,'9','CONFIGURATION',4),(386,'137','ACCESS_IN_CONTROL_PANEL',2),(387,'137','CONFIGURATION',4),(388,'137','VIEW',1),(389,'28','ACCESS_IN_CONTROL_PANEL',2),(390,'28','ADD_TO_PAGE',4),(391,'28','CONFIGURATION',8),(392,'28','VIEW',1),(393,'com.liferay.portlet.bookmarks.model.BookmarksFolder','ACCESS',2),(394,'com.liferay.portlet.bookmarks.model.BookmarksFolder','ADD_ENTRY',4),(395,'com.liferay.portlet.bookmarks.model.BookmarksFolder','ADD_SUBFOLDER',8),(396,'com.liferay.portlet.bookmarks.model.BookmarksFolder','DELETE',16),(397,'com.liferay.portlet.bookmarks.model.BookmarksFolder','PERMISSIONS',32),(398,'com.liferay.portlet.bookmarks.model.BookmarksFolder','UPDATE',64),(399,'com.liferay.portlet.bookmarks.model.BookmarksFolder','VIEW',1),(400,'com.liferay.portlet.bookmarks','ADD_ENTRY',2),(401,'com.liferay.portlet.bookmarks','ADD_FOLDER',4),(402,'com.liferay.portlet.bookmarks','PERMISSIONS',8),(403,'com.liferay.portlet.bookmarks.model.BookmarksEntry','DELETE',2),(404,'com.liferay.portlet.bookmarks.model.BookmarksEntry','PERMISSIONS',4),(405,'com.liferay.portlet.bookmarks.model.BookmarksEntry','UPDATE',8),(406,'com.liferay.portlet.bookmarks.model.BookmarksEntry','VIEW',1),(407,'133','VIEW',1),(408,'133','ADD_TO_PAGE',2),(409,'133','CONFIGURATION',4),(410,'116','VIEW',1),(411,'116','ADD_TO_PAGE',2),(412,'116','CONFIGURATION',4),(413,'15','ACCESS_IN_CONTROL_PANEL',2),(414,'15','ADD_TO_PAGE',4),(415,'15','CONFIGURATION',8),(416,'15','VIEW',1),(417,'com.liferay.portlet.journal.model.JournalFeed','DELETE',2),(418,'com.liferay.portlet.journal.model.JournalFeed','PERMISSIONS',4),(419,'com.liferay.portlet.journal.model.JournalFeed','UPDATE',8),(420,'com.liferay.portlet.journal.model.JournalFeed','VIEW',1),(421,'com.liferay.portlet.journal.model.JournalArticle','ADD_DISCUSSION',2),(422,'com.liferay.portlet.journal.model.JournalArticle','DELETE',4),(423,'com.liferay.portlet.journal.model.JournalArticle','DELETE_DISCUSSION',8),(424,'com.liferay.portlet.journal.model.JournalArticle','EXPIRE',16),(425,'com.liferay.portlet.journal.model.JournalArticle','PERMISSIONS',32),(426,'com.liferay.portlet.journal.model.JournalArticle','UPDATE',64),(427,'com.liferay.portlet.journal.model.JournalArticle','UPDATE_DISCUSSION',128),(428,'com.liferay.portlet.journal.model.JournalArticle','VIEW',1),(429,'com.liferay.portlet.journal','ADD_ARTICLE',2),(430,'com.liferay.portlet.journal','ADD_FEED',4),(431,'com.liferay.portlet.journal','ADD_STRUCTURE',8),(432,'com.liferay.portlet.journal','ADD_TEMPLATE',16),(433,'com.liferay.portlet.journal','APPROVE_ARTICLE',32),(434,'com.liferay.portlet.journal','SUBSCRIBE',64),(435,'com.liferay.portlet.journal.model.JournalStructure','DELETE',2),(436,'com.liferay.portlet.journal.model.JournalStructure','PERMISSIONS',4),(437,'com.liferay.portlet.journal.model.JournalStructure','UPDATE',8),(438,'com.liferay.portlet.journal.model.JournalStructure','VIEW',1),(439,'com.liferay.portlet.journal.model.JournalTemplate','DELETE',2),(440,'com.liferay.portlet.journal.model.JournalTemplate','PERMISSIONS',4),(441,'com.liferay.portlet.journal.model.JournalTemplate','UPDATE',8),(442,'com.liferay.portlet.journal.model.JournalTemplate','VIEW',1),(443,'47','VIEW',1),(444,'47','ADD_TO_PAGE',2),(445,'47','CONFIGURATION',4),(446,'82','VIEW',1),(447,'82','ADD_TO_PAGE',2),(448,'82','CONFIGURATION',4),(449,'103','VIEW',1),(450,'103','ADD_TO_PAGE',2),(451,'103','CONFIGURATION',4),(452,'151','ACCESS_IN_CONTROL_PANEL',2),(453,'151','CONFIGURATION',4),(454,'151','VIEW',1),(455,'140','VIEW',1),(456,'140','ADD_TO_PAGE',2),(457,'140','CONFIGURATION',4),(458,'54','VIEW',1),(459,'54','ADD_TO_PAGE',2),(460,'54','CONFIGURATION',4),(461,'132','ACCESS_IN_CONTROL_PANEL',2),(462,'132','CONFIGURATION',4),(463,'132','VIEW',1),(464,'34','ADD_TO_PAGE',2),(465,'34','CONFIGURATION',4),(466,'34','VIEW',1),(467,'com.liferay.portlet.shopping','ADD_CATEGORY',2),(468,'com.liferay.portlet.shopping','ADD_ITEM',4),(469,'com.liferay.portlet.shopping','MANAGE_COUPONS',8),(470,'com.liferay.portlet.shopping','MANAGE_ORDERS',16),(471,'com.liferay.portlet.shopping','PERMISSIONS',32),(472,'com.liferay.portlet.shopping.model.ShoppingCategory','ADD_ITEM',2),(473,'com.liferay.portlet.shopping.model.ShoppingCategory','ADD_SUBCATEGORY',4),(474,'com.liferay.portlet.shopping.model.ShoppingCategory','DELETE',8),(475,'com.liferay.portlet.shopping.model.ShoppingCategory','PERMISSIONS',16),(476,'com.liferay.portlet.shopping.model.ShoppingCategory','UPDATE',32),(477,'com.liferay.portlet.shopping.model.ShoppingCategory','VIEW',1),(478,'com.liferay.portlet.shopping.model.ShoppingOrder','DELETE',2),(479,'com.liferay.portlet.shopping.model.ShoppingOrder','PERMISSIONS',4),(480,'com.liferay.portlet.shopping.model.ShoppingOrder','UPDATE',8),(481,'com.liferay.portlet.shopping.model.ShoppingOrder','VIEW',1),(482,'com.liferay.portlet.shopping.model.ShoppingItem','DELETE',2),(483,'com.liferay.portlet.shopping.model.ShoppingItem','PERMISSIONS',4),(484,'com.liferay.portlet.shopping.model.ShoppingItem','UPDATE',8),(485,'com.liferay.portlet.shopping.model.ShoppingItem','VIEW',1),(486,'61','VIEW',1),(487,'61','ADD_TO_PAGE',2),(488,'61','CONFIGURATION',4),(489,'73','ADD_TO_PAGE',2),(490,'73','CONFIGURATION',4),(491,'73','VIEW',1),(492,'31','ACCESS_IN_CONTROL_PANEL',2),(493,'31','ADD_TO_PAGE',4),(494,'31','CONFIGURATION',8),(495,'31','VIEW',1),(496,'com.liferay.portlet.imagegallery.model.IGImage','DELETE',2),(497,'com.liferay.portlet.imagegallery.model.IGImage','PERMISSIONS',4),(498,'com.liferay.portlet.imagegallery.model.IGImage','UPDATE',8),(499,'com.liferay.portlet.imagegallery.model.IGImage','VIEW',1),(500,'com.liferay.portlet.imagegallery.model.IGFolder','ACCESS',2),(501,'com.liferay.portlet.imagegallery.model.IGFolder','ADD_IMAGE',4),(502,'com.liferay.portlet.imagegallery.model.IGFolder','ADD_SUBFOLDER',8),(503,'com.liferay.portlet.imagegallery.model.IGFolder','DELETE',16),(504,'com.liferay.portlet.imagegallery.model.IGFolder','PERMISSIONS',32),(505,'com.liferay.portlet.imagegallery.model.IGFolder','UPDATE',64),(506,'com.liferay.portlet.imagegallery.model.IGFolder','VIEW',1),(507,'com.liferay.portlet.imagegallery','ADD_FOLDER',2),(508,'com.liferay.portlet.imagegallery','ADD_IMAGE',4),(509,'com.liferay.portlet.imagegallery','PERMISSIONS',8),(510,'com.liferay.portlet.imagegallery','VIEW',1),(511,'136','ACCESS_IN_CONTROL_PANEL',2),(512,'136','CONFIGURATION',4),(513,'136','VIEW',1),(514,'127','ACCESS_IN_CONTROL_PANEL',2),(515,'127','CONFIGURATION',4),(516,'127','VIEW',1),(517,'com.liferay.portal.model.UserGroup','ASSIGN_MEMBERS',2),(518,'com.liferay.portal.model.UserGroup','DELETE',4),(519,'com.liferay.portal.model.UserGroup','MANAGE_ANNOUNCEMENTS',8),(520,'com.liferay.portal.model.UserGroup','PERMISSIONS',16),(521,'com.liferay.portal.model.UserGroup','MANAGE_LAYOUTS',32),(522,'com.liferay.portal.model.UserGroup','UPDATE',64),(523,'com.liferay.portal.model.UserGroup','VIEW',1),(524,'50','VIEW',1),(525,'50','ADD_TO_PAGE',2),(526,'50','CONFIGURATION',4),(527,'25','ACCESS_IN_CONTROL_PANEL',2),(528,'25','CONFIGURATION',4),(529,'25','VIEW',1),(530,'com.liferay.portlet.polls','ADD_QUESTION',2),(531,'com.liferay.portlet.polls.model.PollsQuestion','ADD_VOTE',2),(532,'com.liferay.portlet.polls.model.PollsQuestion','DELETE',4),(533,'com.liferay.portlet.polls.model.PollsQuestion','PERMISSIONS',8),(534,'com.liferay.portlet.polls.model.PollsQuestion','UPDATE',16),(535,'com.liferay.portlet.polls.model.PollsQuestion','VIEW',1),(536,'24','VIEW',1),(537,'24','ADD_TO_PAGE',2),(538,'24','CONFIGURATION',4),(539,'90','ADD_COMMUNITY',2),(540,'90','ADD_LAYOUT_PROTOTYPE',4),(541,'90','ADD_LAYOUT_SET_PROTOTYPE',8),(542,'90','ADD_LICENSE',16),(543,'90','ADD_ORGANIZATION',32),(544,'90','ADD_PASSWORD_POLICY',64),(545,'90','ADD_ROLE',128),(546,'90','ADD_USER',256),(547,'90','ADD_USER_GROUP',512),(548,'150','ACCESS_IN_CONTROL_PANEL',2),(549,'150','CONFIGURATION',4),(550,'150','VIEW',1),(551,'33','ACCESS_IN_CONTROL_PANEL',2),(552,'33','ADD_TO_PAGE',4),(553,'33','CONFIGURATION',8),(554,'33','VIEW',1),(555,'com.liferay.portlet.blogs','ADD_ENTRY',2),(556,'com.liferay.portlet.blogs','PERMISSIONS',4),(557,'com.liferay.portlet.blogs','SUBSCRIBE',8),(558,'com.liferay.portlet.blogs.model.BlogsEntry','ADD_DISCUSSION',2),(559,'com.liferay.portlet.blogs.model.BlogsEntry','DELETE',4),(560,'com.liferay.portlet.blogs.model.BlogsEntry','DELETE_DISCUSSION',8),(561,'com.liferay.portlet.blogs.model.BlogsEntry','PERMISSIONS',16),(562,'com.liferay.portlet.blogs.model.BlogsEntry','UPDATE',32),(563,'com.liferay.portlet.blogs.model.BlogsEntry','UPDATE_DISCUSSION',64),(564,'com.liferay.portlet.blogs.model.BlogsEntry','VIEW',1),(565,'113','VIEW',1),(566,'113','ADD_TO_PAGE',2),(567,'113','CONFIGURATION',4),(568,'2','VIEW',1),(569,'2','ADD_TO_PAGE',2),(570,'2','CONFIGURATION',4),(571,'119','VIEW',1),(572,'119','ADD_TO_PAGE',2),(573,'119','CONFIGURATION',4),(574,'126','ACCESS_IN_CONTROL_PANEL',2),(575,'126','CONFIGURATION',4),(576,'126','VIEW',1),(577,'com.liferay.portal.model.Organization','APPROVE_PROPOSAL',2),(578,'com.liferay.portal.model.Organization','ASSIGN_MEMBERS',4),(579,'com.liferay.portal.model.Organization','ASSIGN_REVIEWER',8),(580,'com.liferay.portal.model.Organization','ASSIGN_USER_ROLES',16),(581,'com.liferay.portal.model.Organization','DELETE',32),(582,'com.liferay.portal.model.Organization','MANAGE_ANNOUNCEMENTS',64),(583,'com.liferay.portal.model.Organization','MANAGE_ARCHIVED_SETUPS',128),(584,'com.liferay.portal.model.Organization','MANAGE_LAYOUTS',256),(585,'com.liferay.portal.model.Organization','MANAGE_STAGING',512),(586,'com.liferay.portal.model.Organization','MANAGE_SUBORGANIZATIONS',1024),(587,'com.liferay.portal.model.Organization','MANAGE_TEAMS',2048),(588,'com.liferay.portal.model.Organization','MANAGE_USERS',4096),(589,'com.liferay.portal.model.Organization','PERMISSIONS',8192),(590,'com.liferay.portal.model.Organization','PUBLISH_STAGING',16384),(591,'com.liferay.portal.model.Organization','UPDATE',32768),(592,'com.liferay.portal.model.Organization','VIEW',1),(593,'114','VIEW',1),(594,'114','ADD_TO_PAGE',2),(595,'114','CONFIGURATION',4),(596,'149','ACCESS_IN_CONTROL_PANEL',2),(597,'149','CONFIGURATION',4),(598,'149','VIEW',1),(599,'67','VIEW',1),(600,'67','ADD_TO_PAGE',2),(601,'67','CONFIGURATION',4),(602,'110','VIEW',1),(603,'110','ADD_TO_PAGE',2),(604,'110','CONFIGURATION',4),(605,'135','ACCESS_IN_CONTROL_PANEL',2),(606,'135','CONFIGURATION',4),(607,'135','VIEW',1),(608,'59','VIEW',1),(609,'59','ADD_TO_PAGE',2),(610,'59','CONFIGURATION',4),(611,'131','ACCESS_IN_CONTROL_PANEL',2),(612,'131','CONFIGURATION',4),(613,'131','VIEW',1),(614,'102','VIEW',1),(615,'102','ADD_TO_PAGE',2),(616,'102','CONFIGURATION',4);
/*!40000 ALTER TABLE `resourceaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resourcecode`
--

DROP TABLE IF EXISTS `resourcecode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resourcecode` (
  `codeId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  PRIMARY KEY (`codeId`),
  UNIQUE KEY `IX_A32C097E` (`companyId`,`name`,`scope`),
  KEY `IX_717FDD47` (`companyId`),
  KEY `IX_AACAFF40` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourcecode`
--

LOCK TABLES `resourcecode` WRITE;
/*!40000 ALTER TABLE `resourcecode` DISABLE KEYS */;
/*!40000 ALTER TABLE `resourcecode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resourcepermission`
--

DROP TABLE IF EXISTS `resourcepermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resourcepermission` (
  `resourcePermissionId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  `primKey` varchar(255) DEFAULT NULL,
  `roleId` bigint(20) DEFAULT NULL,
  `actionIds` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`resourcePermissionId`),
  UNIQUE KEY `IX_8D83D0CE` (`companyId`,`name`,`scope`,`primKey`,`roleId`),
  KEY `IX_60B99860` (`companyId`,`name`,`scope`),
  KEY `IX_2200AA69` (`companyId`,`name`,`scope`,`primKey`),
  KEY `IX_A37A0588` (`roleId`),
  KEY `IX_2F80C17C` (`roleId`,`scope`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resourcepermission`
--

LOCK TABLES `resourcepermission` WRITE;
/*!40000 ALTER TABLE `resourcepermission` DISABLE KEYS */;
INSERT INTO `resourcepermission` VALUES (1,10130,'com.liferay.portal.model.Layout',4,'10150',10138,63),(2,10130,'com.liferay.portal.model.Layout',4,'10150',10142,3),(3,10130,'com.liferay.portal.model.Layout',4,'10150',10137,1),(4,10130,'com.liferay.portal.model.Layout',4,'10157',10138,63),(5,10130,'com.liferay.portal.model.Layout',4,'10157',10142,3),(6,10130,'com.liferay.portal.model.Layout',4,'10157',10137,1),(7,10130,'com.liferay.portal.model.User',4,'10165',10138,31),(8,10130,'27',1,'10130',10139,2),(9,10130,'27',1,'10130',10140,2),(10,10130,'98',1,'10130',10139,4),(11,10130,'98',1,'10130',10140,4),(12,10130,'66',1,'10130',10139,2),(13,10130,'66',1,'10130',10140,2),(14,10130,'122',1,'10130',10137,2),(15,10130,'122',1,'10130',10139,2),(16,10130,'122',1,'10130',10140,2),(17,10130,'36',1,'10130',10139,2),(18,10130,'36',1,'10130',10140,2),(19,10130,'26',1,'10130',10139,2),(20,10130,'26',1,'10130',10140,2),(21,10130,'104',1,'10130',10136,2),(22,10130,'100',1,'10130',10139,2),(23,10130,'100',1,'10130',10140,2),(24,10130,'19',1,'10130',10139,4),(25,10130,'19',1,'10130',10140,4),(26,10130,'64',1,'10130',10137,2),(27,10130,'64',1,'10130',10139,2),(28,10130,'64',1,'10130',10140,2),(29,10130,'29',1,'10130',10139,2),(30,10130,'29',1,'10130',10140,2),(31,10130,'148',1,'10130',10137,2),(32,10130,'148',1,'10130',10139,2),(33,10130,'148',1,'10130',10140,2),(34,10130,'8',1,'10130',10139,4),(35,10130,'8',1,'10130',10140,4),(36,10130,'11',1,'10130',10139,2),(37,10130,'11',1,'10130',10140,2),(38,10130,'58',1,'10130',10137,2),(39,10130,'58',1,'10130',10139,2),(40,10130,'58',1,'10130',10140,2),(41,10130,'71',1,'10130',10137,2),(42,10130,'71',1,'10130',10139,2),(43,10130,'71',1,'10130',10140,2),(44,10130,'97',1,'10130',10139,2),(45,10130,'97',1,'10130',10140,2),(46,10130,'39',1,'10130',10139,2),(47,10130,'39',1,'10130',10140,2),(48,10130,'85',1,'10130',10137,2),(49,10130,'85',1,'10130',10139,2),(50,10130,'85',1,'10130',10140,2),(51,10130,'118',1,'10130',10137,2),(52,10130,'118',1,'10130',10139,2),(53,10130,'118',1,'10130',10140,2),(54,10130,'79',1,'10130',10136,4),(55,10130,'107',1,'10130',10139,2),(56,10130,'107',1,'10130',10140,2),(57,10130,'30',1,'10130',10139,2),(58,10130,'30',1,'10130',10140,2),(59,10130,'48',1,'10130',10139,2),(60,10130,'48',1,'10130',10140,2),(61,10130,'62',1,'10130',10139,2),(62,10130,'62',1,'10130',10140,2),(63,10130,'108',1,'10130',10139,2),(64,10130,'108',1,'10130',10140,2),(65,10130,'84',1,'10130',10139,4),(66,10130,'84',1,'10130',10140,4),(67,10130,'101',1,'10130',10137,2),(68,10130,'101',1,'10130',10139,2),(69,10130,'101',1,'10130',10140,2),(70,10130,'121',1,'10130',10137,2),(71,10130,'121',1,'10130',10139,2),(72,10130,'121',1,'10130',10140,2),(73,10130,'37',1,'10130',10139,2),(74,10130,'37',1,'10130',10140,2),(75,10130,'143',1,'10130',10137,2),(76,10130,'143',1,'10130',10139,2),(77,10130,'143',1,'10130',10140,2),(78,10130,'77',1,'10130',10137,2),(79,10130,'77',1,'10130',10139,2),(80,10130,'77',1,'10130',10140,2),(81,10130,'6',1,'10130',10139,2),(82,10130,'6',1,'10130',10140,2),(83,10130,'115',1,'10130',10137,2),(84,10130,'115',1,'10130',10139,2),(85,10130,'115',1,'10130',10140,2),(86,10130,'56',1,'10130',10137,2),(87,10130,'56',1,'10130',10139,2),(88,10130,'56',1,'10130',10140,2),(89,10130,'16',1,'10130',10139,8),(90,10130,'16',1,'10130',10140,8),(91,10130,'111',1,'10130',10136,2),(92,10130,'3',1,'10130',10137,2),(93,10130,'3',1,'10130',10139,2),(94,10130,'3',1,'10130',10140,2),(95,10130,'23',1,'10130',10139,2),(96,10130,'23',1,'10130',10140,2),(97,10130,'20',1,'10130',10137,4),(98,10130,'20',1,'10130',10139,4),(99,10130,'20',1,'10130',10140,4),(100,10130,'83',1,'10130',10139,4),(101,10130,'83',1,'10130',10140,4),(102,10130,'99',1,'10130',10136,2),(103,10130,'70',1,'10130',10139,2),(104,10130,'70',1,'10130',10140,2),(105,10130,'141',1,'10130',10137,2),(106,10130,'141',1,'10130',10139,2),(107,10130,'141',1,'10130',10140,2),(108,10130,'9',1,'10130',10136,2),(109,10130,'28',1,'10130',10139,4),(110,10130,'28',1,'10130',10140,4),(111,10130,'47',1,'10130',10137,2),(112,10130,'47',1,'10130',10139,2),(113,10130,'47',1,'10130',10140,2),(114,10130,'15',1,'10130',10139,4),(115,10130,'15',1,'10130',10140,4),(116,10130,'116',1,'10130',10137,2),(117,10130,'116',1,'10130',10139,2),(118,10130,'116',1,'10130',10140,2),(119,10130,'82',1,'10130',10137,2),(120,10130,'82',1,'10130',10139,2),(121,10130,'82',1,'10130',10140,2),(122,10130,'54',1,'10130',10139,2),(123,10130,'54',1,'10130',10140,2),(124,10130,'34',1,'10130',10139,2),(125,10130,'34',1,'10130',10140,2),(126,10130,'61',1,'10130',10139,2),(127,10130,'61',1,'10130',10140,2),(128,10130,'73',1,'10130',10137,2),(129,10130,'73',1,'10130',10139,2),(130,10130,'73',1,'10130',10140,2),(131,10130,'31',1,'10130',10137,4),(132,10130,'31',1,'10130',10139,4),(133,10130,'31',1,'10130',10140,4),(134,10130,'50',1,'10130',10137,2),(135,10130,'50',1,'10130',10139,2),(136,10130,'50',1,'10130',10140,2),(137,10130,'24',1,'10130',10139,2),(138,10130,'24',1,'10130',10140,2),(139,10130,'33',1,'10130',10137,4),(140,10130,'33',1,'10130',10139,4),(141,10130,'33',1,'10130',10140,4),(142,10130,'114',1,'10130',10137,2),(143,10130,'114',1,'10130',10139,2),(144,10130,'114',1,'10130',10140,2),(145,10130,'67',1,'10130',10139,2),(146,10130,'67',1,'10130',10140,2),(147,10130,'110',1,'10130',10139,2),(148,10130,'110',1,'10130',10140,2),(149,10130,'59',1,'10130',10139,2),(150,10130,'59',1,'10130',10140,2),(151,10130,'102',1,'10130',10137,2),(152,10130,'102',1,'10130',10139,2),(153,10130,'102',1,'10130',10140,2),(154,10130,'com.liferay.portal.model.Role',4,'10274',10138,127),(155,10130,'com.liferay.portal.model.Role',4,'10276',10138,127),(156,10130,'103',4,'10157_LAYOUT_103',10138,7),(157,10130,'103',4,'10157_LAYOUT_103',10142,1),(158,10130,'103',4,'10157_LAYOUT_103',10137,1),(159,10130,'47',4,'10157_LAYOUT_47',10138,7),(160,10130,'47',4,'10157_LAYOUT_47',10142,1),(161,10130,'47',4,'10157_LAYOUT_47',10137,1),(162,10130,'58',4,'10157_LAYOUT_58',10138,7),(163,10130,'58',4,'10157_LAYOUT_58',10142,1),(164,10130,'58',4,'10157_LAYOUT_58',10137,1),(165,10130,'com.liferay.portal.model.Layout',4,'10286',10138,63),(166,10130,'com.liferay.portal.model.Layout',4,'10286',10139,3),(167,10130,'com.liferay.portal.model.Layout',4,'10286',10137,1),(168,10130,'com.liferay.portal.model.Layout',4,'10290',10138,63),(169,10130,'com.liferay.portal.model.Layout',4,'10290',10139,3),(170,10130,'com.liferay.portal.model.Layout',4,'10290',10137,1),(171,10130,'145',4,'10157_LAYOUT_145',10138,7),(172,10130,'145',4,'10157_LAYOUT_145',10142,1),(173,10130,'145',4,'10157_LAYOUT_145',10137,1),(174,10130,'87',4,'10150_LAYOUT_87',10138,7),(175,10130,'87',4,'10150_LAYOUT_87',10142,1),(176,10130,'87',4,'10150_LAYOUT_87',10137,1),(177,10130,'145',4,'10150_LAYOUT_145',10138,7),(178,10130,'145',4,'10150_LAYOUT_145',10142,1),(179,10130,'145',4,'10150_LAYOUT_145',10137,1),(180,10130,'125',4,'10150_LAYOUT_125',10138,15),(181,10130,'125',4,'10150_LAYOUT_125',10142,1),(182,10130,'125',4,'10150_LAYOUT_125',10137,1),(183,10130,'com.liferay.portal.model.User',4,'10302',10138,31),(184,10130,'com.liferay.portal.model.User',4,'10311',10138,31),(2001,10130,'com.liferay.portal.model.Layout',4,'12002',10138,63),(2002,10130,'com.liferay.portal.model.Layout',4,'12002',10142,3),(2003,10130,'com.liferay.portal.model.Layout',4,'12002',10137,1),(2004,10130,'com.liferay.portal.model.Layout',4,'12006',10138,63),(2005,10130,'com.liferay.portal.model.Layout',4,'12006',10142,3),(2006,10130,'com.liferay.portal.model.Layout',4,'12006',10137,1),(2007,10130,'103',4,'12002_LAYOUT_103',10138,7),(2008,10130,'103',4,'12002_LAYOUT_103',10142,1),(2009,10130,'103',4,'12002_LAYOUT_103',10137,1),(2010,10130,'145',4,'12002_LAYOUT_145',10138,7),(2011,10130,'145',4,'12002_LAYOUT_145',10142,1),(2012,10130,'145',4,'12002_LAYOUT_145',10137,1),(2013,10130,'87',4,'12002_LAYOUT_87',10138,7),(2014,10130,'87',4,'12002_LAYOUT_87',10142,1),(2015,10130,'87',4,'12002_LAYOUT_87',10137,1),(2016,10130,'33',4,'12002_LAYOUT_33',10138,15),(2017,10130,'33',4,'12002_LAYOUT_33',10142,1),(2018,10130,'33',4,'12002_LAYOUT_33',10137,1),(2019,10130,'103',4,'12006_LAYOUT_103',10138,7),(2020,10130,'103',4,'12006_LAYOUT_103',10142,1),(2021,10130,'103',4,'12006_LAYOUT_103',10137,1),(2022,10130,'145',4,'12006_LAYOUT_145',10138,7),(2023,10130,'145',4,'12006_LAYOUT_145',10142,1),(2024,10130,'145',4,'12006_LAYOUT_145',10137,1),(2025,10130,'87',4,'12006_LAYOUT_87',10138,7),(2026,10130,'87',4,'12006_LAYOUT_87',10142,1),(2027,10130,'87',4,'12006_LAYOUT_87',10137,1),(2028,10130,'36',4,'12006_LAYOUT_36',10138,7),(2029,10130,'36',4,'12006_LAYOUT_36',10142,1),(2030,10130,'36',4,'12006_LAYOUT_36',10137,1),(2031,10130,'com.liferay.portlet.wiki.model.WikiNode',4,'12018',10138,255),(2032,10130,'com.liferay.portlet.wiki.model.WikiNode',4,'12018',10142,71),(2033,10130,'com.liferay.portlet.wiki.model.WikiNode',4,'12018',10137,1),(2034,10130,'com.liferay.portlet.wiki.model.WikiPage',4,'12020',10138,255),(2035,10130,'com.liferay.portlet.wiki.model.WikiPage',4,'12020',10142,99),(2036,10130,'com.liferay.portlet.wiki.model.WikiPage',4,'12020',10137,1),(2037,10130,'com.liferay.portlet.asset.model.AssetVocabulary',4,'12025',10138,15),(2038,10130,'com.liferay.portlet.asset.model.AssetVocabulary',4,'12025',10142,0),(2039,10130,'com.liferay.portlet.asset.model.AssetVocabulary',4,'12025',10137,0),(2040,10130,'128',4,'10150_LAYOUT_128',10138,7),(2041,10130,'128',4,'10150_LAYOUT_128',10142,1),(2042,10130,'128',4,'10150_LAYOUT_128',10137,1);
/*!40000 ALTER TABLE `resourcepermission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_`
--

DROP TABLE IF EXISTS `role_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_` (
  `roleId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `title` longtext,
  `description` longtext,
  `type_` int(11) DEFAULT NULL,
  `subtype` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `IX_A88E424E` (`companyId`,`classNameId`,`classPK`),
  UNIQUE KEY `IX_EBC931B8` (`companyId`,`name`),
  KEY `IX_449A10B9` (`companyId`),
  KEY `IX_5EB4E2FB` (`subtype`),
  KEY `IX_CBE204` (`type_`,`subtype`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_`
--

LOCK TABLES `role_` WRITE;
/*!40000 ALTER TABLE `role_` DISABLE KEYS */;
INSERT INTO `role_` VALUES (10136,10130,10039,10136,'Administrator','','Administrators are super users who can do anything.',1,''),(10137,10130,10039,10137,'Guest','','Unauthenticated users always have this role.',1,''),(10138,10130,10039,10138,'Owner','','This is an implied role with respect to the objects users create.',1,''),(10139,10130,10039,10139,'Power User','','Power Users have their own public and private pages.',1,''),(10140,10130,10039,10140,'User','','Authenticated users should be assigned this role.',1,''),(10141,10130,10039,10141,'Community Administrator','','Community Administrators are super users of their community but cannot make other users into Community Administrators.',2,''),(10142,10130,10039,10142,'Community Member','','All users who belong to a community have this role within that community.',2,''),(10143,10130,10039,10143,'Community Owner','','Community Owners are super users of their community and can assign community roles to users.',2,''),(10144,10130,10039,10144,'Organization Administrator','','Organization Administrators are super users of their organization but cannot make other users into Organization Administrators.',3,''),(10145,10130,10039,10145,'Organization Member','','All users who belong to an organization have this role within that organization.',3,''),(10146,10130,10039,10146,'Organization Owner','','Organization Owners are super users of their organization and can assign organization roles to users.',3,''),(10274,10130,10039,10274,'Community Content Reviewer','','Autogenerated role from workflow definition',2,''),(10276,10130,10039,10276,'Organization Content Reviewer','','Autogenerated role from workflow definition',3,'');
/*!40000 ALTER TABLE `role_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_permissions`
--

DROP TABLE IF EXISTS `roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles_permissions` (
  `roleId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`),
  KEY `IX_7A3619C6` (`permissionId`),
  KEY `IX_E04E486D` (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_permissions`
--

LOCK TABLES `roles_permissions` WRITE;
/*!40000 ALTER TABLE `roles_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scframeworkversi_scproductvers`
--

DROP TABLE IF EXISTS `scframeworkversi_scproductvers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scframeworkversi_scproductvers` (
  `frameworkVersionId` bigint(20) NOT NULL,
  `productVersionId` bigint(20) NOT NULL,
  PRIMARY KEY (`frameworkVersionId`,`productVersionId`),
  KEY `IX_3BB93ECA` (`frameworkVersionId`),
  KEY `IX_E8D33FF9` (`productVersionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scframeworkversi_scproductvers`
--

LOCK TABLES `scframeworkversi_scproductvers` WRITE;
/*!40000 ALTER TABLE `scframeworkversi_scproductvers` DISABLE KEYS */;
/*!40000 ALTER TABLE `scframeworkversi_scproductvers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scframeworkversion`
--

DROP TABLE IF EXISTS `scframeworkversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scframeworkversion` (
  `frameworkVersionId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `url` longtext,
  `active_` tinyint(4) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`frameworkVersionId`),
  KEY `IX_C98C0D78` (`companyId`),
  KEY `IX_272991FA` (`groupId`),
  KEY `IX_6E1764F` (`groupId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scframeworkversion`
--

LOCK TABLES `scframeworkversion` WRITE;
/*!40000 ALTER TABLE `scframeworkversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `scframeworkversion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sclicense`
--

DROP TABLE IF EXISTS `sclicense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sclicense` (
  `licenseId` bigint(20) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `url` longtext,
  `openSource` tinyint(4) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  `recommended` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`licenseId`),
  KEY `IX_1C841592` (`active_`),
  KEY `IX_5327BB79` (`active_`,`recommended`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sclicense`
--

LOCK TABLES `sclicense` WRITE;
/*!40000 ALTER TABLE `sclicense` DISABLE KEYS */;
/*!40000 ALTER TABLE `sclicense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sclicenses_scproductentries`
--

DROP TABLE IF EXISTS `sclicenses_scproductentries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sclicenses_scproductentries` (
  `licenseId` bigint(20) NOT NULL,
  `productEntryId` bigint(20) NOT NULL,
  PRIMARY KEY (`licenseId`,`productEntryId`),
  KEY `IX_27006638` (`licenseId`),
  KEY `IX_D7710A66` (`productEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sclicenses_scproductentries`
--

LOCK TABLES `sclicenses_scproductentries` WRITE;
/*!40000 ALTER TABLE `sclicenses_scproductentries` DISABLE KEYS */;
/*!40000 ALTER TABLE `sclicenses_scproductentries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scproductentry`
--

DROP TABLE IF EXISTS `scproductentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scproductentry` (
  `productEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `tags` varchar(255) DEFAULT NULL,
  `shortDescription` longtext,
  `longDescription` longtext,
  `pageURL` longtext,
  `author` varchar(75) DEFAULT NULL,
  `repoGroupId` varchar(75) DEFAULT NULL,
  `repoArtifactId` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`productEntryId`),
  KEY `IX_5D25244F` (`companyId`),
  KEY `IX_72F87291` (`groupId`),
  KEY `IX_98E6A9CB` (`groupId`,`userId`),
  KEY `IX_7311E812` (`repoGroupId`,`repoArtifactId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scproductentry`
--

LOCK TABLES `scproductentry` WRITE;
/*!40000 ALTER TABLE `scproductentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `scproductentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scproductscreenshot`
--

DROP TABLE IF EXISTS `scproductscreenshot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scproductscreenshot` (
  `productScreenshotId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `productEntryId` bigint(20) DEFAULT NULL,
  `thumbnailId` bigint(20) DEFAULT NULL,
  `fullImageId` bigint(20) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  PRIMARY KEY (`productScreenshotId`),
  KEY `IX_AE8224CC` (`fullImageId`),
  KEY `IX_467956FD` (`productEntryId`),
  KEY `IX_DA913A55` (`productEntryId`,`priority`),
  KEY `IX_6C572DAC` (`thumbnailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scproductscreenshot`
--

LOCK TABLES `scproductscreenshot` WRITE;
/*!40000 ALTER TABLE `scproductscreenshot` DISABLE KEYS */;
/*!40000 ALTER TABLE `scproductscreenshot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scproductversion`
--

DROP TABLE IF EXISTS `scproductversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scproductversion` (
  `productVersionId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `productEntryId` bigint(20) DEFAULT NULL,
  `version` varchar(75) DEFAULT NULL,
  `changeLog` longtext,
  `downloadPageURL` longtext,
  `directDownloadURL` varchar(2000) DEFAULT NULL,
  `repoStoreArtifact` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`productVersionId`),
  KEY `IX_7020130F` (`directDownloadURL`(255)),
  KEY `IX_8377A211` (`productEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scproductversion`
--

LOCK TABLES `scproductversion` WRITE;
/*!40000 ALTER TABLE `scproductversion` DISABLE KEYS */;
/*!40000 ALTER TABLE `scproductversion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicecomponent`
--

DROP TABLE IF EXISTS `servicecomponent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servicecomponent` (
  `serviceComponentId` bigint(20) NOT NULL,
  `buildNamespace` varchar(75) DEFAULT NULL,
  `buildNumber` bigint(20) DEFAULT NULL,
  `buildDate` bigint(20) DEFAULT NULL,
  `data_` longtext,
  PRIMARY KEY (`serviceComponentId`),
  UNIQUE KEY `IX_4F0315B8` (`buildNamespace`,`buildNumber`),
  KEY `IX_7338606F` (`buildNamespace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicecomponent`
--

LOCK TABLES `servicecomponent` WRITE;
/*!40000 ALTER TABLE `servicecomponent` DISABLE KEYS */;
INSERT INTO `servicecomponent` VALUES (10268,'Kaleo',1,1275404009873,'<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<data>\n	<tables-sql><![CDATA[create table Kaleo_KaleoAction (\n	kaleoActionId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNodeId LONG,\n	kaleoNodeName VARCHAR(200) null,\n	name VARCHAR(200) null,\n	description VARCHAR(2000) null,\n	executionType VARCHAR(20) null,\n	script TEXT null,\n	scriptLanguage VARCHAR(75) null,\n	priority INTEGER\n);\n\ncreate table Kaleo_KaleoDefinition (\n	kaleoDefinitionId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	name VARCHAR(200) null,\n	title STRING null,\n	description VARCHAR(2000) null,\n	version INTEGER,\n	active_ BOOLEAN,\n	startKaleoNodeId LONG\n);\n\ncreate table Kaleo_KaleoInstance (\n	kaleoInstanceId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoDefinitionName VARCHAR(200) null,\n	kaleoDefinitionVersion INTEGER,\n	rootKaleoInstanceTokenId LONG,\n	className VARCHAR(200) null,\n	classPK LONG,\n	completed BOOLEAN,\n	completionDate DATE null,\n	workflowContext TEXT null\n);\n\ncreate table Kaleo_KaleoInstanceToken (\n	kaleoInstanceTokenId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoInstanceId LONG,\n	parentKaleoInstanceTokenId LONG,\n	currentKaleoNodeId LONG,\n	currentKaleoNodeName VARCHAR(200) null,\n	completed BOOLEAN,\n	completionDate DATE null\n);\n\ncreate table Kaleo_KaleoLog (\n	kaleoLogId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoInstanceId LONG,\n	kaleoInstanceTokenId LONG,\n	kaleoTaskInstanceTokenId LONG,\n	kaleoNodeId LONG,\n	kaleoNodeName VARCHAR(200) null,\n	terminalKaleoNode BOOLEAN,\n	kaleoActionId LONG,\n	kaleoActionName VARCHAR(200) null,\n	kaleoActionDescription VARCHAR(2000) null,\n	previousKaleoNodeId LONG,\n	previousKaleoNodeName VARCHAR(200) null,\n	previousAssigneeClassName VARCHAR(200) null,\n	previousAssigneeClassPK LONG,\n	currentAssigneeClassName VARCHAR(200) null,\n	currentAssigneeClassPK LONG,\n	type_ VARCHAR(50) null,\n	comment VARCHAR(2000) null,\n	startDate DATE null,\n	endDate DATE null,\n	duration LONG,\n	workflowContext TEXT null\n);\n\ncreate table Kaleo_KaleoNode (\n	kaleoNodeId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	name VARCHAR(200) null,\n	description VARCHAR(2000) null,\n	type_ VARCHAR(20) null,\n	initial BOOLEAN,\n	terminal BOOLEAN\n);\n\ncreate table Kaleo_KaleoNotification (\n	kaleoNotificationId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNodeId LONG,\n	kaleoNodeName VARCHAR(200) null,\n	name VARCHAR(200) null,\n	description VARCHAR(2000) null,\n	executionType VARCHAR(20) null,\n	template TEXT null,\n	templateLanguage VARCHAR(75) null,\n	notificationTypes VARCHAR(25) null\n);\n\ncreate table Kaleo_KaleoNotificationRecipient (\n	kaleoNotificationRecipientId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(75) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNotificationId LONG,\n	recipientClassName VARCHAR(75) null,\n	recipientClassPK LONG,\n	recipientRoleType INTEGER,\n	address VARCHAR(75) null\n);\n\ncreate table Kaleo_KaleoTask (\n	kaleoTaskId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNodeId LONG,\n	name VARCHAR(75) null,\n	description VARCHAR(75) null,\n	dueDateDuration DOUBLE,\n	dueDateScale VARCHAR(75) null\n);\n\ncreate table Kaleo_KaleoTaskAssignment (\n	kaleoTaskAssignmentId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNodeId LONG,\n	kaleoTaskId LONG,\n	assigneeClassName VARCHAR(200) null,\n	assigneeClassPK LONG,\n	defaultAssignment BOOLEAN\n);\n\ncreate table Kaleo_KaleoTaskInstanceToken (\n	kaleoTaskInstanceTokenId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoInstanceId LONG,\n	kaleoInstanceTokenId LONG,\n	kaleoTaskId LONG,\n	kaleoTaskName VARCHAR(200) null,\n	assigneeClassName VARCHAR(200) null,\n	assigneeClassPK LONG,\n	completionUserId LONG,\n	completed BOOLEAN,\n	completionDate DATE null,\n	dueDate DATE null,\n	workflowContext TEXT null\n);\n\ncreate table Kaleo_KaleoTransition (\n	kaleoTransitionId LONG not null primary key,\n	groupId LONG,\n	companyId LONG,\n	userId LONG,\n	userName VARCHAR(200) null,\n	createDate DATE null,\n	modifiedDate DATE null,\n	kaleoDefinitionId LONG,\n	kaleoNodeId LONG,\n	name VARCHAR(200) null,\n	description VARCHAR(2000) null,\n	sourceKaleoNodeId LONG,\n	sourceKaleoNodeName VARCHAR(200) null,\n	targetKaleoNodeId LONG,\n	targetKaleoNodeName VARCHAR(200) null,\n	defaultTransition BOOLEAN\n);]]></tables-sql>\n	<sequences-sql><![CDATA[]]></sequences-sql>\n	<indexes-sql><![CDATA[create index IX_D764E9A1 on Kaleo_KaleoAction (kaleoDefinitionId);\ncreate index IX_B2A93512 on Kaleo_KaleoAction (kaleoNodeId, executionType);\n\ncreate index IX_2B7BEC2E on Kaleo_KaleoDefinition (companyId);\ncreate index IX_2F7F569B on Kaleo_KaleoDefinition (companyId, active_);\ncreate index IX_87D3E16D on Kaleo_KaleoDefinition (companyId, name);\ncreate index IX_2C5DD83C on Kaleo_KaleoDefinition (companyId, name, active_);\ncreate index IX_CC4EDF3B on Kaleo_KaleoDefinition (companyId, name, version);\n\ncreate index IX_EC4CB399 on Kaleo_KaleoInstance (companyId, kaleoDefinitionName, kaleoDefinitionVersion, completionDate);\ncreate index IX_5EB130E2 on Kaleo_KaleoInstance (kaleoDefinitionId);\ncreate index IX_664E0519 on Kaleo_KaleoInstance (kaleoDefinitionId, completed);\n\ncreate index IX_45F8175C on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId);\ncreate index IX_D5BA57FA on Kaleo_KaleoInstanceToken (companyId, parentKaleoInstanceTokenId, completionDate);\ncreate index IX_949FA455 on Kaleo_KaleoInstanceToken (kaleoDefinitionId);\ncreate index IX_E324C3D7 on Kaleo_KaleoInstanceToken (kaleoInstanceId);\n\ncreate index IX_D9FD3CB5 on Kaleo_KaleoLog (kaleoDefinitionId);\ncreate index IX_31CCF437 on Kaleo_KaleoLog (kaleoInstanceId);\ncreate index IX_37870D24 on Kaleo_KaleoLog (kaleoInstanceTokenId, kaleoNodeId, type_);\ncreate index IX_5FD03F99 on Kaleo_KaleoLog (kaleoInstanceTokenId, type_);\ncreate index IX_B0413637 on Kaleo_KaleoLog (kaleoTaskInstanceTokenId);\n\ncreate index IX_ECA77F9F on Kaleo_KaleoNode (companyId, kaleoDefinitionId);\ncreate index IX_78616515 on Kaleo_KaleoNode (kaleoDefinitionId);\n\ncreate index IX_3BDEF6CC on Kaleo_KaleoNotification (kaleoDefinitionId);\ncreate index IX_7A83C847 on Kaleo_KaleoNotification (kaleoNodeId, executionType);\n\ncreate index IX_7F26068B on Kaleo_KaleoNotificationRecipient (kaleoDefinitionId);\ncreate index IX_21EE3763 on Kaleo_KaleoNotificationRecipient (kaleoNotificationId);\n\ncreate index IX_4977BD72 on Kaleo_KaleoTask (kaleoDefinitionId);\ncreate index IX_61E22421 on Kaleo_KaleoTask (kaleoNodeId);\n\ncreate index IX_BCE28DE5 on Kaleo_KaleoTaskAssignment (assigneeClassName, kaleoTaskId);\ncreate index IX_572B5825 on Kaleo_KaleoTaskAssignment (kaleoDefinitionId);\ncreate index IX_5EDD8F17 on Kaleo_KaleoTaskAssignment (kaleoTaskId);\ncreate index IX_787D75A7 on Kaleo_KaleoTaskAssignment (kaleoTaskId, defaultAssignment);\n\ncreate index IX_A9FF7F44 on Kaleo_KaleoTaskInstanceToken (companyId);\ncreate index IX_672441C9 on Kaleo_KaleoTaskInstanceToken (groupId, companyId, assigneeClassName, assigneeClassPK, completed);\ncreate index IX_40C87C3A on Kaleo_KaleoTaskInstanceToken (kaleoDefinitionId);\ncreate index IX_26FC50FC on Kaleo_KaleoTaskInstanceToken (kaleoInstanceId);\n\ncreate index IX_47129C62 on Kaleo_KaleoTransition (kaleoDefinitionId);\ncreate index IX_E90AF711 on Kaleo_KaleoTransition (kaleoNodeId);\ncreate index IX_462C6BF5 on Kaleo_KaleoTransition (kaleoNodeId, defaultTransition);\ncreate index IX_8499F610 on Kaleo_KaleoTransition (kaleoNodeId, name);]]></indexes-sql>\n</data>');
/*!40000 ALTER TABLE `servicecomponent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shard`
--

DROP TABLE IF EXISTS `shard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shard` (
  `shardId` bigint(20) NOT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`shardId`),
  KEY `IX_DA5F4359` (`classNameId`,`classPK`),
  KEY `IX_941BA8C3` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shard`
--

LOCK TABLES `shard` WRITE;
/*!40000 ALTER TABLE `shard` DISABLE KEYS */;
INSERT INTO `shard` VALUES (10131,10007,10130,'default');
/*!40000 ALTER TABLE `shard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcart`
--

DROP TABLE IF EXISTS `shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcart` (
  `cartId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `itemIds` longtext,
  `couponCodes` varchar(75) DEFAULT NULL,
  `altShipping` int(11) DEFAULT NULL,
  `insure` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`cartId`),
  UNIQUE KEY `IX_FC46FE16` (`groupId`,`userId`),
  KEY `IX_C28B41DC` (`groupId`),
  KEY `IX_54101CC8` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcart`
--

LOCK TABLES `shoppingcart` WRITE;
/*!40000 ALTER TABLE `shoppingcart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcategory`
--

DROP TABLE IF EXISTS `shoppingcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcategory` (
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentCategoryId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`categoryId`),
  KEY `IX_5F615D3E` (`groupId`),
  KEY `IX_1E6464F5` (`groupId`,`parentCategoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcategory`
--

LOCK TABLES `shoppingcategory` WRITE;
/*!40000 ALTER TABLE `shoppingcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingcoupon`
--

DROP TABLE IF EXISTS `shoppingcoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingcoupon` (
  `couponId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `code_` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  `limitCategories` longtext,
  `limitSkus` longtext,
  `minOrder` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `discountType` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`couponId`),
  UNIQUE KEY `IX_DC60CFAE` (`code_`),
  KEY `IX_3251AF16` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingcoupon`
--

LOCK TABLES `shoppingcoupon` WRITE;
/*!40000 ALTER TABLE `shoppingcoupon` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingcoupon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingitem`
--

DROP TABLE IF EXISTS `shoppingitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingitem` (
  `itemId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `sku` varchar(75) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` longtext,
  `properties` longtext,
  `fields_` tinyint(4) DEFAULT NULL,
  `fieldsQuantities` longtext,
  `minQuantity` int(11) DEFAULT NULL,
  `maxQuantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `taxable` tinyint(4) DEFAULT NULL,
  `shipping` double DEFAULT NULL,
  `useShippingFormula` tinyint(4) DEFAULT NULL,
  `requiresShipping` tinyint(4) DEFAULT NULL,
  `stockQuantity` int(11) DEFAULT NULL,
  `featured_` tinyint(4) DEFAULT NULL,
  `sale_` tinyint(4) DEFAULT NULL,
  `smallImage` tinyint(4) DEFAULT NULL,
  `smallImageId` bigint(20) DEFAULT NULL,
  `smallImageURL` longtext,
  `mediumImage` tinyint(4) DEFAULT NULL,
  `mediumImageId` bigint(20) DEFAULT NULL,
  `mediumImageURL` longtext,
  `largeImage` tinyint(4) DEFAULT NULL,
  `largeImageId` bigint(20) DEFAULT NULL,
  `largeImageURL` longtext,
  PRIMARY KEY (`itemId`),
  UNIQUE KEY `IX_1C717CA6` (`companyId`,`sku`),
  KEY `IX_FEFE7D76` (`groupId`,`categoryId`),
  KEY `IX_903DC750` (`largeImageId`),
  KEY `IX_D217AB30` (`mediumImageId`),
  KEY `IX_FF203304` (`smallImageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingitem`
--

LOCK TABLES `shoppingitem` WRITE;
/*!40000 ALTER TABLE `shoppingitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingitemfield`
--

DROP TABLE IF EXISTS `shoppingitemfield`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingitemfield` (
  `itemFieldId` bigint(20) NOT NULL,
  `itemId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `values_` longtext,
  `description` longtext,
  PRIMARY KEY (`itemFieldId`),
  KEY `IX_6D5F9B87` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingitemfield`
--

LOCK TABLES `shoppingitemfield` WRITE;
/*!40000 ALTER TABLE `shoppingitemfield` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingitemfield` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingitemprice`
--

DROP TABLE IF EXISTS `shoppingitemprice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingitemprice` (
  `itemPriceId` bigint(20) NOT NULL,
  `itemId` bigint(20) DEFAULT NULL,
  `minQuantity` int(11) DEFAULT NULL,
  `maxQuantity` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `taxable` tinyint(4) DEFAULT NULL,
  `shipping` double DEFAULT NULL,
  `useShippingFormula` tinyint(4) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`itemPriceId`),
  KEY `IX_EA6FD516` (`itemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingitemprice`
--

LOCK TABLES `shoppingitemprice` WRITE;
/*!40000 ALTER TABLE `shoppingitemprice` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingitemprice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingorder`
--

DROP TABLE IF EXISTS `shoppingorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingorder` (
  `orderId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `number_` varchar(75) DEFAULT NULL,
  `tax` double DEFAULT NULL,
  `shipping` double DEFAULT NULL,
  `altShipping` varchar(75) DEFAULT NULL,
  `requiresShipping` tinyint(4) DEFAULT NULL,
  `insure` tinyint(4) DEFAULT NULL,
  `insurance` double DEFAULT NULL,
  `couponCodes` varchar(75) DEFAULT NULL,
  `couponDiscount` double DEFAULT NULL,
  `billingFirstName` varchar(75) DEFAULT NULL,
  `billingLastName` varchar(75) DEFAULT NULL,
  `billingEmailAddress` varchar(75) DEFAULT NULL,
  `billingCompany` varchar(75) DEFAULT NULL,
  `billingStreet` varchar(75) DEFAULT NULL,
  `billingCity` varchar(75) DEFAULT NULL,
  `billingState` varchar(75) DEFAULT NULL,
  `billingZip` varchar(75) DEFAULT NULL,
  `billingCountry` varchar(75) DEFAULT NULL,
  `billingPhone` varchar(75) DEFAULT NULL,
  `shipToBilling` tinyint(4) DEFAULT NULL,
  `shippingFirstName` varchar(75) DEFAULT NULL,
  `shippingLastName` varchar(75) DEFAULT NULL,
  `shippingEmailAddress` varchar(75) DEFAULT NULL,
  `shippingCompany` varchar(75) DEFAULT NULL,
  `shippingStreet` varchar(75) DEFAULT NULL,
  `shippingCity` varchar(75) DEFAULT NULL,
  `shippingState` varchar(75) DEFAULT NULL,
  `shippingZip` varchar(75) DEFAULT NULL,
  `shippingCountry` varchar(75) DEFAULT NULL,
  `shippingPhone` varchar(75) DEFAULT NULL,
  `ccName` varchar(75) DEFAULT NULL,
  `ccType` varchar(75) DEFAULT NULL,
  `ccNumber` varchar(75) DEFAULT NULL,
  `ccExpMonth` int(11) DEFAULT NULL,
  `ccExpYear` int(11) DEFAULT NULL,
  `ccVerNumber` varchar(75) DEFAULT NULL,
  `comments` longtext,
  `ppTxnId` varchar(75) DEFAULT NULL,
  `ppPaymentStatus` varchar(75) DEFAULT NULL,
  `ppPaymentGross` double DEFAULT NULL,
  `ppReceiverEmail` varchar(75) DEFAULT NULL,
  `ppPayerEmail` varchar(75) DEFAULT NULL,
  `sendOrderEmail` tinyint(4) DEFAULT NULL,
  `sendShippingEmail` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  UNIQUE KEY `IX_D7D6E87A` (`number_`),
  KEY `IX_1D15553E` (`groupId`),
  KEY `IX_119B5630` (`groupId`,`userId`,`ppPaymentStatus`),
  KEY `IX_F474FD89` (`ppTxnId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingorder`
--

LOCK TABLES `shoppingorder` WRITE;
/*!40000 ALTER TABLE `shoppingorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shoppingorderitem`
--

DROP TABLE IF EXISTS `shoppingorderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shoppingorderitem` (
  `orderItemId` bigint(20) NOT NULL,
  `orderId` bigint(20) DEFAULT NULL,
  `itemId` varchar(75) DEFAULT NULL,
  `sku` varchar(75) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` longtext,
  `properties` longtext,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `shippedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderItemId`),
  KEY `IX_B5F82C7A` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shoppingorderitem`
--

LOCK TABLES `shoppingorderitem` WRITE;
/*!40000 ALTER TABLE `shoppingorderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `shoppingorderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialactivity`
--

DROP TABLE IF EXISTS `socialactivity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialactivity` (
  `activityId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` bigint(20) DEFAULT NULL,
  `mirrorActivityId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `extraData` longtext,
  `receiverUserId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`activityId`),
  UNIQUE KEY `IX_8F32DEC9` (`groupId`,`userId`,`createDate`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  KEY `IX_82E39A0C` (`classNameId`),
  KEY `IX_A853C757` (`classNameId`,`classPK`),
  KEY `IX_64B1BC66` (`companyId`),
  KEY `IX_2A2468` (`groupId`),
  KEY `IX_1271F25F` (`mirrorActivityId`),
  KEY `IX_1F00C374` (`mirrorActivityId`,`classNameId`,`classPK`),
  KEY `IX_121CA3CB` (`receiverUserId`),
  KEY `IX_3504B8BC` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialactivity`
--

LOCK TABLES `socialactivity` WRITE;
/*!40000 ALTER TABLE `socialactivity` DISABLE KEYS */;
INSERT INTO `socialactivity` VALUES (1,10154,10130,10165,1275983535933,0,10127,12020,1,'',0);
/*!40000 ALTER TABLE `socialactivity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialequityassetentry`
--

DROP TABLE IF EXISTS `socialequityassetentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialequityassetentry` (
  `equityAssetEntryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `assetEntryId` bigint(20) DEFAULT NULL,
  `informationK` double DEFAULT NULL,
  `informationB` double DEFAULT NULL,
  `informationEquity` double DEFAULT NULL,
  PRIMARY KEY (`equityAssetEntryId`),
  UNIQUE KEY `IX_22F6B5CB` (`assetEntryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialequityassetentry`
--

LOCK TABLES `socialequityassetentry` WRITE;
/*!40000 ALTER TABLE `socialequityassetentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialequityassetentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialequityhistory`
--

DROP TABLE IF EXISTS `socialequityhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialequityhistory` (
  `equityHistoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `personalEquity` int(11) DEFAULT NULL,
  PRIMARY KEY (`equityHistoryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialequityhistory`
--

LOCK TABLES `socialequityhistory` WRITE;
/*!40000 ALTER TABLE `socialequityhistory` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialequityhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialequitylog`
--

DROP TABLE IF EXISTS `socialequitylog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialequitylog` (
  `equityLogId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `assetEntryId` bigint(20) DEFAULT NULL,
  `actionId` varchar(75) DEFAULT NULL,
  `actionDate` int(11) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `validity` int(11) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`equityLogId`),
  KEY `IX_E8DA181D` (`assetEntryId`,`type_`,`active_`),
  KEY `IX_60CD2F21` (`userId`,`assetEntryId`,`actionId`,`active_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialequitylog`
--

LOCK TABLES `socialequitylog` WRITE;
/*!40000 ALTER TABLE `socialequitylog` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialequitylog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialequitysetting`
--

DROP TABLE IF EXISTS `socialequitysetting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialequitysetting` (
  `equitySettingId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `actionId` varchar(75) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  `validity` int(11) DEFAULT NULL,
  PRIMARY KEY (`equitySettingId`),
  UNIQUE KEY `IX_903C1B28` (`groupId`,`classNameId`,`actionId`,`type_`),
  KEY `IX_F3AAD60D` (`groupId`,`classNameId`,`actionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialequitysetting`
--

LOCK TABLES `socialequitysetting` WRITE;
/*!40000 ALTER TABLE `socialequitysetting` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialequitysetting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialequityuser`
--

DROP TABLE IF EXISTS `socialequityuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialequityuser` (
  `equityUserId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `contributionEquity` double DEFAULT NULL,
  `participationK` double DEFAULT NULL,
  `participationB` double DEFAULT NULL,
  `participationEquity` double DEFAULT NULL,
  `personalEquity` double DEFAULT NULL,
  PRIMARY KEY (`equityUserId`),
  UNIQUE KEY `IX_D65D3521` (`groupId`,`userId`),
  KEY `IX_6ECBD5D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialequityuser`
--

LOCK TABLES `socialequityuser` WRITE;
/*!40000 ALTER TABLE `socialequityuser` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialequityuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialrelation`
--

DROP TABLE IF EXISTS `socialrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialrelation` (
  `uuid_` varchar(75) DEFAULT NULL,
  `relationId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `createDate` bigint(20) DEFAULT NULL,
  `userId1` bigint(20) DEFAULT NULL,
  `userId2` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  PRIMARY KEY (`relationId`),
  UNIQUE KEY `IX_12A92145` (`userId1`,`userId2`,`type_`),
  KEY `IX_61171E99` (`companyId`),
  KEY `IX_95135D1C` (`companyId`,`type_`),
  KEY `IX_C31A64C6` (`type_`),
  KEY `IX_5A40CDCC` (`userId1`),
  KEY `IX_4B52BE89` (`userId1`,`type_`),
  KEY `IX_5A40D18D` (`userId2`),
  KEY `IX_3F9C2FA8` (`userId2`,`type_`),
  KEY `IX_F0CA24A5` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialrelation`
--

LOCK TABLES `socialrelation` WRITE;
/*!40000 ALTER TABLE `socialrelation` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialrequest`
--

DROP TABLE IF EXISTS `socialrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialrequest` (
  `uuid_` varchar(75) DEFAULT NULL,
  `requestId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` bigint(20) DEFAULT NULL,
  `modifiedDate` bigint(20) DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `type_` int(11) DEFAULT NULL,
  `extraData` longtext,
  `receiverUserId` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`requestId`),
  UNIQUE KEY `IX_36A90CA7` (`userId`,`classNameId`,`classPK`,`type_`,`receiverUserId`),
  UNIQUE KEY `IX_4F973EFE` (`uuid_`,`groupId`),
  KEY `IX_D3425487` (`classNameId`,`classPK`,`type_`,`receiverUserId`,`status`),
  KEY `IX_A90FE5A0` (`companyId`),
  KEY `IX_32292ED1` (`receiverUserId`),
  KEY `IX_D9380CB7` (`receiverUserId`,`status`),
  KEY `IX_80F7A9C2` (`userId`),
  KEY `IX_CC86A444` (`userId`,`classNameId`,`classPK`,`type_`,`status`),
  KEY `IX_AB5906A8` (`userId`,`status`),
  KEY `IX_49D5872C` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialrequest`
--

LOCK TABLES `socialrequest` WRITE;
/*!40000 ALTER TABLE `socialrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialrequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `subscriptionId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `frequency` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`subscriptionId`),
  UNIQUE KEY `IX_2E1A92D4` (`companyId`,`userId`,`classNameId`,`classPK`),
  KEY `IX_786D171A` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_54243AFD` (`userId`),
  KEY `IX_E8F34171` (`userId`,`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasksproposal`
--

DROP TABLE IF EXISTS `tasksproposal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasksproposal` (
  `proposalId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` varchar(75) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `publishDate` datetime DEFAULT NULL,
  `dueDate` datetime DEFAULT NULL,
  PRIMARY KEY (`proposalId`),
  UNIQUE KEY `IX_181A4A1B` (`classNameId`,`classPK`),
  KEY `IX_7FB27324` (`groupId`),
  KEY `IX_6EEC675E` (`groupId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasksproposal`
--

LOCK TABLES `tasksproposal` WRITE;
/*!40000 ALTER TABLE `tasksproposal` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasksproposal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasksreview`
--

DROP TABLE IF EXISTS `tasksreview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasksreview` (
  `reviewId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `proposalId` bigint(20) DEFAULT NULL,
  `assignedByUserId` bigint(20) DEFAULT NULL,
  `assignedByUserName` varchar(75) DEFAULT NULL,
  `stage` int(11) DEFAULT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `rejected` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`reviewId`),
  UNIQUE KEY `IX_5C6BE4C7` (`userId`,`proposalId`),
  KEY `IX_4D0C7F8D` (`proposalId`),
  KEY `IX_70AFEA01` (`proposalId`,`stage`),
  KEY `IX_1894B29A` (`proposalId`,`stage`,`completed`),
  KEY `IX_41AFC20C` (`proposalId`,`stage`,`completed`,`rejected`),
  KEY `IX_36F512E6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasksreview`
--

LOCK TABLES `tasksreview` WRITE;
/*!40000 ALTER TABLE `tasksreview` DISABLE KEYS */;
/*!40000 ALTER TABLE `tasksreview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`teamId`),
  UNIQUE KEY `IX_143DC786` (`groupId`,`name`),
  KEY `IX_AE6E9907` (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket` (
  `ticketId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `key_` varchar(75) DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ticketId`),
  KEY `IX_B2468446` (`key_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_`
--

DROP TABLE IF EXISTS `user_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_` (
  `uuid_` varchar(75) DEFAULT NULL,
  `userId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `defaultUser` tinyint(4) DEFAULT NULL,
  `contactId` bigint(20) DEFAULT NULL,
  `password_` varchar(75) DEFAULT NULL,
  `passwordEncrypted` tinyint(4) DEFAULT NULL,
  `passwordReset` tinyint(4) DEFAULT NULL,
  `passwordModifiedDate` datetime DEFAULT NULL,
  `reminderQueryQuestion` varchar(75) DEFAULT NULL,
  `reminderQueryAnswer` varchar(75) DEFAULT NULL,
  `graceLoginCount` int(11) DEFAULT NULL,
  `screenName` varchar(75) DEFAULT NULL,
  `emailAddress` varchar(75) DEFAULT NULL,
  `openId` varchar(1024) DEFAULT NULL,
  `portraitId` bigint(20) DEFAULT NULL,
  `languageId` varchar(75) DEFAULT NULL,
  `timeZoneId` varchar(75) DEFAULT NULL,
  `greeting` varchar(255) DEFAULT NULL,
  `comments` longtext,
  `firstName` varchar(75) DEFAULT NULL,
  `middleName` varchar(75) DEFAULT NULL,
  `lastName` varchar(75) DEFAULT NULL,
  `jobTitle` varchar(100) DEFAULT NULL,
  `loginDate` datetime DEFAULT NULL,
  `loginIP` varchar(75) DEFAULT NULL,
  `lastLoginDate` datetime DEFAULT NULL,
  `lastLoginIP` varchar(75) DEFAULT NULL,
  `lastFailedLoginDate` datetime DEFAULT NULL,
  `failedLoginAttempts` int(11) DEFAULT NULL,
  `lockout` tinyint(4) DEFAULT NULL,
  `lockoutDate` datetime DEFAULT NULL,
  `agreedToTermsOfUse` tinyint(4) DEFAULT NULL,
  `active_` tinyint(4) DEFAULT NULL,
  `socialContributionEquity` double DEFAULT NULL,
  `socialParticipationEquity` double DEFAULT NULL,
  `socialPersonalEquity` double DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `IX_615E9F7A` (`companyId`,`emailAddress`),
  UNIQUE KEY `IX_C5806019` (`companyId`,`screenName`),
  UNIQUE KEY `IX_9782AD88` (`companyId`,`userId`),
  UNIQUE KEY `IX_5ADBE171` (`contactId`),
  KEY `IX_3A1E834E` (`companyId`),
  KEY `IX_5204C37B` (`companyId`,`active_`),
  KEY `IX_6EF03E4E` (`companyId`,`defaultUser`),
  KEY `IX_762F63C6` (`emailAddress`),
  KEY `IX_A9ED7DD3` (`openId`(255)),
  KEY `IX_A18034A4` (`portraitId`),
  KEY `IX_E0422BDA` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_`
--

LOCK TABLES `user_` WRITE;
/*!40000 ALTER TABLE `user_` DISABLE KEYS */;
INSERT INTO `user_` VALUES ('961baca8-9b55-4ae0-bc0f-a953f74b0a6f',10133,10130,'2010-06-08 07:10:55','2010-06-08 07:10:55',1,10134,'password',0,0,NULL,'','',0,'10133','default@liferay.com','',0,'en_US','UTC','Welcome!','','','','','','2010-06-08 07:10:55','',NULL,'',NULL,0,0,NULL,1,1,0,0,0),('73900cda-682f-4e08-80e5-5ade22b7d24e',10165,10130,'2010-06-08 07:10:56','2010-06-08 07:13:38',0,10166,'qUqP5cyxm6YcTAhz05Hph5gvu9M=',1,0,NULL,'what-is-your-father\'s-middle-name','1',0,'michael.han','michael.han@liferay.com','',0,'en_US','UTC','Welcome Test Test!','','Michael','','Han','Director of Operations','2010-06-08 07:50:29','0:0:0:0:0:0:0:1','2010-06-08 07:13:04','0:0:0:0:0:0:0:1',NULL,0,0,NULL,1,1,0,0,0),('12374320-33aa-445c-87b3-3f7fe63080a7',10302,10130,'2010-06-08 07:14:00','2010-06-08 07:51:12',0,10303,'qUqP5cyxm6YcTAhz05Hph5gvu9M=',1,0,'2010-06-08 07:14:05','','',0,'angela.kim','angela.kim@liferay.com','',0,'en_US','UTC','Welcome Angela Kim!','','Angela','','Kim','Corporate Counsel',NULL,'',NULL,'',NULL,0,0,NULL,0,1,0,0,0),('4d63de9e-6888-4eb2-be93-e3a95349b89f',10311,10130,'2010-06-08 07:14:26','2010-06-08 07:50:52',0,10312,'qUqP5cyxm6YcTAhz05Hph5gvu9M=',1,0,'2010-06-08 07:50:52','','',0,'paul.hinz','paul.hinz@liferay.com','',0,'en_US','UTC','Welcome Paul Hinz!','','Paul','','Hinz','CMO',NULL,'',NULL,'',NULL,0,0,NULL,0,1,0,0,0);
/*!40000 ALTER TABLE `user_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroup` (
  `userGroupId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `parentUserGroupId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`userGroupId`),
  UNIQUE KEY `IX_23EAD0D` (`companyId`,`name`),
  KEY `IX_524FEFCE` (`companyId`),
  KEY `IX_69771487` (`companyId`,`parentUserGroupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroupgrouprole`
--

DROP TABLE IF EXISTS `usergroupgrouprole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroupgrouprole` (
  `userGroupId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userGroupId`,`groupId`,`roleId`),
  KEY `IX_CCBE4063` (`groupId`),
  KEY `IX_CAB0CCC8` (`groupId`,`roleId`),
  KEY `IX_1CDF88C` (`roleId`),
  KEY `IX_DCDED558` (`userGroupId`),
  KEY `IX_73C52252` (`userGroupId`,`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroupgrouprole`
--

LOCK TABLES `usergroupgrouprole` WRITE;
/*!40000 ALTER TABLE `usergroupgrouprole` DISABLE KEYS */;
/*!40000 ALTER TABLE `usergroupgrouprole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergrouprole`
--

DROP TABLE IF EXISTS `usergrouprole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergrouprole` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`,`roleId`),
  KEY `IX_1B988D7A` (`groupId`),
  KEY `IX_871412DF` (`groupId`,`roleId`),
  KEY `IX_887A2C95` (`roleId`),
  KEY `IX_887BE56A` (`userId`),
  KEY `IX_4D040680` (`userId`,`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergrouprole`
--

LOCK TABLES `usergrouprole` WRITE;
/*!40000 ALTER TABLE `usergrouprole` DISABLE KEYS */;
/*!40000 ALTER TABLE `usergrouprole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useridmapper`
--

DROP TABLE IF EXISTS `useridmapper`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useridmapper` (
  `userIdMapperId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `externalUserId` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`userIdMapperId`),
  UNIQUE KEY `IX_41A32E0D` (`type_`,`externalUserId`),
  UNIQUE KEY `IX_D1C44A6E` (`userId`,`type_`),
  KEY `IX_E60EA987` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useridmapper`
--

LOCK TABLES `useridmapper` WRITE;
/*!40000 ALTER TABLE `useridmapper` DISABLE KEYS */;
/*!40000 ALTER TABLE `useridmapper` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_groups`
--

DROP TABLE IF EXISTS `users_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_groups` (
  `userId` bigint(20) NOT NULL,
  `groupId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`groupId`),
  KEY `IX_C4F9E699` (`groupId`),
  KEY `IX_F10B6C6B` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_groups`
--

LOCK TABLES `users_groups` WRITE;
/*!40000 ALTER TABLE `users_groups` DISABLE KEYS */;
INSERT INTO `users_groups` VALUES (10165,10154),(10302,10154),(10311,10154);
/*!40000 ALTER TABLE `users_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_orgs`
--

DROP TABLE IF EXISTS `users_orgs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_orgs` (
  `userId` bigint(20) NOT NULL,
  `organizationId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`organizationId`),
  KEY `IX_7EF4EC0E` (`organizationId`),
  KEY `IX_FB646CA6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_orgs`
--

LOCK TABLES `users_orgs` WRITE;
/*!40000 ALTER TABLE `users_orgs` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_orgs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_permissions`
--

DROP TABLE IF EXISTS `users_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_permissions` (
  `userId` bigint(20) NOT NULL,
  `permissionId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`permissionId`),
  KEY `IX_8AE58A91` (`permissionId`),
  KEY `IX_C26AA64D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_permissions`
--

LOCK TABLES `users_permissions` WRITE;
/*!40000 ALTER TABLE `users_permissions` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_roles` (
  `userId` bigint(20) NOT NULL,
  `roleId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `IX_C19E5F31` (`roleId`),
  KEY `IX_C1A01806` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (10165,10136),(10302,10136),(10311,10136),(10133,10137),(10165,10139),(10302,10139),(10311,10139),(10165,10140),(10302,10140),(10311,10140);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_teams`
--

DROP TABLE IF EXISTS `users_teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_teams` (
  `userId` bigint(20) NOT NULL,
  `teamId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`,`teamId`),
  KEY `IX_4D06AD51` (`teamId`),
  KEY `IX_A098EFBF` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_teams`
--

LOCK TABLES `users_teams` WRITE;
/*!40000 ALTER TABLE `users_teams` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_teams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_usergroups`
--

DROP TABLE IF EXISTS `users_usergroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users_usergroups` (
  `userGroupId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  PRIMARY KEY (`userGroupId`,`userId`),
  KEY `IX_66FF2503` (`userGroupId`),
  KEY `IX_BE8102D6` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_usergroups`
--

LOCK TABLES `users_usergroups` WRITE;
/*!40000 ALTER TABLE `users_usergroups` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_usergroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertracker`
--

DROP TABLE IF EXISTS `usertracker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertracker` (
  `userTrackerId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `sessionId` varchar(200) DEFAULT NULL,
  `remoteAddr` varchar(75) DEFAULT NULL,
  `remoteHost` varchar(75) DEFAULT NULL,
  `userAgent` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userTrackerId`),
  KEY `IX_29BA1CF5` (`companyId`),
  KEY `IX_46B0AE8E` (`sessionId`),
  KEY `IX_E4EFBA8D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertracker`
--

LOCK TABLES `usertracker` WRITE;
/*!40000 ALTER TABLE `usertracker` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertracker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertrackerpath`
--

DROP TABLE IF EXISTS `usertrackerpath`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertrackerpath` (
  `userTrackerPathId` bigint(20) NOT NULL,
  `userTrackerId` bigint(20) DEFAULT NULL,
  `path_` longtext,
  `pathDate` datetime DEFAULT NULL,
  PRIMARY KEY (`userTrackerPathId`),
  KEY `IX_14D8BCC0` (`userTrackerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertrackerpath`
--

LOCK TABLES `usertrackerpath` WRITE;
/*!40000 ALTER TABLE `usertrackerpath` DISABLE KEYS */;
/*!40000 ALTER TABLE `usertrackerpath` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary`
--

DROP TABLE IF EXISTS `vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vocabulary` (
  `vocabularyId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `folksonomy` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`vocabularyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webdavprops`
--

DROP TABLE IF EXISTS `webdavprops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `webdavprops` (
  `webDavPropsId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `props` longtext,
  PRIMARY KEY (`webDavPropsId`),
  UNIQUE KEY `IX_97DFA146` (`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webdavprops`
--

LOCK TABLES `webdavprops` WRITE;
/*!40000 ALTER TABLE `webdavprops` DISABLE KEYS */;
/*!40000 ALTER TABLE `webdavprops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `website`
--

DROP TABLE IF EXISTS `website`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `website` (
  `websiteId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `url` longtext,
  `typeId` int(11) DEFAULT NULL,
  `primary_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`websiteId`),
  KEY `IX_96F07007` (`companyId`),
  KEY `IX_4F0F0CA7` (`companyId`,`classNameId`),
  KEY `IX_F960131C` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_1AA07A6D` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_F75690BB` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `website`
--

LOCK TABLES `website` WRITE;
/*!40000 ALTER TABLE `website` DISABLE KEYS */;
/*!40000 ALTER TABLE `website` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wikinode`
--

DROP TABLE IF EXISTS `wikinode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wikinode` (
  `uuid_` varchar(75) DEFAULT NULL,
  `nodeId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` longtext,
  `lastPostDate` datetime DEFAULT NULL,
  PRIMARY KEY (`nodeId`),
  UNIQUE KEY `IX_920CD8B1` (`groupId`,`name`),
  UNIQUE KEY `IX_7609B2AE` (`uuid_`,`groupId`),
  KEY `IX_5D6FE3F0` (`companyId`),
  KEY `IX_B480A672` (`groupId`),
  KEY `IX_6C112D7C` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wikinode`
--

LOCK TABLES `wikinode` WRITE;
/*!40000 ALTER TABLE `wikinode` DISABLE KEYS */;
INSERT INTO `wikinode` VALUES ('fee40511-79b5-47fd-9540-fae89607de05',12018,10154,10130,10165,'Michael Han','2010-06-08 07:52:15','2010-06-08 07:52:15','Main','','2010-06-08 07:52:15');
/*!40000 ALTER TABLE `wikinode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wikipage`
--

DROP TABLE IF EXISTS `wikipage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wikipage` (
  `uuid_` varchar(75) DEFAULT NULL,
  `pageId` bigint(20) NOT NULL,
  `resourcePrimKey` bigint(20) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `nodeId` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `version` double DEFAULT NULL,
  `minorEdit` tinyint(4) DEFAULT NULL,
  `content` longtext,
  `summary` longtext,
  `format` varchar(75) DEFAULT NULL,
  `head` tinyint(4) DEFAULT NULL,
  `parentTitle` varchar(255) DEFAULT NULL,
  `redirectTitle` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `statusByUserId` bigint(20) DEFAULT NULL,
  `statusByUserName` varchar(75) DEFAULT NULL,
  `statusDate` datetime DEFAULT NULL,
  PRIMARY KEY (`pageId`),
  UNIQUE KEY `IX_3D4AF476` (`nodeId`,`title`,`version`),
  UNIQUE KEY `IX_2CD67C81` (`resourcePrimKey`,`nodeId`,`version`),
  UNIQUE KEY `IX_899D3DFB` (`uuid_`,`groupId`),
  KEY `IX_A2001730` (`format`),
  KEY `IX_C8A9C476` (`nodeId`),
  KEY `IX_E7F635CA` (`nodeId`,`head`),
  KEY `IX_65E84AF4` (`nodeId`,`head`,`parentTitle`),
  KEY `IX_9F7655DA` (`nodeId`,`head`,`parentTitle`,`status`),
  KEY `IX_432F0AB0` (`nodeId`,`head`,`status`),
  KEY `IX_46EEF3C8` (`nodeId`,`parentTitle`),
  KEY `IX_1ECC7656` (`nodeId`,`redirectTitle`),
  KEY `IX_546F2D5C` (`nodeId`,`status`),
  KEY `IX_997EEDD2` (`nodeId`,`title`),
  KEY `IX_E745EA26` (`nodeId`,`title`,`head`),
  KEY `IX_BEA33AB8` (`nodeId`,`title`,`status`),
  KEY `IX_B771D67` (`resourcePrimKey`,`nodeId`),
  KEY `IX_94D1054D` (`resourcePrimKey`,`nodeId`,`status`),
  KEY `IX_FBBE7C96` (`userId`,`nodeId`,`status`),
  KEY `IX_9C0E478F` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wikipage`
--

LOCK TABLES `wikipage` WRITE;
/*!40000 ALTER TABLE `wikipage` DISABLE KEYS */;
INSERT INTO `wikipage` VALUES ('46ba79b4-0c39-4d2b-8182-50786c5d3860',12019,12020,10154,10130,10165,'Michael Han','2010-06-08 07:52:15','2010-06-08 07:52:15',12018,'FrontPage',1,1,'','New','creole',1,'','',0,10165,'Michael Han','2010-06-08 07:52:15');
/*!40000 ALTER TABLE `wikipage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wikipageresource`
--

DROP TABLE IF EXISTS `wikipageresource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wikipageresource` (
  `uuid_` varchar(75) DEFAULT NULL,
  `resourcePrimKey` bigint(20) NOT NULL,
  `nodeId` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`resourcePrimKey`),
  UNIQUE KEY `IX_21277664` (`nodeId`,`title`),
  KEY `IX_BE898221` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wikipageresource`
--

LOCK TABLES `wikipageresource` WRITE;
/*!40000 ALTER TABLE `wikipageresource` DISABLE KEYS */;
INSERT INTO `wikipageresource` VALUES ('90f2450b-d33b-457b-893f-43b839ba5f9b',12020,12018,'FrontPage');
/*!40000 ALTER TABLE `wikipageresource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflowdefinitionlink`
--

DROP TABLE IF EXISTS `workflowdefinitionlink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflowdefinitionlink` (
  `workflowDefinitionLinkId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `workflowDefinitionName` varchar(75) DEFAULT NULL,
  `workflowDefinitionVersion` int(11) DEFAULT NULL,
  PRIMARY KEY (`workflowDefinitionLinkId`),
  KEY `IX_A8B0D276` (`companyId`),
  KEY `IX_A4DB1F0F` (`companyId`,`workflowDefinitionName`,`workflowDefinitionVersion`),
  KEY `IX_B6EE8C9E` (`groupId`,`companyId`,`classNameId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflowdefinitionlink`
--

LOCK TABLES `workflowdefinitionlink` WRITE;
/*!40000 ALTER TABLE `workflowdefinitionlink` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflowdefinitionlink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workflowinstancelink`
--

DROP TABLE IF EXISTS `workflowinstancelink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workflowinstancelink` (
  `workflowInstanceLinkId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `workflowInstanceId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`workflowInstanceLinkId`),
  KEY `IX_415A7007` (`groupId`,`companyId`,`classNameId`,`classPK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workflowinstancelink`
--

LOCK TABLES `workflowinstancelink` WRITE;
/*!40000 ALTER TABLE `workflowinstancelink` DISABLE KEYS */;
/*!40000 ALTER TABLE `workflowinstancelink` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-06-08  2:55:41

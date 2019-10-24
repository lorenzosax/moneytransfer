CREATE DATABASE  IF NOT EXISTS `money_transfer` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `money_transfer`;
-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: money_transfer
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `bank_account`
--

DROP TABLE IF EXISTS `bank_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_number` varchar(255) NOT NULL,
  `available_balance` decimal(19,2) NOT NULL,
  `commissions` varchar(255) NOT NULL,
  `currency_code` varchar(255) NOT NULL,
  `iban` varchar(255) NOT NULL,
  `transfer_limit` decimal(19,2) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj818ht4ban0c4uw4bmsbf3jme` (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_account`
--

LOCK TABLES `bank_account` WRITE;
/*!40000 ALTER TABLE `bank_account` DISABLE KEYS */;
INSERT INTO `bank_account` (`id`, `account_number`, `available_balance`, `commissions`, `currency_code`, `iban`, `transfer_limit`, `customer_id`) VALUES (1,'0001234566',100000.00,'0','EUR','IT34H7895966685880001234566',10000.00,123);
INSERT INTO `bank_account` (`id`, `account_number`, `available_balance`, `commissions`, `currency_code`, `iban`, `transfer_limit`, `customer_id`) VALUES (2,'0001234222',35000.00,'0','EUR','IT74C0300203280410001234222',10000.00,124);
INSERT INTO `bank_account` (`id`, `account_number`, `available_balance`, `commissions`, `currency_code`, `iban`, `transfer_limit`, `customer_id`) VALUES (3,'0001234888',8000.00,'0','EUR','IT34O0300203280440001234888',5000.00,125);
INSERT INTO `bank_account` (`id`, `account_number`, `available_balance`, `commissions`, `currency_code`, `iban`, `transfer_limit`, `customer_id`) VALUES (4,'0001234911',1000.00,'0','EUR','IT42B0300203280530001234911',1000.00,126);
/*!40000 ALTER TABLE `bank_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lastname` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=124 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`id`, `lastname`, `name`) VALUES (123,'Gagliani','Lorenzo');
INSERT INTO `customer` (`id`, `lastname`, `name`) VALUES (124,'Rossi','Mario');
INSERT INTO `customer` (`id`, `lastname`, `name`) VALUES (125,'Verdi','Luigi');
INSERT INTO `customer` (`id`, `lastname`, `name`) VALUES (126,'Bianchi','Massimo');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accreditation_date` varchar(255) NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  `beneficiary_iban` varchar(255) NOT NULL,
  `beneficiary_name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `cro` varchar(255) DEFAULT NULL,
  `currency_code` varchar(255) NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `payment_reason` varchar(255) NOT NULL,
  `trx_id` varchar(255) NOT NULL,
  `bank_account_id` bigint(20) NOT NULL,
  `customer_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKec44dj1u86xnsku7ld84pirje` (`bank_account_id`),
  KEY `FKnbpjofb5abhjg5hiovi0t3k57` (`customer_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-24 16:51:52

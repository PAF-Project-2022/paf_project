-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2022 at 02:48 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pafelectricity`
--

-- --------------------------------------------------------

--
-- Table structure for table `billing1`
--

CREATE TABLE IF NOT EXISTS `billing1` (
  `biil_ID` int(6) NOT NULL AUTO_INCREMENT,
  `bill_AccNo` varchar(200) NOT NULL,
  `bill_Date` varchar(200) NOT NULL,
  `bill_UnitA` varchar(200) NOT NULL,
  `bill_Unitprice` varchar(200) NOT NULL,
  `bill_Total` varchar(200) NOT NULL,
  PRIMARY KEY (`biil_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `customer1`
--

CREATE TABLE IF NOT EXISTS `customer1` (
  `Customer_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Customer_name` varchar(200) NOT NULL,
  `Customer_address` varchar(200) NOT NULL,
  `Customer_nic` varchar(200) NOT NULL,
  `Customer_email` varchar(200) NOT NULL,
  `Customer_mobileNo` varchar(200) NOT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `inquiry1`
--

CREATE TABLE IF NOT EXISTS `inquiry1` (
  `inqID` int(6) NOT NULL AUTO_INCREMENT,
  `PersonName` varchar(200) NOT NULL,
  `Area` varchar(200) NOT NULL,
  `Date` varchar(200) NOT NULL,
  `Reason` varchar(200) NOT NULL,
  PRIMARY KEY (`inqID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `payment1`
--

CREATE TABLE IF NOT EXISTS `payment1` (
  `payment_ID` int(6) NOT NULL AUTO_INCREMENT,
  `Payment_AccountNO` varchar(200) NOT NULL,
  `Payment_CName` varchar(200) NOT NULL,
  `Payment_Date` varchar(200) NOT NULL,
  `Payment_TotalAmount` float NOT NULL,
  PRIMARY KEY (`payment_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

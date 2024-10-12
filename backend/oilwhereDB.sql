DROP DATABASE IF EXISTS `oilwhereDB`;
CREATE DATABASE IF NOT EXISTS `oilwhereDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhereDB`;

-- Creation of purchase history table
DROP TABLE IF EXISTS `Purchase History`;
CREATE TABLE IF NOT EXISTS `Purchase History` (
  `PurchaseID` INT NOT NULL AUTO_INCREMENT,
  `SaleDate` date NOT NULL,
  `SaleType` varchar(32) NOT NULL,
  `Digital`  varchar(32) NOT NULL,
  `CustomerID` int NOT NULL,
  `ZipCode` int(6) NOT NULL,
  `ShippingMethod` varchar(32) NOT NULL,
  `Product` varchar(32) NOT NULL,
  `Variant` int NOT NULL,
  `Quantity` int NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `ProductPrice` decimal(10,2) NOT NULL,
  PRIMARY KEY (`PurchaseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create a temporary table with SaleDate as VARCHAR
DROP TABLE IF EXISTS `PurchaseHistoryTemp`;
CREATE TABLE IF NOT EXISTS `PurchaseHistoryTemp` (
  `PurchaseID` INT NOT NULL AUTO_INCREMENT,
  `SaleDate` VARCHAR(10) NOT NULL,  -- Store date as VARCHAR first
  `SaleType` VARCHAR(32) NOT NULL,
  `Digital`  VARCHAR(32) NOT NULL,
  `CustomerID` INT NOT NULL,
  `ZipCode` INT(6) NOT NULL,
  `ShippingMethod` VARCHAR(32) NOT NULL,
  `Product` VARCHAR(32) NOT NULL,
  `Variant` INT NOT NULL,
  `Quantity` INT NOT NULL,
  `Price` DECIMAL(10,2) NOT NULL,
  `ProductPrice` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`PurchaseID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of user table
DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `Username` varchar(255) NOT NULL,
  `Role` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of NewsLetter table
DROP TABLE IF EXISTS `NewsLetter`;
CREATE TABLE IF NOT EXISTS `Newsletter` (
  `DesignID` INT NOT NULL AUTO_INCREMENT,
  `Content` text NOT NULL,
  PRIMARY KEY (`DesignID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Loading csv data into purchase history
LOAD DATA INFILE 'C:/wamp64/tmp/Sales_Data_updated_Sep 2023.csv'
INTO TABLE `PurchaseHistoryTemp`
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

-- Insert data into the final table with date conversion
INSERT INTO `Purchase History` (
  `SaleDate`, `SaleType`, `Digital`, `CustomerID`, `ZipCode`, `ShippingMethod`, `Product`, `Variant`, `Quantity`, `Price`, `ProductPrice`
)
SELECT 
  STR_TO_DATE(`SaleDate`, '%d/%m/%Y'),  -- Convert from DD/MM/YYYY to YYYY-MM-DD
  `SaleType`, `Digital`, `CustomerID`, `ZipCode`, `ShippingMethod`, `Product`, `Variant`, `Quantity`, `Price`, `ProductPrice`
FROM `PurchaseHistoryTemp`;

-- Drop the temporary table
DROP TABLE IF EXISTS `PurchaseHistoryTemp`;

-- Manually inserting user details into user table
INSERT INTO `User` (`Username`, `Role`, `Password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

-- Manually inserting to newsletter table
INSERT INTO `NewsLetter` (`DesignID`, `Content`) VALUES
(1, 'marketingStuff.txt'),
(2, 'uglyDesign.txt');

COMMIT;
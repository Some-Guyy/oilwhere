DROP DATABASE IF EXISTS `oilwhereDB_test`;
CREATE DATABASE IF NOT EXISTS `oilwhereDB_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhereDB_test`;

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

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Role` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `Newsletter`;
CREATE TABLE IF NOT EXISTS `Newsletter` (
  `DesignID` INT NOT NULL AUTO_INCREMENT,
  `Content` text NOT NULL,
  PRIMARY KEY (`DesignID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `Purchase History` (`PurchaseID`, `SaleDate`, `SaleType`, `Digital`, `CustomerID`,`ZipCode`,`ShippingMethod`,`Product`,`Variant`,`Quantity`,`Price`,`ProductPrice`) VALUES
(1, '2019-11-22', 'Direct - B2B', 'Online - Website', 1, '437050', 'Standard Delivery', 'Chili oil', 100, 3, 5.9, 17.7),
(2, '2019-11-22', 'Consignment', 'Online - Website', 2, '126793', 'Standard Delivery', 'Organic Bio/Olio', 500, 2, 16.9, 33.8),
(3, '2019-11-22', 'Marketing', 'Online - Website', 2, '126793', 'Standard Delivery', 'Basil oil', 100, 1, 5.9, 5.9),
(4, '2020-05-22', 'Direct - B2C', 'Online - Website', 65, '101080', 'Standard Delivery', 'Truffle oil', 100, 1, 7.9, 6.32),
(5, '2019-12-17', 'Direct - B2C', 'Online - Website', 36, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8);

INSERT INTO `User` (`Username`, `Role`, `Password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

INSERT INTO `Newsletter` (`DesignID`, `Content`) VALUES
(1, 'marketingStuff.txt'),
(2, 'uglyDesign.txt');

COMMIT;
DROP DATABASE IF EXISTS `oilwhere`;
CREATE DATABASE IF NOT EXISTS `oilwhere` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhere`;

-- Creation of purchase_history table
DROP TABLE IF EXISTS `purchase_history`;
CREATE TABLE IF NOT EXISTS `purchase_history` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,
  `sale_date` date NOT NULL,
  `sale_type` varchar(255),
  `digital`  varchar(255),
  `customer_id` int NOT NULL,
  `zipcode` int(6),
  `shipping_method` varchar(255),
  `product` varchar(255),
  `variant` int NOT NULL,
  `quantity` int NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create a temporary table with sale_date as VARCHAR
DROP TABLE IF EXISTS `purchase_history_temp`;
CREATE TABLE IF NOT EXISTS `purchase_history_temp` (
  `purchase_id` INT NOT NULL AUTO_INCREMENT,
  `sale_date` VARCHAR(10) NOT NULL,  -- Store date as VARCHAR first
  `sale_type` VARCHAR(255),
  `digital`  VARCHAR(255),
  `customer_id` INT NOT NULL,
  `zipcode` INT(6),
  `shipping_method` VARCHAR(255),
  `product` VARCHAR(255),
  `variant` INT NOT NULL,
  `quantity` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `product_price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of user table
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `role` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of NewsLetter table
DROP TABLE IF EXISTS `newsletter`;
CREATE TABLE IF NOT EXISTS `newsletter` (
  `design_id` INT NOT NULL AUTO_INCREMENT,
  `content` text NOT NULL,
  PRIMARY KEY (`design_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Loading csv data into purchase_history
LOAD DATA INFILE 'C:/wamp64/tmp/Sales_Data_updated_Sep 2023.csv'
INTO TABLE `purchase_history_temp`
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(@discard, sale_date, @vsale_type, @vdigital, customer_id, @vzipcode, @vshipping_method, @vproduct, variant, quantity, price, product_price)
SET
sale_type = NULLIF(@vsale_type, ''),
digital = NULLIF(@vdigital, ''),
zipcode = NULLIF(@vzipcode, ''),
shipping_method = NULLIF(@vshipping_method, ''),
product = NULLIF(@vproduct, '')
;

-- Insert data into the final table with date conversion
INSERT INTO `purchase_history` (
  `sale_date`, `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`
)
SELECT 
  STR_TO_DATE(`sale_date`, '%d/%m/%Y'),  -- Convert from DD/MM/YYYY to YYYY-MM-DD
  `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`
FROM `purchase_history_temp`;

-- Drop the temporary table
DROP TABLE IF EXISTS `purchase_history_temp`;

-- Manually inserting user details into user table
INSERT INTO `user` (`username`, `role`, `password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

-- Manually inserting to newsletter table
INSERT INTO `newsletter` (`design_id`, `content`) VALUES
(1, 'marketingStuff.txt'),
(2, 'uglyDesign.txt');

COMMIT;
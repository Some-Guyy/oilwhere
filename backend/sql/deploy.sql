DROP DATABASE IF EXISTS `oilwhere`;
CREATE DATABASE IF NOT EXISTS `oilwhere` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhere`;


-- Creation of Customer table to hold details about customer, including their name and email address
DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `email` varchar(255),
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create a temporary table with sale_date as VARCHAR to help convert date to appropriate format and dropped afterwards
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

-- Loading csv data into the temporary table, purchase_history_temp
LOAD DATA INFILE 'C:/wamp64/tmp/Sales_Data_updated_Sep 2023.csv' -- Please edit this line to your relevant file directory
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

-- load data from the temporary table, purchase_history_temp to customer table
-- Note that 2 of each customer segment has an email field at TemperioTesting@gmail.com
-- Please edit this to change the emails to the relevant email addresses
INSERT INTO `customer` (`customer_id`, `name`, `email`)
SELECT DISTINCT 
    `customer_id`, 
    'John' AS `name`, 
    IF(`customer_id` IN (2, 219, 251, 281, 338, 477), 'TemperioTesting@gmail.com', NULL) AS `email`
FROM `purchase_history_temp`;

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
  PRIMARY KEY (`purchase_id`),
  FOREIGN KEY (`customer_id`) REFERENCES `customer`(`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Create a new table to store the customer and their total total spending
DROP TABLE IF EXISTS `customer_spending_ranked`;
CREATE TABLE IF NOT EXISTS `customer_spending_ranked` (
  `customer_id` INT NOT NULL,
  `total_spending` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of user table to hold account personal details
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `role` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creation of NewsLetter table to hold all newsletter details
DROP TABLE IF EXISTS `newsletter`;
CREATE TABLE IF NOT EXISTS `newsletter` (
  `design_id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`design_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Insert data into the correct purchase_history final table with the proper date conversion
INSERT INTO `purchase_history` (
  `sale_date`, `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`
)
SELECT 
  STR_TO_DATE(`sale_date`, '%d/%m/%Y'),  -- Convert from DD/MM/YYYY to YYYY-MM-DD
  `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`
FROM `purchase_history_temp`;

-- Drop the temporary purchase_history table
DROP TABLE IF EXISTS `purchase_history_temp`;

-- Load the customer_spending_ranked table to get details of each customer and their total spending 
INSERT INTO `customer_spending_ranked` (`customer_id`, `total_spending`)
SELECT `customer_id`, SUM(`product_price`) AS `total_spending`
FROM `purchase_history`
GROUP BY `customer_id`;

-- Manually inserting user details into user table
-- Please edit this to add or remove user accounts
INSERT INTO `user` (`username`, `role`, `password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

-- Manually inserting to newsletter table
-- Please edit this to add or remove newsletter templates
INSERT INTO `newsletter` (`name`, `content`) VALUES
("high", "Dear [Customer Name],\n\nAs one of our most valued customers, we’re excited to present you with exclusive offers just for you! Based on your purchases, we’ve curated some premium deals we think you’ll love.\n\nExclusive Offers:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Buy two, get one free\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Special Deal: Complimentary gift with purchase of [Related Product]\n\nThank you for being a loyal customer! Shop now and enjoy your VIP offers at Timperio.\n\nWarm regards,\nMarketing team"),
("mid", "Dear [Customer Name],\n\nWe’ve curated something special for you! Based on your recent purchases, here are some personalized offers and recommendations we think you’ll love.\n\nTop Picks for You:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Buy one, get one Free\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Discount: Save [Discount Amount] when you buy with [Related Product]\n\nTake advantage of these personalized offers and discover more at Timperio. Shop now and enjoy the best deals tailored for you!\n\nWarm regards,\nMarketing team"),
("low", "Dear [Customer Name],\n\nWe miss you! To welcome you back, we’ve prepared some special offers just for you. Take advantage of these incredible discounts and explore more with us.\n\nSpecial Offers:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Get 50% off your second item\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Special Offer: Free shipping on your next order with [Promo Code]\n\nWe hope to see you soon! Don't miss out on these great deals from Timperio.\n\nWarm regards,\nMarketing team");


COMMIT;
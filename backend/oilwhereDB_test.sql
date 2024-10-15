DROP DATABASE IF EXISTS `oilwhereDB_test`;
CREATE DATABASE IF NOT EXISTS `oilwhereDB_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhereDB_test`;

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

INSERT INTO `purchase_history` (`purchase_id`, `sale_date`, `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`) VALUES
(1, '2019-11-22', 'Direct - B2B', 'Online - Website', 1, '437050', 'Standard Delivery', 'Chili oil', 100, 3, 5.9, 17.7),
(2, '2019-11-22', 'Consignment', 'Online - Website', 2, '126793', 'Standard Delivery', 'Organic Bio/Olio', 500, 2, 16.9, 33.8),
(3, '2019-11-22', 'Marketing', 'Online - Website', 2, '126793', 'Standard Delivery', 'Basil oil', 100, 1, 5.9, 5.9),
(4, '2020-05-22', 'Direct - B2C', 'Online - Website', 65, '101080', 'Standard Delivery', 'Truffle oil', 100, 1, 7.9, 6.32),
(5, '2019-12-17', 'Direct - B2C', 'Online - Website', 36, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8);

INSERT INTO `user` (`username`, `role`, `password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

INSERT INTO `newsletter` (`design_id`, `content`) VALUES
(1, 'marketingStuff.txt'),
(2, 'uglyDesign.txt');

COMMIT;
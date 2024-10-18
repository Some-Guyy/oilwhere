DROP DATABASE IF EXISTS `oilwhere_test`;
CREATE DATABASE IF NOT EXISTS `oilwhere_test` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oilwhere_test`;

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

-- Create a new table to store the customer by total spending
DROP TABLE IF EXISTS `customer_spending_ranked`;
CREATE TABLE IF NOT EXISTS `customer_spending_ranked` (
  `customer_id` INT NOT NULL,
  `total_spending` DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `purchase_history` (`purchase_id`, `sale_date`, `sale_type`, `digital`, `customer_id`, `zipcode`, `shipping_method`, `product`, `variant`, `quantity`, `price`, `product_price`) VALUES
(1, '2019-11-22', 'Direct - B2B', 'Online - Website', 1, '437050', 'Standard Delivery', 'Chili oil', 100, 3, 5.9, 17.7),
(2, '2019-11-22', 'Consignment', 'Online - Website', 2, '126793', 'Standard Delivery', 'Organic Bio/Olio', 500, 2, 16.9, 33.8),
(3, '2019-11-22', 'Marketing', 'Online - Website', 2, '126793', 'Standard Delivery', 'Basil oil', 100, 1, 5.9, 5.9),
(4, '2020-05-22', 'Direct - B2C', 'Online - Website', 65, '101080', 'Standard Delivery', 'Truffle oil', 100, 1, 7.9, 6.32),
(5, '2019-12-17', 'Direct - B2C', 'Online - Website', 36, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(6, '2019-12-17', 'Direct - B2C', 'Online - Website', 3, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(7, '2019-12-17', 'Direct - B2C', 'Online - Website', 4, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(8, '2019-12-17', 'Direct - B2C', 'Online - Website', 5, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(9, '2019-12-17', 'Direct - B2C', 'Online - Website', 6, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(10, '2019-12-17', 'Direct - B2C', 'Online - Website', 7, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8),
(11, '2019-12-17', 'Direct - B2C', 'Online - Website', 8, '608531', 'Standard Delivery', 'Limited Edition Gift box 3x100ml', 2020, 2, 19.9, 39.8);

INSERT INTO `customer_spending_ranked` (`customer_id`, `total_spending`)
SELECT `customer_id`, SUM(`product_price`) AS `total_spending`
FROM `purchase_history`
GROUP BY `customer_id`;


INSERT INTO `user` (`username`, `role`, `password`) VALUES
('marketing', 'MARKETING', '123'),
('sales', 'SALES', '123'),
('admin', 'ADMIN', '123');

-- Manually inserting to newsletter table
INSERT INTO `newsletter` (`design_id`, `content`) VALUES
(1, "Dear [Customer Name],\n\nAs one of our most valued customers, we’re excited to present you with exclusive offers just for you! Based on your purchases, we’ve curated some premium deals we think you’ll love.\n\nExclusive Offers:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Buy two, get one free\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Special Deal: Complimentary gift with purchase of [Related Product]\n\nThank you for being a loyal customer! Shop now and enjoy your VIP offers at Timperio.\n\nWarm regards,\nMarketing team"),
(2, "Dear [Customer Name],\n\nWe’ve curated something special for you! Based on your recent purchases, here are some personalized offers and recommendations we think you’ll love.\n\nTop Picks for You:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Buy one, get one Free\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Discount: Save [Discount Amount] when you buy with [Related Product]\n\nTake advantage of these personalized offers and discover more at Timperio. Shop now and enjoy the best deals tailored for you!\n\nWarm regards,\nMarketing team"),
(3, "Dear [Customer Name],\n\nWe miss you! To welcome you back, we’ve prepared some special offers just for you. Take advantage of these incredible discounts and explore more with us.\n\nSpecial Offers:\n1. [Product Name 1]\n   - Price: $[Product Price]\n   - Discount: [Discount Percentage]% off with code [Promo Code]\n2. [Product Name 2]\n   - Price: $[Product Price]\n   - Discount: Get 50% off your second item\n3. [Product Name 3]\n   - Price: $[Product Price]\n   - Special Offer: Free shipping on your next order with [Promo Code]\n\nWe hope to see you soon! Don't miss out on these great deals from Timperio.\n\nWarm regards,\nMarketing team");


COMMIT;
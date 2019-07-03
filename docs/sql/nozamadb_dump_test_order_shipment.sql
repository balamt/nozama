-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.25-0ubuntu0.18.04.2 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for nozamadb
CREATE DATABASE IF NOT EXISTS `nozamadb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `nozamadb`;

-- Dumping structure for table nozamadb.addresses
CREATE TABLE IF NOT EXISTS `addresses` (
  `addressid` bigint(20) NOT NULL,
  `address1` varchar(100) DEFAULT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `pincode` varchar(10) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `street` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`addressid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.addresses: ~2 rows (approximately)
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT IGNORE INTO `addresses` (`addressid`, `address1`, `address2`, `city`, `country`, `pincode`, `state`, `street`) VALUES
	(20100, 'RK Appartments', 'Guduvanchery', 'Chennai', 'India', '603009', 'Tamil Nadu', 'Kuppu sawmy Street'),
	(20101, 'Venkatesware Residency', 'Doddathogur', 'Bengaluru', 'India', '560100', 'Karnataka', 'Celebrity Layout');
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;

-- Dumping structure for table nozamadb.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FKpu4bcbluhsxagirmbdn7dilm5` (`product_id`),
  CONSTRAINT `FKpu4bcbluhsxagirmbdn7dilm5` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2394 DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.cart: ~4 rows (approximately)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT IGNORE INTO `cart` (`item_id`, `price`, `quantity`, `product_id`) VALUES
	(2390, 45, 10, 89002),
	(2391, 120, 1, 89000),
	(2392, 450, 100, 89002),
	(2393, 120, 1, 89000);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;

-- Dumping structure for table nozamadb.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.hibernate_sequence: ~6 rows (approximately)
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT IGNORE INTO `hibernate_sequence` (`next_val`) VALUES
	(3),
	(3),
	(3),
	(3),
	(3),
	(3);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table nozamadb.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` bigint(20) NOT NULL,
  `discount_applied` bit(1) DEFAULT NULL,
  `discount_percent` double DEFAULT NULL,
  `final_price` double NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `total_price` double NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.orders: ~3 rows (approximately)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT IGNORE INTO `orders` (`order_id`, `discount_applied`, `discount_percent`, `final_price`, `order_status`, `total_price`, `user_id`) VALUES
	(2, b'0', 0, 570, 'SHIP_CREATED', 570, 40102),
	(19200, b'1', 10, 148.5, 'NEW', 165, 40102);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;

-- Dumping structure for table nozamadb.orders_items
CREATE TABLE IF NOT EXISTS `orders_items` (
  `order_order_id` bigint(20) NOT NULL,
  `items_item_id` bigint(20) NOT NULL,
  KEY `FK95oa9at8usr6a08mq3o1vluoh` (`items_item_id`),
  KEY `FK4tfk575hrsnk9dra65jl15myg` (`order_order_id`),
  CONSTRAINT `FK4tfk575hrsnk9dra65jl15myg` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK95oa9at8usr6a08mq3o1vluoh` FOREIGN KEY (`items_item_id`) REFERENCES `cart` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.orders_items: ~4 rows (approximately)
/*!40000 ALTER TABLE `orders_items` DISABLE KEYS */;
INSERT IGNORE INTO `orders_items` (`order_order_id`, `items_item_id`) VALUES
	(19200, 2390),
	(19200, 2391),
	(2, 2392),
	(2, 2393);
/*!40000 ALTER TABLE `orders_items` ENABLE KEYS */;

-- Dumping structure for table nozamadb.products
CREATE TABLE IF NOT EXISTS `products` (
  `product_id` bigint(20) NOT NULL,
  `category` varchar(255) NOT NULL,
  `price_per_item` double DEFAULT NULL,
  `product_code` varchar(255) NOT NULL,
  `product_img` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) NOT NULL,
  `stock_quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.products: ~3 rows (approximately)
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT IGNORE INTO `products` (`product_id`, `category`, `price_per_item`, `product_code`, `product_img`, `product_name`, `stock_quantity`) VALUES
	(89000, 'TOY', 120, 'TOY 101', NULL, 'Elephant Soft Toy', 20),
	(89001, 'FOOD', 12.89, 'FOO101', NULL, 'Chocolate', 120),
	(89002, 'FOOD', 4.5, 'FOO 102', NULL, 'Eggs', 2000);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;

-- Dumping structure for table nozamadb.tracking
CREATE TABLE IF NOT EXISTS `tracking` (
  `track_id` bigint(20) NOT NULL,
  `created_on` datetime DEFAULT NULL,
  `tracking_status` int(11) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `warehouse_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`track_id`),
  KEY `FKi98mmtn23yut7syskwu0m1b9w` (`user_id`),
  KEY `FKf46j6c8nsgw664aj5apfk46f5` (`order_id`),
  KEY `FKdj0e4s2398c37ho7y1qliwpwg` (`warehouse_id`),
  CONSTRAINT `FKdj0e4s2398c37ho7y1qliwpwg` FOREIGN KEY (`warehouse_id`) REFERENCES `warehouses` (`warehouseid`),
  CONSTRAINT `FKf46j6c8nsgw664aj5apfk46f5` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FKi98mmtn23yut7syskwu0m1b9w` FOREIGN KEY (`user_id`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.tracking: ~1 rows (approximately)
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
INSERT IGNORE INTO `tracking` (`track_id`, `created_on`, `tracking_status`, `modified_on`, `user_id`, `order_id`, `warehouse_id`) VALUES
	(345120, '2019-05-01 15:09:49', 3, NULL, 40102, 2, NULL);
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;

-- Dumping structure for table nozamadb.users
CREATE TABLE IF NOT EXISTS `users` (
  `userid` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(120) NOT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_type` varchar(255) NOT NULL,
  `addressid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `FK2gx45jn66hwaen903copo4ckm` (`addressid`),
  CONSTRAINT `FK2gx45jn66hwaen903copo4ckm` FOREIGN KEY (`addressid`) REFERENCES `addresses` (`addressid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.users: ~3 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT IGNORE INTO `users` (`userid`, `email`, `fullname`, `gender`, `mobile`, `password`, `user_type`, `addressid`) VALUES
	(40100, 'ram.kumar@email.com', 'Ram Kumar', 'MALE', '9898998989', 'password', 'BASIC', 20100),
	(40101, 'BOT101@nozama.in', 'BOT 101', NULL, '9090900000', 'password', 'BOT', NULL),
	(40102, 'kavery.v@email.com', 'Kavery L', 'FEMALE', '9900900990', 'password', 'PRIME', 20101);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table nozamadb.warehouses
CREATE TABLE IF NOT EXISTS `warehouses` (
  `warehouseid` bigint(20) NOT NULL,
  `warehouse_city` varchar(255) NOT NULL,
  `warehoust_pincode` varchar(255) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`warehouseid`),
  KEY `FK6761pfh1jhx6fm22eo9moqd1t` (`userid`),
  CONSTRAINT `FK6761pfh1jhx6fm22eo9moqd1t` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.warehouses: ~0 rows (approximately)
/*!40000 ALTER TABLE `warehouses` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouses` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

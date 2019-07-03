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

-- Dumping structure for table nozamadb.orders_products
CREATE TABLE IF NOT EXISTS `orders_products` (
  `order_order_id` bigint(20) NOT NULL,
  `products_product_id` bigint(20) NOT NULL,
  KEY `FKn8r554lnpi65snj4306u5kbpq` (`products_product_id`),
  KEY `FKnglu8plo4wkypn1gls3oqjsok` (`order_order_id`),
  CONSTRAINT `FKn8r554lnpi65snj4306u5kbpq` FOREIGN KEY (`products_product_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `FKnglu8plo4wkypn1gls3oqjsok` FOREIGN KEY (`order_order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.orders_products: ~2 rows (approximately)
DELETE FROM `orders_products`;
/*!40000 ALTER TABLE `orders_products` DISABLE KEYS */;
INSERT INTO `orders_products` (`order_order_id`, `products_product_id`) VALUES
	(19200, 8000),
	(19200, 8001);
/*!40000 ALTER TABLE `orders_products` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

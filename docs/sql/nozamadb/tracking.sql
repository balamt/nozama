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
DELETE FROM `tracking`;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
INSERT INTO `tracking` (`track_id`, `created_on`, `tracking_status`, `modified_on`, `user_id`, `order_id`, `warehouse_id`) VALUES
	(45001, '2019-04-29 23:13:16', 6, '2019-04-29 23:13:23', 1291, 19200, NULL);
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

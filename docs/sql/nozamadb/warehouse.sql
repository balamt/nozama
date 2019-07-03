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

-- Dumping structure for table nozamadb.warehouse
CREATE TABLE IF NOT EXISTS `warehouse` (
  `warehouseid` bigint(20) NOT NULL AUTO_INCREMENT,
  `warehouse_city` varchar(255) NOT NULL,
  `warehoust_pincode` varchar(255) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`warehouseid`),
  KEY `FK24e61lyaxi2pj3rkcojrq81i5` (`userid`),
  CONSTRAINT `FK24e61lyaxi2pj3rkcojrq81i5` FOREIGN KEY (`userid`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.warehouse: ~2 rows (approximately)
DELETE FROM `warehouse`;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
INSERT INTO `warehouse` (`warehouseid`, `warehouse_city`, `warehoust_pincode`, `userid`) VALUES
	(1001, 'CHENNAI', '600028', 1291),
	(1002, 'BENGALURU', '506100', 1292),
	(1003, 'GUJARAT', '395003', 1293);
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

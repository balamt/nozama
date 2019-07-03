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

-- Dumping structure for table nozamadb.user
CREATE TABLE IF NOT EXISTS `user` (
  `userid` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(120) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `usertype` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.user: ~9 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`userid`, `email`, `fullname`, `gender`, `mobile`, `password`, `usertype`) VALUES
	(3, 'july.thomas@email.com', 'July Thomas', 1, '9840098400', '$2a$10$Mv40EbUajcaPJRF18vIywuRXVjyVU92nm6U8RoTl5eu8ThTRjkyDe', 0),
	(4, 'ram.varma@email.com', 'Ram Varma', 0, '9898998989', '$2a$10$tJSBQLHJPnzHotXwS70cQuSbWqft9PXwqHwl.bKQ5P2Zfaoi0ldSC', 1),
	(5, 'fathima.ismail@jktraders.in', 'JK Traders', 1, '8899088990', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', 2),
	(101, 'BOT101@nozama.in', 'BOT101', 0, '0', '$2a$10$zkQRn0PdXCREab4kcJATpudrw2pk0yOG65n4vJINKzSQHbockpXyG', 3),
	(201, 'ADMIN101@nozama.in', 'ADMIN101', 1, '9898989898', '$2a$10$M6NAhm0cM7q3CVLiCfH39uty8CVbcpWe9nxrsNqdNacrLGoKQqmvK', 4),
	(1291, 'chennai.warehouse@nozama.in', 'CHENNAI_WAREHOUSE', NULL, '4490939203', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', 2),
	(1292, 'noida.warehouse@nozama.in', 'NOIDA_WAREHOUSE', NULL, '9829384999', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', 2),
	(1293, 'bengaluru.warehouse@nozama.in', 'BENGALURU_WAREHOUSE', NULL, '9829384990', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', 2),
	(1294, 'gujarat.warehouse@nozama.in', 'GUJARAT_WAREHOUSE', NULL, '9829384991', '$2a$10$048r/t.llB7cfhAwaTij7.kSNL5AEt/qStDNI/ihSBjUWvjyjQhh6', 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

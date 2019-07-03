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

-- Dumping structure for table nozamadb.users


CREATE TABLE `nozamadb`.`users` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(120) NOT NULL,
  `email` VARCHAR(120) NOT NULL,
  `mobile` VARCHAR(10) NOT NULL,
  `password` VARCHAR(250) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`userid`));
CREATE TABLE IF NOT EXISTS `users` (
  `userid` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(120) NOT NULL,
  `gender` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `usertype` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table nozamadb.users: ~2 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userid`, `email`, `fullname`, `gender`, `mobile`, `usertype`) VALUES
	(1291, 'BOT101@email.com', 'BOT 1291', NULL, NULL, 2),
	(5600, 'Ram.kumar@email.com', 'Ram Kumar', 0, '9898998989', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

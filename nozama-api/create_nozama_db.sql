CREATE DATABASE `nozamadb` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE USER 'nozama_dba'@'%' IDENTIFIED BY 'nozama19';
--GRANT ALL ON nozamadb.* TO 'nozama_dba'@'%' IDENTIFIED BY 'nozama19';
GRANT ALL PRIVILEGES ON nozamadb.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
CREATE DATABASE `nozama_cart_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_delivery_schedule_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_orderinfo_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_order_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_products_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_shipment_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_user_auth_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_user_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_wallet_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE DATABASE `nozama_address_db` /*!40100 DEFAULT CHARACTER SET utf8 */;



GRANT ALL PRIVILEGES ON nozama_cart_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_delivery_schedule_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_orderinfo_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_order_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_products_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_shipment_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_user_auth_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_wallet_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_user_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
GRANT ALL PRIVILEGES ON nozama_address_db.* TO 'nozama_dba'@'%' with grant option;
FLUSH PRIVILEGES;
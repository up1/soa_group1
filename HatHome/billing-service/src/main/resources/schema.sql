DROP TABLE IF EXISTS BILLS;

CREATE TABLE `bill`.`bills` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address` VARCHAR(300) NOT NULL,
  `date` VARCHAR(20) NOT NULL,
  `cart_id` INT NOT NULL,
  `cart_cost` DOUBLE NOT NULL,
  `shipping_cost` DOUBLE NOT NULL,
  `total` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS BILLS;
DROP TABLE IF EXISTS ITEMS;

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

CREATE TABLE `bill`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bill_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  PRIMARY KEY (`id`));


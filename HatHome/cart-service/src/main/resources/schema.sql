DROP TABLE if EXISTS cart;

CREATE TABLE `cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` LONG NOT NULL,
  `product_id` LONG NOT NULL,
  `amount` INTEGER(3) NOT NULL DEFAULT 1,
  `status` VARCHAR(45) NOT NULL DEFAULT 'unpaid',
  PRIMARY KEY (`id`)
  );
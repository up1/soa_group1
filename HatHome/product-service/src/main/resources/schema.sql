DROP TABLE IF EXISTS product;

CREATE TABLE `hathome`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `detail` VARCHAR(700) NOT NULL,
  `price` DECIMAL NOT NULL,
  `color` VARCHAR(20) NOT NULL,
  `brand` VARCHAR(100) NOT NULL,
  `amount` INT NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));
DROP TABLE IF EXISTS bills;
DROP TABLE IF EXISTS items;

CREATE TABLE `bill`.`bills` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `address` VARCHAR(300) NOT NULL,
  `date` VARCHAR(30) NOT NULL,
  `cartPrice` DOUBLE NOT NULL,
  `shippingCost` DOUBLE NOT NULL,
  `total` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `bill`.`items` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `billId` INT NOT NULL,
  `productId` INT NOT NULL,
  `amount` INT NOT NULL,
  PRIMARY KEY (`id`));


DROP TABLE IF EXISTS wishlist;

CREATE TABLE wishlist (
	`wishlistId` INT NOT NULL AUTO_INCREMENT,
	`userId` INT NOT NULL,
	`productId` INT NOT NULL,
	PRIMARY KEY (`wishlistId`));
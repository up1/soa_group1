DROP TABLE IF EXISTS wishlist;

CREATE TABLE WISHLIST (
	`wishlist_id` INT NOT NULL AUTO_INCREMENT,
	`user_id` INT NOT NULL,
	`product_id` INT NOT NULL,
	PRIMARY KEY (`wishlist_id`));
DROP TABLE IF EXISTS PRODUCT;

CREATE TABLE 'hathome'.'wishlist' (
	'wishlist_id' INT NOT NULL AUTO_INCREMENT,
	'user_id' INT NOT NULL,
	'product_id INT NOT NULL',
	PRIMARY KEY ('wishlist_id'));
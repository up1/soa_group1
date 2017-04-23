INSERT INTO `bill`.`bills` VALUES (1,1,'99 ถนนไทไท ต.ทดลอง อ.เมือง จ.สมุทรสงคราม 75000','01-01-2017',333, 50, 383);
INSERT INTO `bill`.`bills` VALUES (2,2,'33 ถนนลาดกระบัง เขตลาดกระบัง กรุงเทพ 33333','13-09-1016',25.75,0,25.75);

INSERT INTO `bill`.`items` (`bill_id`, `product_id`, `amount`) VALUES ('1', '2', 2);
INSERT INTO `bill`.`items` (`bill_id`, `product_id`, `amount`) VALUES ('1', '3', 2);
INSERT INTO `bill`.`items` (`bill_id`, `product_id`, `amount`) VALUES ('1', '4', 2);
INSERT INTO `bill`.`items` (`bill_id`, `product_id`, `amount`) VALUES ('2', '1', 2);
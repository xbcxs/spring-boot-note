CREATE TABLE `flyway_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(100) DEFAULT NULL COMMENT '登录名',
  `name` varchar(45) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8
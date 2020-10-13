CREATE TABLE `mybatis_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY  KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
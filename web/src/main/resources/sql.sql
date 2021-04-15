DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255)     DEFAULT NULL,
    `password`  varchar(255)     DEFAULT NULL,
    `age`       int(11)          DEFAULT NULL,
    `version`   int(10) NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
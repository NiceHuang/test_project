CREATE TABLE `data_portral_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(12) NOT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为停用，1为启用',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator` varchar(12) DEFAULT NULL,
  `mtime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modifier` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_index` (`email`),
  KEY `union_index` (`username`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


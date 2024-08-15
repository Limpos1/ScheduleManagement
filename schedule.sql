CREATE TABLE schedule (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `task` varchar(200) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `managername` varchar(20) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `fix_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
);
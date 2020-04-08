DROP DATABASE  IF EXISTS `wrss`;

CREATE DATABASE  IF NOT EXISTS `wrss`;
USE `wrss`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` char(50) NOT NULL,
  `encryptedPassword` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_USER_idx` (`user_id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `summer_trip`;

CREATE TABLE `summer_trip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` char(50) NOT NULL,
  `number` char(50) NOT NULL,
  `shirt` varchar(50) NOT NULL,
  `transport` char(50) NOT NULL,
  
  PRIMARY KEY (`id`),
  
  KEY `FK_RAJD_idx` (`user_id`),
  CONSTRAINT `FK_RAJD` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `paid`;

CREATE TABLE `paid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `summer_trip_id` int(11) DEFAULT NULL,
  `paid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  
  KEY `FK_PAID_idx` (`summer_trip_id`),
  CONSTRAINT `FK_PAID` FOREIGN KEY (`summer_trip_id`) 
  REFERENCES `summer_trip` (`id`) 
  
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

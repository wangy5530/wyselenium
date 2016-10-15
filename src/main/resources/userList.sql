/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.9-MariaDB 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `userlist` (
	`account` varchar (60),
	`realname` varchar (3000),
	`gender` char (3),
	`dept` int (11),
	`role` int (11),
	`password` varchar (300),
	`email` varchar (3000)
); 
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('user1792102','John Smith','m','0','2','123456','@demo.com\r');
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('0user1792103','Lucy Sally','f','1','3','123456','@demo.com\r');
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('user1792104','Lily Sally','f','2','4','123456','@demo.com\r');
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('user1792105','John Smith','m','2','5','123456','@demo.com\r');
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('user1792106','Lucy Sally','f','1','6','123456','@demo.com\r');
insert into `userlist` (`account`, `realname`, `gender`, `dept`, `role`, `password`, `email`) values('user1792101','Lily Sally','f','0','7','123456','@demo.com');

/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.9-MariaDB 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `userlogin` (
	`account` varchar (300),
	`password` varchar (300)
); 
insert into `userlogin` (`account`, `password`) values('admin','admin');
insert into `userlogin` (`account`, `password`) values('demo1','123456');
insert into `userlogin` (`account`, `password`) values('demo3','123456');

drop database if exists sample_dao;

create database sample_dao character set utf8;

use sample_dao;

create table FoodItem (
	foodItemId bigint auto_increment primary key,
	name_ varchar(75) null,
	points integer null
);

insert into FoodItem (name_, points) values ('Hamburger', 5);
insert into FoodItem (name_, points) values ('French fries', 3);
insert into FoodItem (name_, points) values ('Milk Shake', 4);
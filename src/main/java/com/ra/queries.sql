drop database if exists md05_db;
create database md05_db;
use md05_db;
create table categories
(
    id bigint primary key auto_increment,
    name varchar(255),
    description text,
    status bit(1)
);
create table products
(
    id   bigint primary key auto_increment,
    name varchar(255),
    price decimal(10,0),
    description text,
    status bit(1),
    category_id bigint,
    foreign key (category_id) references categories(id)
);



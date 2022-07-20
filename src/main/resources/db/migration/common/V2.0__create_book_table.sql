drop table if exists book;

create table book (
    id bigint not null AUTO_INCREMENT,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    primary key (id)
) engine=InnoDB;
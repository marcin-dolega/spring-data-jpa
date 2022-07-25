drop table if exists author;

create table author (
    id bigint not null AUTO_INCREMENT primary key,
    first_name varchar(255),
    last_name varchar(255)
) engine = InnoDB;
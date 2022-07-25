drop table if exists book cascade;

create table book (
    id bigint not null AUTO_INCREMENT primary key,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    author_id BIGINT
) engine = InnoDB;
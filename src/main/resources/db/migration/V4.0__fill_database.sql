insert into author (first_name, last_name) values ('Brudas', 'Dog');

insert into book (title, isbn, publisher, author_id) values (
    'Dirty Dog', '88', 'Szajba', (select id from author where first_name = 'Brudas' and last_name = 'Dog')
    );

insert into book (title, isbn, publisher, author_id) values (
    'Dirty Dog 2', '777', 'Szajba', (select id from author where first_name = 'Brudas' and last_name = 'Dog')
    );

insert into book (title, isbn, publisher, author_id) values (
    'Dirty Dog 3', '666', 'Szajba', (select id from author where first_name = 'Brudas' and last_name = 'Dog')
    );

insert into author (first_name, last_name) values ('Tiga', 'Cat');

insert into book (title, isbn, publisher, author_id) values (
    'Nice Cat', '1', 'Kido', (select id from author where first_name = 'Tiga' and last_name = 'Cat')
    );

insert into author (first_name, last_name) values ('Martyn', 'Groch');

insert into book (title, isbn, publisher, author_id) values (
    'Nice Cat', '1', 'Kido', (select id from author where first_name = 'Martyn' and last_name = 'Groch')
    );
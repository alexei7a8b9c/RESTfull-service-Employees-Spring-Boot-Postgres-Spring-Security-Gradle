drop table if exists authorities;
drop table if exists users;

-- Создание таблицы пользователей
create table users (
                       username varchar(50) primary key,
                       password varchar(100) not null,
                       enabled boolean not null
);

-- Создание таблицы ролей
create table authorities (
                             username varchar(50) not null,
                             authority varchar(50) not null,
                             constraint fk_authorities_users
                                 foreign key (username) references users(username)
);

-- Уникальность роли для пользователя
create unique index ix_auth_username on authorities (username, authority);

-- USER
insert into users (username, password, enabled)
values ('user', 'password', true);
-- альтернативно с префиксом:
-- values ('user', '{noop}password', true);

insert into authorities (username, authority)
values ('user', 'ROLE_USER');

-- ADMIN
insert into users (username, password, enabled)
values ('admin', 'password', true);
-- альтернативно с префиксом:
-- values ('admin', '{noop}password', true);

insert into authorities (username, authority)
values ('admin', 'ROLE_ADMIN');
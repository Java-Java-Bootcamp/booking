-- create table if not exists person
-- (
--     id            bigint NOT NULL PRIMARY KEY,
--     name          varchar(20)
-- );
--
-- create table if not exists booking
-- (
--     id            bigint NOT NULL PRIMARY KEY,
--     person_id bigint,
--     foreign key (person_id) references person (id)
-- );
--
-- create table if not exists organization
-- (
--     id int NOT NULL PRIMARY KEY,
--     name varchar(20),
--     schedule varchar(50),
--     average_check double,
--     rating double
-- );
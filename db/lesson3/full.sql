BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial, name varchar(255), PRIMARY KEY (id));
INSERT INTO buyers (name) VALUES
('Alex'),
('Andrey'),
('Alice'),
('Bob'),
('Galina'),
('Diana'),
('Denis'),
('Vlad');

DROP TABLE IF EXISTS lots CASCADE;
CREATE TABLE lots (id bigserial, name varchar(255), PRIMARY KEY (id));
INSERT INTO lots (name) VALUES
('Trixie'),
('Patent'),
('Yankee'),
('Handicap');

COMMIT;
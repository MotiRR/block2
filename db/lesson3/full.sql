BEGIN;

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users (id bigserial, name varchar(255), PRIMARY KEY (id));
INSERT INTO users (name) VALUES
('Alex'),
('Andrey'),
('Alice'),
('Bob'),
('Galina'),
('Diana'),
('Denis'),
('Vlad');

DROP TABLE IF EXISTS bets CASCADE;
CREATE TABLE bets (id bigserial, name varchar(255), rate DECIMAL, version bigint, user_id bigint REFERENCES users (id), PRIMARY KEY (id));
INSERT INTO bets (name, rate, version) VALUES
('Trixie', 0, 0),
('Patent', 0, 0),
('Yankee', 0, 0),
('Handicap', 0, 0);

COMMIT;
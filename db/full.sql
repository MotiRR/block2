BEGIN;

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial, name varchar(255), PRIMARY KEY (id));
INSERT INTO buyers (name) VALUES
('Alex'),
('Andrey'),
('Alice'),
('Bob'),
('Galina'),
('Diana'),
('Vlad');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial, name varchar(255), PRIMARY KEY (id));
INSERT INTO products (name) VALUES
('toy'),
('ball'),
('pen'),
('pencil'),
('telephone'),
('car'),
('telly'),
('pillow'),
('clothes');

DROP TABLE IF EXISTS buyers_products CASCADE;
CREATE TABLE buyers_products (buyer_id bigint REFERENCES buyers (id), product_id bigint REFERENCES products (id), date_buy TIMESTAMP);
INSERT INTO buyers_products (buyer_id, product_id, date_buy) VALUES
(1, 1, '2020-07-12'),
(1, 2, '2020-07-12'),
(2, 6, '2020-07-12'),
(3, 3, '2020-07-12'),
(3, 4, '2020-07-12'),
(4, 9, '2020-07-12'),
(4, 6, '2020-07-12'),
(5, 9, '2020-07-12'),
(6, 7, '2020-07-12'),
(7, 5, '2020-07-12'),
(7, 6, '2020-07-12');

DROP TABLE IF EXISTS costs CASCADE;
CREATE TABLE costs (product_id bigint REFERENCES products (id), date_cost TIMESTAMP, cost NUMERIC(9,3));
INSERT INTO costs (product_id, date_cost, cost) VALUES
(1, '2020-07-12 12:45:44', 100.12),
(1, '2020-07-12 14:14:14', 432.20),
(2, '2020-07-12', 22.22),
(2, '2020-07-12', 123.321);

COMMIT;
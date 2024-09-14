CREATE TABLE IF NOT EXISTS customers (
    id SERIAL PRIMARY KEY,
    email VARCHAR (255) NOT NULL UNIQUE,
    password VARCHAR (255) NOT NULL
);

ALTER TABLE
    pets DROP CONSTRAINT fk_user;

ALTER TABLE
    pets RENAME COLUMN user_id TO customer_id;

ALTER TABLE
    pets
ADD
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE;

DROP TABLE users;
CREATE TABLE IF NOT EXISTS pets
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(255),
    type    VARCHAR(255),
    user_id INTEGER,
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
            ON DELETE CASCADE
);
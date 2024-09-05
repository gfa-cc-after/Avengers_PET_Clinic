CREATE TABLE IF NOT EXISTS email_verifications
(
    verification_id VARCHAR(50),
    email VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (verification_id)
);
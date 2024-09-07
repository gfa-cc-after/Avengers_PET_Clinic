CREATE TABLE IF NOT EXISTS email_verifications
(
    verification_id VARCHAR (50),
    email VARCHAR (255),
    PRIMARY KEY (verification_id)
)
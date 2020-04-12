CREATE TABLE trainings
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT REFERENCES users (id),
    name          VARCHAR(255),
    is_complete   BOOLEAN,
    creation_date TIMESTAMP,
    date          TIMESTAMP
);
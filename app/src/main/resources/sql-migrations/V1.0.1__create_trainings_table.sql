CREATE TABLE trainings
(
    id            BIGSERIAL PRIMARY KEY,
    user_id       BIGINT REFERENCES users (id),
    name          VARCHAR(255),
    completed     BOOLEAN,
    creation_time TIMESTAMP
);
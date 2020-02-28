CREATE TABLE exercise_approaches
(
    id                BIGSERIAL PRIMARY KEY,
    exercise_id       BIGINT REFERENCES exercises (id),
    weight            FLOAT,
    repetitions_count INT
);
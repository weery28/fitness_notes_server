CREATE TABLE sets
(
    id          BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT REFERENCES exercises (id),
    weight      FLOAT,
    reps_count  INT,
    index       INT
);
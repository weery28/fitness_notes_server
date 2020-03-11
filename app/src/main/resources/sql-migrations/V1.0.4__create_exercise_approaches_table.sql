CREATE TABLE exercise_approaches
(
    id          BIGSERIAL PRIMARY KEY,
    exercise_id BIGINT REFERENCES exercises (id),
    weight      FLOAT,
    reps_count  INT
);
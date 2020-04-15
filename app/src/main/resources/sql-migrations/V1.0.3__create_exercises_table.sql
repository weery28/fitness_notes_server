CREATE TABLE exercises
(
    id                      BIGSERIAL PRIMARY KEY,
    exercise_description_id BIGINT REFERENCES exercise_descriptions (id),
    training_id             BIGINT REFERENCES trainings (id),
    sets_count              INT,
    weight                  FLOAT,
    reps_count              INT,
    index                   INT
);
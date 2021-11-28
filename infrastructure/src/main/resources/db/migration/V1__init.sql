CREATE TABLE IF NOT EXISTS MOVIES
(
    id                      SERIAL PRIMARY KEY,
    name                    VARCHAR NOT NULL UNIQUE,
    rating                  DECIMAL(2,1) CONSTRAINT chk_ratings CHECK (rating >= 0 AND rating <= 5) DEFAULT 0 NOT NULL,
    number_of_votes         INT NOT NULL DEFAULT 1,
    runtime                 INT, /* In minutes */
    imdb_id                 VARCHAR(20),
    release_date            DATE
);


CREATE TABLE IF NOT EXISTS MOVIES_TIMES
(
    movie_id                SERIAL,
    show_time               VARCHAR(5), /* 24 Hour system Format */
    prize                   DECIMAL,
    PRIMARY KEY (movie_id, show_time),
    FOREIGN KEY (movie_id) REFERENCES MOVIES
    ON DELETE CASCADE
);

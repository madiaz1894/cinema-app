CREATE TABLE IF NOT EXISTS MOVIES
(
    imdb_id                 VARCHAR(20) PRIMARY KEY,
    rating                  DECIMAL(2,1) CONSTRAINT chk_ratings CHECK (rating >= 0 AND rating <= 5) DEFAULT 5.0 NOT NULL,
    number_of_votes         INT NOT NULL DEFAULT 0
);


CREATE TABLE IF NOT EXISTS MOVIES_TIMES
(
    movie_id                VARCHAR(20),
    day                     VARCHAR,
    show_time               VARCHAR(5), /* 24 Hour system Format */
    price                   DECIMAL,
    PRIMARY KEY (movie_id, day, show_time),
    FOREIGN KEY (movie_id) REFERENCES MOVIES
    ON DELETE CASCADE
);

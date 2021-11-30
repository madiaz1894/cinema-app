package com.marcos.projects.repositories.queries

internal const val FIND_MOVIE_TIMES_BY_ID = """
    SELECT * FROM movie_times
    WHERE movie_id = :id;
"""
internal const val FIND_MOVIE_ID_NAME = """
    SELECT * FROM movies
    WHERE imdb_id = :imdbId;
"""

internal const val INSERT_MOVIE = """
    INSERT INTO movies (imdb_id, rating) 
     VALUES (:imdbId, :rating);
"""

internal const val INSERT_MOVIE_TIMES_AND_PRIZES = """
    INSERT INTO  movies_times(movie_id, day, show_time, price) 
    VALUES (:movieId, :day, :showTime, :price) ON CONFLICT (movie_id, day, show_time) DO UPDATE SET 
    price = :price WHERE movies_times.movie_id = :movieId AND movies_times.day = :day AND movies_times.show_time = :showTime ;
"""

internal const val UPDATE_RATING_FOR_MOVIE = """
    UPDATE movies SET rating = :rating , number_of_votes = :numberOfVotes WHERE imdb_id = :movieId
"""

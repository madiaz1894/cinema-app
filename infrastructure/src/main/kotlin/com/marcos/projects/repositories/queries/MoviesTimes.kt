package com.marcos.projects.repositories.queries

internal const val FIND_MOVIE_TIMES_BY_ID = """
    SELECT * FROM movie_times
    WHERE id = :id;
"""
internal const val FIND_MOVIE_ID_NAME = """
    SELECT id, name FROM movies
    WHERE name ILIKE :name;
"""

package com.marcos.projects.repositories.mappers

import com.marcos.projects.http.dto.MovieBody
import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.http.dto.MovieTimesResponse
import java.sql.ResultSet

internal fun movieTimesMapper(findTemplateRequest: (String) -> List<MovieScheduleResponse>): (ResultSet, Int) -> MovieTimesResponse =
    { rs, _ ->
        val id = rs.getString("imdb_id")
        val movie = MovieBody(
            id,
            rs.getDouble("rating"),
            rs.getInt("number_of_votes")
        )
        MovieTimesResponse(
            movie = movie,
            times = findTemplateRequest(id)
        )
    }

internal fun movieMapper(): (ResultSet, Int) -> MovieBody =
    { rs, _ ->
       MovieBody(
            rs.getString("imdb_id"),
            rs.getDouble("rating"),
            rs.getInt("number_of_votes")
        )
    }





internal fun movieScheduleResponseMapper(): (ResultSet, Int) -> MovieScheduleResponse =
    { rs, _ ->
        MovieScheduleResponse(
            showTime = rs.getString("show_time"),
            day = rs.getString("day"),
            price = rs.getDouble("price")
        )
    }
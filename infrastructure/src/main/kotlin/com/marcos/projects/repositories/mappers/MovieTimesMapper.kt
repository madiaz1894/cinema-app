package com.marcos.projects.repositories.mappers

import com.marcos.projects.http.dto.MovieScheduleResponse
import com.marcos.projects.http.dto.MovieTimesResponse
import java.sql.ResultSet

internal fun movieTimesMapper(findTemplateRequest: (Long) -> List<MovieScheduleResponse>): (ResultSet, Int) -> MovieTimesResponse =
    { rs, _ ->
        val id = rs.getLong("id")
        MovieTimesResponse(
            id = id,
            movieName = rs.getString("movieName"),
            times = findTemplateRequest(id)
        )
    }



internal fun movieScheduleResponseMapper(): (ResultSet, Int) -> MovieScheduleResponse =
    { rs, _ ->
        MovieScheduleResponse(
            showTime = rs.getString("method"),
            price = rs.getString("price")
        )
    }
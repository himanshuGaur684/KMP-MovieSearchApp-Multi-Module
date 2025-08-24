package dev.himanshu.details.domain.repository

import dev.himanshu.details.domain.model.MovieDetails

interface DetailsRepository {

    suspend fun getMovieDetails(id: String): Result<MovieDetails>

}
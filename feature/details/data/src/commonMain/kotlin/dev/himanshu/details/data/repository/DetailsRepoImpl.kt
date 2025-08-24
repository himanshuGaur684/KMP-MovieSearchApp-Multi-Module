package dev.himanshu.details.data.repository

import dev.himanshu.coreNetwork.service.MovieApiService
import dev.himanshu.details.domain.model.MovieDetails
import dev.himanshu.details.domain.repository.DetailsRepository

class DetailsRepoImpl(
    private val service: MovieApiService
) : DetailsRepository {
    override suspend fun getMovieDetails(id: String): Result<MovieDetails> {
        return service.getMovieDetails(id).map {
            MovieDetails(
                title = it.title,
                imageUrl = buildImageUrl(it.poster_path),
                description = it.overview
            )
        }
    }

    private fun buildImageUrl(posterPath: String): String {
        return if (posterPath.isEmpty()) "" else "https://image.tmdb.org/t/p/original/$posterPath"
    }

}
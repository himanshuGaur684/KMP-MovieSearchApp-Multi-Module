package dev.himanshu.details.data.repository

import dev.himanshu.coreNework.service.MovieApiService
import dev.himanshu.details.domain.model.MovieDetails
import dev.himanshu.details.domain.repository.DetailsRepository

class DetailsRepoImpl(
    private val service: MovieApiService
) : DetailsRepository {


    override suspend fun getMovieDetails(id: String): Result<MovieDetails> {
        return service.getMovieDetails(id)
            .map { data->
                MovieDetails(
                    id = data.id.toString(),
                    title = data.title,
                    overView = data.overview,
                    imageUrl = buildImageUrl(data.poster_path?:"")
                )
            }
    }

    // https://image.tmdb.org/t/p/original/wigZBAmNrIhxp2FNGOROUAeHvdh.jpg
    private fun buildImageUrl(path: String): String {
        return if (path.isEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
    }
}
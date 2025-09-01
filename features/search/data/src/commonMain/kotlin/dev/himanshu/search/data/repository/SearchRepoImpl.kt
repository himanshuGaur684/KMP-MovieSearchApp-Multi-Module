package dev.himanshu.search.data.repository

import dev.himanshu.coreNework.service.MovieApiService
import dev.himanshu.search.domain.model.Movie
import dev.himanshu.search.domain.repository.SearchRepository

class SearchRepoImpl(
    private val service: MovieApiService
) : SearchRepository {
    override suspend fun search(q: String): Result<List<Movie>> {
        return service.search(q).map { list ->
            list.map { dto ->
                Movie(
                    id = dto.id.toString(),
                    title = dto.title,
                    imageUrl = buildImageUrl(dto.poster_path ?: "")
                )
            }
        }
    }

    // https://image.tmdb.org/t/p/original/wigZBAmNrIhxp2FNGOROUAeHvdh.jpg
    private fun buildImageUrl(path: String): String {
        return if (path.isEmpty()) "" else "https://image.tmdb.org/t/p/original/$path"
    }


}
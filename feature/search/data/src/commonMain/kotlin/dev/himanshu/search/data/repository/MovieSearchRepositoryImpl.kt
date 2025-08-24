package dev.himanshu.search.data.repository

 import dev.himanshu.coreNetwork.service.MovieApiService
 import dev.himanshu.search.domain.model.Movie
import dev.himanshu.search.domain.repository.SearchRepository

class MovieSearchRepositoryImpl(private val service: MovieApiService) : SearchRepository {
    override suspend fun search(q: String): Result<List<Movie>> {
        return service.getMovies(q).map { movies ->
            movies.map { movieDTO ->
                Movie(
                    id = movieDTO.id,
                    imageUrl = buildImageUrl(movieDTO.poster_path ?:"")
                )
            }
        }
    }

    private fun buildImageUrl(posterPath: String): String {
        return if(posterPath.isEmpty()) "" else "https://image.tmdb.org/t/p/original/$posterPath"
    }

}
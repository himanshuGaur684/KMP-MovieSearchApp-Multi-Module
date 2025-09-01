package dev.himanshu.coreNework.service

import dev.himanshu.coreNework.model.MovieDTO
import dev.himanshu.coreNework.model.MovieDetailsResponse
import dev.himanshu.coreNework.model.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiService(private val client: HttpClient) {

    suspend fun search(q: String): Result<List<MovieDTO>> {
        return try {
            // https://api.themoviedb.org/3/search/movie

            val searchResponse = client.get("3/search/movie") {
                parameter("query", q)
            }.body<SearchResponse>()

            Result.success(searchResponse.results)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // https://api.themoviedb.org/3/movie/{movie_id}
    suspend fun getMovieDetails(id: String): Result<MovieDetailsResponse> {
        return try {
            val movieDetailResponse = client.get("3/movie/${id}").body<MovieDetailsResponse>()
            Result.success(movieDetailResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
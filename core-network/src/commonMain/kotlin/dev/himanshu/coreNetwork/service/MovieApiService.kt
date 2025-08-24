package dev.himanshu.coreNetwork.service

import dev.himanshu.coreNetwork.model.MovieDTO
import dev.himanshu.coreNetwork.model.MovieDetailsResponse
import dev.himanshu.coreNetwork.model.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MovieApiService(private val client: HttpClient) {

    suspend fun getMovies(q: String): Result<List<MovieDTO>> {
        return try {
            val searchResponse = client.get("3/search/movie") {
                parameter("query", q)
                parameter("page", 1)
            }.body<SearchResponse>()
            Result.success(searchResponse.results)
        } catch (e: Exception) {
            println("Search Exception -> $e")
            Result.failure(e)
        }
    }

    suspend fun getMovieDetails(movieId: String): Result<MovieDetailsResponse> {
        return try {
            val movieResponse = client.get("3/movie/$movieId").body<MovieDetailsResponse>()
            Result.success(movieResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
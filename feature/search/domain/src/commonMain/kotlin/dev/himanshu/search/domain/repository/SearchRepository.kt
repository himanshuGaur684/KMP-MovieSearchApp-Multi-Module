package dev.himanshu.search.domain.repository

import dev.himanshu.search.domain.model.Movie

interface SearchRepository {

    suspend fun search(q: String): Result<List<Movie>>

}
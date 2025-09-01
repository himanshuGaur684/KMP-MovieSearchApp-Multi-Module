package dev.himanshu.search.domain.useCases

import dev.himanshu.search.domain.repository.SearchRepository

class SearchUseCase(private val searchRepository: SearchRepository) {

    suspend fun execute(q: String) = searchRepository.search(q)

}
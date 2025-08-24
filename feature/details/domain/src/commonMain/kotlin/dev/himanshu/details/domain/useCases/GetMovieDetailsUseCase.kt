package dev.himanshu.details.domain.useCases

import dev.himanshu.details.domain.repository.DetailsRepository

class GetMovieDetailsUseCase(private val detailsRepository: DetailsRepository) {

    suspend fun execute(movieId: String) = detailsRepository.getMovieDetails(movieId)

}
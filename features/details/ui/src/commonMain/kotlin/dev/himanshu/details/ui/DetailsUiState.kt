package dev.himanshu.details.ui

import dev.himanshu.details.domain.model.MovieDetails

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val movieDetails: MovieDetails? = null
)

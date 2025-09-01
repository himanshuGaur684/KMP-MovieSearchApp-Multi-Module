package dev.himanshu.search.ui

import dev.himanshu.search.domain.model.Movie

data class SearchUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: List<Movie>? = null
)

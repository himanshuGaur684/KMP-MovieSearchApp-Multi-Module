package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
)
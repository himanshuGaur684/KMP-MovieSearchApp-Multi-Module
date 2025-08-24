package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieDTO(
    val adult: Boolean,
    val id: Int,
    val poster_path: String?,
    val title: String,
)
package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollectionDTO(
    val backdrop_path: String,
    val id: Int,
    val name: String,
    val poster_path: String
)
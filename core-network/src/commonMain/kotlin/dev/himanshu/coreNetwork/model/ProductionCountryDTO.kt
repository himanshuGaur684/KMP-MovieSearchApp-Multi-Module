package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountryDTO(
    val iso_3166_1: String,
    val name: String
)
package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguageDTO(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)
package dev.himanshu.coreNetwork.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductionCompanyDTO(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)
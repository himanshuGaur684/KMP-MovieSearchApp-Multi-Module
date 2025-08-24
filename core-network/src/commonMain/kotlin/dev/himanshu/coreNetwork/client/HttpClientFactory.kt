package dev.himanshu.coreNetwork.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun getInstance() = HttpClient {
        install(ContentNegotiation) {
            json(
                Json { ignoreUnknownKeys = true }
            )
        }

        install(HttpTimeout){
            requestTimeoutMillis = 3000
            socketTimeoutMillis = 3000
            connectTimeoutMillis = 3000
        }

        install(DefaultRequest.Plugin) {
            url {
                host = "api.themoviedb.org"
                protocol = URLProtocol.Companion.HTTPS
            }
            header(
                HttpHeaders.Authorization,
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkYzcyNzdkNGE5MDhjNzg1M2Y5OTdmYjFkMGFkMDE5MSIsIm5iZiI6MTcyMDM2ODAyMC41MzMsInN1YiI6IjY2OGFiYjk0YWE5ZGUxNjE4OTlmMTQ0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.OyJOyJU_wKolDcEVSPKHovIc1zAg941ru2QULL66SkA"
            )
        }
    }
}
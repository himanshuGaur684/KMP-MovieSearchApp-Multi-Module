package dev.himanshu.coreNetwork.di

import dev.himanshu.coreNetwork.client.HttpClientFactory
import dev.himanshu.coreNetwork.service.MovieApiService
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single<HttpClient> { HttpClientFactory.getInstance() }
        single<MovieApiService> { MovieApiService(get()) }
    }
}
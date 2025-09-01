package dev.himanshu.coreNework.di

import dev.himanshu.coreNework.client.HttpClientFactory
import dev.himanshu.coreNework.service.MovieApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkModule(): Module {
    return module {
        single { MovieApiService(HttpClientFactory.getInstance()) }
    }
}
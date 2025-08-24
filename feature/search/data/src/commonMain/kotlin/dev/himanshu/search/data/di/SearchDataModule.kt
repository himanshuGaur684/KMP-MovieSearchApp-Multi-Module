package dev.himanshu.search.data.di

import dev.himanshu.search.data.repository.MovieSearchRepositoryImpl
import dev.himanshu.search.domain.repository.SearchRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule(): Module {
    return module {
        single<SearchRepository> { MovieSearchRepositoryImpl(get()) }
    }
}
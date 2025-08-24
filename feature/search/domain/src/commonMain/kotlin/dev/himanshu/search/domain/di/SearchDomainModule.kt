package dev.himanshu.search.domain.di

import dev.himanshu.search.domain.useCases.SearchMoviesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDomainModule(): Module {
    return module {
        factory { SearchMoviesUseCase(get()) }
    }
}
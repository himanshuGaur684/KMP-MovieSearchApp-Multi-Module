package dev.himanshu.search.domain.di

import dev.himanshu.search.domain.useCases.SearchUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDomainModule(): Module {
    return module {
        factory { SearchUseCase(get()) }
    }
}
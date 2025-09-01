package dev.himanshu.details.domain.di

import dev.himanshu.details.domain.useCases.GetMovieDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDomainModule(): Module {
    return module {
        factory { GetMovieDetailsUseCase(get()) }
    }
}